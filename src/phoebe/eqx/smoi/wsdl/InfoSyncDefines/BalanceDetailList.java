package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class BalanceDetailList {
	
	@SerializedName("BALANCE_INSTANCE_ID")
	private String balanceInsId;
	@SerializedName("AMOUNT")
	private String amount;
	@SerializedName("INITIAL_AMOUNT")
	private String innitialAmount;
	@SerializedName("EFFECTIVE_TIME")
	private String effectiveAmount;
	@SerializedName("EXPIRE_TIME")
	private String expTime;
	@SerializedName("ORIGINAL_TYPE")
	private String orgType;
	@SerializedName("ORIGINAL_ID")
	private String orgId;
	@SerializedName("LAST_UPDATE_TIME")
	private String lastUpdateTime;

	@SerializedName("OLD_BALANCE_AMOUNT")
	private String oldBalanceAmount;
	@SerializedName("NEW_BALANCE_AMOUNT")
	private String newBalanceAmount;
	
	
	public String getBalanceInsId() {
		return balanceInsId;
	}

	public void setBalanceInsId(String balanceInsId) {
		this.balanceInsId = balanceInsId;
	}

	public String getOldBalanceAmount() {
		return oldBalanceAmount;
	}

	public void setOldBalanceAmount(String oldBalanceAmount) {
		this.oldBalanceAmount = oldBalanceAmount;
	}

	public String getInnitialAmount() {
		return innitialAmount;
	}

	public void setInnitialAmount(String innitialAmount) {
		this.innitialAmount = innitialAmount;
	}

	public String getEffectiveAmount() {
		return effectiveAmount;
	}

	public void setEffectiveAmount(String effectiveAmount) {
		this.effectiveAmount = effectiveAmount;
	}

	public String getExpTime() {
		return expTime;
	}

	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNewBalanceAmount() {
		return newBalanceAmount;
	}

	public void setNewBalanceAmount(String newBalanceAmount) {
		this.newBalanceAmount = newBalanceAmount;
	}

}
