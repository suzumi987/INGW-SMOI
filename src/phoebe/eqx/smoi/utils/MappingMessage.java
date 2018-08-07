/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.utils;

import ec02.af.abstracts.AbstractAF;
import ec02.utils.Log;
import phoebe.eqx.model.ldap.ds2a.InquirySubscriberResponse;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.EQLRequestInstance;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.AdjustType;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.message.soapmsg.*;
import phoebe.eqx.smoi.utils.Msisdn.EMsisdnFormat;
import phoebe.eqx.smoi.wsdl.CommonComponents.*;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author pavarisa
 */
public class MappingMessage {

	private AbstractAF af;
	private String EState;
	private HashMap<String, ArrayList<String>> smoi_conf;
//	private OctetString octet = new OctetString();

	public MappingMessage(AbstractAF _af) {
		af = _af;
		smoi_conf = af.getUtils().getHmWarmConfig();
	}

	public String CreateMessage(MyAppData myAppData) {
		String msg = "";
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String page = smoiIns.getPage();
		String scpid = smoiIns.getScp();
		boolean isBosProfile = SMOIUtils.isBosProfile(this.af, scpid);
		boolean isINSProfile = SMOIUtils.isINSProfile(this.af, scpid);
		boolean isAVATARProfile = SMOIUtils.isAVATARProfile(this.af, scpid);

		Log.d("Page=" + page + " ,IsBosProfile=" + isBosProfile);

		/**
		 * @see phase 2 but different
		 *
		 */
		if (page.equals(ECommand.chgtrigChrgAcnt.getCommand())) {
			String dat = smoiIns.getHttpPostValue("dat");
			String ms = smoiIns.getMsisdn();
			String sgw = smoiIns.getSgw();
			String ssid = smoiIns.getSsid();
			String scp = smoiIns.getScp();
			if (dat.equals("3")) {
				String pin = smoiIns.getHttpPostValue("pin");
				msg = "page=rechargeBalance&ms=" + ms + "&pin=" + pin + "&dat=" + dat + "&ssid=" + ssid + "&sgw=" + sgw
						+ "&scp=" + scp;
				smoiIns.setMapCmd("rechargeBalance");
				this.setEState(AFState.W_PPGW);
			} else {
				// @see phase 2 fix to smoi
				/*
				 * if (isBosProfile) { msg = mapChgtrigChrgAcntCmd(myAppData); }
				 * else {
				 */
				msg = forwardToSMOI(myAppData);
				// }
			}
		}else if (isAVATARProfile) {
			if (page.equals(ECommand.dispPPSInfo.getCommand())) {
				
				msg = SendMessage.QueryE01Destination(af, myAppData);
				this.setEState(AFState.W_E01_DESTINATION);
				
			} else if (page.equals(ECommand.modiPPSValidity.getCommand())) {// Chatl
																			// 20/09/2017
				msg = mapModiPPSValidity(myAppData);
				
			} else if (page.equals(ECommand.modiPPSMultiAttr.getCommand())) {// Chatl
																				// 20/09/2017
				msg = mapModiPPSMultiAttrAVATAR(myAppData);
				
			} else if (page.equals(ECommand.activatePPSSingSub.getCommand())) {// Max
																			// 16/03/2018
				msg = mapActivatePPSSingSubAVATAR(myAppData);
				
			} else if (page.equals(ECommand.setPPSReward.getCommand())) {// AM 
																		// 24/04/2018
				msg = mapSetPPSRewardAVATAR(myAppData);

			}  else if (page.equals(ECommand.modiPPSCreditLimit.getCommand())) {// AM 
																		  // 24/04/2018
				msg = mapModiPPSCreditLimitAVATAR(myAppData);

			}else {
				
				msg = forwardToSMOI(myAppData);
				
			}
		}else if (isBosProfile) {
			/**
			 * TODO
			 *
			 * @see phase 1
			 */
			//// BOS DCC
			if (page.equals(ECommand.dispPPSInfo.getCommand()) && smoiIns.isBssbrokerFlag() == true) {
				msg = mapQuerySCPProfile(myAppData);
			} else if (page.equals(ECommand.dispPPSInfo.getCommand())) {// Send to BOS DCC
				msg = SendMessage.QueryE01Destination(af, myAppData);
				this.setEState(AFState.W_E01_DESTINATION);
			} else if (page.equals(ECommand.dispPPSPkgrew.getCommand())) {// Send to BOS DCC
				msg = SendMessage.QueryE01Destination(af, myAppData);
				this.setEState(AFState.W_E01_DESTINATION);
			} else if (page.equals(ECommand.modiPPSValidity.getCommand())) {// Send to BOS DCC
				msg = SendMessage.QueryE01Destination(af, myAppData);
				this.setEState(AFState.W_E01_DESTINATION);
			} else if (page.equals(ECommand.modiPPSMultiAttr.getCommand())) {
				msg = mapModiPPSMultiAttr(smoiIns, myAppData);
			} else if (page.equals(ECommand.purchasePPSPackage.getCommand())) {
				msg = mapPurchasePPSPackageCmd(myAppData);
			} else if (page.equals(ECommand.modiPPSCreditLimit.getCommand())) {
				msg = mapModiPPSCreditLimitCmd(myAppData);
			} else if (page.equals(ECommand.activatePPSSingSub.getCommand())) {
				msg = mapActivatePPSSingSubCmd(myAppData);
			} else if (page.equals(ECommand.addScrScreenNo.getCommand())) {
				msg = mapAddScrScreenNoCmd(myAppData);
			} else if (page.equals(ECommand.deleScrScreenNo.getCommand())) {
				msg = mapDeleScrScreenNoCmd(myAppData);
			} else if (page.equals(ECommand.modiScrWhiteList.getCommand())) {
				msg = mapModiScrWhiteListCmd(myAppData);
			} else if (page.equals(ECommand.dispScrScreenNo.getCommand())) {
				msg = mapDispScrScreenNoCmd(myAppData);
			} // new 26/08/2015
			else if (page.equals(ECommand.setPPSReward.getCommand())) {
				msg = mapSetPPSReward(smoiIns, myAppData);
			} else if (page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())) {
				msg = mapModiPPSCallNotifyFlag(myAppData);
			}

			/**
			 * @see phase 2 * } else if
			 *      (page.equals(ECommand.modiScrScreenType.getCommand())) { msg
			 *      = mapModiScrScreenTypeCmd(myAppData); } else if
			 *      (page.equals(ECommand.actPPSRBT.getCommand())) { msg =
			 *      mapActPPSRBTCmd(myAppData); } else if
			 *      (page.equals(ECommand.delePPSPkgres.getCommand())) { msg =
			 *      mapDelePPSPkgresCmd(myAppData); } else if
			 *      (page.equals(ECommand.delePPSPackageID.getCommand())) { msg
			 *      = mapDelePPSPackageIdCmd(myAppData); } else if
			 *      (page.equals(ECommand.setPPSReward.getCommand())) { msg =
			 *      mapSetPPSRewardCmd(myAppData); } else if
			 *      (page.equals(ECommand.dispPPSFntelNo.getCommand())) { msg =
			 *      mapDispPPSFntelNoCmd(myAppData); } else if
			 *      (page.equals(ECommand.modiPPSLanguage.getCommand())) { msg =
			 *      mapModiPPSLanguageCmd(myAppData); } else if
			 *      (page.equals(ECommand.modiPPSSMSLanguage.getCommand())) {
			 *      msg = mapModiPPSSMSLanguageCmd(myAppData);
			 */
			else {
				// phase 2 other command send to SMOI
				msg = forwardToSMOI(myAppData);
			}
		} else if (isINSProfile) {
			// if (page.equals(ECommand.activatePPSSingSub.getCommand())
			// || page.equals(ECommand.dispPPSInfo.getCommand())
			// || page.equals(ECommand.modiPPSValidity.getCommand())
			// || page.equals(ECommand.modiPPSMultiAttr.getCommand())
			// || page.equals(ECommand.addScrScreenNo.getCommand())
			// || page.equals(ECommand.deleScrScreenNo.getCommand())
			// || page.equals(ECommand.modiScrScreenType.getCommand())
			// || page.equals(ECommand.modiScrWhiteList.getCommand())
			// || page.equals(ECommand.dispScrScreenNo.getCommand())
			// || page.equals(ECommand.modiPPSLanguage.getCommand())
			// || page.equals(ECommand.modiPPSSMSLanguage.getCommand())
			// || page.equals(ECommand.modiPPSCreditLimit.getCommand())) {
			// msg = forwardToINS(myAppData);
			// } else {
			// msg = forwardToSMOI(myAppData);
			// }
			msg = forwardToINS(myAppData);

		}  else {
			msg = forwardToSMOI(myAppData);
		}

		return msg;
	}

	

	public String mapSetPPSReward(SmoiInstance smoiIns, MyAppData myAppData) {
		String msg = "";
		String balance = smoiIns.getHttpPostValue("balance");
		String validity = smoiIns.getHttpPostValue("validity");
		String prmmoney = smoiIns.getHttpPostValue("prmMoney");
		String prmsm = smoiIns.getHttpPostValue("prmSM");
		String prmminute = smoiIns.getHttpPostValue("prmMinute");
		String prmPoint = smoiIns.getHttpPostValue("prmPoint");
		String freecalltimes = smoiIns.getHttpPostValue("freeCallTimes");
		String rbtSong = smoiIns.getHttpPostValue("rbtSong");
		String rbtMF = smoiIns.getHttpPostValue("rbtMF");
		String smDis = smoiIns.getHttpPostValue("smDis");
		String callDis = smoiIns.getHttpPostValue("callDis");
		String rewTariff = smoiIns.getHttpPostValue("rewTariff");
		String expire = smoiIns.getHttpPostValue("expire");
		String flag = smoiIns.getHttpPostValue("flag");
		String goToBosDcc = "";
		if (null != smoi_conf.get(Conf.setPPSReward_BOS_DCC) && smoi_conf.get(Conf.setPPSReward_BOS_DCC).size() > 0) {
			goToBosDcc = smoi_conf.get(Conf.setPPSReward_BOS_DCC).get(0);
		}
		if ((isNotEmpty(balance) || isNotEmpty(validity)) && (!isNotEmpty(prmmoney) || !isNotEmpty(prmsm)
				|| !isNotEmpty(prmminute) || !isNotEmpty(prmPoint) || !isNotEmpty(freecalltimes) || !isNotEmpty(rbtSong)
				|| !isNotEmpty(rbtMF) || !isNotEmpty(smDis) || !isNotEmpty(callDis) || !isNotEmpty(rewTariff)
				|| !isNotEmpty(expire))) {
			// BOS-DCC
			if ("true".equals(goToBosDcc)) {
				msg = SendMessage.QueryE01Destination(af, myAppData);
				this.setEState(AFState.W_E01_DESTINATION);
			} // BOS-BSSBroker
			else {
				msg = mapSetPPSRewardCmd_Balance_and_Validity_BSSBroker(myAppData, balance, validity);
			}

		} else if (!isNotEmpty(balance) && (isNotEmpty(prmmoney) || isNotEmpty(prmsm) || isNotEmpty(prmminute)
				|| isNotEmpty(prmPoint) || isNotEmpty(freecalltimes) || isNotEmpty(rbtSong) || isNotEmpty(rbtMF)
				|| isNotEmpty(smDis) || isNotEmpty(callDis) || isNotEmpty(rewTariff) || isNotEmpty(expire))) {
			// BOS-DCC
			if ("true".equals(goToBosDcc)) {
				msg = SendMessage.QueryE01Destination(af, myAppData);
				this.setEState(AFState.W_E01_DESTINATION);
			} // BOS-BSSBroker
			else {
				msg = mapSetPPSRewardCmd_Free_Resource_Adjustment_BSSBroker(myAppData, prmmoney, prmsm, prmminute,
						prmPoint, freecalltimes, rbtSong, rbtMF, smDis, callDis, rewTariff, expire, flag);
			}

		} else {
			// no CCR to smoi and and response to SGSCP
			this.setEState(AFState.IDLE);
			StringBuilder sb = new StringBuilder("");
			sb.append("<vcrr>").append("<res>").append(EResponseCode.INCOMPLETE_DATA_RESPONSE.getCode())
					.append("</res>").append("<desc>").append(EResponseCode.INCOMPLETE_DATA_RESPONSE.getDesc())
					.append("</desc>").append("</vcrr>");
			msg = sb.toString();
		}
		return msg;
	}

	public String mapModiPPSMultiAttr(SmoiInstance smoiIns, MyAppData myAppData) {
		String msg = "";
		// select sub command of modiPPSMultiAttr
		String balance = smoiIns.getHttpPostValue("balance");
		String validity = smoiIns.getHttpPostValue("validity");
		String prmmoney = smoiIns.getHttpPostValue("prmmoney");
		String prmsm = smoiIns.getHttpPostValue("prmsm");
		String prmminute = smoiIns.getHttpPostValue("prmminute");
		String point = smoiIns.getHttpPostValue("point");
		String freecalltimes = smoiIns.getHttpPostValue("freecalltimes");
		String freerbtsong = smoiIns.getHttpPostValue("freerbtsong");
		String freerbtmf = smoiIns.getHttpPostValue("freerbtmf");
		String score = smoiIns.getHttpPostValue("score");
		String prmscore = smoiIns.getHttpPostValue("prmscore");
		String smusage = smoiIns.getHttpPostValue("smusage");
		if (((balance != null && !balance.trim().equals("")) || (validity != null && !validity.trim().equals("")))
				&& (prmmoney == null || prmmoney.trim().equals("")) 
				&& (prmsm == null || prmsm.trim().equals(""))
				&& (prmminute == null || prmminute.trim().equals("")) 
				&& (point == null || point.trim().equals(""))
				&& (freecalltimes == null || freecalltimes.trim().equals(""))
				&& (freerbtsong == null || freerbtsong.trim().equals(""))
				&& (freerbtmf == null || freerbtmf.trim().equals(""))
				&& (freerbtsong == null || freerbtsong.trim().equals("")) 
				&& (score == null || score.trim().equals(""))
				&& (prmscore == null || prmscore.trim().equals("")) 
				&& (smusage == null || smusage.trim().equals(""))) {
			// balance or validity is not null
			// and prmmoney, prmsm, prmminute, point, freecalltimes, freerbtsong
			// ,freerbtmf, score, prmscore, smusage parameter is null
				msg = SendMessage.QueryE01Destination(af, myAppData);
				this.setEState(AFState.W_E01_DESTINATION);
		}else if ((balance == null || balance.trim().equals("")) && ((prmmoney != null && !prmmoney.trim().equals(""))
				|| (prmsm != null && !prmsm.trim().equals("")) 
				|| (prmminute != null && !prmminute.trim().equals(""))
				|| (point != null && !point.trim().equals(""))
				|| (freecalltimes != null && !freecalltimes.trim().equals(""))
				|| (freerbtsong != null && !freerbtsong.trim().equals(""))
				|| (freerbtmf != null && !freerbtmf.trim().equals(""))
				|| (freerbtsong != null && !freerbtsong.trim().equals(""))
				|| (score != null && !score.trim().equals("")) 
				|| (prmscore != null && !prmscore.trim().equals(""))
				|| (smusage != null && !smusage.trim().equals("")))) {
			// balance is null
			// and prmmoney, prmsm, prmminute, point, freecalltimes, freerbtsong
			// , freerbtmf, score, prmscore, smusage parameter one is not null
			msg = SendMessage.QueryE01Destination(af, myAppData);
			this.setEState(AFState.W_E01_DESTINATION);
		} else {
			// no CCR to smoi and and response to SGSCP
			this.setEState(AFState.IDLE);
			StringBuilder sb = new StringBuilder("");
			sb.append("<vcrr>").append("<res>").append(EResponseCode.INCOMPLETE_DATA.getCode())
					.append("</res>").append("<desc>").append(EResponseCode.INCOMPLETE_DATA.getDesc())
					.append("</desc>").append("</vcrr>");
			msg = sb.toString();
		}

		// BOS BSS Broker
		return msg;
	}

	public void setEState(String eState) {
		EState = eState;
	}

	public String getEState() {
		return EState;
	}

	private String forwardToSMOI(MyAppData myAppData) {
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String msg = smoiIns.getRequestInfo().getRequestMessage();
		this.setEState(AFState.W_SMOI);
		return msg;
	}

	private String forwardToINS(MyAppData myAppData) {
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String msg = smoiIns.getRequestInfo().getRequestMessage();
		this.setEState(AFState.W_INS);
		return msg;
	}

	/**
	 * TODO Pharse 1
	 *
	 * @author nuiss
	 */
//	private String mapSetPPSRewardCmd_Balance_and_Validity_(MyAppData myAppData, String balance, String validity) {
//		String msg;
//		msg = BOSMessage.buildSetPPSRewardCmd_Balance_and_Validity(af, myAppData, balance, validity);
//		this.setEState(AFState.W_BOS_DCC);
//		return msg;
//	}

//	private String mapSetPPSRewardCmd_Free_Resource_Adjustment_(MyAppData myAppData, String prmmoney, String prmsm,
//			String prmminute, String prmPoint, String freecalltimes, String rbtSong, String rbtMF, String smDis,
//			String callDis, String rewTariff, String expire, String flag) {
//		String msg;
//		msg = BOSMessage.buildSetPPSRewardCmd_Free_Resource_Adjustment(af, myAppData, flag, expire, prmmoney, prmsm,
//				prmminute, freecalltimes);
//		this.setEState(AFState.W_BOS_DCC);
//		return msg;
//	}

	private String mapPurchasePPSPackageCmd(MyAppData myAppData) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		// String scp = smoiIns.getScp();
		String packageid = smoiIns.getHttpPostValue("packageid");
		String channel = smoiIns.getHttpPostValue("channel");
		String charge = smoiIns.getHttpPostValue("charge");

		DoChangeService doChangeService = new DoChangeService();
		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("1021");
		if (channel.equals("")) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		if (charge.equals("")) {
			sOper.setChargeFlag((short) 1);
		} else {
			sOper.setChargeFlag(Short.parseShort(charge));
		}
		sOper.setPhoneId(ms);
		doChangeService.setSOper(sOper);
		// doChangeService.setSiteId(smoiIns.getScp());

		ChangeService changeService = new ChangeService();
		changeService.setPhoneId(ms);

		SProductOrderOper sProductOrderOper = new SProductOrderOper();
		sProductOrderOper.setOperType((short) 1);
		sProductOrderOper.setSProductOrderList(new SProductOrderList());
		SProductOrder sProductOrder = new SProductOrder();
		sProductOrder.setProductId(Integer.parseInt(packageid));
		sProductOrder.setProductClass(Short.valueOf(packageid));
		sProductOrderOper.getSProductOrderList().getSProductOrderListItem().add(sProductOrder);

		changeService.setSProductOrderOperList(new SProductOrderOperList());
		changeService.getSProductOrderOperList().getSProductOrderOperListItem().add(sProductOrderOper);
		ChangeServiceService changeServiceService = new ChangeServiceService();
		InquirySubscriberResponse inqRes = smoiIns.getInquirySubscriberResponse();
		if (inqRes != null) {
			String rsltInfo = inqRes.getResultInfo();
			if (rsltInfo != null && !rsltInfo.trim().equals("")) {
				String resultInfo[] = rsltInfo.split(",");
				if (resultInfo != null && resultInfo.length >= 10) {
					// String brandId = resultInfo[10];
					// changeService.setBrandId(brandId);
				}
			}
		}

		doChangeService.setChangeService(changeService);
		msg = changeServiceService.buildSoapMessage(doChangeService);
		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_changeService");
		return msg;
	}

	private String mapModiPPSMultiAttrAVATAR( MyAppData myAppData) {
		String msg = "";
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		String flag = smoiIns.getFlag();
		// select sub command of modiPPSMultiAttr
		String balance = smoiIns.getHttpPostValue("balance");
		String validity = smoiIns.getHttpPostValue("validity");
		String prmmoney = smoiIns.getHttpPostValue("prmmoney");
		String prmsm = smoiIns.getHttpPostValue("prmsm");
		String prmminute = smoiIns.getHttpPostValue("prmminute");
		String point = smoiIns.getHttpPostValue("point");
		String freecalltimes = smoiIns.getHttpPostValue("freecalltimes");
		String freerbtsong = smoiIns.getHttpPostValue("freerbtsong");
		String freerbtmf = smoiIns.getHttpPostValue("freerbtmf");
		String score = smoiIns.getHttpPostValue("score");
		String prmscore = smoiIns.getHttpPostValue("prmscore");
		String smusage = smoiIns.getHttpPostValue("smusage");
		String freeresourcesID = smoiIns.getHttpPostValue("freeresourcesID");
		String merchant = smoiIns.getHttpPostValue("merchant");
		String service = smoiIns.getHttpPostValue("service");
		       
		
		if(!point.trim().equals("") || !freecalltimes.trim().equals("")  || !freerbtmf.trim().equals("")   
	 			|| !score.trim().equals("")  || !prmscore.trim().equals("")   || !smusage.trim().equals("")){
				// parameter not support.
				StringBuilder sb = new StringBuilder("");
	            sb.append("<vcrr>")
	                    .append("<res>").append(EResponseCode.INCOMPLETE_DATA.getCode()).append("</res>")
	                    .append("<desc>").append(EResponseCode.INCOMPLETE_DATA.getDesc()).append("</desc>")
	                    .append("</vcrr>");
	            this.setEState(AFState.IDLE);
	            msg = sb.toString();
		}else{
				
				boolean isFreeType = false;
				boolean isBalanceType = false;
				boolean isValidityType = false;
				boolean isFreeTypePrmmoney = false;
				boolean isFreeTypePrmsm = false;
				boolean isFreeTypePrmminute = false;
				boolean isFreeTypefreerbtsong = false;
				
				// check free parameter is not null
				if(!prmmoney.trim().equals("") || !prmsm.trim().equals("") 
					|| !prmminute.trim().equals("") || !freerbtsong.trim().equals("") ){
					
					isFreeType = true;
					
					if(prmmoney != null && !prmmoney.trim().equals("")){
						isFreeTypePrmmoney = true;
					}
					if(prmsm != null && !prmsm.trim().equals("")){
						isFreeTypePrmsm = true;
					}
					if(prmminute != null && !prmminute.trim().equals("")){
						isFreeTypePrmminute = true;
					}
					if(freerbtsong != null && !freerbtsong.trim().equals("")){
						isFreeTypefreerbtsong = true;
					}
				}

				// check balance parameter is not null
				if( !balance.trim().equals("")) {
					isBalanceType = true;
				}
				
				// check validity parameter is not null
				if(!validity.trim().equals("")){
					isValidityType = true;
				}
				
				
				if(isBalanceType && isFreeType || isFreeType && isValidityType || (!isBalanceType && !isFreeType && !isValidityType) ){
				    // no adjust type
					StringBuilder sb = new StringBuilder("");
		            sb.append("<vcrr>")
		                    .append("<res>").append(EResponseCode.INCOMPLETE_DATA.getCode()).append("</res>")
		                    .append("<desc>").append(EResponseCode.INCOMPLETE_DATA.getDesc()).append("</desc>")
		                    .append("</vcrr>");
		            this.setEState(AFState.IDLE);
		            msg = sb.toString();
				}else{
					// gen BWO Header
					EqlBsoAdjustCbs<Object> adjustCbs = new EqlBsoAdjustCbs<Object>();
		 			adjustCbs.setBwoid(SMOIUtils.createSmoiEQUULEUS(ssid));
		 			adjustCbs.setRetransmit("0");
		 			adjustCbs.setUser("INGW-SMOI");
		 					 			
					///// generate /////
					if(isBalanceType && isValidityType && !isFreeType){ 
						// adj balance + validity 
						if(flag.equals("1")){
							StringBuilder sb = new StringBuilder("");
				            sb.append("<vcrr>")
				                    .append("<res>").append(EResponseCode.INCOMPLETE_DATA.getCode()).append("</res>")
				                    .append("<desc>").append(EResponseCode.INCOMPLETE_DATA.getDesc()).append("</desc>")
				                    .append("</vcrr>");
				            this.setEState(AFState.IDLE);
				            msg = sb.toString();
						}else{
							smoiIns.setAdjustType(AdjustType.BALANCE_AND_VALIDITY); //for tag type adjustment.
//							ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		 					EqlBsoAdjustCbsBalanceAndValidity adjustCbsBalAndVal = new EqlBsoAdjustCbsBalanceAndValidity();
		 					adjustCbsBalAndVal.setBwoid(SMOIUtils.createSmoiEQUULEUS(ssid));
		 					adjustCbsBalAndVal.setRetransmit("0");
		 					adjustCbsBalAndVal.setUser("INGW-SMOI");
		 					BSONOBalanceAndValidity bsonoBalAndVal = new BSONOBalanceAndValidity();
		 					bsonoBalAndVal.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
		 					bsonoBalAndVal.setBso("ADJUST_CBS_BALANCE_VALIDITY");
		 					bsonoBalAndVal.setReqType("2");
		 					bsonoBalAndVal.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
		 					bsonoBalAndVal.setMsisdn1(ms);
		 					bsonoBalAndVal.setOperType("1");
		 					bsonoBalAndVal.setChannel(smoi_conf.get(Conf.avatar_channel).get(0));
		 					
		 					AdjustmentinfolistBalanceAndValidity adjustmentinfolistBalAndVal = new AdjustmentinfolistBalanceAndValidity();
		 					adjustmentinfolistBalAndVal.setBalanceType(smoi_conf.get(Conf.mainBalanceType).get(0));
		 					
		 					
		 					if (flag.equals("0")) {
		 						if(Integer.parseInt(balance)>=0){
		 							adjustmentinfolistBalAndVal.setAdjustmentType("1");	
		 						}else{
		 							adjustmentinfolistBalAndVal.setAdjustmentType("2");
		 						}
		 					} 
		 					
		 					adjustmentinfolistBalAndVal.setAdjustmentAmout(String.valueOf(Math.abs(Long.parseLong(balance))));

		 					bsonoBalAndVal.getAdjustmentinfolistBalanceAndValidityListItem().add(adjustmentinfolistBalAndVal);
		 					bsonoBalAndVal.setInsufficienFlag("1");
		 					
		 					if(smoi_conf.get(Conf.avatar_ajd_channel_id) != null 
		 							&& !smoi_conf.get(Conf.avatar_ajd_channel_id).get(0).trim().equals("")){
		 						AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
	 		 					adjPropertylistBalance.setCode("ADJUST_CHANNEL_ID");
	 		 					adjPropertylistBalance.setValue(smoi_conf.get(Conf.avatar_ajd_channel_id).get(0).trim());
	 		 					bsonoBalAndVal.getAdjPropertylistListItem().add(adjPropertylistBalance);
		 					}
		 					
		 					if(!(merchant.trim().equals("") && service.trim().equals(""))){
		 						if(!merchant.trim().equals("")){
		 							AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
		 		 					adjPropertylistBalance.setCode("ADJUST_PARTNER_ID");
		 		 					adjPropertylistBalance.setValue(merchant);
		 		 					bsonoBalAndVal.getAdjPropertylistListItem().add(adjPropertylistBalance);
		 						}
		 						if(!service.trim().equals("")){
		 							AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
		 		 					adjPropertylistBalance.setCode("ADJUST_SERVICE_ID");
		 		 					adjPropertylistBalance.setValue(service);		
		 		 					bsonoBalAndVal.getAdjPropertylistListItem().add(adjPropertylistBalance);
		 						}
		 					}
		 					
		 					bsonoBalAndVal.setDays(validity);
		 					if(!merchant.trim().equals("")){
		 						bsonoBalAndVal.setRemark(merchant);	
				 			}
		 					
		 					adjustCbsBalAndVal.getBSONOBalanceAndValidityListItem().add(bsonoBalAndVal);
		 					Gson gson = new Gson();
		 					msg = gson.toJson(adjustCbsBalAndVal);
		 					
		 					this.setEState(AFState.W_EQL);
						}

					} 
					
					if( isBalanceType && !isValidityType && !isFreeType){
						// adj balance

							smoiIns.setAdjustType(AdjustType.BALANCE); //for tag type adjustment.
//							ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		 					
		 					BSONOBalanceAndFree bsonoBalance = new BSONOBalanceAndFree();
		 					bsonoBalance.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
		 					bsonoBalance.setBso("ADJUST_CBS_BALANCE");
		 					bsonoBalance.setReqType("2");
		 					bsonoBalance.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
		 					bsonoBalance.setMsisdn1(ms);
		 					bsonoBalance.setChannel(smoi_conf.get(Conf.avatar_channel).get(0));
		 					
		 					AdjustmentinfolistBalance adjustmentinfolistBalance = new AdjustmentinfolistBalance();
		 					adjustmentinfolistBalance.setBalanceType(smoi_conf.get(Conf.mainBalanceType).get(0));
		 					
		 					if (flag.equals("1")) {
		 						bsonoBalance.setOperType("2S");
		 					}else if (flag.equals("0")) {
		 						bsonoBalance.setOperType("1");
		 						
		 						if(Integer.parseInt(balance)>=0){
		 							adjustmentinfolistBalance.setAdjustmentType("1");	
		 						}else{
		 							adjustmentinfolistBalance.setAdjustmentType("2");
		 						}
		 					}
		 					
		 					adjustmentinfolistBalance.setAdjustmentAmout(String.valueOf(Math.abs(Integer.parseInt(balance))));
		 					adjustmentinfolistBalance.setCurrencyName("THB");
		 					
		 					if(smoi_conf.get(Conf.avatar_ajd_channel_id) != null 
		 							&&!smoi_conf.get(Conf.avatar_ajd_channel_id).get(0).trim().equals("")){
		 						AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
	 		 					adjPropertylistBalance.setCode("ADJUST_CHANNEL_ID");
	 		 					adjPropertylistBalance.setValue(smoi_conf.get(Conf.avatar_ajd_channel_id).get(0).trim());
	 		 					bsonoBalance.getAdjPropertylistListItem().add(adjPropertylistBalance);
		 					}
		 					
		 					if(!(merchant.trim().equals("") && service.trim().equals(""))){
		 						if(!merchant.trim().equals("")){
		 							AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
		 		 					adjPropertylistBalance.setCode("ADJUST_PARTNER_ID");
		 		 					adjPropertylistBalance.setValue(merchant);
		 		 					bsonoBalance.getAdjPropertylistListItem().add(adjPropertylistBalance);
		 						}
		 						if(!service.trim().equals("")){
		 							AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
		 		 					adjPropertylistBalance.setCode("ADJUST_SERVICE_ID");
		 		 					adjPropertylistBalance.setValue(service);		
		 		 					bsonoBalance.getAdjPropertylistListItem().add(adjPropertylistBalance);
		 						}
		 					}
		 					
		 					bsonoBalance.getAdjustmentinfolistBalanceListItem().add(adjustmentinfolistBalance);
		 					bsonoBalance.setInsufficientFlag("1");
		 					if(!merchant.trim().equals("")){
		 						bsonoBalance.setRemark(merchant);
		 					}
		 					
		 					adjustCbs.getBSONOListItem().add(bsonoBalance);
		 					Gson gson = new Gson();
		 					msg = gson.toJson(adjustCbs);
		 					
		 					this.setEState(AFState.W_EQL);
//						}
	 					
					}
					
					if( isValidityType && !isBalanceType && !isFreeType){
						// adj validity
					    smoiIns.setAdjustType(AdjustType.VALIDITY); //for tag type adjustment.
//					    ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
						EqlBsoAdjustCbsValidity<Object> eqlmessage = new EqlBsoAdjustCbsValidity<Object>();
						eqlmessage.setBwoid(SMOIUtils.createSmoiEQUULEUS(ssid));
						eqlmessage.setRetransmit("0");
						eqlmessage.setUser("INGW-SMOI");
						
						// set BSO for Validity
						BSONO bsono = new BSONO();
						bsono.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
						bsono.setBso("ADJUST_CBS_VALIDITY");
						bsono.setReqType("2");
						bsono.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
						bsono.setMsisdn1(ms);
						bsono.setChannel(smoi_conf.get(Conf.avatar_channel).get(0));
						if (flag.equals("0")) {
							bsono.setOperType("1");
						} else if (flag.equals("1")) {
							bsono.setOperType("2");
						}
						bsono.setDays(validity);
						
						// set BSO for Query
						BSONOQuery bsonoQuery = new BSONOQuery();
						bsonoQuery.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
						bsonoQuery.setBso("QUERY_CBS_SUB");
						bsonoQuery.setReqType("4");
						bsonoQuery.setQueryType("2");
						bsonoQuery.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
						bsonoQuery.setMsisdn1(ms);
						
						eqlmessage.getBSONOListItem().add(bsono);
						eqlmessage.getBSONOListItem().add(bsonoQuery);
						
						Gson gson = new Gson();
						msg = gson.toJson(eqlmessage);
						
						this.setEState(AFState.W_EQL);
						
					}
					
					if(isFreeType){
						// gen BSONO Free
						BSONOBalanceAndFree bsonoFreeUnit = new BSONOBalanceAndFree();
			 			bsonoFreeUnit.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
			 			bsonoFreeUnit.setBso("ADJUST_CBS_FREEUNIT");
			 			bsonoFreeUnit.setReqType("2");
			 			bsonoFreeUnit.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
			 			bsonoFreeUnit.setMsisdn1(ms);
			 			bsonoFreeUnit.setInsufficientFlag("1");	
			 			bsonoFreeUnit.setChannel(smoi_conf.get(Conf.avatar_channel).get(0));
			 			if( isFreeTypePrmmoney && !isBalanceType && !isValidityType ){
							// adj free prmmoney

								smoiIns.setAdjustType(AdjustType.BALANCE); //for tag type adjustment.
//					 			ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
					 			BSONOBalanceAndFree bsonoBalance = new BSONOBalanceAndFree();
					 			bsonoBalance.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
					 			bsonoBalance.setBso("ADJUST_CBS_BALANCE");
					 			bsonoBalance.setReqType("2");
					 			bsonoBalance.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
					 			bsonoBalance.setMsisdn1(ms);
					 			bsonoBalance.setChannel(smoi_conf.get(Conf.avatar_channel).get(0));
					 			
					 			AdjustmentinfolistBalance adjustmentinfolistBalance = new AdjustmentinfolistBalance();
					 			adjustmentinfolistBalance.setBalanceType(smoi_conf.get(Conf.BALANCETYPE_PRMMONEY).get(0));
					 			
					 			if (flag.equals("0")) {
					 				bsonoBalance.setOperType("1");
					 				if(Integer.parseInt(prmmoney)>=0){
					 					adjustmentinfolistBalance.setAdjustmentType("1");	
					 				}else{
					 					adjustmentinfolistBalance.setAdjustmentType("2");
					 				}
					 			} else if (flag.equals("1")) {
					 				bsonoBalance.setOperType("2S");
					 				adjustmentinfolistBalance.setBalanceId(freeresourcesID);
					 			}
					 			
					 			adjustmentinfolistBalance.setAdjustmentAmout(String.valueOf(Math.abs(Long.parseLong(prmmoney))));
					 			adjustmentinfolistBalance.setCurrencyName("THB");
					 			
					 			bsonoBalance.getAdjustmentinfolistBalanceListItem().add(adjustmentinfolistBalance);
					 			bsonoBalance.setInsufficientFlag("1");
					 			
					 			if(smoi_conf.get(Conf.avatar_ajd_channel_id) != null 
			 							&&!smoi_conf.get(Conf.avatar_ajd_channel_id).get(0).trim().equals("")){
			 						AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
		 		 					adjPropertylistBalance.setCode("ADJUST_CHANNEL_ID");
		 		 					adjPropertylistBalance.setValue(smoi_conf.get(Conf.avatar_ajd_channel_id).get(0).trim());
		 		 					bsonoBalance.getAdjPropertylistListItem().add(adjPropertylistBalance);
			 					}
					 			
					 			if(!(merchant.trim().equals("") && service.trim().equals(""))){
			 						if(!merchant.trim().equals("")){
			 							AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
			 		 					adjPropertylistBalance.setCode("ADJUST_PARTNER_ID");
			 		 					adjPropertylistBalance.setValue(merchant);
			 		 					bsonoBalance.getAdjPropertylistListItem().add(adjPropertylistBalance);
			 						}
			 						if(!service.trim().equals("")){
			 							AdjPropertylist adjPropertylistBalance = new AdjPropertylist();
			 		 					adjPropertylistBalance.setCode("ADJUST_SERVICE_ID");
			 		 					adjPropertylistBalance.setValue(service);		
			 		 					bsonoBalance.getAdjPropertylistListItem().add(adjPropertylistBalance);
			 						}
			 					}
					 			
					 			//save data to instance
			 					EQLRequestInstance balIns = new EQLRequestInstance();
			 					balIns.setBsoName(bsonoBalance.getBso());
			 					balIns.setBsoId(bsonoBalance.getBsoid());
			 					smoiIns.getEqlRequestInstance().add(balIns);
					 			
			 					if(!merchant.trim().equals("")){
					 				bsonoBalance.setRemark(merchant);	
					 			}
					 			adjustCbs.getBSONOListItem().add(bsonoBalance);
					 			
					 			Gson gson = new Gson();
					 			msg = gson.toJson(adjustCbs);
					 			
					 			this.setEState(AFState.W_EQL);
//							}
							
						}
			 			
			 			if( isFreeTypePrmsm && !isBalanceType && !isValidityType){
							// adj Free	prmsm			
			 					if(AdjustType.BALANCE.equals(smoiIns.getAdjustType())){
			 						smoiIns.setAdjustType(AdjustType.PRMMONEY_AND_FREEUNIT); //for tag type adjustment.
			 					}else{
			 						smoiIns.setAdjustType(AdjustType.FREEUNIT); //for tag type adjustment.
			 					}
								
					 			//ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
					 			FreeUnitAdjInfo freeUnitAdjInfo = new FreeUnitAdjInfo();
					 			if (flag.equals("0")) {
					 				bsonoFreeUnit.setOperType("1");
					 				if(Integer.parseInt(prmsm) >= 0){
						 				  freeUnitAdjInfo.setAdjType("1");	
						 			  }else{
						 				  freeUnitAdjInfo.setAdjType("2");
						 			  }
					 			} else if (flag.equals("1")) {
					 				bsonoFreeUnit.setOperType("2S");
					 				freeUnitAdjInfo.setFreeUnitInstanceId(freeresourcesID);
					 			}
					 			
					 			freeUnitAdjInfo.setFreeUnitId(smoi_conf.get(Conf.freeUnitId_PRMSM).get(0));
					 			freeUnitAdjInfo.setAdjAmount(String.valueOf(Math.abs(Integer.parseInt(prmsm))));
					 			
					 			genAdjPropertyList(merchant, service, bsonoFreeUnit);
					 			
					 			
					 			
					 			bsonoFreeUnit.getFreeUnitAdjInfoListItem().add(freeUnitAdjInfo);
					 			
//					 			//save data to instance
//			 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
//			 					eqlRequestInstance.setBsoName(bsonoFreeUnit.getBso());
//			 					eqlRequestInstance.setBsoId(bsonoFreeUnit.getBsoid());
//			 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
			 					
					 			if(!merchant.trim().equals("")){
					 				bsonoFreeUnit.setRemark(merchant);	
					 			}
					 			// 2018/04/18
					 			adjustCbs.getBSONOListItem().add(bsonoFreeUnit);
					 			
					 			Gson gson = new Gson();
					 			msg = gson.toJson(adjustCbs);
					 			
					 			this.setEState(AFState.W_EQL);
//							}
				 			
						}
						
						if( isFreeTypePrmminute && !isBalanceType && !isValidityType ){
							// adj Free prmminute
								if(AdjustType.BALANCE.equals(smoiIns.getAdjustType()) ){
									smoiIns.setAdjustType(AdjustType.PRMMONEY_AND_FREEUNIT); //for tag type adjustment.
								}else if(!AdjustType.PRMMONEY_AND_FREEUNIT.equals(smoiIns.getAdjustType())){
									smoiIns.setAdjustType(AdjustType.FREEUNIT); //for tag type adjustment.
								}
//					 			ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
					 			FreeUnitAdjInfo freeUnitAdjInfo = new FreeUnitAdjInfo();
					 			if (flag.equals("0")) {
					 				bsonoFreeUnit.setOperType("1");
					 				if(Integer.parseInt(prmminute) >= 0){
						 				  freeUnitAdjInfo.setAdjType("1");	
						 			  }else{
						 				  freeUnitAdjInfo.setAdjType("2");
						 			  }
					 			} else if (flag.equals("1")) {
					 				bsonoFreeUnit.setOperType("2S");
					 				freeUnitAdjInfo.setFreeUnitInstanceId(freeresourcesID);
					 			}
					 			
					 			freeUnitAdjInfo.setFreeUnitId(smoi_conf.get(Conf.freeUnitId_PRMMINUTE).get(0));
					 			freeUnitAdjInfo.setAdjAmount(String.valueOf(Math.abs(Integer.parseInt(prmminute))));
					 			
					 			genAdjPropertyList(merchant, service, bsonoFreeUnit);
					 			
					 			
					 			bsonoFreeUnit.getFreeUnitAdjInfoListItem().add(freeUnitAdjInfo);
					 			
//					 			//save data to instance
//			 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
//			 					eqlRequestInstance.setBsoName(bsonoFreeUnit.getBso());
//			 					eqlRequestInstance.setBsoId(bsonoFreeUnit.getBsoid());
//			 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
					 			if(!merchant.trim().equals("")){
					 				bsonoFreeUnit.setRemark(merchant);	
					 			}
					 			if(bsonoFreeUnit.getFreeunitadjustmentinfolist() == null){
					 				adjustCbs.getBSONOListItem().add(bsonoFreeUnit);
					 				
					 			}
					 			Gson gson = new Gson();
					 			msg = gson.toJson(adjustCbs);
					 			
					 			this.setEState(AFState.W_EQL);
//							}
							
						}
						
						if( isFreeTypefreerbtsong && !isBalanceType && !isValidityType ){
							// adj Free freerbtsong

								if(AdjustType.BALANCE.equals(smoiIns.getAdjustType())){
									smoiIns.setAdjustType(AdjustType.PRMMONEY_AND_FREEUNIT); //for tag type adjustment.
								}else if(!AdjustType.PRMMONEY_AND_FREEUNIT.equals(smoiIns.getAdjustType())){
		 							smoiIns.setAdjustType(AdjustType.FREEUNIT); //for tag type adjustment.
		 						}
								
//					 			ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
					 			FreeUnitAdjInfo freeUnitAdjInfo = new FreeUnitAdjInfo();
					 			if (flag.equals("0")) {
					 				bsonoFreeUnit.setOperType("1");
					 				if(Integer.parseInt(freerbtsong) >= 0){
						 				  freeUnitAdjInfo.setAdjType("1");	
						 			  }else{
						 				  freeUnitAdjInfo.setAdjType("2");
						 			  }
					 			} else if (flag.equals("1")) {
					 				bsonoFreeUnit.setOperType("2S");
					 				freeUnitAdjInfo.setFreeUnitInstanceId(freeresourcesID);
					 			}
					 			
					 			freeUnitAdjInfo.setFreeUnitId(smoi_conf.get(Conf.freeUnitId_FREERBTSONG).get(0));
					 			freeUnitAdjInfo.setAdjAmount(String.valueOf(Math.abs(Integer.parseInt(freerbtsong))));
			
					 			genAdjPropertyList(merchant, service, bsonoFreeUnit);
					 			
					 			bsonoFreeUnit.getFreeUnitAdjInfoListItem().add(freeUnitAdjInfo);
					 			
//					 			//save data to instance
//			 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
//			 					eqlRequestInstance.setBsoName(bsonoFreeUnit.getBso());
//			 					eqlRequestInstance.setBsoId(bsonoFreeUnit.getBsoid());
//			 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
			 					
					 			if(!merchant.trim().equals("")){
					 				bsonoFreeUnit.setRemark(merchant);	
					 			}
					 			
					 			if(bsonoFreeUnit.getFreeunitadjustmentinfolist() == null){
					 				adjustCbs.getBSONOListItem().add(bsonoFreeUnit);
					 			}
					 			Gson gson = new Gson();
					 			msg = gson.toJson(adjustCbs);
					 			
					 			this.setEState(AFState.W_EQL);
//							}
					}
					
					if(isFreeTypefreerbtsong || isFreeTypePrmminute || isFreeTypePrmsm){
				 			//save data to instance
		 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
		 					eqlRequestInstance.setBsoName(bsonoFreeUnit.getBso());
		 					eqlRequestInstance.setBsoId(bsonoFreeUnit.getBsoid());
		 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
					}	
						
					// set BSO Query Type2 for prmmoney 
					BSONOQuery bsoQueryType2 = new BSONOQuery();
					bsoQueryType2.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
					bsoQueryType2.setBso("QUERY_CBS_SUB");
					bsoQueryType2.setReqType("4");
					bsoQueryType2.setQueryType("2");
					bsoQueryType2.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
					bsoQueryType2.setMsisdn1(ms);
			 		//save data to instance
	 				EQLRequestInstance qrType2Inc = new EQLRequestInstance();
	 				qrType2Inc.setBsoName(bsoQueryType2.getBso());
	 				qrType2Inc.setBsoId(bsoQueryType2.getBsoid());
	 				qrType2Inc.setQueryType("2");
	 				smoiIns.getEqlRequestInstance().add(qrType2Inc);
						
					// set BSO Query Type3 for other free 
					BSONOQuery bsoQueryType3 = new BSONOQuery();
					bsoQueryType3.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
					bsoQueryType3.setBso("QUERY_CBS_SUB");
					bsoQueryType3.setReqType("4");
					bsoQueryType3.setQueryType("3");
					bsoQueryType3.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
					bsoQueryType3.setMsisdn1(ms);
					adjustCbs.getBSONOListItem().add(bsoQueryType2);
			 		adjustCbs.getBSONOListItem().add(bsoQueryType3);
			 		Gson gson = new Gson();
		 			msg = gson.toJson(adjustCbs);
			 		//save data to instance
	 				EQLRequestInstance qrType3Inc = new EQLRequestInstance();
	 				qrType3Inc.setBsoName(bsoQueryType3.getBso());
	 				qrType3Inc.setBsoId(bsoQueryType3.getBsoid());
	 				qrType3Inc.setQueryType("3");
	 				smoiIns.getEqlRequestInstance().add(qrType3Inc);
						

			     }
			   }	
//				for (int i = 0; i < smoiIns.getEqlRequestInstance().size(); i++) {
//					System.out.println("BsoId : "+smoiIns.getEqlRequestInstance().get(i).getBsoId());
//					System.out.println("Name : "+smoiIns.getEqlRequestInstance().get(i).getBsoName());
//				}
				
			}
		return msg;

	}

	private void genAdjPropertyList(String merchant, String service, BSONOBalanceAndFree bsonoFreeUnit) {
		if(bsonoFreeUnit.getAdjPropertylist() == null ){
			if(smoi_conf.get(Conf.avatar_ajd_channel_id) != null 
					&&!smoi_conf.get(Conf.avatar_ajd_channel_id).get(0).trim().equals("")){
				AdjPropertylist adjPropertylist = new AdjPropertylist();
				adjPropertylist.setCode("ADJUST_CHANNEL_ID");
				adjPropertylist.setValue(smoi_conf.get(Conf.avatar_ajd_channel_id).get(0).trim());
				bsonoFreeUnit.getAdjPropertylistListItem().add(adjPropertylist);
			}
			
			if(!(merchant.trim().equals("") && service.trim().equals(""))){
				if( !merchant.trim().equals("")){
					AdjPropertylist adjPropertylist = new AdjPropertylist();
					adjPropertylist.setCode("ADJUST_PARTNER_ID");
					adjPropertylist.setValue(merchant);
					bsonoFreeUnit.getAdjPropertylistListItem().add(adjPropertylist);
				}
				if( !service.trim().equals("")){
					AdjPropertylist adjPropertylist = new AdjPropertylist();
					adjPropertylist.setCode("ADJUST_SERVICE_ID");
					adjPropertylist.setValue(service);		
					bsonoFreeUnit.getAdjPropertylistListItem().add(adjPropertylist);
				}
			}
				
		}
	}
	
	private String mapModiPPSCreditLimitCmd(MyAppData myAppData) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		// String scp = smoiIns.getScp();
		String increment = smoiIns.getHttpPostValue("increment");
		// String flag = smoiIns.getHttpPostValue("flag");
		String channel = smoiIns.getHttpPostValue("channel");
		// String timeout = smoiIns.getHttpPostValue("timeout");
		// String lang = smoiIns.getHttpPostValue("lang");
		int addincrement = 0;

		DoSetNegativeBalance doSetNegativeBalance = new DoSetNegativeBalance();
		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("1037");
		if (channel.equals("")) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		sOper.setChargeFlag((short) 1);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		sOper.setPhoneId(ms);
		doSetNegativeBalance.setSOper(sOper);
		doSetNegativeBalance.setPhoneId(ms);
		// doSetNegativeBalance.setSiteId(smoiIns.getScp());

		try {
			addincrement = Integer.parseInt(increment);
		} catch (Exception ex) {
			addincrement = 0;
			Log.e("increment can not parseInt");
		}

		if (addincrement <= 0) {
			doSetNegativeBalance.setNegativeBalance((short) 0);
		} else {
			doSetNegativeBalance.setNegativeBalance(Short.parseShort(increment));
		}

		doSetNegativeBalance.setNotificationFlag((short) 0);
		doSetNegativeBalance.setNegativeFlag((short) 0);

		SetNegativeBalanceService setNegativeBalanceService = new SetNegativeBalanceService();
		msg = setNegativeBalanceService.buildSoapMessage(doSetNegativeBalance);
		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_SetNegativeBalance");
		return msg;
	}

	private String mapActivatePPSSingSubCmd(MyAppData myAppData) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		// String scp = smoiIns.getScp();
		String channel = smoiIns.getHttpPostValue("channel");
		String lang = smoiIns.getHttpPostValue("lang");
		String location = smoiIns.getHttpPostValue("location");

		DoFirstActivationCRM doFirstActivationCRM = new DoFirstActivationCRM();
		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("1009");
		sOper.setSoMode("1");
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		sOper.setChargeFlag((short) 1);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		sOper.setPhoneId(ms);
		if (channel.equals("") == true) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		doFirstActivationCRM.setSOper(sOper);
		// doFirstActivationCRM.setUserId((long)0);
		doFirstActivationCRM.setPhoneId(ms);
		if (lang.equals("1")) {
			lang = "THA";
		} else if (lang.equals("2")) {
			lang = "ENG";
		} else {
			lang = "THA";
		}
		doFirstActivationCRM.setSMSLanguage(lang);
		doFirstActivationCRM.setUSSDLanguage(lang);
		doFirstActivationCRM.setIVRLanguage(lang);
		doFirstActivationCRM.setLocationCode(location);
		/**
		 * @author Nuiss
		 * @since 20/12/1012
		 * @see fix NotificationFlag =1
		 */
		doFirstActivationCRM.setNotificationFlag((short) 1);
		// doFirstActivationCRM.setSiteId(smoiIns.getScp());

		FirstActivationCRMService soapMessage = new FirstActivationCRMService();
		msg = soapMessage.buildSoapMessage(doFirstActivationCRM);
		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_FirstActivationCRM");
		return msg;
	}

	private String mapAddScrScreenNoCmd(MyAppData myAppData) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		// String scp = smoiIns.getScp();
		String channel = smoiIns.getHttpPostValue("channel");
		String screentype = smoiIns.getHttpPostValue("screentype");
		String dat = smoiIns.getHttpPostValue("dat");
		String[] list_of_number = dat.split("\\%26");

		DoCallScreenNo doCallScreenNo = new DoCallScreenNo();
		// SOperInfo
		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("1029");
		if (channel.equals("") == true) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		sOper.setChargeFlag((short) 1);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		sOper.setPhoneId(ms);

		doCallScreenNo.setSOper(sOper);
		doCallScreenNo.setPhoneId(ms);
		doCallScreenNo.setOperationType((short) 1);
		// doCallScreenNo.setSiteId(smoiIns.getScp());
		// doCallScreenNo.setCallScreenNoType(Short.parseShort(screentype));

		CallScreenNoInfoList callScreenNoInfoList = new CallScreenNoInfoList();

		for (String b_number : list_of_number) {

			SCallScreenNo sCallScreenNo = new SCallScreenNo();
			sCallScreenNo.setCallscreenNo(b_number);

			short listType = (short) (Integer.parseInt(screentype) - 1);
			sCallScreenNo.setListType(listType);

			callScreenNoInfoList.getCallScreenNoInfoListItem().add(sCallScreenNo);

		}

		// SCallScreenNo sCallScreenNo = new SCallScreenNo();
		// sCallScreenNo.setCallscreenNo(dat);
		// short listType = (short) (Integer.parseInt(screentype) - 1);
		// sCallScreenNo.setListType(listType);
		// callScreenNoInfoList.getCallScreenNoInfoListItem().add(sCallScreenNo);
		doCallScreenNo.setCallScreenNoInfoList(callScreenNoInfoList);

		CallScreenNoService soapMessage = new CallScreenNoService();
		msg = soapMessage.buildSoapMessage(doCallScreenNo);
		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_CallScreenNo");
		return msg;
	}

	/*
	 * public static void main(String[] args) { String dat =
	 * "66899010069%2666899010006%2666812550295"; String[] ss =
	 * dat.split("\\%26");
	 * 
	 * System.out.println(ss.length);
	 * 
	 * //smoiIns.setDat(String.valueOf(number_of_b.length)); for (String s : ss)
	 * { System.out.println(s);
	 * 
	 * } }
	 */

	private String mapDeleScrScreenNoCmd(MyAppData myAppData) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		// String scp = smoiIns.getScp();
		String dat = smoiIns.getHttpPostValue("dat");
		String channel = smoiIns.getHttpPostValue("channel");
		String screentype = smoiIns.getHttpPostValue("screentype");
		String[] list_of_bnumber = dat.split("\\%26");

		DoCallScreenNo doCallScreenNo = new DoCallScreenNo();
		// SOperInfo
		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("1029");
		if (channel.equals("") == true) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		sOper.setChargeFlag((short) 1);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		sOper.setPhoneId(ms);
		doCallScreenNo.setSOper(sOper);
		doCallScreenNo.setPhoneId(ms);
		doCallScreenNo.setOperationType((short) 2);
		// doCallScreenNo.setSiteId(smoiIns.getScp());
		// doCallScreenNo.setCallScreenNoType(Short.parseShort(screentype));

		CallScreenNoInfoList callScreenNoInfoList = new CallScreenNoInfoList();

		for (String b_number : list_of_bnumber) {

			SCallScreenNo sCallScreenNo = new SCallScreenNo();
			sCallScreenNo.setCallscreenNo(b_number);

			short listType = (short) (Integer.parseInt(screentype) - 1);
			sCallScreenNo.setListType(listType);

			callScreenNoInfoList.getCallScreenNoInfoListItem().add(sCallScreenNo);
		}

		// SCallScreenNo sCallScreenNo = new SCallScreenNo();
		// sCallScreenNo.setCallscreenNo(dat);
		// short listType = (short) (Integer.parseInt(screentype) - 1);
		// sCallScreenNo.setListType(listType);
		// callScreenNoInfoList.getCallScreenNoInfoListItem().add(sCallScreenNo);
		doCallScreenNo.setCallScreenNoInfoList(callScreenNoInfoList);

		CallScreenNoService soapMessage = new CallScreenNoService();
		msg = soapMessage.buildSoapMessage(doCallScreenNo);
		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_CallScreenNo");
		return msg;
	}

	private String mapModiScrWhiteListCmd(MyAppData myAppData) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		// String scp = smoiIns.getScp();
		String dat = smoiIns.getHttpPostValue("dat");
		String channel = smoiIns.getHttpPostValue("channel");
		// String screentype = smoiIns.getHttpPostValue("screentype");

		DoCallScreenNo doCallScreenNo = new DoCallScreenNo();
		// SOperInfo
		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("1029");
		if (channel.equals("") == true) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		sOper.setChargeFlag((short) 1);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		sOper.setPhoneId(ms);
		doCallScreenNo.setSOper(sOper);
		doCallScreenNo.setPhoneId(ms);
		doCallScreenNo.setOperationType((short) 3);
		// doCallScreenNo.setSiteId(smoiIns.getScp());

		CallScreenNoInfoList callScreenNoInfoList = new CallScreenNoInfoList();
		SCallScreenNo sCallScreenNo = new SCallScreenNo();
		// sCallScreenNo.setCallscreenNo(dat);

		String modifyWL = smoi_conf.get(Conf.ModifyWLAlternateSolutionFlag).get(0); // ->
																					// verify
																					// @
																					// conf

		if (dat.equals("1")) {
			if (modifyWL.equals("0")) {
				sCallScreenNo.setRouteNumber("6681900129");
				sCallScreenNo.setRoutingMethod((short) 3);
			} else { // modifyWL = 1
				sCallScreenNo.setRoutingMethod((short) 2);
			}
		} else if (dat.equals("0")) {
			sCallScreenNo.setRoutingMethod((short) 1);
		}

		/*
		 * // active wellcome call if (dat.equals("1") == true) {
		 * sCallScreenNo.setRoutingMethod((short) 2);
		 * sCallScreenNo.setRouteNumber("6681900129");
		 * //doCallScreenNo.setCallScreenNoType((short) 2);
		 * 
		 * // deactive wellcome call } else if (dat.equals("0") == true) {
		 * sCallScreenNo.setRoutingMethod((short) 1);
		 * //doCallScreenNo.setCallScreenNoType((short) 1); }
		 */

		// CallScreenNoInfoList callScreenNoInfoList = new
		// CallScreenNoInfoList();
		// SCallScreenNo sCallScreenNo = new SCallScreenNo();
		// sCallScreenNo.setCallscreenNo(dat);
		// sCallScreenNo.setRoutingMethod((short) 1);
		// sCallScreenNo.setEffectiveDate(date);
		// sCallScreenNo.setExpireDate(date);
		// sCallScreenNo.setEffectTime("090000");
		// sCallScreenNo.setExpireTime("180000");
		callScreenNoInfoList.getCallScreenNoInfoListItem().add(sCallScreenNo);
		doCallScreenNo.setCallScreenNoInfoList(callScreenNoInfoList);

		CallScreenNoService soapMessage = new CallScreenNoService();
		msg = soapMessage.buildSoapMessage(doCallScreenNo);
		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_CallScreenNo");
		return msg;
	}

	private String mapModiPPSValidity(MyAppData myAppData) { // Chatl 20/09/2017
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		smoiIns.setAdjustType(AdjustType.VALIDITY);
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		String flag = smoiIns.getHttpPostValue("flag");
		String increment = smoiIns.getHttpPostValue("increment");

		EqlBsoAdjustCbsValidity<BSONO> adjustCbsValidity = new EqlBsoAdjustCbsValidity<BSONO>();

		BSONO bsono = new BSONO();
		bsono.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
		bsono.setBso("ADJUST_CBS_VALIDITY");
		bsono.setReqType("2");
		bsono.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
		bsono.setMsisdn1(ms);
		if ("0".equals(flag)) {
			bsono.setOperType("1");
		} else if ("1".equals(flag)) {
			bsono.setOperType("2");
		}
		bsono.setDays(increment);
		adjustCbsValidity.setRetransmit("0");
		if (smoiIns.isRetry()) {
			adjustCbsValidity.setBwoid(smoiIns.getBwoid());
		} else {
			String bwoid = SMOIUtils.createSmoiEQUULEUS(ssid);
			adjustCbsValidity.setBwoid(bwoid);
			smoiIns.setBwoid(bwoid);
		}
		adjustCbsValidity.setUser("INGW-SMOI");
		adjustCbsValidity.getBSONOListItem().add(bsono);
		Gson gson = new Gson();
		msg = gson.toJson(adjustCbsValidity);
		this.setEState(AFState.W_EQL);
		return msg;
	}

	private String mapDispScrScreenNoCmd(MyAppData myAppData) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		// String scp = smoiIns.getScp();
		String channel = smoiIns.getHttpPostValue("channel");

		DoQueryCallScreen doQueryCallScreen = new DoQueryCallScreen();
		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("6017");
		if (channel.equals("") == true) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		sOper.setChargeFlag((short) 1);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		sOper.setPhoneId(ms);
		doQueryCallScreen.setSOper(sOper);
		doQueryCallScreen.setPhoneId(ms);
		// doQueryCallScreen.setSiteId(smoiIns.getScp());

		QueryCallScreen soapMessage = new QueryCallScreen();
		msg = soapMessage.buildSoapMessage(doQueryCallScreen);
		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_QueryCallScreen");
		return msg;
	}

	private String mapModiPPSCallNotifyFlag(MyAppData myAppData) {
		// String msg;
		StringBuilder msg = new StringBuilder();
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		String flag = smoiIns.getFlag();
		String channel = smoiIns.getHttpPostValue("channel");

		// DoManageSCPProfile doManageSCPProfile = new DoManageSCPProfile();
		// SOperInfo sOper = new SOperInfo();

		// sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		// sOper.setBusiCode("1043");

		/*
		 * if (channel.equals("") == true) { sOper.setSoMode("IVR"); } else {
		 * sOper.setSoMode(channel); }
		 */
		// sOper.setSourceSystem("INGW");
		// sOper.setRemark(sgw);
		// sOper.setChargeFlag((short) 1);

		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}

		/*
		 * if(!flag.equals("")){ SUserAuth sUserAuth = new SUserAuth();
		 * sUserAuth.setInterIntraflag(Short.parseShort(flag)); //
		 * doManageSCPProfile.setSUserAuth(sUserAuth); }
		 */

		// sOper.setSoDate(gc);
		// sOper.setPhoneId(ms);
		// doManageSCPProfile.setSOper(sOper);
		// doManageSCPProfile.setPhoneId(ms);
		// doQueryCallScreen.setSiteId(smoiIns.getScp());

		// ManageSCPProfile soapMessage = new ManageSCPProfile();
		// msg = soapMessage.buildSoapMessage(doManageSCPProfile);

		msg.append("<UriOverride value=\"/BSSBroker/Gateways/ManageSCPProfile\" />");
		msg.append("<HeaderOverride name=\"SOAPAction\" value=\"do_ManageSCPProfile\" />");
		msg.append("<soapenv:Envelope");
		msg.append(" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		msg.append("<soapenv:Header/>");
		msg.append("<soapenv:Body>");
		msg.append("<ns2:do_ManageSCPProfile");
		msg.append(" xmlns:com=\"http://www.huawei.com/bme/cbsinterface/common\"");
		msg.append(" xmlns=\"http://www.asiainfo.com/obd/CommonComponents.obd\"");
		msg.append(" xmlns:ns2=\"http://www.asiainfo.com/obd/InfoSyncDefines.obd\">");
		msg.append("<ns2:sOper>");
		msg.append("<com:_so_nbr>" + SMOIUtils.createSmoiSessionIdKey(sgw, ssid) + "</com:_so_nbr>");
		msg.append("<com:_busi_code>1043</com:_busi_code>");

		if (channel.equals("") == true) {
			msg.append("<com:_so_mode>IVR</com:_so_mode>");
		} else {
			msg.append("<com:_so_mode>" + channel + "</com:_so_mode>");
		}
		msg.append("<com:_source_system>INGW</com:_source_system>");
		msg.append("<com:_charge_flag>" + (short) 1 + "</com:_charge_flag>");
		msg.append("<com:_so_date>" + gc + "</com:_so_date>");
		msg.append("</ns2:sOper>");
		msg.append("<ns2:_phone_id>" + ms + "</ns2:_phone_id>");
		msg.append("<ns2:SUserAuth>");
		msg.append("<InterIntraflag>" + flag + "</InterIntraflag>");
		msg.append("</ns2:SUserAuth>");
		msg.append("</ns2:do_ManageSCPProfile>");
		msg.append("</soapenv:Body>");
		msg.append("</soapenv:Envelope>");

		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_ManageSCPProfile");

		return msg.toString();
	}

	private String mapQuerySCPProfile(MyAppData myAppData) {
		StringBuilder msg = new StringBuilder();
		// String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		String channel = smoiIns.getHttpPostValue("channel");

		/*
		 * DoQuerySCPProfile doQuerySCPProfile = new DoQuerySCPProfile();
		 * SOperInfo sOper = new SOperInfo();
		 * 
		 * sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		 * sOper.setBusiCode("6016");
		 * 
		 * if (channel.equals("") == true) { sOper.setSoMode("IVR"); } else {
		 * sOper.setSoMode(channel); } sOper.setSourceSystem("INGW"); //
		 * sOper.setRemark(sgw); sOper.setChargeFlag((short) 1);
		 */

		if (null == channel || channel.equals("")) {
			channel = "IVR";
		} else {
			channel = channel;
		}

		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}

		/*
		 * if(!flag.equals("")){ SUserAuth sUserAuth = new SUserAuth();
		 * sUserAuth.setInterIntraflag(Short.parseShort(flag));
		 * doQuerySCPProfile.setSUserAuth(sUserAuth); }
		 */

		/*
		 * sOper.setSoDate(gc); sOper.setPhoneId(ms); if (!sgw.equals("")) {
		 * sOper.setRemark(sgw); } doQuerySCPProfile.setSOper(sOper);
		 * doQuerySCPProfile.setPhoneId(ms);
		 * //doQueryCallScreen.setSiteId(smoiIns.getScp());
		 * 
		 * QuerySCPProfile soapMessage = new QuerySCPProfile(); msg =
		 * soapMessage.buildSoapMessage(doQuerySCPProfile);
		 * this.setEState(AFState.W_BSSbroker);
		 * smoiIns.setMapCmd("do_QuerySCPProfile"); return msg;
		 */

		msg.append("<UriOverride value=\"/BSSBroker/Gateways/QuerySCPProfile\" />\n"
				+ "<HeaderOverride name=\"SOAPAction\" value=\"do_QuerySCPProfile\" />\n"
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "\t<soapenv:Header/>\n" + "\t<soapenv:Body>\n"
				+ "\t\t<ns2:do_QuerySCPProfile xmlns=\"http://www.asiainfo.com/obd/CommonComponents.obd\" xmlns:ns2=\"http://www.asiainfo.com/obd/InfoSyncDefines.obd\" xmlns:com=\"http://www.asiainfo.com/obd/CommonComponents.obd\">\n"
				+ "\t\t\t<ns2:sOper>\n" + "\t\t\t\t<com:_so_nbr>" + SMOIUtils.createSmoiSessionIdKey(sgw, ssid)
				+ "</com:_so_nbr>\n" + "\t\t\t\t<com:_busi_code>6016</com:_busi_code>\n" + "\t\t\t\t<com:_so_mode>"
				+ channel + "</com:_so_mode>\n" + "\t\t\t\t<com:_source_system>INGW</com:_source_system>\n"
				+ "\t\t\t\t<com:_charge_flag>1</com:_charge_flag>\n" + "\t\t\t\t<com:_so_date>" + gc
				+ "</com:_so_date>\n" + "\t\t\t</ns2:sOper>\n" + "\t\t\t<ns2:_phone_id>" + ms + "</ns2:_phone_id>\n"
				+ "\t\t</ns2:do_QuerySCPProfile>\n" + "\t</soapenv:Body>\n" + "</soapenv:Envelope>");

		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_QuerySCPProfile");
		return msg.toString();
	}

	/**
	 * //*@see phase 2
	 *
	 * private String mapModiScrScreenTypeCmd(MyAppData myAppData) { String msg;
	 * SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith08); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); //String dat =
	 * smoiIns.getHttpPostValue("dat"); String channel =
	 * smoiIns.getHttpPostValue("channel"); String screentype =
	 * smoiIns.getHttpPostValue("screentype");
	 *
	 * DoCallScreenNo doCallScreenNo = new DoCallScreenNo(); //SOperInfo
	 * SOperInfo sOper = new SOperInfo();
	 * sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
	 * sOper.setBusiCode("1029"); if (channel.equals("") == true) {
	 * sOper.setSoMode("IVR"); } else { sOper.setSoMode(channel); }
	 * sOper.setSourceSystem(sgw); sOper.setChargeFlag((short) 1); Date date =
	 * new Date(); GregorianCalendar gregory = new GregorianCalendar();
	 * gregory.setTime(date); XMLGregorianCalendar gc = null; try { gc =
	 * DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory); } catch
	 * (DatatypeConfigurationException e) { Log.e("Error Massage:" +
	 * e.getMessage(), e); } sOper.setSoDate(gc); sOper.setPhoneId(ms);
	 * doCallScreenNo.setSOper(sOper); doCallScreenNo.setPhoneId(ms);
	 * doCallScreenNo.setOperationType((short) 3);
	 * doCallScreenNo.setCallScreenNoType(Short.parseShort(screentype));
	 *
	 * CallScreenNoInfoList callScreenNoInfoList = new CallScreenNoInfoList();
	 * SCallScreenNo sCallScreenNo = new SCallScreenNo();
	 * //sCallScreenNo.setCallscreenNo(dat);
	 * sCallScreenNo.setRoutingMethod((short) 4);
	 * //sCallScreenNo.setEffectiveDate(date);
	 * //sCallScreenNo.setExpireDate(date);
	 * //sCallScreenNo.setEffectTime("090000");
	 * //sCallScreenNo.setExpireTime("180000");
	 * callScreenNoInfoList.getCallScreenNoInfoListItem().add(sCallScreenNo);
	 * doCallScreenNo.setCallScreenNoInfoList(callScreenNoInfoList);
	 *
	 * CallScreenNoService soapMessage = new CallScreenNoService(); msg =
	 * soapMessage.buildSoapMessage(doCallScreenNo);
	 * this.setEState(AFState.W_BSSbroker);
	 * smoiIns.setMapCmd("do_CallScreenNo"); return msg; }
	 *
	 * private String mapActPPSRBTCmd(MyAppData myAppData) { String msg;
	 * SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith08); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); String dat =
	 * smoiIns.getHttpPostValue("dat"); String channel =
	 * smoiIns.getHttpPostValue("channel");
	 *
	 * DoChangeService doChangeService = new DoChangeService(); SOperInfo sOper
	 * = new SOperInfo(); sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw,
	 * ssid)); sOper.setBusiCode("1021"); if ("".equals(channel)) {
	 * sOper.setSoMode("IVR"); } else { sOper.setSoMode(channel); }
	 * sOper.setSourceSystem(sgw); sOper.setChargeFlag((short) 1); Date date =
	 * new Date(); GregorianCalendar gregory = new GregorianCalendar();
	 * gregory.setTime(date); XMLGregorianCalendar gc = null; try { gc =
	 * DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory); } catch
	 * (DatatypeConfigurationException e) { Log.e("Error Massage:" +
	 * e.getMessage(), e); } sOper.setSoDate(gc); sOper.setPhoneId(ms);
	 * doChangeService.setSOper(sOper);
	 *
	 * ChangeService changeService = new ChangeService();
	 * changeService.setPhoneId(ms); SProductOrder sProductOrder = new
	 * SProductOrder(); //sProductOrder.setProductId(null);
	 * sProductOrder.setProductClass((short) 56); SProductOrderOper
	 * sProductOrderOper = new SProductOrderOper();
	 * sProductOrderOper.setOperType(Short.parseShort(dat)); SProductOrderList
	 * sProductOrderList = new SProductOrderList();
	 * sProductOrderOper.setSProductOrderList(sProductOrderList);
	 * sProductOrderOper.getSProductOrderList().getSProductOrderListItem().add(
	 * sProductOrder); SProductOrderOperList sProductOrderOperList = new
	 * SProductOrderOperList();
	 * sProductOrderOperList.getSProductOrderOperListItem().add(
	 * sProductOrderOper);
	 * changeService.setSProductOrderOperList(sProductOrderOperList);
	 * ChangeServiceService changeServiceService = new ChangeServiceService();
	 * InquirySubscriberResponse inqRes =
	 * smoiIns.getInquirySubscriberResponse(); if(inqRes!=null){ String rsltInfo
	 * = inqRes.getResultInfo();
	 * if(rsltInfo!=null&&!rsltInfo.trim().equals("")){ String resultInfo[] =
	 * rsltInfo.split(","); if(resultInfo!=null&&resultInfo.length>=10){ String
	 * brandId = resultInfo[10]; Log.d("brandId = "+brandId);
	 * changeService.setBrandId(brandId); } } }
	 *
	 * doChangeService.setChangeService(changeService); msg =
	 * changeServiceService.buildSoapMessage(doChangeService);
	 * this.setEState(AFState.W_BSSbroker);
	 * smoiIns.setMapCmd("do_changeService"); return msg; }
	 *
	 * private String mapDelePPSPkgresCmd(MyAppData myAppData) { String msg;
	 * SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith08); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); String pkgid =
	 * smoiIns.getHttpPostValue("pkgid"); String channel =
	 * smoiIns.getHttpPostValue("channel");
	 *
	 * DoChangeService doChangeService = new DoChangeService(); SOperInfo sOper
	 * = new SOperInfo(); sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw,
	 * ssid)); sOper.setBusiCode("1021"); if (channel.equals("")) {
	 * sOper.setSoMode("IVR"); } else { sOper.setSoMode(channel); }
	 * sOper.setSourceSystem(sgw); sOper.setChargeFlag((short) 1); Date date =
	 * new Date(); GregorianCalendar gregory = new GregorianCalendar();
	 * gregory.setTime(date); XMLGregorianCalendar gc = null; try { gc =
	 * DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory); } catch
	 * (DatatypeConfigurationException e) { Log.e("Error Massage:" +
	 * e.getMessage(), e); } sOper.setSoDate(gc); sOper.setPhoneId(ms);
	 * doChangeService.setSOper(sOper);
	 *
	 * ChangeService changeService = new ChangeService();
	 * changeService.setPhoneId(ms); SProductOrder sProductOrder = new
	 * SProductOrder(); sProductOrder.setProductId(Long.parseLong(pkgid));
	 * SProductOrderOper sProductOrderOper = new SProductOrderOper();
	 * sProductOrderOper.setOperType((short) 1); SProductOrderList
	 * sProductOrderList = new SProductOrderList();
	 * sProductOrderOper.setSProductOrderList(sProductOrderList);
	 * sProductOrderOper.getSProductOrderList().getSProductOrderListItem().add(
	 * sProductOrder); SProductOrderOperList sProductOrderOperList = new
	 * SProductOrderOperList();
	 * sProductOrderOperList.getSProductOrderOperListItem().add(
	 * sProductOrderOper);
	 * changeService.setSProductOrderOperList(sProductOrderOperList);
	 * ChangeServiceService changeServiceService = new ChangeServiceService();
	 * InquirySubscriberResponse inqRes =
	 * smoiIns.getInquirySubscriberResponse(); if(inqRes!=null){ String rsltInfo
	 * = inqRes.getResultInfo(); Log.d("rsltInfo = "+rsltInfo);
	 * if(rsltInfo!=null&&!rsltInfo.trim().equals("")){ String resultInfo[] =
	 * rsltInfo.split(","); if(resultInfo!=null&&resultInfo.length>=10){ String
	 * brandId = resultInfo[10]; changeService.setBrandId(brandId); } } }
	 * doChangeService.setChangeService(changeService);
	 *
	 * msg = changeServiceService.buildSoapMessage(doChangeService);
	 * this.setEState(AFState.W_BSSbroker);
	 * smoiIns.setMapCmd("do_changeService"); return msg; }
	 *
	 * private String mapDelePPSPackageIdCmd(MyAppData myAppData) { String msg;
	 * SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith08); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); String packageid =
	 * smoiIns.getHttpPostValue("packageid"); String prodseqid =
	 * smoiIns.getHttpPostValue("prodseqid"); String channel =
	 * smoiIns.getHttpPostValue("channel");
	 *
	 * DoChangeService doChangeService = new DoChangeService(); SOperInfo sOper
	 * = new SOperInfo(); sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw,
	 * ssid)); sOper.setBusiCode("1021"); if (channel.equals("")) {
	 * sOper.setSoMode("IVR"); } else { sOper.setSoMode(channel); }
	 * sOper.setSourceSystem(sgw); sOper.setChargeFlag((short) 1); Date date =
	 * new Date(); GregorianCalendar gregory = new GregorianCalendar();
	 * gregory.setTime(date); XMLGregorianCalendar gc = null; try { gc =
	 * DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory); } catch
	 * (DatatypeConfigurationException e) { Log.e("Error Massage:" +
	 * e.getMessage(), e); } sOper.setSoDate(gc); sOper.setPhoneId(ms);
	 * doChangeService.setSOper(sOper);
	 *
	 * ChangeService changeService = new ChangeService();
	 * changeService.setPhoneId(ms); SProductOrder sProductOrder = new
	 * SProductOrder();
	 *
	 * try{ if(packageid!=null&&!packageid.trim().equals("")){ long
	 * packageidLong = Long.valueOf(packageid);
	 * sProductOrder.setProductId(packageidLong); }
	 * if(prodseqid!=null&&!prodseqid.trim().equals("")){ long prodseqidLong =
	 * Long.valueOf(prodseqid);
	 * sProductOrder.setProductSequenceId(prodseqidLong); } }catch(Exception
	 * ex){
	 *
	 * } //sProductOrder.setProductId(Long.parseLong(packageid));
	 * //sProductOrder.setProductSequenceId(Long.parseLong(prodseqid));
	 * SProductOrderOper sProductOrderOper = new SProductOrderOper();
	 * sProductOrderOper.setOperType((short) 1); SProductOrderList
	 * sProductOrderList = new SProductOrderList();
	 * sProductOrderOper.setSProductOrderList(sProductOrderList);
	 * sProductOrderOper.getSProductOrderList().getSProductOrderListItem().add(
	 * sProductOrder); SProductOrderOperList sProductOrderOperList = new
	 * SProductOrderOperList();
	 * sProductOrderOperList.getSProductOrderOperListItem().add(
	 * sProductOrderOper);
	 * changeService.setSProductOrderOperList(sProductOrderOperList);
	 * ChangeServiceService changeServiceService = new ChangeServiceService();
	 * InquirySubscriberResponse inqRes =
	 * smoiIns.getInquirySubscriberResponse(); if(inqRes!=null){ String rsltInfo
	 * = inqRes.getResultInfo();
	 * if(rsltInfo!=null&&!rsltInfo.trim().equals("")){ String resultInfo[] =
	 * rsltInfo.split(","); if(resultInfo!=null&&resultInfo.length>=10){ String
	 * brandId = resultInfo[10]; changeService.setBrandId(brandId); } } }
	 * doChangeService.setChangeService(changeService);
	 *
	 * msg = changeServiceService.buildSoapMessage(doChangeService);
	 * this.setEState(AFState.W_BSSbroker);
	 * smoiIns.setMapCmd("do_changeService"); return msg; }
	 *
	 *
	 *
	 * private String mapSetPPSRewardCmd(MyAppData myAppData) { String msg;
	 * SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith08); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); String pkgid =
	 * smoiIns.getHttpPostValue("pkgid"); String channel =
	 * smoiIns.getHttpPostValue("channel"); String charge =
	 * smoiIns.getHttpPostValue("charge");
	 *
	 * DoChangeService doChangeService = new DoChangeService(); SOperInfo sOper
	 * = new SOperInfo(); sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw,
	 * ssid)); sOper.setBusiCode("1021"); if (channel.equals("") == true) {
	 * sOper.setSoMode("IVR"); } else { sOper.setSoMode(channel); }
	 * sOper.setSourceSystem(sgw); Date date = new Date(); GregorianCalendar
	 * gregory = new GregorianCalendar(); gregory.setTime(date);
	 * XMLGregorianCalendar gc = null; try { gc =
	 * DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory); } catch
	 * (DatatypeConfigurationException e) { Log.e("Error Massage:" +
	 * e.getMessage(), e); } sOper.setSoDate(gc); if (charge.equals("")) {
	 * sOper.setChargeFlag((short) 1); } else {
	 * sOper.setChargeFlag(Short.parseShort(charge)); } sOper.setPhoneId(ms);
	 * doChangeService.setSOper(sOper);
	 *
	 * ChangeService changeService = new ChangeService();
	 * changeService.setPhoneId(ms); SProductOrder sProductOrder = new
	 * SProductOrder(); sProductOrder.setProductId(Long.parseLong(pkgid));
	 * SProductOrderOper sProductOrderOper = new SProductOrderOper();
	 * sProductOrderOper.setOperType((short) 0);
	 * sProductOrderOper.getSProductOrderList().getSProductOrderListItem().add(
	 * sProductOrder); SProductOrderOperList sProductOrderOperList = new
	 * SProductOrderOperList();
	 * sProductOrderOperList.getSProductOrderOperListItem().add(
	 * sProductOrderOper);
	 * changeService.setSProductOrderOperList(sProductOrderOperList);
	 * doChangeService.setChangeService(changeService);
	 *
	 * ChangeServiceService soapMessage = new ChangeServiceService(); msg =
	 * soapMessage.buildSoapMessage(doChangeService);
	 * this.setEState(AFState.W_BSSbroker);
	 * smoiIns.setMapCmd("do_changeService"); return msg; }
	 *
	 * private String mapDispPPSFntelNoCmd(MyAppData myAppData) { String msg;
	 * SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith08); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); String channel =
	 * smoiIns.getHttpPostValue("channel");
	 *
	 * DoQueryFN doQueryFN = new DoQueryFN(); SOperInfo sOper = new SOperInfo();
	 * sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
	 * sOper.setBusiCode("6014"); if (channel.equals("") == true) {
	 * sOper.setSoMode("IVR"); } else { sOper.setSoMode(channel); }
	 * sOper.setSourceSystem(sgw); sOper.setChargeFlag((short) 1); Date date =
	 * new Date(); GregorianCalendar gregory = new GregorianCalendar();
	 * gregory.setTime(date); XMLGregorianCalendar gc = null; try { gc =
	 * DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory); } catch
	 * (DatatypeConfigurationException e) { Log.e("Error Massage:" +
	 * e.getMessage(), e); } sOper.setSoDate(gc); sOper.setPhoneId(ms);
	 * doQueryFN.setSOper(sOper); doQueryFN.setPhoneId(ms); // String _phone_id
	 * = ms; // long product_id = 100001; // long product_seq = 36250; QueryFN
	 * soapMessage = new QueryFN(); msg =
	 * soapMessage.buildSoapMessage(doQueryFN);
	 * this.setEState(AFState.W_BSSbroker); smoiIns.setMapCmd("do_QueryFN");
	 * return msg; }
	 *
	 * private String mapModiPPSLanguageCmd(MyAppData myAppData) { String msg;
	 * SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith08); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); String channel =
	 * smoiIns.getHttpPostValue("channel"); String lang =
	 * smoiIns.getHttpPostValue("lang");
	 *
	 * DoModifySubsciberBasicInfo doModifySubsciberBasicInfo = new
	 * DoModifySubsciberBasicInfo(); SOperInfo sOper = new SOperInfo();
	 * sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
	 * sOper.setBusiCode("1033"); if (channel.equals("") == true) {
	 * sOper.setSoMode("IVR"); } else { sOper.setSoMode(channel); }
	 * sOper.setSourceSystem(sgw); sOper.setChargeFlag((short) 1); Date date =
	 * new Date(); GregorianCalendar gregory = new GregorianCalendar();
	 * gregory.setTime(date); XMLGregorianCalendar gc = null; try { gc =
	 * DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory); } catch
	 * (DatatypeConfigurationException e) { Log.e("Error Massage:" +
	 * e.getMessage(), e); } sOper.setSoDate(gc); sOper.setPhoneId(ms);
	 * doModifySubsciberBasicInfo.setSOper(sOper);
	 *
	 * SubscriberBasicInfo subscriberBasicInfo = new SubscriberBasicInfo(); if
	 * (lang.equals("1")) { lang = "THA"; } else if (lang.equals("2")) { lang =
	 * "ENG"; } else { lang = "THA"; } subscriberBasicInfo.setIVRLanguage(lang);
	 * subscriberBasicInfo.setPhoneId(ms);
	 * doModifySubsciberBasicInfo.setSubscriberBasicInfo(subscriberBasicInfo);
	 *
	 * ModifySubscriberBasicInfoService soapMessage = new
	 * ModifySubscriberBasicInfoService(); msg =
	 * soapMessage.buildSoapMessage(doModifySubsciberBasicInfo);
	 * this.setEState(AFState.W_BSSbroker);
	 * smoiIns.setMapCmd("do_ModifySubscriberBasicInfo"); return msg; }
	 *
	 * private String mapModiPPSSMSLanguageCmd(MyAppData myAppData) { String
	 * msg; SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith08); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); String channel =
	 * smoiIns.getHttpPostValue("channel"); String lang =
	 * smoiIns.getHttpPostValue("lang");
	 *
	 * DoModifySubsciberBasicInfo doModifySubsciberBasicInfo = new
	 * DoModifySubsciberBasicInfo(); SOperInfo sOper = new SOperInfo();
	 * sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
	 * sOper.setBusiCode("1033"); if (channel.equals("") == true) {
	 * sOper.setSoMode("IVR"); } else { sOper.setSoMode(channel); }
	 * sOper.setSourceSystem(sgw); sOper.setChargeFlag((short) 1); Date date =
	 * new Date(); GregorianCalendar gregory = new GregorianCalendar();
	 * gregory.setTime(date); XMLGregorianCalendar gc = null; try { gc =
	 * DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory); } catch
	 * (DatatypeConfigurationException e) { Log.e("Error Massage:" +
	 * e.getMessage(), e); } sOper.setSoDate(gc); sOper.setPhoneId(ms);
	 * doModifySubsciberBasicInfo.setSOper(sOper);
	 *
	 * SubscriberBasicInfo subscriberBasicInfo = new SubscriberBasicInfo(); if
	 * (lang.equals("1")) { lang = "THA"; } else if (lang.equals("2")) { lang =
	 * "ENG"; } else { lang = "THA"; }
	 * subscriberBasicInfo.setUSSDLanguage(lang);
	 * subscriberBasicInfo.setSMSLanguage(lang);
	 * subscriberBasicInfo.setPhoneId(ms);
	 * doModifySubsciberBasicInfo.setSubscriberBasicInfo(subscriberBasicInfo);
	 *
	 * ModifySubscriberBasicInfoService soapMessage = new
	 * ModifySubscriberBasicInfoService(); msg =
	 * soapMessage.buildSoapMessage(doModifySubsciberBasicInfo);
	 * this.setEState(AFState.W_BSSbroker);
	 * smoiIns.setMapCmd("do_ModifySubscriberBasicInfo"); return msg; }
	 *
	 *
	 *
	 * private String mapChgtrigChrgAcntCmd(MyAppData myAppData) { String msg;
	 * SmoiInstance smoiIns = myAppData.getSmoiIns(); String sgw =
	 * smoiIns.getSgw(); String ms = smoiIns.getMsisdn(); ms = Msisdn.format(ms,
	 * Msisdn.MSISDN_FORMAT.FirstWith66); String ssid = smoiIns.getSsid();
	 * //String scp = smoiIns.getScp(); String dat =
	 * smoiIns.getHttpPostValue("dat");
	 *
	 * //dat != 3 And profile is BOS String batchNo =
	 * smoiIns.getHttpPostValue("batchno"); //String fee =
	 * smoiIns.getHttpPostValue("fee"); String pin =
	 * smoiIns.getHttpPostValue("pin");
	 *
	 * String originHost = smoi_conf.get(Conf.ingwSmoi_OriginHost).get(0);
	 * String originRealm = smoi_conf.get(Conf.ingwSmoi_OriginRealm).get(0);
	 * String destinationHost = smoi_conf.get(Conf.bos_DestinationHost).get(0);
	 * String destinationRealm =
	 * smoi_conf.get(Conf.bos_DestinationRealm).get(0);
	 *
	 * CCRMessage ccr = new CCRMessage();
	 * ccr.setServiceId(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
	 * ccr.setOriginHost(originHost); ccr.setOriginRealm(originRealm);
	 * ccr.setDestinationHost(destinationHost);
	 * ccr.setDestinationRealm(destinationRealm); ccr.setAuthApplicationId("4");
	 * ccr.setServiceContextId("TopupBalance@ais.co.th");
	 * ccr.setCcRequestType("4"); ccr.setCcRequestNumber("0");
	 * ccr.setEventTimeStamp(String.valueOf(DateTime.getSecondNow()));
	 * ccr.setRequestAction("0"); ccr.setSubscriptionIdType("0");
	 * ccr.setSubscriptionIdData(ms); ccr.setEtopupSessionId(batchNo);
	 * //ccr.setRechargeNumber("*120"); //ccr.setRechargeMoney_money(fee);
	 * //ccr.setRechargeMethod("8"); ccr.setRechargePartnerId(pin);
	 * ccr.setServiceId(dat);
	 *
	 * EquinoxMessageBuilder builder = EquinoxMessageBuilder.newInstance();
	 * builder.setMessage(ccr); builder.createXMLMessage(); msg =
	 * builder.getMessage(); this.setEState(AFState.W_BOS_DCC);
	 * smoiIns.setMapCmd("TopupBalance"); return msg; }
	 */
	private String mapSetPPSRewardCmd_Balance_and_Validity_BSSBroker(MyAppData myAppData, String balance,
			String validity) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();

		DoAdjustBalance doAdjustBalance = new DoAdjustBalance();

		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("1042");
		String channel = smoiIns.getHttpPostValue("channel");
		if (channel.equals("")) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		sOper.setPhoneId(ms);

		doAdjustBalance.setSOper(sOper);
		// doAdjustBalance.setSiteId(smoiIns.getScp());
		// 1:adjust balance,
		// 2:adjust validity
		// 3:adjust balance and validity
		Short adjustType = (short) 1;
		if (isNotEmpty(validity)) {
			adjustType = (short) 2;
		}
		if (isNotEmpty(balance) && isNotEmpty(validity)) {
			adjustType = (short) 3;
		}
		doAdjustBalance.setAdjustType(adjustType);
		doAdjustBalance.setPhoneId(ms);
		String flag = smoiIns.getHttpPostValue("flag");
		if ("".equals(flag)) {
			flag = "0";
		}
		doAdjustBalance.setFlag(Short.valueOf(flag));
		doAdjustBalance.setRtner((short) 1);

		SBalance sBalance = new SBalance();
		// sBalance.setBookItem(100000001);
		if (isNotEmpty(balance)) {
			sBalance.setAmount(Long.valueOf(balance));
		}
		sBalance.setPhoneId(ms);
		if (isNotEmpty(validity)) {
			sBalance.setDays(Long.valueOf(validity));
		}
		doAdjustBalance.setSBalance(sBalance);

		AdjustBalanceService adjustBalanceService = new AdjustBalanceService();
		msg = adjustBalanceService.buildSoapMessage(doAdjustBalance);

		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_AdjustBalance");
		return msg;
	}

	private String mapSetPPSRewardCmd_Free_Resource_Adjustment_BSSBroker(MyAppData myAppData, String prmmoney,
			String prmsm, String prmminute, String prmPoint, String freecalltimes, String rbtSong, String rbtMF,
			String smDis, String callDis, String rewTariff, String expire, String flag) {
		String msg;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();

		DoAdjustBalance doAdjustBalance = new DoAdjustBalance();

		SOperInfo sOper = new SOperInfo();
		sOper.setSoNbr(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
		sOper.setBusiCode("1042");
		String channel = smoiIns.getHttpPostValue("channel");
		if (channel.equals("")) {
			sOper.setSoMode("IVR");
		} else {
			sOper.setSoMode(channel);
		}
		sOper.setSourceSystem("INGW");
		sOper.setRemark(sgw);
		Date date = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (DatatypeConfigurationException e) {
			Log.e("Error Massage:" + e.getMessage(), e);
		}
		sOper.setSoDate(gc);
		sOper.setPhoneId(ms);

		doAdjustBalance.setSOper(sOper);
		// doAdjustBalance.setSiteId(smoiIns.getScp());

		// 4:adjust free resource
		// 5: adjust free resource and validity
		doAdjustBalance.setAdjustType((short) 4);
		doAdjustBalance.setPhoneId(ms);
		if ("".equals(flag)) {
			flag = "0";
		}
		doAdjustBalance.setFlag(Short.valueOf(flag));
		doAdjustBalance.setRtner((short) 1);

		FreeResourceList freeResourceList = new FreeResourceList();
		List<FreeResource> freeResource = new ArrayList<FreeResource>();
		FreeResource free = new FreeResource();

		// --------------------------------------------------------------------
//		XMLGregorianCalendar gcValid = null;
		XMLGregorianCalendar gcExpire = null;

		if (isNotEmpty(expire)) {
			// String soapValid = "";
			String soapExpire = "";
			Integer tmpExpire = Integer.parseInt(expire) - 1;
			Date nextDate = new Date();
			nextDate.setTime(nextDate.getTime() + (86400000 * tmpExpire));
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			soapExpire = dt1.format(nextDate) + "T23:59:59";
			// soapValid = dt1.format(date)+"T23:59:59";

			dt1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

			try {
				// Date dValid = dt1.parse(soapValid);
				// gregory.setTime(dValid);
				/// gcValid =
				// DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);

				Date dExpire = dt1.parse(soapExpire);
				gregory.setTime(dExpire);
				gcExpire = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
			} catch (Exception e) {
				Log.e("Error Massage:" + e.getMessage(), e);
			}
		}
		// --------------------------------------------------------------------

		if (isNotEmpty(prmmoney)) {
			free = new FreeResource();
			free.setPhoneId(ms);
			free.setResourceId(Long.valueOf(smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMMONEY).get(0)));
			free.setResourceRequest(Long.valueOf(prmmoney));
			free.setResourceUnit("Satang");
			if (null != gcExpire) {
				free.setExpireDate(gcExpire);
			}
			freeResource.add(free);
		} else if (isNotEmpty(prmminute)) {
			free = new FreeResource();
			free.setPhoneId(ms);
			free.setResourceId(Long.valueOf(smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMMINUTE).get(0)));
			free.setResourceRequest(Long.valueOf(prmminute));
			free.setResourceUnit("Second");
			if (null != gcExpire) {
				free.setExpireDate(gcExpire);
			}
			freeResource.add(free);
		} else if (isNotEmpty(prmsm)) {
			free = new FreeResource();
			free.setPhoneId(ms);
			free.setResourceId(Long.valueOf(smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMSM).get(0)));
			free.setResourceRequest(Long.valueOf(prmsm));
			free.setResourceUnit("Piece");
			if (null != gcExpire) {
				free.setExpireDate(gcExpire);
			}
			freeResource.add(free);
		}

		freeResourceList.getFreeResourceListItem().addAll(freeResource);
		doAdjustBalance.setFreeResourceList(freeResourceList);

		AdjustBalanceService adjustBalanceService = new AdjustBalanceService();
		msg = adjustBalanceService.buildSoapMessage(doAdjustBalance);

		this.setEState(AFState.W_BSSbroker);
		smoiIns.setMapCmd("do_AdjustBalance");
		return msg;
	}
	
	private String mapActivatePPSSingSubAVATAR(MyAppData myAppData) {
		
		String msg = "";
		String username = smoi_conf.get(Conf.md_Login_User).get(0);
		String password = smoi_conf.get(Conf.md_Login_Password).get(0);
		
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String ssid = smoiIns.getSsid();
		String ms = smoiIns.getMsisdn();
		
		StringBuilder builder = new StringBuilder();
	    builder.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ins=\"http://soa.comptel.com/2011/02/instantlink\">");
	    builder.append("<soap:Header>");
	    builder.append("<wsse:Security soap:mustUnderstand=\"true\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">");
	    builder.append("<wsse:UsernameToken wsu:Id=\"SOAI_req_SOAI\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">");
	    builder.append("<wsse:Username>"+username+"</wsse:Username> ");
	    builder.append("<wsse:Password>"+password+"</wsse:Password> ");
	    builder.append("</wsse:UsernameToken>");
	    builder.append("</wsse:Security>");
	    builder.append("</soap:Header>");
	    builder.append("<soap:Body>");
	    builder.append("<ins:CreateRequest>");
	    builder.append("<ins:RequestHeader>");
	    builder.append("<ins:NeType>DUMMY</ins:NeType>");
	    builder.append("<ins:OrderNo>"+ssid+"</ins:OrderNo>");
	    builder.append("<ins:Priority>5</ins:Priority>");
	    builder.append("<ins:ReqUser>INGW-SMOI</ins:ReqUser>");
	    builder.append("</ins:RequestHeader>");
	    builder.append("<ins:RequestParameters>");
	    builder.append("<ins:Parameter name=\"NO_OF_BSO\" value=\"1\"/>");
	    builder.append("<ins:Parameter name=\"BSO_1\" value=\"CBS_SUBSTATUS\"/>");
	    builder.append("<ins:Parameter name=\"MSG_SEQ_1\" value=\""+ssid+"\"/>");
	    builder.append("<ins:Parameter name=\"REQ_TYPE_1\" value=\"2\"/>");
	    builder.append("<ins:Parameter name=\"ERROR_FLAG_1\" value=\"0\"/>");
	    builder.append("<ins:Parameter name=\"MOD_TYPE_1\" value=\"1\"/>");
	    builder.append("<ins:Parameter name=\"ROLLBACK\" value=\"2\"/>");
	    builder.append("<ins:Parameter name=\"RE_TRANSMIT\" value=\"0\"/>");
	    builder.append("<ins:Parameter name=\"MSISDN1\" value=\""+ms+"\"/>");
	    builder.append("</ins:RequestParameters>");
	    builder.append("</ins:CreateRequest>");
		builder.append("</soap:Body>");
		builder.append("</soap:Envelope>");
		
		msg = builder.toString();
		this.setEState(AFState.W_MD);
		return msg;
	}
	
private String mapModiPPSCreditLimitToMD(MyAppData myAppData) {
		
		String msg = "";
		String un = smoi_conf.get(Conf.md_Login_User).get(0);
		String pw = smoi_conf.get(Conf.md_Login_Password).get(0);
		
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String ssid = smoiIns.getSsid();
		String ms = smoiIns.getMsisdn();
		String increment = smoiIns.getHttpPostValue("increment");
		StringBuilder builder = new StringBuilder();
	    builder.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ins=\"http://soa.comptel.com/2011/02/instantlink\">");
	    builder.append("<soap:Header>");
	    builder.append("<wsse:Security soap:mustUnderstand=\"true\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">");
	    builder.append("<wsse:UsernameToken wsu:Id=\"SOAI_req_SOAI\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">");
	    builder.append("<wsse:Username>"+un+"</wsse:Username> ");
	    builder.append("<wsse:Password>"+pw+"</wsse:Password> ");
	    builder.append("</wsse:UsernameToken>");
	    builder.append("</wsse:Security>");
	    builder.append("</soap:Header>");
	    builder.append("<soap:Body>");
	    builder.append("<ins:CreateRequest>");
	    builder.append("<ins:RequestHeader>");
	    builder.append("<ins:NeType>DUMMY</ins:NeType>");
	    builder.append("<ins:OrderNo>"+ssid+"</ins:OrderNo>");
	    builder.append("<ins:Priority>5</ins:Priority>");
	    builder.append("<ins:ReqUser>INGW-SMOI</ins:ReqUser>");
	    builder.append("</ins:RequestHeader>");
	    builder.append("<ins:RequestParameters>");
	    builder.append("<ins:Parameter name=\"NO_OF_BSO\" value=\"1\"/>");
	    builder.append("<ins:Parameter name=\"BSO_1\" value=\"CBS_NEGATIVE\"/>");
	    builder.append("<ins:Parameter name=\"MSG_SEQ_1\" value=\""+ssid+"\"/>");
	    builder.append("<ins:Parameter name=\"REQ_TYPE_1\" value=\"2\"/>");
	    builder.append("<ins:Parameter name=\"ERROR_FLAG_1\" value=\"0\"/>");
	    builder.append("<ins:Parameter name=\"ROLLBACK\" value=\"2\"/>");
	    builder.append("<ins:Parameter name=\"RE_TRANSMIT\" value=\"0\"/>");
	    builder.append("<ins:Parameter name=\"MSISDN1\" value=\""+ms+"\"/>");
	    builder.append("<ins:Parameter name=\"NEGATIVE_BALANCE_1\" value=\""+increment+"\"/>");
	    builder.append("</ins:RequestParameters>");
	    builder.append("</ins:CreateRequest>");
		builder.append("</soap:Body>");
		builder.append("</soap:Envelope>");
		
		msg = builder.toString();
		this.setEState(AFState.W_MD);
		return msg;
	}

	private String mapModiPPSCreditLimitToEQL(MyAppData myAppData) {
		String msg = "";
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
		String ssid = smoiIns.getSsid();
		smoiIns.setAdjustType(AdjustType.QUERY);
		EqlBsoAdjustCbsValidity<Object> eqlmessage = new EqlBsoAdjustCbsValidity<Object>();
		eqlmessage.setBwoid(SMOIUtils.createSmoiEQUULEUS(ssid));
		eqlmessage.setRetransmit("0");
		eqlmessage.setUser("INGW-SMOI");
		// set BSO for Query
		BSONOQuery bsonoQuery = new BSONOQuery();
		bsonoQuery.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
		bsonoQuery.setBso("QUERY_CBS_SUB");
		bsonoQuery.setReqType("4");
		bsonoQuery.setQueryType("8");
		bsonoQuery.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
		bsonoQuery.setMsisdn1(ms);
		eqlmessage.getBSONOListItem().add(bsonoQuery);
		
		Gson gson = new Gson();
		msg = gson.toJson(eqlmessage);
		return msg;
	}

	private String mapSetPPSRewardAVATAR(MyAppData myAppData) {
		String msg = "";
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String sgw = smoiIns.getSgw();
		String ms = smoiIns.getMsisdn();
		String ssid = smoiIns.getSsid();
		String flag = smoiIns.getFlag();
		// select sub command of setPPSReward
		
		String balance = smoiIns.getHttpPostValue("balance");
        String validity = smoiIns.getHttpPostValue("validity");
        String prmmoney = smoiIns.getHttpPostValue("prmMoney");
        String prmsm = smoiIns.getHttpPostValue("prmSM");
        String prmminute = smoiIns.getHttpPostValue("prmMinute");
        String prmPoint = smoiIns.getHttpPostValue("prmPoint");
        String freecalltimes = smoiIns.getHttpPostValue("freeCallTimes");
        String rbtSong = smoiIns.getHttpPostValue("rbtSong");
        String rbtMF = smoiIns.getHttpPostValue("rbtMF");
        String smDis = smoiIns.getHttpPostValue("smDis");
        String callDis = smoiIns.getHttpPostValue("callDis");
        String rewTariff = smoiIns.getHttpPostValue("rewTariff");
//        String expire = smoiIns.getHttpPostValue("expire");
        String freeresourcesID = smoiIns.getHttpPostValue("freeresourcesID");
        //String flag = smoiIns.getHttpPostValue("flag");
		
		
		if(!prmPoint.trim().equals("") || !freecalltimes.trim().equals("")  || !rbtMF.trim().equals("")   
	 			|| !smDis.trim().equals("")  || !callDis.trim().equals("")   || !rewTariff.trim().equals("")){
				// parameter not support.
				StringBuilder sb = new StringBuilder("");
	            sb.append("<vcrr>")
	                    .append("<res>").append(EResponseCode.INCOMPLETE_DATA.getCode()).append("</res>")
	                    .append("<desc>").append(EResponseCode.INCOMPLETE_DATA.getDesc()).append("</desc>")
	                    .append("</vcrr>");
	            this.setEState(AFState.IDLE);
	            msg = sb.toString();
		}
		else{
			boolean isFreeType = false;
			boolean isBalanceType = false;
			boolean isValidityType = false;
			boolean isFreeTypePrmMoney = false;
			boolean isFreeTypePrmSm = false;
			boolean isFreeTypePrmMinute = false;
			boolean isFreeTypeRbtSong = false;
			
			// check free parameter is not null
			if(!prmmoney.trim().equals("") || !prmsm.trim().equals("") 
				|| !prmminute.trim().equals("") || !rbtSong.trim().equals("") ){
				
				isFreeType = true;
				
				if(prmmoney != null && !prmmoney.trim().equals("")){
					isFreeTypePrmMoney = true;
				}
				if(prmsm != null && !prmsm.trim().equals("")){
					isFreeTypePrmSm = true;
				}
				if(prmminute != null && !prmminute.trim().equals("")){
					isFreeTypePrmMinute = true;
				}
				if(rbtSong != null && !rbtSong.trim().equals("")){
					isFreeTypeRbtSong = true;
				}
			}

			// check balance parameter is not null
			if( !balance.trim().equals("")) {
				isBalanceType = true;
			}
			
			// check validity parameter is not null
			if(!validity.trim().equals("")){
				isValidityType = true;
			}
			
			
			if((isBalanceType && isFreeType) || (isFreeType && isValidityType)){
			    // no adjust type
				StringBuilder sb = new StringBuilder("");
	            sb.append("<vcrr>")
	                    .append("<res>").append(EResponseCode.INCOMPLETE_DATA.getCode()).append("</res>")
	                    .append("<desc>").append(EResponseCode.INCOMPLETE_DATA.getDesc()).append("</desc>")
	                    .append("</vcrr>");
	            this.setEState(AFState.IDLE);
	            msg = sb.toString();
			}else{			
				// gen BWO Header
				EqlBsoAdjustCbs<Object> adjustCbs = new EqlBsoAdjustCbs<Object>();
	 			adjustCbs.setBwoid(SMOIUtils.createSmoiEQUULEUS(ssid));
	 			adjustCbs.setRetransmit("0");
	 			adjustCbs.setUser("INGW-SMOI");
	 			
				///// generate /////
				if(isBalanceType && isValidityType){
					// adj balance + validity 
					if(flag.equals("1")){
						StringBuilder sb = new StringBuilder("");
			            sb.append("<vcrr>")
			                    .append("<res>").append(EResponseCode.INCOMPLETE_DATA.getCode()).append("</res>")
			                    .append("<desc>").append(EResponseCode.INCOMPLETE_DATA.getDesc()).append("</desc>")
			                    .append("</vcrr>");
			            this.setEState(AFState.IDLE);
			            msg = sb.toString();
					}else{
						smoiIns.setAdjustType(AdjustType.BALANCE_AND_VALIDITY); //for tag type adjustment.
						ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
	 					
						// gen BWO Header
						EqlBsoAdjustCbsBalanceAndValidity adjustCbsBalAndVal = new EqlBsoAdjustCbsBalanceAndValidity();
	 					adjustCbsBalAndVal.setBwoid(SMOIUtils.createSmoiEQUULEUS(ssid));
	 					adjustCbsBalAndVal.setRetransmit("0");
	 					adjustCbsBalAndVal.setUser("INGW-SMOI");
	 					
	 					
	 					BSONOBalanceAndValidity bsonoBalAndVal = new BSONOBalanceAndValidity();
	 					bsonoBalAndVal.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
	 					bsonoBalAndVal.setBso("ADJUST_CBS_BALANCE_VALIDITY");
	 					bsonoBalAndVal.setReqType("2");
	 					bsonoBalAndVal.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
	 					bsonoBalAndVal.setMsisdn1(ms);
	 					bsonoBalAndVal.setOperType("1");
	 					
	 					AdjustmentinfolistBalanceAndValidity adjustmentinfolistBalAndVal = new AdjustmentinfolistBalanceAndValidity();
//	 					adjustmentinfolistBalAndVal.setBalanceType("MAIN_ACCOUNT");
	 					adjustmentinfolistBalAndVal.setBalanceType(smoi_conf.get(Conf.mainBalanceType).get(0));
	 					
	 					if (flag.equals("0")) {
	 						if(Integer.parseInt(balance)>=0){
	 							adjustmentinfolistBalAndVal.setAdjustmentType("1");	
	 						}else{
	 							adjustmentinfolistBalAndVal.setAdjustmentType("2");
	 						}
	 					} 
	 					
	 					adjustmentinfolistBalAndVal.setAdjustmentAmout(String.valueOf(Math.abs(Long.parseLong(balance))));

	 					bsonoBalAndVal.getAdjustmentinfolistBalanceAndValidityListItem().add(adjustmentinfolistBalAndVal);
	 					bsonoBalAndVal.setInsufficienFlag("1");
	 					bsonoBalAndVal.setDays(validity);
	 					
	 					//save data to instance
	 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
	 					eqlRequestInstance.setBsoName(bsonoBalAndVal.getBso());
	 					eqlRequestInstance.setBsoId(bsonoBalAndVal.getBsoid());
	 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
	 					//
	 					adjustCbsBalAndVal.getBSONOBalanceAndValidityListItem().add(bsonoBalAndVal);
	 					Gson gson = new Gson();
	 					msg = gson.toJson(adjustCbsBalAndVal);
	 					
	 					this.setEState(AFState.W_EQL);
					}

				} else if( isBalanceType){
					// adj balance
					
		 			smoiIns.setAdjustType(AdjustType.BALANCE); //for tag type adjustment.
					ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
 					
 					BSONOBalanceAndFree bsonoBalance = new BSONOBalanceAndFree();
 					bsonoBalance.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
 					bsonoBalance.setBso("ADJUST_CBS_BALANCE");
 					bsonoBalance.setReqType("2");
 					bsonoBalance.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
 					bsonoBalance.setMsisdn1(ms);
 					
 					AdjustmentinfolistBalance adjustmentinfolistBalance = new AdjustmentinfolistBalance();
// 					adjustmentinfolistBalance.setBalanceType("MAIN_ACCOUNT");
 					adjustmentinfolistBalance.setBalanceType(smoi_conf.get(Conf.mainBalanceType).get(0));

 					
 					if (flag.equals("1")) {
 						bsonoBalance.setOperType("2S");
 					}else if (flag.equals("0")) {
 						bsonoBalance.setOperType("1");
 						
 						if(Integer.parseInt(balance)>=0){
 							adjustmentinfolistBalance.setAdjustmentType("1");	
 						}else{
 							adjustmentinfolistBalance.setAdjustmentType("2");
 						}
 					}
 					
 					adjustmentinfolistBalance.setAdjustmentAmout(String.valueOf(Math.abs(Integer.parseInt(balance))));
 					adjustmentinfolistBalance.setCurrencyName("THB");
 								
 					bsonoBalance.getAdjustmentinfolistBalanceListItem().add(adjustmentinfolistBalance);
 					bsonoBalance.setInsufficientFlag("1");
 					
 					//save data to instance
 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
 					eqlRequestInstance.setBsoName(bsonoBalance.getBso());
 					eqlRequestInstance.setBsoId(bsonoBalance.getBsoid());
 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
 					//
 					adjustCbs.getBSONOListItem().add(bsonoBalance);
 					Gson gson = new Gson();
 					msg = gson.toJson(adjustCbs);
 					
 					this.setEState(AFState.W_EQL);
 					
				} else if( isValidityType){
					// adj validity
				    smoiIns.setAdjustType(AdjustType.VALIDITY); //for tag type adjustment.
				    ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
				    
				 // gen BWO Header
					EqlBsoAdjustCbsValidity<Object> eqlmessage = new EqlBsoAdjustCbsValidity<Object>();
					eqlmessage.setBwoid(SMOIUtils.createSmoiEQUULEUS(ssid));
					eqlmessage.setRetransmit("0");
					eqlmessage.setUser("INGW-SMOI");
					
					// set BSO for Validity
					BSONO bsono = new BSONO();
					bsono.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
					bsono.setBso("ADJUST_CBS_VALIDITY");
					bsono.setReqType("2");
					bsono.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
					bsono.setMsisdn1(ms);
					if (flag.equals("0")) {
						bsono.setOperType("1");
					} else if (flag.equals("1")) {
						bsono.setOperType("2");
					}
					bsono.setDays(validity);
					
					//save data to instance
 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
 					eqlRequestInstance.setBsoName(bsono.getBso());
 					eqlRequestInstance.setBsoId(bsono.getBsoid());
 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
 					//
					eqlmessage.getBSONOListItem().add(bsono);
//					eqlmessage.getBSONOListItem().add(bsonoQuery);
					
					Gson gson = new Gson();
					msg = gson.toJson(eqlmessage);
					
					this.setEState(AFState.W_EQL);
					
				}else if(isFreeType){
		 			
		 			if( isFreeTypePrmMoney){
						// adj free prmMoney

							smoiIns.setAdjustType(AdjustType.BALANCE); //for tag type adjustment.
				 			ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
				 			BSONOBalanceAndFree bsonoBalance = new BSONOBalanceAndFree();
				 			bsonoBalance.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
				 			bsonoBalance.setBso("ADJUST_CBS_BALANCE");
				 			bsonoBalance.setReqType("2");
				 			bsonoBalance.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
				 			bsonoBalance.setMsisdn1(ms);
				 			
				 			AdjustmentinfolistBalance adjustmentinfolistBalance = new AdjustmentinfolistBalance();
				 			adjustmentinfolistBalance.setBalanceType(smoi_conf.get(Conf.BALANCETYPE_PRMMONEY).get(0));
				 			
				 			if (flag.equals("0")) {
				 				bsonoBalance.setOperType("1");
				 				if(Integer.parseInt(prmmoney)>=0){
				 					adjustmentinfolistBalance.setAdjustmentType("1");	
				 				}else{
				 					adjustmentinfolistBalance.setAdjustmentType("2");
				 				}
				 			} else if (flag.equals("1")) {
				 				bsonoBalance.setOperType("2S");
				 				adjustmentinfolistBalance.setBalanceId(freeresourcesID);
				 			}
				 			
				 			adjustmentinfolistBalance.setAdjustmentAmout(String.valueOf(Math.abs(Long.parseLong(prmmoney))));
				 			adjustmentinfolistBalance.setCurrencyName("THB");
				 			
				 			bsonoBalance.getAdjustmentinfolistBalanceListItem().add(adjustmentinfolistBalance);
				 			bsonoBalance.setInsufficientFlag("1");
				 			
				 			//save data to instance
		 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
		 					eqlRequestInstance.setBsoName(bsonoBalance.getBso());
		 					eqlRequestInstance.setBsoId(bsonoBalance.getBsoid());
		 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
		 					//
				 			
				 			adjustCbs.getBSONOListItem().add(bsonoBalance);
				 			Gson gson = new Gson();
				 			msg = gson.toJson(adjustCbs);
				 			
				 			this.setEState(AFState.W_EQL);
//						}
						
					}
		 			
		 			if(isFreeTypePrmSm || isFreeTypePrmMinute || isFreeTypeRbtSong) {
			 			// gen BSONO Free
						BSONOBalanceAndFree bsonoFreeUnit = new BSONOBalanceAndFree();
			 			bsonoFreeUnit.setBsoid(SMOIUtils.createSmoiBSOID(sgw, ssid));
			 			bsonoFreeUnit.setBso("ADJUST_CBS_FREEUNIT");
			 			bsonoFreeUnit.setMsgSeq(SMOIUtils.createSmoiEQUULEUS(ssid));
			 			bsonoFreeUnit.setMsisdn1(ms);
			 			bsonoFreeUnit.setReqType("2");
			 			bsonoFreeUnit.setInsufficientFlag("1");	
		 			
		 			
		 			if( isFreeTypePrmSm){
						// adj Free	prmsm			
		 					if(AdjustType.BALANCE.equals(smoiIns.getAdjustType())){
		 						smoiIns.setAdjustType(AdjustType.PRMMONEY_AND_FREEUNIT); //for tag type adjustment.
		 					}else{
		 						smoiIns.setAdjustType(AdjustType.FREEUNIT); //for tag type adjustment.
		 					}
							
				 			ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
				 			FreeUnitAdjInfo freeUnitAdjInfo = new FreeUnitAdjInfo();
				 
				 			if (flag.equals("0")) {
				 				bsonoFreeUnit.setOperType("1");
				 				if(Integer.parseInt(prmsm) >= 0){
					 				  freeUnitAdjInfo.setAdjType("1");	
					 			  }else{
					 				  freeUnitAdjInfo.setAdjType("2");
					 			  }
				 			}
				 			
//				 			else if (flag.equals("1")) {
//				 				bsonoFreeUnit.setOperType("2S");
//				 				freeUnitAdjInfo.setFreeUnitInstanceId(freeresourcesID);
//				 			}
				 			
				 			freeUnitAdjInfo.setFreeUnitId(smoi_conf.get(Conf.freeUnitId_PRMSM).get(0));
				 			freeUnitAdjInfo.setAdjAmount(String.valueOf(Math.abs(Integer.parseInt(prmsm))));
				 			
				 			bsonoFreeUnit.getFreeUnitAdjInfoListItem().add(freeUnitAdjInfo);
				 			
//				 			adjustCbs.getBSONOListItem().add(bsonoFreeUnit);	
//				 			Gson gson = new Gson();
//				 			msg = gson.toJson(adjustCbs);
//				 			
//				 			this.setEState(AFState.W_EQL);
//						}
			 			
					}
					
					if( isFreeTypePrmMinute){
						// adj Free prmminute
							if(AdjustType.BALANCE.equals(smoiIns.getAdjustType()) ){
								smoiIns.setAdjustType(AdjustType.PRMMONEY_AND_FREEUNIT); //for tag type adjustment.
							}else if(!AdjustType.PRMMONEY_AND_FREEUNIT.equals(smoiIns.getAdjustType())){
								smoiIns.setAdjustType(AdjustType.FREEUNIT); //for tag type adjustment.
							}
				 			ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
				 			FreeUnitAdjInfo freeUnitAdjInfo = new FreeUnitAdjInfo();
				 			if (flag.equals("0")) {
				 				bsonoFreeUnit.setOperType("1");
				 				if(Integer.parseInt(prmminute) >= 0){
					 				  freeUnitAdjInfo.setAdjType("1");	
					 			  }else{
					 				  freeUnitAdjInfo.setAdjType("2");
					 			  }
				 			} else if (flag.equals("1")) {
				 				bsonoFreeUnit.setOperType("2S");
				 				freeUnitAdjInfo.setFreeUnitInstanceId(freeresourcesID);
				 			}
				 			
				 			freeUnitAdjInfo.setFreeUnitId(smoi_conf.get(Conf.freeUnitId_PRMMINUTE).get(0));
				 			freeUnitAdjInfo.setAdjAmount(String.valueOf(Math.abs(Integer.parseInt(prmminute))));				 							 		
				 			
				 			bsonoFreeUnit.getFreeUnitAdjInfoListItem().add(freeUnitAdjInfo);
////				 			if(bsonoFreeUnit.getFreeunitadjustmentinfolist() == null){
//				 				adjustCbs.getBSONOListItem().add(bsonoFreeUnit);	
////				 			}
//				 			Gson gson = new Gson();
//				 			msg = gson.toJson(adjustCbs);
//				 			
//				 			this.setEState(AFState.W_EQL);
////						}
						
					}
					
					if( isFreeTypeRbtSong ){
						// adj Free freerbtsong

							if(AdjustType.BALANCE.equals(smoiIns.getAdjustType())){
								smoiIns.setAdjustType(AdjustType.PRMMONEY_AND_FREEUNIT); //for tag type adjustment.
							}else if(!AdjustType.PRMMONEY_AND_FREEUNIT.equals(smoiIns.getAdjustType())){
	 							smoiIns.setAdjustType(AdjustType.FREEUNIT); //for tag type adjustment.
	 						}
							
				 			ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN2_0d_8digit);
				 			FreeUnitAdjInfo freeUnitAdjInfo = new FreeUnitAdjInfo();
				 			if (flag.equals("0")) {
				 				bsonoFreeUnit.setOperType("1");
				 				if(Integer.parseInt(rbtSong) >= 0){
					 				  freeUnitAdjInfo.setAdjType("1");	
					 			  }else{
					 				  freeUnitAdjInfo.setAdjType("2");
					 			  }
				 			} else if (flag.equals("1")) {
				 				bsonoFreeUnit.setOperType("2S");
				 				freeUnitAdjInfo.setFreeUnitInstanceId(freeresourcesID);
				 			}
				 			
				 			freeUnitAdjInfo.setFreeUnitId(smoi_conf.get(Conf.freeUnitId_FREERBTSONG).get(0));
				 			freeUnitAdjInfo.setAdjAmount(String.valueOf(Math.abs(Integer.parseInt(rbtSong))));
				 			
				 			bsonoFreeUnit.getFreeUnitAdjInfoListItem().add(freeUnitAdjInfo);
////				 			if(bsonoFreeUnit.getFreeunitadjustmentinfolist() == null){
//				 				adjustCbs.getBSONOListItem().add(bsonoFreeUnit);	
////				 			}
//				 			Gson gson = new Gson();
//				 			msg = gson.toJson(adjustCbs);
//				 			
//				 			this.setEState(AFState.W_EQL);
//						}
					}
					
					//save data to instance
 					EQLRequestInstance eqlRequestInstance = new EQLRequestInstance();
 					eqlRequestInstance.setBsoName(bsonoFreeUnit.getBso());
 					eqlRequestInstance.setBsoId(bsonoFreeUnit.getBsoid());
 					smoiIns.getEqlRequestInstance().add(eqlRequestInstance);
 					//
 					
					adjustCbs.getBSONOListItem().add(bsonoFreeUnit);	
		 			Gson gson = new Gson();
		 			msg = gson.toJson(adjustCbs);
		 			
		 			this.setEState(AFState.W_EQL);
		 		}
		     }
				
			}
		}
		
		return msg;
	}

	private String mapModiPPSCreditLimitAVATAR(MyAppData myAppData) {
		
		String msg = "";
		if(this.EState != null &&  this.EState.equals(AFState.W_MD)){
			msg = mapModiPPSCreditLimitToMD(myAppData);
			this.setEState(AFState.W_MD);
		}else{
			msg = mapModiPPSCreditLimitToEQL(myAppData);
			this.setEState(AFState.W_EQL);
		}
		return msg;
	}
	
	private boolean isNotEmpty(String input) {
		return null != input && !"".equals(input.trim()) ? true : false;
	}
}
