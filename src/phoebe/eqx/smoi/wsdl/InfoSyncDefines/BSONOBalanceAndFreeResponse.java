package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BSONOBalanceAndFreeResponse {
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
	@SerializedName("BALANCELIST")
	private List<BalanceList> balancelist;
	@SerializedName("ADJUSTMENTINFOLIST")
	private  List<Adjustmentinfolist> adjustmentinfolist;
	@SerializedName("LIFECYCLECHGINFO")
	private  Lifecyclechginfo lifecyclechginfo;
	// 2018/04/18
	@SerializedName("FREEUNITITEMLIST")
	private  List<FreeUnitItemList> freeUnitItemlist;
	@SerializedName("FREEUNITADJUSTMENTINFOLIST")
	private  List<FreeUnitAdjInfoResponse> freeUnitAdjInfolist;
	
	public List<FreeUnitAdjInfoResponse> getFreeUnitAdjInfoListItem() {
        if (freeUnitAdjInfolist == null) {
        	freeUnitAdjInfolist = new ArrayList<FreeUnitAdjInfoResponse>();
        }
        return this.freeUnitAdjInfolist;
    }

	public List<FreeUnitItemList> getFreeUnitItemListItem() {
        if (freeUnitItemlist == null) {
        	freeUnitItemlist = new ArrayList<FreeUnitItemList>();
        }
        return this.freeUnitItemlist;
    }
	
	public List<BalanceList> getBalanceListItem() {
        if (balancelist == null) {
        	balancelist = new ArrayList<BalanceList>();
        }
        return this.balancelist;
    }
	
	public List<Adjustmentinfolist> getAdjustmentinfoListItem() {
        if (adjustmentinfolist == null) {
        	adjustmentinfolist = new ArrayList<Adjustmentinfolist>();
        }
        return this.adjustmentinfolist;
    }
	public Lifecyclechginfo getLifecyclechginfoObj() {
        if (lifecyclechginfo == null) {
        	lifecyclechginfo = new Lifecyclechginfo();
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
	public Lifecyclechginfo getLifecyclechginfo() {
		return lifecyclechginfo;
	}
	public void setLifecyclechginfo(Lifecyclechginfo lifecyclechginfo) {
		this.lifecyclechginfo = lifecyclechginfo;
	}
	
	public List<BalanceList> getBalancelist() {
		return balancelist;
	}
	public void setBalancelist(List<BalanceList> balancelist) {
		this.balancelist = balancelist;
	}

	public List<FreeUnitItemList> getFreeUnitItemlist() {
		return freeUnitItemlist;
	}

	public void setFreeUnitItemlist(List<FreeUnitItemList> freeUnitItemlist) {
		this.freeUnitItemlist = freeUnitItemlist;
	}

	public List<FreeUnitAdjInfoResponse> getFreeUnitAdjInfolist() {
		return freeUnitAdjInfolist;
	}

	public void setFreeUnitAdjInfolist(List<FreeUnitAdjInfoResponse> freeUnitAdjInfolist) {
		this.freeUnitAdjInfolist = freeUnitAdjInfolist;
	}
	
	
}
