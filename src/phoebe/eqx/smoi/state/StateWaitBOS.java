package phoebe.eqx.smoi.state;

import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;
import ec02.utils.Log;
import phoebe.eqx.model.dcc.cca.*;
import phoebe.eqx.model.ldap.ds2a.InquirySubscriberResponse;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.enums.CommandBOS;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.BOSMessage;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.message.parser.bos.DiameterCCA;
import phoebe.eqx.smoi.utils.MappingMessage;
import phoebe.eqx.smoi.utils.SmoiStatAlarm;
import smoi.enums.*;
import smoi.message.OctetString;

import java.text.SimpleDateFormat;
import java.util.*;

public class StateWaitBOS implements IAFState {

    private OctetString octet = new OctetString();

    @Override
    public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
        String nextState = AFState.IDLE;
        MyAppData myAppData = (MyAppData) instance;
        SmoiInstance smoiIns = myAppData.getSmoiIns();

        String page = smoiIns.getPage();

//        if (af.getEquinoxProperties().isTimeout()) {
//
//        }
        for (EquinoxRawData r : rawDatas) {
            String inputMsg = r.getRawDataMessage();
            String outputMsg = "";
            HashMap<String, String> hmMessage = null;
            String log_RESULTCODE = "";
            String log_DESC = "";
            String invokeId = r.getInvoke();
            if (r.getRawEventType().equals(AFEvent.IncomingCCA)) {
            	boolean flagError = false;
                //DiameterCCA dcca = new DiameterCCA();
                DiameterCreditControlAnswer cca = DiameterCCA.createDiameterCreditControlAnswer(r.getRawDataMessage());
                Log.d("page =" + page);
                
                if (page.equals(ECommand.dispPPSInfo.getCommand())) {
                	smoiIns.setCca(cca);
                	smoiIns.setBssbrokerFlag(true);
                	
                    String customerLifeCycleState = "";
                    ServiceInformation serviceInfo = cca.getServiceInformation();
                    if (serviceInfo != null) {
                    	 INInformation inInf = serviceInfo.getInInformation();
                         if (inInf != null) {
                             customerLifeCycleState = octet.Convert2String(inInf.getCustomerLifeCycleState());
                         }
                    }

//                    String sessionId = cca.getSessionId();
//                    String originHost = cca.getOriginHost();
//                    String originRealm = cca.getOriginRealm();
//                    String authApplicationId = cca.getAuthApplicationId();
//                    String ccRequestType = cca.getCcRequestType();
//                    String ccRequestNumber = cca.getCcRequestNumber();
//                    ServiceInformation serviceInformation = cca.getServiceInformation();

                    if (cca.getResultCode().equals("2001") || (cca.getResultCode().equals("5003")
                            && (customerLifeCycleState.equals("0")
                            || customerLifeCycleState.equals("8")
                            || customerLifeCycleState.equals("9")))) {
                    	
                        if (cca.getResultCode().equals("2001")) {
                            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response, smoiIns);
                        	
                            /*MappingMessage messageFn = new MappingMessage(af);
                            outputMsg = messageFn.CreateMessage(myAppData);
                            
                            SendMessage.BSSBroker(outputMsg, af, myAppData, Conf.resourceNameBssbroker_Active);
                            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_BOS_BSSBroker_$s_Request, smoiIns);
                            */
                        } else if (cca.getResultCode().equals("5003")) {
                            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);

                        }
                        
                        MappingMessage messageFn = new MappingMessage(af);
                        outputMsg = messageFn.CreateMessage(myAppData);
                        
                        SendMessage.BSSBroker(outputMsg, af, myAppData, Conf.resourceNameBssbroker_Active);
                        SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_BSSBroker_$s_Request, smoiIns);
//                        Log.d("outputMsg=" + outputMsg);
                    } else {

                        SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);
                        String res = cca.getResultCode();
                        String desc = "FAILED";
                        flagError = true;
                        if (cca.getResultCode().equals("5004")) {
                            res = "322";
                            desc = "Invalid AVP Value";

                            log_RESULTCODE = res;
                            log_DESC = desc;
                        } else if (cca.getResultCode().equals("5005")) {
                            res = "322";
                            desc = "Missing AVP Value";

                            log_RESULTCODE = res;
                            log_DESC = desc;
                        } else {
                            log_RESULTCODE = res;
                            log_DESC = desc;
                        }

                        StringBuilder sb = new StringBuilder();

                        sb.append("<vcrr>")
                                .append("<res>").append(res).append("</res>")
                                .append("<desc>").append(desc).append("</desc>")
                                .append("</vcrr>");

                        HashMap<String, String> msgReturn = new HashMap<String, String>();

                        msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
                        msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
                        msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);

                        outputMsg = msgReturn.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                        log_RESULTCODE = msgReturn.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                        log_DESC = msgReturn.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                    }
                    /* hmMessage = mapDispPPSInfoCmd(cca, af, smoiIns);
                    outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                    log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                    log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());*/
                } else if (page.equals(ECommand.dispPPSPkgrew.getCommand())) {
                    hmMessage = mapDispPPSPkgrewCmd(cca, af, smoiIns);
                    outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                    log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                    log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                } else if (page.equals(ECommand.modiPPSValidity.getCommand())) {
                    hmMessage = mapModiPPSValidityCmd(cca, af, smoiIns);
                    outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                    log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                    log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                } else if (page.equals(ECommand.modiPPSMultiAttr.getCommand()) || page.equals(ECommand.setPPSReward.getCommand())) {
                    //select sub command  of modiPPSMultiAttr      
                    String balance = smoiIns.getHttpPostValue("balance");
                    String validity = smoiIns.getHttpPostValue("validity");
                    String prmmoney = smoiIns.getHttpPostValue("prmmoney");
                    String prmsm = smoiIns.getHttpPostValue("prmsm");
                    String prmminute = smoiIns.getHttpPostValue("prmminute");
                    String point = smoiIns.getHttpPostValue("point");
                    String freecall = smoiIns.getHttpPostValue("freecall");
                    String times = smoiIns.getHttpPostValue("times");
                    String freerbtsong = smoiIns.getHttpPostValue("freerbtsong");
                    String freerbtmf = smoiIns.getHttpPostValue("freerbtmf");
                    String score = smoiIns.getHttpPostValue("score");
                    String prmscore = smoiIns.getHttpPostValue("prmscore");
                    String smusage = smoiIns.getHttpPostValue("smusage");

                    if (page.equals(ECommand.setPPSReward.getCommand())) {
                        balance = smoiIns.getHttpPostValue("balance");
                        prmmoney = smoiIns.getHttpPostValue("prmMoney");
                        prmsm = smoiIns.getHttpPostValue("prmSm");
                        prmminute = smoiIns.getHttpPostValue("prmMinute");
                        point = smoiIns.getHttpPostValue("prmPoint");
                        freecall = smoiIns.getHttpPostValue("freeCallTimes");
                        freerbtsong = smoiIns.getHttpPostValue("rbtSong");
                        freerbtmf = smoiIns.getHttpPostValue("rbtMF");
                        //smDis = smoiIns.getHttpPostValue("smDis");
                        //callDis = smoiIns.getHttpPostValue("callDis");
                        //rewTariff = smoiIns.getHttpPostValue("rewTariff");
                        //expire = smoiIns.getHttpPostValue("expire");
                    }

                    if (((balance != null && !balance.trim().equals("")) || (validity != null && !validity.trim().equals("")))
                            && (prmmoney == null || prmmoney.trim().equals(""))
                            && (prmsm == null || prmsm.trim().equals(""))
                            && (prmminute == null || prmminute.trim().equals(""))
                            && (point == null || point.trim().equals(""))
                            && (freecall == null || freecall.trim().equals(""))
                            && (times == null || times.trim().equals(""))
                            && (freerbtsong == null || freerbtsong.trim().equals(""))
                            && (freerbtmf == null || freerbtmf.trim().equals(""))
                            && (freerbtsong == null || freerbtsong.trim().equals(""))
                            && (score == null || score.trim().equals(""))
                            && (prmscore == null || prmscore.trim().equals(""))
                            && (smusage == null || smusage.trim().equals(""))) {
						// balance  or validity   is not null 
                        // and prmmoney, prmsm, prmminute, point, freecall,times, freerbtsong
                        //, freerbtmf, score, prmscore, smusage parameter is null
                        hmMessage = mapModiPPSMultiAttrCmd_Modify_Balance_and_Validity(cca, af, smoiIns);
                        outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                        log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                        log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                    } else if ((balance == null || balance.trim().equals(""))
                            && ((prmmoney != null && !prmmoney.trim().equals(""))
                            || (prmsm != null && !prmsm.trim().equals(""))
                            || (prmminute != null && !prmminute.trim().equals(""))
                            || (point != null && !point.trim().equals(""))
                            || (freecall != null && !freecall.trim().equals(""))
                            || (times != null && !times.trim().equals(""))
                            || (freerbtsong != null && !freerbtsong.trim().equals(""))
                            || (freerbtmf != null && !freerbtmf.trim().equals(""))
                            || (freerbtsong != null && !freerbtsong.trim().equals(""))
                            || (score != null && !score.trim().equals(""))
                            || (prmscore != null && !prmscore.trim().equals(""))
                            || (smusage != null && !smusage.trim().equals("")))) {
						// balance  is null 
                        // and prmmoney, prmsm, prmminute, point, freecall,times, freerbtsong
                        //, freerbtmf, score, prmscore, smusage parameter one is not null
                        hmMessage = mapModiPPSMultiAttrCmd_Modify_Free_Resource_Adjustment(cca, af, smoiIns);
                        outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                        log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                        log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                    }

                    /**
                     * @see phase 2 }else if
                     * (page.equals(ECommand.chgtrigChrgAcnt.getCommand())) {
                     * msg = mapChgtrigChrgAcntCmd(cca);
                     */
                }
                
                
                if(page.equals(ECommand.dispPPSInfo.getCommand())){
					if(!flagError){
						nextState = AFState.W_BSSbroker;
					}else{
						nextState = AFState.IDLE;
						SendMessage.Client(outputMsg, af, myAppData);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					}
					
				}else{
					//other command
					nextState = AFState.IDLE;
					SendMessage.Client(outputMsg, af, myAppData);
					if(log_RESULTCODE.equals("000")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
					}else{
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					}
				}
                
                
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {
                int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resourceNameBos_Standby_MaxRetry);
                if (maxRetry > 0) {
                    //TODO Standby_MaxRetry
                    ec02.utils.Log.d("Retry: " + Conf.resourceNameBos_Standby_MaxRetry + " maxRetry: " + maxRetry);
                    maxRetry -= 1;
                    smoiIns.setResourceNameStandbyMaxRetry(Conf.resourceNameBos_Standby_MaxRetry, maxRetry);

                    //MappingMessage messageFn = new MappingMessage(af);
                    inputMsg = "";
                    //outputMsg = messageFn.CreateMessage(myAppData);
                    outputMsg = BOSMessage.mappingMessage(af, myAppData);
                    nextState = AFState.W_BOS_DCC;

                    String noGroupBos = StateWaitDS2A.checkGroupBosLocation(smoiIns.getScp(), af);
                    if (!"".equals(noGroupBos)) {
                        SendMessage.BOS(outputMsg, af, myAppData, Conf.resourceNameBos_Standby + noGroupBos);
                    } else {
                        SendMessage.BOS(outputMsg, af, myAppData, Conf.resourceNameBos_Standby);
                    }
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Reject, smoiIns);
//                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_BOS_$s_Response, smoiIns);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_BOS_$s_Request, smoiIns);
                } else {
//                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Request_Reject, smoiIns);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Reject, smoiIns);
//                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
//                    if (page.equals(ECommand.dispPPSInfo.getCommand()) || page.equals(ECommand.dispPPSPkgrew.getCommand())) {
//                        hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_REJECT);
//                    } else if (page.equals(ECommand.modiPPSValidity.getCommand()) || page.equals(ECommand.modiPPSMultiAttr.getCommand()) || page.equals(ECommand.setPPSReward.getCommand())) {
//                        hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_REJECT);
//                    } else {
//                        hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_REJECT);
//                    }
                    
                    if (smoiIns.getMapCmd().equals(CommandBOS.ACCOUNT_QUERY)) {
                        hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_REJECT);
                    } else if (smoiIns.getMapCmd().equals(CommandBOS.ACCOUNT_ADJUSTMENT)) {
                        hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_REJECT);
                    } else if (smoiIns.getMapCmd().equals(CommandBOS.ADJUST_ASSET)) {
                        hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ADJUSTASSET_RESPONSE_REJECT);
                    }
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.CCR_Reject);
                    outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                    log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                    log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                    SendMessage.Client(outputMsg, af, myAppData);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
//                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_Internal_Error_Response, smoiIns);
                    nextState = AFState.IDLE;
                }
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Abort, smoiIns);

//                if (page.equals(ECommand.dispPPSInfo.getCommand()) || page.equals(ECommand.dispPPSPkgrew.getCommand())) {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_ABORT);
//                } else if (page.equals(ECommand.modiPPSValidity.getCommand()) || page.equals(ECommand.modiPPSMultiAttr.getCommand()) || page.equals(ECommand.setPPSReward.getCommand())) {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_ABORT);
//                } else {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_ABORT);
//                }
                
                if (smoiIns.getMapCmd().equals(CommandBOS.ACCOUNT_QUERY)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_ABORT);
                } else if (smoiIns.getMapCmd().equals(CommandBOS.ACCOUNT_ADJUSTMENT)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_ABORT);
                } else if (smoiIns.getMapCmd().equals(CommandBOS.ADJUST_ASSET)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ADJUSTASSET_RESPONSE_ABORT);
                }
                outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);

//                if (page.equals(ECommand.dispPPSInfo.getCommand()) || page.equals(ECommand.dispPPSPkgrew.getCommand())) {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_ERROR);
//                } else if (page.equals(ECommand.modiPPSValidity.getCommand()) || page.equals(ECommand.modiPPSMultiAttr.getCommand()) || page.equals(ECommand.setPPSReward.getCommand())) {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_ERROR);
//                } else {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_ERROR);
//                }
                
                if (smoiIns.getMapCmd().equals(CommandBOS.ACCOUNT_QUERY)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_ERROR);
                } else if (smoiIns.getMapCmd().equals(CommandBOS.ACCOUNT_ADJUSTMENT)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_ERROR);
                } else if (smoiIns.getMapCmd().equals(CommandBOS.ADJUST_ASSET)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ADJUSTASSET_RESPONSE_ERROR);
                }

                outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Timeout, smoiIns);

//                if (page.equals(ECommand.dispPPSInfo.getCommand()) || page.equals(ECommand.dispPPSPkgrew.getCommand())) {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_TIMEOUT);
//                } else if (page.equals(ECommand.modiPPSValidity.getCommand()) || page.equals(ECommand.modiPPSMultiAttr.getCommand()) || page.equals(ECommand.setPPSReward.getCommand())) {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_TIMEOUT);
//                } else {
//                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_TIMEOUT);
//                }
                
                if (smoiIns.getMapCmd().equals(CommandBOS.ACCOUNT_QUERY)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_TIMEOUT);
                } else if (smoiIns.getMapCmd().equals(CommandBOS.ACCOUNT_ADJUSTMENT)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_TIMEOUT);
                } else if (smoiIns.getMapCmd().equals(CommandBOS.ADJUST_ASSET)) {
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_BOS_ADJUSTASSET_RESPONSE_TIMEOUT);
                }
//                hmMessage = mapEquinoxReturnCmd(EResponseCode.CCR_Timeout);
                outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_Unknown_Event, smoiIns);
                SendMessage.EqxClearInstance(af, myAppData);
                nextState = AFState.IDLE;
            }

            myAppData.setInput_Msg(inputMsg);
            myAppData.setInput_InvokeId(invokeId);
            myAppData.setInput_resultcode(log_RESULTCODE);
            myAppData.setInput_desc(log_DESC);
            myAppData.setOutput_Msg(outputMsg);
        }
        return nextState;
    }

    private HashMap<String, String> mapDispPPSInfoCmd(DiameterCreditControlAnswer cca, AbstractAF af, SmoiInstance smoiIns) {
        HashMap<String, String> msgReturn = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("");

        String res = cca.getResultCode();
        String desc = "FAILED";

        String log_RESULTCODE = "";
        String log_DESC = "";

        //get Data from CCA
        String msisdn = smoiIns.getMsisdn();
        long balance = 0;
        String subspid = "";
		//TODO	    											   ds2classOfservice, 
        //<AVP type="accountingInfo"><vals value="600001,3BOSC103,66818310536,3511,,3BOCB802,," />
        InquirySubscriberResponse inquirySubscriber = smoiIns.getInquirySubscriberResponse();
        String accountingInfo = "";
        String ds2classOfservice = "";
        if (inquirySubscriber != null) {
            accountingInfo = inquirySubscriber.getAccountingInfo();
            if (accountingInfo != null && !accountingInfo.trim().equals("")) {
                ds2classOfservice = accountingInfo.split(",")[0];
            }
        }
        //String subcosid = ds2classOfservice;
        String subcosid = "";
        String accountstate = "";
        String activestop = "";
        String suspendstop = "";
        String disablestop = "";
        String servicestop = "";
        String callingscreenflag = "";
        String roamflag = "";
        String fraudlock = "";
        String servicearea = "";
        String maxactivedays = "";
        String maxcounttotal = "";
        String languagetype = "";
        long creditlimit = 0;
        String score = "";
        String voicemail = "";
        String activecac = "";
        String groupid = "";
        long prmsm = 0;
        long prmminute = 0;
        String querytimes = "";
        String rbtflag = "";
        String agingdis = "";
        String timeenteractive = "";
        String ccc = "";
        String volumndiscountflag = "";
        String fphflag = "";
        String lastfph = "";
        String callscreenflag = "";
        String interroamflag = "";
        String smslangtype = "";
        String smusage = "";
        String smusagetoptime = "";
        String prmscore = "";
        String prmscorestoptime = "";
        String scorestoptime = "";
        String prmpointexp = "";
        String calldis = "";
        String calldisexp = "";
        String smdis = "";
        String smdisexp = "";
        String smstamp = "";
        String ivrprmtimes = "";
        String ussdbtimes = "";
        String ussdprmtimes = "";
        String prmpoint = "";
        String paytype = "";
        String brandid = "";
        String paymentMode = null;
        String callnotifyflag = "";
        //ServiceInformation
        ServiceInformation serviceInfo = cca.getServiceInformation();
        String customerLifeCycleState = "";
        if (serviceInfo != null) {
            //INInformation
            INInformation inInf = serviceInfo.getInInformation();
            SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            if (inInf != null) {
                customerLifeCycleState = octet.Convert2String(inInf.getCustomerLifeCycleState());
                //mapping EAccountState 
                for (EAccountState e : EAccountState.values()) {
                    if (e.getResultCode().equalsIgnoreCase(res)
                            && e.getCustomerLifeCycle().equalsIgnoreCase(customerLifeCycleState)) {
                        accountstate = e.getAccountState();
                        break;
                    }
                }

                if (inInf.getActivePeriod() != null && !inInf.getActivePeriod().trim().equals("")) {
                    activestop = octet.Convert2String(inInf.getActivePeriod());
                }
                if (inInf.getSuspendPeriod() != null && !inInf.getSuspendPeriod().trim().equals("")) {
                    suspendstop = octet.Convert2String(inInf.getSuspendPeriod());
                }
                if (inInf.getDisablePeriod() != null && !inInf.getDisablePeriod().trim().equals("")) {
                    disablestop = octet.Convert2String(inInf.getDisablePeriod());
                    servicestop = octet.Convert2String(inInf.getDisablePeriod());
                }

                //SUBCOSID, LANGUAGETYPE, SMSLANGTYPE
                if (inInf.getMainPromotion() != null && !inInf.getMainPromotion().trim().equals("")
                        && !inInf.getMainPromotion().trim().equals("0")) {
                    subcosid = inInf.getMainPromotion();
                }
                if (inInf.getIVRLanguage() != null && !inInf.getIVRLanguage().trim().equals("")
                        && !inInf.getIVRLanguage().trim().equals("0")) {
                    languagetype = inInf.getIVRLanguage();
                }
                if (inInf.getSMSLanguage() != null && !inInf.getSMSLanguage().trim().equals("")
                        && !inInf.getSMSLanguage().trim().equals("0")) {
                    smslangtype = inInf.getSMSLanguage();
                }

                maxactivedays = inInf.getMaxValidity();
                fraudlock = inInf.getFraudLock();
                maxcounttotal = inInf.getMaxCountTotal();
                brandid = inInf.getBrandId();
                paymentMode = inInf.getPaymentMode();
                //AccountInfo
                List<AccountInfo> accountInfo = inInf.getAccountInfo();
                if (accountInfo != null) {
                    for (AccountInfo accInfo : accountInfo) {
                        //balance
                        if (accInfo.getBookItemType().equals("2000") || accInfo.getBookItemType().equals("2100")) {

                            timeenteractive = octet.Convert2String(accInfo.getValidityDate());

                            if (accInfo.getBookItemAmount() != null
                                    && !accInfo.getBookItemAmount().trim().equals("")) {
                                try {
                                    Calendar now = Calendar.getInstance(Locale.US);
                                    Calendar validDate = Calendar.getInstance(Locale.US);
                                    Calendar expireDate = Calendar.getInstance(Locale.US);

                                    validDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getValidityDate())));
                                    expireDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getExpireDate())));
                                    if (now.compareTo(validDate) >= 0 && now.compareTo(expireDate) <= 0) {
                                        balance += Long.valueOf(accInfo.getBookItemAmount());
                                    }
                                } catch (Exception ex) {
                                    balance += 0;
                                }
                            }
                        }
                        //credit limit
                        if (paymentMode != null && paymentMode.equals("0")) {
                            //prepaid
                            if ((accInfo.getBookItemType().equals("4000")
                                    || accInfo.getBookItemType().equals("4100"))
                                    && accInfo.getValidityDate() != null && !accInfo.getValidityDate().equals("")
                                    && accInfo.getExpireDate() != null && !accInfo.getExpireDate().equals("")) {
                                try {
                                    Calendar now = Calendar.getInstance(Locale.US);
                                    Calendar validDate = Calendar.getInstance(Locale.US);
                                    Calendar expireDate = Calendar.getInstance(Locale.US);

                                    validDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getValidityDate())));
                                    expireDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getExpireDate())));
                                    if (now.compareTo(validDate) >= 0 && now.compareTo(expireDate) <= 0) {
                                        creditlimit += Long.valueOf(accInfo.getBookItemAmount());
                                    }
                                } catch (Exception ex) {
                                    creditlimit += 0;
                                }
                            }
                        } else if (paymentMode != null && (paymentMode.equals("1")
                                || paymentMode.equals("2"))) {
                            //postpaid
                            if ((accInfo.getBookItemType().equals("3100"))
                                    && accInfo.getValidityDate() != null && !accInfo.getValidityDate().equals("")
                                    && accInfo.getExpireDate() != null && !accInfo.getExpireDate().equals("")) {
                                try {
                                    Calendar now = Calendar.getInstance(Locale.US);
                                    Calendar validDate = Calendar.getInstance(Locale.US);
                                    Calendar expireDate = Calendar.getInstance(Locale.US);

                                    validDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getValidityDate())));
                                    expireDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getExpireDate())));
                                    if (now.compareTo(validDate) >= 0 && now.compareTo(expireDate) <= 0) {
                                        creditlimit = Long.valueOf(accInfo.getBookItemAmount());
                                    }
                                } catch (Exception ex) {
                                    creditlimit = 0;
                                }
                            }
                        } else {
                            //Hybrid
                            if ((accInfo.getBookItemType().equals("3100")
                                    || accInfo.getBookItemType().equals("4000")
                                    || accInfo.getBookItemType().equals("4100"))
                                    && accInfo.getValidityDate() != null && !accInfo.getValidityDate().equals("")
                                    && accInfo.getExpireDate() != null && !accInfo.getExpireDate().equals("")) {
                                try {
                                    Calendar now = Calendar.getInstance(Locale.US);
                                    Calendar validDate = Calendar.getInstance(Locale.US);
                                    Calendar expireDate = Calendar.getInstance(Locale.US);

                                    validDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getValidityDate())));
                                    expireDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getExpireDate())));
                                    if (now.compareTo(validDate) >= 0 && now.compareTo(expireDate) <= 0) {
                                        creditlimit += Long.valueOf(accInfo.getBookItemAmount());
                                    }
                                } catch (Exception ex) {
                                    creditlimit += 0;
                                }
                            }
                        }
                    }
                }

                //FreeResInfo    		
                List<FreeResInfo> freeResInfo = inInf.getFreeResInfo();
                if (freeResInfo != null && freeResInfo.size() > 0) {
                    for (FreeResInfo freeInfo : freeResInfo) {
                        if (freeInfo.getResourceGroupId().startsWith(EResourceGroupIdPrefix._17.getPrefix())) {
                            //prmsm
                            try {
                                Calendar now = Calendar.getInstance(Locale.US);
                                Calendar validDate = Calendar.getInstance(Locale.US);
                                Calendar expireDate = Calendar.getInstance(Locale.US);

                                validDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getValidityDate())));
                                expireDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getExpireDate())));
                                if (now.compareTo(validDate) >= 0
                                        && now.compareTo(expireDate) <= 0) {
                                    prmsm += Long.valueOf(freeInfo.getRemainingAmount());
                                }
                            } catch (Exception ex) {
                                prmsm += 0;
                            }
                        } else if (freeInfo.getResourceGroupId().startsWith(EResourceGroupIdPrefix._11.getPrefix())) {
                            //prmminute
                            try {
                                Calendar now = Calendar.getInstance(Locale.US);
                                Calendar validDate = Calendar.getInstance(Locale.US);
                                Calendar expireDate = Calendar.getInstance(Locale.US);

                                validDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getValidityDate())));
                                expireDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getExpireDate())));
                                if (now.compareTo(validDate) >= 0
                                        && now.compareTo(expireDate) <= 0) {
                                    prmminute += Long.valueOf(freeInfo.getRemainingAmount());
                                }
                            } catch (Exception ex) {
                                prmminute += 0;
                            }
                        }
                    }
                }
            }
        }

        if (res.equals("2001") || (res.equals("5003")
                && (customerLifeCycleState.equals("0")
                || customerLifeCycleState.equals("8")
                || customerLifeCycleState.equals("9")))) {

            if (res.equals("2001")) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response, smoiIns);
                res = "000";
                desc = "Query the subscriber's basic information successfully.";

                log_RESULTCODE = res;
                log_DESC = desc;
            } else if (res.equals("5003")) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);
                res = "000";
                desc = "User service information query succeeds";
//                desc = "Query the subscriber's basic information successfully.";

//                log_RESULTCODE = res;
//                log_DESC = desc;

                log_RESULTCODE = "5003";
                log_DESC = "DIAMETER_AUTHORIZATION_REJECT";
            }

            sb.append("<vcrr>");
            sb.append("<res>").append(res).append("</res>");
            sb.append("<desc>").append(desc).append("</desc>");
            sb.append("<msisdn>").append(msisdn).append("</msisdn>");
            sb.append("<balance>").append(balance).append("</balance>");
            sb.append("<subspid>").append(subspid).append("</subspid>");
            sb.append("<subcosid>").append(subcosid).append("</subcosid>");
            sb.append("<accountstate>").append(accountstate).append("</accountstate>");
            if (activestop != null && !activestop.trim().equals("")) {
                sb.append("<activestop>").append(activestop).append("</activestop>");
            }
            if (suspendstop != null && !suspendstop.trim().equals("")) {
                sb.append("<suspendstop>").append(suspendstop).append("</suspendstop>");
            }
            if (disablestop != null && !disablestop.trim().equals("")) {
                sb.append("<disablestop>").append(disablestop).append("</disablestop>");
            }
            if (servicestop != null && !servicestop.trim().equals("")) {
                sb.append("<servicestop>").append(servicestop).append("</servicestop>");
            }
            sb.append("<callingscreenflag>").append(callingscreenflag).append("</callingscreenflag>");
            sb.append("<roamflag>").append(roamflag).append("</roamflag>");
            sb.append("<fraudlock>").append(fraudlock).append("</fraudlock>");
            sb.append("<servicearea>").append(servicearea).append("</servicearea>");
            sb.append("<maxactivedays>").append(maxactivedays).append("</maxactivedays>");
            sb.append("<maxcounttotal>").append(maxcounttotal).append("</maxcounttotal>");
            sb.append("<languagetype>").append(languagetype).append("</languagetype>");
            sb.append("<creditlimit>").append(creditlimit).append("</creditlimit>");
            sb.append("<score>").append(score).append("</score>");
            sb.append("<voicemail>").append(voicemail).append("</voicemail>");
            sb.append("<activecac>").append(activecac).append("</activecac>");
            sb.append("<groupid>").append(groupid).append("</groupid>");
            //(prmsm==0?"":String.valueOf(prmsm))
            sb.append("<prmsm>").append(prmsm).append("</prmsm>");
            //(prmminute==0?"":String.valueOf(prmminute))
            sb.append("<prmminute>").append(prmminute).append("</prmminute>");
            sb.append("<querytimes>").append(querytimes).append("</querytimes>");
            sb.append("<rbtflag>").append(rbtflag).append("</rbtflag>");
            sb.append("<agingdis>").append(agingdis).append("</agingdis>");
            sb.append("<timeenteractive>").append(timeenteractive).append("</timeenteractive>");
            sb.append("<ccc>").append(ccc).append("</ccc>");
            sb.append("<volumndiscountflag>").append(volumndiscountflag).append("</volumndiscountflag>");
            sb.append("<fphflag>").append(fphflag).append("</fphflag>");
            sb.append("<lastfph>").append(lastfph).append("</lastfph>");
            sb.append("<callscreenflag>").append(callscreenflag).append("</callscreenflag>");
            sb.append("<interroamflag>").append(interroamflag).append("</interroamflag>");
            sb.append("<smslangtype>").append(smslangtype).append("</smslangtype>");
            sb.append("<smusage>").append(smusage).append("</smusage>");
            sb.append("<smusagetoptime>").append(smusagetoptime).append("</smusagetoptime>");
            sb.append("<prmscore>").append(prmscore).append("</prmscore>");
            sb.append("<prmscorestoptime>").append(prmscorestoptime).append("</prmscorestoptime>");
            sb.append("<scorestoptime>").append(scorestoptime).append("</scorestoptime>");
            sb.append("<prmpointexp>").append(prmpointexp).append("</prmpointexp>");
            sb.append("<calldis>").append(calldis).append("</calldis>");
            sb.append("<calldisexp>").append(calldisexp).append("</calldisexp>");
            sb.append("<smdis>").append(smdis).append("</smdis>");
            sb.append("<smdisexp>").append(smdisexp).append("</smdisexp>");
            sb.append("<smstamp>").append(smstamp).append("</smstamp>");
            sb.append("<ivrprmtimes>").append(ivrprmtimes).append("</ivrprmtimes>");
            sb.append("<ussdbtimes>").append(ussdbtimes).append("</ussdbtimes>");
            sb.append("<ussdprmtimes>").append(ussdprmtimes).append("</ussdprmtimes>");
            sb.append("<prmpoint>").append(prmpoint).append("</prmpoint>");
            sb.append("<paytype>").append(paytype).append("</paytype>");
            sb.append("<brandid>").append(brandid).append("</brandid>");
            sb.append("<callnotifyflag>").append(callnotifyflag).append("</callnotifyflag>");
            sb.append("</vcrr>");
        } else {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);

            if (res.equals("5004")) {
                res = "322";
                desc = "Invalid AVP Value";

                log_RESULTCODE = res;
                log_DESC = desc;
            } else if (res.equals("5005")) {
                res = "322";
                desc = "Missing AVP Value";

                log_RESULTCODE = res;
                log_DESC = desc;
            } else {
                log_RESULTCODE = res;
                log_DESC = desc;
            }

            sb.append("<vcrr>")
                    .append("<res>").append(res).append("</res>")
                    .append("<desc>").append(desc).append("</desc>")
                    .append("</vcrr>");
        }

        msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
        msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
        msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
        return msgReturn;
    }

    private HashMap<String, String> mapDispPPSPkgrewCmd(DiameterCreditControlAnswer cca, AbstractAF af, SmoiInstance smoiIns) {
        HashMap<String, String> msgReturn = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("");
        String res = String.valueOf(cca.getResultCode());
        String desc = "FAILED";

        String log_RESULTCODE = "";
        String log_DESC = "";

        String state = "";
        String rewpoint = "";
        String rewpointexp = "";
        String rewcalldis = "";
        String rewcalldisexp = "";
        String rewsmdis = "";
        String rewsmdisexp = "";
        long rewprmminute = 0;
        String rewprmminuteexp = "";
        long rewprmsm = 0;
        String rewprmsmexp = "";

        StringBuilder strPattern2 = new StringBuilder("");
        StringBuilder strPattern3 = new StringBuilder("");

        //ServiceInformation
        ServiceInformation serviceInfo = cca.getServiceInformation();
        String customerLifeCycleState = "";
        if (serviceInfo != null) {
            //INInformation
            INInformation inInf = serviceInfo.getInInformation();
            if (inInf != null) {
                customerLifeCycleState = octet.Convert2String(inInf.getCustomerLifeCycleState());
                //mapping EAccountState 
                for (EAccountState e : EAccountState.values()) {
                    if (e.getResultCode().equalsIgnoreCase(res)
                            && e.getCustomerLifeCycle().equalsIgnoreCase(customerLifeCycleState)) {
                        state = e.getAccountState();
                        break;
                    }
                }
                //FreeResInfo
                List<FreeResInfo> freeResInfo = inInf.getFreeResInfo();
                if (freeResInfo != null && freeResInfo.size() > 0) {
                    HashMap<String, List<String[]>> hmLeftNamePattern2 = new HashMap<String, List<String[]>>();
                    HashMap<String, List<String[]>> hmLeftNamePattern3 = new HashMap<String, List<String[]>>();

                    for (FreeResInfo freeInfo : freeResInfo) {
                        if (freeInfo.getResourceGroupId().startsWith(EResourceGroupIdPrefix._112.getPrefix())) {
                            //rewprmminute 
                            try {
                                rewprmminute = Long.valueOf(freeInfo.getRemainingAmount());
                            } catch (Exception ex) {
                                rewprmminute = 0;
                            }
                            //rewprmminuteexp
                            rewprmminuteexp = octet.Convert2String(freeInfo.getExpireDate());
                        }

                        if (freeInfo.getResourceGroupId().startsWith(EResourceGroupIdPrefix._172.getPrefix())) {
                            //rewprmsm 
                            try {
                                rewprmsm = Long.valueOf(freeInfo.getRemainingAmount());
                            } catch (Exception ex) {
                                rewprmsm = 0;
                            }
                            //rewprmsmexp
                            rewprmsmexp = octet.Convert2String(freeInfo.getExpireDate());
                        }

                        //pattern 2 and pattern 3 
                        String resourceId = freeInfo.getResourceId();
                        String AA = resourceId.substring(0, 2);
                        String B = resourceId.substring(2, 3);

                        String productId = freeInfo.getProductId();
                        String productSequenceId = freeInfo.getProductSequenceId();

                        String remainAmt = freeInfo.getRemainingAmount();
                        String expDate = octet.Convert2String(freeInfo.getExpireDate());

                        String leftName2 = "";
                        String leftName3 = "";
                        String key = productId + ":" + productSequenceId + ":" + expDate;

						//pattern 2
                        //Log.d("=========>Pattern2 resourceGroupId :"+resourceGroupId +",AA : "+AA +",B : "+B);
                        if (!B.equals("2")) {
                            for (ELeftNamePattern2 e : ELeftNamePattern2.values()) {
                                if (e.getFreeResourceAA().equalsIgnoreCase(AA)) {
                                    leftName2 = e.getMappingLeftName();
                                    break;
                                }
                            }
                        }
                        //Log.d("=========>Pattern2 resourceGroupId : "+resourceGroupId +"leftName2 : "+leftName2);
                        if (leftName2 != null && !leftName2.equals("")) {
                            if (hmLeftNamePattern2.containsKey(key)) {
                                List<String[]> listLeftNamePattern2 = hmLeftNamePattern2.get(key);
                                listLeftNamePattern2.add(new String[]{leftName2, remainAmt});
                                hmLeftNamePattern2.put(key, listLeftNamePattern2);
                            } else {
                                List<String[]> listLeftNamePattern2 = new ArrayList<String[]>();
                                listLeftNamePattern2.add(new String[]{leftName2, remainAmt});
                                hmLeftNamePattern2.put(key, listLeftNamePattern2);
                            }
                        }

						//pattern 3
                        //Log.d("=========>Pattern3 resourceGroupId :"+resourceGroupId +",AA : "+AA +",B : "+B);
                        if (!B.equals("2")) {
                            for (ELeftNamePattern3 e : ELeftNamePattern3.values()) {
                                if (e.getFreeResourceAA().equalsIgnoreCase(AA)) {
                                    leftName3 = e.getMappingLeftName();
                                    break;
                                }
                            }
                        }
                        //Log.d("=========>Pattern3 resourceGroupId : "+resourceGroupId +"leftName3 : "+leftName3);
                        if (leftName3 != null && !leftName3.equals("")) {
                            if (hmLeftNamePattern3.containsKey(key)) {
                                List<String[]> listLeftNamePattern3 = hmLeftNamePattern3.get(key);
                                listLeftNamePattern3.add(new String[]{leftName3, remainAmt});
                                hmLeftNamePattern3.put(key, listLeftNamePattern3);
                            } else {
                                List<String[]> listLeftNamePattern3 = new ArrayList<String[]>();
                                listLeftNamePattern3.add(new String[]{leftName3, remainAmt});
                                hmLeftNamePattern3.put(key, listLeftNamePattern3);
                            }
                        }
                    }//end for loop : freeResInfo

                    /**
                     * TODO Pattern2
                     */
                    int idPattern2 = 0;
                    for (Iterator<Map.Entry<String, List<String[]>>> i = hmLeftNamePattern2.entrySet().iterator(); i.hasNext();) {
                        Map.Entry<String, List<String[]>> entry = i.next();
                        String key = entry.getKey();
                        String keyValue[] = key.split(":");
                        String productId = keyValue[0];
                        //String productSequenceId = keyValue[1];
                        String expDate = keyValue[2];
                        List<String[]> value = entry.getValue();
                        idPattern2++;
                        strPattern2.append("<pkg").append(idPattern2).append("id>")
                                .append(productId)
                                .append("</pkg").append(idPattern2).append("id>")
                                .append("<pkg").append(idPattern2).append("name>")
                                .append("")
                                .append("</pkg").append(idPattern2).append("name>");
                        for (String[] v : value) {
                            String leftName = v[0];
                            String remainAmount = v[1];
                            System.out.println("leftName:" + leftName + " ,remainAmount:" + remainAmount);
                            strPattern2.append("<pkg").append(idPattern2).append(leftName).append(">")
                                    .append(remainAmount)
                                    .append("</pkg").append(idPattern2).append(leftName).append(">");
                        }
                        strPattern2.append("<pkg").append(idPattern2).append("valueexp>")
                                .append(expDate)
                                .append("</pkg").append(idPattern2).append("valueexp>");
                    }

                    /**
                     * TODO Pattern3
                     */
                    int idPattern3 = 0;
                    for (Iterator<Map.Entry<String, List<String[]>>> i = hmLeftNamePattern3.entrySet().iterator(); i.hasNext();) {
                        Map.Entry<String, List<String[]>> entry = i.next();
                        String key = entry.getKey();
                        String keyValue[] = key.split(":");
                        String productId = keyValue[0];
                        //String productSequenceId = keyValue[1];
                        String expDate = keyValue[2];
                        List<String[]> value = entry.getValue();
                        idPattern3++;
                        strPattern3.append("<pkg").append(idPattern3).append("id>")
                                .append(productId)
                                .append("</pkg").append(idPattern3).append("id>")
                                .append("<pkg").append(idPattern3).append("name>")
                                .append("")
                                .append("</pkg").append(idPattern3).append("name>");
                        for (String[] v : value) {
                            String leftName = v[0];
                            String remainAmount = v[1];
                            System.out.println("leftName:" + leftName + " ,remainAmount:" + remainAmount);
                            strPattern3.append("<pkg").append(idPattern3).append(leftName).append(">")
                                    .append(remainAmount)
                                    .append("</pkg").append(idPattern3).append(leftName).append(">");
                        }
                        strPattern3.append("<pkg").append(idPattern3).append("valueexp>")
                                .append(expDate)
                                .append("</pkg").append(idPattern3).append("valueexp>");
                    }
                }//end if freeResInfo
            }
        }

        if (res.equals("2001") || (res.equals("5003")
                && (customerLifeCycleState.equals("0")
                || customerLifeCycleState.equals("8")
                || customerLifeCycleState.equals("9")))) {
            if (res.equals("2001")) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response, smoiIns);
                res = "000";
                desc = "Display subscriber's reward and package information succeed.";

                log_RESULTCODE = res;
                log_DESC = desc;
            } else if (res.equals("5003")) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);
                res = "000";
                desc = "User service information query succeeds";

                log_RESULTCODE = "5003";
                log_DESC = "DIAMETER_AUTHORIZATION_REJECT";
            }

            sb.append("<vcrr>");
            //pattern 1
            sb.append("<res>").append(res).append("</res>");
            sb.append("<desc>").append(desc).append("</desc>");
            sb.append("<state>").append(state).append("</state>");
            sb.append("<rewpoint>").append(rewpoint).append("</rewpoint>");
            sb.append("<rewpointexp>").append(rewpointexp).append("</rewpointexp>");
            sb.append("<rewcalldis>").append(rewcalldis).append("</rewcalldis>");
            sb.append("<rewcalldisexp>").append(rewcalldisexp).append("</rewcalldisexp>");
            sb.append("<rewsmdis>").append(rewsmdis).append("</rewsmdis>");
            sb.append("<rewsmdisexp>").append(rewsmdisexp).append("</rewsmdisexp>");
            //(rewprmminute==0?"":String.valueOf(rewprmminute))
            sb.append("<rewprmminute>").append(rewprmminute).append("</rewprmminute>");
            sb.append("<rewprmminuteexp>").append(rewprmminuteexp).append("</rewprmminuteexp>");
            sb.append("<rewprmsm>").append(rewprmsm).append("</rewprmsm>");
            sb.append("<rewprmsmexp>").append(rewprmsmexp).append("</rewprmsmexp>");
            sb.append(strPattern2)
                    .append(strPattern3);
            sb.append("</vcrr>");
        } else {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);

            if (res.equals("5004")) {
                res = "322";
                desc = "Invalid AVP Value";

                log_RESULTCODE = res;
                log_DESC = desc;
            } else if (res.equals("5005")) {
                res = "322";
                desc = "Missing AVP Value";

                log_RESULTCODE = res;
                log_DESC = desc;
            } else {
                log_RESULTCODE = res;
                log_DESC = desc;
            }

            sb.append("<vcrr>");
            sb.append("<res>").append(res).append("</res>");
            sb.append("<desc>").append(desc).append("</desc>");
            sb.append("</vcrr>");
        }

        msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
        msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
        msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
        return msgReturn;
    }

    private HashMap<String, String> mapModiPPSValidityCmd(DiameterCreditControlAnswer cca, AbstractAF af, SmoiInstance smoiIns) {
        HashMap<String, String> msgReturn = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("");
        String res = String.valueOf(cca.getResultCode());
        String desc = "FAILED";

        String log_RESULTCODE = "";
        String log_DESC = "";

        if (res.equals("2001")) {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response, smoiIns);
            res = "000";
            desc = "Modifying the validity term succeeded";
        } else {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);
            if (res.equals("5004")) {
                res = "322";
                desc = "Invalid AVP Value";
            } else if (res.equals("5005")) {
                res = "322";
                desc = "Missing AVP Value";
            }
        }

        sb.append("<vcrr>");
        sb.append("<res>").append(res).append("</res>");
        sb.append("<desc>").append(desc).append("</desc>");
        sb.append("</vcrr>");

        log_RESULTCODE = res;
        log_DESC = desc;
        msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
        msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
        msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
        return msgReturn;
    }

    private HashMap<String, String> mapModiPPSMultiAttrCmd_Modify_Balance_and_Validity(DiameterCreditControlAnswer cca, AbstractAF af, SmoiInstance smoiIns) {
        HashMap<String, String> msgReturn = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("");
        String res = String.valueOf(cca.getResultCode());
        String desc = "FAILED";

        String log_RESULTCODE = "";
        String log_DESC = "";

        if (res.equals("2001")) {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response, smoiIns);

            res = "000";
            desc = "Modifying the validity term succeeded";

            long newbalance = 0;
            long newprmmoney = 0;
            long newprmsm = 0;
            long newprmminute = 0;
            String newpoint = "";
            long newfreecalltimes = 0;
            long newfreerbtsong = 0;
            long newfreerbtmf = 0;

            String newscore = "";
            String newprmscore = "";
            String newsmusage = "";
            String newactivestop = "";

            ServiceInformation serviceInfo = cca.getServiceInformation();
            if (serviceInfo != null) {
                //INInformation
                INInformation inInf = serviceInfo.getInInformation();

                SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                if (inInf != null) {
                    //AccountInfo
                    List<AccountInfo> accountInfo = inInf.getAccountInfo();
                    for (AccountInfo accInfo : accountInfo) {
                        //newactivestop
                        newactivestop = octet.Convert2String(accInfo.getExpireDate());
                        //newbalance
                        if (accInfo.getBookItemType().equals("2000")
                                || accInfo.getBookItemType().equals("2100")) {
                            if (accInfo.getBookItemAmount() != null
                                    && !accInfo.getBookItemAmount().trim().equals("")) {
                                try {
                                    //Calendar now = Calendar.getInstance(Locale.US);
                                    Calendar validDate = Calendar.getInstance(Locale.US);
                                    Calendar expireDate = Calendar.getInstance(Locale.US);
                                    Calendar cerrentDate = Calendar.getInstance(Locale.US); // chatl 24/10/2017
                                    
                                    validDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getValidityDate())));
                                    expireDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getExpireDate())));
                                    
                                    if(cerrentDate.compareTo(validDate)>=0&&cerrentDate.compareTo(expireDate)<=0){  // chatl 24/10/2017 uncomment
                                    newbalance += Long.valueOf(accInfo.getBookItemAmount());
                                    }	         				
                                } catch (Exception ex) {
                                    newbalance += 0;
                                }
                            }
                        }
                    }//loop AccountInfo
                }
            }

            if (smoiIns.getPage().equals(ECommand.setPPSReward.getCommand())) {
                sb.append("<vcrr>");
                sb.append("<res>").append(res).append("</res>");
                sb.append("<desc>").append("Operation succeeded.").append("</desc>");
                sb.append("</vcrr>");
            } else {
                sb.append("<vcrr>");
                sb.append("<res>").append(res).append("</res>");
                sb.append("<desc>").append(desc).append("</desc>");
                //get value from CCA
                sb.append("<newbalance>").append(newbalance).append("</newbalance>");
                //fix 0,blank value
                sb.append("<newprmmoney>").append(newprmmoney).append("</newprmmoney>");
                sb.append("<newprmsm>").append(newprmsm).append("</newprmsm>");
                sb.append("<newprmminute>").append(newprmminute).append("</newprmminute>");
                sb.append("<newpoint>").append(newpoint).append("</newpoint>");
                sb.append("<newfreecalltimes>").append(newfreecalltimes).append("</newfreecalltimes>");
                sb.append("<newfreerbtsong>").append(newfreerbtsong).append("</newfreerbtsong>");
                sb.append("<newfreerbtmf>").append(newfreerbtmf).append("</newfreerbtmf>");
                sb.append("<newscore>").append(newscore).append("</newscore>");
                sb.append("<newprmscore>").append(newprmscore).append("</newprmscore>");
                sb.append("<newsmusage>").append(newsmusage).append("</newsmusage>");
                sb.append("<newactivestop>").append(newactivestop).append("</newactivestop>");
                sb.append("</vcrr>");
            }
        } else {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);
            if (res.equals("5004")) {
                res = "322";
                desc = "Invalid AVP Value";
            } else if (res.equals("5005")) {
                res = "322";
                desc = "Missing AVP Value";
            }
            sb.append("<vcrr>");
            sb.append("<res>").append(res).append("</res>");
            sb.append("<desc>").append(desc).append("</desc>");
            sb.append("</vcrr>");
        }

        log_RESULTCODE = res;
        log_DESC = desc;

        msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
        msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
        msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
        return msgReturn;
    }

    private HashMap<String, String> mapModiPPSMultiAttrCmd_Modify_Free_Resource_Adjustment(DiameterCreditControlAnswer cca, AbstractAF af, SmoiInstance smoiIns) {
        HashMap<String, String> msgReturn = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("");
        String res = String.valueOf(cca.getResultCode());
        String desc = "FAILED";

        String log_RESULTCODE = "";
        String log_DESC = "";

        if (res.equals("2001")) {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response, smoiIns);

            res = "000";
            desc = "Modifying the validity term succeeded";

            long newbalance = 0;
            long newprmmoney = 0;
            long newprmsm = 0;
            long newprmminute = 0;
            String newpoint = "";
            long newfreecalltimes = 0;
            long newfreerbtsong = 0;
            long newfreerbtmf = 0;

            String newscore = "";
            String newprmscore = "";
            String newsmusage = "";
            String newactivestop = "";

            ServiceInformation serviceInfo = cca.getServiceInformation();
            if (serviceInfo != null) {
                //INInformation
                INInformation inInf = serviceInfo.getInInformation();

                SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

                if (inInf != null) {
                    /*
                     * not use 
                     * //AccountInfo
                     List<AccountInfo> accountInfo = inInf.getAccountInfo();
                     for(AccountInfo accInfo :accountInfo){
                     //newbalance
                     if(accInfo.getBookItemType().equals("2000")
                     ||accInfo.getBookItemType().equals("2001")){ 
                     if(accInfo.getBookItemAmount()!=null
                     &&!accInfo.getBookItemAmount().trim().equals("")){
                     try{
                     //Calendar now = Calendar.getInstance(Locale.US);
                     Calendar validDate = Calendar.getInstance(Locale.US);
                     Calendar expireDate = Calendar.getInstance(Locale.US);

                     validDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getValidityDate())));
                     expireDate.setTime(simDateFormat.parse(octet.Convert2String(accInfo.getExpireDate()))); 
                     // if(now.compareTo(validDate)>=0 &&now.compareTo(expireDate)<=0){
                     newbalance+=Long.valueOf(accInfo.getBookItemAmount());
                     //}	         				
                     }catch(Exception ex){
                     newbalance+=0;
                     } 
                     }
                     }  
                     }*/

                    //FreeResInfo    		
                    List<FreeResInfo> freeResInfo = inInf.getFreeResInfo();

                    String rsc_group_id = "";

                    for (FreeResInfo freeInfo : freeResInfo) {
                        //format : AABCDDDDD
                        rsc_group_id = freeInfo.getResourceGroupId();
                        String add_zero = "";

                        for (int i = rsc_group_id.length(); i < 9; i++) {
                            add_zero += "0";
                        }
                        rsc_group_id = add_zero + rsc_group_id;

                        if (rsc_group_id.startsWith(EResourceGroupIdPrefix._10.getPrefix())) {
                            //newprmmoney
                            try {
                                Calendar now = Calendar.getInstance(Locale.US);
                                Calendar validDate = Calendar.getInstance(Locale.US);
                                Calendar expireDate = Calendar.getInstance(Locale.US);

                                validDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getValidityDate())));
                                expireDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getExpireDate())));

                                if (now.compareTo(validDate) >= 0 && now.compareTo(expireDate) <= 0) {
                                    newprmmoney += Long.valueOf(freeInfo.getRemainingAmount());
                                }
                            } catch (Exception ex) {
                                newprmmoney += 0;
                            }
                        }

                        if (rsc_group_id.startsWith(EResourceGroupIdPrefix._17.getPrefix())) {
                            //newprmsm 
                            try {
                                Calendar now = Calendar.getInstance(Locale.US);
                                Calendar validDate = Calendar.getInstance(Locale.US);
                                Calendar expireDate = Calendar.getInstance(Locale.US);

                                validDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getValidityDate())));
                                expireDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getExpireDate())));

                                if (now.compareTo(validDate) >= 0
                                        && now.compareTo(expireDate) <= 0) {
                                    newprmsm += Long.valueOf(freeInfo.getRemainingAmount());
                                }
                            } catch (Exception ex) {
                                newprmsm += 0;
                            }
                        }

                        //if(rsc_group_id.startsWith(EResourceGroupIdPrefix._02.getPrefix())) {
                        if (rsc_group_id.startsWith(EResourceGroupIdPrefix._11.getPrefix())) {
                            //newprmminute
                            try {
                                Calendar now = Calendar.getInstance(Locale.US);
                                Calendar validDate = Calendar.getInstance(Locale.US);
                                Calendar expireDate = Calendar.getInstance(Locale.US);

                                validDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getValidityDate())));
                                expireDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getExpireDate())));
                                if (now.compareTo(validDate) >= 0
                                        && now.compareTo(expireDate) <= 0) {
                                    newprmminute += Long.valueOf(freeInfo.getRemainingAmount());
                                }
                            } catch (Exception ex) {
                                newprmminute += 0;
                            }
                        }

                        /*if(rsc_group_id.startsWith(EResourceGroupIdPrefix._11.getPrefix())){
                         //newfreecalltimes 
                         try{
                         Calendar now = Calendar.getInstance(Locale.US);
                         Calendar validDate = Calendar.getInstance(Locale.US);
                         Calendar expireDate = Calendar.getInstance(Locale.US);

                         validDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getValidityDate())));
                         expireDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getExpireDate()))); 
								
                         if(now.compareTo(validDate) >= 0
                         &&now.compareTo(expireDate) <=0 ) {
                         newfreecalltimes += Long.valueOf(freeInfo.getRemainingAmount());
                         }
                         }catch(Exception ex){
                         newfreecalltimes += 0;
                         }
                         }*/
                        if (rsc_group_id.startsWith(EResourceGroupIdPrefix._29.getPrefix())) {
                            //newfreerbtsong 
                            try {
                                Calendar now = Calendar.getInstance(Locale.US);
                                Calendar validDate = Calendar.getInstance(Locale.US);
                                Calendar expireDate = Calendar.getInstance(Locale.US);

                                validDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getValidityDate())));
                                expireDate.setTime(simDateFormat.parse(octet.Convert2String(freeInfo.getExpireDate())));

                                if (now.compareTo(validDate) >= 0
                                        && now.compareTo(expireDate) <= 0) {
                                    newfreerbtsong += Long.valueOf(freeInfo.getRemainingAmount());
                                }
                            } catch (Exception ex) {
                                newfreerbtsong += 0;
                            }
                        }
                    }//loop FreeResInfo
                }
            }

            if (smoiIns.getPage().equals(ECommand.setPPSReward.getCommand())) {
                desc = "Operation succeeded.";
                sb.append("<vcrr>");
                sb.append("<res>").append(res).append("</res>");
                sb.append("<desc>").append(desc).append("</desc>");
                /*sb.append("<newbalance>").append(newbalance).append("</newbalance>");
                 sb.append("<newactivestop>").append(newactivestop).append("</newactivestop>");
                 sb.append("<pkgminute>").append(newprmminute).append("</pkgminute>");
                 sb.append("<pkgsm>").append(newprmsm).append("</pkgsm>");
                 sb.append("<pkgprmmoney>").append(newprmmoney).append("</pkgprmmoney>");
                 sb.append("<pkgcalltimes>").append(newfreecalltimes).append("</pkgcalltimes>");
                 sb.append("<pkgrbtsong>").append(newfreerbtsong).append("</pkgrbtsong>");
                 sb.append("<pkgrbtmf>").append(newfreerbtmf).append("</pkgrbtmf>");
                 sb.append("<pkgidexp>").append("").append("</pkgidexp>");
                 sb.append("<pkgebd>").append("").append("</pkgebd>");*/
                sb.append("</vcrr>");
            } else {
                sb.append("<vcrr>");
                sb.append("<res>").append(res).append("</res>");
                sb.append("<desc>").append(desc).append("</desc>");
                //fix 0,blank value
                sb.append("<newbalance>").append(newbalance).append("</newbalance>");
                //get value from CCA
                sb.append("<newprmmoney>").append(newprmmoney).append("</newprmmoney>");
                sb.append("<newprmsm>").append(newprmsm).append("</newprmsm>");
                sb.append("<newprmminute>").append(newprmminute).append("</newprmminute>");
                sb.append("<newpoint>").append(newpoint).append("</newpoint>");
                sb.append("<newfreecalltimes>").append(newfreecalltimes).append("</newfreecalltimes>");
                sb.append("<newfreerbtsong>").append(newfreerbtsong).append("</newfreerbtsong>");
                sb.append("<newfreerbtmf>").append(newfreerbtmf).append("</newfreerbtmf>");
                sb.append("<newscore>").append(newscore).append("</newscore>");
                sb.append("<newprmscore>").append(newprmscore).append("</newprmscore>");
                sb.append("<newsmusage>").append(newsmusage).append("</newsmusage>");
                sb.append("<newactivestop>").append(newactivestop).append("</newactivestop>");
                sb.append("</vcrr>");
            }

        } else {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);
            if (res.equals("5004")) {
                res = "322";
                desc = "Invalid AVP Value";
            } else if (res.equals("5005")) {
                res = "322";
                desc = "Missing AVP Value";
            }
            sb.append("<vcrr>");
            sb.append("<res>").append(res).append("</res>");
            sb.append("<desc>").append(desc).append("</desc>");
            sb.append("</vcrr>");
        }

        log_RESULTCODE = res;
        log_DESC = desc;

        msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
        msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
        msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
        return msgReturn;
    }

    private HashMap<String, String> mapEquinoxReturnCmd(EResponseCode _ResponseCode) {
        HashMap<String, String> msgReturn = new HashMap<String, String>();
        String msg = "";
        String res = _ResponseCode.getCode();
        String desc = _ResponseCode.getDesc();
        String log_RESULTCODE = "";
        String log_DESC = "";

        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";

        log_RESULTCODE = res;
        log_DESC = desc;

        msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), msg);
        msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
        msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);

        return msgReturn;
    }

    public static void main(String[] args) {
        /*LinkedHashMap<String,List<String[]>> hmLeftNamePattern2 = new LinkedHashMap<String,List<String[]>>();

         //String key =productId+":"+productSequenceId+":"+expDate;
         String key1 ="123"+":"+"1"+":"+"20130807";
         String key2 ="123"+":"+"2"+":"+"20130807";
         String key3 ="1234"+":"+"2"+":"+"20130807";
         String key4 ="1234"+":"+"2"+":"+"20130807";
         String key5 ="1234"+":"+"2"+":"+"20130807"; 
         String leftName2 = "leftName2";String remainAmt="10";


         testHash(  hmLeftNamePattern2
         ,  key1,  leftName2,  remainAmt);
         testHash(  hmLeftNamePattern2
         ,  key2,  leftName2,  remainAmt);
         testHash(  hmLeftNamePattern2
         ,  key3,  leftName2,  remainAmt);
         testHash(  hmLeftNamePattern2
         ,  key4,  leftName2,  remainAmt);
         testHash(  hmLeftNamePattern2
         ,  key5,  leftName2,  remainAmt);

         System.out.println(hmLeftNamePattern2.size());
         StringBuilder strPattern2 = new StringBuilder("");
         int idPattern2 =0;
         for (Iterator<Map.Entry<String,List<String[]>>> i = hmLeftNamePattern2.entrySet().iterator(); i.hasNext();){  
         Map.Entry<String,List<String[]>> entry = i.next();  
         String key = entry.getKey();
         String keyValue[] = key.split(":");
         String productId = keyValue[0];
         String productSequenceId = keyValue[1];
         String expDate = keyValue[2];
         System.out.println("productId:"+productId + " ,productSequenceId:"+productSequenceId+" ,expDate:"+expDate);
         List<String[]> value= entry.getValue();
         idPattern2 ++;
         strPattern2.append("<pkg").append(idPattern2).append("id>")
         .append(productId)
         .append("</pkg").append(idPattern2).append("id>") 
         .append("<pkg").append(idPattern2).append("name>")
         .append("")
         .append("</pkg").append(idPattern2).append("name>") ;
         for(String[] v :value){	 
         String leftName = v[0];
         String remainAmount = v[1];
         System.out.println("leftName:"+leftName + " ,remainAmount:"+remainAmount);
         strPattern2.append("<pkg").append(idPattern2).append(leftName).append(">")
         .append(remainAmount)
         .append("</pkg").append(idPattern2).append(leftName).append(">")  ;
         }			
         strPattern2.append("<pkg").append(idPattern2).append("valueexp>")
         .append(expDate)
         .append("</pkg").append(idPattern2).append("valueexp>");
         } 
         */
    }

    public static void testHash(HashMap<String, List<String[]>> hmLeftNamePattern2, String key, String leftName2, String remainAmt) {
        if (hmLeftNamePattern2.containsKey(key)) {
            List<String[]> listLeftNamePattern2 = hmLeftNamePattern2.get(key);
            listLeftNamePattern2.add(new String[]{leftName2, remainAmt});
            hmLeftNamePattern2.put(key, listLeftNamePattern2);
        } else {
            List<String[]> listLeftNamePattern2 = new ArrayList<String[]>();
            listLeftNamePattern2.add(new String[]{leftName2, remainAmt});
            hmLeftNamePattern2.put(key, listLeftNamePattern2);
        }
    }

    /**
     * @see phase 2
     *
     * private String mapChgtrigChrgAcntCmd(DiameterCreditControlAnswer cca) {
     * StringBuilder sb = new StringBuilder(); String res =
     * String.valueOf(cca.getResultCode()); String desc = "FAILED"; if
     * (res.equals("2001")) { res = "000"; desc = "Manual recharging succeeds";
     * } sb.append("<vcrr>"); sb.append("<res>").append(res).append("</res>");
     * sb.append("<desc>").append(desc).append("</desc>"); sb.append("</vcrr>");
     * return sb.toString(); }
     */
}
