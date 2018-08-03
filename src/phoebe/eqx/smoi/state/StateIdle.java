package phoebe.eqx.smoi.state;

import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;
import ec02.utils.Log;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.ClientRequestInfo;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.utils.*;

import java.util.ArrayList;

public class StateIdle implements IAFState {

    @Override
    public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
        String nextState = AFState.IDLE;
        MyAppData myAppData = (MyAppData) instance;
        SmoiInstance smoiIns = myAppData.getSmoiIns();

        //TODO Standby_MaxRetry
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNameDs2a_Standby_MaxRetry);
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNameBssbroker_Standby_MaxRetry);
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNameBos_Standby_MaxRetry);
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNamePpgw_Standby_MaxRetry);
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNameSmoi_Standby_MaxRetry);
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNameE01_Standby_MaxRetry);
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNameIns_Standby_MaxRetry);
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNameAVATAR_Standby_MaxRetry); // Chatl 25/09/2017
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resourceNameEQL_Standby_MaxRetry);
        smoiIns.setResourceNameStandbyMaxRetry(af.getUtils(), Conf.resource_Name_Standby_MD_MaxRetry);
        

        for (EquinoxRawData r : rawDatas) {
            String requestMsg = r.getRawDataMessage();
            String invokeId = r.getInvoke();
            long requestTime = System.currentTimeMillis();
            String resourceName = r.getOrig();
            String sessionId = af.getEquinoxProperties().getSession();

            ClientRequestInfo requestInfo = smoiIns.getRequestInfo();
            requestInfo.setRequestMessage(requestMsg);
            requestInfo.setInvokeid(invokeId);
            requestInfo.setRequestTime(requestTime);
            requestInfo.setResourceName(resourceName);
            requestInfo.setSessionId(sessionId);

            Log.d("Save Request Info:SessionId=" + sessionId
                    + " ,resourceName=" + resourceName
                    + " ,invokeId=" + invokeId
                    + " ,requestTime=" + requestTime
                    + " ,requestMsg=" + requestMsg
                    + " ,sessionId=" + sessionId);

            String ms = SMOIUtils.getHttpPostValue(requestMsg, "ms");
            String page = SMOIUtils.getHttpPostValue(requestMsg, "page");
            String sgw = SMOIUtils.getHttpPostValue(requestMsg, "sgw");
            String ssid = SMOIUtils.getHttpPostValue(requestMsg, "ssid");

            if ("".equals(page) || page == null) {
                page = "Unknown Command";
            }
            if ("".equals(sgw) || sgw == null) {
                sgw = "Unknown Sgw";
            }
            if ("".equals(ssid) || ssid == null) {
                ssid = "Unknown Ssid";
            }

            smoiIns.setMsisdn(ms);
            smoiIns.setPage(page);
            smoiIns.setSgw(sgw);
            smoiIns.setSsid(ssid);
            smoiIns.setStartTime(System.currentTimeMillis());

            Log.d("Save Command Info:ms=" + ms
                    + " ,page=" + page
                    + " ,sgw=" + sgw
                    + " ,ssid=" + ssid
                    + " ,startTime=" + smoiIns.getStartTime());

            String inputMsg = r.getRawDataMessage();
            String outputMsg = "";
            String event = r.getRawEventType();
            Log.d("Command Event is " + event);

            if (event.equals(AFEvent.IncomingDispPPSInfo)
                    || event.equals(AFEvent.IncomingDispPPSPkgrew)
                    || event.equals(AFEvent.IncomingModiPPSValidity)
                    || event.equals(AFEvent.IncomingModiPPSMultiAttr)
                    || event.equals(AFEvent.IncomingAddScrScreenNo)
                    || event.equals(AFEvent.IncomingDeleScrScreenNo)
                    || event.equals(AFEvent.IncomingModiScrScreenType)
                    || event.equals(AFEvent.IncomingModiScrWhiteList)
                    || event.equals(AFEvent.IncomingDispScrScreenNo)
                    || event.equals(AFEvent.IncomingActPPSRBT)
                    || event.equals(AFEvent.IncomingDelePPSPkgres)
                    || event.equals(AFEvent.IncomingDelePPSPackageID)
                    || event.equals(AFEvent.IncomingPurchasePPSPackage)
                    || event.equals(AFEvent.IncomingSetPPSReward)
                    || event.equals(AFEvent.IncomingDispPPSFntelNo)
                    || event.equals(AFEvent.IncomingModiPPSLanguage)
                    || event.equals(AFEvent.IncomingModiPPSSMSLanguage)
                    || event.equals(AFEvent.IncomingModiPPSCreditLimit)
                    || event.equals(AFEvent.IncomingChgtrigChrgAcnt)
                    || event.equals(AFEvent.IncomingActivatePPSSingSub)
                    || event.equals(AFEvent.IncomingModiPPSCallNotifyFlag)
                    || event.equals(AFEvent.IncomingOtherCommand)) {

                boolean isInvalidReq = false;
                if ((event.equals(AFEvent.IncomingOtherCommand) && !page.equals("chgtrigChrgAcnt"))) {
                    isInvalidReq = false;
                } else {
                    isInvalidReq = new CheckInputParameter(smoiIns).isInvalidReq();  
                }
                
                if(event.equals(AFEvent.IncomingModiPPSCallNotifyFlag)){
                	SmoiStatAlarm.incrementStats(af, "ModiPPSCallNotifyFlag", StatAlarm.INGateway_Receive_HTTP_$s_Request, smoiIns);
                	smoiIns.setFlagModiPPSCallNotify(true);
                } else {
                	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_HTTP_$s_Request, smoiIns);
//                	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_HTTP_Request_$s, smoiIns);
                }

                if (!isInvalidReq) {
                    Log.d("Check Input Parameter: Success!!!");

                    String scpid = SMOIUtils.getHttpPostValue(smoiIns.getRequestInfo().getRequestMessage(), "scpid");
                    if (!scpid.equals("") || (event.equals(AFEvent.IncomingOtherCommand)
                            && !page.equals("chgtrigChrgAcnt"))) {
                        smoiIns.setScp(scpid);
                        MappingMessage messageFn = new MappingMessage(af);
                        outputMsg = messageFn.CreateMessage(myAppData);
                        String state = messageFn.getEState();
                        invokeId = myAppData.getSmoiIns().getRequestInfo().getInvokeid();

                        if (state.equals(AFState.W_BSSbroker)) {
                            SendMessage.BSSBroker(outputMsg, af, myAppData, Conf.resourceNameBssbroker_Active);
                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_BSSBroker_$s_Request, smoiIns);
                        } else if (state.equals(AFState.W_PPGW)) {
                            SendMessage.PPGW(outputMsg, af, myAppData, Conf.resourceNamePpgw_Active);
                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_SGSCP_PPGW_$s_Request, smoiIns);
                        } else if (state.equals(AFState.W_SMOI)) {
                            SendMessage.SMOI(outputMsg, af, myAppData, Conf.resourceNameSmoi_Active);
                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_SGSCP_SMOI_$s_Request, smoiIns);
                        } else if (state.equals(AFState.W_INS)) {
                            SendMessage.INS(outputMsg, af, myAppData, Conf.resourceNameIns_Active);
                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_INGW_INS_$s_Request, smoiIns);
                        } else if (state.equals(AFState.W_E01_DESTINATION)) {
                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_E01_QueryDestination_Request, smoiIns);
                        } else if (state.equals(AFState.W_EQL)) { // Chatl 20/09/2017
                        	SendMessage.eql(outputMsg, af, myAppData, Conf.resourceNameEQL_Active);
                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_E01_QueryDestination_Request, smoiIns);
                        }
                        nextState = state;
                    } else {
                        Log.d("Don't have scpid");
                        outputMsg = StateWaitDS2A.createInquirySubscriberRequestMessage(smoiIns , r);
                        SendMessage.DS2A(outputMsg, af, myAppData, Conf.resourceNameDs2a_Active);
                        if(event.equals(AFEvent.IncomingModiPPSCallNotifyFlag)){
                        	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_DS2A_InquirySubscriber_Request, smoiIns);
                        } else {
                        	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_DS2A_InquirySubscriber_Request, smoiIns);
                        }
                        nextState = AFState.W_DS2A;
                    }

                } else {
                    Log.d("Check Input Parameter: Fail!!!");
                    outputMsg = createIncompleteDataMessage(event);
                    SendMessage.Client(outputMsg, af, myAppData);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Incomplete_Data, smoiIns);
                    nextState = AFState.IDLE;
                }
                SmoiLog.writeSummaryLogReq(af, myAppData);
            } else if (event.equals(AFEvent.IncomingNoCommand)) { //Not Send page
                smoiIns.setPage("Unknown Command");
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_HTTP_$s_Request, smoiIns);
//                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_HTTP_Request_$s, smoiIns);
                outputMsg = createIncompleteDataMessage(event);
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Incomplete_Data, smoiIns);
//                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_Incomplete_Data_Response, smoiIns);
                nextState = AFState.IDLE;
                SmoiLog.writeSummaryLogReq(af, myAppData);
            } else {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_Unknown_Event, smoiIns);
                SendMessage.EqxClearInstance(af, myAppData);
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

    private String createIncompleteDataMessage(String event) {
        String msg;
        if(event.equals(AFEvent.IncomingModiPPSCallNotifyFlag)){
        	msg = "<vcrr><res>" + EResponseCode.INCOMPLETE_DATA.getCode()
            + "</res><desc>INGateway Send HTTP Response Incomplete Data</desc></vcrr>";
        } else {
	        msg = "<vcrr><res>" + EResponseCode.INCOMPLETE_DATA.getCode()
	                + "</res><desc>" + EResponseCode.INCOMPLETE_DATA.getDesc() + "</desc></vcrr>";
        }
        return msg;
    }
}
