package phoebe.eqx.smoi.utils;

import ec02.af.abstracts.AbstractAF;


import ec02.af.enums.EStatType;
import ec02.utils.Log;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.enums.ECommand;


public class SmoiStatAlarm {
    
    public static void incrementStats(AbstractAF af, String command, String stat, SmoiInstance smoiIns) {
        String message = stat;
        /*if(smoiIns.isFlagModiPPSCallNotify() == true && stat.contains("%s") && stat.contains("BSSBroker")){
        	
        	message = message.replaceAll("%s", "DoManageSCPProfile");
        	message = message.replaceAll("BOS ", "");
        	message = message.replaceAll(" Success", "");
        	
        } else if(smoiIns.isBssbrokerFlag() == true && stat.contains("%s") && stat.contains("BSSBroker")){
        	
        	message = message.replaceAll("%s", "DoQuerySCPProfile");
        	message = message.replaceAll("BOS ", "");
        	message = message.replaceAll(" Success", "");
        	
        } else*/ if (stat.contains("%s")) {
//            char firstWord = command.charAt(0);
//            char upperFirstWord = firstWord; 
//            if(isFirstChar2UpperCase){
//            	 upperFirstWord = Character.toUpperCase(firstWord); 
//            }
        	
//          
//            command = upperFirstWord + command.substring(1, command.length());
//            if(smoiIns.isFlagModiPPSCallNotify() == true){
//            	message = message.replaceAll("%s", "ModiPPSCallNotifyFlag");
//            } else {
//            	message = message.replaceAll("%s", command);
            	message = message.replaceAll("%s", ECommand.getStatPrint(command));
//            }
        }
        Log.d("Send Stat:" + message);
        af.getUtils().incrementStats(message, EStatType.INCREMENT);
    }
    
    

//    public static void incrementStatsAndRaiseAlarm(AbstractAF af, String stat_alarm) {
//        String[] Parameter = {""};
//        af.getUtils().incrementStats(stat_alarm);
//        af.getUtils().raiseAlarm(stat_alarm, Parameter, AlarmSeverity.WARNING, AlarmCategory.APPLICATION, AlarmType.Normal);
//    }
//    
//    public static void raiseAlarm(AbstractAF af, String stat_alarm) {
//        String[] Parameter = {""};
//        af.getUtils().raiseAlarm(stat_alarm, Parameter, AlarmSeverity.WARNING, AlarmCategory.APPLICATION, AlarmType.Normal);
//    }
}
