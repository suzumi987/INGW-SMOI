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
import phoebe.eqx.smoi.wsdl.CommonComponents.STopUpInfo;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoTopUpByCash;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoTopUpByCashResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class TopupByCashService {

    private DoTopUpByCash doTopUpByCash;
    private DoTopUpByCashResponse doTopUpByCashResponse;

    public String builSoapMessage(SOperInfo _sOper,
            STopUpInfo sTopUpInfo,
            short _notification_flag) {

        String SoapMessage = null;

        doTopUpByCash = new DoTopUpByCash();
        doTopUpByCash.setSOper(_sOper);
        doTopUpByCash.setSTopUpInfo(sTopUpInfo);
        if (_notification_flag != -1) {
            doTopUpByCash.setNotificationFlag(_notification_flag);
        }
        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(doTopUpByCash, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/TopupByCash", "do_topUpByCash");
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
            this.doTopUpByCashResponse = factory.createDoTopUpByCashResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doTopUpByCashResponse = (DoTopUpByCashResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoTopUpByCash(DoTopUpByCash doTopUpByCash) {
        this.doTopUpByCash = doTopUpByCash;
    }

    public DoTopUpByCash getDoTopUpByCash() {
        return doTopUpByCash;
    }

    public void setDoTopUpByCashResponse(DoTopUpByCashResponse doTopUpByCashResponse) {
        this.doTopUpByCashResponse = doTopUpByCashResponse;
    }

    public DoTopUpByCashResponse getDoTopUpByCashResponse() {
        return doTopUpByCashResponse;
    }
}
