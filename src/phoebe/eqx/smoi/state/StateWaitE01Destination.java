/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.state;

import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;
import ec02.utils.Log;

import java.util.ArrayList;

import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.AVATARMessage;
import phoebe.eqx.smoi.message.builder.BOSMessage;
import phoebe.eqx.smoi.message.builder.SendMessage;

import static phoebe.eqx.smoi.state.StateWaitDS2A.checkGroupBosLocation;

import phoebe.eqx.smoi.utils.SMOIUtils;
import phoebe.eqx.smoi.utils.SmoiStatAlarm;

/**
 * @author pavarisa
 */
public class StateWaitE01Destination implements IAFState {

    @Override
    public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
        String nextState = AFState.IDLE;
        MyAppData myAppData = (MyAppData) instance;
        SmoiInstance smoiIns = myAppData.getSmoiIns();

        for (EquinoxRawData r : rawDatas) {
            String requestMsg = r.getRawDataMessage();
            String outputMsg = "";
            String invokeId = r.getInvoke();
            if (r.getRawEventType().equals(AFEvent.IncomingE01Destination)) {
                String resultCode = SMOIUtils.getHttpPostValue(requestMsg, "resultcode");
                String description = SMOIUtils.getHttpPostValue(requestMsg, "description");
                Log.d("E01 ResultCode=" + resultCode);
                if ("0".equals(resultCode)) {
//                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Success, smoiIns);
                    String datas = SMOIUtils.getHttpPostValue(requestMsg, "data");
                    Log.d("E01 Data=" + datas);
                    String[] data = datas.split("\\|", -1);
                    if (data.length < 3 || data[1].isEmpty() || data[2].isEmpty()) {
                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Incomplete_Data, smoiIns);
                        outputMsg = createE01ErrorMessage();
                        SendMessage.Client(outputMsg, af, myAppData);
                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                        nextState = AFState.IDLE;
                    } else {
                        String state = data[0];
                        String destinationHost = data[1];
                        String destinationRealm = data[2];

                        if ("1".equals(state)) {
                            Log.d("E01 State is " + datas + ",destinationHost=" + destinationHost + " ,destinationRealm=" + destinationRealm);
                            smoiIns.setDestinationHost(destinationHost);
                            smoiIns.setDestinationRealm(destinationRealm);

                            String scpId = smoiIns.getScp();
                            String noGroupBos = checkGroupBosLocation(scpId, af);
                            Log.d("scpId=" + scpId + ",noGroupBos=" + noGroupBos);

                            if (SMOIUtils.isAVATARProfile(af, scpId)) {
                                outputMsg = AVATARMessage.mappingMessage(af, myAppData);
                                SendMessage.AVATAR(outputMsg, af, myAppData, Conf.resourceNameAVATAR_Active);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Success, smoiIns);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_AVATAR_$s_Request, smoiIns);
                                nextState = AFState.W_AVATAR;
                            } else {
                                outputMsg = BOSMessage.mappingMessage(af, myAppData);
                                if (!noGroupBos.isEmpty()) {
                                    SendMessage.BOS(outputMsg, af, myAppData, Conf.resourceNameBos_Active + noGroupBos);
                                } else {
                                    SendMessage.BOS(outputMsg, af, myAppData, Conf.resourceNameBos_Active);
                                }
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Success, smoiIns);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_BOS_$s_Request, smoiIns);
                                nextState = AFState.W_BOS_DCC;
                            }
                        } else {
                            Log.d("E01 State is " + datas + ",State Not Used");
                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Error, smoiIns);
                            outputMsg = createE01ErrorMessage();
                            SendMessage.Client(outputMsg, af, myAppData);

                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                            nextState = AFState.IDLE;
                        }
                    }
                } else if ("32".equals(resultCode)) {
                    Log.d("E01 ResultCode=32 (No Such Object)");
                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_NoSuchObject, smoiIns);
                    outputMsg = createE01NoSuchObjectErrorMessage();
                    SendMessage.Client(outputMsg, af, myAppData);

                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                    nextState = AFState.IDLE;
                } else {
                    Log.d("E01 ResultCode=" + resultCode + " (" + description + ")");
                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Error, smoiIns);
                    outputMsg = createE01ErrorMessage();
                    SendMessage.Client(outputMsg, af, myAppData);

                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                    nextState = AFState.IDLE;
                }
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Error, smoiIns);
                outputMsg = createEquinoxErrorMessage();
                SendMessage.Client(outputMsg, af, myAppData);

                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Reject, smoiIns);
                int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resourceNameE01_Standby_MaxRetry);
                if (maxRetry > 0) {
                    //TODO Standby_MaxRetry
                    ec02.utils.Log.d("Retry: " + Conf.resourceNameE01_Standby_MaxRetry + " maxRetry: " + maxRetry);
                    maxRetry -= 1;
                    smoiIns.setResourceNameStandbyMaxRetry(Conf.resourceNameE01_Standby_MaxRetry, maxRetry);
                    requestMsg = "";
                    outputMsg = SendMessage.QueryE01Destination(af, myAppData);

                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_E01_QueryDestination_Request, smoiIns);
                    nextState = AFState.W_E01_DESTINATION;
                } else {

                    outputMsg = createEquinoxRejectMessage();
                    SendMessage.Client(outputMsg, af, myAppData);

                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                    nextState = AFState.IDLE;
                }
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Abort, smoiIns);
                outputMsg = createEquinoxAbortMessage();
                SendMessage.Client(outputMsg, af, myAppData);

                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_E01_Query_DestinationResponse_Timeout, smoiIns);
                outputMsg = createE01TimeoutMessage();
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
            myAppData.setInput_Msg(requestMsg);
            myAppData.setInput_InvokeId(invokeId);
            myAppData.setInput_resultcode(res);
            myAppData.setInput_desc(desc);
            myAppData.setOutput_Msg(outputMsg);
        }
        return nextState;
    }

    private String createE01NoSuchObjectErrorMessage() {
        String msg;
        String res = EResponseCode.E01_QueryDestinationResposne_NoSuchObject_Error.getCode();
        String desc = EResponseCode.E01_QueryDestinationResposne_NoSuchObject_Error.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createE01ErrorMessage() {
        String msg;
        String res = EResponseCode.E01_QueryDestinationResposne_Error.getCode();
        String desc = EResponseCode.E01_QueryDestinationResposne_Error.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createEquinoxErrorMessage() {
        String msg;
        String res = EResponseCode.E01_QueryDestinationRequest_Error.getCode();
        String desc = EResponseCode.E01_QueryDestinationResposne_Error.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createEquinoxRejectMessage() {
        String msg;
        String res = EResponseCode.E01_QueryDestinationRequest_Reject.getCode();
        String desc = EResponseCode.E01_QueryDestinationResposne_Error.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createEquinoxAbortMessage() {
        String msg;
        String res = EResponseCode.E01_QueryDestinationRequest_Abort.getCode();
        String desc = EResponseCode.E01_QueryDestinationResposne_Error.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createE01TimeoutMessage() {
        String msg;
        String res = EResponseCode.E01_QueryDestinationRequest_Timeout.getCode();
        String desc = EResponseCode.E01_QueryDestinationRequest_Timeout.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

}
