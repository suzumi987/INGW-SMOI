/**
 * @version 1.0
 * @author pawarich
 */
package phoebe.eqx.smoi.message.soapmsg;

import ec02.utils.AppLog;
import phoebe.eqx.smoi.bean.bmpManageSCPProfile.ManageSCPProfileRes;
import phoebe.eqx.smoi.message.parser.SoapMessageParser;


public class ManageSCPProfile {
    
    
    public ManageSCPProfileRes readManageSCPProfileRes(String message) {

		SoapMessageParser messageParser = new SoapMessageParser();

		ManageSCPProfileRes manageSCPProfileRes = messageParser.parserXml(message, ManageSCPProfileRes.class);
//
		AppLog.i("Model = " + manageSCPProfileRes);

//
		return manageSCPProfileRes;
	}
    

}
