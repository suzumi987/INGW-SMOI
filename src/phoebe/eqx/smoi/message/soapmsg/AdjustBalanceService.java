/**
 * @version 1.0
 * @author pawarich
 */
package phoebe.eqx.smoi.message.soapmsg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import ec02.exception.MessageParserException;
import ec02.utils.Log;
import phoebe.eqx.smoi.utils.HeaderMessage;
import phoebe.eqx.smoi.utils.JAXBUtils;
import phoebe.eqx.smoi.utils.TestSendToBSSBroker;
import phoebe.eqx.smoi.wsdl.CommonComponents.FreeResourceList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SBalance;
import phoebe.eqx.smoi.wsdl.CommonComponents.SOperInfo;
import phoebe.eqx.smoi.wsdl.MyNamespaceMapper;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoAdjustBalance;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoAdjustBalanceResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class AdjustBalanceService {

    private DoAdjustBalance doAdjustBalance;
    private DoAdjustBalanceResponse doAdjustBalanceResponse;

    public String buildSoapMessage(DoAdjustBalance doAdjustBalance) {
        String SoapMessage = "";

         this.doAdjustBalance = doAdjustBalance;
        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Marshaller marshaller = JAXBUtils.getMarshaller();
            marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new MyNamespaceMapper());
            OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doAdjustBalance, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/AdjustBalance", "do_AdjustBalance");
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
            this.doAdjustBalanceResponse = factory.createDoAdjustBalanceResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doAdjustBalanceResponse = (DoAdjustBalanceResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoAdjustBalance(DoAdjustBalance doAdjustBalance) {
        this.doAdjustBalance = doAdjustBalance;
    }

    public DoAdjustBalance getDoAdjustBalance() {
        return doAdjustBalance;
    }

    public void setDoAdjustBalanceResponse(DoAdjustBalanceResponse doAdjustBalanceResponse) {
        this.doAdjustBalanceResponse = doAdjustBalanceResponse;
    }

    public DoAdjustBalanceResponse getDoAdjustBalanceResponse() {
        return doAdjustBalanceResponse;
    }

    /*public static void main(String[] args) throws DatatypeConfigurationException {
        AdjustBalanceService adjustBalance = new AdjustBalanceService();
        SOperInfo _sOper = new SOperInfo();
        _sOper.setSoNbr("12345");
        _sOper.setBusiCode("1042");
        _sOper.setSoMode("IVR");
        _sOper.setSourceSystem("olympus");
        _sOper.setChargeFlag((short) 0);
        Date date = new Date();
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(date);
        XMLGregorianCalendar gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        _sOper.setSoDate(gc);
        _sOper.setPhoneId("878740115");
        short _adjust_type = 0;
        String _acct_id = "";
        String _phone_id = "878740115";
        short _flag = 0;
        short _maximum_flag = 0;
        short _rtner = 0;
        SBalance _sBalance = new SBalance();
        _sBalance.setDays((long) 20);
        FreeResourceList _freeResourceList = null;
        String _remark = "";
        short _notify_flag = 0;
        DoAdjustBalance doAdjustBalance1 = new DoAdjustBalance();
        doAdjustBalance1.setSOper(_sOper);
        String input = adjustBalance.buildSoapMessage( doAdjustBalance1);
        System.out.println("<-----------------Input----------------->");
        System.out.println(input);
        String output = TestSendToBSSBroker.Send(input, "/BSSBroker/Gateways/AdjustBalance");
        adjustBalance.CreateObj(output);
        System.out.println("Output :" + output);
        DoAdjustBalanceResponse doAdjustBalanceResponse = adjustBalance.getDoAdjustBalanceResponse();
        System.out.println("<-----------------Output----------------->");
        System.out.println("_so_nb :" + doAdjustBalanceResponse.getSResult().getSoNbr());
        System.out.println("_bos_so_nbr :" + doAdjustBalanceResponse.getSResult().getBosSoNbr());
        System.out.println("_finish_date :" + doAdjustBalanceResponse.getSResult().getFinishDate());
        System.out.println("_result_code :" + doAdjustBalanceResponse.getSResult().getResultCode());
        System.out.println("_error_msg :" + doAdjustBalanceResponse.getSResult().getErrorMsg());
    }*/
}
