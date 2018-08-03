package phoebe.eqx.smoi.message.soapmsg;
import phoebe.eqx.smoi.message.parser.SoapMessageParser;

public class QueryResponseHeader {
	
	public MDResponse readResponseMD(String message){
		SoapMessageParser messageParser = new SoapMessageParser();
		MDResponse requestHeader = messageParser.parserXml(message, MDResponse.class);
		return requestHeader;
	}

}
