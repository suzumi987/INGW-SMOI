package smoi.enums;

public enum EAccountState {
	IDLE("5003","0","01")
	,ACTIVE("2001","1","02")
	,FRAUD_ACTIVE("2001","2","02")
	,SUSPEND_VALIDITY("2001","3","03")
	,SUSPEND_REQUEST("2001","4","03")
	,FRAUD_SUSPEND("2001","5","03")
	,FRAUD_DISABLE("2001","6","04")
	,DISABLE("2001","7","04")
	,TERMINATE("5003","8","05")
	,POOL("5003","9","05");

	EAccountState(String _resultCode,String _customerLifeCycle ,String _accountState){
		this.resultCode = _resultCode;
		this.customerLifeCycle = _customerLifeCycle;
		this.accountState  = _accountState;
	}
	private String resultCode ="";
	private String customerLifeCycle ="";
	private String accountState ="";	
	
	public String getResultCode(){
		return this.resultCode;
	}
	public String getCustomerLifeCycle(){
		return this.customerLifeCycle;
	}
	public String getAccountState(){
		return this.accountState;
	}
}
