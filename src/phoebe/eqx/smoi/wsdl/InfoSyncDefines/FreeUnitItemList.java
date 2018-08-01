package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FreeUnitItemList {
	@SerializedName("FREE_UNIT_ID")
	private String freeUnitId;
	@SerializedName("FREE_UNIT_NAME")
	private String freeUnitName;
	@SerializedName("MEASURE_UNIT")
	private String measureUnit;
	@SerializedName("MEASURE_UNIT_NAME")
	private String measureUnitName;
	@SerializedName("TOTAL_INITIAL_AMOUNT")
	private String totalInitialAmount;
	@SerializedName("TOTAL_REMAINING_AMOUNT")
	private String totalRemainingAmount;
	@SerializedName("FREEUNITITEMDETAILLIST")
	private  List<FreeUnitItemDetail> freeUnitItemDetail;
	@SerializedName("MEMBERUSAGELIST")
	private  List<Memberusagelist> memberusagelists;
	
	public List<Memberusagelist> getMemberusageListItem() {
        if (memberusagelists == null) {
        	memberusagelists = new ArrayList<Memberusagelist>();
        }
        return this.memberusagelists;
    }
	
	public List<FreeUnitItemDetail> getFreeUnitItemDetailListItem() {
        if (freeUnitItemDetail == null) {
        	freeUnitItemDetail = new ArrayList<FreeUnitItemDetail>();
        }
        return this.freeUnitItemDetail;
    }

	public String getFreeUnitId() {
		return freeUnitId;
	}

	public void setFreeUnitId(String freeUnitId) {
		this.freeUnitId = freeUnitId;
	}

	public String getFreeUnitName() {
		return freeUnitName;
	}

	public void setFreeUnitName(String freeUnitName) {
		this.freeUnitName = freeUnitName;
	}

	public String getMeasureUnitName() {
		return measureUnitName;
	}

	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}

	public List<FreeUnitItemDetail> getFreeUnitItemDetail() {
		return freeUnitItemDetail;
	}

	public void setFreeUnitItemDetail(List<FreeUnitItemDetail> freeUnitItemDetail) {
		this.freeUnitItemDetail = freeUnitItemDetail;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public String getTotalInitialAmount() {
		return totalInitialAmount;
	}

	public void setTotalInitialAmount(String totalInitialAmount) {
		this.totalInitialAmount = totalInitialAmount;
	}

	public String getTotalRemainingAmount() {
		return totalRemainingAmount;
	}

	public void setTotalRemainingAmount(String totalRemainingAmount) {
		this.totalRemainingAmount = totalRemainingAmount;
	}

	public List<Memberusagelist> getMemberusagelists() {
		return memberusagelists;
	}

	public void setMemberusagelists(List<Memberusagelist> memberusagelists) {
		this.memberusagelists = memberusagelists;
	}
	
	
}
