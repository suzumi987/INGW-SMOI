package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class Adjustmentinfolist {
	@SerializedName("BALANCE_TYPE")
	private String balanceType;
	@SerializedName("BALANCE_ID")
	private String balanceId;
	@SerializedName("BALANCE_TYPE_NAME")
	private String balanceTypeName;
	@SerializedName("OLD_BALANCE_AMOUNT")
	private Long oldBalanceAmount;
	@SerializedName("NEW_BALANCE_AMOUNT")
	private Long newBalanceAmount;
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
	public String getBalanceTypeName() {
		return balanceTypeName;
	}
	public void setBalanceTypeName(String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
	}
	public Long getOldBalanceAmount() {
		return oldBalanceAmount;
	}
	public void setOldBalanceAmount(Long oldBalanceAmount) {
		this.oldBalanceAmount = oldBalanceAmount;
	}
	public Long getNewBalanceAmount() {
		return newBalanceAmount;
	}
	public void setNewBalanceAmount(Long newBalanceAmount) {
		this.newBalanceAmount = newBalanceAmount;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
}
