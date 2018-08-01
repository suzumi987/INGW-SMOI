package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class AdjustmentinfolistBalance {
	@SerializedName("BALANCE_TYPE")
	private String balanceType;
	@SerializedName("BALANCE_ID")
	private String balanceId;
	@SerializedName("ADJUSTMENT_TYPE")
	private String adjustmentType;
	@SerializedName("ADJUSTMENT_AMOUNT")
	private String adjustmentAmout;
	@SerializedName("CURRENCY_NAME")
	private String currencyName;
	@SerializedName("EFFECTIVE_TIME")
	private String effectiveTime;
	@SerializedName("EXPIRE_TIME")
	private String expireTime;
	
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	public String getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}
	public String getAdjustmentType() {
		return adjustmentType;
	}
	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}
	public String getAdjustmentAmout() {
		return adjustmentAmout;
	}
	public void setAdjustmentAmout(String adjustmentAmout) {
		this.adjustmentAmout = adjustmentAmout;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
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
	
}
