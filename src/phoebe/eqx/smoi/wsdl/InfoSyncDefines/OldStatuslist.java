package phoebe.eqx.smoi.wsdl.InfoSyncDefines;



import com.google.gson.annotations.SerializedName;

public class OldStatuslist {
	@SerializedName("OLD_STATUS_NAME")
	private  String oldStatusName;
	@SerializedName("OLD_EXPIRE_TIME")
	private  String oldExpireTime;
	@SerializedName("OLD_STATUS_INDEX")
	private  String oldStatusIndex;
	
	public String getOldStatusName() {
		return oldStatusName;
	}
	public void setOldStatusName(String oldStatusName) {
		this.oldStatusName = oldStatusName;
	}
	public String getOldExpireTime() {
		return oldExpireTime;
	}
	public void setOldExpireTime(String oldExpireTime) {
		this.oldExpireTime = oldExpireTime;
	}
	public String getOldStatusIndex() {
		return oldStatusIndex;
	}
	public void setOldStatusIndex(String oldStatusIndex) {
		this.oldStatusIndex = oldStatusIndex;
	}
}
