package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BSONOBalanceAndValidityResponse {

	@SerializedName("BSOID")
	private String bsoid;
	@SerializedName("BSO")
	private String bso;
	@SerializedName("REQ_TYPE")
	private String reqType ;
	@SerializedName("MSISDN1")
	private String msisdn1;
	@SerializedName("BSO_STATUS")
	private String bsoStatus;
	@SerializedName("SMESSAGE")
	private String smessage;
	@SerializedName("ACCT_ID")
	private String acttId;
	@SerializedName("ADJUSTMENTINFOLIST")
	private  List<Adjustmentinfolist> adjustmentinfolist;
	@SerializedName("LIFECYCLECHGINFO")
	private  LifecyclechginfoBalanceAndValidity lifecyclechginfo;
	@SerializedName("CHANGE_DAYS")
	private String changeDay;
	
	

	public List<Adjustmentinfolist> getAdjustmentinfoListItem() {
        if (adjustmentinfolist == null) {
        	adjustmentinfolist = new ArrayList<Adjustmentinfolist>();
        }
        return this.adjustmentinfolist;
    }
	public LifecyclechginfoBalanceAndValidity getLifecyclechginfoObj() {
        if (lifecyclechginfo == null) {
        	lifecyclechginfo = new LifecyclechginfoBalanceAndValidity();
        }
        return this.lifecyclechginfo;
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
	public String getMsisdn1() {
		return msisdn1;
	}
	public void setMsisdn1(String msisdn1) {
		this.msisdn1 = msisdn1;
	}
	public String getBsoStatus() {
		return bsoStatus;
	}
	public void setBsoStatus(String bsoStatus) {
		this.bsoStatus = bsoStatus;
	}
	public String getSmessage() {
		return smessage;
	}
	public void setSmessage(String smessage) {
		this.smessage = smessage;
	}
	public String getActtId() {
		return acttId;
	}
	public void setActtId(String acttId) {
		this.acttId = acttId;
	}
	public List<Adjustmentinfolist> getAdjustmentinfolist() {
		return adjustmentinfolist;
	}
	public void setAdjustmentinfolist(List<Adjustmentinfolist> adjustmentinfolist) {
		this.adjustmentinfolist = adjustmentinfolist;
	}
	public LifecyclechginfoBalanceAndValidity getLifecyclechginfo() {
		return lifecyclechginfo;
	}
	public void setLifecyclechginfo(LifecyclechginfoBalanceAndValidity lifecyclechginfo) {
		this.lifecyclechginfo = lifecyclechginfo;
	}
	
	public String getChangeDay() {
		return changeDay;
	}
	public void setChangeDay(String changeDay) {
		this.changeDay = changeDay;
	}

}
