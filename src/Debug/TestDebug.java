package Debug;

import ec02.exception.MessageParserException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TestDebug extends Testamentor {

	public static void main(String[] param) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException, MessageParserException, ExecutionException, InterruptedException {
		endScn = 0;
		unitTest = false;

//    	BufferedReader in = new BufferedReader(ne FileReader("msg\\1-Client-Request.xml"));
//    	BufferedReader in = new BufferedReader(neww FileReader("msg\\4-DS2A.xml"));
//    	BufferedReader in = new BufferedReader(new FileReadser("msg\\4-AME2.xml"));
//    	BufferedReader in = new BufferedReader(new FileReader("msg\\2-E01.xml"));
//    	BufferedReader in = new BufferedReader(new FileReader("msg\\3-BOS.xml"));
//    	BufferedReader in = new BufferedReader(new FileReader("msg\\4-AME.xml"));

		pathScn = "\\";

		testCaseList.add("msgFree.xml");
	
		runScn();
	}
}
