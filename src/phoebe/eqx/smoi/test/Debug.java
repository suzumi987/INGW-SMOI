/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.test;

import ec02.exception.MessageParserException;

import ec02.server.EC02Handler;
import ec02.server.EC02Server;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author pavarisa
 */
public class Debug {

    public static void main(String[] args) throws IOException, TransformerException, XPathExpressionException, ParserConfigurationException, SAXException, MessageParserException, ExecutionException, InterruptedException {

        /*File file = new File("D:\\PROJECT@AIS\\INGW-SMOI_V2.5.0\\log\\INGW_SMOI.EC02LIB.SMOI.0.log");
        file.delete();*/
        
        BufferedReader in = new BufferedReader(new FileReader("./msg/msgFree.xml"));

        String str, reqMessage = "";
        String conf = "";
        String temp = "";
        while ((str = in.readLine()) != null) {
            reqMessage += str;
        }
        in.close();
        in = new BufferedReader(new FileReader("./conf/INGW_SMOI.EC02.0.0"));
        //		in = new BufferedReader(new FileReader("conf/srfp2.EC02.srfp2.0"));
        while ((temp = in.readLine()) != null) {
            conf += temp;
        }
        in.close();
        String[] a = {"INGW_SMOI", "SMOI", "0", conf};
        EC02Server.main(a);
        EC02Handler handler = new EC02Handler();
        System.out.println(handler.verifyAFConfig(conf));
        String output = handler.handle(reqMessage, 1024 * 1000 * 1000);
        System.out.println(output);

    }
}
