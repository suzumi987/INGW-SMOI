/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.message.builder;

import ec02.af.abstracts.AbstractAF;
import ec02.af.data.GlobalData;
import ec02.af.data.KeyObject;
import ec02.utils.Log;
import java.util.ArrayList;
import java.util.HashMap;
import phoebe.eqx.smoi.bean.EquinoxMessage;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.ClientRequestInfo;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.enums.EObjectType;
import phoebe.eqx.smoi.utils.Atomic;

/**
 *
 * @author pavarisa
 */
public class SendMessage {

    public static String QueryE01Destination(AbstractAF af, MyAppData myAppData) {
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        String scpid = smoiIns.getScp();

        Log.d("Build Query E01 Device");
        KeyObject keyObject = new KeyObject();
        keyObject.setObjectType(EObjectType.DESTINATION.getText());
        keyObject.setKey0("0");
        keyObject.setKey1(scpid);
        keyObject.setKey2("def");
        keyObject.setKey3("def");
        keyObject.setKey4("def");

        String messageId = Atomic.getNextAtomicIndex(af.getEquinoxProperties().getApplicationName()
                + "-" + keyObject.getObjectType());
        GlobalData globalData = af.getUtils().getGlobalData();
        globalData.setTransactionId(messageId);
        globalData.search(keyObject, messageId);

        StringBuilder msg = new StringBuilder();
        msg.append("ObjectType=").append(EObjectType.DESTINATION.getText()).append("&resultcode=")
                .append("&messageId=").append(messageId)
                .append("&key0=").append(keyObject.getKey0())
                .append("&key1=").append(keyObject.getKey1())
                .append("&key2=").append(keyObject.getKey2())
                .append("&key3=").append(keyObject.getKey3())
                .append("&key4=").append(keyObject.getKey4());
        return msg.toString();
    }

    public static void DS2A(String msg, AbstractAF af, MyAppData myAppData, String resourceName) {
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "InquiryMsisdn:" + reqInfo.getInvokeid();

        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("LDAP");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("extended");
        eqxMsg.setEqxAttribute_Oid("0.0.17.1218.8.7.0");
        eqxMsg.setMessage(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);

        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.ds2aTimeout).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }

    public static void SMOI(String msg, AbstractAF af, MyAppData myAppData, String resourceName) {
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "SMOI:" + reqInfo.getInvokeid();

        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("HTTP");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("text/plain");
        eqxMsg.setEqxAttribute_Val(msg);
        
        myAppData.getEquinoxMessagelists().add(eqxMsg);

        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.smoiTimeout).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }
    
    public static void eql(String msg, AbstractAF af, MyAppData myAppData, String resourceName){
    	SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "EQL:" + reqInfo.getInvokeid(); // 
        
        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("HTTP");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("application/json");
        eqxMsg.setEqxAttribute_Other("method", "POST"); 
        eqxMsg.setEqxAttribute_Other("url", smoi_conf.get(Conf.url_eql).get(0));
        eqxMsg.setEqxAttribute_Val(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);
        
        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.EQLTimeout).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }
    
    public static void MD(String msg, AbstractAF af, MyAppData myAppData, String resourceName){
    	SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "MD:" + reqInfo.getInvokeid(); // 
        
        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("HTTP");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("application/json");
        eqxMsg.setEqxAttribute_Other("method", "POST"); 
        eqxMsg.setEqxAttribute_Other("url", smoi_conf.get(Conf.url_MD).get(0));
        eqxMsg.setEqxAttribute_Val(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);
        
        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.tm_MD).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }
    
    public static void INS(String msg, AbstractAF af, MyAppData myAppData, String resourceName) {
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "INS:" + reqInfo.getInvokeid();

        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("HTTP");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("text/plain");
        eqxMsg.setEqxAttribute_Val(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);

        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.insTimeout).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }

    public static void PPGW(String msg, AbstractAF af, MyAppData myAppData, String resourceName) {
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "PPGW:" + reqInfo.getInvokeid();

        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("HTTP");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("text/plain");
        eqxMsg.setEqxAttribute_Val(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);

        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.ppgwTimeout).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }

    public static void BOS(String msg, AbstractAF af, MyAppData myAppData, String resourceName) {
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "BOS:" + reqInfo.getInvokeid();

        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("DIAMETER");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("Credit-Control");
        eqxMsg.setMessage(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);

        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.bosTimeout).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }

    public static void AVATAR(String msg, AbstractAF af, MyAppData myAppData, String resourceName) {
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "AVATAR:" + reqInfo.getInvokeid();

        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("DIAMETER");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("Credit-Control");
        eqxMsg.setMessage(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);

        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.AVATARTimeout).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }

    public static void BSSBroker(String msg, AbstractAF af, MyAppData myAppData, String resourceName) {
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String to = smoi_conf.get(resourceName).get(0);
        String invokeId = "BSSBroker:" + reqInfo.getInvokeid();

        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("HTTP");
        eqxMsg.setEqxAttribute_Type("request");
        eqxMsg.setEqxAttribute_Ctype("text/xml");
        eqxMsg.setMessage(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);

        myAppData.setRet("0");
        myAppData.setTimeout(Integer.parseInt(smoi_conf.get(Conf.bssTimeout).get(0)));
        myAppData.setSmoiIns(smoiIns);
    }

    public static void Client(String msg, AbstractAF af, MyAppData myAppData) {
        Log.d("Send To Client:msg=" + msg);
        SmoiInstance smoiIns = myAppData.getSmoiIns();
        ClientRequestInfo reqInfo = smoiIns.getRequestInfo();
        String to = smoiIns.getRequestInfo().getResourceName();
        String invokeId = reqInfo.getInvokeid();

        EquinoxMessage eqxMsg = new EquinoxMessage();
        eqxMsg.setEqxAttribute_Invoke(invokeId);
        eqxMsg.setEqxAttribute_To(to);
        eqxMsg.setEqxAttribute_Name("HTTP");
        eqxMsg.setEqxAttribute_Type("response");
        eqxMsg.setEqxAttribute_Ctype("text/xml");
        //eqxMsg.setEqxAttribute_Val(msg);
        eqxMsg.setMessage(msg);
        myAppData.getEquinoxMessagelists().add(eqxMsg);

        myAppData.setRet("10");
        myAppData.setTimeout(10);
        myAppData.setSmoiIns(smoiIns);
    }

    public static void EqxClearInstance(AbstractAF af, MyAppData myAppData) {
        myAppData.setRet("10");
        myAppData.setTimeout(10);
    }
    
   
}
