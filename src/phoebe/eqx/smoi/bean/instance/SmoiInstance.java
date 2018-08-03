package phoebe.eqx.smoi.bean.instance;

import java.util.ArrayList;
import java.util.HashMap;

import ec02.af.utils.EquinoxUtilities;
import ec02.utils.Log;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;
import phoebe.eqx.model.ldap.ds2a.InquirySubscriberResponse;
import phoebe.eqx.smoi.utils.SMOIUtils;

public class SmoiInstance {

    private ClientRequestInfo requestInfo;
    //Mandatory HTTP Post Request
    private String msisdn;
    private String ssid;
    private String page;
    private String sgw;
    private String scp;
    private String flag;
    
    //-
    private InquirySubscriberResponse inquirySubscriberResponse;
    private long startTime;
    private String mapCmd;
    //-
    private String destinationHost;
    private String destinationRealm;
    
    DiameterCreditControlAnswer cca;

    private boolean bssbrokerFlag;
    private boolean flagModiPPSCallNotify;

    //AVATAR
    private boolean isAVATAR;

    // Chatl 22/09/2017 
    private boolean isRetry = false ;
    private String bwoid;
    private String adjustType;
    private ArrayList<EQLRequestInstance> eqlRequestInstance;
    
    
    public boolean isAVATAR() {
        return isAVATAR;
    }

    public void setAVATAR(boolean AVATAR) {
        isAVATAR = AVATAR;
    }

    private Boolean aBoolean = false;

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public boolean isBssbrokerFlag() {
		return bssbrokerFlag;
	}

	public void setBssbrokerFlag(boolean bssbrokerFlag) {
		this.bssbrokerFlag = bssbrokerFlag;
	}

	public DiameterCreditControlAnswer getCca() {
		return cca;
	}

	public void setCca(DiameterCreditControlAnswer cca) {
		this.cca = cca;
	}

	public boolean isFlagModiPPSCallNotify() {
		return flagModiPPSCallNotify;
	}

	public void setFlagModiPPSCallNotify(boolean flagModiPPSCallNotify) {
		this.flagModiPPSCallNotify = flagModiPPSCallNotify;
	}

	public SmoiInstance() {
        requestInfo = new ClientRequestInfo();
        eqlRequestInstance = new ArrayList<EQLRequestInstance>();
    }

    public InquirySubscriberResponse getInquirySubscriberResponse() {
        return inquirySubscriberResponse;
    }

    public void setInquirySubscriberResponse(InquirySubscriberResponse inquirySubscriberResponse) {
        this.inquirySubscriberResponse = inquirySubscriberResponse;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }
    
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

	/**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the ssid
     */
    public String getSsid() {
        return ssid;
    }

    /**
     * @param ssid the ssid to set
     */
    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    /**
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * @return the sgw
     */
    public String getSgw() {
        return sgw;
    }

    /**
     * @param sgw the sgw to set
     */
    public void setSgw(String sgw) {
        this.sgw = sgw;
    }

    public String getHttpPostValue(String key) {
        //Log.d("RequestMessage:" + this.getRequestInfo().getRequestMessage());
        String value = SMOIUtils.getHttpPostValue(this.getRequestInfo().getRequestMessage(), key);
        Log.d("key=" + key + ",value=" + value);
        return value;
    }

    /**
     * @return the requestInfo
     */
    public ClientRequestInfo getRequestInfo() {
        return requestInfo;
    }

    /**
     * @param requestInfo the requestInfo to set
     */
    public void setClientRequestInfo(ClientRequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    /**
     * @return the scp
     */
    public String getScp() {
        return scp;
    }

    /**
     * @param scp the scp to set
     */
    public void setScp(String scp) {
        this.scp = scp;
    }

    /**
     * @return the mapCmd
     */
    public String getMapCmd() {
        return mapCmd;
    }

    /**
     * @param mapCmd the mapCmd to set
     */
    public void setMapCmd(String mapCmd) {
        this.mapCmd = mapCmd;
    }

    /**
     * TODO
     *
     * @author nuiss
     * @since 2013-08-09
     * @see resourceName_Standby_MaxRetry
     */
    private HashMap<String, Integer> hmStandbyMaxRetry = null;

    
    public void setResourceNameStandbyMaxRetry(EquinoxUtilities _eqxUtils, String _keyResourceNameStandbyMaxRetry) {
        if (_eqxUtils.getHmWarmConfig() != null
                && _eqxUtils.getHmWarmConfig().get(_keyResourceNameStandbyMaxRetry).get(0) != null
                && !_eqxUtils.getHmWarmConfig().get(_keyResourceNameStandbyMaxRetry).get(0).trim().equals("")) {
            this.setResourceNameStandbyMaxRetry(_keyResourceNameStandbyMaxRetry, Integer.parseInt(_eqxUtils.getHmWarmConfig().get(_keyResourceNameStandbyMaxRetry).get(0).trim()));
        }
    }

    public void setResourceNameStandbyMaxRetry(String _keyResourceNameStandbyMaxRetry, int _valueOfMaxRetry) {
        if (this.hmStandbyMaxRetry == null) {
            this.hmStandbyMaxRetry = new HashMap<String, Integer>();
        }
        /*ec02.utils.Log.d("==========> setResourceNameStandbyMaxRetry : "
         +"key="+_keyResourceNameStandbyMaxRetry
         +",value="+_valueOfMaxRetry);*/
        this.hmStandbyMaxRetry.put(_keyResourceNameStandbyMaxRetry, _valueOfMaxRetry);
    }

    public int getResourceNameStandbyMaxRetry(String _keyResourceNameStandbyMaxRetry) {
        int valueOfMaxRetry = 0;
        if (this.hmStandbyMaxRetry != null
                && this.hmStandbyMaxRetry.size() > 0
                && this.hmStandbyMaxRetry.containsKey(_keyResourceNameStandbyMaxRetry)) {
            valueOfMaxRetry = Integer.valueOf(this.hmStandbyMaxRetry.get(_keyResourceNameStandbyMaxRetry));
        }
        /*ec02.utils.Log.d("==========> getResourceNameStandbyMaxRetry : "
         +"key="+_keyResourceNameStandbyMaxRetry
         +",value="+valueOfMaxRetry);*/
        return valueOfMaxRetry;
    }

    /**
     * @return the destinationHost
     */
    public String getDestinationHost() {
        return destinationHost;
    }

    /**
     * @param destinationHost the destinationHost to set
     */
    public void setDestinationHost(String destinationHost) {
        this.destinationHost = destinationHost;
    }

    /**
     * @return the destinationRealm
     */
    public String getDestinationRealm() {
        return destinationRealm;
    }

    /**
     * @param destinationRealm the destinationRealm to set
     */
    public void setDestinationRealm(String destinationRealm) {
        this.destinationRealm = destinationRealm;
    }

	public boolean isRetry() {
		return isRetry;
	}

	public void setRetry(boolean isRetry) {
		this.isRetry = isRetry;
	}

	public String getBwoid() {
		return bwoid;
	}

	public void setBwoid(String bwoid) {
		this.bwoid = bwoid;
	}

	public String getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	public ArrayList<EQLRequestInstance> getEqlRequestInstance() {
		return eqlRequestInstance;
	}

	public void setEqlRequestInstance(ArrayList<EQLRequestInstance> eqlRequestInstance) {
		this.eqlRequestInstance = eqlRequestInstance;
	}
	
}
