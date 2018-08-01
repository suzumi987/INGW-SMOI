package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BSONOFreeUnitResponse {
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

	public List<FreeUnitAdjInfoResponse> getFreeUnitAdjInfolist() {
		return freeUnitAdjInfolist;
	}

	public void setFreeUnitAdjInfolist(List<FreeUnitAdjInfoResponse> freeUnitAdjInfolist) {
		this.freeUnitAdjInfolist = freeUnitAdjInfolist;
	}

	public List<FreeUnitItemList> getFreeUnitItemlist() {
		return freeUnitItemlist;
	}

	public void setFreeUnitItemlist(List<FreeUnitItemList> freeUnitItemlist) {
		this.freeUnitItemlist = freeUnitItemlist;
	}
	
	
}
