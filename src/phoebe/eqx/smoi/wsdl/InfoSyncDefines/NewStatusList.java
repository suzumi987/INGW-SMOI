package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class NewStatusList {
	
	@SerializedName("NEW_STATUS_NAME")
	private String newStatusName;
	@SerializedName("NEW_EXPIRE_TIME")
	private String newExpireTime;
	@SerializedName("NEW_STATUS_INDEX")
	private String newStatusIndex;
	
	public String getNewStatusName() {
		return newStatusName;
	}
	public void setNewStatusName(String newStatusName) {
		this.newStatusName = newStatusName;
	}
	public String getNewExpireTime() {
		return newExpireTime;
	}
	public void setNewExpireTime(String newExpireTime) {
		this.newExpireTime = newExpireTime;
	}
	public String getNewStatusIndex() {
		return newStatusIndex;
	}
	public void setNewStatusIndex(String newStatusIndex) {
		this.newStatusIndex = newStatusIndex;
	}
	
	
}
