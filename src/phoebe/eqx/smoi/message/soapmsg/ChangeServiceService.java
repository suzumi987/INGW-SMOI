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
import phoebe.eqx.smoi.wsdl.CommonComponents.SOperInfo;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ChangeService;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoChangeService;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoChangeServiceResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class ChangeServiceService {

    private DoChangeService doChangeService = new DoChangeService();
    private DoChangeServiceResponse doChangeServiceResponse;

    public String buildSoapMessage(DoChangeService _doChangeService) {
        String SoapMessage = null;
        doChangeService = _doChangeService;
        
        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doChangeService, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/ChangeServiceByCRM", "do_ChangeService");
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
            this.doChangeServiceResponse = factory.createDoChangeServiceResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doChangeServiceResponse = (DoChangeServiceResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoChangeServiceResponse(DoChangeServiceResponse doChangeServiceResponse) {
        this.doChangeServiceResponse = doChangeServiceResponse;
    }

    public DoChangeServiceResponse getDoChangeServiceResponse() {
        return doChangeServiceResponse;
    }

    public void setDoChangeService(DoChangeService doChangeService) {
        this.doChangeService = doChangeService;
    }

    public DoChangeService getDoChangeService() {
        return doChangeService;
    }

//    public static void main(String ages[]) {
//        ChangeServiceService changeServiceService = new ChangeServiceService();
//        SOperInfo sOperInfo = new SOperInfo();
//        sOperInfo.setAcctId("123");
//        
//        ChangeService changeService = new ChangeService();
//        changeService.setAcctId("456");
//        changeService.setCustId("1111");
//        
//        changeServiceService.getDoChangeService().getChangeService().setCustId("123");
//        //System.out.print(changeServiceService.buildSoapMessage(sOperInfo, changeService));
//    }
}
