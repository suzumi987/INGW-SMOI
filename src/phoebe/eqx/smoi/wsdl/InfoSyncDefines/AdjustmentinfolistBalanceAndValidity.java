package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class AdjustmentinfolistBalanceAndValidity {

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

}
