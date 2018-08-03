package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class LifeCycleStatus {
	@SerializedName("STATUS_NAME")
	private String statusName;
	@SerializedName("STATUS_EXPIRE_TIME")
	private String statusExpireTime;
	@SerializedName("STATUS_INDEX")
	private String statusIndex;
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusExpireTime() {
		return statusExpireTime;
	}
	public void setStatusExpireTime(String statusExpireTime) {
		this.statusExpireTime = statusExpireTime;
	}
	public String getStatusIndex() {
		return statusIndex;
	}
	public void setStatusIndex(String statusIndex) {
		this.statusIndex = statusIndex;
	}
	
}
