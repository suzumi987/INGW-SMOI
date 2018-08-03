package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class LifecyclechginfoBalanceAndValidity {

	@SerializedName("NEW_STATUSLIST")
	private  List<NewStatusList> newStatuslist;
	@SerializedName("SUB_STATUS")
	private  String subStatus;

	
	public List<NewStatusList> getNewStatusListItem() {
        if (newStatuslist == null) {
        	newStatuslist = new ArrayList<NewStatusList>();
        }
        return this.newStatuslist;
    }
	
	
	public List<NewStatusList> getNewStatuslist() {
		return newStatuslist;
	}

	public void setNewStatuslist(List<NewStatusList> newStatuslist) {
		this.newStatuslist = newStatuslist;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}
	
	

}
