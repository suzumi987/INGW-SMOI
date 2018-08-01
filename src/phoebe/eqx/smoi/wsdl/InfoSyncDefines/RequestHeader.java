package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

public class RequestHeader {
	private String requestId;
	private String status;
	private String orderNo;
	private String reqUser;
	private String statusMessage;
	private String statusMessageId;
	private String priority;
	private String receivedDate;
	private String finishedDate;
	
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
	@Override
	public String toString() {
		return "RequestHeader [requestId=" + requestId + ", status=" + status
				+ ", orderNo=" + orderNo + ", reqUser=" + reqUser
				+ ", statusMessage=" + statusMessage + ", statusMessageId="
				+ statusMessageId + ", priority=" + priority
				+ ", receivedDate=" + receivedDate + ", finishedDate="
				+ finishedDate + "]";
	}
	

}
