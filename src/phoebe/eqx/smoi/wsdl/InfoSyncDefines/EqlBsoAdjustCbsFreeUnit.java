package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class EqlBsoAdjustCbsFreeUnit {
	@SerializedName("BWOID")
	private String bwoid;
	@SerializedName("RETRANSMIT")
	private String retransmit;
	@SerializedName("USER")
	private String user;
	@SerializedName("PARALLELBSO")
	private String  parallelbso;
	@SerializedName("ORDERTYPE")
	private String ordertype;
	@SerializedName("ORDERSUBTYPE")
	private String ordersubtype;
	@SerializedName("ROLLBACK")
	private  String rollback;
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
	@SerializedName("PRIORITY")
	private String priority;
	@SerializedName("BSONO")
	private List<BSONOFreeUnit> bsono;
	
	public List<BSONOFreeUnit> getBSONOFreeUnitListItem() {
        if (bsono == null) {
        	bsono = new ArrayList<BSONOFreeUnit>();
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<BSONOFreeUnit> getBsono() {
		return bsono;
	}

	public void setBsono(List<BSONOFreeUnit> bsono) {
		this.bsono = bsono;
	}
	
	
}
