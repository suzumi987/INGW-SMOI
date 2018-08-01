package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class FreeUnitAdjInfo {
	@SerializedName("FREE_UNIT_INSTANCE_ID")
	private String freeUnitInstanceId;
	@SerializedName("FREE_UNIT_ID")
	private String freeUnitId;
	@SerializedName("ADJUSTMENT_TYPE")
	private String adjType;
	@SerializedName("ADJUSTMENT_AMOUNT")
	private String adjAmount;
	@SerializedName("EFFECTIVE_TIME")
	private String effecttiveTime;
	@SerializedName("EXPIRE_TIME")
	private String expireTime;
	
	public String getFreeUnitInstanceId() {
		return freeUnitInstanceId;
	}
	public void setFreeUnitInstanceId(String freeUnitInstanceId) {
		this.freeUnitInstanceId = freeUnitInstanceId;
	}
	public String getFreeUnitId() {
		return freeUnitId;
	}
	public void setFreeUnitId(String freeUnitId) {
		this.freeUnitId = freeUnitId;
	}
	public String getAdjType() {
		return adjType;
	}
	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}
	public String getAdjAmount() {
		return adjAmount;
	}
	public void setAdjAmount(String adjAmount) {
		this.adjAmount = adjAmount;
	}
	public String getEffecttiveTime() {
		return effecttiveTime;
	}
	public void setEffecttiveTime(String effecttiveTime) {
		this.effecttiveTime = effecttiveTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	
	
}
