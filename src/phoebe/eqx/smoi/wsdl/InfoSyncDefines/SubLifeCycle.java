package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SubLifeCycle {
	@SerializedName("SUB_STATUS")
	private String subStatus;
	@SerializedName("LIFECYCLESTATUS")
	private List<LifeCycleStatus> lifeCycleStatus;
	@SerializedName("PPS_SUSPEND_FRAUD")
	private String ppsSuspFraud;
	@SerializedName("FRAUD_TIMES")
	private String fraudTime;
	@SerializedName("SUSPEND_CUSTOMER_REQUEST")
	private String suspCusReq;
	@SerializedName("SUSPEND_MISSING_CLAIMING")
	private String suspMissingClaiming;
	@SerializedName("SUSPEND_DEBT")
	private String suspDebt;
	@SerializedName("SUSPEND_CREDIT_LIMIT")
	private String suspCreditLimit;
	@SerializedName("SUSPEND_STATUS")
	private String suspStatus;
	@SerializedName("SUSPEND_FRAUD")
	private String suspFraud;
	
	public List<LifeCycleStatus> getLifeCycleStatusListItem() {
        if (lifeCycleStatus == null) {
        	lifeCycleStatus = new ArrayList<LifeCycleStatus>();
        }
        return this.lifeCycleStatus;
    }

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public List<LifeCycleStatus> getLifeCycleStatus() {
		return lifeCycleStatus;
	}

	public void setLifeCycleStatus(List<LifeCycleStatus> lifeCycleStatus) {
		this.lifeCycleStatus = lifeCycleStatus;
	}

	public String getPpsSuspFraud() {
		return ppsSuspFraud;
	}

	public void setPpsSuspFraud(String ppsSuspFraud) {
		this.ppsSuspFraud = ppsSuspFraud;
	}

	public String getFraudTime() {
		return fraudTime;
	}

	public void setFraudTime(String fraudTime) {
		this.fraudTime = fraudTime;
	}

	public String getSuspCusReq() {
		return suspCusReq;
	}

	public void setSuspCusReq(String suspCusReq) {
		this.suspCusReq = suspCusReq;
	}

	public String getSuspMissingClaiming() {
		return suspMissingClaiming;
	}

	public void setSuspMissingClaiming(String suspMissingClaiming) {
		this.suspMissingClaiming = suspMissingClaiming;
	}

	public String getSuspDebt() {
		return suspDebt;
	}

	public void setSuspDebt(String suspDebt) {
		this.suspDebt = suspDebt;
	}

	public String getSuspCreditLimit() {
		return suspCreditLimit;
	}

	public void setSuspCreditLimit(String suspCreditLimit) {
		this.suspCreditLimit = suspCreditLimit;
	}

	public String getSuspStatus() {
		return suspStatus;
	}

	public void setSuspStatus(String suspStatus) {
		this.suspStatus = suspStatus;
	}

	public String getSuspFraud() {
		return suspFraud;
	}

	public void setSuspFraud(String suspFraud) {
		this.suspFraud = suspFraud;
	}
	
	
}
