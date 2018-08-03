package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BSONOResponse {

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
	@SerializedName("SUB_STATUS")
	private String subStatus;
	@SerializedName("NEW_STATUSLIST")
	private List<NewStatusList> newStatuslist;
	@SerializedName("CHANGE_DAYS")
	private String changDays;
	@SerializedName("BALANCELIST")
	private List<BalanceList> balanceList;
	
	
	public List<BalanceList> getBalanceListItem() {
        if (balanceList == null) {
        	balanceList = new ArrayList<BalanceList>();
        }
        return this.balanceList;
    }
	
	public List<NewStatusList> getNewStatusListItem() {
        if (newStatuslist == null) {
        	newStatuslist = new ArrayList<NewStatusList>();
        }
        return this.newStatuslist;
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
	public String getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}
	public List<NewStatusList> getNewStatuslist() {
		return newStatuslist;
	}
	public void setNewStatuslist(List<NewStatusList> newStatuslist) {
		this.newStatuslist = newStatuslist;
	}
	public String getChangDays() {
		return changDays;
	}
	public void setChangDays(String changDays) {
		this.changDays = changDays;
	}

	public List<BalanceList> getBalanceList() {
		return balanceList;
	}

	public void setBalanceList(List<BalanceList> balanceList) {
		this.balanceList = balanceList;
	}
	
	
}
