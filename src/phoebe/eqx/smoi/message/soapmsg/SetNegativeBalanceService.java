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

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

import ec02.exception.MessageParserException;
import ec02.utils.Log;
import phoebe.eqx.smoi.utils.HeaderMessage;
import phoebe.eqx.smoi.utils.JAXBUtils;
import phoebe.eqx.smoi.wsdl.MyNamespaceMapper;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoSetNegativeBalance;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoSetNegativeBalanceResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class SetNegativeBalanceService {

    private DoSetNegativeBalance doSetNegativeBalance = new DoSetNegativeBalance();
    private DoSetNegativeBalanceResponse doSetNegativeBalanceResponse;

    public String buildSoapMessage(DoSetNegativeBalance _doSetNegativeBalance) {
        String SoapMessage = null;
        this.doSetNegativeBalance = _doSetNegativeBalance;
        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new MyNamespaceMapper());
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doSetNegativeBalance, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/SetNegativeBalance", "setNegativeBalance");
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
            this.doSetNegativeBalanceResponse = factory.createDoSetNegativeBalanceResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doSetNegativeBalanceResponse = (DoSetNegativeBalanceResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoSetNegativeBalanceResponse(
            DoSetNegativeBalanceResponse doSetNegativeBalanceResponse) {
        this.doSetNegativeBalanceResponse = doSetNegativeBalanceResponse;
    }

    public DoSetNegativeBalanceResponse getDoSetNegativeBalanceResponse() {
        return doSetNegativeBalanceResponse;
    }

    public void setDoSetNegativeBalance(DoSetNegativeBalance doSetNegativeBalance) {
        this.doSetNegativeBalance = doSetNegativeBalance;
    }

    public DoSetNegativeBalance getDoSetNegativeBalance() {
        return doSetNegativeBalance;
    }
}
