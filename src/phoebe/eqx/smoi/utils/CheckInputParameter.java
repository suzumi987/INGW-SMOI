package phoebe.eqx.smoi.utils;
 
import phoebe.eqx.smoi.bean.instance.SmoiInstance;

import phoebe.eqx.smoi.enums.ECommand;

public class CheckInputParameter {

    private boolean isInvalidReq = false;

    public CheckInputParameter(SmoiInstance smoiIns) { 
        //mandatory parameter for all command
        String inputMsg = smoiIns.getRequestInfo().getRequestMessage();

        String ms = SMOIUtils.getHttpPostValue(inputMsg, "ms");
        String page = SMOIUtils.getHttpPostValue(inputMsg, "page");
        String sgw = SMOIUtils.getHttpPostValue(inputMsg, "sgw");
        String ssid = SMOIUtils.getHttpPostValue(inputMsg, "ssid");

        if ("".equals(ms) || "".equals(page) || "".equals(sgw) || "".equals(ssid)
                || ms == null || page == null || sgw == null || ssid == null) {
            this.isInvalidReq = true;
        } else if (Msisdn.isPattern(ms)==null) {
            this.isInvalidReq = true;
        } else {
        	if (page.equals(ECommand.modiPPSCallNotifyFlag.getCommand())) {
              String flag = smoiIns.getHttpPostValue("flag");
              if (flag.equals("") || !(flag.equals("1") || flag.equals("0") )) {
                  this.isInvalidReq = true;
              } else {
            	  smoiIns.setFlag(flag);
              }
        	} else if (page.equals(ECommand.dispPPSInfo.getCommand())) {
//                if (sgw == null || ms == null || ssid == null) {
//                    this.isInvalidReq = true;
//                }
//                String dat = smoiIns.getHttpPostValue("dat");
//                if (dat.equals("")) {
//                    this.isInvalidReq = true;
//                }
            } else if (page.equals(ECommand.dispPPSPkgrew.getCommand())) {
//                String dat = smoiIns.getHttpPostValue("dat");
//                if (dat.equals("")) {
//                    this.isInvalidReq = true;
//                }
            } else if (page.equals(ECommand.modiPPSValidity.getCommand())) {
                String increment = smoiIns.getHttpPostValue("increment");
                if ("".equals(increment)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.modiPPSMultiAttr.getCommand())) { 
                
            	 String flag = smoiIns.getHttpPostValue("flag");
//            	 if(flag == null || flag.equals("")){
//            		 smoiIns.setFlag("0"); // set default value
//            	 }else 
            	 if(flag.equals("0") || flag.equals("1")){
            		 smoiIns.setFlag(flag); // set flag from client request
            	 }else{
            		 smoiIns.setFlag("0"); // set default value
            	 }
            
             /* else if (page.equals(ECommand.addScrScreenNo.getCommand())) {
                String dat = smoiIns.getHttpPostValue("dat");
                //String screentype = smoiIns.getHttpPostValue("screentype");
                if ("".equals(dat)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.deleScrScreenNo.getCommand())) {
                String dat = smoiIns.getHttpPostValue("dat");
                //String screentype = smoiIns.getHttpPostValue("screentype");
                if ("".equals(dat)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.modiScrScreenType.getCommand())) {
                String dat = smoiIns.getHttpPostValue("dat");
                //String screentype = smoiIns.getHttpPostValue("screentype");
                if ("".equals(dat)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.modiScrWhiteList.getCommand())) {
                String dat = smoiIns.getHttpPostValue("dat");
                //String screentype = smoiIns.getHttpPostValue("screentype");
                if ("".equals(dat)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.dispScrScreenNo.getCommand())) {
                String dat = smoiIns.getHttpPostValue("dat");
                if ("".equals(dat)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.actPPSRBT.getCommand())) {
                String dat = smoiIns.getHttpPostValue("dat");
                Log.d("dat=" + dat);
                if ("".equals(dat)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.delePPSPkgres.getCommand())) {
                String pkgid = smoiIns.getHttpPostValue("pkgid");
                if ("".equals(pkgid)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.delePPSPackageID.getCommand())) {
                String packageid = smoiIns.getHttpPostValue("packageid");
                //String prodseqid = smoiIns.getHttpPostValue("prodseqid");
                if ("".equals(packageid)) {
                    this.isInvalidReq = true;
                }*/
            } else if (page.equals(ECommand.purchasePPSPackage.getCommand())) {
                String packageid = smoiIns.getHttpPostValue("packageid");
                if ("".equals(packageid)) {
                    this.isInvalidReq = true;
                }
           /* } else if (page.equals(ECommand.setPPSReward.getCommand())) {
//                String pkgid = smoiIns.getHttpPostValue("pkgid");
//                if (pkgid.equals("")) {
//                    this.isInvalidReq = true;
//                }
            } else if (page.equals(ECommand.dispPPSFntelNo.getCommand())) {
                String dat = smoiIns.getHttpPostValue("dat");
                Log.d("dat=" + dat);
                if ("".equals(dat)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.modiPPSLanguage.getCommand())) {
                String lang = smoiIns.getHttpPostValue("lang");
                if ("".equals(lang)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.modiPPSSMSLanguage.getCommand())) {
                String lang = smoiIns.getHttpPostValue("lang");
                if ("".equals(lang)) {
                    this.isInvalidReq = true;
                }*/
            } else if (page.equals(ECommand.modiPPSCreditLimit.getCommand())) {
                String increment = smoiIns.getHttpPostValue("increment");
                if ("".equals(increment)) {
                    this.isInvalidReq = true;
                }
            } else if (page.equals(ECommand.chgtrigChrgAcnt.getCommand())) {
                String dat = smoiIns.getHttpPostValue("dat");
                String pin = smoiIns.getHttpPostValue("pin");
                String fee = smoiIns.getHttpPostValue("fee");
                
                if(dat!=null){
                	if(dat.equals("3")){
                		//dat ==3 pin ==null
                		if(pin==null || pin.trim().equals("")){
                			this.isInvalidReq = true;
                		}
                	}else{
                		//dat !=3  free ==null (dont care pin)
                		if(fee==null || fee.trim().equals("")){
                			this.isInvalidReq = true;
                		}
                	}            	
                }else{ 
                	this.isInvalidReq = true;
                }
             } else if (page.equals(ECommand.activatePPSSingSub.getCommand())) {
//                String lang = smoiIns.getHttpPostValue("lang");
//                if ("".equals(lang)) {
//                    this.isInvalidReq = true;
//                }
            }
        	
             else if (page.equals(ECommand.setPPSReward.getCommand())) { 
                 
            	 String flag = smoiIns.getHttpPostValue("flag");
//            	 if(flag == null || flag.equals("")){
//            		 smoiIns.setFlag("0"); // set default value
//            	 }else 
            	 if(flag.equals("0") || flag.equals("1")){
            		 smoiIns.setFlag(flag); // set flag from client request
            	 }else{
            		 smoiIns.setFlag("0"); // set default value
            	 }
             }
        	
        }
    }

    public boolean isInvalidReq() {
        return isInvalidReq;
    }
}
