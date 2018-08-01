package phoebe.eqx.smoi.state;

//import com.sun.xml.internal.bind.v2.TODO;

import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;
import ec02.utils.Log;
import phoebe.eqx.model.dcc.cca.*;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.AVATARMessage;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.message.parser.bos.DiameterCCA;
import phoebe.eqx.smoi.utils.DateTime;
import phoebe.eqx.smoi.utils.SmoiStatAlarm;
import smoi.enums.*;
import smoi.message.OctetString;

//import java.text.SimpleDateFormat;
import java.util.*;

public class StateWaitAVATAR implements IAFState {

    private OctetString octet = new OctetString();

    @Override
    public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
        String nextState = AFState.IDLE;
        MyAppData myAppData = (MyAppData) instance;
        SmoiInstance smoiIns = myAppData.getSmoiIns();

        String page = smoiIns.getPage();
//        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        for (EquinoxRawData r : rawDatas) {
            String inputMsg = r.getRawDataMessage();
            String outputMsg = "";
            HashMap<String, String> hmMessage = null;
            String log_RESULTCODE = "";
            String log_DESC = "";
            String invokeId = r.getInvoke();
            if (r.getRawEventType().equals(AFEvent.IncomingCCA)) {
//            	boolean flagError = false;
                DiameterCreditControlAnswer cca = DiameterCCA.createDiameterCreditControlAnswer(r.getRawDataMessage());
                Log.d("page =" + page);
//                String customerLife = mapCustomerLifeCycle(cca);

                if (page.equals(ECommand.dispPPSInfo.getCommand())) {
//                    ServiceInformation serviceInfo = cca.getServiceInformation();

                    hmMessage = mapDispPPSInfoCmd(cca, af, smoiIns,instance);
                    outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                    log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                    log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());

                    SendMessage.Client(outputMsg, af, myAppData);
                    nextState = AFState.IDLE;
                }

            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {
//                int maxRetry = Integer.parseInt(smoi_conf.get(Conf.resourceNameAVATAR_Standby_MaxRetry).get(0));
                int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resourceNameAVATAR_Standby_MaxRetry);
                if (maxRetry > 0) {
                    //TODO Standby_MaxRetry
                    Log.d("Retry: " + Conf.resourceNameAVATAR_Standby_MaxRetry + " maxRetry: " + maxRetry);
                    maxRetry -= 1;
                    smoiIns.setResourceNameStandbyMaxRetry(Conf.resourceNameAVATAR_Standby_MaxRetry, maxRetry);

                    inputMsg = "";
                    outputMsg = AVATARMessage.mappingMessage(af, myAppData);
                    nextState = AFState.W_AVATAR;

                    String noGroupBos = StateWaitDS2A.checkGroupBosLocation(smoiIns.getScp(), af);
                    if (!"".equals(noGroupBos)) {
                        SendMessage.AVATAR(outputMsg, af, myAppData, Conf.resourceNameAVATAR_Standby + noGroupBos);
                    } else {
                        SendMessage.AVATAR(outputMsg, af, myAppData, Conf.resourceNameAVATAR_Standby);
                    }
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Response_Reject, smoiIns);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_AVATAR_$s_Request, smoiIns);
                } else {
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Response_Reject, smoiIns);
                    hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_AVATAR_ACCOUNTQUERY_RESPONSE_REJECT);
                    outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                    log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                    log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                    SendMessage.Client(outputMsg, af, myAppData);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                    nextState = AFState.IDLE;
                }
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Response_Abort, smoiIns);
                hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_AVATAR_ACCOUNTQUERY_RESPONSE_ABORT);
                outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Response_Error, smoiIns);
                hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_AVATAR_ACCOUNTQUERY_RESPONSE_ERROR);
                outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                SendMessage.Client(outputMsg, af, myAppData);
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Timeout, smoiIns);
                hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_AVATAR_ACCOUNTQUERY_RESPONSE_TIMEOUT);
                outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
                SendMessage.Client(outputMsg, af, myAppData);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
//                }
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

    private HashMap<String, String> mapDispPPSInfoCmd(DiameterCreditControlAnswer cca, AbstractAF af, SmoiInstance smoiIns,Object instance) {
        HashMap<String, String> msgReturn = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("");
        String customerLife = mapCustomerLifeCycle(cca);
//        MyAppData myAppData = (MyAppData) instance;
//        String outputMsg = "";
//        String nextState = AFState.IDLE;

        String res = cca.getResultCode();
        String desc = "FAILED";

        String log_RESULTCODE = "";
        String log_DESC = "";

        //get Data from CCA
        String msisdn = smoiIns.getMsisdn();
        long balance = 0;
        String subspid = "";
//        InquirySubscriberResponse inquirySubscriber = smoiIns.getInquirySubscriberResponse();
//        String accountingInfo = "";
//        String ds2classOfservice = "";
//        if (inquirySubscriber != null) {
//            accountingInfo = inquirySubscriber.getAccountingInfo();
//            if (accountingInfo != null && !accountingInfo.trim().equals("")) {
//                ds2classOfservice = accountingInfo.split(",")[0];
//            }
//        }

        String subcosid = "";
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
//        long creditlimit = 0;
//        String score = "";
//        String voicemail = "";
//        String activecac = "";
//        String groupid = "";
        long prmsm = 0;
        long prmminute = 0;
//        String querytimes = "";
//        String rbtflag = "";
//        String agingdis = "";
        String timeenteractive = "";
//        String ccc = "";
//        String volumndiscountflag = "";
//        String fphflag = "";
//        String lastfph = "";
//        String callscreenflag = "";
//        String interroamflag = "";
        String smslangtype = "";
//        String smusage = "";
//        String smusagetoptime = "";
//        String prmscore = "";
//        String prmscorestoptime = "";
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
//        String paymentMode = null;
        String callnotifyflag = "";
        ServiceInformation serviceInfo = cca.getServiceInformation();
        INInformation inInf = serviceInfo.getInInformation();


        if (res.equals("2001") || (res.equals("5003"))) {
        	
            if (serviceInfo != null) {
                //INInformation
//                SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                if (inInf != null) {
                    if (inInf.getActivePeriodAVATAR() != null && !inInf.getActivePeriodAVATAR().trim().equals("")) {
                        activestop = octet.Convert2String(inInf.getActivePeriodAVATAR());
                    }
                    if (inInf.getGracePeriod() != null && !inInf.getGracePeriod().trim().equals("")) {
                        suspendstop = octet.Convert2String(inInf.getGracePeriod());
                    }
                    if (inInf.getDisablePeriodAVATAR() != null && !inInf.getDisablePeriodAVATAR().trim().equals("")) {
                        disablestop = octet.Convert2String(inInf.getDisablePeriodAVATAR());
                        servicestop = octet.Convert2String(inInf.getDisablePeriodAVATAR());
                    }

                    //SUBCOSID, LANGUAGETYPE, SMSLANGTYPE
                    if (inInf.getMainProductID() != null && !inInf.getMainProductID().trim().equals("")) {
                        subcosid = inInf.getMainProductID();
                    }
                    if (inInf.getIVRLang() != null && !inInf.getIVRLang().trim().equals("")) {
                        languagetype = inInf.getIVRLang();
                        if(languagetype.equals("1")){
                        	languagetype ="1";
                        }else if (languagetype.equals("2")){
                        	languagetype ="2";
                        }else {
                        	languagetype ="3";
                        }
                    }
                    if (inInf.getSMSLang() != null && !inInf.getSMSLang().trim().equals("")) {
                        smslangtype = inInf.getSMSLang();
                        if(smslangtype.equals("1")){
                        	smslangtype ="1";
                        }else if (smslangtype.equals("2")){
                        	smslangtype ="2";
                        }else {
                        	smslangtype ="3";
                        }
                    }

                    maxactivedays = inInf.getMaximumValidity();
                    fraudlock = inInf.getFraudLockAVATAR();
                    maxcounttotal = inInf.getMaximumBalance();
                    brandid = inInf.getBrand();
                    if(inInf.getActivationDate() != null){
                    	balance = Long.parseLong(inInf.getPPSBalance());	
                    }
                    subcosid = inInf.getMainProductID();
                    if(inInf.getActivationDate() != null){
                    	timeenteractive = octet.Convert2String(inInf.getActivationDate());	
                    }

                    //BalanceInfo
                    List<BalanceInfo> balanceInfo = inInf.getBalanceInfo();
                    HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
                    String sms_17 = smoi_conf.get(Conf.avatar_balance_SMS_Domestic).get(0);
                    String voice_11 = smoi_conf.get(Conf.avatar_balance_Voice_Domestic).get(0);

                    if (balanceInfo != null && balanceInfo.size() > 0) {
                        for (BalanceInfo bi : balanceInfo) {
                            if (sms_17.contains(",")) {
                                for (String sms : sms_17.split(",", -1)) {
                                    if (sms.equals("17")) {
                                        if (bi.getBalance_Type() != null && bi.getBalance_Type().startsWith(sms)) {
                                        	if(bi.getCurrent_Balance() != null){
                                        		prmsm += Long.valueOf(bi.getCurrent_Balance());	
                                        	}
                                        }
                                    }
                                }
                            } else {
                                if (bi.getBalance_Category() != null && "2".equals(bi.getBalance_Category()) 
                                	&& bi.getBalance_Type() != null && bi.getBalance_Type().startsWith(sms_17) 
                                	&& bi.getBalanceDate() != null && (DateTime.getSecondNow() <= Long.valueOf(bi.getBalanceDate()))) {
                                	if(bi.getCurrent_Balance() != null){
                                		prmsm += Long.valueOf(bi.getCurrent_Balance());	
                                	}
                                }
                            }

                            if (voice_11.contains(",")) {
                                for (String voice : voice_11.split(",", -1)) {
                                    if (voice.equals("11")) {
                                        if (bi.getBalance_Type() != null && bi.getBalance_Type().startsWith(voice)) {
                                        	if(bi.getCurrent_Balance() != null){
                                        		prmminute += Long.valueOf(bi.getCurrent_Balance());	
                                        	}
                                        }
                                    }
                                }
                            } else {
                                if (bi.getBalance_Category() != null && "2".equals(bi.getBalance_Category()) 
                                	&& bi.getBalance_Type() != null	&& bi.getBalance_Type().startsWith(voice_11) 
                                	&& bi.getBalanceDate() != null && (DateTime.getSecondNow() <= Long.valueOf(bi.getBalanceDate()))) {
                                	if(bi.getCurrent_Balance() != null){
                                		prmminute += Long.valueOf(bi.getCurrent_Balance());
                                	}
                                    
                                }
                            }
                        }
                    }
                }
            }
        	
            if (res.equals("2001")) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Response, smoiIns);
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_DispPPSInfo_Response_Success, smoiIns);

                res = "000";
                desc = "Query the subscriber's basic information successfully.";

                log_RESULTCODE = res;
                log_DESC = desc;
            } else if (res.equals("5003")) {
                if (("0".equals(customerLife) || "8".equals(customerLife))) {
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Response_Error, smoiIns);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_DispPPSInfo_Response_Success, smoiIns);

                    res = "000";
                    desc = "User service information query succeeds";
                    log_RESULTCODE = res;
                    log_DESC = desc;
                } else {
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Response_Error, smoiIns);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_DispPPSInfo_Response_Error, smoiIns);

                    sb.append("<vcrr>")
                            .append("<res>").append(res).append("</res>")
                            .append("<desc>").append("FAILED").append("</desc>")
                            .append("</vcrr>");

                    log_RESULTCODE = "5003";
                    log_DESC = "DIAMETER_AUTHORIZATION_REJECT";

                    msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
                    msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
                    msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
                    return msgReturn;
                }
            }

            sb.append("<vcrr>");
            sb.append("<res>").append(res).append("</res>");
            sb.append("<desc>").append(desc).append("</desc>");
            sb.append("<msisdn>").append(msisdn).append("</msisdn>");
            sb.append("<balance>").append(balance).append("</balance>");
            sb.append("<subspid>").append(subspid).append("</subspid>");
            sb.append("<subcosid>").append(subcosid).append("</subcosid>");
            sb.append("<accountstate>").append(mapAccountState(cca)).append("</accountstate>");
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
            sb.append("<creditlimit>").append("0").append("</creditlimit>");
            sb.append("<score>").append("").append("</score>");
            sb.append("<voicemail>").append("").append("</voicemail>");
            sb.append("<activecac>").append("").append("</activecac>");
            sb.append("<groupid>").append("").append("</groupid>");
            sb.append("<prmsm>").append(prmsm).append("</prmsm>");
            sb.append("<prmminute>").append(prmminute).append("</prmminute>");
            sb.append("<querytimes>").append("").append("</querytimes>");
            sb.append("<rbtflag>").append("").append("</rbtflag>");
            sb.append("<agingdis>").append("").append("</agingdis>");
            sb.append("<timeenteractive>").append(timeenteractive).append("</timeenteractive>");
            sb.append("<ccc>").append("").append("</ccc>");
            sb.append("<volumndiscountflag>").append("").append("</volumndiscountflag>");
            sb.append("<fphflag>").append("").append("</fphflag>");
            sb.append("<lastfph>").append("").append("</lastfph>");
            sb.append("<callscreenflag>").append("").append("</callscreenflag>");
            sb.append("<interroamflag>").append("").append("</interroamflag>");
            sb.append("<smslangtype>").append(smslangtype).append("</smslangtype>");
            sb.append("<smusage>").append("").append("</smusage>");
            sb.append("<smusagetoptime>").append("").append("</smusagetoptime>");
            sb.append("<prmscore>").append("").append("</prmscore>");
            sb.append("<prmscorestoptime>").append("").append("</prmscorestoptime>");
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
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_AVATAR_$s_Response_Error, smoiIns);
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_HTTP_DispPPSInfo_Response_Error, smoiIns);


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

    private static String mapCustomerLifeCycle(DiameterCreditControlAnswer cca) {
        String cusCycle = null;
        if (cca != null) {
            if (cca.getServiceInformation() != null) {
                if (cca.getServiceInformation().getInInformation() != null) {
                    String state = cca.getServiceInformation().getInInformation().getState();
                    String managementstate = cca.getServiceInformation().getInInformation().getManagementstate();
                    String fraudLock = cca.getServiceInformation().getInInformation().getFraudLockAVATAR();
                    if ("0".equals(state) && "2".equals(managementstate) && "0".equals(fraudLock)) {
                        cusCycle = "0";
                    } else if ("1".equals(state) && "2".equals(managementstate) && "0".equals(fraudLock)) {
                        cusCycle = "1";
                    } else if ("1".equals(state) && "2".equals(managementstate) && "1".equals(state)) {
                        cusCycle = "2";
                    } else if ("2".equals(state) && "2".equals(managementstate) && "0".equals(fraudLock)) {
                        cusCycle = "3";
                    } else if ("3".equals(managementstate) || "4".equals(managementstate)) {
                        cusCycle = "4";
                    } else if ("2".equals(state) && "2".equals(managementstate) && "1".equals(fraudLock)) {
                        cusCycle = "5";
                    } else if ("3".equals(state) && "2".equals(managementstate) && "1".equals(fraudLock)) {
                        cusCycle = "6";
                    } else if ("3".equals(state) && "2".equals(managementstate) && "0".equals(fraudLock)) {
                        cusCycle = "7";
                    } else if ("4".equals(state)) {
                        cusCycle = "8";
                    } else {
                        cusCycle = "0";
                    }
                }
            }
        }
        return cusCycle;
    }

    private static String mapAccountState(DiameterCreditControlAnswer cca) {
        String accState = null;
        String customerLifeCycle = mapCustomerLifeCycle(cca);
        if (customerLifeCycle.equals("0")) {
            accState = "01";
        } else if (customerLifeCycle.equals("1") || customerLifeCycle.equals("2")) {
            accState = "02";
        } else if (customerLifeCycle.equals("3") || customerLifeCycle.equals("4") || customerLifeCycle.equals("5")) {
            accState = "03";
        } else if (customerLifeCycle.equals("6") || customerLifeCycle.equals("7")) {
            accState = "04";
        } else if (customerLifeCycle.equals("8")) {
            accState = "05";
        }
        return accState;
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
}