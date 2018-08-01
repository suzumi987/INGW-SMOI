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

public class StateWaitPPGW implements IAFState {

    @Override
    public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
        String nextState = AFState.IDLE;
        MyAppData myAppData = (MyAppData) instance;

        for (EquinoxRawData r : rawDatas) {
        	String inputMsg=r.getRawDataMessage();
            String outputMsg = "";
            String invokeId = r.getInvoke();
            SmoiInstance smoiIns = myAppData.getSmoiIns();
            if (r.getRawEventType().equals(AFEvent.IncomingHttpRes)) {
                String res = SMOIUtils.getXMLText(r.getRawDataMessage(), "res");
                if (res.equals("000")) {
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_SGSCP_PPGW_$s_Response, smoiIns);
                    outputMsg = mapSgscPPPGWRechargeBalanceCmdSuccess(r.getRawDataMessage());
                 } else {
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_SGSCP_PPGW_$s_Response_Error, smoiIns);
                    outputMsg = mapSgscPPPGWRechargeBalanceCmdError(r.getRawDataMessage());
                 }
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {
            	int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resourceNamePpgw_Standby_MaxRetry);
            	if(maxRetry>0){       
            		 //TODO Standby_MaxRetry
            		ec02.utils.Log.d("Retry: "+Conf.resourceNamePpgw_Standby_MaxRetry+" maxRetry: "+maxRetry);
             		maxRetry -= 1;
                    smoiIns.setResourceNameStandbyMaxRetry(Conf.resourceNamePpgw_Standby_MaxRetry,maxRetry);
                    
                    MappingMessage messageFn = new MappingMessage(af);
                    inputMsg="";
                    outputMsg = messageFn.CreateMessage(myAppData);
                    nextState=messageFn.getEState();
                    
                    SendMessage.PPGW(outputMsg, af, myAppData,Conf.resourceNamePpgw_Standby);
	                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_SGSCP_PPGW_$s_Response_Reject, smoiIns);
                   	SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_SGSCP_PPGW_$s_Request, smoiIns);
             	}else{
	                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_SGSCP_PPGW_$s_Response_Reject, smoiIns);
	                outputMsg = mapEquinoxRejectCmd();
	                SendMessage.Client(outputMsg, af, myAppData);
	                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
	                nextState = AFState.IDLE;
             	}
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_SGSCP_PPGW_$s_Response_Abort, smoiIns);
                outputMsg = mapEquinoxAbortCmd();
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_SGSCP_PPGW_$s_Response_Error, smoiIns);
                outputMsg = mapEquinoxErrorCmd();
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_SGSCP_PPGW_$s_Response_Timeout, smoiIns);
                outputMsg = mapEquinoxTimeoutCmd();
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

    private String mapSgscPPPGWRechargeBalanceCmdSuccess(String msg) {
        String res = SMOIUtils.getXMLText(msg, "res");
        String desc = SMOIUtils.getXMLText(msg, "desc");
        String balance = SMOIUtils.getXMLText(msg, "balance");
        String activestop = SMOIUtils.getXMLText(msg, "activestop");
        StringBuilder sb = new StringBuilder();
        sb.append("<vcrr>");
        sb.append("<res>").append(res).append("</res>");
        sb.append("<desc>").append(desc).append("</desc>");
        if (!"".equals(balance)) {
            sb.append("<balance>").append(balance).append("</balance>");
        }
        if (!"".equals(activestop)) {
            sb.append("<activestop>").append(activestop).append("</activestop>");
        }
        sb.append("<value></value>");
        sb.append("<valueadd></valueadd>");
        sb.append("<validity></validity>");
        sb.append("<validityadd></validityadd>");
        sb.append("</vcrr>");
        return sb.toString();
    }

    private String mapEquinoxRejectCmd() {
        String msg;
        String res = EResponseCode.SGSCP_PPGW_Reject.getCode();
        String desc = EResponseCode.SGSCP_PPGW_Reject.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String mapEquinoxAbortCmd() {
        String msg;
        String res = EResponseCode.SGSCP_PPGW_Abort.getCode();
        String desc = EResponseCode.SGSCP_PPGW_Abort.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String mapEquinoxErrorCmd() {
        String msg;
        String res = EResponseCode.SGSCP_PPGW_Error.getCode();
        String desc = EResponseCode.SGSCP_PPGW_Abort.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String mapEquinoxTimeoutCmd() {
        String msg;
        String res = EResponseCode.SGSCP_PPGW_Timeout.getCode();
        String desc = EResponseCode.SGSCP_PPGW_Timeout.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String mapSgscPPPGWRechargeBalanceCmdError(String msg) {
        String res = EResponseCode.SGSCP_PPGW_Error.getCode();
        String desc = EResponseCode.SGSCP_PPGW_Error.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }
}
