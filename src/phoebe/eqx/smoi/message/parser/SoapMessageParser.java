package phoebe.eqx.smoi.message.parser;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import phoebe.eqx.smoi.bean.bmpManageSCPProfile.ManageSCPProfileRes;
import phoebe.eqx.smoi.bean.bmpQuerySCPProfile.QuerySCPProfileRes;
import phoebe.eqx.smoi.message.soapmsg.MDResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.RequestHeader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class SoapMessageParser {

	public SoapMessageParser() {

	}

	@SuppressWarnings("unchecked")
	public <T> T parserXml(String rawMessge, Class<T> clazz, boolean addRootElement) {
		String processMsg = null;

		if (addRootElement) {
			String XmlString;
			XmlString=rawMessge.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
			
			processMsg = new StringBuilder("<root>").append(XmlString).append("</root>").toString();
		} else {
			processMsg = rawMessge;
		}

		T xmlModel = null;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			if (ManageSCPProfileRes.class.equals(clazz)) {
				ManageSCPProfileRes model = new ManageSCPProfileRes();
				saxParser.parse(new InputSource(new StringReader(processMsg)), processManageSCPProfileRes(model));
				xmlModel = (T) model;
			} else if (QuerySCPProfileRes.class.equals(clazz)) {
				QuerySCPProfileRes model = new QuerySCPProfileRes();
				saxParser.parse(new InputSource(new StringReader(processMsg)), processQuerySCPProfileRes(model));
				xmlModel = (T) model;
			} else if (MDResponse.class.equals(clazz)) {
				MDResponse model = new MDResponse();
				saxParser.parse(new InputSource(new StringReader(processMsg)), processCBSSubstatus(model));
				xmlModel = (T) model;
			}


		} catch (ParserConfigurationException e) {
			e.printStackTrace();

			// AppLog.w(e.getMessage());
		} catch (SAXException e) {
			e.printStackTrace();
			// AppLog.w(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();

			// AppLog.w(e.getMessage());
		}

		// StringBuilder stringBuilder = new StringBuilder();
		// stringBuilder.append("Convert model: ");
		// stringBuilder.append(rawMessge);
		// stringBuilder.append(" => ");
		// stringBuilder.append(xmlModel);

		// System.err.println( xmlModel.toString() );
		// AppLog.d(stringBuilder.toString());

//		addInput.setData(xmlModel);
		// if(xmlModel == null){
		// addInput.setData(null);
		// }else{
		// addInput.setData( xmlModel );
		// }
		return xmlModel;
	}

	public <T> T parserXml(String rawMessge, Class<T> clazz) {
		return parserXml(rawMessge, clazz, true);

	}
	
	private DefaultHandler processManageSCPProfileRes(final ManageSCPProfileRes manageSCPProfileRes) {

		DefaultHandler d = new DefaultHandler() {
			StringBuilder path = new StringBuilder();
			
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				super.startElement(uri, localName, qName, attributes);
				path.append(".").append(qName);
				collect(path.toString(), "", attributes);
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				super.endElement(uri, localName, qName);
				int length = path.length();
				int lengthQName = qName.length() + 1; // +1 == "."
				path.delete(length - lengthQName, length);
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				super.characters(ch, start, length);
				String value = new String(ch, start, length);
				if (!value.trim().isEmpty()) {
					collect(path.toString(), value, null);
				}
			}

			private void collect(String path, String value, Attributes attributes) {
//				System.out.println(path);
                if (".root.soapenv:Envelope.soap:Body.ns1:do_ManageSCPProfileResponse.ns1:sResult.ns0:_so_nbr".equalsIgnoreCase(path)) {
                	manageSCPProfileRes.setSo_nbr(value);
				} else if (".root.soapenv:Envelope.soap:Body.ns1:do_ManageSCPProfileResponse.ns1:sResult.ns0:_finish_date".equalsIgnoreCase(path)) {
					manageSCPProfileRes.setFinish_date(value);
				} else if (".root.soapenv:Envelope.soap:Body.ns1:do_ManageSCPProfileResponse.ns1:sResult.ns0:_result_code".equalsIgnoreCase(path)) {
					manageSCPProfileRes.setResult_code(value);
				}  else if (".root.soapenv:Envelope.soap:Body.ns1:do_ManageSCPProfileResponse.ns1:sResult.ns0:_bos_so_nbr".equalsIgnoreCase(path)) {
					manageSCPProfileRes.setBos_so_nbr(value);
				}  else if (".root.soapenv:Envelope.soap:Body.ns1:do_ManageSCPProfileResponse.ns1:sResult.ns0:_error_msg".equalsIgnoreCase(path)) {
					manageSCPProfileRes.setError_msg(value);
				} 
                
            }
		};

		return d;
	}

	private DefaultHandler processQuerySCPProfileRes(final QuerySCPProfileRes querySCPProfileRes) {

		DefaultHandler d = new DefaultHandler() {
			StringBuilder path = new StringBuilder();

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				super.startElement(uri, localName, qName, attributes);
				path.append(".").append(qName);
				collect(path.toString(), "", attributes);
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				super.endElement(uri, localName, qName);
				int length = path.length();
				int lengthQName = qName.length() + 1; // +1 == "."
				path.delete(length - lengthQName, length);
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				super.characters(ch, start, length);
				String value = new String(ch, start, length);
				if (!value.trim().isEmpty()) {
					collect(path.toString(), value, null);
				}
			}

			private void collect(String path, String value, Attributes attributes) {
//				System.out.println(path);
				if (".root.soapenv:Envelope.soap:Body.ns1:do_QuerySCPProfileResponse.ns1:sResult.ns0:_so_nbr".equalsIgnoreCase(path)) {
					querySCPProfileRes.setSo_nbr(value);
				} else if (".root.soapenv:Envelope.soap:Body.ns1:do_QuerySCPProfileResponse.ns1:sResult.ns0:_finish_date".equalsIgnoreCase(path)) {
					querySCPProfileRes.setFinish_date(value);
				} else if (".root.soapenv:Envelope.soap:Body.ns1:do_QuerySCPProfileResponse.ns1:sResult.ns0:_result_code".equalsIgnoreCase(path)) {
					querySCPProfileRes.setResult_code(value);
				} else if (".root.soapenv:Envelope.soap:Body.ns1:do_QuerySCPProfileResponse.ns1:SUserAuth.ns0:InterIntraflag".equalsIgnoreCase(path)) {
					querySCPProfileRes.setInterIntraflag(value);
				}

			}
		};

		return d;
	}
	
	private DefaultHandler processCBSSubstatus(final MDResponse cbsSubstatus) {

		DefaultHandler d = new DefaultHandler() {
			StringBuilder path = new StringBuilder();

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				super.startElement(uri, localName, qName, attributes);
				path.append(".").append(qName);
				collect(path.toString(), "", attributes);
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				super.endElement(uri, localName, qName);
				int length = path.length();
				int lengthQName = qName.length() + 1; // +1 == "."
				path.delete(length - lengthQName, length);
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				super.characters(ch, start, length);
				String value = new String(ch, start, length);
				if (!value.trim().isEmpty()) {
					collect(path.toString(), value, null);
				}
			}

			private void collect(String path, String value, Attributes attributes) {
//				System.out.println("path: "+path +",   value ::"+value);
				
				//System.out.println("attributes "+attributes.toString());
				if (".root.S:Envelope.S:Body.Response.ResponseHeader.RequestId".equalsIgnoreCase(path)) {
					cbsSubstatus.setRequestId(value);
				}
				else if (".root.S:Envelope.S:Body.Response.ResponseHeader.Status".equalsIgnoreCase(path)) {
					cbsSubstatus.setStatus(value);
				} 
				else if (".root.S:Envelope.S:Body.Response.ResponseHeader.OrderNo".equalsIgnoreCase(path)) {
					cbsSubstatus.setOrderNo(value);
				}
				else if (".root.S:Envelope.S:Body.Response.ResponseHeader.StatusMessage".equalsIgnoreCase(path)) {
					cbsSubstatus.setStatusMessage(value);
				}
				else if (".root.S:Envelope.S:Body.Response.ResponseHeader.StatusMessageId".equalsIgnoreCase(path)) {
					cbsSubstatus.setStatusMessageId(value);
				}
				else if (".root.S:Envelope.S:Body.Response.ResponseHeader.Priority".equalsIgnoreCase(path)) {
					cbsSubstatus.setPriority(value);
				}
				else if (".root.S:Envelope.S:Body.Response.ResponseHeader.ReqUser".equalsIgnoreCase(path)) {
					cbsSubstatus.setReqUser(value);
				}
				else if (".root.S:Envelope.S:Body.Response.ResponseHeader.ReceivedDate".equalsIgnoreCase(path)) {
					cbsSubstatus.setReceivedDate(value);
				}
				else if (".root.S:Envelope.S:Body.Response.ResponseHeader.FinishedDate".equalsIgnoreCase(path)) {
					cbsSubstatus.setFinishedDate(value);
				}
				else if(".root.S:Envelope.S:Body.Response.ResponseParameters.Parameter".equalsIgnoreCase(path)){
//					System.out.println(attributes.getValue(attributes.getQName(0)));
//					System.out.println(attributes.getValue(attributes.getQName(1)));
					
					String val = attributes.getValue(attributes.getQName(1));
					if(attributes.getValue(attributes.getQName(0)) != null ){
						if(attributes.getValue(attributes.getQName(0)).equals("BSO_1")){
							cbsSubstatus.setBSO_1(val);
						}else if(attributes.getValue(attributes.getQName(0)).equals("REQ_TYPE_1")){
							cbsSubstatus.setREQ_TYPE_1(val);
						}else if(attributes.getValue(attributes.getQName(0)).equals("MSISDN1")){
							cbsSubstatus.setMSISDN1(val);
						}else if(attributes.getValue(attributes.getQName(0)).equals("SMESSAGE_1")){
							cbsSubstatus.setSMESSAGE_1(val);
						}else if(attributes.getValue(attributes.getQName(0)).equals("SUB_ACTIVE_TIME_1")){
							cbsSubstatus.setSUB_ACTIVE_TIME_1(val);
						}	
					}
				}
			}
		};

		return d;
	}
	
}
