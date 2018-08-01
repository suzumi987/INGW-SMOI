package phoebe.eqx.smoi.enums;

public enum ECommand {
	/**
	 * //@see Phase 1.
	 */
	//BOS DCC
    dispPPSInfo("dispPPSInfo","DispPPSInfo"),
    dispPPSPkgrew("dispPPSPkgrew","DispPPSPkgrew"),
    modiPPSValidity("modiPPSValidity","ModiPPSValidity"),
    modiPPSMultiAttr("modiPPSMultiAttr","ModiPPSMultiAttr"),     
    
    //BOS BSS Broker
    activatePPSSingSub("activatePPSSingSub","ActivatePPSSingSub"),
    purchasePPSPackage("purchasePPSPackage","PurchasePPSPackage"),
    modiPPSCreditLimit("modiPPSCreditLimit","ModiPPSCreditLimit"),
    //phase 2 but different 
    chgtrigChrgAcnt("chgtrigChrgAcnt","ChgtrigChrgAcnt"),
    dispScrScreenNo("dispScrScreenNo","DispScrScreenNo"),
    addScrScreenNo("addScrScreenNo","AddScrScreenNo"),
    deleScrScreenNo("deleScrScreenNo","DeleScrScreenNo"),
    modiScrWhiteList("modiScrWhiteList","ModiScrWhiteList"),
    setPPSReward("setPPSReward","SetPPSReward"),
    //INS
    modiScrScreenType("modiScrScreenType","ModiScrScreenType"),
    modiPPSLanguage("modiPPSLanguage","ModiPPSLanguage"),
    modiPPSSMSLanguage("modiPPSSMSLanguage","ModiPPSSMSLanguage"),
    modiPPSCallNotifyFlag("modiPPSCallNotifyFlag","ModiPPSCallNotifyFlag"),
    
    // Bssbroker
    doAdjustBalance("do_AdjustBalance","DoAdjustBalance"),
    doFirstActivation("do_FirstActivationCRM","DoFirstActivationCRM"),
    doChangeService("do_changeService","DoChangeService"),
    doSetNegativeBalance("do_SetNegativeBalance","DoSetNegativeBalance"),
    doQueryCallScreen("do_QueryCallScreen","DoQueryCallScreen"),
    doCallScreen("do_CallScreenNo","DoCallScreenNo"),
    doManageSCPProfile("do_ManageSCPProfile","DoManageSCPProfile"),
    doQuerySCPProfile("do_QuerySCPProfile","DoQuerySCPProfile"),
    
    ;
	   /**
     * //@see Phase 2.
	    modiScrScreenType("modiScrScreenType"),
	    modiScrWhiteList("modiScrWhiteList"),
	    actPPSRBT("actPPSRBT"),
	    delePPSPkgres("delePPSPkgres"),
	    delePPSPackageID("delePPSPackageID"), 
	    setPPSReward("setPPSReward"),
	    dispPPSFntelNo("dispPPSFntelNo"),
	    modiPPSLanguage("modiPPSLanguage"),
	    modiPPSSMSLanguage("modiPPSSMSLanguage"),  
    ; 
    */ 
    String command = "";
    String stat = "";
	ECommand(String command, String stat){
		this.command = command;
		this.stat = stat;
	}
    ECommand(String command) {
        this.command = command;
    }
     
    public String getCommand() {
        return this.command;
    } 
    
    public String getStat(){
		return this.stat;
	}
	
	public static String getStatPrint(String text){
		for(ECommand a : ECommand.values()){
			if(a.getCommand().equalsIgnoreCase(text)){
				return a.getStat();
			}
		}
		return text;
		
	}
}
