package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class EqlBsoAdjustCbsResponse {
	@SerializedName("BWOID")
	private String bwoid;
	@SerializedName("RETRANSMIT")
	private String retransmit;
	@SerializedName("USER")
	private String user;
	@SerializedName("PARALLELBSO")
	private String parallelbso;
	@SerializedName("ORDERTYPE")
	private String ordertype;
	@SerializedName("ORDERSUBTYPE")
	private String ordersubtype;
	@SerializedName("ROLLBACK")
	private String rollback;
	@SerializedName("CANO")
	private String cano;
	@SerializedName("BANO")
	private String bano;
	@SerializedName("SANO")
	private String sano;
	@SerializedName("ORDERREF")
	private String orderref;
	@SerializedName("BILLINGSYSTEM")
	private String billingsystem;
	@SerializedName("RESPSTATUS")
	private String respstatus;
	@SerializedName("REQUESTID")
	private String requestid;
	@SerializedName("SMESSAGE")
	private String smessage;
	@SerializedName("RECEIVEDATE")
	private String receivedate;
	@SerializedName("ACTIVATEDATE")
	private String activatedate;
	@SerializedName("FINISHDATE")
	private String finishdate;
	@SerializedName("PRIORITY")
	private String priority;
	@SerializedName("BSONO")
	private List<BSONOBalanceAndFreeResponse> bsono;
	
	public List<BSONOBalanceAndFreeResponse> getBSONOListItem() {
        if (bsono == null) {
        	bsono = new ArrayList<BSONOBalanceAndFreeResponse>();
        }
        return this.bsono;
    }

	public String getBwoid() {
		return bwoid;
	}

	public void setBwoid(String bwoid) {
		this.bwoid = bwoid;
	}

	public String getRetransmit() {
		return retransmit;
	}

	public void setRetransmit(String retransmit) {
		this.retransmit = retransmit;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getParallelbso() {
		return parallelbso;
	}

	public void setParallelbso(String parallelbso) {
		this.parallelbso = parallelbso;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getOrdersubtype() {
		return ordersubtype;
	}

	public void setOrdersubtype(String ordersubtype) {
		this.ordersubtype = ordersubtype;
	}

	public String getRollback() {
		return rollback;
	}

	public void setRollback(String rollback) {
		this.rollback = rollback;
	}

	public String getCano() {
		return cano;
	}

	public void setCano(String cano) {
		this.cano = cano;
	}

	public String getBano() {
		return bano;
	}

	public void setBano(String bano) {
		this.bano = bano;
	}

	public String getSano() {
		return sano;
	}

	public void setSano(String sano) {
		this.sano = sano;
	}

	public String getOrderref() {
		return orderref;
	}

	public void setOrderref(String orderref) {
		this.orderref = orderref;
	}

	public String getBillingsystem() {
		return billingsystem;
	}

	public void setBillingsystem(String billingsystem) {
		this.billingsystem = billingsystem;
	}

	public String getRespstatus() {
		return respstatus;
	}

	public void setRespstatus(String respstatus) {
		this.respstatus = respstatus;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getSmessage() {
		return smessage;
	}

	public void setSmessage(String smessage) {
		this.smessage = smessage;
	}

	public String getReceivedate() {
		return receivedate;
	}

	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

	public String getActivatedate() {
		return activatedate;
	}

	public void setActivatedate(String activatedate) {
		this.activatedate = activatedate;
	}

	public String getFinishdate() {
		return finishdate;
	}

	public void setFinishdate(String finishdate) {
		this.finishdate = finishdate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<BSONOBalanceAndFreeResponse> getBsono() {
		return bsono;
	}

	public void setBsono(List<BSONOBalanceAndFreeResponse> bsono) {
		this.bsono = bsono;
	}
	
	
}
