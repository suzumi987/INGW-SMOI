package smoi.enums;

public enum ELeftNamePattern3 {
	_12("12","leftamount","Voice – International")
	,_13("13","leftamount","Voice IR (Roaming)")     
	,_15("15","leftamount","VDO-Call IR")         
	,_16("16","leftamount","VDO-Call International") 
	,_21("21","leftamount","MMS IR (Roaming)")       
	,_22("22","leftamount","MMS – International")    
	,_27("27","leftamount","VPN")                    
	,_28("28","leftamount","Content (Download, SSS)")
	,_30("30","leftamount","Wifi")
	;
	
	ELeftNamePattern3(String _freeResourceAA,String _mappingLeftName,String _description){
		freeResourceAA=_freeResourceAA;
		mappingLeftName=_mappingLeftName;
		description=_description;
	}
	private String freeResourceAA;
	private String mappingLeftName="";
	private String description="";
	
	public String getFreeResourceAA(){
		return freeResourceAA;
	}
	public String getMappingLeftName(){
		return 	  mappingLeftName ;

	}
	public String getDescription(){
		return description;
	}

}
