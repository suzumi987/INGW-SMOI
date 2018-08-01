package phoebe.eqx.smoi.state;

import ec02.af.abstracts.AbstractAF;


import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;
import ec02.utils.Log;
import phoebe.eqx.main.EquinoxMessageBuilder;
import phoebe.eqx.main.EquinoxMessageParser;
import phoebe.eqx.model.ldap.ds2a.InquirySubscriberResponse;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.InquirySubscriberRequest;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.message.parser.AccountingInfoParser;
import phoebe.eqx.smoi.utils.EQLUtils;
import phoebe.eqx.smoi.utils.MappingMessage;
import phoebe.eqx.smoi.utils.SMOIUtils;
import phoebe.eqx.smoi.utils.SmoiStatAlarm;

import java.util.ArrayList;

public class StateWaitDS2A implements IAFState {

    @Override
    public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
        String nextState = AFState.IDLE;
        MyAppData myAppData = (MyAppData) instance;
        SmoiInstance smoiIns = myAppData.getSmoiIns();

        for (EquinoxRawData r : rawDatas) {
            String inputMsg = r.getRawDataMessage();
            String outputMsg = "";
            String invokeId = r.getInvoke();

            if (r.getRawEventType().equals(AFEvent.IncomingInquirySubscriberRes)) {
                String ecode = r.getRawDataAttribute("ecode");
                Log.d("LDAP Ecode=" + ecode);
                if (ecode.equals("0")) {
                    EquinoxMessageParser parser = EquinoxMessageParser.newInstance();
                    parser.setEquinoxMessage(r.getRawDataMessage());
                    InquirySubscriberResponse inquirySubscriberResponse = null;
                    try {
                        inquirySubscriberResponse = parser.getLDAPTranslator().getDS2ATranslator().translateInquirySubscriberRes();
                        smoiIns.setInquirySubscriberResponse(inquirySubscriberResponse);
                    } catch (Exception e) {
                        Log.e("Error Massage:" + e.getMessage(), e);
                    }
                    String resultInfo = inquirySubscriberResponse.getResultInfo();
                    Log.d("Inquiry Subscriber ResultInfo:" + resultInfo);

                    if ("221 Key Unknown".equalsIgnoreCase(resultInfo)) {

                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Unknown_MSISDN, smoiIns);

//                        if(smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
//	                        outputMsg = createDS2UnknownMsisdnMessage();
//	                        SendMessage.Client(outputMsg, af, myAppData);
//	
//	                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_ModiPPSCallNotifyFlag_Response_Error, smoiIns);
//                    	} else {
//	                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Unknown_MSISDN, smoiIns);
	                        outputMsg = createDS2UnknownMsisdnMessage();
	                        SendMessage.Client(outputMsg, af, myAppData);
	
	                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
//	                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_Unknown_MSISDN_Response, smoiIns);
//                    	}
                        String res = SMOIUtils.getXMLText(outputMsg, "res");
                        String desc = SMOIUtils.getXMLText(outputMsg, "desc");
                        myAppData.setInput_Msg(inputMsg);
                        myAppData.setInput_InvokeId(invokeId);
                        myAppData.setInput_resultcode(res);
                        myAppData.setInput_desc(desc);
                        myAppData.setOutput_Msg(outputMsg);

                        return AFState.IDLE;

                    } else {
                        AccountingInfoParser accInfoParser = new AccountingInfoParser(inquirySubscriberResponse.getAccountingInfo());
                        if (accInfoParser.isSuccessParsing()
                        		&& inquirySubscriberResponse.getPrefixAccountingInfo() != null && !inquirySubscriberResponse.getPrefixAccountingInfo().isEmpty()
                        		&& inquirySubscriberResponse.getResultInfo() != null && !inquirySubscriberResponse.getResultInfo().isEmpty()
                        		&& inquirySubscriberResponse.getToggledAccountingInfo() != null && !inquirySubscriberResponse.getToggledAccountingInfo().isEmpty() ) {

                            String scp_id = accInfoParser.getDs2cbpLocation();

                            if (scp_id.equals("") || scp_id == null) {
                                scp_id = accInfoParser.getDs2serviceLocation();
                            }

//                            if (smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())) {
//                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response, smoiIns);
//                            } else {
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response, smoiIns);
//                            	 SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriberResponse_Success, smoiIns);
//                            }


                            smoiIns.setScp(scp_id);
                            Log.d("Scp Id=" + scp_id);

                            if (scp_id.equals("")) {
                                outputMsg = createNotFoundServiceLocationMessage(smoiIns.getPage());
                                SendMessage.Client(outputMsg, af, myAppData);

                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_Not_Found_Service_Location_Response, smoiIns);
                                nextState = AFState.IDLE;
                                break;
                            }

                            MappingMessage messageFn = new MappingMessage(af);
                            outputMsg = messageFn.CreateMessage(myAppData);
                            String state = messageFn.getEState();

                            if (state.equals(AFState.W_BSSbroker)) {
                                SendMessage.BSSBroker(outputMsg, af, myAppData, Conf.resourceNameBssbroker_Active);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_BSSBroker_$s_Request, smoiIns);
                            } else if (state.equals(AFState.W_PPGW)) {
                                SendMessage.PPGW(outputMsg, af, myAppData, Conf.resourceNamePpgw_Active);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_SGSCP_PPGW_$s_Request, smoiIns);
                            } else if (state.equals(AFState.W_SMOI) && smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())) {
//                                outputMsg = outputMsg + "&scpid=" + scp_id;
                            	outputMsg = "<vcrr><res>322</res><desc>INGateway Send HTTP Response Incomplete Data</desc></vcrr>";  	
//                                SendMessage.SMOI(outputMsg, af, myAppData, Conf.resourceNameSmoi_Active);
                            	SendMessage.Client(outputMsg, af, myAppData);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_ModiPPSCallNotifyFlag_Response_Incomplete_Data, smoiIns);
                                state = AFState.IDLE; 
                            } else if (state.equals(AFState.W_SMOI)) {
                                outputMsg = outputMsg + "&scpid=" + scp_id;
                                SendMessage.SMOI(outputMsg, af, myAppData, Conf.resourceNameSmoi_Active);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_SGSCP_SMOI_$s_Request, smoiIns);
                            } else if (state.equals(AFState.W_INS)) {
                                SendMessage.INS(outputMsg, af, myAppData, Conf.resourceNameIns_Active);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_INGW_INS_$s_Request, smoiIns);
                            } else if (state.equals(AFState.IDLE)) {
                                SendMessage.Client(outputMsg, af, myAppData);
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Incomplete_Data, smoiIns);
//                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_BOS_INCOMPLETE_DATA_RESPONSE, smoiIns);
                            } else if (state.equals(AFState.W_E01_DESTINATION)) {
                                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_E01_QueryDestination_Request, smoiIns);
                            } else if (state.equals(AFState.W_EQL)) { // Chatl 20/09/2017
                            	SendMessage.eql(outputMsg, af, myAppData, Conf.resourceNameEQL_Active);
                            	EQLUtils.writeStatEQL(smoiIns,af);
                            } else if (state.equals(AFState.W_MD)) { // Max 19/03/2018
                            	SendMessage.MD(outputMsg, af, myAppData, Conf.resource_Name_Active_MD);
                            	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGATEWAY_SEND_MD_BSO_CBS_SUBSTATUS_REQUEST, smoiIns);
                            	//EQLUtils.writeStatEQL(smoiIns,af);
                            }
                            nextState = state;

                        } else {
//                        	if(smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
//                        		
//                        		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_Bad_InquirySubscriber_Response, smoiIns);
//	                            outputMsg = createNotFoundServiceLocationMessage(smoiIns.getPage());
//	                            SendMessage.Client(outputMsg, af, myAppData);
//	
//	                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_ModiPPSCallNotifyFlag_Response_Error, smoiIns);
//                        	
//                        	} else {
	                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_Bad_InquirySubscriber_Response, smoiIns);
//	                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriberResponse_Incomplete_Data, smoiIns);
	                            outputMsg = createNotFoundServiceLocationMessage(smoiIns.getPage());
	                            SendMessage.Client(outputMsg, af, myAppData);
	
	                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
//	                            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_Not_Found_Service_Location_Response, smoiIns);
//                        	}
	                        nextState = AFState.IDLE;
                        }
                    }
                } else { //Ecode != 0 LDAP Error !
                	
                	if(smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
                		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Error, smoiIns);
	                    outputMsg = createInquirySubscriberErrorMessage(r ,smoiIns.getPage());
	                    SendMessage.Client(outputMsg, af, myAppData);
	
	                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_ModiPPSCallNotifyFlag_Response_Error, smoiIns);
                	}else{
                		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Error, smoiIns);
	                    outputMsg = createInquirySubscriberErrorMessage(r ,smoiIns.getPage());
	                    SendMessage.Client(outputMsg, af, myAppData);
	
	                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                	}
	                nextState = AFState.IDLE;
                }
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Error, smoiIns);
                outputMsg = createEquinoxErrorMessage( smoiIns.getPage());
                SendMessage.Client(outputMsg, af, myAppData);

                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
//                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_Internal_Error_Response, smoiIns);
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {
                int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resourceNameDs2a_Standby_MaxRetry);
                if (maxRetry > 0) {
                    //Standby_MaxRetry
                    ec02.utils.Log.d("Retry: " + Conf.resourceNameDs2a_Standby_MaxRetry + " maxRetry: " + maxRetry);
                    maxRetry -= 1;
                    smoiIns.setResourceNameStandbyMaxRetry(Conf.resourceNameDs2a_Standby_MaxRetry, maxRetry);
                    inputMsg = "";
                    outputMsg = StateWaitDS2A.createInquirySubscriberRequestMessage(smoiIns, r);
                    SendMessage.DS2A(outputMsg, af, myAppData, Conf.resourceNameDs2a_Standby);
                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Reject, smoiIns);
                    if(smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
 	                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_DS2A_InquirySubscriber_Request, smoiIns);
                    } else {
//	                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriberRequest_Reject, smoiIns);
	                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_DS2A_InquirySubscriber_Request, smoiIns);
                    } 
	               nextState = AFState.W_DS2A;
                } else {
                    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Reject, smoiIns);
                    if(smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
                		outputMsg = createEquinoxRejectMessage(smoiIns.getPage());
                        SendMessage.Client(outputMsg, af, myAppData);

                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_ModiPPSCallNotifyFlag_Response_Error, smoiIns);
                	} else {
//                		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriberRequest_Reject, smoiIns);
                		outputMsg = createEquinoxRejectMessage( smoiIns.getPage());
                        SendMessage.Client(outputMsg, af, myAppData);

                        SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                	}
                	
                    nextState = AFState.IDLE;
                }
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {
//            	if(smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
//            		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Abort, smoiIns);
//            		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_ModiPPSCallNotifyFlag_Response_Error, smoiIns);
//            	} else {
//            		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriberRequest_Abort, smoiIns);
//            		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_Internal_Error_Response, smoiIns);
//            	}
            	
            	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Abort, smoiIns);
        		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Abort, smoiIns);
                
                outputMsg = createEquinoxAbortMessage(smoiIns.getPage());
                SendMessage.Client(outputMsg, af, myAppData);
                
                nextState = AFState.IDLE;
            } else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {
            	
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_DS2A_InquirySubscriber_Response_Timeout, smoiIns);
                
                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
//                if(smoiIns.getPage().equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
//            		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_ModiPPSCallNotifyFlag_Response_Error, smoiIns);
//            	} 
                
                outputMsg = createDS2ATimeoutMessage(smoiIns.getPage());
                SendMessage.Client(outputMsg, af, myAppData);

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

    public static String createInquirySubscriberRequestMessage(SmoiInstance smoiIns, EquinoxRawData r) {
        String msg = "";
        String ms = smoiIns.getMsisdn();
        InquirySubscriberRequest inquirySubscriberRequest = new InquirySubscriberRequest();
//        String event = r.getRawEventType();
        inquirySubscriberRequest.setMethodVersion("6");
        inquirySubscriberRequest.setMsisdn(ms);
        EquinoxMessageBuilder builder = EquinoxMessageBuilder.newInstance();
        builder.setMessage(inquirySubscriberRequest);
        builder.createXMLMessage();
        msg = builder.getMessage();
        return msg;
    }

    private String createDS2UnknownMsisdnMessage() {
        String msg;
        //Unknown Msisdn
        String res = EResponseCode.DS2_Unknown_Msisdn.getCode();
        String desc = EResponseCode.DS2_Unknown_Msisdn.getDesc();
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createNotFoundServiceLocationMessage(String page) {
        String msg;
        String res;
        String desc;
        if(page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
        	res = EResponseCode.DS2A_Bad.getCode();
            desc = EResponseCode.DS2A_Bad.getDesc();
        } else {
        	res = EResponseCode.DS2A_Bad.getCode();
            desc = EResponseCode.DS2A_Bad.getDesc();
        }
        
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createInquirySubscriberErrorMessage(EquinoxRawData r, String page) {
        String msg;
        String res;
        String desc;
        if(page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
	        res = EResponseCode.DS2A_InquirySubscriberRequestError_333.getCode();
	        desc = EResponseCode.DS2A_InquirySubscriberRequestError_333.getDesc();
        } else {
        	res = EResponseCode.DS2A_InquirySubscriberRequestError_333.getCode();
	        desc = EResponseCode.DS2A_InquirySubscriberRequestError_333.getDesc();
        }
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createEquinoxErrorMessage(String page) {
        String msg;
        String res ;
        String desc ;
        if(page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
        	res = EResponseCode.DS2A_InquirySubscriber_Error_response.getCode();
            desc = EResponseCode.DS2A_InquirySubscriber_Error_response.getDesc();
        } else {
        	res = EResponseCode.DS2A_InquirySubscriberRequest_Error.getCode();
            desc = EResponseCode.DS2A_InquirySubscriberRequest_Error.getDesc();
        }
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createEquinoxRejectMessage(String page) {
        String msg;
        String res ;
        String desc ;
        if(page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
        	res = EResponseCode.DS2A_InquirySubscriber_Reject_response.getCode();
            desc = EResponseCode.DS2A_InquirySubscriber_Reject_response.getDesc();
        } else {
        	res = EResponseCode.DS2A_InquirySubscriber_Response_Reject.getCode();
            desc = EResponseCode.DS2A_InquirySubscriber_Response_Reject.getDesc();
//            res = EResponseCode.DS2A_InquirySubscriberRequest_Reject.getCode();
//            desc = EResponseCode.DS2A_InquirySubscriberRequest_Reject.getDesc();
        }
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createEquinoxAbortMessage(String page) {
        String msg;
        String res;
        String desc;
        if(page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
        	res = EResponseCode.DS2A_InquirySubscriber_Abort_response.getCode();
            desc = EResponseCode.DS2A_InquirySubscriber_Abort_response.getDesc();
        } else {
        	res = EResponseCode.DS2A_InquirySubscriberRequest_Abort.getCode();
            desc = EResponseCode.DS2A_InquirySubscriberRequest_Abort.getDesc();
        }
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

    private String createDS2ATimeoutMessage(String page) {
        String msg;
        String res;
        String desc;
        if(page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())){
        	res = EResponseCode.DS2A_Timeout_response.getCode();
            desc = EResponseCode.DS2A_Timeout_response.getDesc();
        } else {
        	res = EResponseCode.DS2A_Timeout_response.getCode();
            desc = EResponseCode.DS2A_Timeout_response.getDesc();
//            res = EResponseCode.DS2A_Timeout.getCode();
//            desc = EResponseCode.DS2A_Timeout.getDesc();

        }
        msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        return msg;
    }

//    public void writeStatEQL(SmoiInstance smoiIns,AbstractAF af ){
//      if(smoiIns.getAdjustType().equals(AdjustType.VALIDITY)){
//   		  SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_VALIDITY_Request, smoiIns);
//   		  if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
//   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns);  
//   		  }
//   	  }else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE)){
//   		  SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_BALANCE_Request, smoiIns);  
//   	  }else if(smoiIns.getAdjustType().equals(AdjustType.FREEUNIT)){
//   		  SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_FREEUNIT_Request, smoiIns);  
//   	  }else if(smoiIns.getAdjustType().equals(AdjustType.BALANCEANDVALIDITY)){
//   		  SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Request, smoiIns);  
//   	  } 
//     
//    }
    
    public static String checkGroupBosLocation(String scp_id, AbstractAF af) {

        String tempNoGroupBos = "";
        for (int i = 0; i < 5; i++) {
            tempNoGroupBos = "00" + String.valueOf(i);
            ArrayList<String> groupBos = af.getUtils().getHmWarmConfig().get("BOS" + tempNoGroupBos);
            if (null != groupBos && groupBos.size() != 0) {
                String[] groupBosArray = groupBos.get(0).split(",", -1);
                for (int gbl = 0; gbl < groupBosArray.length; gbl++) {
                    if (scp_id.equals(groupBosArray[gbl])) {
                        return tempNoGroupBos;
                    }
                }
            }
        }
        return "";
    }
}
