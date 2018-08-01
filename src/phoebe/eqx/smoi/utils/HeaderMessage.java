/**
 * @version 1.0
 * @author pawarich
 */
package phoebe.eqx.smoi.utils;

import ec02.enums.EInstanceMode;
import ec02.exception.MessageParserException;
import ec02.utils.MessageParser;

public class HeaderMessage {

    public static String addHeader(String xmlMessage, String uri, String soapActionMethod) {
        xmlMessage = xmlMessage.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        StringBuilder soapMessage = new StringBuilder("");
        soapMessage.append("<UriOverride value=\"").append(uri).append("\" />\n");
        soapMessage.append("<HeaderOverride name=\"SOAPAction\" value=\"").append(soapActionMethod).append("\" />\n");
        soapMessage.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n<soapenv:Header/>\n<soapenv:Body>");
        soapMessage.append(xmlMessage);
        soapMessage.append("</soapenv:Body>\n</soapenv:Envelope>");
        return soapMessage.toString();
    }

    public static String pureBSSMessageResponse(String requestMessage) throws MessageParserException {
        String pureMessage = requestMessage.replaceAll("\t|\r|\n", "");
        MessageParser parser = new MessageParser(EInstanceMode.STRING);
        parser.setXMLMessage(pureMessage);

        parser.processWithRexExpression("<soapenv:Body.*</soapenv:Body>", "</soapenv:Body>");
        pureMessage = parser.getInnerText().trim();
        pureMessage = pureMessage.replaceAll("<soapenv:Body.*?>", "");
        if (pureMessage.equals("")) {
            parser.processWithRexExpression("<soap:Body.*</soap:Body>", "</soap:Body>");
            pureMessage = parser.getInnerText().trim();
            pureMessage = pureMessage.replaceAll("<soap:Body.*?>", "");
        }
        return pureMessage;
    }
}
