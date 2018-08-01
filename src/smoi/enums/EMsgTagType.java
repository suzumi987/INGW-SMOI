package smoi.enums;

public enum EMsgTagType {
	RESPONSE_MESSAGE("msg")
	,RESPONSE_LOG_RES("res")
	,RESPONSE_LOG_DESC("desc")
	;
	EMsgTagType(String _displayName){
		this.displayName =_displayName;
	}
	private String displayName="";
	public String getDisplayName(){
		return this.displayName;
	}
}
