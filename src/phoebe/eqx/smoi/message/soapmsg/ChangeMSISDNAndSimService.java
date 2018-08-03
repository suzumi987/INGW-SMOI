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
import phoebe.eqx.smoi.wsdl.CommonComponents.OneTimeFee;
import phoebe.eqx.smoi.wsdl.CommonComponents.SOperInfo;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoChangeMSISDNAndSIM;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoChangeMSISDNAndSIMResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class ChangeMSISDNAndSimService {

    private DoChangeMSISDNAndSIM doChangeMSISDNAndSIM;
    private DoChangeMSISDNAndSIMResponse doChangeMSISDNAndSIMResponse;

    public String builSoapMessage(SOperInfo _sOper, String _cust_id,
            String _acct_id, long _user_id, String _phone_id,
            short _change_type, String _new_phone_id,
            String _old_imsi, String _new_imsi,
            OneTimeFee OneTimeFee, short _notification_flag) {
        String SoapMessage = "";
        doChangeMSISDNAndSIM = new DoChangeMSISDNAndSIM();
        doChangeMSISDNAndSIM.setSOper(_sOper);
        doChangeMSISDNAndSIM.setCustId(_cust_id);
        doChangeMSISDNAndSIM.setAcctId(_acct_id);
        if (_user_id != -1) {
            doChangeMSISDNAndSIM.setUserId(_user_id);
        }
        doChangeMSISDNAndSIM.setPhoneId(_phone_id);
        if (_change_type != -1) {
            doChangeMSISDNAndSIM.setChangeType(_change_type);
        }
        doChangeMSISDNAndSIM.setNewPhoneId(_new_phone_id);
        doChangeMSISDNAndSIM.setOldImsi(_old_imsi);
        doChangeMSISDNAndSIM.setNewImsi(_new_imsi);
        doChangeMSISDNAndSIM.setOneTimeFee(OneTimeFee);
        if (_notification_flag != -1) {
            doChangeMSISDNAndSIM.setNotificationFlag(_notification_flag);
        }

        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doChangeMSISDNAndSIM, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/ChangeMSISDNAndSim", "do_ChangeMSISDNAndSIMReq");
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
            this.doChangeMSISDNAndSIMResponse = factory.createDoChangeMSISDNAndSIMResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doChangeMSISDNAndSIMResponse = (DoChangeMSISDNAndSIMResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoChangeMSISDNAndSIM(DoChangeMSISDNAndSIM doChangeMSISDNAndSIM) {
        this.doChangeMSISDNAndSIM = doChangeMSISDNAndSIM;
    }

    public DoChangeMSISDNAndSIM getDoChangeMSISDNAndSIM() {
        return doChangeMSISDNAndSIM;
    }

    public void setDoChangeMSISDNAndSIMResponse(
            DoChangeMSISDNAndSIMResponse doChangeMSISDNAndSIMResponse) {
        this.doChangeMSISDNAndSIMResponse = doChangeMSISDNAndSIMResponse;
    }

    public DoChangeMSISDNAndSIMResponse getDoChangeMSISDNAndSIMResponse() {
        return doChangeMSISDNAndSIMResponse;
    }
}
