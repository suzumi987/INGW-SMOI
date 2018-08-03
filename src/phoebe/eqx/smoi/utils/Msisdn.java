/**
 * @version 1.0
 * @author pawarich
 */
package phoebe.eqx.smoi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Msisdn {

	public enum EMsisdnFormat {
        PATTERN1_66_9digit("^66\\d{9,9}$"),
        PATTERN2_0d_8digit("^0\\d\\d{8,8}$"),   
        PATTERN3_9Digit("^\\d{9,9}$");
   
        EMsisdnFormat(String _regexPattern) {
        	regexPattern =_regexPattern;
        }
        private String regexPattern;
        
        public String getRegexPattern() {
            return regexPattern;
        }
    }
	
	public static EMsisdnFormat isPattern(String msisdn){
		EMsisdnFormat eMsisdnFormat=null;
		if(msisdn!=null&&!msisdn.trim().equals("")){
			for(EMsisdnFormat e : EMsisdnFormat.values()){ 
				Pattern p = Pattern.compile(e.getRegexPattern());
				Matcher m = p.matcher(msisdn);
		 		if(m.find()) { 
		 			eMsisdnFormat = e; 
		 			break;
				}
			}
		}
		return eMsisdnFormat;
	}
	
	public static String convert(String msisdn ,EMsisdnFormat eMsisdnFormat){
		String newMsisdn="";
		if(msisdn!=null&&!msisdn.trim().equals("")){
			 switch(eMsisdnFormat){
			 	case PATTERN1_66_9digit:
			 		switch(isPattern(msisdn)){ 
			 			case PATTERN2_0d_8digit:
			 				newMsisdn="66"+msisdn.substring(1);
			 				break;
			 			case PATTERN3_9Digit:
			 				newMsisdn="66"+msisdn;
			 				break;
			 			default:newMsisdn=msisdn;
			 		}
			 		break;
			 	case PATTERN2_0d_8digit:
			 		switch(isPattern(msisdn)){ 
			 			case PATTERN1_66_9digit:
			 				newMsisdn="0"+msisdn.substring(2);
			 				break;
			 			case PATTERN3_9Digit:
			 				newMsisdn="0"+msisdn;
			 				break;
			 			default:newMsisdn=msisdn;
			 		}
			 		break;
			 	case PATTERN3_9Digit:
			 		switch(isPattern(msisdn)){ 
			 			case PATTERN1_66_9digit:
			 				newMsisdn=msisdn.substring(2);
			 				break;
			 			case PATTERN2_0d_8digit:
			 				newMsisdn=msisdn.substring(1);
			 				break;
			 			default:newMsisdn=msisdn;
			 		}
			 		break;
			 	default:newMsisdn=msisdn;
			 }
		}
		return newMsisdn;
	}

    public static String checkLengthAndConvert(String msisdn){
        if(msisdn!=null&&!msisdn.trim().equals("")){
            if(msisdn.trim().length()==10 && !msisdn.startsWith("66")){
                return convert(msisdn,Msisdn.EMsisdnFormat.PATTERN1_66_9digit);
            }
            else if(msisdn.trim().length()==9){
                return "66"+msisdn.substring(1);
            }
        }
        return msisdn;
    }
	
	/*
	public static void main(String[] args) {
		System.out.println(isPattern("0869057566"));
		System.out.println(isPattern("1869057566"));
		System.out.println(isPattern("66869057566"));
		System.out.println(isPattern("67869057566"));
		System.out.println(isPattern("869057566"));
		System.out.println(isPattern("0869057566bb"));
		System.out.println(isPattern("08690575665"));
		System.out.println(isPattern("66"));
		System.out.println(isPattern("86ff"));
		System.out.println(isPattern("8690"));
		System.out.println("===========");
		String x ="66869057566"; 
		String a = convert(x,EMsisdnFormat.PATTERN1_66_9digit);
		String b = convert(x,EMsisdnFormat.PATTERN2_0d_8digit);
		String c = convert(x,EMsisdnFormat.PATTERN3_9Digit);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println("===========");
		String xx ="0869057566"; 
		String aa = convert(xx,EMsisdnFormat.PATTERN1_66_9digit);
		String bb = convert(xx,EMsisdnFormat.PATTERN2_0d_8digit);
		String cc =  convert(xx,EMsisdnFormat.PATTERN3_9Digit);
		System.out.println(aa);
		System.out.println(bb);
		System.out.println(cc);
		System.out.println("===========");
		String xxx ="869057566"; 
		String aaa = convert(xxx,EMsisdnFormat.PATTERN1_66_9digit);
		String bbb = convert(xxx,EMsisdnFormat.PATTERN2_0d_8digit);
		String ccc =  convert(xxx,EMsisdnFormat.PATTERN3_9Digit);
		System.out.println(aaa);
		System.out.println(bbb);
		System.out.println(ccc);
		 
	}
	*/
}
