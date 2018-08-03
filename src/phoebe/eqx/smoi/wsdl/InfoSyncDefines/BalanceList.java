package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BalanceList {

	@SerializedName("ACCT_ID")
	private String acctId;
	@SerializedName("BALANCERESULTLIST")
	private List<BalanceResultList> balanceResultList;

	public List<BalanceResultList> getBalanceResultListItem() {
        if (balanceResultList == null) {
        	balanceResultList = new ArrayList<BalanceResultList>();
        }
        return this.balanceResultList;
    }

	public List<BalanceResultList> getBalanceResultList() {
		return balanceResultList;
	}

	public void setBalanceResultList(List<BalanceResultList> balanceResultList) {
		this.balanceResultList = balanceResultList;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	
	
	
}
