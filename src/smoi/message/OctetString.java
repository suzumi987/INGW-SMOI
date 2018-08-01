package smoi.message;

import java.io.UnsupportedEncodingException;

import ec02.utils.Log;

public class OctetString {
	
	public static void main(String[] args) {
		OctetString aa = new OctetString();
	 	System.out.println(aa.Convert2String("0x30"));
	 	//System.out.println(aa.Convert2String("0x3230313330363234313133393139"));
	 	
	 /*	System.out.println(aa.Convert2Octet("Hello, World!"));
	 	
	 	System.out.println(aa.Convert2Octet("Hello, World! TEST4445566"));
	 	System.out.println(aa.Convert2String("0x48656c6c6f2c20576f726c6421205445535434343435353636"));
	 	
	 	System.out.println(aa.Convert2Octet("10"));*/
	 	System.exit(0);
	} 
 
	 public String Convert2Octet(String _strValue){ 
	 	String retValue = "0x";
	 	try {  
	    	byte[] bs = _strValue.getBytes();
	     	for(byte b:bs){ 
	     		retValue+=Integer.toString(b, 16);
	     	}
		} catch (Exception ex) {
			Log.e(ex);
		}
		return retValue;
	 }
 
	 public String Convert2String(String _strValue){ 
	 	String retValue = "";
	 	try {
	 		_strValue = _strValue.replaceFirst("0x", "");
	    	byte bs[] = new byte[_strValue.length() / 2];
	    	for (int i=0; i<_strValue.length(); i+=2) {
	    		bs[i/2] = (byte) Integer.parseInt(_strValue.substring(i, i+2), 16);
	    	}
 	 		retValue = new String(bs, "UTF8");
		} catch (UnsupportedEncodingException unex) {
			Log.e(unex);
		} catch (Exception ex) {
			Log.e(ex);
		}
		return retValue;
	 }
}
