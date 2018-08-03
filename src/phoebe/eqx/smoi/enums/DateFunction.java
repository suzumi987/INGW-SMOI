/**
 * 
 */
package phoebe.eqx.smoi.enums;

import ec02.utils.AppLog;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * @author Thanakhan I. <thanakhi@ais.co.th>
 *
 */
public enum DateFunction {
//	DEFAULT			("yyyyMMdd HH:mm:ss.SSS"),
//	TRAMSDATE		("yyyyMMdd:HHmmss"), 
	LOG					("yyyyMMdd HH:mm:ss.SSS"),
	DEFAULT				("yyyyMMddHHmmss"), 
	AMFRF				("yyyyMMdd:HHmmss"), 
	ABMF				("yyyyMMdd:HHmmss"), 
	SDF					("yyyyMMddHHmmssZ"), 
	DS2A				("yyyyMMddHHmmssZ"), 
	EQL					("yyyy-MM-dd HH:mm:ssZ"), //PIT 27/09/2017
	
	RMF					("yyyyMMdd:HHmmss"), 
	USMP				("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ"),
	SSB            		("ddMMyyyy HH:mm:ss"),
	SSB_PREPAID          ("dd/MM/yyyy HH:mm:ss"),
	REPORT				("yyyyMMddHHmmss"),
	REPORT_EFFECTIVE	("yyyyMMdd"),
	SRFC_SPNV			("yyyyMMdd:HHmmss"),
	RMCR				("yyyyMMdd:HHmmss"),
	MANAGE_REWARD_WO	 ("yyyyMMddHHmmss"),
	E01NEWCLASSOFSERVICE ("yyyyMMddHHmmss"),
	SRFC 				 ("dd-MM-yyyy HH:mm:ss"),
	
	SMOI_DISPPPSINFO("yyyyMMdd"),
	SMOI_DISPPPSINFO_("yyyyMMddHHmmss"),


	;

	private DateTimeFormatter FMT;
	private String pattern;
	
	private DateFunction(String pattern) {
		this.pattern = pattern;
		this.FMT = DateTimeFormat.forPattern(pattern).withLocale(Locale.US);
	}

	
	public DateTime current() {
		return new DateTime();
	}
	
	
	
	public DateTime parser(String date) {
		if(date == null) return null;
		try {
			return this.FMT.parseDateTime( date );
			
		} catch (Exception e) {
			AppLog.d( "Cannot parser To DateTime : Value=" + date );
			return null;
		}
		
	}

	public DateTime parser(long date) {
		try {
			return new DateTime( date );
			
		} catch (Exception e) {
			
			AppLog.d( "Cannot parser To DateTime : value=" + date );
			return null;
		}
		
	}
	
	public DateTime parser(Date date) {
		if(date == null) return null;
		try {
			return new DateTime( date );
			
		} catch (Exception e) {
			
			AppLog.d( "Cannot parser to dateTime : value=" + date );
			return null;
		}
	}
	
	public String printNow() {
		return this.FMT.print( System.currentTimeMillis() );
	}

	public String print(DateTime date) {
		if(date == null) return null;
		try {
			return this.FMT.print( date );
			
		} catch (Exception e) {
			
			AppLog.d( "Cannot parser to dateTime : value=" + date );
			return null;
		}
		
	}
	
	public String print(Date date) {
		if(date == null) return null;
		try {
			return this.FMT.print( date.getTime() );
			
		} catch (Exception e) {
			
			AppLog.d( "Cannot parser to dateTime : value=" + date );
			return null;
		}
		
	}

	public String print(long date) {
		try {
			return this.FMT.print( date );
			
		} catch (Exception e) {
			
			AppLog.d( "Cannot parser to dateTime : value=" + date );
			return null;
		}
	}
	
	public boolean validateDate( String date ) {
		try{
			this.FMT.parseDateTime(date);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	public String getPattern() {
		return pattern;
	}
	
	
//	public String convertFormatPrint( DateFunction toFormat, String date) {
//		DateTime parser = this.parser( date );
//		return toFormat.print( parser );
//	}
	
//	public static void main(String[] args) {
//		System.err.println( DateFunction.RMF.convertFormatPrint( DateFunction.SRFC_SPNV, "20150601:112535") );
//		System.err.println( DateFunction.SRFC_SPNV.convertFormatPrint( DateFunction.RMF, "01/06/2015 11:25:35") );
//	}
//	 static class AppLog{
//		 public static void d(String t){
//		 System.out.println(t);
//		 }
//		 public static void i(String t){
//		 System.out.println(t);
//		 }
//		 }
}
