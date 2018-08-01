package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BalanceResultList {

	@SerializedName("BALANCE_TYPE")
	private String balanceType;
	@SerializedName("BALANCE_TYPE_NAME")
	private String balanceTypeName;
	@SerializedName("BALANCE_TYPE_NAME_THAI")
	private String balanceTypeNameThai;
	@SerializedName("TOTAL_AMOUNT")
	private String totalAmount;
	@SerializedName("RESERVED_AMOUNT")
	private String reservedAmount;
	@SerializedName("DEPOSIT_FLAG")
	private String depositFlag;
	@SerializedName("REFUND_FLAG")
	private String refundFlag;
	@SerializedName("CURRENCY_NAME")
	private String currencyName;
	@SerializedName("BALANCEDETAILLIST")
	private List<BalanceDetailList> balanceDetailList;

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public String getBalanceTypeName() {
		return balanceTypeName;
	}

	public void setBalanceTypeName(String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public List<BalanceDetailList> getBalanceDetailList() {
		return balanceDetailList;
	}

	public void setBalanceDetailList(List<BalanceDetailList> balanceDetailList) {
		this.balanceDetailList = balanceDetailList;
	}
	
	public List<BalanceDetailList> getBalanceDetailItem() {
        if (balanceDetailList == null) {
        	balanceDetailList = new ArrayList<BalanceDetailList>();
        }
        return this.balanceDetailList;
    }

	public String getBalanceTypeNameThai() {
		return balanceTypeNameThai;
	}

	public void setBalanceTypeNameThai(String balanceTypeNameThai) {
		this.balanceTypeNameThai = balanceTypeNameThai;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getReservedAmount() {
		return reservedAmount;
	}

	public void setReservedAmount(String reservedAmount) {
		this.reservedAmount = reservedAmount;
	}

	public String getDepositFlag() {
		return depositFlag;
	}

	public void setDepositFlag(String depositFlag) {
		this.depositFlag = depositFlag;
	}

	public String getRefundFlag() {
		return refundFlag;
	}

	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}
	
	
	
}
