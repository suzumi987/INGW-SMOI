package smoi.enums;

/**TODO
 * @author nuiss
 * @see mapping value
 */  
public enum EBrandId{
	GSM("0","gsm")
	,ONE2CALL("1","one2call")
	,SAWASDEE("2","sawasdee")
	,GSM1800("3","gsm1800")
	,_3G_POSTPAID("4","3g postpaid")
	,_3G_PREPAID("5","3g prepaid")
	;
	EBrandId(String _code,String _name){
		this.code=_code;
		this.name =_name;
	}
	private String code="";
	private String name="";
	public String getCode(){
		return this.code;
	}
	public String getName(){
		return this.name;
	}
}
