/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.utils;

import ec02.af.abstracts.AbstractAF;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar; 
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import phoebe.eqx.smoi.conf.Conf;

/**
 *
 * @author pavarisa
 */
public class SMOIUtils {

    public static String createSmoiSessionIdKey(String sgw, String ssid) {
        SimpleDateFormat sf = new SimpleDateFormat(";yyyyMMdd;HHmmss;SSS");
        return sgw + "@" + ssid + sf.format(Calendar.getInstance(Locale.US).getTime());
    }
    
    public static String createSmoiBSOID(String sgw, String ssid) { // Chatl 20/09/2017
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
   	 	String alphabetUpperCase= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String strRan = "";
        Random random = new Random();
        int ranUpperCase = random.nextInt(2);
        int ranTypeChar = random.nextInt(2);
        for (int i = 0; i < 5; i++) {
       	 
       	 ranUpperCase = random.nextInt(2);
       	 ranTypeChar = random.nextInt(2);
       	 
       	 if(ranTypeChar == 0){ // random number
           		 strRan += String.valueOf(random.nextInt(10));
       	 }else{ // random char
           	 if(ranUpperCase == 0){ // Upper case
           		 strRan += alphabetUpperCase.charAt(random.nextInt(26));
           	 }else{ // Lower case
           		 strRan += alphabet.charAt(random.nextInt(26));
           	 }
       	 }
        }
        return sgw + "@" + ssid + sf.format(Calendar.getInstance(Locale.US).getTime()) + strRan;
    }
    
    public static String createSmoiEQUULEUS(String ssid) { // Chatl 20/09/2017
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return "SMOI_" + sf.format(Calendar.getInstance(Locale.US).getTime()) + ssid;
    }

    public static String getHttpPostValue(String input, String key) {
        String data = "";
        if (input != null) {
            if (input.indexOf("&") > -1) {
                StringTokenizer token = new StringTokenizer(input, "&");
                String pair;
                while (token.hasMoreTokens()) {
                    pair = token.nextToken();
                    int num = pair.indexOf("=");
                    if (num < 0) {
                        data = "";
                    } else {
                        if (key.equals(pair.substring(0, num))) {
                            data = pair.substring(num + 1, pair.length());
                            return data;
                        }
                    }
                }
            }
        }
        return data;
    }

    public static String getXMLText(String xmlMessage, String regx) {
        String text = null;
        if(xmlMessage!=null && !xmlMessage.trim().equals("")){
	        xmlMessage = xmlMessage.replaceAll("(\r|\n|\t)", "");
 	        try {
	            Pattern p = Pattern.compile("<" + regx + ">(.*?)</" + regx + ">", Pattern.DOTALL);
	            Matcher m = p.matcher(xmlMessage);
	
	            for (;m.find();) {
	                text = m.group(1);
	            }
	        } catch (Exception e) {
	        }
        }
        
        if (text == null) {
            if (xmlMessage.contains("<" + regx + " />")) {
                text = "";
            }
        }
        return text;
    }

    public static boolean isBosProfile(AbstractAF af, String scp_id) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String scpIdList = smoi_conf.get(Conf.bosLocation).get(0);
        String[] s = scpIdList.split(",");
        for (String str : s) {
            //Ignore Case Sensitive
            String prefix = str.toLowerCase();
            scp_id = scp_id.toLowerCase();
            if (scp_id.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAVATARProfile(AbstractAF af, String scp_id) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String scpIdList = smoi_conf.get(Conf.avatarLocation).get(0);
        String[] s = scpIdList.split(",");
        for (String str : s) {
            //Ignore Case Sensitive
            String prefix = str.toLowerCase();
            scp_id = scp_id.toLowerCase();
            if (scp_id.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }


    
    public static boolean isINSProfile(AbstractAF af, String scp_id) {
        HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
        String scpIdList = smoi_conf.get(Conf.insLocation).get(0);
        String[] s = scpIdList.split(",");
        for (String str : s) {
            //Ignore Case Sensitive
            String prefix = str.toLowerCase();
            scp_id = scp_id.toLowerCase();
            if (scp_id.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
    
//    public static void main(String[] args) {
//    	 String alphabet= "abcdefghijklmnopqrstuvwxyz";
//    	 String alphabetUpperCase= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//         String s = "";
//         Random random = new Random();
//         int ranUpperCase = random.nextInt(2);
//         int ranTypeChar = random.nextInt(2);
//         System.out.println(ranUpperCase);
//         for (int i = 0; i < 5; i++) {
//        	 
//        	 ranUpperCase = random.nextInt(2);
//        	 ranTypeChar = random.nextInt(2);
//        	 
//        	 if(ranTypeChar == 0){ // random number
//            		 s += String.valueOf(random.nextInt(10));
//        	 }else{ // random char
//            	 if(ranUpperCase == 0){ // Upper case
//            		 s += alphabetUpperCase.charAt(random.nextInt(26));
//            	 }else{
//            		 s += alphabet.charAt(random.nextInt(26));
//            	 }
//        	 }
//         }
//          
//         System.out.println(s);
//	}
//    
}
