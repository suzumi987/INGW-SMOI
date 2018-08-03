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
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoMailGroupManage;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoMailGroupManageResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.MailGroup;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.MailGroupMemberList;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.MailGroupPost;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class ManageGroupMemberService {
    //private DoManageGroupMember

    private DoMailGroupManage doMailGroupManage;
    private DoMailGroupManageResponse doMailGroupManageResponse;

    public String builSoapMessage(SOperInfo sOper, MailGroup mailGroup,
            MailGroupMemberList mailGroupMemberList,
            MailGroupPost mailGroupPost, short _notifyFlag) {
        String SoapMessage = null;
        doMailGroupManage = new DoMailGroupManage();
        doMailGroupManage.setSOper(sOper);
        doMailGroupManage.setMailGroup(mailGroup);
        doMailGroupManage.setMailGroupMemberList(mailGroupMemberList);
        doMailGroupManage.setMailGroupPost(mailGroupPost);
        if (_notifyFlag != -1) {
            doMailGroupManage.setNotificationFlag(_notifyFlag);
        }

        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doMailGroupManage, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/MailGroupManagement", "do_MailGroupManage");
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
            this.doMailGroupManage = factory.createDoMailGroupManage();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doMailGroupManage = (DoMailGroupManage) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoMailGroupManage(DoMailGroupManage doMailGroupManage) {
        this.doMailGroupManage = doMailGroupManage;
    }

    public DoMailGroupManage getDoMailGroupManage() {
        return doMailGroupManage;
    }

    public void setDoMailGroupManageResponse(DoMailGroupManageResponse doMailGroupManageResponse) {
        this.doMailGroupManageResponse = doMailGroupManageResponse;
    }

    public DoMailGroupManageResponse getDoMailGroupManageResponse() {
        return doMailGroupManageResponse;
    }
}
