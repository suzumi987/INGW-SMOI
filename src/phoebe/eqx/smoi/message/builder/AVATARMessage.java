/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.message.builder;

import ec02.af.abstracts.AbstractAF;
import phoebe.eqx.main.EquinoxMessageBuilder;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.utils.DateTime;
import phoebe.eqx.smoi.utils.Msisdn;
import phoebe.eqx.smoi.utils.SMOIUtils;
import smoi.message.OctetString;

import java.util.*;

/**
 *
 * @author pavarisa
 */
public class AVATARMessage {

    public static String mappingMessage(AbstractAF af, MyAppData myAppData) {
        String msg = "";
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String page = smoiIns.getPage();
        if (page.equals(ECommand.dispPPSInfo.getCommand())) {//Send to AVATAR
            msg = mapDispPPSInfoCmd(af, myAppData);
        } /*else if (page.equals(ECommand.dispPPSPkgrew.getCommand())) {//Send to BOS DCC
            msg = mapDispPPSPkgrewCmd(af, myAppData);
        } else if (page.equals(ECommand.modiPPSValidity.getCommand())) {//Send to BOS DCC
            msg = mapModiPPSValidityCmd(af, myAppData);
        } else if (page.equals(ECommand.modiPPSMultiAttr.getCommand())) {
            msg = mapModiPPSMultiAttr(af, smoiIns, myAppData);
        } else if (page.equals(ECommand.setPPSReward.getCommand())) {
            msg = mapSetPPSReward(af, smoiIns, myAppData);
        }*/

        return msg;

    }

    /*public static String mapSetPPSReward(AbstractAF af, SmoiInstance smoiIns, MyAppData myAppData) {
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

        if ((isNotEmpty(balance) || isNotEmpty(validity))
                && (!isNotEmpty(prmmoney) || !isNotEmpty(prmsm) || !isNotEmpty(prmminute) || !isNotEmpty(prmPoint)
                || !isNotEmpty(freecalltimes) || !isNotEmpty(rbtSong) || !isNotEmpty(rbtMF) || !isNotEmpty(smDis)
                || !isNotEmpty(callDis) || !isNotEmpty(rewTariff) || !isNotEmpty(expire))) {

            msg = mapSetPPSRewardCmd_Balance_and_Validity(af, myAppData, balance, validity);

        } else if (!isNotEmpty(balance)
                && (isNotEmpty(prmmoney) || isNotEmpty(prmsm) || isNotEmpty(prmminute) || isNotEmpty(prmPoint)
                || isNotEmpty(freecalltimes) || isNotEmpty(rbtSong) || isNotEmpty(rbtMF) || isNotEmpty(smDis)
                || isNotEmpty(callDis) || isNotEmpty(rewTariff) || isNotEmpty(expire))) {

            msg = mapSetPPSRewardCmd_Free_Resource_Adjustment(af, myAppData, prmmoney, prmsm, prmminute, prmPoint, freecalltimes, rbtSong, rbtMF, smDis, callDis, rewTariff, expire, flag);
        }
        return msg;
    }

    public static String mapModiPPSMultiAttr(AbstractAF af, SmoiInstance smoiIns, MyAppData myAppData) {
        String msg = "";
        //select sub command  of modiPPSMultiAttr
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
        String merchant = smoiIns.getHttpPostValue("merchant");
        String service = smoiIns.getHttpPostValue("service");
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
            //balance or validity  is not null
            // and prmmoney, prmsm, prmminute, point, freecalltimes, freerbtsong
            //,freerbtmf, score, prmscore, smusage parameter is null
            msg = mapModiPPSMultiAttrCmd_Modify_Balance_and_Validity(af, myAppData, balance, validity, merchant, service);//Send to BOS DCC
        } else if ((balance == null || balance.trim().equals(""))
                && ((prmmoney != null && !prmmoney.trim().equals(""))
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
            // balance  is null
            // and prmmoney, prmsm, prmminute, point, freecalltimes, freerbtsong
            //, freerbtmf, score, prmscore, smusage parameter one is not null
            msg = mapModiPPSMultiAttrCmd_Modify_Free_Resource_Adjustment(af, myAppData, prmmoney, prmsm, prmminute, point, freecalltimes, freerbtsong, freerbtmf, score, prmscore, smusage, merchant, service);
        }
        //BOS BSS Broker
        return msg;
    }

    private static String mapSetPPSRewardCmd_Balance_and_Validity(AbstractAF af, MyAppData myAppData, String balance, String validity) {
        String msg;
        msg = AVATARMessage.buildSetPPSRewardCmd_Balance_and_Validity(af, myAppData, balance, validity);
        return msg;
    }

    private static String mapSetPPSRewardCmd_Free_Resource_Adjustment(AbstractAF af, MyAppData myAppData, String prmmoney, String prmsm, String prmminute, String prmPoint, String freecalltimes, String rbtSong, String rbtMF, String smDis, String callDis, String rewTariff, String expire, String flag) {
        String msg;
        msg = AVATARMessage.buildSetPPSRewardCmd_Free_Resource_Adjustment(af, myAppData, flag, expire, prmmoney, prmsm, prmminute, freecalltimes);
        return msg;
    }

    private static String mapModiPPSMultiAttrCmd_Modify_Balance_and_Validity(AbstractAF af, MyAppData myAppData, String modaccinfoModifyAmount, String modaccinfoValidityAmount, String merchant, String service) {
        String msg;
        msg = AVATARMessage.buildModiPPSMultiAttrCmd_Modify_Balance_and_Validity(af, myAppData, modaccinfoModifyAmount, modaccinfoValidityAmount, merchant, service);
        return msg;
    }

    private static String mapModiPPSMultiAttrCmd_Modify_Free_Resource_Adjustment(AbstractAF af, MyAppData myAppData, String prmmoney, String prmsm, String prmminute, String point, String freecalltimes, String freerbtsong, String freerbtmf, String score, String prmscore, String smusage, String merchant, String service) {
        String msg;
        msg = AVATARMessage.buildModiPPSMultiAttrCmd_Modify_Free_Resource_Adjestment(af, myAppData, merchant, service, prmmoney, prmsm, prmminute, freecalltimes, freerbtsong);
        return msg;
    }*/

    private static String mapDispPPSInfoCmd(AbstractAF af, MyAppData myAppData) {
        String msg;
        msg = AVATARMessage.buildDispPPSInfoCmd(af, myAppData);
        return msg;
    }

    /*private static String mapModiPPSValidityCmd(AbstractAF af, MyAppData myAppData) {
        String msg;
        msg = AVATARMessage.buildModiPPSValidityCmd(af, myAppData);
        return msg;
    }

    private static String mapDispPPSPkgrewCmd(AbstractAF af, MyAppData myAppData) {
        String msg;
        msg = AVATARMessage.buildDispPPSPkgrewCmd(af, myAppData);
        return msg;
    }*/

    public static String buildDispPPSInfoCmd(AbstractAF af, MyAppData myAppData) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String msg;
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String sgw = smoiIns.getSgw();
        String ms = smoiIns.getMsisdn();
        ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN1_66_9digit);
        String ssid = smoiIns.getSsid();
        //String scp = smoiIns.getScp();
        String originHost = smoi_conf.get(Conf.ingwSmoi_OriginHost).get(0);
        String originRealm = smoi_conf.get(Conf.ingwSmoi_OriginRealm).get(0);
        String destinationHost = smoiIns.getDestinationHost();
        String destinationRealm = smoiIns.getDestinationRealm();
        /*String accessMethod = null;
        if (smoi_conf.get(Conf.bos_CCR_Access_Method) != null
                && smoi_conf.get(Conf.bos_CCR_Access_Method).get(0) != null
                && !smoi_conf.get(Conf.bos_CCR_Access_Method).get(0).trim().equals("")) {
            accessMethod = smoi_conf.get(Conf.bos_CCR_Access_Method).get(0);
        }*/
        CCRMessage ccr = new CCRMessage();
        ccr.setSessionid(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
        ccr.setOriginHost(originHost);
        ccr.setOriginRealm(originRealm);
        ccr.setDestinationHost(destinationHost);
        ccr.setDestinationRealm(destinationRealm);
        ccr.setAuthApplicationId("4");
        ccr.setServiceContextId("AccountQuery@ais.co.th");
        ccr.setCcRequestType("4");
        ccr.setCcRequestNumber("0");
        ccr.setEventTimeStamp(String.valueOf(DateTime.getSecondNow()));
        ccr.setSubscriptionIdType("0");
        ccr.setSubscriptionIdData(ms);
        ccr.setRequestAction("0");
        ccr.setServiceIdentifier("1");
/*        ccr.setQueryType("0");
        if (accessMethod != null) {
            ccr.setAccessMethod(accessMethod);
        }*/
        EquinoxMessageBuilder builder = EquinoxMessageBuilder.newInstance();
        builder.setMessage(ccr);
        builder.createXMLMessage();
        msg = builder.getMessage();
        smoiIns.setMapCmd("AccountQuery");
        return msg;
    }

    /*public static String buildModiPPSMultiAttrCmd_Modify_Balance_and_Validity(AbstractAF af, MyAppData myAppData, String modaccinfoModifyAmount, String modaccinfoValidityAmount, String merchant, String service) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String msg;
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String sgw = smoiIns.getSgw();
        String ms = smoiIns.getMsisdn();
        ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN1_66_9digit);
        String ssid = smoiIns.getSsid();
        String originHost = smoi_conf.get(Conf.ingwSmoi_OriginHost).get(0);
        String originRealm = smoi_conf.get(Conf.ingwSmoi_OriginRealm).get(0);
        String destinationHost = myAppData.getSmoiIns().getDestinationHost();
        String destinationRealm = myAppData.getSmoiIns().getDestinationRealm();
        String channelId = "";
        String measureId = "11101";
        String rechargePartner = "";
        CCRMessage ccr = new CCRMessage();
        ccr.setSessionid(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
        ccr.setOriginHost(originHost);
        ccr.setOriginRealm(originRealm);
        //ccr.setDestinationHost(destinationHost);
        ccr.setDestinationHost(destinationHost);
        ccr.setDestinationRealm(destinationRealm);
        ccr.setAuthApplicationId("4");
        ccr.setServiceContextId("AccountAdjustment@ais.co.th");
        ccr.setCcRequestType("4");
        ccr.setCcRequestNumber("0");
        ccr.setEventTimeStamp(String.valueOf(DateTime.getSecondNow()));
        ccr.setRequestAction("0");
        ccr.setSubscriptionIdType("0");
        ccr.setSubscriptionIdData(ms);
        //Service-Information
        ccr.setEtopupSessionId(sgw + "@" + ssid);
        ccr.setModifyType("0");
        //Modify-Account-Info
        if(null==modaccinfoModifyAmount || "".equals(modaccinfoModifyAmount)) modaccinfoModifyAmount = "0";
        ccr.setModaccinfoModifyAmount(modaccinfoModifyAmount);
        ccr.setModaccinfoValidityAmount(modaccinfoValidityAmount);
        ccr.setModaccinfoDeductType("1");
        ccr.setRechargePartnerId(rechargePartner);
        ccr.setServiceId("");
        ccr.setChannelId(channelId);
        //DEC to Octet
        OctetString octet = new OctetString();
        ccr.setTransparentData1(octet.Convert2Octet(merchant));
        ccr.setTransparentData2(octet.Convert2Octet(service));
        ccr.setMeasureId(measureId);
        EquinoxMessageBuilder builder = EquinoxMessageBuilder.newInstance();
        builder.setMessage(ccr);
        builder.createXMLMessage();
        msg = builder.getMessage();
        smoiIns.setMapCmd("AccountAdjustment");
        return msg;
    }

    public static String buildModiPPSMultiAttrCmd_Modify_Free_Resource_Adjestment(AbstractAF af, MyAppData myAppData, String merchant, String service, String prmmoney, String prmsm, String prmminute, String freecalltimes, String freerbtsong) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String msg;
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String sgw = smoiIns.getSgw();
        String ms = smoiIns.getMsisdn();
        ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN1_66_9digit);
        String ssid = smoiIns.getSsid();
        String originHost = smoi_conf.get(Conf.ingwSmoi_OriginHost).get(0);
        String originRealm = smoi_conf.get(Conf.ingwSmoi_OriginRealm).get(0);
        String destinationHost = myAppData.getSmoiIns().getDestinationHost();
        String destinationRealm = myAppData.getSmoiIns().getDestinationRealm();
        *//**
         * <Session-Id (263)>
         * <Origin-Host (264)>
         * <Origin-Realm (296)>
         * <Destination-Host (293)>
         * <Destination-Realm (283)>
         * <Auth-Application-Id (258)>
         * <Service-Context-Id (461)>
         * <CC-Request-Type (416)>
         * <CC-Request-Number (415)>
         * <Event-Timestamp (55)>
         * <Subscription-Id (443)>
         * <Subscription-Id-Type (450)>
         * <Subscription-Id-Data (444)>
         * <Subscription-Id (443)>
         * <Requested-Action (436)>
         * <Service-Information(873)>
         * <IN-Information(20300)>
         * <Etopup-Session-Id(20740)>
         * <Modify-Type(61018)>
         * <Modify-Account-Info(61019)>
         * <Resource-Id(61045)>
         * <Modify-Amount(61022)>
         * <Validity-Amount(61023)>
         * <Deduct-Type(61044)>
         * <Measure-Id(61043)>
         * </Modify-Account-Info(61019)>
         * <Transparent-Data-1(61030)>
         * <Transparent-Data-2(61031)>
         * <Transparent-Data-3(61032)>
         * </IN-Information(20300)>
         * </Service-Information(873)>
         *//*
        StringBuilder msgBuilder = new StringBuilder("");
        //ccrStandard
        LinkedHashMap<String, String> ccrStandard = new LinkedHashMap<String, String>();
        ccrStandard.put("Session-Id", SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
        ccrStandard.put("Origin-Host", originHost);
        ccrStandard.put("Origin-Realm", originRealm);
        //ccrStandard.put("Destination-Host",destinationHost);
        ccrStandard.put("Destination-Host", destinationHost);
        ccrStandard.put("Destination-Realm", destinationRealm);
        ccrStandard.put("Auth-Application-Id", "4");
        ccrStandard.put("Service-Context-Id", "AdjustAsset@ais.co.th");
        ccrStandard.put("CC-Request-Type", "4");
        ccrStandard.put("CC-Request-Number", "0");
        ccrStandard.put("Event-Timestamp", String.valueOf(DateTime.getSecondNow()));
        ccrStandard.put("Requested-Action", "0");
        for (Map.Entry<String, String> entry : ccrStandard.entrySet()) {
            msgBuilder.append("<").append(entry.getKey())
                    .append(" value=\"").append(entry.getValue())
                    .append("\"/>");
        }
        //ccrSubscriptionId
        LinkedHashMap<String, String> ccrSubscriptionId = new LinkedHashMap<String, String>();
        ccrSubscriptionId.put("Subscription-Id-Type", "0");
        ccrSubscriptionId.put("Subscription-Id-Data", ms);
        msgBuilder.append("<Subscription-Id>");
        for (Map.Entry<String, String> entry : ccrSubscriptionId.entrySet()) {
            msgBuilder.append("<").append(entry.getKey())
                    .append(" value=\"").append(entry.getValue())
                    .append("\"/>");
        }
        msgBuilder.append("</Subscription-Id>");
        //ccrServiceInformation
        LinkedHashMap<String, String> ccrServiceInformation = new LinkedHashMap<String, String>();
        ccrServiceInformation.put("Etopup-Session-Id", sgw + "@" + ssid);
        ccrServiceInformation.put("Modify-Type", "0");
        ccrServiceInformation.put("Modify-Type", "0");
        OctetString octet = new OctetString();
        ccrServiceInformation.put("Transparent-Data-1", octet.Convert2Octet(merchant));
        ccrServiceInformation.put("Transparent-Data-2", octet.Convert2Octet(service));
        ccrServiceInformation.put("Transparent-Data-3", "");
        msgBuilder.append("<Service-Information>")
                .append("<IN-Information>");
        for (Map.Entry<String, String> entry : ccrServiceInformation.entrySet()) {
            msgBuilder.append("<").append(entry.getKey())
                    .append(" value=\"").append(entry.getValue())
                    .append("\"/>");
        }
        //ccrModifyAccountInfo
        msgBuilder.append("<Modify-Account-Info>");
        if (prmmoney != null && !prmmoney.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_prmmoney = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_prmmoney.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMMONEY).get(0));
            ccrModifyAccountInfo_prmmoney.put("Modify-Amount", prmmoney);
            ccrModifyAccountInfo_prmmoney.put("Validity-Amount", "");
            ccrModifyAccountInfo_prmmoney.put("Deduct-Type", "1");
            ccrModifyAccountInfo_prmmoney.put("Measure-Id", "11101");

            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_prmmoney.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        if (prmsm != null && !prmsm.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_prmsm = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_prmsm.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMSM).get(0));
            ccrModifyAccountInfo_prmsm.put("Modify-Amount", prmsm);
            ccrModifyAccountInfo_prmsm.put("Validity-Amount", "");
            ccrModifyAccountInfo_prmsm.put("Deduct-Type", "1");
            ccrModifyAccountInfo_prmsm.put("Measure-Id", "109");
            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_prmsm.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        if (prmminute != null && !prmminute.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_prmminute = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_prmminute.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMMINUTE).get(0));
            ccrModifyAccountInfo_prmminute.put("Modify-Amount", prmminute);
            ccrModifyAccountInfo_prmminute.put("Validity-Amount", "");
            ccrModifyAccountInfo_prmminute.put("Deduct-Type", "1");
            ccrModifyAccountInfo_prmminute.put("Measure-Id", "102");
            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_prmminute.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        if (freecalltimes != null && !freecalltimes.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_freecalltimes = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_freecalltimes.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_FREECALLTIMES).get(0));
            ccrModifyAccountInfo_freecalltimes.put("Modify-Amount", freecalltimes);
            ccrModifyAccountInfo_freecalltimes.put("Validity-Amount", "");
            ccrModifyAccountInfo_freecalltimes.put("Deduct-Type", "1");
            ccrModifyAccountInfo_freecalltimes.put("Measure-Id", "109");
            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_freecalltimes.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        if (freerbtsong != null && !freerbtsong.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_freerbtsong = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_freerbtsong.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_FREERBTSONG).get(0));
            ccrModifyAccountInfo_freerbtsong.put("Modify-Amount", freerbtsong);
            ccrModifyAccountInfo_freerbtsong.put("Validity-Amount", "");
            ccrModifyAccountInfo_freerbtsong.put("Deduct-Type", "1");
            ccrModifyAccountInfo_freerbtsong.put("Measure-Id", "109");
            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_freerbtsong.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        msgBuilder.append("</Modify-Account-Info>")
                .append("</IN-Information>")
                .append("</Service-Information>");
        smoiIns.setMapCmd("Free Resource Adjustment");
        msg = msgBuilder.toString();
        return msg;
    }

    public static String buildSetPPSRewardCmd_Balance_and_Validity(AbstractAF af, MyAppData myAppData, String balance, String validity) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String msg;
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String sgw = smoiIns.getSgw();
        String ms = smoiIns.getMsisdn();
        ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN1_66_9digit);
        String ssid = smoiIns.getSsid();
        String originHost = smoi_conf.get(Conf.ingwSmoi_OriginHost).get(0);
        String originRealm = smoi_conf.get(Conf.ingwSmoi_OriginRealm).get(0);
        String destinationHost = myAppData.getSmoiIns().getDestinationHost();
        String destinationRealm = myAppData.getSmoiIns().getDestinationRealm();
        String channelId = "";
        String measureId = "11101";
        String rechargePartner = "";
        CCRMessage ccr = new CCRMessage();
        ccr.setSessionid(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
        ccr.setOriginHost(originHost);
        ccr.setOriginRealm(originRealm);
        //ccr.setDestinationHost(destinationHost);
        ccr.setDestinationHost(destinationHost);
        ccr.setDestinationRealm(destinationRealm);
        ccr.setAuthApplicationId("4");
        ccr.setServiceContextId("AccountAdjustment@ais.co.th");
        ccr.setCcRequestType("4");
        ccr.setCcRequestNumber("0");
        ccr.setEventTimeStamp(String.valueOf(DateTime.getSecondNow()));
        ccr.setRequestAction("0");
        ccr.setSubscriptionIdType("0");
        ccr.setSubscriptionIdData(ms);
        //Service-Information
        ccr.setEtopupSessionId(sgw + "@" + ssid);
        ccr.setModifyType("0");
        //Modify-Account-Info
        if(null==balance || "".equals(balance)) balance = "0";
        ccr.setModaccinfoModifyAmount(balance);
        ccr.setModaccinfoValidityAmount(validity);
        ccr.setModaccinfoDeductType("1");
        ccr.setRechargePartnerId(rechargePartner);
        ccr.setServiceId("");
        ccr.setChannelId(channelId);
        //DEC to Octet
        //ccr.setTransparentData1(this.octet.Convert2Octet(merchant));
        //ccr.setTransparentData2(this.octet.Convert2Octet(service));
        ccr.setMeasureId(measureId);
        EquinoxMessageBuilder builder = EquinoxMessageBuilder.newInstance();
        builder.setMessage(ccr);
        builder.createXMLMessage();
        msg = builder.getMessage();
        smoiIns.setMapCmd("AccountAdjustment");
        return msg;
    }

    public static String buildSetPPSRewardCmd_Free_Resource_Adjustment(AbstractAF af, MyAppData myAppData, String flag, String expire, String prmmoney, String prmsm, String prmminute, String freecalltimes) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String msg;
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String sgw = smoiIns.getSgw();
        String ms = smoiIns.getMsisdn();
        ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN1_66_9digit);
        String ssid = smoiIns.getSsid();
        String originHost = smoi_conf.get(Conf.ingwSmoi_OriginHost).get(0);
        String originRealm = smoi_conf.get(Conf.ingwSmoi_OriginRealm).get(0);
        String destinationHost = myAppData.getSmoiIns().getDestinationHost();
        String destinationRealm = myAppData.getSmoiIns().getDestinationRealm();
        StringBuilder msgBuilder = new StringBuilder("");
        //ccrStandard
        LinkedHashMap<String, String> ccrStandard = new LinkedHashMap<String, String>();
        ccrStandard.put("Session-Id", SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
        ccrStandard.put("Origin-Host", originHost);
        ccrStandard.put("Origin-Realm", originRealm);
        //ccrStandard.put("Destination-Host",destinationHost);
        ccrStandard.put("Destination-Host", destinationHost);
        ccrStandard.put("Destination-Realm", destinationRealm);
        ccrStandard.put("Auth-Application-Id", "4");
        ccrStandard.put("Service-Context-Id", "AdjustAsset@ais.co.th");
        ccrStandard.put("CC-Request-Type", "4");
        ccrStandard.put("CC-Request-Number", "0");
        ccrStandard.put("Event-Timestamp", String.valueOf(DateTime.getSecondNow()));
        ccrStandard.put("Requested-Action", "0");
        for (Iterator<Map.Entry<String, String>> i = ccrStandard.entrySet().iterator(); i.hasNext();) {
            Map.Entry<String, String> entry = i.next();
            msgBuilder.append("<").append(entry.getKey())
                    .append(" value=\"").append(entry.getValue())
                    .append("\"/>");
        }
        //ccrSubscriptionId
        LinkedHashMap<String, String> ccrSubscriptionId = new LinkedHashMap<String, String>();
        ccrSubscriptionId.put("Subscription-Id-Type", "0");
        ccrSubscriptionId.put("Subscription-Id-Data", ms);
        msgBuilder.append("<Subscription-Id>");
        for (Iterator<Map.Entry<String, String>> i = ccrSubscriptionId.entrySet().iterator(); i.hasNext();) {
            Map.Entry<String, String> entry = i.next();
            msgBuilder.append("<").append(entry.getKey())
                    .append(" value=\"").append(entry.getValue())
                    .append("\"/>");
        }
        msgBuilder.append("</Subscription-Id>");
        //ccrServiceInformation
        LinkedHashMap<String, String> ccrServiceInformation = new LinkedHashMap<String, String>();
        ccrServiceInformation.put("Etopup-Session-Id", sgw + "@" + ssid);
        if (isNotEmpty(flag)) {
            ccrServiceInformation.put("Modify-Type", flag);
        } else {
            ccrServiceInformation.put("Modify-Type", "0");
        }
        //ccrServiceInformation.put("Transparent-Data-1",this.octet.Convert2Octet(merchant));
        //ccrServiceInformation.put("Transparent-Data-2",this.octet.Convert2Octet(service));
        ccrServiceInformation.put("Transparent-Data-3", "");
        msgBuilder.append("<Service-Information>")
                .append("<IN-Information>");
        for (Iterator<Map.Entry<String, String>> i = ccrServiceInformation.entrySet().iterator(); i.hasNext();) {
            Map.Entry<String, String> entry = i.next();
            msgBuilder.append("<").append(entry.getKey())
                    .append(" value=\"").append(entry.getValue())
                    .append("\"/>");
        }
        if (!isNotEmpty(expire)) {
            expire = "";
        }
        //ccrModifyAccountInfo
        msgBuilder.append("<Modify-Account-Info>");
        if (prmmoney != null && !prmmoney.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_prmmoney = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_prmmoney.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMMONEY).get(0));
            ccrModifyAccountInfo_prmmoney.put("Modify-Amount", prmmoney);
            ccrModifyAccountInfo_prmmoney.put("Validity-Amount", "");
            ccrModifyAccountInfo_prmmoney.put("Deduct-Type", "1");
            ccrModifyAccountInfo_prmmoney.put("Measure-Id", "11101");

            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_prmmoney.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        if (prmsm != null && !prmsm.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_prmsm = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_prmsm.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMSM).get(0));
            ccrModifyAccountInfo_prmsm.put("Modify-Amount", prmsm);
            ccrModifyAccountInfo_prmsm.put("Validity-Amount", "");
            ccrModifyAccountInfo_prmsm.put("Deduct-Type", "1");
            ccrModifyAccountInfo_prmsm.put("Measure-Id", "109");
            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_prmsm.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        if (prmminute != null && !prmminute.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_prmminute = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_prmminute.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_PRMMINUTE).get(0));
            ccrModifyAccountInfo_prmminute.put("Modify-Amount", prmminute);
            ccrModifyAccountInfo_prmminute.put("Validity-Amount", "");
            ccrModifyAccountInfo_prmminute.put("Deduct-Type", "1");
            ccrModifyAccountInfo_prmminute.put("Measure-Id", "102");
            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_prmminute.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        if (freecalltimes != null && !freecalltimes.equals("")) {
            LinkedHashMap<String, String> ccrModifyAccountInfo_freecalltimes = new LinkedHashMap<String, String>();
            ccrModifyAccountInfo_freecalltimes.put("Resource-Id", smoi_conf.get(Conf.bos_CCR_Resource_Id_FREECALLTIMES).get(0));
            ccrModifyAccountInfo_freecalltimes.put("Modify-Amount", freecalltimes);
            ccrModifyAccountInfo_freecalltimes.put("Validity-Amount", "");
            ccrModifyAccountInfo_freecalltimes.put("Deduct-Type", "1");
            ccrModifyAccountInfo_freecalltimes.put("Measure-Id", "109");
            for (Iterator<Map.Entry<String, String>> i = ccrModifyAccountInfo_freecalltimes.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, String> entry = i.next();
                msgBuilder.append("<").append(entry.getKey())
                        .append(" value=\"").append(entry.getValue())
                        .append("\"/>");
            }
        }
        msgBuilder.append("</Modify-Account-Info>")
                .append("</IN-Information>")
                .append("</Service-Information>");
        msg = msgBuilder.toString();
        smoiIns.setMapCmd("Free Resource Adjustment");
        return msg;
    }

    public static String buildDispPPSPkgrewCmd(AbstractAF af, MyAppData myAppData) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String msg;
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String sgw = smoiIns.getSgw();
        String ms = smoiIns.getMsisdn();
        ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN1_66_9digit);
        String ssid = smoiIns.getSsid();
        //String scp = smoiIns.getScp();
        String originHost = smoi_conf.get(Conf.ingwSmoi_OriginHost).get(0);
        String originRealm = smoi_conf.get(Conf.ingwSmoi_OriginRealm).get(0);
        String destinationHost = myAppData.getSmoiIns().getDestinationHost();
        String destinationRealm = myAppData.getSmoiIns().getDestinationRealm();
        CCRMessage ccr = new CCRMessage();
        ccr.setSessionid(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
        ccr.setOriginHost(originHost);
        ccr.setOriginRealm(originRealm);
        //ccr.setDestinationHost(destinationHost);
        ccr.setDestinationHost(destinationHost);
        ccr.setDestinationRealm(destinationRealm);
        ccr.setAuthApplicationId("4");
        ccr.setServiceContextId("AccountQuery@ais.co.th");
        ccr.setCcRequestType("4");
        ccr.setCcRequestNumber("0");
        ccr.setEventTimeStamp(String.valueOf(DateTime.getSecondNow()));
        ccr.setSubscriptionIdType("0");
        ccr.setSubscriptionIdData(ms);
        ccr.setQueryType("1");
        ccr.setAccessMethod("0");
        EquinoxMessageBuilder builder = EquinoxMessageBuilder.newInstance();
        builder.setMessage(ccr);
        builder.createXMLMessage();
        msg = builder.getMessage();
        smoiIns.setMapCmd("AccountQuery");
        return msg;
    }

    public static String buildModiPPSValidityCmd(AbstractAF af, MyAppData myAppData) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String msg;
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String sgw = smoiIns.getSgw();
        String ms = smoiIns.getMsisdn();
        ms = Msisdn.convert(ms, Msisdn.EMsisdnFormat.PATTERN1_66_9digit);
        String ssid = smoiIns.getSsid();
        //String scp = smoiIns.getScp();
        String originHost = smoi_conf.get(Conf.ingwSmoi_OriginHost).get(0);
        String originRealm = smoi_conf.get(Conf.ingwSmoi_OriginRealm).get(0);
        String destinationHost = myAppData.getSmoiIns().getDestinationHost();
        String destinationRealm = myAppData.getSmoiIns().getDestinationRealm();
        String flag = smoiIns.getHttpPostValue("flag");
        String balance = "0";//fix it. smoiIns.getHttpPostValue("balance");
        //String validity = smoiIns.getHttpPostValue("validity");
        String merchant = smoiIns.getHttpPostValue("merchant");
        String service = smoiIns.getHttpPostValue("service");
        String increment = smoiIns.getHttpPostValue("increment");
        String channelId = "";
        String transparentData1 = merchant;
        String transparentData2 = service;
        String measureId = "11101";
        CCRMessage ccr = new CCRMessage();
        ccr.setSessionid(SMOIUtils.createSmoiSessionIdKey(sgw, ssid));
        ccr.setOriginHost(originHost);
        ccr.setOriginRealm(originRealm);
        //ccr.setDestinationHost(destinationHost);
        ccr.setDestinationHost(destinationHost);
        ccr.setDestinationRealm(destinationRealm);
        ccr.setAuthApplicationId("4");
        ccr.setServiceContextId("AccountAdjustment@ais.co.th");
        ccr.setCcRequestType("4");
        ccr.setCcRequestNumber("0");
        ccr.setEventTimeStamp(String.valueOf(DateTime.getSecondNow()));
        ccr.setRequestAction("0");
        ccr.setSubscriptionIdType("0");
        ccr.setSubscriptionIdData(ms);
        ccr.setEtopupSessionId(sgw + "@" + ssid);
        ccr.setModifyType(flag);
        ccr.setModaccinfoModifyAmount(balance);
        ccr.setModaccinfoValidityAmount(increment);
        ccr.setModaccinfoDeductType("1");
        ccr.setRechargePartnerId("");
        ccr.setServiceId("");
        ccr.setChannelId(channelId);
        ccr.setTransparentData1(transparentData1);
        ccr.setTransparentData2(transparentData2);
        ccr.setMeasureId(measureId);
        EquinoxMessageBuilder builder = EquinoxMessageBuilder.newInstance();
        builder.setMessage(ccr);
        builder.createXMLMessage();
        msg = builder.getMessage();
        smoiIns.setMapCmd("AccountAdjustment");
        return msg;
    }*/

    private static boolean isNotEmpty(String input) {
        return null != input && !"".equals(input.trim());
    }
    
    /*public static void main(String[] args) {
        CCRMessage ccr = new CCRMessage();
        ccr.setSessionid("111");
        ccr.setOriginHost("sss");
        
        EquinoxMessageBuilder builder = EquinoxMessageBuilder.newInstance();
        builder.setMessage(ccr);
        builder.createXMLMessage();
        String msg = builder.getMessage();
        System.out.println(msg);
    }*/
}
