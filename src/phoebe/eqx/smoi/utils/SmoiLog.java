package phoebe.eqx.smoi.utils;

import ec02.af.abstracts.AbstractAF; 
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.ClientRequestInfo;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;

public class SmoiLog {

    public static void writeSummaryLogReq(AbstractAF af, MyAppData myAppData) {
        boolean isEnable = af.getUtils().getHmWarmConfig().get(Conf.logEnableSummary).get(0).equalsIgnoreCase("Enable") ? true : false;
        if (isEnable) {
            String Log_Summary = af.getUtils().getHmWarmConfig().get(Conf.logNameSummary).get(0);
            SmoiInstance smoiIns = myAppData.getSmoiIns();
            ClientRequestInfo requestInfo = myAppData.getSmoiIns().getRequestInfo();
            String session = smoiIns.getSsid();
            String invokeid = requestInfo.getInvokeid();
            String msisdn = smoiIns.getMsisdn();
            String command = smoiIns.getPage();
            af.getUtils().writeLog(Log_Summary,
                    "SESSION=" + session
                    + "|INVOKE=" + invokeid
                    + "|MSISDN=" + msisdn
                    + "|COMMAND=" + command);
        }
    }

    public static void writeSummaryLogRes(AbstractAF af, MyAppData myAppData) {
        boolean isEnable = af.getUtils().getHmWarmConfig().get(Conf.logEnableSummary).get(0).equalsIgnoreCase("Enable") ? true : false;
        if (isEnable) {
            String Log_Summary = af.getUtils().getHmWarmConfig().get(Conf.logNameSummary).get(0);
            SmoiInstance smoiIns = myAppData.getSmoiIns();
            ClientRequestInfo requestInfo = myAppData.getSmoiIns().getRequestInfo();
            String session = smoiIns.getSsid();
            String invokeid = requestInfo.getInvokeid();
            String msisdn = smoiIns.getMsisdn();
            String command = smoiIns.getPage();
            String resultcode = myAppData.getInput_resultcode(); 
            String desc = myAppData.getInput_desc(); 
            long responseTime = System.currentTimeMillis() - requestInfo.getRequestTime();
            af.getUtils().writeLog(Log_Summary,
                    "SESSION=" + session
                    + "|INVOKE=" + invokeid
                    + "|MSISDN=" + msisdn
                    + "|COMMAND=" + command
                    + "|RESSULTCODE=" + resultcode
                    + "|DESC=" + desc
                    + "|RESPONSE_TIME=" + responseTime);
        }
    }

    public static void writeDetailLog(AbstractAF af, MyAppData myAppData, String _nextstate) {
        boolean isEnable = af.getUtils().getHmWarmConfig().get(Conf.logEnableDetail).get(0).equalsIgnoreCase("Enable") ? true : false;
        if (isEnable) {
            String Log_Detail = af.getUtils().getHmWarmConfig().get(Conf.logNameDetail).get(0);
            SmoiInstance smoiIns = myAppData.getSmoiIns();
            ClientRequestInfo requestInfo = myAppData.getSmoiIns().getRequestInfo();
            String session = smoiIns.getSsid();
            String invokeid = requestInfo.getInvokeid();
            String state = myAppData.getInput_state();
            String msisdn = smoiIns.getMsisdn();
            String command = smoiIns.getPage();
            String input ="";String output ="";
            if(myAppData.getInput_Msg()!=null&&!myAppData.getInput_Msg().trim().equals("")){
            	  input = myAppData.getInput_Msg().replaceAll("\t|\r|\n", "").replaceAll(">\\s*<", "><");
            } 
            if(myAppData.getOutput_Msg()!=null&&!myAppData.getOutput_Msg().trim().equals("")){
            	  output = myAppData.getOutput_Msg().replaceAll("\t|\r|\n", "").replaceAll(">\\s*<", "><");
            }
            String resultcode = myAppData.getInput_resultcode();
            String desc = myAppData.getInput_desc();

            String message = "SESSIONID=" + session
                    + "|INVOKE=" + invokeid
                    + "|STATE=" + state
                    + "|MSISDN=" + msisdn
                    + "|COMMAND=" + command
                    + "|INPUT=" + input
                    + "|NEXTSTATE=" + _nextstate
                    + "|OUTPUT=" + output;

            if (resultcode != null && !"".equals(resultcode)) {
                message = message + "|RESULTCODE=" + resultcode + "|DESC=" + desc;
            }
            af.getUtils().writeLog(Log_Detail, message);
        }
    }
}
