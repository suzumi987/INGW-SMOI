package phoebe.eqx.smoi.message.builder;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import phoebe.eqx.abstracts.AEQXMsg;
import phoebe.eqx.enums.dcc.ccr.EDCC_CCR;
import phoebe.eqx.enums.dcc.ccr.EDCC_CCR_SERVINFO_IN;
import phoebe.eqx.enums.dcc.ccr.EDCC_CCR_SUBSCPTID;
import phoebe.eqx.interfaces.dcc.ccr.IDCC_CCR;
import phoebe.eqx.interfaces.dcc.ccr.IDCC_CCR_SERVINFO_IN;
import phoebe.eqx.interfaces.dcc.ccr.IDCC_CCR_SUBSCPTID;

public class CCRMessage extends AEQXMsg implements IDCC_CCR
, IDCC_CCR_SUBSCPTID
, IDCC_CCR_SERVINFO_IN {

    //CCR Body
    private String sessionid;
    private String originHost;
    private String originRealm;
    private String destinationHost;
    private String destinationRealm;
    private String authApplicationId;
    private String serviceContextId;
    private String ccRequestType;
    private String ccRequestNumber;
    private String eventTimeStamp;
    private String requestAction;
    //CCR SubscrId Body
    private String subscriptionIdType;
    private String subscriptionIdData;
    //CCR in Body
    private String etopupSessionId;
    private String modaccinfoProductId; 
    private String modaccinfoResourceId;
    private String modaccinfoModifyAmount;
    private String modaccinfoValidityAmount;
    private String modaccinfoDeductType;
    private String rechargePartnerId;
    private String serviceId;
    private String modifyType;
    private String queryType;
    private String accessMethod;

    private String channelId;
    private String transparentData1;
    private String transparentData2;
    private String transparentData3;
    
    private String measureId;
    
    private String inMeasureId;

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(String serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    private String serviceIdentifier;
    
    @Override
    public Map<EDCC_CCR, String> getDccCcrBody() {
        Map<EDCC_CCR, String> body = new EnumMap<EDCC_CCR, String>(EDCC_CCR.class);
        if (this.getSessionid() != null && !"".equals(this.getSessionid())) {
            body.put(EDCC_CCR.SESSION_ID, this.getSessionid());
        }
        if (this.getOriginHost() != null && !"".equals(this.getOriginHost())) {
            body.put(EDCC_CCR.ORIGIN_HOST, this.getOriginHost());
        }
        if (this.getOriginRealm() != null && !"".equals(this.getOriginRealm())) {
            body.put(EDCC_CCR.ORIGIN_REALM, this.getOriginRealm());
        }
        if (this.getDestinationHost() != null && !"".equals(this.getDestinationHost())) {
            body.put(EDCC_CCR.DESTINATION_HOST, this.getDestinationHost());
        }
        if (this.getDestinationRealm() != null && !"".equals(this.getDestinationRealm())) {
            body.put(EDCC_CCR.DESTINATION_REALM, this.getDestinationRealm());
        }
        if (this.getAuthApplicationId() != null && !"".equals(this.getAuthApplicationId())) {
            body.put(EDCC_CCR.AUTH_APPLICATION_ID, this.getAuthApplicationId());
        }
        if (this.getServiceContextId() != null && !"".equals(this.getServiceContextId())) {
            body.put(EDCC_CCR.SERVICE_CONTEXT_ID, this.getServiceContextId());
        }
        if (this.getCcRequestType() != null && !"".equals(this.getCcRequestType())) {
            body.put(EDCC_CCR.CC_REQUEST_TYPE, this.getCcRequestType());
        }
        if (this.getCcRequestNumber() != null && !"".equals(this.getCcRequestNumber())) {
            body.put(EDCC_CCR.CC_REQUEST_NUMBER, this.getCcRequestNumber());
        }
        if (this.getEventTimeStamp() != null && !"".equals(this.getEventTimeStamp())) {
            body.put(EDCC_CCR.EVENT_TIMESTAMP, this.getEventTimeStamp());
        }
        if (this.getRequestAction() != null && !"".equals(this.getRequestAction())) {
            body.put(EDCC_CCR.REQUESTED_ACTION, this.getRequestAction());
        } 
        if (this.getMeasureId() != null && !"".equals(this.getMeasureId())) {
            body.put(EDCC_CCR.MEASURE_ID, this.getMeasureId());
        }
        if (this.getServiceIdentifier() != null && !"".equals(this.getServiceIdentifier())) {
            body.put(EDCC_CCR.SERVICE_IDENTIFIER, this.getServiceIdentifier());
        }
        return body;
    }

    @Override
    public List<Map<EDCC_CCR_SUBSCPTID, String>> getDccCcrSubscrIdBody() {
        List<Map<EDCC_CCR_SUBSCPTID, String>> SubscriptIDList = new ArrayList<Map<EDCC_CCR_SUBSCPTID, String>>();
        Map<EDCC_CCR_SUBSCPTID, String> SubscriptID = new EnumMap<EDCC_CCR_SUBSCPTID, String>(EDCC_CCR_SUBSCPTID.class);
        if (this.getSubscriptionIdType() != null && !"".equals(this.getSubscriptionIdType())) {
            SubscriptID.put(EDCC_CCR_SUBSCPTID.SUBSCRIPTION_ID_TYPE, this.getSubscriptionIdType());
        }
        if (this.getSubscriptionIdData() != null && !"".equals(this.getSubscriptionIdData())) {
            SubscriptID.put(EDCC_CCR_SUBSCPTID.SUBSCRIPTION_ID_DATA, this.getSubscriptionIdData());
        }
        SubscriptIDList.add(SubscriptID);
        return SubscriptIDList;
    }

    @Override
    public Map<EDCC_CCR_SERVINFO_IN, String> getDccCcrInBody() {
        Map<EDCC_CCR_SERVINFO_IN, String> ServiceINFO_In = new EnumMap<EDCC_CCR_SERVINFO_IN, String>(EDCC_CCR_SERVINFO_IN.class);
        if (this.getEtopupSessionId() != null && !"".equals(this.getEtopupSessionId())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_ETOPUP_SESSION_ID, this.getEtopupSessionId());
        }
        if (this.getModaccinfoProductId() != null && !"".equals(this.getModaccinfoProductId())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_MODACCINFO_PRODUCT_ID, this.getModaccinfoProductId());
        }
        if (this.getModaccinfoResourceId() != null && !"".equals(this.getModaccinfoResourceId())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_MODACCINFO_RESOURCE_ID, this.getModaccinfoResourceId());
        }
        if (this.getModaccinfoModifyAmount() != null && !"".equals(this.getModaccinfoModifyAmount())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_MODACCINFO_MODIFY_AMOUNT, this.getModaccinfoModifyAmount());
        }
        if (this.getModaccinfoValidityAmount() != null && !"".equals(this.getModaccinfoValidityAmount())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_MODACCINFO_VALIDITY_AMOUNT, this.getModaccinfoValidityAmount());
        }
        if (this.getModaccinfoDeductType() != null && !"".equals(this.getModaccinfoDeductType())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_MODACCINFO_DEDUCT_TYPE, this.getModaccinfoDeductType());
        }
        if (this.getRechargePartnerId() != null && !"".equals(this.getRechargePartnerId())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_RECHARGE_PARTNER_ID, this.getRechargePartnerId());
        }
        if (this.getServiceId() != null && !"".equals(this.getServiceId())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_SERVICE_ID, this.getServiceId());
        }
        if (this.getChannelId() != null && !"".equals(this.getChannelId())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_CHANNEL_ID, this.getChannelId());
        }
        if (this.getTransparentData1() != null && !"".equals(this.getTransparentData1())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_TRANSPARENT_DATA_1, this.getTransparentData1());
        }
        if (this.getTransparentData2() != null && !"".equals(this.getTransparentData2())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_TRANSPARENT_DATA_2, this.getTransparentData2());
        }
        if (this.getTransparentData3() != null && !"".equals(this.getTransparentData3())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_TRANSPARENT_DATA_3, this.getTransparentData3());
        }
        if (this.getINMeasureId() != null && !"".equals(this.getINMeasureId())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_MEASURE_ID, this.getINMeasureId());
        }
        if (this.getModifyType() != null && !"".equals(this.getModifyType())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_MODIFY_TYPE, this.getModifyType());
        }
       if (this.getQueryType() != null && !"".equals(this.getQueryType())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_QUERY_TYPE, this.getQueryType());
        }
        if (this.getAccessMethod() != null && !"".equals(this.getAccessMethod())) {
            ServiceINFO_In.put(EDCC_CCR_SERVINFO_IN.IN_ACCESS_METHOD, this.getAccessMethod());
        }
        return ServiceINFO_In;
    }

    /**
     * @return the sessionid
     */
    public String getSessionid() {
        return sessionid;
    }

    /**
     * @param sessionid the sessionid to set
     */
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    /**
     * @return the originHost
     */
    public String getOriginHost() {
        return originHost;
    }

    /**
     * @param originHost the originHost to set
     */
    public void setOriginHost(String originHost) {
        this.originHost = originHost;
    }

    /**
     * @return the originRealm
     */
    public String getOriginRealm() {
        return originRealm;
    }

    /**
     * @param originRealm the originRealm to set
     */
    public void setOriginRealm(String originRealm) {
        this.originRealm = originRealm;
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

    /**
     * @return the authApplicationId
     */
    public String getAuthApplicationId() {
        return authApplicationId;
    }

    /**
     * @param authApplicationId the authApplicationId to set
     */
    public void setAuthApplicationId(String authApplicationId) {
        this.authApplicationId = authApplicationId;
    }

    /**
     * @return the serviceContextId
     */
    public String getServiceContextId() {
        return serviceContextId;
    }

    /**
     * @param serviceContextId the serviceContextId to set
     */
    public void setServiceContextId(String serviceContextId) {
        this.serviceContextId = serviceContextId;
    }

    /**
     * @return the ccRequestType
     */
    public String getCcRequestType() {
        return ccRequestType;
    }

    /**
     * @param ccRequestType the ccRequestType to set
     */
    public void setCcRequestType(String ccRequestType) {
        this.ccRequestType = ccRequestType;
    }

    /**
     * @return the ccRequestNumber
     */
    public String getCcRequestNumber() {
        return ccRequestNumber;
    }

    /**
     * @param ccRequestNumber the ccRequestNumber to set
     */
    public void setCcRequestNumber(String ccRequestNumber) {
        this.ccRequestNumber = ccRequestNumber;
    }

    /**
     * @return the eventTimeStamp
     */
    public String getEventTimeStamp() {
        return eventTimeStamp;
    }

    /**
     * @param eventTimeStamp the eventTimeStamp to set
     */
    public void setEventTimeStamp(String eventTimeStamp) {
        this.eventTimeStamp = eventTimeStamp;
    }

    /**
     * @return the requestAction
     */
    public String getRequestAction() {
        return requestAction;
    }

    /**
     * @param requestAction the requestAction to set
     */
    public void setRequestAction(String requestAction) {
        this.requestAction = requestAction;
    }

    /**
     * @return the subscriptionIdType
     */
    public String getSubscriptionIdType() {
        return subscriptionIdType;
    }

    /**
     * @param subscriptionIdType the subscriptionIdType to set
     */
    public void setSubscriptionIdType(String subscriptionIdType) {
        this.subscriptionIdType = subscriptionIdType;
    }

    /**
     * @return the subscriptionIdData
     */
    public String getSubscriptionIdData() {
        return subscriptionIdData;
    }

    /**
     * @param subscriptionIdData the subscriptionIdData to set
     */
    public void setSubscriptionIdData(String subscriptionIdData) {
        this.subscriptionIdData = subscriptionIdData;
    }

    /**
     * @return the etopupSessionId
     */
    public String getEtopupSessionId() {
        return etopupSessionId;
    }

    /**
     * @param etopupSessionId the etopupSessionId to set
     */
    public void setEtopupSessionId(String etopupSessionId) {
        this.etopupSessionId = etopupSessionId;
    }

    /**
     * @return the modaccinfoProductId
     */
    public String getModaccinfoProductId() {
        return modaccinfoProductId;
    }

    /**
     * @param modaccinfoProductId the modaccinfoProductId to set
     */
    public void setModaccinfoProductId(String modaccinfoProductId) {
        this.modaccinfoProductId = modaccinfoProductId;
    }
 
    /**
     * @return the modaccinfoModifyAmount
     */
    public String getModaccinfoModifyAmount() {
        return modaccinfoModifyAmount;
    }

    /**
     * @param modaccinfoModifyAmount the modaccinfoModifyAmount to set
     */
    public void setModaccinfoModifyAmount(String modaccinfoModifyAmount) {
        this.modaccinfoModifyAmount = modaccinfoModifyAmount;
    }

    /**
     * @return the modaccinfoValidityAmount
     */
    public String getModaccinfoValidityAmount() {
        return modaccinfoValidityAmount;
    }

    /**
     * @param modaccinfoValidityAmount the modaccinfoValidityAmount to set
     */
    public void setModaccinfoValidityAmount(String modaccinfoValidityAmount) {
        this.modaccinfoValidityAmount = modaccinfoValidityAmount;
    }

    /**
     * @return the rechargePartnerId
     */
    public String getRechargePartnerId() {
        return rechargePartnerId;
    }

    /**
     * @param rechargePartnerId the rechargePartnerId to set
     */
    public void setRechargePartnerId(String rechargePartnerId) {
        this.rechargePartnerId = rechargePartnerId;
    }

    /**
     * @return the serviceId
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return the modifyType
     */
    public String getModifyType() {
        return modifyType;
    }

    /**
     * @param modifyType the modifyType to set
     */
    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }

    /**
     * @return the modaccinfoDeductType
     */
    public String getModaccinfoDeductType() {
        return modaccinfoDeductType;
    }

    /**
     * @param modaccinfoDeductType the modaccinfoDeductType to set
     */
    public void setModaccinfoDeductType(String modaccinfoDeductType) {
        this.modaccinfoDeductType = modaccinfoDeductType;
    }

    /**
     * @return the queryType
     */
    public String getQueryType() {
        return queryType;
    }

    /**
     * @param queryType the queryType to set
     */
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    /**
     * @return the accessMethod
     */
    public String getAccessMethod() {
        return accessMethod;
    }

    /**
     * @param accessMethod the accessMethod to set
     */
    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }
    
 
    public String getChannelId() {
        return channelId;
    }
 
    public void setChannelId(String _channelId) {
        this.channelId = _channelId;
    }
    
    public String getModaccinfoResourceId() {
        return modaccinfoResourceId;
    }
    public void setModaccinfoResourceId(String _modaccinfoResourceId) {
        this.modaccinfoResourceId = _modaccinfoResourceId;
    } 
    
    public String getTransparentData1() {
        return transparentData1;
    }
    public void setTransparentData1(String _transparentData1) {
        this.transparentData1 = _transparentData1;
    } 
    
    public String getTransparentData2() {
        return transparentData2;
    }
    public void setTransparentData2(String _transparentData2) {
        this.transparentData2= _transparentData2;
    }
    
    public String getTransparentData3() {
        return transparentData3;
    }
    public void setTransparentData3(String _transparentData3) {
        this.transparentData3= _transparentData3;
    }
    
    public String getMeasureId() {
        return measureId;
    }
    public void setMeasureId(String _measureId) {
        this.measureId= _measureId;
    }
    
    public String getINMeasureId() {
        return inMeasureId;
    }
    public void setINMeasureId(String _inMeasureId) {
        this.inMeasureId= _inMeasureId;
    }
    
}
