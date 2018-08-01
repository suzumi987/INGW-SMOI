package phoebe.eqx.smoi.control;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.*;
import ec02.af.exception.ActionProcessException;
import ec02.data.E01Data;
import ec02.enums.EE01MessageType;
import ec02.interfaces.IEC02;
import ec02.utils.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import phoebe.eqx.smoi.bean.EquinoxMessage;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.state.AFStateManager;
import phoebe.eqx.smoi.utils.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
//import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmoiMain extends AbstractAF implements IEC02 {

    @Override
    public Object extractInstance(String instance) {
        MyAppData myAppData = new MyAppData();
        try {
            SmoiInstance smoiIns = null;
            if (instance.equals("")) {
                smoiIns = new SmoiInstance();
            } else {
                try {
                    smoiIns = decodeInstance(instance);
                } catch (Exception e) {
                    Log.e("Error Massage:" + e.getMessage(), e);
                }
            }
            myAppData.setSmoiIns(smoiIns);
        } catch (Exception e) {
            Log.e("Error:" + e.getMessage(), e);
        }

        return (Object) myAppData;
    }

    @Override
    public void extractRawData(Object instance, ArrayList<EquinoxRawData> rawDatas) {

        if (rawDatas.size() > 1) {
            Log.e("INGW_SMOI can't not support Mutiple Messages");
        }

        if (this.getEquinoxProperties().isTimeout()) {
            Log.d(DateTime.getNow() + " Incomming Equinox Timeout");
            rawDatas.clear();
            EquinoxRawData r = new EquinoxRawData();
            r.setRawEventType(AFEvent.IncomingEquinox_Timeout);
            rawDatas.add(r);
        } else {
            for (EquinoxRawData r : rawDatas) {
                try {
                    String ret = r.getRet();
                    String ctype = r.getRawDataAttribute("ctype");
                    String type = r.getRawDataAttribute("type");
                    String oid = r.getRawDataAttribute("oid");
                    String ecode = r.getRawDataAttribute("ecode");

                    Log.d("Attributes Message :ret=" + ret
                            + " ,ctyp=" + ctype
                            + " ,type=" + type
                            + " ,oid=" + oid
                            + " ,ecode=" + ecode);

                    if (ret.equals("0")) {
                        if ((ctype.equals("text/plain") || ctype.equals("text/xml")) && type.equals("request")) {
                            String requestMessage;
                            if (ctype.equals("text/plain")) {
                                requestMessage = r.getRawDataAttribute("val");
                                if (requestMessage != null) {
                                    requestMessage = requestMessage.trim();
                                }
                                r.setRawMessage(requestMessage);
                            } else {
                                requestMessage = r.getRawDataMessage();
                            }

                            String page = SMOIUtils.getHttpPostValue(requestMessage, "page");
                            if (page.equals(ECommand.dispPPSInfo.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingDispPPSInfo);
                            } else if (page.equals(ECommand.dispPPSPkgrew.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingDispPPSPkgrew);
                            } else if (page.equals(ECommand.modiPPSValidity.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingModiPPSValidity);
                            } else if (page.equals(ECommand.modiPPSMultiAttr.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingModiPPSMultiAttr);
                            } else if (page.equals(ECommand.addScrScreenNo.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingAddScrScreenNo);
                            } else if (page.equals(ECommand.deleScrScreenNo.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingDeleScrScreenNo);
                            } else if (page.equals(ECommand.modiScrWhiteList.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingModiScrWhiteList);
                            } else if (page.equals(ECommand.dispScrScreenNo.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingDispScrScreenNo);
                                /* } else if (page.equals(ECommand.modiScrScreenType.getCommand())) {
                                 r.setRawEventType(AFEvent.IncomingModiScrScreenType);
                                 } else if (page.equals(ECommand.actPPSRBT.getCommand())) {
                                 r.setRawEventType(AFEvent.IncomingActPPSRBT);
                                 } else if (page.equals(ECommand.delePPSPkgres.getCommand())) {
                                 r.setRawEventType(AFEvent.IncomingDelePPSPkgres);
                                 } else if (page.equals(ECommand.delePPSPackageID.getCommand())) {
                                 r.setRawEventType(AFEvent.IncomingDelePPSPackageID);*/
                            } else if (page.equals(ECommand.purchasePPSPackage.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingPurchasePPSPackage);
                            } else if (page.equals(ECommand.setPPSReward.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingSetPPSReward);
                                /*} else if (page.equals(ECommand.dispPPSFntelNo.getCommand())) {
                                 r.setRawEventType(AFEvent.IncomingDispPPSFntelNo);
                                 } else if (page.equals(ECommand.modiPPSLanguage.getCommand())) {
                                 r.setRawEventType(AFEvent.IncomingModiPPSLanguage);
                                 } else if (page.equals(ECommand.modiPPSSMSLanguage.getCommand())) {
                                 r.setRawEventType(AFEvent.IncomingModiPPSSMSLanguage);
                                 } else if (page.equals(ECommand.chgtrigChrgAcnt.getCommand())) {
                                 r.setRawEventType(AFEvent.IncomingChgtrigChrgAcnt);*/
                            } else if (page.equals(ECommand.modiPPSCreditLimit.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingModiPPSCreditLimit);
                            } else if (page.equals(ECommand.activatePPSSingSub.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingActivatePPSSingSub);
                            } else if (page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())) {
                                r.setRawEventType(AFEvent.IncomingModiPPSCallNotifyFlag);
                            } else if (page.equals("")) {
                                r.setRawEventType(AFEvent.IncomingNoCommand);
                            } else {
                                r.setRawEventType(AFEvent.IncomingOtherCommand);
                            }
                        } else if (ctype.equals("Credit-Control") && type.equals("response")) {
                            r.setRawEventType(AFEvent.IncomingCCA);
                        } else if (oid.equals("0.0.17.1218.8.7.0")) {
                            r.setRawEventType(AFEvent.IncomingInquirySubscriberRes);
                        } else if ((ctype.equals("text/html") || ctype.equals("text/xml") || ctype.equals("text/plain")) && type.equals("response")) {
                            r.setRawEventType(AFEvent.IncomingHttpRes);
                            if (ctype.equals("text/plain")) {
                                String requestMessage = r.getRawDataAttribute("val");
                                if (requestMessage != null) {
                                    requestMessage = requestMessage.trim();
                                }
                                r.setRawMessage(requestMessage);
                            }

                        }
                    } else if (ret.equals("1")) {
                        r.setRawEventType(AFEvent.IncomingEquinox_Error);
                    } else if (ret.equals("2")) {
                        r.setRawEventType(AFEvent.IncomingEquinox_Reject);
                    } else if (ret.equals("3")) {
                        r.setRawEventType(AFEvent.IncomingEquinox_Abourt);
                    } else if (ret.equals("4")) {
                        r.setRawEventType(AFEvent.IncomingEquinox_Timeout);
                    }
                    Log.d("EventType = " + r.getRawEventType());
                } catch (Exception e) {
                    Log.e("Error:" + e.getMessage(), e);
                }
                break;
            }

            GlobalData globalData = this.getUtils().getGlobalData();
            if (globalData.isRecieve()) {
                String messageId = globalData.getTransactionId();
                EE01MessageType e01MessageType = globalData.getGlobaldataMessageType();
                ArrayList<E01Data> e01Datas = globalData.getDataResultSet();
                for (E01Data e01Data : e01Datas) {
                    EquinoxRawData r = new EquinoxRawData();
                    final KeyObject keyObject = e01Data.getKeyObject();
                    String objectType = keyObject.getObjectType();
                    String mid = e01Data.getMid();

                    if ("Destination".equalsIgnoreCase(objectType)) {
                        r.setInvoke(messageId + ":" + mid);
                        r.setName("E01Query");
                        r.setOrig("E01");
                        r.setRet(String.valueOf(e01MessageType.getCode()));

                        StringBuilder msg = new StringBuilder();
                        msg.append("ObjectType=").append(objectType).append("&resultcode=")
                                .append(e01Data.getResultCode()).append("&description=")
                                .append(e01Data.getDescription())
                                .append("&key0=").append(keyObject.getKey0())
                                .append("&key1=").append(keyObject.getKey1())
                                .append("&key2=").append(keyObject.getKey2())
                                .append("&key3=").append(keyObject.getKey3())
                                .append("&key4=").append(keyObject.getKey4())
                                .append("&data=").append(e01Data.getData());
                        r.setRawMessage(msg.toString());
                        if (e01MessageType == EE01MessageType.R0) {
                            r.setRawEventType(AFEvent.IncomingE01Destination);
                        } else if (e01MessageType == EE01MessageType.R1) {
                            r.setRawEventType(AFEvent.IncomingEquinox_Error);
                        } else if (e01MessageType == EE01MessageType.R2) {
                            r.setRawEventType(AFEvent.IncomingEquinox_Reject);
                        } else if (e01MessageType == EE01MessageType.R3) {
                            r.setRawEventType(AFEvent.IncomingEquinox_Abourt);
                        } else if (e01MessageType == EE01MessageType.R4) {
                            r.setRawEventType(AFEvent.IncomingEquinox_Timeout);
                        }
                    } else {
                        r.setRawEventType(AFEvent.IncomingUnknowMessge);
                    }

                    rawDatas.add(r);
                }
            }

        }
    }

    @Override
    public ECDialogue actionProcess(EquinoxProperties prop, ArrayList<EquinoxRawData> rawDatas, Object instanceData) throws ActionProcessException {
        try {
            MyAppData myAppData = (MyAppData) instanceData;

            String currentState = prop.getState();
            myAppData.setInput_state(currentState);
            Log.d(DateTime.getNow() + " : Current state : " + currentState);
            AFStateManager afMan = new AFStateManager(currentState);
            String nextState = afMan.doAction((AbstractAF) this, instanceData, rawDatas);

            String ret = myAppData.getRet();
            String timeout = String.valueOf(myAppData.getTimeout());

            Log.d(DateTime.getNow() + " : Next state : " + nextState);
            Log.d(DateTime.getNow() + " : Ret : " + ret);
            Log.d(DateTime.getNow() + " : Timeout : " + timeout);
            prop.setState(nextState);
            prop.setRet(ret);
            prop.setDiag("");
            prop.setTimeout(timeout);
            ECDialogue ep = new ECDialogue(prop, myAppData);
            SmoiLog.writeDetailLog(this, myAppData, nextState);
            if (nextState.equals(AFState.IDLE)) {
                SmoiLog.writeSummaryLogRes(this, myAppData);
            }
            return ep;
        } catch (Exception e) {
            Log.e("Error:" + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public ArrayList<EquinoxRawData> constructRawData(Object instance) {
        MyAppData myAppData = (MyAppData) instance;
        ArrayList<EquinoxRawData> rawDatas = new ArrayList<EquinoxRawData>();
        ArrayList<EquinoxMessage> equinoxMessages = myAppData.getEquinoxMessagelists();
        for (Iterator<EquinoxMessage> it = equinoxMessages.iterator(); it.hasNext(); ) {
            try {
                EquinoxMessage equinoxMessage = it.next();
                EquinoxRawData r = new EquinoxRawData();
                r.setRawDataAttributes(equinoxMessage.getRawDataAttributes());
                if (equinoxMessage.getMessage() != null) {
                    r.setRawMessage(equinoxMessage.getMessage());
                }
                rawDatas.add(r);
            } catch (Exception e) {
                Log.e("Error:" + e.getMessage(), e);
            }
        }
        return rawDatas;
    }

    @Override
    public String composeInstance(Object instance) {
        String encodeString = "";
        try {
            MyAppData myAppData = (MyAppData) instance;
            SmoiInstance smoiIns = myAppData.getSmoiIns();
            encodeString = encodeInstance(smoiIns);
        } catch (Exception e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
//        Log.d("Instance:" + encodeString);
        return encodeString;
    }

    /*private String encodeInstance(SmoiInstance instance) throws Exception {
        Kryo kryo = new Kryo();
        kryo.register(SmoiInstance.class);
        Output output = new Output(new ByteArrayOutputStream(), 32000);

        kryo.writeObject(output, instance);
        byte[] bytes = output.toBytes();
//        Log.d("output: "+ new String(bytes));
        output.close();
        bytes = Zip.compressBytes(bytes);
        String encodeString = Base64.encode(bytes);
        return encodeString;
    }

    private SmoiInstance decodeInstance(String instance) throws Exception {
        byte[] decodeString = Base64.decode(instance);
        Kryo kryo = new Kryo();
        kryo.register(SmoiInstance.class);
        decodeString = Zip.extractBytes(decodeString);
//        Log.d("input: "+ new String(decodeString));
        SmoiInstance smoiIns = kryo.readObject(new Input(decodeString), SmoiInstance.class);
        return smoiIns;
    }*/

    private String encodeInstance(SmoiInstance instance) {
        String encodeString = "";
        try {
            String str = JsonService.getInstance().encodeInstance(instance);
            byte[] bytes = str.getBytes();
            byte[] zipBytes = Zip.compressBytes(bytes);
            encodeString = Base64.encode(zipBytes);
        } catch (Exception e) {
            Log.i("Encode fail!!", e);
        }
        return encodeString;
    }

    private SmoiInstance decodeInstance(String instance) {
        SmoiInstance afInstance = null;
        try {
            byte[] simpleString = Base64.decode(instance);
            byte[] unZipString = Zip.extractBytes(simpleString);
            afInstance = JsonService.getInstance().decodeInstance(new String(unZipString));
        } catch (Exception e) {
            Log.i(e.getMessage());
            Log.i("Decode fail!!", e);
        }
        return afInstance;
    }

    @Override
    public boolean verifyAFConfiguration(String xmlConf) {
        try {
            Log.d(xmlConf);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDocument = db.parse(new InputSource(new StringReader(xmlConf)));

            XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
            XPath xPath = factory.newXPath();

            //Log Name
            XPathExpression xPathExpression = xPath.compile("//configuration//warm//" + Conf.logNameSummary + "//@value");
            String logNameSummary = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyLogNameSummary = logNameSummary.matches("\\w+");

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.logNameDetail + "//@value");
            String logNameDetail = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyLogNameDetail = logNameDetail.matches("\\w+");

            //Log Enable
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.logEnableSummary + "//@value");
            String logEnableSummary = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyLogEnableSummary = logEnableSummary.matches("Enable|Disable");

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.logEnableDetail + "//@value");
            String logEnableDetail = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyLogEnableDetail = logEnableDetail.matches("Enable|Disable");

            //Resource Name Active
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameDs2a_Active + "//@value");
            String resourceNameDs2a_Active = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern p1 = Pattern.compile("\\w+(\\.ES03\\.)\\w+");
            Matcher m1 = p1.matcher(resourceNameDs2a_Active);
            boolean verifyResourceNameDs2a_Active = m1.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameBos_Active + "//@value");
            String resourceNameBos_Active = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern p2 = Pattern.compile("\\w+(\\.ES0[6|7]\\.)\\w+");
            Matcher m2 = p2.matcher(resourceNameBos_Active);
            boolean verifyResourceNameBos_Active = m2.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameAVATAR_Active + "//@value");
            String resourceNameAvatar_Active = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern p9 = Pattern.compile("\\w+(\\.ES0[6|7]\\.)\\w+");
            Matcher m9 = p9.matcher(resourceNameAvatar_Active);
            boolean verifyResourceNameAvatar_Active = m9.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameBssbroker_Active + "//@value");
            String resourceNameBssbroker_Active = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern p3 = Pattern.compile("\\w+(\\.ES04\\.)\\w+");
            Matcher m3 = p3.matcher(resourceNameBssbroker_Active);
            boolean verifyResourceNameBssbroker_Active = m3.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNamePpgw_Active + "//@value");
            String resourceNamePpgw_Active = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern p4 = Pattern.compile("\\w+(\\.ES04\\.)\\w+");
            Matcher m4 = p4.matcher(resourceNamePpgw_Active);
            boolean verifyResourceNamePpgw_Active = m4.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameSmoi_Active + "//@value");
            String resourceNameSmoi_Active = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern p5 = Pattern.compile("\\w+(\\.ES04\\.)\\w+");
            Matcher m5 = p5.matcher(resourceNameSmoi_Active);
            boolean verifyResourceNameSmoi_Active = m5.find();
            
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resource_Name_Active_MD + "//@value");
            String resource_Name_Active_MD = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern p6 = Pattern.compile("\\w+(\\.ES04\\.)\\w+");
            Matcher m6 = p6.matcher(resource_Name_Active_MD);
            boolean verifyResource_Name_Active_MD = m6.find();

            //Resource Name Standby
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameDs2a_Standby + "//@value");
            String resourceNameDs2a_Standby = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern ps1 = Pattern.compile("\\w+(\\.ES03\\.)\\w+");
            Matcher ms1 = ps1.matcher(resourceNameDs2a_Standby);
            boolean verifyResourceNameDs2a_Standby = ms1.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameBos_Standby + "//@value");
            String resourceNameBos_Standby = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern ps2 = Pattern.compile("\\w+(\\.ES0[6|7]\\.)\\w+");
            Matcher ms2 = ps2.matcher(resourceNameBos_Standby);
            boolean verifyResourceNameBos_Standby = ms2.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameAVATAR_Standby + "//@value");
            String resourceNameAvatar_Standby = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern ps8 = Pattern.compile("\\w+(\\.ES0[6|7]\\.)\\w+");
            Matcher ms8 = ps8.matcher(resourceNameAvatar_Standby);
            boolean verifyResourceNameAvatar_Standby = ms8.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameBssbroker_Standby + "//@value");
            String resourceNameBssbroker_Standby = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern ps3 = Pattern.compile("\\w+(\\.ES04\\.)\\w+");
            Matcher ms3 = ps3.matcher(resourceNameBssbroker_Standby);
            boolean verifyResourceNameBssbroker_Standby = ms3.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNamePpgw_Standby + "//@value");
            String resourceNamePpgw_Standby = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern ps4 = Pattern.compile("\\w+(\\.ES04\\.)\\w+");
            Matcher ms4 = ps4.matcher(resourceNamePpgw_Standby);
            boolean verifyResourceNamePpgw_Standby = ms4.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameSmoi_Standby + "//@value");
            String resourceNameSmoi_Standby = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern ps5 = Pattern.compile("\\w+(\\.ES04\\.)\\w+");
            Matcher ms5 = ps5.matcher(resourceNameSmoi_Standby);
            boolean verifyResourceNameSmoi_Standby = ms5.find();
            
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resource_Name_Standby_MD + "//@value");
            String resource_Name_Standby_MD = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern ps6 = Pattern.compile("\\w+(\\.ES04\\.)\\w+");
            Matcher ms6 = ps6.matcher(resource_Name_Standby_MD);
            boolean verifyResource_Name_Standby_MD = ms6.find();

            //Resource Name Standby MaxRetry
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameDs2a_Standby_MaxRetry + "//@value");
            String resourceNameDs2a_Standby_MaxRetry = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern psm1 = Pattern.compile("\\d+");
            Matcher msm1 = psm1.matcher(resourceNameDs2a_Standby_MaxRetry);
            boolean verifyResourceNameDs2a_Standby_MaxRetry = msm1.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameBos_Standby_MaxRetry + "//@value");
            String resourceNameBos_Standby_MaxRetry = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern psm2 = Pattern.compile("\\d+");
            Matcher msm2 = psm2.matcher(resourceNameBos_Standby_MaxRetry);
            boolean verifyResourceNameBos_Standby_MaxRetry = msm2.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameAVATAR_Standby_MaxRetry + "//@value");
            String resourceNameAvatar_Standby_MaxRetry = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern psm7 = Pattern.compile("\\d+");
            Matcher msm7 = psm7.matcher(resourceNameAvatar_Standby_MaxRetry);
            boolean verifyResourceNameAvatar_Standby_MaxRetry = msm7.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameBssbroker_Standby_MaxRetry + "//@value");
            String resourceNameBssbroker_Standby_MaxRetry = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern psm3 = Pattern.compile("\\d+");
            Matcher msm3 = psm3.matcher(resourceNameBssbroker_Standby_MaxRetry);
            boolean verifyResourceNameBssbroker_Standby_MaxRetry = msm3.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNamePpgw_Standby_MaxRetry + "//@value");
            String resourceNamePpgw_Standby_MaxRetry = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern psm4 = Pattern.compile("\\d+");
            Matcher msm4 = psm4.matcher(resourceNamePpgw_Standby_MaxRetry);
            boolean verifyResourceNamePpgw_Standby_MaxRetry = msm4.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameSmoi_Standby_MaxRetry + "//@value");
            String resourceNameSmoi_Standby_MaxRetry = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern psm5 = Pattern.compile("\\d+");
            Matcher msm5 = psm5.matcher(resourceNameSmoi_Standby_MaxRetry);
            boolean verifyResourceNameSmoi_Standby_MaxRetry = msm5.find();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.resourceNameE01_Standby_MaxRetry + "//@value");
            String resourceNameE01_Standby_MaxRetry = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            Pattern psm6 = Pattern.compile("\\d+");
            Matcher msm6 = psm6.matcher(resourceNameE01_Standby_MaxRetry);
            boolean verifyResourceNameE01_Standby_MaxRetry = msm6.find();

            //Timeout
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.ds2aTimeout + "//@value");
            String ds2aTimeout = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyDs2aTimeout = ds2aTimeout.matches("\\d+");

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.bosTimeout + "//@value");
            String bosTimeout = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyBosTimeout = bosTimeout.matches("\\d+");

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.AVATARTimeout + "//@value");
            String avatarTimeout = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvatarTimeout = avatarTimeout.matches("\\d+");

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.bssTimeout + "//@value");
            String bssTimeout = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyBssTimeout = bssTimeout.matches("\\d+");

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.ppgwTimeout + "//@value");
            String ppgwTimeout = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyPpgwTimeout = ppgwTimeout.matches("\\d+");

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.smoiTimeout + "//@value");
            String smoiTimeout = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifySmoiTimeout = smoiTimeout.matches("\\d+");
            
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.tm_MD + "//@value");
            String tm_MD = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyTm_MD = tm_MD.matches("\\d+");

            //SMOI
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.ingwSmoi_OriginHost + "//@value");
            String ingwSmoi_OriginHost = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyIngwSmoi_OriginHost = !ingwSmoi_OriginHost.isEmpty();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.ingwSmoi_OriginRealm + "//@value");
            String ingwSmoi_OriginRealm = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyIngwSmoi_OriginRealm = !ingwSmoi_OriginRealm.isEmpty();

            //BOS
//            xPathExpression = xPath.compile("//configuration//warm//" + Conf.bos_DestinationHost + "//@value");
//            String bos_DestinationHost = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
//            boolean verifyBos_DestinationHost = !bos_DestinationHost.isEmpty();
//
//            xPathExpression = xPath.compile("//configuration//warm//" + Conf.bos_DestinationRealm + "//@value");
//            String bos_DestinationRealm = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
//            boolean verifyBos_DestinationRealm = !bos_DestinationRealm.isEmpty();
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.bosLocation + "//@value");
            String bosLocation = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyBosLocation = !bosLocation.isEmpty();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.avatarLocation + "//@value");
            String avatarLocation = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvatarLocation = !avatarLocation.isEmpty();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.avatar_balance_SMS_Domestic + "//@value");
            String avatarBalanceSMS = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvataBalanceSMS = !avatarBalanceSMS.isEmpty();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.avatar_balance_Voice_Domestic + "//@value");
            String avatarBalanceVoice = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvataBalanceVoice = !avatarBalanceVoice.isEmpty();

            xPathExpression = xPath.compile("//configuration//warm//" + Conf.avatar_balance_Money + "//@value");
            String avatarBalanceMoney = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvataBalanceMoney = !avatarBalanceMoney.isEmpty();
            
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.avatar_balance_RTBSong + "//@value");
            String avatarBalanceRTBSong = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvataBalanceRTBSong = !avatarBalanceRTBSong.isEmpty();
            
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.balanceType + "//@value");
            String balanceType = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvatabalanceType = !balanceType.isEmpty();
         
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.freeUnitId_PRMMINUTE + "//@value");
            String prmminutType= xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvataprmminutType = !prmminutType.isEmpty();
            
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.freeUnitId_PRMSM + "//@value");
            String prmsmType = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvataprmsmType = !prmsmType.isEmpty();
            
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.freeUnitId_FREERBTSONG + "//@value");
            String freerbtsongType = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvatafreerbtsongType = !freerbtsongType.isEmpty();
            
            
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.BALANCETYPE_PRMMONEY + "//@value");
            String balancetypePrmmoney = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvataBalancetypePrmmoney = !balancetypePrmmoney.isEmpty();
            
            Node nodeAvatarChannel = (Node) xPath.evaluate("//configuration//warm//"+ Conf.avatar_channel +"//@value", xmlDocument, XPathConstants.NODE);
            xPathExpression = xPath.compile("//configuration//warm//" + Conf.avatar_channel + "//@value");
            String avatarChannel = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
            boolean verifyAvatarChannel = false;
            if(nodeAvatarChannel != null){
            	verifyAvatarChannel = true;
            }
           
            Node nodeAvatarAdjChannelID = (Node) xPath.evaluate("//configuration//warm//"+ Conf.avatar_ajd_channel_id +"//@value", xmlDocument, XPathConstants.NODE);
            String avatarAjdChannelId = getWarmConfigValue(xPathExpression, xPath, Conf.avatar_ajd_channel_id, xmlDocument);
            boolean verifyAvatarAjdChannelId = true;
            if(nodeAvatarAdjChannelID != null){ 
            	verifyAvatarAjdChannelId = verifyNumberConfigValue(avatarAjdChannelId, "\\d+");
            }
            
            String resourceActiveIns = getWarmConfigValue(xPathExpression, xPath, Conf.resourceNameIns_Active, xmlDocument);
            boolean verifyResourceActiveIns = verifyConfigValue(resourceActiveIns, "\\w+(\\.ES04\\.)\\w+");

            String resourceStanbyIns = getWarmConfigValue(xPathExpression, xPath, Conf.resourceNameIns_Standby, xmlDocument);
            boolean verifyResourceStanbyIns = verifyConfigValue(resourceStanbyIns, "\\w+(\\.ES04\\.)\\w+");

            String insMaxRetry = getWarmConfigValue(xPathExpression, xPath, Conf.resourceNameIns_Standby_MaxRetry, xmlDocument);
            boolean verifyInsMaxRetry = verifyConfigValue(insMaxRetry, "\\d+");

            String insTimeout = getWarmConfigValue(xPathExpression, xPath, Conf.insTimeout, xmlDocument);
            boolean verifyInsTimeout = verifyConfigValue(insTimeout, "\\d+");

            String insLocation = getWarmConfigValue(xPathExpression, xPath, Conf.insLocation, xmlDocument);
            boolean verifyInsLocation = verifyConfigValue(insLocation, ".+");

            String modifyWL = getWarmConfigValue(xPathExpression, xPath, Conf.ModifyWLAlternateSolutionFlag, xmlDocument);
            boolean verifyModifyWL = verifyConfigValue(modifyWL, "^[0-1]{1}$");
            
            String url_MD = getWarmConfigValue(xPathExpression, xPath, Conf.url_MD, xmlDocument);
            boolean verifyurl_MD = !url_MD.isEmpty();
            
            String url_eql = getWarmConfigValue(xPathExpression, xPath, Conf.url_eql, xmlDocument);
            boolean verifyurl_eql = !url_eql.isEmpty();
            
            String md_Login_User = getWarmConfigValue(xPathExpression, xPath, Conf.md_Login_User, xmlDocument);
            boolean verifymd_Login_User = !md_Login_User.isEmpty();
            
            String md_Login_Password = getWarmConfigValue(xPathExpression, xPath, Conf.md_Login_Password, xmlDocument);
            boolean verifymd_Login_Password = !md_Login_Password.isEmpty();

            Log.d(Conf.logNameSummary + "= " + logNameSummary + (verifyLogNameSummary ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.logNameDetail + "= " + logNameDetail + (verifyLogNameDetail ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.logEnableSummary + "= " + logEnableSummary + (verifyLogEnableSummary ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.logEnableDetail + "= " + logEnableDetail + (verifyLogEnableDetail ? " < Correct >" : " < Wrong >"));

            Log.d(Conf.resourceNameDs2a_Active + "= " + resourceNameDs2a_Active + (verifyResourceNameDs2a_Active ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameBos_Active + "= " + resourceNameBos_Active + (verifyResourceNameBos_Active ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameAVATAR_Active + "= " + resourceNameAvatar_Active + (verifyResourceNameAvatar_Active ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameBssbroker_Active + "= " + resourceNameBssbroker_Active + (verifyResourceNameBssbroker_Active ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNamePpgw_Active + "= " + resourceNamePpgw_Active + (verifyResourceNamePpgw_Active ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameSmoi_Active + "= " + resourceNameSmoi_Active + (verifyResourceNameSmoi_Active ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resource_Name_Active_MD + "= " + resource_Name_Active_MD + (verifyResource_Name_Active_MD ? " < Correct >" : " < Wrong >"));

            Log.d(Conf.resourceNameDs2a_Standby + "= " + resourceNameDs2a_Standby + (verifyResourceNameDs2a_Standby ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameBos_Standby + "= " + resourceNameBos_Standby + (verifyResourceNameBos_Standby ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameAVATAR_Standby + "= " + resourceNameAvatar_Standby + (verifyResourceNameAvatar_Standby ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameBssbroker_Standby + "= " + resourceNameBssbroker_Standby + (verifyResourceNameBssbroker_Standby ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNamePpgw_Standby + "= " + resourceNamePpgw_Standby + (verifyResourceNamePpgw_Standby ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameSmoi_Standby + "= " + resourceNameSmoi_Standby + (verifyResourceNameSmoi_Standby ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resource_Name_Standby_MD + "= " + resource_Name_Standby_MD + (verifyResource_Name_Standby_MD ? " < Correct >" : " < Wrong >"));

            Log.d(Conf.resourceNameDs2a_Standby_MaxRetry + "= " + resourceNameDs2a_Standby_MaxRetry + (verifyResourceNameDs2a_Standby_MaxRetry ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameBos_Standby_MaxRetry + "= " + resourceNameBos_Standby_MaxRetry + (verifyResourceNameBos_Standby_MaxRetry ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameAVATAR_Standby_MaxRetry + "= " + resourceNameAvatar_Standby_MaxRetry + (verifyResourceNameAvatar_Standby_MaxRetry ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameBssbroker_Standby_MaxRetry + "= " + resourceNameBssbroker_Standby_MaxRetry + (verifyResourceNameBssbroker_Standby_MaxRetry ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNamePpgw_Standby_MaxRetry + "= " + resourceNamePpgw_Standby_MaxRetry + (verifyResourceNamePpgw_Standby_MaxRetry ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameSmoi_Standby_MaxRetry + "= " + resourceNameSmoi_Standby_MaxRetry + (verifyResourceNameSmoi_Standby_MaxRetry ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.resourceNameE01_Standby_MaxRetry + "= " + resourceNameE01_Standby_MaxRetry + (verifyResourceNameE01_Standby_MaxRetry ? " < Correct >" : " < Wrong >"));

            Log.d(Conf.url_MD + "= " + url_MD + (verifyurl_MD ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.url_eql + "= " + url_eql + (verifyurl_eql ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.md_Login_User + "= " + md_Login_User + (verifymd_Login_User ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.md_Login_Password + "= " + md_Login_Password + (verifymd_Login_Password ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.ds2aTimeout + "= " + ds2aTimeout + (verifyDs2aTimeout ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.bosTimeout + "= " + bosTimeout + (verifyBosTimeout ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.AVATARTimeout + "= " + avatarTimeout + (verifyAvatarTimeout ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.bssTimeout + "= " + bssTimeout + (verifyBssTimeout ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.ppgwTimeout + "= " + ppgwTimeout + (verifyPpgwTimeout ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.smoiTimeout + "= " + smoiTimeout + (verifySmoiTimeout ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.tm_MD + "= " + tm_MD + (verifyTm_MD ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.ingwSmoi_OriginHost + "= " + ingwSmoi_OriginHost + (verifyIngwSmoi_OriginHost ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.ingwSmoi_OriginRealm + "= " + ingwSmoi_OriginRealm + (verifyIngwSmoi_OriginRealm ? " < Correct >" : " < Wrong >"));
//            Log.d(Conf.bos_DestinationHost + "= " + furl_eqlbos_DestinationHost + (verifyBos_DestinationHost ? " < Correct >" : " < Wrong >"));
//            Log.d(Conf.bos_DestinationRealm + "= " + bos_DestinationRealm + (verifyBos_DestinationRealm ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.bosLocation + "= " + bosLocation + (verifyBosLocation ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.avatarLocation + "= " + avatarLocation + (verifyAvatarLocation ? " < Correct >" : " < Wrong >"));

            Log.d(Conf.avatar_balance_SMS_Domestic + "= " + avatarBalanceSMS + (verifyAvataBalanceSMS ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.avatar_balance_Voice_Domestic + "= " + avatarBalanceVoice + (verifyAvataBalanceVoice ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.avatar_channel + "= " + avatarChannel + (verifyAvatarChannel ? " < Correct >" : " < Wrong >"));
            Log.d(Conf.avatar_ajd_channel_id + "= " + avatarAjdChannelId + (verifyAvatarAjdChannelId ? " < Correct >" : " < Wrong >"));
//            Log.d(Conf.ModifyWLAlternateSolutionFlag + "= " + modifyWL + (verifyModifyWL ? " < Correct >" : " < Wrong >"));


            
            writeLogResult(Conf.resourceNameIns_Active, resourceActiveIns, verifyResourceActiveIns);
            writeLogResult(Conf.resourceNameIns_Standby, resourceStanbyIns, verifyResourceStanbyIns);
            writeLogResult(Conf.resourceNameIns_Standby_MaxRetry, insMaxRetry, verifyInsMaxRetry);
            writeLogResult(Conf.insLocation, insLocation, verifyInsLocation);
            writeLogResult(Conf.insTimeout, insTimeout, verifyInsTimeout);
            //String configName, String configValue, boolean verifyResult
            writeLogResult(Conf.ModifyWLAlternateSolutionFlag, modifyWL, verifyModifyWL);


            return verifyLogNameSummary
                    && verifyLogNameDetail
                    && verifyLogEnableSummary
                    && verifyLogEnableDetail
                    && verifyResourceNameDs2a_Active
                    && verifyResourceNameBos_Active
                    && verifyResourceNameBssbroker_Active
                    && verifyResourceNamePpgw_Active
                    && verifyResourceNameSmoi_Active
                    && verifyResource_Name_Active_MD
                    && verifyResourceNameDs2a_Standby
                    && verifyResourceNameBos_Standby
                    && verifyResourceNameBssbroker_Standby
                    && verifyResourceNamePpgw_Standby
                    && verifyResourceNameSmoi_Standby
                    && verifyResource_Name_Standby_MD
                    && verifyResourceNameDs2a_Standby_MaxRetry
                    && verifyResourceNameBos_Standby_MaxRetry
                    && verifyResourceNameBssbroker_Standby_MaxRetry
                    && verifyResourceNamePpgw_Standby_MaxRetry
                    && verifyResourceNameE01_Standby_MaxRetry
                    && verifyDs2aTimeout
                    && verifyBosTimeout
                    && verifyBssTimeout
                    && verifyPpgwTimeout
                    && verifySmoiTimeout
                    && verifyTm_MD
                    && verifyIngwSmoi_OriginHost
                    && verifyIngwSmoi_OriginRealm
                    && verifyurl_MD
                    && verifyurl_eql
                    && verifymd_Login_User
                    && verifymd_Login_Password
                    //                    && verifyBos_DestinationHost
                    //                    && verifyBos_DestinationRealm
                    && verifyBosLocation

                    && verifyResourceActiveIns
                    && verifyResourceStanbyIns
                    && verifyInsMaxRetry
                    && verifyInsLocation
                    && verifyInsTimeout
                    && verifyModifyWL
                    && verifyAvataBalanceSMS
                    && verifyAvataBalanceVoice
                    && verifyAvataBalanceMoney
                    && verifyAvataBalanceRTBSong
                    && verifyAvatabalanceType
                    && verifyAvataprmminutType
                    && verifyAvataprmsmType
                    && verifyAvatafreerbtsongType
                    && verifyAvataBalancetypePrmmoney
                    && verifyAvatarChannel
                    && verifyAvatarAjdChannelId
                    ;

        } catch (Exception e) {
            Log.e("verifyAFConfiguration Error: " + e.getMessage(), e);
            return false;
        }
    }

    private void writeLogResult(String configName, String configValue, boolean verifyResult) {
        Log.d(configName + "= " + configValue + (verifyResult ? " < Correct >" : " < Wrong >"));
    }

    private String getWarmConfigValue(XPathExpression xPathExpression, XPath xPath, String configName, Document xmlDocument) throws XPathExpressionException {
        xPathExpression = xPath.compile("//configuration//warm//" + configName + "//@value");
        String value = xPathExpression.evaluate(xmlDocument, XPathConstants.STRING).toString();
        return value;
    }

    private boolean verifyConfigValue(String configValue, String pattern) {
        Pattern p1 = Pattern.compile(pattern);
        Matcher m1 = p1.matcher(configValue);
        return m1.find();
    }
    private boolean verifyNumberConfigValue(String configValue, String pattern) {
        Pattern p1 = Pattern.compile(pattern);
        Matcher m1 = p1.matcher(configValue);
        return m1.matches();
    }

    /*public static void main(String[] args) {

        Pattern p = Pattern.compile("(\\d{2})+(?:,\\d{2})*");
        Matcher m = p.matcher("123");


        boolean verifyAvataBalanceSMS =m.find();
        System.out.println("verifyAvataBalanceSMS:" + verifyAvataBalanceSMS);
    }*/
}
