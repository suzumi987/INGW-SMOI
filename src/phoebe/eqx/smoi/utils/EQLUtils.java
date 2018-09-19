package phoebe.eqx.smoi.utils;

import ec02.af.abstracts.AbstractAF;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.enums.AdjustType;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.enums.StatAlarm;


public class EQLUtils {

	public static void writeStatEQL(SmoiInstance smoiIns,AbstractAF af ){
	      if(smoiIns.getAdjustType().equals(AdjustType.VALIDITY)){
	   		  if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_VALIDITY_Request, smoiIns);
	   			  SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns);  
	   		  }else{
	   			  SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_VALIDITY_Request, smoiIns);
	   		  }
	   	  }else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE)){
	   		if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_BALANCE_Request, smoiIns);
	   			 if(!(smoiIns.getHttpPostValue("prmmoney").trim().equals(""))){
	   				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns);  
		   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns); 
	   			 }
	   		  }else{
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_BALANCE_Request, smoiIns);  
	   		  }
	   		  	    
	   	  }else if(smoiIns.getAdjustType().equals(AdjustType.FREEUNIT)){
	   		  if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_FREEUNIT_Request, smoiIns);
   			    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns);  
   			    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns);
	   		  }else{
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_FREEUNIT_Request, smoiIns);  
	   		  }
	   	  }else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE_AND_VALIDITY)){
	   		  	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Request, smoiIns);  
	   	  }else if(smoiIns.getAdjustType().equals(AdjustType.PRMMONEY_AND_FREEUNIT)){
	   		if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_BALANCE_Request, smoiIns);  
		   		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_FREEUNIT_Request, smoiIns);
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns);  
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns);
	   		  }else{
	   			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_BALANCE_Request, smoiIns);  
		   		SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_ADJUST_CBS_FREEUNIT_Request, smoiIns);  
	   		  }
	   		  
	   	  }else if(smoiIns.getAdjustType().equals(AdjustType.QUERY)){
	   		  	SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_EQL_BSO_QUERY_CBS_SUB_Request, smoiIns);
	   	  }
	      
	}
	
}
