package phoebe.eqx.smoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class SGSCPClient {

    public static String Address = "http://10.104.130.38:7020";
    //public static String Address = "http://10.240.1.15:7023";
    
    public static String randomSSID() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10000000);
        return String.valueOf(randomInt);
    }

    public static void SendMsg(String message) {
        String response = "";
        try {
            System.setProperty("sun.net.client.defaultReadTimeout", "50000");
            StringBuilder Request = new StringBuilder(message);

            URL url = new URL(Address);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            int Timeout = 200;// S
            connection.setConnectTimeout(Timeout * 1000);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/plain");
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            System.out.println("Request is :\n" + Request);
            out.print(Request);
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuilder strBuf = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                strBuf.append(inputLine);
                strBuf.append("\n");
            }
            in.close();
            response = strBuf.toString();
            connection.getInputStream().close();
            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Error Massage:" + e.getMessage());
        }

        System.out.println("Response is :\n" + response);
    }

    public static void main(String args[]) {
        //String msisdn = "66933520000";
        //String msisdn = "66878880500";
        //String msisdn = "66916670219";

        /*
         *        Tel.               SCPID(ds2serviceLocation)       CBPID(ds2cbpLocation)
         089-034-2266                   BOSCFxxx                          BOCBFxxx                      
         089-738-0280                   BOSCFxxx                          BOCBFxxx
         086-905-7566                   OCSCFxxx                          OCCBFxxx
         086-017-3581                   OCSCFxxx                          OCCBFxxx
         081-906-3336                   HWPCFxxx     
         */

        //BSS Broker 10.104.168.11 7005
        
        //AdjustBalance Service
        /*String modiPPSValidity = "page=modiPPSValidity&sgw=smsgw4&ms=" + msisdn + "&ssid=1234567890&flag=0&validity=7&merchant=199&smp=5&balance=2&service=4&increment=20";
        String modiPPSMultiAttr = "page=modiPPSMultiAttr&sgw=smsgw4&ms=" + msisdn + "&ssid=" + SGSCPClient.randomSSID() + "&flag=0&balance=200";

        //Change Service Service
        //String activateScrScreen = "page=activateScrScreen&ms="+msisdn+"&dat=0&ssid=1234567890&sgw=010";
        //String deactivateScrScreen = "page=deactivateScrScreen&ms="+msisdn+"&dat=0&ssid=50552&sgw=010"; 
        String actPPSRBT = "page=actPPSRBT&ms=" + msisdn + "&dat=2&ssid=50552&sgw=010";	//product id = 110030
        String delePPSPkgres = "delePPSPkgres";
        String modiPPSSerClass = "page=modiPPSSerClass&ms=" + msisdn + "&cos=30&ssid=3195&sgw=010&timeout=15000";
       
        //page=purchasePPSPackage&ms=66933520000&packageid=760190&ssid=1234&sgw=INGW
        String purchasePPSPackage = "page=purchasePPSPackage&ms=" + msisdn + "&packageid=3043&ssid=" + SGSCPClient.randomSSID() + "&sgw=olympus6&timeout=14000";
         
        String delePPSPackageID = "page=delePPSPackageID&ms=" + msisdn + "&packageid=3043&prodseqid=36584&cancel=0&ssid=" + SGSCPClient.randomSSID() + "&sgw=olympus6&timeout=14000";

        //Manage Call Screen
        String addScrScreenNo = "page=addScrScreenNo&ms=" + msisdn + "&dat=0866185888&ssid=" + SGSCPClient.randomSSID() + "&sgw=010&screentype=2";
        String deleScrScreenNo = "page=deleScrScreenNo&ms=" + msisdn + "&dat=0866185887&ssid=" + SGSCPClient.randomSSID() + "&sgw=010&screentype=1";
        String modiScrScreenType = "modiScrScreenType&ms=" + msisdn + "&dat=0&ssid=" + SGSCPClient.randomSSID() + "&sgw=010&screentype=1";
        String modiScrWhiteList = "modiScrWhiteList";
        String modifyVPNWeekTime = "page=modifyVPNWeekTime&ms=" + msisdn + "&dat=1&ssid=5876&sgw=010";
        String setVPNBlackWhite = "page=setVPNBlackWhite&ms=" + msisdn + "&dat=017359700|063670538|000000000|000000000|000000000|000000000|000"
                + "000000|000000000|000000000|000000000|&ssid=3439&sgw=010&bwf=0";

        //Query
        page=dispPPSInfo&ms=66933520000&dat=0&ssid=1234&sgw=INGW
        String dispPPSInfo = "page=dispPPSInfo&ms=" + msisdn + "&dat=0&ssid=" + SGSCPClient.randomSSID() + "&sgw=EAI Orders&timeout=4000";
        String dispPPSExtInfo = "page=dispPPSExtInfo&ms=" + msisdn + "&dat=0&ssid=" + SGSCPClient.randomSSID() + "&sgw=EAI Orders&timeout=9000";
        String dispPPSPkgrew = "page=dispPPSPkgrew&ms=" + msisdn + "&dat=0&ssid=" + SGSCPClient.randomSSID() + "&sgw=EAI Orders&timeout=9000";

        //Query Call Screen
        */
        String msisdn = "66616820343";
        msisdn="0935850604";
        msisdn="66935850601";
        String dispScrScreenNo = "page=dispScrScreenNo&ms=" + msisdn + "&dat=0&ssid=" + SGSCPClient.randomSSID() + "&sgw=010";
        SGSCPClient.SendMsg(dispScrScreenNo);
        /*
        String listVPNBlackWhite = "page=listVPNBlackWhite&ms=" + msisdn + "&dat=0&ssid=5735&sgw=010&bwf=0";
        String listVPNWeekTime = "page=listVPNWeekTime&ms=" + msisdn + "&dat=0&ssid=5744&sgw=010";

        //Top-up Using Cash
        String chgtrigChrgAcnt = "page=chgtrigChrgAcnt&sgw=000&ms=" + msisdn + "&fee=1000&ssid=911145929&batchno=434FWU407&pin=0500&dat=32";

        //Set Negative balance
        String modiPPSCreditLimit = "page=modiPPSCreditLimit&ms=" + msisdn + "&increment=5000&ssid=4341113995&sgw=010";
        */
        //First Activation by CRM
        //String msisdn = "66860052229";
        //String activatePPSSingSub = "page=activatePPSSingSub&ms=" + msisdn + "&lang=11&ssid=" + SGSCPClient.randomSSID() + "&sgw=010&timeout=15000&location=66811111111";
        //SGSCPClient.SendMsg(activatePPSSingSub);
        /*
        //Query Subscriber Friend Number
        String dispPPSFntelNo = "page=dispPPSFntelNo&ms=" + msisdn + "&dat=0&ssid=" + SGSCPClient.randomSSID() + "&sgw=010";

        //Modify Subscriber basic information
        String modiPPSLanguage = "page=modiPPSLanguage&ms=" + msisdn + "&lang=ENG&ssid=" + SGSCPClient.randomSSID() + "&sgw=EAI&channel=WEB";
        String modiPPSSMSLanguage = "page=modiPPSSMSLanguage&ms=" + msisdn + "&lang=11&ssid=" + SGSCPClient.randomSSID() + "&sgw=010";

        
        String verifyRcmsCard ="page=verifyRcmsCard&pin=3204053387244&dat=bUSSD&ssid=6&sgw=zeus&timeout=15000";
        
        String setPPSReward ="page=setPPSReward&ms=66932080506&ssid=22400000807104502361&sgw=athenaDEV1&timeout=30000&prmMinute=300&expire=15"; */
        
        //66923095331 smoi
        //66932080514, 66935850610, 66932080508 BOS DCC
        
        //String aa ="page=dispPPSInfo&ms=66933510509&dat=0&ssid=5&sgw=zeus&timeout=15000";
        
        //String aa ="page=addScrScreenNo&ms=66932080514&dat=66819063336&ssid=78351&sgw=olympus7&timeout=20000&screentype=1";
        //String aa ="page=deleScrScreenNo&ms=66935850610&dat=6622787092&ssid=538&sgw=zeus&timeout=40000&screentype=1";
    	//String aa = "page=addScrScreenNo&ms=66933510515&dat=66899010006%2666812550295&ssid=2516&sgw=zeus&timeout=40000&screentype=1";
    	//String aa ="page=dispScrScreenNo&ms=66935850610&dat=0&ssid=77966&sgw=ingatest&timeout=20000";
        //String aa ="page=modiScrWhiteList&ms=66932080514&dat=1&ssid=1995&sgw=olympus7&timeout=20000";
    	//String aa = "page=addScrScreenNo&ms=66932080508&dat=6621221524&ssid=453&sgw=zeus&timeout=40000&screentype=1";
    	
        //SGSCPClient.SendMsg(aa);
 
    }
}
