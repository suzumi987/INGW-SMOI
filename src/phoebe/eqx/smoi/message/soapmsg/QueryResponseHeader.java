package phoebe.eqx.smoi.message.soapmsg;
import phoebe.eqx.smoi.message.parser.SoapMessageParser;

public class QueryResponseHeader {
	
	public CBSSubstatus readResponseMD(String message){
		SoapMessageParser messageParser = new SoapMessageParser();
		CBSSubstatus requestHeader = messageParser.parserXml(message, CBSSubstatus.class);
		return requestHeader;
	}
	
//	public RequestHeader readRequestHeader(String message) {
//
//        SoapMessageParser messageParser = new SoapMessageParser();
//
//        RequestHeader requestHeader = messageParser.parserXml(message, RequestHeader.class);
////
//        AppLog.i("Model = " + requestHeader);
//
////
//        return requestHeader;
//    }
//	
//	public ResponseParameters readResponseParameters(String message) {
//
//        SoapMessageParser messageParser = new SoapMessageParser();
//
//        ResponseParameters responseParameters = messageParser.parserXml(message, ResponseParameters.class);
////
//        AppLog.i("Model = " + responseParameters);
//
////
//        return responseParameters;
//    }
}
