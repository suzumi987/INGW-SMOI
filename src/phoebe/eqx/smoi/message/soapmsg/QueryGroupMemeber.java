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
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoQueryGroupMember;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoQueryGroupMemberResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class QueryGroupMemeber {

    private DoQueryGroupMember doQueryGroupMember;
    private DoQueryGroupMemberResponse doQueryGroupMemberResponse;

    public String builSoapMessage(SOperInfo _sOper,
            String _phone_id,
            String _short_phone_id,
            String _group_id,
            short _group_info_flag) {
        String SoapMessage = null;
        doQueryGroupMember = new DoQueryGroupMember();
        doQueryGroupMember.setSOper(_sOper);
        doQueryGroupMember.setPhoneId(_phone_id);
        doQueryGroupMember.setShortPhoneId(_short_phone_id);
        doQueryGroupMember.setGroupId(_group_id);
        if (_group_info_flag != -1) {
            doQueryGroupMember.setGroupInfoFlag(_group_info_flag);
        }

        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doQueryGroupMember, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/QueryGroupMember", "do_QueryGroupMember");
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
            this.doQueryGroupMemberResponse = factory.createDoQueryGroupMemberResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doQueryGroupMemberResponse = (DoQueryGroupMemberResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoQueryGroupMember(DoQueryGroupMember doQueryGroupMember) {
        this.doQueryGroupMember = doQueryGroupMember;
    }

    public DoQueryGroupMember getDoQueryGroupMember() {
        return doQueryGroupMember;
    }

    public void setDoQueryGroupMemberResponse(DoQueryGroupMemberResponse doQueryGroupMemberResponse) {
        this.doQueryGroupMemberResponse = doQueryGroupMemberResponse;
    }

    public DoQueryGroupMemberResponse getDoQueryGroupMemberResponse() {
        return doQueryGroupMemberResponse;
    }
}
