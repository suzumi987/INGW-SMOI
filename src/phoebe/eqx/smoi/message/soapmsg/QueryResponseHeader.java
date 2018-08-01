package phoebe.eqx.smoi.message.soapmsg;

import phoebe.eqx.smoi.wsdl.InfoSyncDefines.RequestHeader;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ResponseParameters;
import phoebe.eqx.smoi.message.parser.SoapMessageParser;
import ec02.utils.AppLog;

public class QueryResponseHeader {
	public RequestHeader readRequestHeader(String message) {

        SoapMessageParser messageParser = new SoapMessageParser();

        RequestHeader requestHeader = messageParser.parserXml(message, RequestHeader.class);
//
        AppLog.i("Model = " + requestHeader);

//
        return requestHeader;
    }
	
	public ResponseParameters readResponseParameters(String message) {

        SoapMessageParser messageParser = new SoapMessageParser();

        ResponseParameters responseParameters = messageParser.parserXml(message, ResponseParameters.class);
//
        AppLog.i("Model = " + responseParameters);

//
        return responseParameters;
    }
}
