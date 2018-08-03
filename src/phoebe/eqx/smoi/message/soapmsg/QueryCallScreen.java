/**
 * @version 1.0
 * @author pawarich
 */
package phoebe.eqx.smoi.message.soapmsg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ec02.exception.MessageParserException;
import ec02.utils.Log;
import phoebe.eqx.smoi.utils.HeaderMessage;
import phoebe.eqx.smoi.utils.JAXBUtils;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoQueryCallScreen;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoQueryCallScreenResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class QueryCallScreen {

    private DoQueryCallScreen doQueryCallScreen;
    private DoQueryCallScreenResponse doQueryCallScreenResponse;

    public String buildSoapMessage(DoQueryCallScreen _doQueryCallScreen) {
        String SoapMessage = null;
        this.doQueryCallScreen = _doQueryCallScreen;

        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doQueryCallScreen, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/QueryCallScreen", "do_QueryCallScreen");
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
        return SoapMessage;
    }

    public void CreateObj(String input) {
        try {
            input = HeaderMessage.pureBSSMessageResponse(input);
            InputStream inputstream = new ByteArrayInputStream(input.getBytes());
            ObjectFactory factory = new ObjectFactory();
            this.doQueryCallScreenResponse = factory.createDoQueryCallScreenResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doQueryCallScreenResponse = (DoQueryCallScreenResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoQueryCallScreen(DoQueryCallScreen doQueryCallScreen) {
        this.doQueryCallScreen = doQueryCallScreen;
    }

    public DoQueryCallScreen getDoQueryCallScreen() {
        return doQueryCallScreen;
    }

    public void setDoQueryCallScreenResponse(DoQueryCallScreenResponse doQueryCallScreenResponse) {
        this.doQueryCallScreenResponse = doQueryCallScreenResponse;
    }

    public DoQueryCallScreenResponse getDoQueryCallScreenResponse() {
        return doQueryCallScreenResponse;
    }
}
