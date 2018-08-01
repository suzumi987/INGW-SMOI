package phoebe.eqx.smoi.state;

import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;
import ec02.utils.Log;
import phoebe.eqx.model.dcc.cca.*;
import phoebe.eqx.model.ldap.ds2a.InquirySubscriberResponse;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.bmpManageSCPProfile.ManageSCPProfileRes;
import phoebe.eqx.smoi.bean.bmpQuerySCPProfile.QuerySCPProfileRes;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.message.soapmsg.*;
import phoebe.eqx.smoi.utils.MappingMessage;
import phoebe.eqx.smoi.utils.Msisdn;
import phoebe.eqx.smoi.utils.ResponseCode;
import phoebe.eqx.smoi.utils.SMOIUtils;
import phoebe.eqx.smoi.utils.SmoiStatAlarm;
import phoebe.eqx.smoi.wsdl.CommonComponents.SCallScreenNo;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.*;
import smoi.enums.EAccountState;
import smoi.enums.EMsgTagType;
import smoi.enums.EResourceGroupIdPrefix;
import smoi.message.OctetString;

import java.text.SimpleDateFormat;
import java.util.*;

public class StateWaitBSSbroker implements IAFState {
	
	private OctetString octet = new OctetString();
	
	@Override
	public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
		String nextState = AFState.IDLE;
		MyAppData myAppData = (MyAppData) instance;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String page = smoiIns.getPage();

		for (EquinoxRawData r : rawDatas) {
			String inputMsg=r.getRawDataMessage();
			String outputMsg = "";
			String invokeId = r.getInvoke();
			if (r.getRawEventType().equals(AFEvent.IncomingHttpRes)) {
				if (r.getRawDataMessage().contains("do_QueryFNResponse")) {
					/**
					 * @see phase 2
                    msg = mapDispPPSFntelNoCmd(r,af,smoiIns);                
					 */
				} else if (r.getRawDataMessage().contains("do_QueryCallScreenResponse")) {
					outputMsg = mapDispScrScreenNoCmd(r, af, smoiIns);
				} else if (r.getRawDataMessage().contains("do_CallScreenNoResponse")) {
					CallScreenNoService callScreenNoService = new CallScreenNoService();
					callScreenNoService.CreateObj(r.getRawDataMessage());
					DoCallScreenNoResponse doCallScreenNoResponse = callScreenNoService.getDoCallScreenNoResponse();
					if (page.equals(ECommand.addScrScreenNo.getCommand())) {
						outputMsg = mapAddScrScreenNoCmd(doCallScreenNoResponse, af, smoiIns);
					} else if (page.equals(ECommand.deleScrScreenNo.getCommand())) {
						outputMsg = mapDeleScrScreenNoCmd(doCallScreenNoResponse, af, smoiIns);
					} else if (page.equals(ECommand.modiScrWhiteList.getCommand())) {
						outputMsg = mapModiScrWhiteListCmd(doCallScreenNoResponse, af, smoiIns);
					}

					/**
					 * } else if (page.equals(ECommand.modiScrScreenType.getCommand())) {
					 * outputMsg = mapModiScrScreenTypeCmd(doCallScreenNoResponse);
					 */

				} else if (r.getRawDataMessage().contains("do_ChangeServiceResponse")) {
					ChangeServiceService changeServiceService = new ChangeServiceService();
					changeServiceService.CreateObj(r.getRawDataMessage());
					DoChangeServiceResponse doChangeServiceResponse = changeServiceService.getDoChangeServiceResponse();
					if (page.equals(ECommand.purchasePPSPackage.getCommand())) {
						outputMsg = mapPurchasePPSPackageCmd(doChangeServiceResponse,af,smoiIns);
						/**
						 * @see phase 2
                    } else if (page.equals(ECommand.actPPSRBT.getCommand())) {
                        msg = mapActPPSRBTCmd(doChangeServiceResponse);
                    } else if (page.equals(ECommand.delePPSPkgres.getCommand())) {
                        msg = mapDelePPSPkgresCmd(doChangeServiceResponse);
                    } else if (page.equals(ECommand.delePPSPackageID.getCommand())) {
                        msg = mapDelePPSPackageIDCmd(doChangeServiceResponse);
                    } else if (page.equals(ECommand.setPPSReward.getCommand())) {
                        msg = mapSetPPSRewardCmd(doChangeServiceResponse);
						 */
					}
				} else if (r.getRawDataMessage().contains("do_FirstActivationCRMResponse")) {
					outputMsg = mapActivatePPSSingSubCmd(r,af,smoiIns);
				} else if (r.getRawDataMessage().contains("do_ModifySubsciberBasicInfoResponse")) {
					/**
					 * @see phase 2
                    ModifySubscriberBasicInfoService modifySubscriberBasicInfoService = new ModifySubscriberBasicInfoService();
                    modifySubscriberBasicInfoService.CreateObj(r.getRawDataMessage());
                    DoModifySubscriberBasicInfoResponse doModifySubsciberBasicInfoResponse = modifySubscriberBasicInfoService.getDoModifySubsciberBasicInfoResponse();
                    if (page.equals(ECommand.modiPPSLanguage.getCommand())) {
                        msg = mapModiPPSLanguageCmd(doModifySubsciberBasicInfoResponse);
                    } else if (page.equals(ECommand.modiPPSSMSLanguage.getCommand())) {
                        msg = mapModiPPSSMSLanguageCmd(doModifySubsciberBasicInfoResponse);
                    }
					 */
				} else if (r.getRawDataMessage().contains("do_SetNegativeBalanceResponse")) {
					SetNegativeBalanceService setNegativeBalanceService = new SetNegativeBalanceService();
					setNegativeBalanceService.CreateObj(r.getRawDataMessage());
					DoSetNegativeBalanceResponse doSetNegativeBalanceResponse = setNegativeBalanceService.getDoSetNegativeBalanceResponse();
					outputMsg = mapModiPPSCreditLimitCmd(doSetNegativeBalanceResponse,af,smoiIns);
				} else if (r.getRawDataMessage().contains("do_AdjustBalance")) {
                    AdjustBalanceService adjustBalanceService = new AdjustBalanceService();
                    adjustBalanceService.CreateObj(r.getRawDataMessage());
                    DoAdjustBalanceResponse doAdjustBalanceResponse = adjustBalanceService.getDoAdjustBalanceResponse();
                    outputMsg = mapSetPPSRewardCmd(doAdjustBalanceResponse, af, smoiIns);
                } else if (r.getRawDataMessage().contains("do_ManageSCPProfile")) {
                	ManageSCPProfile manageSCPProfile = new ManageSCPProfile();
                	ManageSCPProfileRes manageSCPProfileRes = manageSCPProfile.readManageSCPProfileRes(r.getRawDataMessage());
//                	DoManageSCPProfileResponse doManageSCPProfileResponse = manageSCPProfile.getDoManageSCPProfileResponse();
                    outputMsg = mapModiPPSCallNotifyFlagCmd(manageSCPProfileRes, af, smoiIns);
                } else if (r.getRawDataMessage().contains("do_QuerySCPProfile")) {
					QuerySCPProfile querySCPProfile = new QuerySCPProfile();
					QuerySCPProfileRes querySCPProfileRes = querySCPProfile.readQuerySCPProfileres(r.getRawDataMessage());

//					outputMsg = mapDispPPSInfoCmd(querySCPProfileRes, af, smoiIns);
//                	DoManageSCPProfileResponse doManageSCPProfileResponse = manageSCPProfile.getDoManageSCPProfileResponse();
//                	querySCPProfile.CreateObj(r.getRawDataMessage());
//                	DoQuerySCPProfileResponse doManageSCPProfileResponse = querySCPProfile.getDoQuerySCPProfileResponse();
					HashMap<String, String> hmMessage = null;
					hmMessage = mapDispPPSInfoCmd(smoiIns.getCca(), af, smoiIns , querySCPProfileRes);
                    outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
                    /*log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
                    log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());*/
                    
                }
				
				SendMessage.Client(outputMsg, af, myAppData);
				String res = SMOIUtils.getXMLText(outputMsg, "res");
				if("000".equals(res)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
				}else{
//					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					chooseStatForCaseAbNormal(af, smoiIns.getPage(), smoiIns);
				}
				nextState = AFState.IDLE;
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);
				outputMsg = generateResponseForCaseAbNormal(af, smoiIns , page, r.getRawEventType(), null);
				SendMessage.Client(outputMsg, af, myAppData);
				chooseStatForCaseAbNormal(af, smoiIns.getPage(), smoiIns);
				nextState = AFState.IDLE;
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {
				int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resourceNameBssbroker_Standby_MaxRetry);
				if(maxRetry>0){       
					//TODO Standby_MaxRetry
					ec02.utils.Log.d("Retry: "+Conf.resourceNameBssbroker_Standby_MaxRetry+" maxRetry: "+maxRetry);
					maxRetry -= 1;
					smoiIns.setResourceNameStandbyMaxRetry(Conf.resourceNameBssbroker_Standby_MaxRetry,maxRetry);

					MappingMessage messageFn = new MappingMessage(af);
					inputMsg="";
					outputMsg = messageFn.CreateMessage(myAppData);
					nextState=messageFn.getEState();

					SendMessage.BSSBroker(outputMsg, af, myAppData,Conf.resourceNameBssbroker_Standby);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Reject, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Send_BSSBroker_$s_Request, smoiIns);
				}else{
					smoiIns.setaBoolean(true);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Reject, smoiIns);
					outputMsg = generateResponseForCaseAbNormal(af, smoiIns , page, r.getRawEventType(), null);
					SendMessage.Client(outputMsg, af, myAppData);
					chooseStatForCaseAbNormal(af, smoiIns.getPage(), smoiIns);
					nextState = AFState.IDLE;
				}
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Abort, smoiIns);
				outputMsg = generateResponseForCaseAbNormal(af, smoiIns , page, r.getRawEventType(), null);
				SendMessage.Client(outputMsg, af, myAppData);
				
				chooseStatForCaseAbNormal(af, smoiIns.getPage(), smoiIns);
				nextState = AFState.IDLE;
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {
				smoiIns.setaBoolean(true);
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Timeout, smoiIns);
				outputMsg = generateResponseForCaseAbNormal(af, smoiIns , page, r.getRawEventType(), null);
				SendMessage.Client(outputMsg, af, myAppData);
				chooseStatForCaseAbNormal(af, smoiIns.getPage(), smoiIns);
				nextState = AFState.IDLE;
			} else {
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Receive_Unknown_Event, smoiIns);
				SendMessage.EqxClearInstance(af, myAppData);
				nextState = AFState.IDLE;
			}

//			if (page.equals(ECommand.dispPPSInfo.getCommand())) {
				String res = SMOIUtils.getXMLText(outputMsg, "res");
				String desc = SMOIUtils.getXMLText(outputMsg, "desc");
				myAppData.setInput_Msg(inputMsg);
				myAppData.setInput_InvokeId(invokeId);
				myAppData.setInput_resultcode(res);
				myAppData.setInput_desc(desc);
				myAppData.setOutput_Msg(outputMsg);
				
//			}
		}

		return nextState;
	}
	private void chooseStatForCaseAbNormal(AbstractAF af, String page, SmoiInstance smoiIns) {
		if (page.equals(ECommand.dispPPSInfo.getCommand())) {
			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
		} else {
			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
		}
		
	}
	
	private String generateResponseForCaseAbNormal(AbstractAF af, SmoiInstance smoiIns, String page, String rawMsgType, QuerySCPProfileRes querySCPProfileRes) {
		if (page.equals(ECommand.dispPPSInfo.getCommand())) {
			HashMap<String, String> hmMessage = null;
			hmMessage = mapDispPPSInfoCmd(smoiIns.getCca(), af, smoiIns , null);
			return hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
		
		} else {
			if(rawMsgType.equals(AFEvent.IncomingEquinox_Error))
				return mapEquinoxErrorCmd(smoiIns);
			if(rawMsgType.equals(AFEvent.IncomingEquinox_Reject))
				return mapEquinoxRejectCmd(smoiIns);
			if(rawMsgType.equals(AFEvent.IncomingEquinox_Abourt))
				return mapEquinoxAbortCmd(smoiIns);
			if(rawMsgType.equals(AFEvent.IncomingEquinox_Timeout))
				return mapEquinoxTimeoutCmd(smoiIns);
		}
		return "";
	}
	
	private HashMap<String, String> mapDispPPSInfoCmd(DiameterCreditControlAnswer cca, AbstractAF af, SmoiInstance smoiIns, QuerySCPProfileRes querySCPProfileRes) {
		
		HashMap<String, String> msgReturn = new HashMap<String, String>();
		StringBuilder sb = new StringBuilder("");
		
		try {

			String resBss = null;
			String resBos = null;
			String desc = null;
			String nbr = null;
			String date = null;
			if (querySCPProfileRes != null) {
//				SResultDescription sResult = doQuerySCPProfileResponse.getSResult();
				resBss = querySCPProfileRes.getResult_code();
				nbr = querySCPProfileRes.getSo_nbr();
				date = querySCPProfileRes.getFinish_date();
				
				if ((resBss == null || nbr == null || date == null )) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
				} else if (resBss.equals("1000000")) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);
				}else {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);
				}
			}

			

//	        if (nbr == null || date == null || resultCode == 0 || res == null) {


//				if (sResult == null || res.isEmpty()) {
//					sb.append("<vcrr>");
//					sb.append("<res>319</res>");
//					sb.append("<desc>INGateway Receive BSSBroker Bad DoManageSCPProfile Response</desc>");
//					sb.append("</vcrr>");
//				}

			
		        resBos = cca.getResultCode();
		        desc = "FAILED";

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
		                    if (e.getResultCode().equalsIgnoreCase(resBos)
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
						if (querySCPProfileRes != null) {
							callnotifyflag = querySCPProfileRes.getInterIntraflag();
						}
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
//		                                    if (now.compareTo(validDate) >= 0 && now.compareTo(expireDate) <= 0) {
		                                        balance += Long.valueOf(accInfo.getBookItemAmount());
//		                                    }
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

		        if (resBos.equals("2001") || (resBos.equals("5003")
		                && (customerLifeCycleState.equals("0")
		                || customerLifeCycleState.equals("8")
		                || customerLifeCycleState.equals("9")))) {

		            if (resBos.equals("2001")) {
		//                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Success, smoiIns);
		                resBos = "000";
		                desc = "Query the subscriber's basic information successfully.";

		                log_RESULTCODE = resBos;
		                log_DESC = desc;
		            } else if (resBos.equals("5003")) {
		//                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);
		                resBos = "000";
		                desc = "User service information query succeeds";

		                log_RESULTCODE = "5003";
		                log_DESC = "DIAMETER_AUTHORIZATION_REJECT";
		            }

		            sb.append("<vcrr>");
		            sb.append("<res>").append(resBos).append("</res>");
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

//					if (!smoiIns.getaBoolean()) {
//						if (!res.equals("1000000")) {
//							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_BSSBroker_$s_Response_Error, smoiIns);
//						} else {
//							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_BSSBroker_$s_Response, smoiIns);
//						}
//					}


				} /*else {
		//            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_$s_Response_Error, smoiIns);

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
		        }*/


		        msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
		        msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
		        msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);

			

	  } catch(Exception ex){
		  StringBuilder sbError = new StringBuilder();
		  sbError.append("<vcrr>");
		  sbError.append("<res>319</res>");
		  sbError.append("<desc>INGateway Receive BOS BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
		  sbError.append("</vcrr>");
		  SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
		  msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sbError.toString());
	  }
        return msgReturn;
    }
	
	
	private String mapPurchasePPSPackageCmd(DoChangeServiceResponse doChangeServiceResponse
			,AbstractAF af,SmoiInstance smoiIns) {
		StringBuilder sb = new StringBuilder();
		try{     
			String res = String.valueOf(doChangeServiceResponse.getSResult().getResultCode());
			String desc = doChangeServiceResponse.getSResult().getErrorMsg();	        
			if (res.equals("1000000")) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);         

				res = "000";
				desc = "Purchasing package succeeded.";

				sb.append("<vcrr>");
				sb.append("<res>").append(res).append("</res>");
				sb.append("<desc>").append(desc).append("</desc>");
				sb.append("<newbalance></newbalance>");
				sb.append("<newactivestop></newactivestop>");
				sb.append("<pkgminute></pkgminute>");
				sb.append("<pkgsm></pkgsm>");
				sb.append("<pkgprmmoney></pkgprmmoney>");
				sb.append("<pkgcalltimes></pkgcalltimes>");
				sb.append("<pkgrbtsong></pkgrbtsong>");
				sb.append("<pkgrbtmf></pkgrbtmf>");
				sb.append("<pkgidexp></pkgidexp>");
				sb.append("<pkgebd></pkgebd>");
				sb.append("</vcrr>");

			} else { 
				sb.append("<vcrr>");
				sb.append("<res>").append(res).append("</res>");
				sb.append("<desc>").append(desc).append("</desc>");
				sb.append("</vcrr>");

				//Raise Stat &  Alarm if res != 1000000
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);         
			}
		}catch(Exception ex){
			sb.append("<vcrr>");
			sb.append("<res>319</res>");
	  		sb.append("<desc>INGateway Receive BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
			sb.append("</vcrr>");
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
		}
		return sb.toString();
	}

	private String mapModiPPSCreditLimitCmd(DoSetNegativeBalanceResponse doSetNegativeBalanceResponse
			,AbstractAF af,SmoiInstance smoiIns) {
		StringBuilder sb = new StringBuilder();
		try{
			String res = String.valueOf(doSetNegativeBalanceResponse.getSResult().getResultCode());
			String desc = doSetNegativeBalanceResponse.getSResult().getErrorMsg();
			if (res.equals("1000000")) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);         

				res = "000";
				desc = "Operation succeeded.";
			}else{
				//Raise Stat &  Alarm if res != 1000000
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);         
			}
			sb.append("<vcrr>");
			sb.append("<res>").append(res).append("</res>");
			sb.append("<desc>").append(desc).append("</desc>");
			sb.append("</vcrr>");
		
	    }catch(Exception ex){
	    	
	    	sb.append("<vcrr>");
	    	sb.append("<res>319</res>");
	    	sb.append("<desc>INGateway Receive BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
	    	sb.append("</vcrr>");
	    	SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
	    }
		
		return sb.toString();
	}

	private String mapActivatePPSSingSubCmd(EquinoxRawData r, AbstractAF af,SmoiInstance smoiIns) {
//		String msg;
		StringBuilder sb = new StringBuilder();
		//for activatePPSSingSub
		try{
			FirstActivationCRMService firstActivationCRMService = new FirstActivationCRMService();
			firstActivationCRMService.CreateObj(r.getRawDataMessage());
			DoFirstActivationCRMResponse doFirstActivationCRMResponse = firstActivationCRMService.getDoFirstActivationCRMResponse();
			String res = String.valueOf(doFirstActivationCRMResponse.getSResult().getResultCode());
			String desc = doFirstActivationCRMResponse.getSResult().getErrorMsg();
			if (res.equals("1000000")) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);         

				res = "000";
				desc = "Activating subscriber successfully.";
			}else{
				//Raise Stat &  Alarm if res != 1000000
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);

			}
			sb.append("<vcrr>");
			sb.append("<res>").append(res).append("</res>");
			sb.append("<desc>").append(desc).append("</desc>");
			sb.append("</vcrr>");
//			msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
		}catch(Exception ex){
	  		sb.append("<vcrr>");
	  		sb.append("<res>319</res>");
	  		sb.append("<desc>INGateway Receive BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
	  		sb.append("</vcrr>");
	  		SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
  	    }
		
		return sb.toString();
//		return msg;
	}

	private String mapDispScrScreenNoCmd(EquinoxRawData r, AbstractAF af, SmoiInstance smoiIns) {
		String xmlMessage;
		StringBuilder sb = new StringBuilder();
		//for dispScrScreenNo
		try{
			QueryCallScreen queryCallScreen = new QueryCallScreen();
			queryCallScreen.CreateObj(r.getRawDataMessage());
			DoQueryCallScreenResponse doQueryCallScreenResponse = queryCallScreen.getDoQueryCallScreenResponse();
			String res = String.valueOf(doQueryCallScreenResponse.getSResult().getResultCode());
			String desc = doQueryCallScreenResponse.getSResult().getErrorMsg();
			if (res.equals("1000000")) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);         

				res = "000";
				desc = "Operation Suceeded.";

				String screentype = String.valueOf(doQueryCallScreenResponse.getCallScreenNoType());
				String routingmethod = null;
	            String routingmethodTmp = String.valueOf(doQueryCallScreenResponse.getRoutingMethod());

	            if (routingmethodTmp != null && !routingmethodTmp.trim().equals("")){
	                routingmethod = routingmethodTmp;
	            }

				String icstype = "";
				String totalnum = "0";

				CallScreenNoInfoList callscreen_no_infolist = doQueryCallScreenResponse.getCallScreenNoInfoList();

				String totalnumBlack = "0";
				String totalnumWhite = "0";
				String msisdnArrayBlack = "";
				String msisdnArrayWhite = "";

	            try{
	                if (routingmethod != null && (routingmethod.equals("1"))) {
	                    screentype = "1";
	                } else if(routingmethod != null ){
	                    screentype = "2";
	                } else {
	                    screentype = "1";
	                }
	            }catch(Exception ex){
	                Log.d("call Screenno type not found");
	            }

				if(callscreen_no_infolist != null){
					ArrayList<SCallScreenNo> callscreen_no_infolistitem = (ArrayList<SCallScreenNo>) callscreen_no_infolist.getCallScreenNoInfoListItem();
					ArrayList<String> blackList = new ArrayList<String>();
					ArrayList<String> whiteList = new ArrayList<String>();
					for (Iterator<SCallScreenNo> it = callscreen_no_infolistitem.iterator(); it.hasNext();) {
						SCallScreenNo sCallScreenNo = it.next();
						if (sCallScreenNo.getListType() != null && sCallScreenNo.getCallscreenNo() != null) {
							if ((short)sCallScreenNo.getListType() == 0) {
								blackList.add(sCallScreenNo.getCallscreenNo());
							} else if((short)sCallScreenNo.getListType() == 1) {
								whiteList.add(sCallScreenNo.getCallscreenNo());
							}
						}
					}

					totalnumBlack = String.valueOf(blackList.size());	
					for (int i = 0; i < blackList.size(); i++) {
	                    //msisdnArrayBlack += "\t<msisdn>" + blackList.get(i) + "</msisdn>";
	                    msisdnArrayBlack += "\t<msisdn>" + Msisdn.checkLengthAndConvert(blackList.get(i)) + "</msisdn>";
					}

					totalnumWhite = String.valueOf(whiteList.size());
					for (int i = 0; i < whiteList.size(); i++) {
						//msisdnArrayWhite += "\t<msisdn>" + whiteList.get(i) + "</msisdn>";
	                    msisdnArrayWhite += "\t<msisdn>" + Msisdn.checkLengthAndConvert(whiteList.get(i)) + "</msisdn>";
					}
				}

				xmlMessage = "<vcrr>"
					+ "<res>" + res + "</res>"
					+ "<desc>" + desc + "</desc>"
					+ "<screentype>" + screentype + "</screentype>"
					+ "<icstype>" + icstype + "</icstype>"
					+ "<totalnum>" + totalnum + "</totalnum>"
					+ "<msisdnArray></msisdnArray>"
					+ "<totalnumBlack>" + totalnumBlack + "</totalnumBlack>"
					+ "<msisdnArrayBlack>"+ msisdnArrayBlack+ "</msisdnArrayBlack>"
					+ "<totalnumWhite>" + totalnumWhite + "</totalnumWhite>"
					+ "<msisdnArrayWhite>"+ msisdnArrayWhite+ "</msisdnArrayWhite>"
					+ "</vcrr>";
			} else {
				if (res.equals("2021006")) {
					res = "1014";
					desc = "The Call Screening function is not enabled on the subscriber level.";
				}
				xmlMessage = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);  
			}
		}catch(Exception ex){
	  		sb.append("<vcrr>");
	  		sb.append("<res>319</res>");
	  		sb.append("<desc>INGateway Receive BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
	  		sb.append("</vcrr>");
	  		SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
	  		xmlMessage = sb.toString();
  	    }
		
		return xmlMessage;
	}


	private String mapAddScrScreenNoCmd(DoCallScreenNoResponse doCallScreenNoResponse
			, AbstractAF af,SmoiInstance smoiIns) {
		StringBuilder sb = new StringBuilder();
		try{
			String res = String.valueOf(doCallScreenNoResponse.getSResult().getResultCode());
			String desc = doCallScreenNoResponse.getSResult().getErrorMsg();
			String dat = smoiIns.getHttpPostValue("dat");
			String number_of_succnum = "0";
			String[] number_of_b = dat.split("\\%26");

			if (res.equals("1000000")) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);  
				res = "000";
				desc = "The request is successfully executed.";
				number_of_succnum = String.valueOf(number_of_b.length);
			} else {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);

			}

			sb.append("<vcrr>");
			sb.append("<res>").append(res).append("</res>");
			sb.append("<desc>").append(desc).append("</desc>");
			sb.append("<succnum>").append(number_of_succnum).append("</succnum>");
			sb.append("</vcrr>");
		}catch(Exception ex){
	  		sb.append("<vcrr>");
	  		sb.append("<res>319</res>");
	  		sb.append("<desc>INGateway Receive BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
	  		sb.append("</vcrr>");
	  		SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
  	    }
		
		return sb.toString();
	}

	private String mapDeleScrScreenNoCmd(DoCallScreenNoResponse doCallScreenNoResponse
			,AbstractAF af,SmoiInstance smoiIns) {
		StringBuilder sb = new StringBuilder();
		try{
			String res = String.valueOf(doCallScreenNoResponse.getSResult().getResultCode());
			String desc = doCallScreenNoResponse.getSResult().getErrorMsg();
			String dat = smoiIns.getHttpPostValue("dat");
			String number_of_succnum = "0";
			String[] number_of_b = dat.split("\\%26");

			if (res.equals("1000000")) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);  
				res = "000";
				desc = "The request is successfully executed.";
				number_of_succnum = String.valueOf(number_of_b.length);
			} else {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns); 
			}
			sb.append("<vcrr>");
			sb.append("<res>").append(res).append("</res>");
			sb.append("<desc>").append(desc).append("</desc>");
			sb.append("<succnum>").append(number_of_succnum).append("</succnum>");
			sb.append("</vcrr>");
		}catch(Exception ex){
	  		sb.append("<vcrr>");
	  		sb.append("<res>319</res>");
	  		sb.append("<desc>INGateway Receive BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
	  		sb.append("</vcrr>");
	  		SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
  	    }
		
		return sb.toString();
	}

	private String mapModiScrWhiteListCmd(DoCallScreenNoResponse doCallScreenNoResponse
			, AbstractAF af,SmoiInstance smoiIns) {
		StringBuilder sb = new StringBuilder();
		try{
			String res = String.valueOf(doCallScreenNoResponse.getSResult().getResultCode());
			String desc = doCallScreenNoResponse.getSResult().getErrorMsg();
			if (res.equals("1000000")) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);  
				res = "000";
				desc = "Operation succeeded.";
			} else {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);

			}
			sb.append("<vcrr>");
			sb.append("<res>").append(res).append("</res>");
			sb.append("<desc>").append(desc).append("</desc>");
			//sb.append("<succnum>").append("1").append("</succnum>");
			sb.append("</vcrr>");
		}catch(Exception ex){
	  		sb.append("<vcrr>");
	  		sb.append("<res>319</res>");
	  		sb.append("<desc>INGateway Receive BOS BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
	  		sb.append("</vcrr>");
	  		SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
  	    }
		
		return sb.toString();
	}



	   private String mapSetPPSRewardCmd(DoAdjustBalanceResponse doAdjustBalanceResponse, AbstractAF af, SmoiInstance smoiIns) {
	        StringBuilder sb = new StringBuilder();
	        try{
	            String res = String.valueOf(doAdjustBalanceResponse.getSResult().getResultCode());
	            String desc = doAdjustBalanceResponse.getSResult().getErrorMsg();
	            if (res.equals("1000000")) {
	                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);

	                res = "000";
	                desc = "Operation succeeded.";

	                /*String newbalance = "";
	                String newactivestop = "";
	                String pkgminute = "";
	                String pkgsm = "";
	                String pkgprmmoney = "";

	                String balance = smoiIns.getHttpPostValue("balance");
	                String validity = smoiIns.getHttpPostValue("validity");
	                String prmmoney = smoiIns.getHttpPostValue("prmMoney");
	                String prmsm = smoiIns.getHttpPostValue("prmSm");
	                String prmminute = smoiIns.getHttpPostValue("prmMinute");
	                String expire = smoiIns.getHttpPostValue("expire");

	                if(null!=doAdjustBalanceResponse.getSBalanceAdjustResponse())
	                {
	                    if(isNotEmpty(balance)) newbalance = doAdjustBalanceResponse.getSBalanceAdjustResponse().getAmountAfter().toString();
	                    if(isNotEmpty(expire)){
	                        newactivestop = doAdjustBalanceResponse.getSBalanceAdjustResponse().getExpiredateAfter().toString();
	                        newactivestop = newactivestop.replaceAll("-","").substring(0,8);
	                    }
	                }
	                else if(null!=doAdjustBalanceResponse.getFreeResourceListAdjustResponse()
	                        && null!=doAdjustBalanceResponse.getFreeResourceListAdjustResponse().getFreeResourceListItem()
	                        && doAdjustBalanceResponse.getFreeResourceListAdjustResponse().getFreeResourceListItem().size() >0
	                ){
	                    String resoureAfter = doAdjustBalanceResponse.getFreeResourceListAdjustResponse().getFreeResourceListItem().get(0).getResourceAmount();
	                    if(isNotEmpty(prmsm)) pkgsm = resoureAfter;
	                    if(isNotEmpty(prmmoney)) pkgprmmoney = resoureAfter;
	                    if(isNotEmpty(prmminute)) pkgminute = resoureAfter;
	                    if(isNotEmpty(expire)){
	                        newactivestop = doAdjustBalanceResponse.getFreeResourceListAdjustResponse().getFreeResourceListItem().get(0).getExpireDate().toString();
	                        newactivestop = newactivestop.replaceAll("-","").substring(0,8);
	                    }
	                }*/

	                sb.append("<vcrr>");
	                sb.append("<res>").append(res).append("</res>");
	                sb.append("<desc>").append(desc).append("</desc>");
	                /*sb.append("<newbalance>").append(newbalance).append("</newbalance>");
	                sb.append("<newactivestop>").append(newactivestop).append("</newactivestop>");
	                sb.append("<pkgminute>").append(pkgminute).append("</pkgminute>");
	                sb.append("<pkgsm>").append(pkgsm).append("</pkgsm>");
	                sb.append("<pkgprmmoney>").append(pkgprmmoney).append("</pkgprmmoney>");
	                sb.append("<pkgcalltimes></pkgcalltimes>");
	                sb.append("<pkgrbtsong></pkgrbtsong>");
	                sb.append("<pkgrbtmf></pkgrbtmf>");
	                sb.append("<pkgidexp></pkgidexp>");
	                sb.append("<pkgebd></pkgebd>");*/
	                sb.append("</vcrr>");

	            } else {
	                sb.append("<vcrr>");
	                sb.append("<res>").append(res).append("</res>");
	                sb.append("<desc>").append(desc).append("</desc>");
	                sb.append("</vcrr>");

	                //Raise Stat &  Alarm if res != 1000000
	                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);

				}
	        }catch(Exception ex){
		  		sb.append("<vcrr>");
		  		sb.append("<res>319</res>");
		  		sb.append("<desc>INGateway Receive BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
		  		sb.append("</vcrr>");
		  		SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
	  	    }
	        
//	        catch(Exception ex){
//	            sb.append("<vcrr>");
//	            sb.append("<res>Exception</res>");
//	            sb.append("<desc>Exception</desc>");
//	            sb.append("</vcrr>");
//	        }
	        return sb.toString();
	    }
    
    private String mapModiPPSCallNotifyFlagCmd(ManageSCPProfileRes doManageSCPProfileResponse, AbstractAF af, SmoiInstance smoiIns) {
        StringBuilder sb = new StringBuilder();
        try{
            String res = String.valueOf(doManageSCPProfileResponse.getResult_code());
            String desc = doManageSCPProfileResponse.getError_msg();
            String nbr = doManageSCPProfileResponse.getSo_nbr();
            String date = doManageSCPProfileResponse.getFinish_date();
            if (nbr == null || nbr.equals("") || date == null || date.equals("") 
            		|| doManageSCPProfileResponse.getResult_code() == null || doManageSCPProfileResponse.getResult_code().equals("")) {
            	
            	sb.append("<vcrr>");
                sb.append("<res>319</res>");
                sb.append("<desc>INGateway Receive BSSBroker Bad "+ smoiIns.getMapCmd() +" Response</desc>");
                sb.append("</vcrr>");
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
//                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
                
            } else if (res.equals("1000000")) {
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response, smoiIns);

                res = "000";
                desc = "Modifying Call Notification flag succeeded";
                
                sb.append("<vcrr>");
                sb.append("<res>").append(res).append("</res>");
                sb.append("<desc>").append(desc).append("</desc>");
                sb.append("</vcrr>");
//                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
            } else {
            	
                sb.append("<vcrr>");
                sb.append("<res>"+res+"</res>");
                sb.append("<desc>"+desc+"</desc>");
                sb.append("</vcrr>");

                //Raise Stat &  Alarm if res != 1000000
                SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_$s_Response_Error, smoiIns);
//                SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
            }
        }catch(Exception ex){
            sb.append("<vcrr>");
            sb.append("<res>319</res>");
            sb.append("<desc>INGateway Receive BSSBroker Bad DoManageSCPProfile Response</desc>");
            sb.append("</vcrr>");
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BSSBroker_Bad_$s_Response, smoiIns);
//            SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
        }
        return sb.toString();
    }

	/**
	 *@see //phase 2

    private String mapDispPPSFntelNoCmd(EquinoxRawData r,AbstractAF af,SmoiInstance smoiIns) {
        String xmlMessage;
        //for dispPPSFntelNo
        QueryFN queryFN = new QueryFN();
        queryFN.CreateObj(r.getRawDataMessage());
        DoQueryFNResponse doQueryFNResponse = queryFN.getDoQueryFNResponse();
        String res = String.valueOf(doQueryFNResponse.getSResult().getResultCode());
        String desc = doQueryFNResponse.getSResult().getErrorMsg();
        if (res.equals("1000000")) {
            SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_BOS_BSSBroker_$s_Response_Success);         

            res = "000";
            desc = "Purchasing package succeeded.";

            ProductFNList productFNList = doQueryFNResponse.getProductFNList();
            String FNListMsg =""; int count =0;
            if(productFNList!=null){
	            List<ProductFN> FNList = productFNList.getProductFNListItem();
	            if(FNList!=null&&FNList.size()>0){
		            List<FN1> FN = FNList.get(0).getFNList().getFNListItem();
		            count = FN.size();
		            FNListMsg = "";
		            for (int i = 0; i < FN.size(); i++) {
		            	FNListMsg += "<fn" + (i + 1) + ">" + FN.get(i).getFnId() + "</fn" + (i + 1) + ">";
		            	FNListMsg += "<effectiveDate" + (i + 1) + ">" + FN.get(i).getValidDate() + "</effectiveDate" + (i + 1) + ">";
		            	FNListMsg += "<expireDate" + (i + 1) + ">" + FN.get(i).getExpireDate() + "</expireDate" + (i + 1) + ">";
		            	FNListMsg += "<GroupType" + (i + 1) + "></GroupType" + (i + 1) + ">";
		            }
	            }
            }
            xmlMessage = "<vcrr>"
                    + "<res>" + res + "</res>"
                    + "<desc>" + desc + "</desc>"
                    + "<total>3</total>"
                    + "<count>" + count + "</count>"
                    + FNListMsg
                    + "</vcrr>";
        } else {
            xmlMessage = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
        }
        return xmlMessage;
    }

    private String mapModiScrScreenTypeCmd(DoCallScreenNoResponse doCallScreenNoResponse) {
        StringBuilder sb = new StringBuilder();
        String res = String.valueOf(doCallScreenNoResponse.getSResult().getResultCode());
        String desc = doCallScreenNoResponse.getSResult().getErrorMsg();
        if (res.equals("1000000")) {
            res = "000";
            desc = "Operation succeeded.";
        }
        sb.append("<vcrr>");
        sb.append("<res>").append(res).append("</res>");
        sb.append("<desc>").append(desc).append("</desc>");
        sb.append("<succnum>").append("1").append("</succnum>");
        sb.append("</vcrr>");
        return sb.toString();
    }

    private String mapActPPSRBTCmd(DoChangeServiceResponse doChangeServiceResponse) {
        StringBuilder sb = new StringBuilder();
        String res = String.valueOf(doChangeServiceResponse.getSResult().getResultCode());
        String desc = doChangeServiceResponse.getSResult().getErrorMsg();
        if (res.equals("1000000")) {
            res = "000";
            desc = "Operation succeeded.";
        }
        sb.append("<vcrr>");
        sb.append("<res>").append(res).append("</res>");
        sb.append("<desc>").append(desc).append("</desc>");
        sb.append("</vcrr>");
        return sb.toString();
    }

    private String mapDelePPSPkgresCmd(DoChangeServiceResponse doChangeServiceResponse) {
        StringBuilder sb = new StringBuilder();
        String res = String.valueOf(doChangeServiceResponse.getSResult().getResultCode());
        String desc = doChangeServiceResponse.getSResult().getErrorMsg();
        if (res.equals("1000000")) {
            res = "000";
            desc = "delete package succeeded.";
        }
        sb.append("<vcrr>");
        sb.append("<res>").append(res).append("</res>");
        sb.append("<desc>").append(desc).append("</desc>");
        sb.append("</vcrr>");
        return sb.toString();
    }

    private String mapDelePPSPackageIDCmd(DoChangeServiceResponse doChangeServiceResponse) {
        StringBuilder sb = new StringBuilder();
        String res = String.valueOf(doChangeServiceResponse.getSResult().getResultCode());
        String desc = doChangeServiceResponse.getSResult().getErrorMsg();
        if (res.equals("1000000")) {
            res = "000";
            desc = "delete package succeeded.";
        }
        sb.append("<vcrr>");
        sb.append("<res>").append(res).append("</res>");
        sb.append("<desc>").append(desc).append("</desc>");
        sb.append("</vcrr>");
        return sb.toString();
    }

    private String mapSetPPSRewardCmd(DoChangeServiceResponse doChangeServiceResponse) {
        StringBuilder sb = new StringBuilder();
        String res = String.valueOf(doChangeServiceResponse.getSResult().getResultCode());
        String desc = doChangeServiceResponse.getSResult().getErrorMsg();
        if (res.equals("1000000")) {
            res = "000";
            desc = "Purchasing package succeeded.";

            sb.append("<vcrr>");
            sb.append("<res>").append(res).append("</res>");
            sb.append("<desc>").append(desc).append("</desc>");
            sb.append("<newbalance></newbalance>");
            sb.append("<newactivestop></newactivestop>");
            sb.append("<pkgminute></pkgminute>");
            sb.append("<pkgsm></pkgsm>");
            sb.append("<pkgprmmoney></pkgprmmoney>");
            sb.append("<pkgcalltimes></pkgcalltimes>");
            sb.append("<pkgrbtsong></pkgrbtsong>");
            sb.append("<pkgrbtmf></pkgrbtmf>");
            sb.append("<pkgidexp></pkgidexp>");
            sb.append("<pkgebd></pkgebd>");
            sb.append("</vcrr>");
        } else {
            sb.append("<vcrr>");
            sb.append("<res>").append(res).append("</res>");
            sb.append("<desc>").append(desc).append("</desc>");
            sb.append("</vcrr>");
        }
        return sb.toString();
    }



    private String mapModiPPSLanguageCmd(DoModifySubscriberBasicInfoResponse doModifySubsciberBasicInfoResponse) {
        StringBuilder sb = new StringBuilder();
        String res = String.valueOf(doModifySubsciberBasicInfoResponse.getSResult().getResultCode());
        //String desc = doModifySubsciberBasicInfoResponse.getSResult().getErrorMsg();
        if (res.equals("1000000")) {
            res = "000";
        }
        sb.append("<vcrr>");
        sb.append("<res>").append(res).append("</res>");
        sb.append("</vcrr>");
        return sb.toString();
    }

    private String mapModiPPSSMSLanguageCmd(DoModifySubscriberBasicInfoResponse doModifySubsciberBasicInfoResponse) {
        StringBuilder sb = new StringBuilder();
        String res = String.valueOf(doModifySubsciberBasicInfoResponse.getSResult().getResultCode());
        //String desc = doModifySubsciberBasicInfoResponse.getSResult().getErrorMsg();
        if (res.equals("1000000")) {
            res = "000";
        }
        sb.append("<vcrr>");
        sb.append("<res>").append(res).append("</res>");
        sb.append("</vcrr>");
        return sb.toString();
    }
	 */

	private String mapEquinoxRejectCmd(SmoiInstance smoiIns) {
		String msg;
		String res = EResponseCode.BSSBroker_$s_Reject.getCode();
		String desc = null;
//		if(smoiIns.isBssbrokerFlag()){
//			desc = EResponseCode.BSSBrokerDoQuerySCPProfile_Reject.getDesc();
//		} else if(smoiIns.isFlagModiPPSCallNotify() == true){
//			desc = EResponseCode.BSSBrokerDoManageSCPProfile_Reject.getDesc();
//		} else {
//			desc = EResponseCode.BSSBroker_Reject.getDesc();
		    desc = ResponseCode.responsecode(EResponseCode.BSSBroker_$s_Reject.getDesc(), smoiIns);
//		}
		msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
		return msg;
	}

	private String mapEquinoxErrorCmd(SmoiInstance smoiIns) {
		String msg;
		String res = EResponseCode.BSSBroker_$s_Error.getCode();
//		String desc = null;
		String desc = ResponseCode.responsecode(EResponseCode.BSSBroker_$s_Error.getDesc(), smoiIns);
//		if(smoiIns.isBssbrokerFlag()){
//			desc = EResponseCode.BSSBrokerDoQuerySCPProfile_Error.getDesc();
//		} else if(smoiIns.isFlagModiPPSCallNotify() == true){
//			desc = EResponseCode.BSSBrokerDoManageSCPProfile_Error.getDesc();
//		} else {
//			desc = EResponseCode.BSSBroker_Error.getDesc();
//		}
		msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
		return msg;
	}

	private String mapEquinoxAbortCmd(SmoiInstance smoiIns) {
		String msg;
		String res = EResponseCode.BSSBroker_$s_Abort.getCode();
//		String desc = null;
		String desc = ResponseCode.responsecode(EResponseCode.BSSBroker_$s_Abort.getDesc(), smoiIns);
//		if(smoiIns.isBssbrokerFlag()){
//			desc = EResponseCode.BSSBrokerDoQuerySCPProfile_Abort.getDesc();
//		} else if(smoiIns.isFlagModiPPSCallNotify() == true){
//			desc = EResponseCode.BSSBrokerDoManageSCPProfile_Abort.getDesc();
//		} else {
//			desc = EResponseCode.BSSBroker_Abort.getDesc();
//		}
		
		msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
		return msg;
	}

	private String mapEquinoxTimeoutCmd(SmoiInstance smoiIns) {
		String msg;
		String res = EResponseCode.BSSBroker_$s_Timeout.getCode();
//		String desc = null;
		String desc = ResponseCode.responsecode(EResponseCode.BSSBroker_$s_Timeout.getDesc(), smoiIns);
//		if(smoiIns.isBssbrokerFlag()){
//			desc = EResponseCode.BSSBrokerDoQuerySCPProfile_Timeout.getDesc();
//		} else if(smoiIns.isFlagModiPPSCallNotify() == true){
//			desc = EResponseCode.BSSBrokerDoManageSCPProfile_Timeout.getDesc();
//		} else {
//			desc = EResponseCode.BSSBbroker_Timeout.getDesc();
//		}
		
		msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";
		return msg;
	}

    private boolean isNotEmpty(String input){
        return null!=input && !"".equals(input.trim()) ? true:false;
    }
}
