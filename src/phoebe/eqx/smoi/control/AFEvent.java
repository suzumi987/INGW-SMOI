package phoebe.eqx.smoi.control;

public interface AFEvent {

    //IncomingKnownCommand
    public String IncomingDispPPSInfo = "IncomingDispPPSInfo";
    public String IncomingDispPPSPkgrew = "IncomingDispPPSPkgrew";
    public String IncomingModiPPSValidity = "IncomingModiPPSValidity";
    public String IncomingModiPPSMultiAttr = "IncomingModiPPSMultiAttr";
    public String IncomingAddScrScreenNo = "IncomingAddScrScreenNo";
    public String IncomingDeleScrScreenNo = "IncomingDeleScrScreenNo";
    public String IncomingModiScrScreenType = "IncomingModiScrScreenType";
    public String IncomingModiScrWhiteList = "IncomingModiScrWhiteList";
    public String IncomingDispScrScreenNo = "IncomingDispScrScreenNo";
    public String IncomingActPPSRBT = "IncomingActPPSRBT";
    public String IncomingDelePPSPkgres = "IncomingDelePPSPkgres";
    public String IncomingDelePPSPackageID = "IncomingDelePPSPackageID";
    public String IncomingPurchasePPSPackage = "IncomingPurchasePPSPackage";
    public String IncomingSetPPSReward = "IncomingSetPPSReward";
    public String IncomingDispPPSFntelNo = "IncomingDispPPSFntelNo";
    public String IncomingModiPPSLanguage = "IncomingModiPPSLanguage";
    public String IncomingModiPPSSMSLanguage = "IncomingModiPPSSMSLanguage";
    public String IncomingModiPPSCreditLimit = "IncomingModiPPSCreditLimit";
    public String IncomingChgtrigChrgAcnt = "IncomingChgtrigChrgAcnt";
    public String IncomingActivatePPSSingSub = "IncomingActivatePPSSingSub";
    public String IncomingModiPPSCallNotifyFlag = "IncomingModiPPSCallNotifyFlag";
    //IncomingUknownCommand
    public String IncomingNoCommand = "IncomingNoCommand";        //Sub set of UknownCommand
    public String IncomingOtherCommand = "IncomingOtherCommand"; //Sub set of UknownCommand
    //response event
    public String IncomingInquirySubscriberRes = "IncomingInquirySubscriberRes";
    public String IncomingHttpRes = "IncomingHttpRes";
    //public String IncomingBSSbrokerRes = "IncomingBSSbrokerRes";
    public String IncomingCCA = "IncomingCCA";
    public String IncomingEquinox_Timeout = "IncomingEquinox_Timeout";
    public String IncomingEquinox_Error = "IncomingEquinox_Error";
    public String IncomingEquinox_Reject = "IncomingEquinox_Reject";
    public String IncomingEquinox_Abourt = "IncomingEquinox_Abourt";
    public String IncomingUnknowMessge = "IncomingUnknowMessge";
    
//    public String IncomingE01BosDestination = "IncomingE01BosDestination";
    public String IncomingE01Destination = "IncomingE01Destination";

//    public String IncomingInquirySubscriberRes_Error = "IncomingInquirySubscriberRes_Error";
//    public String IncomingBSSbrokerReq_Reject = "IncomingBSSbrokerReq_Reject";
//    public String IncomingBSSbrokerReq_Abort = "IncomingBSSbrokerReq_Abort";
//    public String IncomingBSSbrokerReq_Error = "IncomingBSSbrokerReq_Error";
    
//    public String IncomingSGSCPReq_Reject = "IncomingSGSCPReq_Reject";
//    public String IncomingSGSCPReq_Abort = "IncomingSGSCPReq_Abort";
//    public String IncomingSGSCPReq_Error = "IncomingSGSCPReq_Error";
    
    
//    public String IncomingInquirySubscriberReq_Error = "IncomingInquirySubscriberReq_Error";
//    public String IncomingInquirySubscriberReq_Reject = "IncomingInquirySubscriberReq_Reject";
//    public String IncomingInquirySubscriberReq_Abort = "IncomingInquirySubscriberReq_Abort";
    
//    public String IncomingCCR_Reject = "IncomingCCR_Reject";
//    public String IncomingCCR_Error = "IncomingCCR_Error";
//    public String IncomingCCR_Abort = "IncomingCCR_Abort";
    
}
