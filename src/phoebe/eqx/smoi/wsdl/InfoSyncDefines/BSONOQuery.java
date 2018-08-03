package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class BSONOQuery {

	
	@SerializedName("BSOID")
	private String bsoid;
	@SerializedName("BSO")
	private String bso;
	@SerializedName("REQ_TYPE")
	private String reqType ;
	@SerializedName("QUERY_TYPE")
	private String queryType ;
	@SerializedName("CHANNEL")
	private String channel ;
	@SerializedName("MSG_SEQ")
	private String msgSeq ;
	@SerializedName("BALANCE_TYPE")
	private String balance_type;
	@SerializedName("PAY_TYPE")
	private String pay_type;
	@SerializedName("MSISDN1")
	private String msisdn1;
	@SerializedName("CUST_ID")
	private String custId;
	@SerializedName("ACCT_ID")
	private String acctId;
	@SerializedName("GROUP_ID")
	private String groupId;
	@SerializedName("QUERY_OBJECT")
	private String queryObject;
	@SerializedName("PRODUCT_ID")
	private String productId;
	@SerializedName("PRODUCT_SEQUENCE_ID")
	private String productSeqId;
	@SerializedName("FILTER_FLAG")
	private String filterFlag;
	@SerializedName("ACCM_TYPE")
	private String accmType;
	@SerializedName("LIMIT_TYPE")
	private String limitType;

	
	public String getBsoid() {
		return bsoid;
	}
	public void setBsoid(String bsoid) {
		this.bsoid = bsoid;
	}
	public String getBso() {
		return bso;
	}
	public void setBso(String bso) {
		this.bso = bso;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getMsgSeq() {
		return msgSeq;
	}
	public void setMsgSeq(String msgSeq) {
		this.msgSeq = msgSeq;
	}
	public String getMsisdn1() {
		return msisdn1;
	}
	public void setMsisdn1(String msisdn1) {
		this.msisdn1 = msisdn1;
	}
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getBalance_type() {
		return balance_type;
	}
	public void setBalance_type(String balance_type) {
		this.balance_type = balance_type;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getQueryObject() {
		return queryObject;
	}
	public void setQueryObject(String queryObject) {
		this.queryObject = queryObject;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductSeqId() {
		return productSeqId;
	}
	public void setProductSeqId(String productSeqId) {
		this.productSeqId = productSeqId;
	}
	public String getFilterFlag() {
		return filterFlag;
	}
	public void setFilterFlag(String filterFlag) {
		this.filterFlag = filterFlag;
	}
	public String getAccmType() {
		return accmType;
	}
	public void setAccmType(String accmType) {
		this.accmType = accmType;
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

}
