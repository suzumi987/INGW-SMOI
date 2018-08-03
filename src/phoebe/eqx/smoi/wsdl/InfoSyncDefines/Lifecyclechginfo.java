package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Lifecyclechginfo {
	@SerializedName("OLD_STATUSLIST")
	private  List<OldStatuslist> oldStatuslist;
	@SerializedName("NEW_STATUSLIST")
	private  List<NewStatusList> newStatuslist;
	@SerializedName("SUB_STATUS")
	private  String subStatus;
	

	public List<OldStatuslist> getOldStatusListItem() {
        if (oldStatuslist == null) {
        	oldStatuslist = new ArrayList<OldStatuslist>();
        }
        return this.oldStatuslist;
    }
	
	public List<NewStatusList> getNewStatusListItem() {
        if (newStatuslist == null) {
        	newStatuslist = new ArrayList<NewStatusList>();
        }
        return this.newStatuslist;
    }
	
	
	public List<OldStatuslist> getOldStatuslist() {
		return oldStatuslist;
	}

	public void setOldStatuslist(List<OldStatuslist> oldStatuslist) {
		this.oldStatuslist = oldStatuslist;
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
