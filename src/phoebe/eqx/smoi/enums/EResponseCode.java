package phoebe.eqx.smoi.enums;

public enum EResponseCode {

    SUCCESS("000", "SUCCESS"),
    DS2A_InquirySubscriberRequestError("300","INGateway Receive DS2A InquirySubscriber Request Error"), //ecode =1
    DS2A_InquirySubscriberRequestError_333("333","INGateway Receive DS2A InquirySubscriber Response Error"), //ecode =1
    DS2A_NotFoundServiceLocation("334","INGateway Receive DS2A Not Found Service Location"),
    DS2A_Bad("334","INGateway Receive DS2A Bad InquirySubscriber Response"),
    DS2A_InquirySubscriber_Response_Reject("333","INGateway Receive DS2A InquirySubscriber Response Reject"),
//    DS2A_InquirySubscriberRequest_Reject("333","INGateway Receive DS2A InquirySubscriber Request Reject"),
    DS2A_InquirySubscriberRequest_Error("333","INGateway Receive DS2A InquirySubscriber Request Error"),
    DS2A_InquirySubscriberRequest_Abort("333","INGateway Receive DS2A InquirySubscriber Request Abort"),
    DS2A_InquirySubscriber_Reject_response("333","INGateway Receive DS2A InquirySubscriber Response Reject"),
    DS2A_InquirySubscriber_Error_response("333","INGateway Receive DS2A InquirySubscriber Response Error"),
    DS2A_InquirySubscriber_Abort_response("333","INGateway Receive DS2A InquirySubscriber Response Abort"),
    DS2A_Timeout("333", "INGateway Receive DS2A InquirySubscriberRequest Timeout"),
    DS2_Unknown_Msisdn("002", "INGateway Receive DS2A InquirySubscriber Response Unknown Msisdn"),
    CCR_Timeout("328", "INGateway Receive CCR Timeout"),
    CCR_Error("319", "INGateway Receive CCR Error"),
    CCR_Reject("319", "INGateway Receive CCR Reject"),

    INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_REJECT("319", "INGateway Receive BOS AccountQuery Response Reject"),
    INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_TIMEOUT("328", "INGateway Receive BOS AccountQuery Response Timeout"),
    INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_ERROR("319", "INGateway Receive BOS AccountQuery Response Error"),
    INGATEWAY_RECEIVE_BOS_ACCOUNTQUERY_RESPONSE_ABORT("319", "INGateway Receive BOS AccountQuery Response Abort"),


    INGATEWAY_RECEIVE_AVATAR_ACCOUNTQUERY_RESPONSE_ABORT("319", "INGateway Receive AVATAR AccountQuery Response Abort"),
    INGATEWAY_RECEIVE_AVATAR_ACCOUNTQUERY_RESPONSE_ERROR("319", "INGateway Receive AVATAR AccountQuery Response Error"),
    INGATEWAY_RECEIVE_AVATAR_ACCOUNTQUERY_RESPONSE_REJECT("319", "INGateway Receive AVATAR AccountQuery Response Reject"),
    INGATEWAY_RECEIVE_AVATAR_ACCOUNTQUERY_RESPONSE_TIMEOUT("328", "INGateway Receive AVATAR AccountQuery Response Timeout"),
    
    INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_TIMEOUT("328", "INGateway Receive BOS AccountAdjustment Response Timeout"),
    INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_REJECT("319", "INGateway Receive BOS AccountAdjustment Response Reject"),
    INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_ABORT("319", "INGateway Receive BOS AccountAdjustment Response Abort"),
    INGATEWAY_RECEIVE_BOS_ACCOUNTADJ_RESPONSE_ERROR("319", "INGateway Receive BOS AccountAdjustment Response Error"),
    
    INGATEWAY_RECEIVE_BOS_ADJUSTASSET_RESPONSE_TIMEOUT("328", "INGateway Receive BOS AdjustAsset Response Timeout"),
    INGATEWAY_RECEIVE_BOS_ADJUSTASSET_RESPONSE_REJECT("319", "INGateway Receive BOS AdjustAsset Response Reject"),
    INGATEWAY_RECEIVE_BOS_ADJUSTASSET_RESPONSE_ABORT("319", "INGateway Receive BOS AdjustAsset Response Abort"),
    INGATEWAY_RECEIVE_BOS_ADJUSTASSET_RESPONSE_ERROR("319", "INGateway Receive BOS AdjustAsset Response Error"),


    CCR_Abort("319", "INGateway Receive CCR Abort"),
    BSSBbroker_Timeout("328", "INGateway Receive BSSBroker Timeout"),
    BSSBroker_Error("319", "INGateway Receive BSSBroker Error"),
    BSSBroker_Reject("319", "INGateway Receive BSSBroker Reject"),
    BSSBroker_Abort("319", "INGateway Receive BSSBroker Abort"),
    
    BSSBroker_$s_Timeout("328", "INGateway Receive BSSBroker %s Response Timeout"),
    BSSBroker_$s_Error("319", "INGateway Receive BSSBroker %s Response Error"),
    BSSBroker_$s_Reject("319", "INGateway Receive BSSBroker %s Response Reject"),
    BSSBroker_$s_Abort("319", "INGateway Receive BSSBroker %s Response Abort"),
    
    BSSBrokerDoManageSCPProfile_Timeout("328", "INGateway Receive BSSBroker DoManageSCPProfile Response Timeout"),
    BSSBrokerDoManageSCPProfile_Error("319", "INGateway Receive BSSBroker DoManageSCPProfile Response Error"),
    BSSBrokerDoManageSCPProfile_Reject("319", "INGateway Receive BSSBroker DoManageSCPProfile Response Reject"),
    BSSBrokerDoManageSCPProfile_Abort("319", "INGateway Receive BSSBroker DoManageSCPProfile Response Abort"),
    
    BSSBrokerDoQuerySCPProfile_Timeout("328", "INGateway Receive BSSBroker DoQuerySCPProfile Response Timeout"),
    BSSBrokerDoQuerySCPProfile_Error("319", "INGateway Receive BSSBroker DoQuerySCPProfile Response Error"),
    BSSBrokerDoQuerySCPProfile_Reject("319", "INGateway Receive BSSBroker DoQuerySCPProfile Response Reject"),
    BSSBrokerDoQuerySCPProfile_Abort("319", "INGateway Receive BSSBroker DoQuerySCPProfile Response Abort"),
    
//    BSSBroker_Reject("319", "INGateway Receive BSSBroker %s Response Reject"),
    
    // Chatl 21/09/2017
    VALIDITY_ERROR("319","SMOI Send HTTP ModiPPSValidity Response Error"),
    VALIDITY_SUCCESS("000","Modifying the validity term succeeded"),
    OPERATION_SUCCESS("000","Operation succeeded"),
    VALIDITY_BAD("319","INGateway Receive EQL Bad BSO ADJUST_CBS_VALIDITY Response"),
    BALANCE_BAD("319","INGateway Receive EQL Bad BSO ADJUST_CBS_BALANCE Response"),
    FREEUNIT_BAD("319","INGateway Receive EQL Bad BSO ADJUST_CBS_FREEUNIT Response"),
    BALANCEANDVALIDITY_BAD("319","INGateway Receive EQL Bad BSO ADJUST_CBS_BALANCE_VALIDITY Response"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR("319","INGateway Receive EQL BSO ADJUST_CBS_FREEUNIT Response Error"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ABORT("319","INGateway Receive EQL BSO ADJUST_CBS_FREEUNIT Response Abort"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_REJECT("319","INGateway Receive EQL BSO ADJUST_CBS_FREEUNIT Response Reject"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_TIMEOUT("328","INGateway Receive EQL BSO ADJUST_CBS_FREEUNIT Response Timeout"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR("319","INGateway Receive EQL BSO ADJUST_CBS_BALANCE Response Error"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ABORT("319","INGateway Receive EQL BSO ADJUST_CBS_BALANCE Response Abort"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_REJECT("319","INGateway Receive EQL BSO ADJUST_CBS_BALANCE Response Reject"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_TIMEOUT("328","INGateway Receive EQL BSO ADJUST_CBS_BALANCE Response Timeout"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_TIMEOUT("328", "INGateway Receive EQL BSO ADJUST_CBS_VALIDITY Response Timeout"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_REJECT("319", "INGateway Receive EQL BSO ADJUST_CBS_VALIDITY Response Reject"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_ABORT("319", "INGateway Receive EQL BSO ADJUST_CBS_VALIDITY Response Abort"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_ERROR("319", "INGateway Receive EQL BSO ADJUST_CBS_VALIDITY Response Error"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_TIMEOUT("328", "INGateway Receive EQL BSO ADJUST_CBS_BALANCE_VALIDITY Response Timeout"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_REJECT("319", "INGateway Receive EQL BSO ADJUST_CBS_BALANCE_VALIDITY Response Reject"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_ABORT("319", "INGateway Receive EQL BSO ADJUST_CBS_BALANCE_VALIDITY Response Abort"),
    INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_ERROR("319", "INGateway Receive EQL BSO ADJUST_CBS_BALANCE_VALIDITY Response Error"),
    INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_TIMEOUT("328", "INGateway Receive EQL BSO QUERY_CBS_SUB Response Timeout"),
    INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_REJECT("319", "INGateway Receive EQL BSO QUERY_CBS_SUB Response Reject"),
    INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_ABORT("319", "INGateway Receive EQL BSO QUERY_CBS_SUB Response Abort"),
    INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_ERROR("319", "INGateway Receive EQL BSO QUERY_CBS_SUB Response Error"),
    
    
    
    SGSCP_SMOI_Timeout("328", "INGateway Receive SGSCP SMOI Timeout"),
    SGSCP_SMOI_Error("319", "INGateway Receive SGSCP SMOI Error"),
    SGSCP_SMOI_Reject("319", "INGateway Receive SGSCP SMOI Reject"),
    SGSCP_SMOI_Abort("319", "INGateway Receive SGSCP SMOI Abort"),
    SGSCP_PPGW_Timeout("328", "INGateway Receive SGSCP PPGW Timeout"),
    SGSCP_PPGW_Error("319", "INGateway Receive SGSCP PPGW Error"),
    SGSCP_PPGW_Reject("319", "INGateway Receive SGSCP PPGW Reject"),
    SGSCP_PPGW_Abort("319", "INGateway Receive SGSCP PPGW Abort"),
    
    INGW_INS_Timeout("328", "INGateway Receive INGW INS Timeout"),
    INGW_INS_Error("319", "INGateway Receive INGW INS Error"),
    INGW_INS_Reject("319", "INGateway Receive INGW INS Reject"),
    INGW_INS_Abort("319", "INGateway Receive INGW INS Abort"),
    
    E01_QueryDestinationResposne_Error("354", "INGateway Receive E01 Query Destination Response Error"),
    E01_QueryDestinationResposne_NoSuchObject_Error("354", "INGateway Receive E01 Query Destination Response Error"),
    E01_QueryDestinationRequest_Error("354", "INGateway Receive E01 Query Destination Error"),
    E01_QueryDestinationRequest_Reject("354", "INGateway Receive E01 Query Destination Reject"),
    E01_QueryDestinationRequest_Abort("354", "INGateway Receive E01 Query Destination Abort"),
    E01_QueryDestinationRequest_Timeout("354", "INGateway Receive E01 Query Destination Response Timeout"),
    
    INVALID_PARAM("222", "INVALID_PARAM"),
    INCOMPLETE_DATA("322", "INGateway Send HTTP Response Incomplete Data"),
//    INCOMPLETE_DATA("322", "Incomplete Data"),
    INCOMPLETE_DATA_RESPONSE("322", "INGateway Send HTTP Incomplete Data Response"),
    UNKNOWN_EVENT("999", "UNKNOWN_EVENT"), 
    DS2A_Timeout_response("333", "INGateway Receive DS2A InquirySubscriber Response Timeout"),

    //MD
    ACTIVATING_SUCCESS("000", "Activating subscriber successfully."),
    INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Error("319", "INGateway Receive MD BSO CBS_SUBSTATUS  Response Error"),
    INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Reject("319", "INGateway Receive MD BSO CBS_SUBSTATUS  Response Reject"),
    INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Abort("319", "INGateway Receive MD BSO CBS_SUBSTATUS  Response Abort"),
    INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Timeout("319", "INGateway Receive MD BSO CBS_SUBSTATUS  Response Timeout"),
    INGateway_Receive_MD_Bad_BSO_CBS_SUBSTATUS_Response("319", "INGateway Receive MD Bad BSO CBS_SUBSTATUS Response"),
	INGateway_Receive_MD_ALREADY_ACTIVE("1030", "The subscriber has been already actived."),;
	
    EResponseCode(String _code, String _description) {
        this.code = _code;
        this.description = _description;
    }
    private String description;
    private String code;

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.description;
    }
}