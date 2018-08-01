package smoi.message;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLBuilder { 
	private DocumentBuilder builder = null ;
	private Document document =null;
	
	public static void main(String[] args) {  
		/*try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
			Document document = builder.newDocument();
			document.setXmlStandalone(true);
			Element rootElement =  document.createElement("EquinoxRawData");

			String elementName = "AVP";
			String attributeName ="type";
			String attributeValue ="resultInfo";
    	 
            Element elementAVP = document.createElement(elementName);
            elementAVP.setAttribute(attributeName, attributeValue); 
            
            Element elementVals = document.createElement("vals");
            elementVals.setAttribute("value", "test"); 
            
            elementAVP.appendChild(elementVals);
            rootElement.appendChild(elementAVP);             
            document.appendChild(rootElement);
            System.out.println(getXMLMessage(document));
        } catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		XMLBuilder xml = new XMLBuilder();
		String elementNameAVP = "AVP";
		HashMap<String,String> attributesAVP = new HashMap<String,String>();
		attributesAVP.put("type","resultInfo");
		
		 
		xml.appendChild(elementNameAVP,attributesAVP,null); 
		
		System.out.println(xml.getXMLMessage());
	}
    
	public XMLBuilder(){
		try {
			this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
 			this.document = builder.newDocument();
 			this.document.setXmlStandalone(true);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
 
	public  void appendChild(String _elementName
			,HashMap<String,String> _attributes
			,String innerText){
		
		Element element = this.document.createElement(_elementName);
		if(_attributes!=null && _attributes.size()>0){
			try{
				for (Iterator<Map.Entry<String,String>> i = _attributes.entrySet().iterator(); i.hasNext();){  
				    Map.Entry<String,String> entry = i.next();  
				    String attributeName = entry.getKey();
				    String attributeValue = entry.getValue();
				    element.setAttribute(attributeName, attributeValue);
				} 
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		if(innerText!=null && !innerText.trim().equals("")){
			element.setTextContent(innerText);
		}
 		
		this.document.appendChild(element);
		
		/*NodeList nl = this.document.getElementsByTagName("AVP");  
		for (int i = 0; i < nl.getLength(); i++) {
	     
			Element avp = (Element) nl.item(i); 

			if(avp.getAttribute("type").equalsIgnoreCase("resultInfo")){
				String elementNameVals = "vals";
				HashMap<String,String> attributesVals = new HashMap<String,String>();
				attributesVals.put("value","1");
			 
				appendChild(elementNameVals,attributesVals,null);
			}
	    }*/
	}
	
    public   String getXMLMessage(){
    	String xmlMessage = "";
		if(this.document!=null){	
			try {
				//set up a transformer
				TransformerFactory transfac = TransformerFactory.newInstance();
				Transformer trans= transfac.newTransformer();
				trans.setOutputProperty("indent", "yes");
				//create string from xml tree
		        StringWriter sw = new StringWriter();
		        StreamResult result = new StreamResult(sw);
		        DOMSource source = new DOMSource(this.document);
				trans.transform(source, result);
				xmlMessage = sw.toString();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
		return xmlMessage;
	}
}
