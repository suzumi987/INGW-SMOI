package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

public class ResponseParameters {
	private String BSO_1;
	private String REQ_TYPE_1;
	private String MSISDN1;
	private String SMESSAGE_1;
	private String SUB_ACTIVE_TIME_1;
	public String getBSO_1() {
		return BSO_1;
	}
	public void setBSO_1(String bSO_1) {
		BSO_1 = bSO_1;
	}
	public String getREQ_TYPE_1() {
		return REQ_TYPE_1;
	}
	public void setREQ_TYPE_1(String rEQ_TYPE_1) {
		REQ_TYPE_1 = rEQ_TYPE_1;
	}
	public String getMSISDN1() {
		return MSISDN1;
	}
	public void setMSISDN1(String mSISDN1) {
		MSISDN1 = mSISDN1;
	}
	public String getSMESSAGE_1() {
		return SMESSAGE_1;
	}
	public void setSMESSAGE_1(String sMESSAGE_1) {
		SMESSAGE_1 = sMESSAGE_1;
	}
	public String getSUB_ACTIVE_TIME_1() {
		return SUB_ACTIVE_TIME_1;
	}
	public void setSUB_ACTIVE_TIME_1(String sUB_ACTIVE_TIME_1) {
		SUB_ACTIVE_TIME_1 = sUB_ACTIVE_TIME_1;
	}
	
	@Override
	public String toString() {
		return "ResponseParameters [BSO_1=" + BSO_1 + ", REQ_TYPE_1="
				+ REQ_TYPE_1 + ", MSISDN1=" + MSISDN1 + ", SMESSAGE_1="
				+ SMESSAGE_1 + ", SUB_ACTIVE_TIME_1=" + SUB_ACTIVE_TIME_1 + "]";
	}
	
	
}
