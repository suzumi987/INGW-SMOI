package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BSONOFreeUnit {
	@SerializedName("BSOID")
	private String bsoid;
	@SerializedName("BSO")
	private String bso;
	@SerializedName("REQ_TYPE")
	private String reqType;
	@SerializedName("MSG_SEQ")
	private String msgSeq;
	@SerializedName("ADJUSTMENT_SERIAL_NO")
	private String adjSerialNo;
	@SerializedName("MSISDN1")
	private String msisdn1;
	@SerializedName("ACCT_ID")
	private String acctId;
	@SerializedName("PAY_TYPE")
	private String payType;
	@SerializedName("ADJUST_OBJECT")
	private String adjObject;
	@SerializedName("OPER_TYPE")
	private String operType;
	@SerializedName("FREEUNITADJUSTMENTINFOLIST")
	private List<FreeUnitAdjInfo> freeunitadjustmentinfolist;
	@SerializedName("REASON_CODE")
	private String reasonCode;
	@SerializedName("INSUFFICIENT_FLAG")
	private String insufficientFlag;
	@SerializedName("ADJ_PROPERTYLIST")
	private List<AdjPropertylist> adjPropertylist;
	@SerializedName("REMARK")
	private String remark;
	
	public List<FreeUnitAdjInfo> getFreeUnitAdjInfoListItem() {
        if (freeunitadjustmentinfolist == null) {
        	freeunitadjustmentinfolist = new ArrayList<FreeUnitAdjInfo>();
        }
        return this.freeunitadjustmentinfolist;
    }
	
	public List<AdjPropertylist> getAdjPropertylistListItem() {
        if (adjPropertylist == null) {
        	adjPropertylist = new ArrayList<AdjPropertylist>();
        }
        return this.adjPropertylist;
    }

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

	public String getAdjSerialNo() {
		return adjSerialNo;
	}

	public void setAdjSerialNo(String adjSerialNo) {
		this.adjSerialNo = adjSerialNo;
	}

	public String getMsisdn1() {
		return msisdn1;
	}

	public void setMsisdn1(String msisdn1) {
		this.msisdn1 = msisdn1;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getAdjObject() {
		return adjObject;
	}

	public void setAdjObject(String adjObject) {
		this.adjObject = adjObject;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public List<FreeUnitAdjInfo> getFreeunitadjustmentinfolist() {
		return freeunitadjustmentinfolist;
	}

	public void setFreeunitadjustmentinfolist(List<FreeUnitAdjInfo> freeunitadjustmentinfolist) {
		this.freeunitadjustmentinfolist = freeunitadjustmentinfolist;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getInsufficientFlag() {
		return insufficientFlag;
	}

	public void setInsufficientFlag(String insufficientFlag) {
		this.insufficientFlag = insufficientFlag;
	}

	public List<AdjPropertylist> getAdjPropertylist() {
		return adjPropertylist;
	}

	public void setAdjPropertylist(List<AdjPropertylist> adjPropertylist) {
		this.adjPropertylist = adjPropertylist;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
