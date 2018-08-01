package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import com.google.gson.annotations.SerializedName;

public class FreeUnitAdjInfoResponse {
	@SerializedName("FREE_UNIT_INSTANCE_ID")
	private String freeUnitInstanceId;
	@SerializedName("FREE_UNIT_ID")
	private String freeUnitId;
	@SerializedName("FREE_UNIT_NAME")
	private String freeUnitName;
	@SerializedName("MEASURE_UNIT")
	private String measureUnit;
	@SerializedName("MEASURE_UNIT_NAME")
	private String measureUnitName;
	@SerializedName("OLD_FREEUNIT_AMOUNT")
	private Long oldFreeunitAmount;
	@SerializedName("NEW_FREEUNIT_AMOUNT")
	private Long newFreeunitAmount;
	
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
	public String getFreeUnitName() {
		return freeUnitName;
	}
	public void setFreeUnitName(String freeUnitName) {
		this.freeUnitName = freeUnitName;
	}
	public String getMeasureUnit() {
		return measureUnit;
	}
	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}
	public String getMeasureUnitName() {
		return measureUnitName;
	}
	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}
	public Long getOldFreeunitAmount() {
		return oldFreeunitAmount;
	}
	public void setOldFreeunitAmount(Long oldFreeunitAmount) {
		this.oldFreeunitAmount = oldFreeunitAmount;
	}
	public Long getNewFreeunitAmount() {
		return newFreeunitAmount;
	}
	public void setNewFreeunitAmount(Long newFreeunitAmount) {
		this.newFreeunitAmount = newFreeunitAmount;
	}
	
	
}
