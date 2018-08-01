package phoebe.eqx.smoi.utils;

import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.enums.ECommand;

public class ResponseCode {
	
	public static String responsecode (String res,SmoiInstance smoiIns){
		String message = res;
		if(res.contains("%s")){
			message = message.replaceAll("%s", ECommand.getStatPrint(smoiIns.getMapCmd()));
		}
		return message;
	}
}
