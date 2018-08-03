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
import phoebe.eqx.smoi.wsdl.CommonComponents.SOperInfo;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoQuery;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.DoQueryResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.QueryTypeList;

public class Query {

    private DoQuery doQuery;
    private DoQueryResponse doQueryResponse;

    public String builSoapMessage(SOperInfo _sOper, String _acct_id,
            String _phone_id, Long _product_sequence_id,
            QueryTypeList _queryTypeList, XMLGregorianCalendar _start_date,
            XMLGregorianCalendar _end_date, String _invoice_no) {
        String SoapMessage = null;
        this.setDoQuery(new DoQuery());
        this.doQuery.setSOper(_sOper);
        this.doQuery.setAcctId(_acct_id);
        this.doQuery.setPhoneId(_phone_id);
        this.doQuery.setProductSequenceId(_product_sequence_id);
        this.doQuery.setQueryTypeList(_queryTypeList);
        this.doQuery.setStartDate(_start_date);
        this.doQuery.setEndDate(_end_date);
        this.doQuery.setInvoiceNo(_invoice_no);

        try {
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	Marshaller marshaller = JAXBUtils.getMarshaller();
        	OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(this.getDoQuery(), outputStream);
            SoapMessage = outputStream.toString();
            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/Query", "do_Query");
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
            this.doQueryResponse = factory.createDoQueryResponse();
//            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
            this.doQueryResponse = (DoQueryResponse) unmarshaller.unmarshal(inputstream); // NOI18N
            System.out.print(this.doQueryResponse);
        } catch (javax.xml.bind.JAXBException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        } catch (MessageParserException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
    }

    public void setDoQuery(DoQuery doQuery) {
        this.doQuery = doQuery;
    }

    public DoQuery getDoQuery() {
        return doQuery;
    }

    public void setDoQueryResponse(DoQueryResponse doQueryResponse) {
        this.doQueryResponse = doQueryResponse;
    }

    public DoQueryResponse getDoQueryResponse() {
        return doQueryResponse;
    }

    public static void main(String[] args) throws DatatypeConfigurationException {
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

        String phone_id = "878740115";
        QueryTypeList queryTypeList = new QueryTypeList();
        queryTypeList.getQueryType().add((short) 1);
        queryTypeList.getQueryType().add((short) 5);
        //queryTypeList.getQueryType().add((short)16);
        queryTypeList.getQueryType().add((short) 13);
        queryTypeList.getQueryType().add((short) 18);

        Query query = new Query();
        String XmlMessage = query.builSoapMessage(_sOper, null, phone_id, null, queryTypeList, gc, gc, null);

        Log.d("<-----------------Input----------------->");
        Log.d(XmlMessage);
//		String output = TestSendToBSSBroker.Send(XmlMessage, "/BSSBroker/Gateways/Query");
//		query.CreateObj(output);
//		Log.d("Output :" + output);
//		DoQueryResponse doQueryResponse = query.getDoQueryResponse();
//		Log.d("<-----------------Output----------------->");
//		Log.d("_so_nb :" + doQueryResponse.getSResult().getSoNbr());
//		Log.d("_bos_so_nbr :" + doQueryResponse.getSResult().getBosSoNbr());
//		Log.d("_finish_date :" + doQueryResponse.getSResult().getFinishDate());
//		Log.d("_result_code :" + doQueryResponse.getSResult().getResultCode());
//		Log.d("_error_msg :" + doQueryResponse.getSResult().getErrorMsg());
    }
}
