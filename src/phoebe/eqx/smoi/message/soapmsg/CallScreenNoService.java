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
import phoebe.eqx.smoi.wsdl.CommonComponents.SCallScreenNo;
import phoebe.eqx.smoi.wsdl.CommonComponents.SOperInfo;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.CallScreenNoInfoList;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoCallScreenNo;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoCallScreenNoResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;

public class CallScreenNoService {

    private DoCallScreenNo doCallScreenNo;
    private DoCallScreenNoResponse doCallScreenNoResponse;

    public String buildSoapMessage(DoCallScreenNo _doCallScreenNo) {
        String SoapMessage = "";
        this.doCallScreenNo = _doCallScreenNo;

        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.doCallScreenNo, outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/ManageCallScreen", "do_CallScreenNo");
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
            this.doCallScreenNoResponse = factory.createDoCallScreenNoResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doCallScreenNoResponse = (DoCallScreenNoResponse) unmarshaller.unmarshal(inputstream); // NOI18N
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoCallScreenNo(DoCallScreenNo doCallScreenNo) {
        this.doCallScreenNo = doCallScreenNo;
    }

    public DoCallScreenNo getDoCallScreenNo() {
        return doCallScreenNo;
    }

    public void setDoCallScreenNoResponse(DoCallScreenNoResponse doCallScreenNoResponse) {
        this.doCallScreenNoResponse = doCallScreenNoResponse;
    }

    public DoCallScreenNoResponse getDoCallScreenNoResponse() {
        return doCallScreenNoResponse;
    }

//    public static void main(String[] args) throws DatatypeConfigurationException {
//        //CallScreenNoService callScreenNoService = new CallScreenNoService();
//        SOperInfo _sOper = new SOperInfo();
//        _sOper.setSoNbr("12345");
//        _sOper.setBusiCode("1042");
//        _sOper.setSoMode("IVR");
//        _sOper.setSourceSystem("olympus");
//        _sOper.setChargeFlag((short) 0);
//        Date date = new Date();
//        GregorianCalendar gregory = new GregorianCalendar();
//        gregory.setTime(date);
//        XMLGregorianCalendar gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
//        _sOper.setSoDate(gc);
//        _sOper.setPhoneId("878700000");
//        //String _phone_id = "878700000";
//        //String _group_id = "";
//        //short _operationType = 0;
//        //short _call_screen_no_type = 0;
//        CallScreenNoInfoList _callScreenNoInfoList = new CallScreenNoInfoList();
//        SCallScreenNo sCallScreenNo = new SCallScreenNo();
//        sCallScreenNo.setCallscreenNo("878700001");
//        sCallScreenNo.setEffectiveDate(gc);
//        sCallScreenNo.setExpireDate(gc);
////        sCallScreenNo.setEffectTime("090000");
////        sCallScreenNo.setEffectTime("180000");
//        sCallScreenNo.setListType((short) 0);
//        _callScreenNoInfoList.getCallScreenNoInfoListItem().add(sCallScreenNo);
// //       String input = callScreenNoService.buildSoapMessage(_sOper, _phone_id, _group_id, _operationType, _call_screen_no_type, _callScreenNoInfoList, null);
//        Log.d("<-----------------Input----------------->");
////        Log.d(input);
////        String output = TestSendToBSSBroker.Send(input, "/BSSBroker/Gateways/ManageCallScreen");
////        callScreenNoService.CreateObj(output);
////        DoCallScreenNoResponse doCallScreenNoResponse = callScreenNoService.getDoCallScreenNoResponse();
////        Log.d("<-----------------Output----------------->");
////        Log.d("_so_nb :" + doCallScreenNoResponse.getSResult().getSoNbr());
////        Log.d("_bos_so_nbr :" + doCallScreenNoResponse.getSResult().getBosSoNbr());
////        Log.d("_finish_date :" + doCallScreenNoResponse.getSResult().getFinishDate());
////        Log.d("_result_code :" + doCallScreenNoResponse.getSResult().getResultCode());
////        Log.d("_error_msg :" + doCallScreenNoResponse.getSResult().getErrorMsg());
//    }
}
