package smoi.enums;

public enum EResourceGroupIdPrefix {
	_11("11","")
	,_17("17","")
	,_112("112","")
	,_172("172","")
	,_10("10","")
	,_08("08","")
	,_02("02","")
	,_29("29","")
	;
	EResourceGroupIdPrefix(String _prefix,String _description){
		prefix=_prefix;
		description=_description;
 	}
	private String prefix = "";
	private String description = "";
	public String getPrefix(){
		return prefix;
	}
	public String getDescription(){
		return description;	
	}
}
