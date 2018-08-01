package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class FreeUnitItemDetail {
	@SerializedName("FREE_UNIT_INSTANCE_ID")
	private String freeUnitInsId;
	@SerializedName("OLD_FREEUNIT_AMOUNT")
	private String oldFreeUnitAmount;
	@SerializedName("NEW_FREEUNIT_AMOUNT")
	private String newFreeUnitAmount;
	
	@SerializedName("INITIAL_AMOUNT")
	private String innitialAmount;
	@SerializedName("REMAINING_AMOUNT")
	private String remainingAmount;
	@SerializedName("EFFECTIVE_TIME")
	private String effectiveTime;
	@SerializedName("EXPIRE_TIME")
	private String expireTime;
	@SerializedName("ORIGIN_TYPE")
	private String originType;
	@SerializedName("ORIGIN_PRODUCT_ID")
	private String originProductId;
	@SerializedName("ORIGIN_PRODUCT_TYPE")
	private String originProductType;
	@SerializedName("ORIGIN_PRODUCT_SEQUENCE_ID")
	private String originProductSequenceId;
	@SerializedName("ORIGIN_PRODUCT_NAME")
	private String originProductName;
	@SerializedName("ORIGIN_NOTIFICATION_NAME")
	private String originNotificationName;
	@SerializedName("ORIGIN_PLAN_ID")
	private String originPlanName;
	@SerializedName("ROLL_OVER_FLAG")
	private String rollOverFlag;
	@SerializedName("LAST_ROLLOVERED_TIME")
	private String lastRolloveredTime;
	
	
	public String getFreeUnitInsId() {
		return freeUnitInsId;
	}
	public void setFreeUnitInsId(String freeUnitInsId) {
		this.freeUnitInsId = freeUnitInsId;
	}
	public String getOldFreeUnitAmount() {
		return oldFreeUnitAmount;
	}
	public void setOldFreeUnitAmount(String oldFreeUnitAmount) {
		this.oldFreeUnitAmount = oldFreeUnitAmount;
	}
	public String getNewFreeUnitAmount() {
		return newFreeUnitAmount;
	}
	public void setNewFreeUnitAmount(String newFreeUnitAmount) {
		this.newFreeUnitAmount = newFreeUnitAmount;
	}
	public String getInnitialAmount() {
		return innitialAmount;
	}
	public void setInnitialAmount(String innitialAmount) {
		this.innitialAmount = innitialAmount;
	}
	public String getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public String getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getOriginType() {
		return originType;
	}
	public void setOriginType(String originType) {
		this.originType = originType;
	}
	public String getOriginProductId() {
		return originProductId;
	}
	public void setOriginProductId(String originProductId) {
		this.originProductId = originProductId;
	}
	public String getOriginProductType() {
		return originProductType;
	}
	public void setOriginProductType(String originProductType) {
		this.originProductType = originProductType;
	}
	public String getOriginProductSequenceId() {
		return originProductSequenceId;
	}
	public void setOriginProductSequenceId(String originProductSequenceId) {
		this.originProductSequenceId = originProductSequenceId;
	}
	public String getOriginProductName() {
		return originProductName;
	}
	public void setOriginProductName(String originProductName) {
		this.originProductName = originProductName;
	}
	public String getOriginNotificationName() {
		return originNotificationName;
	}
	public void setOriginNotificationName(String originNotificationName) {
		this.originNotificationName = originNotificationName;
	}
	public String getOriginPlanName() {
		return originPlanName;
	}
	public void setOriginPlanName(String originPlanName) {
		this.originPlanName = originPlanName;
	}
	public String getRollOverFlag() {
		return rollOverFlag;
	}
	public void setRollOverFlag(String rollOverFlag) {
		this.rollOverFlag = rollOverFlag;
	}
	public String getLastRolloveredTime() {
		return lastRolloveredTime;
	}
	public void setLastRolloveredTime(String lastRolloveredTime) {
		this.lastRolloveredTime = lastRolloveredTime;
	}
	
	
}
