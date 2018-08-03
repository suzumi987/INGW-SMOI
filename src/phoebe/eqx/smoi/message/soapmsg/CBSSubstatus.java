package phoebe.eqx.smoi.message.soapmsg;

public class CBSSubstatus {
	//header
	private String requestId;
	private String status;
	private String orderNo;
	private String reqUser;
	private String statusMessage;
	private String statusMessageId;
	private String priority;
	private String receivedDate;
	private String finishedDate;
	//body
	private String BSO_1;
	private String REQ_TYPE_1;
	private String MSISDN1;
	private String SMESSAGE_1;
	private String SUB_ACTIVE_TIME_1;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getReqUser() {
		return reqUser;
	}
	public void setReqUser(String reqUser) {
		this.reqUser = reqUser;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getStatusMessageId() {
		return statusMessageId;
	}
	public void setStatusMessageId(String statusMessageId) {
		this.statusMessageId = statusMessageId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}
	
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
	
	
}
