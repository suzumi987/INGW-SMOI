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
import phoebe.eqx.smoi.wsdl.CommonComponents.SUserAuth;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoManageSCPProfile;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoManageSCPProfileResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class ManageSCPprofileService {

    private DoManageSCPProfile doManageSCPProfile;
    private DoManageSCPProfileResponse doManageSCPProfileResponse;

    public String builSoapMessage(SOperInfo sOper, String _phone_id,
            SUserAuth sUserAuth, short _USSDcallBackFlag,
            short _FHPFlag,
            short _ICFFlag, String ICFNumber, OneTimeFee oneTimeFee) {
        String SoapMessage = null;
        doManageSCPProfile = new DoManageSCPProfile();
        doManageSCPProfile.setSOper(sOper);
        doManageSCPProfile.setPhoneId(_phone_id);
        doManageSCPProfile.setSUserAuth(sUserAuth);
        doManageSCPProfile.setUSSDCallbackFlag(_USSDcallBackFlag);
        //doManageSCPProfile.setFHPFlag(_FHPFlag);
        doManageSCPProfile.setICFFlag(_ICFFlag);
        doManageSCPProfile.setICFNumber(ICFNumber);
        doManageSCPProfile.setOneTimeFee(oneTimeFee);

        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doManageSCPProfile, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/ManageSCPprofile", "do_ManageSCPProfile");
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
            this.doManageSCPProfileResponse = factory.createDoManageSCPProfileResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doManageSCPProfileResponse = (DoManageSCPProfileResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoManageSCPProfile(DoManageSCPProfile doManageSCPProfile) {
        this.doManageSCPProfile = doManageSCPProfile;
    }

    public DoManageSCPProfile getDoManageSCPProfile() {
        return doManageSCPProfile;
    }

    public void setDoManageSCPProfileResponse(DoManageSCPProfileResponse doManageSCPProfileResponse) {
        this.doManageSCPProfileResponse = doManageSCPProfileResponse;
    }

    public DoManageSCPProfileResponse getDoManageSCPProfileResponse() {
        return doManageSCPProfileResponse;
    }
}
