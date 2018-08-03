package smoi.enums;

/**
 * Free Resource ID is number(9 digits) : AABCDDDDD
 * ==========================================
 * 	AA		B			meaning										Mapping 
 * ==========================================
  	10		Not “2“	Money										leftprmmoney     
	11		Not “2“	Voice Domestic							leftmin          
	14		Not “2“	VDO-Call Domestic						leftcalltime     
	17		Not “2“	SMS Domestic            					leftsm           
	18		Not “2“	SMS IR										leftroamsm       
	19		Not “2“	SMS – International						leftprmisms      
	20		Not “2“	MMS Domestic								leftprmmms       
	23		Not “2“	GPRS Domestic Time base			leftprmgprstb    
	24		Not “2“	GPRS Domestic Volume base			leftprmgprsvb    
	25		Not “2“	GPRS IR (Roaming) Time base		leftprmroamgprstb
	26		Not “2“	GPRS IR (Roaming) Volume base	leftprmroamgprsvb
	29		Not “2“	RBT (Song D/L)							leftrbtsong      
 * ==========================================	
 */

public enum ELeftNamePattern2 {
	leftprmmoney("10","leftprmmoney","Money")     
	,leftmin("11","leftmin","Voice Domestic")
	,leftcalltime("14","leftcalltime","VDO-Call Domestic")						     
	,leftsm("17","leftsm","SMS Domestic")
	,leftroamsm("18","leftroamsm","SMS IR")										       
	,leftprmisms("19","leftprmisms","SMS � International")						      
	,leftprmmms("20","leftprmmms","MMS Domestic")								       
	,leftprmgprstb("23","leftprmgprstb","GPRS Domestic Time base")			    
	,leftprmgprsvb("24","leftprmgprsvb","GPRS Domestic Volume base")			    
	,leftprmroamgprstb("25","leftprmroamgprstb","GPRS IR (Roaming) Time base")		
	,leftprmroamgprsvb("26","leftprmroamgprsvb","GPRS IR (Roaming) Volume base")	
	,leftrbtsong("29","leftrbtsong","RBT (Song D/L)")
	;
	ELeftNamePattern2(String _freeResourceAA,String _mappingLeftName,String _description){
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
