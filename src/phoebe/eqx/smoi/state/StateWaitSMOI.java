package phoebe.eqx.smoi.state;

import java.util.ArrayList;
  
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.utils.MappingMessage;
import phoebe.eqx.smoi.utils.SMOIUtils;
import phoebe.eqx.smoi.utils.SmoiStatAlarm;

public class StateWaitSMOI implements IAFState {

    @Override
    public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
        String nextState = AFState.IDLE;
        MyAppData myAppData = (MyAppData) instance;

        for (EquinoxRawData r : rawDatas) {
            String inputMsg=r.getRawDataMessage();
        	String outputMsg="";
            String invokeId = r.getInvoke();
            SmoiInstance smoiIns = myAppData.getSmoiIns();
            if (r.getRawEventType().equals(AFEvent.IncomingHttpRes)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_SGSCP_SMOI_$s_Response, smoiIns);
                  
                if(r.getRawDataAttribute("ctype").equals("text/xml")){
                	 outputMsg = r.getRawDataMessage(); 
                }else if(r.getRawDataAttribute("ctype").equals("text/plain")
                		||r.getRawDataAttribute("ctype").equals("text/html")){
                	 outputMsg  = r.getRawDataAttribute("val");
                	 outputMsg  = outputMsg.replaceAll("<\\?xml.*?\\?>","");
                }
                //ec02.utils.Log.d("=============>msg:"+msg);
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {
            	int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resourceNameSmoi_Standby_MaxRetry);
            	if(maxRetry>0){       
            		 //TODO Standby_MaxRetry
            		ec02.utils.Log.d("Retry: "+Conf.resourceNameSmoi_Standby_MaxRetry+" maxRetry: "+maxRetry);
             		maxRetry -= 1;
                    smoiIns.setResourceNameStandbyMaxRetry(Conf.resourceNameSmoi_Standby_MaxRetry,maxRetry);
                    
                    MappingMessage messageFn = new MappingMessage(af);
                    inputMsg="";
                    outputMsg = messageFn.CreateMessage(myAppData);
                    nextState=messageFn.getEState();
                    
                    SendMessage.SMOI(outputMsg, af, myAppData,Conf.resourceNameSmoi_Standby);
	            	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_SGSCP_SMOI_$s_Response_Reject, smoiIns);
                   	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_SGSCP_SMOI_$s_Request, smoiIns);
             	}else{
	            	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_SGSCP_SMOI_$s_Response_Reject, smoiIns);
	                outputMsg = mapEquinoxRejectCmd();
	                SendMessage.Client(outputMsg, af, myAppData);
	                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
	                nextState = AFState.IDLE;
             	}
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_SGSCP_SMOI_$s_Response_Abort, smoiIns);
                outputMsg = mapEquinoxAbortCmd();
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_SGSCP_SMOI_$s_Response_Timeout, smoiIns);
                outputMsg = mapEquinoxTimoutCmd();
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_SGSCP_SMOI_$s_Response_Error, smoiIns);
                outputMsg = mapEquinoxErrorCmd();
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_Unknown_Event, smoiIns);
                SendMessage.EqxClearInstance(af, myAppData);
                nextState = AFState.IDLE;
            }
 
            String res = SMOIUtils.getXMLText(outputMsg, "res");
            String desc = SMOIUtils.getXMLText(outputMsg, "desc");
            myAppData.setInput_Msg(inputMsg);
            myAppData.setInput_InvokeId(invokeId);
            myAppData.setInput_resultcode(res);
            myAppData.setInput_desc(desc);
            myAppData.setOutput_Msg(outputMsg);
        }
        return nextState;
    }

    private String mapEquinoxRejectCmd() {
        String msg;
        String res = EResponseCode.SGSCP_SMOI_Reject.getCode();
        String desc = EResponseCode.SGSCP_SMOI_Reject.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String mapEquinoxErrorCmd() {
        String msg;
        String res = EResponseCode.SGSCP_SMOI_Error.getCode();
        String desc = EResponseCode.SGSCP_SMOI_Error.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String mapEquinoxAbortCmd() {
        String msg;
        String res = EResponseCode.SGSCP_SMOI_Abort.getCode();
        String desc = EResponseCode.SGSCP_SMOI_Abort.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String mapEquinoxTimoutCmd() {
        String msg;
        String res = EResponseCode.SGSCP_SMOI_Timeout.getCode();
        String desc = EResponseCode.SGSCP_SMOI_Timeout.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }
}
