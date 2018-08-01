/**
 * @version 1.0
 * @author pawarich
 */
package phoebe.eqx.smoi.message.soapmsg;

import ec02.utils.AppLog;
import phoebe.eqx.smoi.bean.bmpQuerySCPProfile.QuerySCPProfileRes;
import phoebe.eqx.smoi.message.parser.SoapMessageParser;

public class QuerySCPProfile {

    public QuerySCPProfileRes readQuerySCPProfileres(String message) {

        SoapMessageParser messageParser = new SoapMessageParser();

        QuerySCPProfileRes querySCPProfileRes = messageParser.parserXml(message, QuerySCPProfileRes.class);
//
        AppLog.i("Model = " + querySCPProfileRes);

//
        return querySCPProfileRes;
    }

//    private DoQuerySCPProfile doQuerySCPProfile;
//    private DoQuerySCPProfileResponse doQuerySCPProfileResponse;
//
//    public String buildSoapMessage(DoQuerySCPProfile doQuerySCPProfile) {
//        String SoapMessage = null;
//        this.doQuerySCPProfile = doQuerySCPProfile;
//
//        try {
////            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
////            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
////            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
////            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
////            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        	Marshaller marshaller = JAXBUtils.getMarshaller();
//        	OutputStream outputStream = new ByteArrayOutputStream();
//            marshaller.marshal(this.doQuerySCPProfile, outputStream);
//            SoapMessage = outputStream.toString();
//            SoapMessage = HeaderMessage.addHeader(SoapMessage, "/BSSBroker/Gateways/QuerySCPProfile", "do_QuerySCPProfile");
//        } catch (javax.xml.bind.JAXBException e) {
//            Log.e("Error Massage:" + e.getMessage(), e);
//        }
//        return SoapMessage;
//    }
//
//    public void CreateObj(String input) {
//        try {
//            input = HeaderMessage.pureBSSMessageResponse(input);
//            InputStream inputstream = new ByteArrayInputStream(input.getBytes());
//            ObjectFactory factory = new ObjectFactory();
//            this.doQuerySCPProfileResponse = factory.createDoQuerySCPProfileResponse();
////            ClassLoader cl = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
////            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", cl);
////            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
//            Unmarshaller unmarshaller = JAXBUtils.getUnmarshaller();
//            this.doQuerySCPProfileResponse = (DoQuerySCPProfileResponse) unmarshaller.unmarshal(inputstream); // NOI18N
//        } catch (javax.xml.bind.JAXBException e) {
//            Log.e("Error Massage:" + e.getMessage(), e);
//        } catch (MessageParserException e) {
//            Log.e("Error Massage:" + e.getMessage(), e);
//        }
//    }
//
//	public DoQuerySCPProfile getDoQuerySCPProfile() {
//		return doQuerySCPProfile;
//	}
//
//	public void setDoQuerySCPProfile(DoQuerySCPProfile doQuerySCPProfile) {
//		this.doQuerySCPProfile = doQuerySCPProfile;
//	}
//
//	public DoQuerySCPProfileResponse getDoQuerySCPProfileResponse() {
//		return doQuerySCPProfileResponse;
//	}
//
//	public void setDoQuerySCPProfileResponse(DoQuerySCPProfileResponse doQuerySCPProfileResponse) {
//		this.doQuerySCPProfileResponse = doQuerySCPProfileResponse;
//	}

	

}
