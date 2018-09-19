package phoebe.eqx.smoi.state;


import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;

import com.google.gson.Gson;

import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;
import ec02.utils.AppLog;
import ec02.utils.Log;
import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.EQLRequestInstance;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.AdjustType;
import phoebe.eqx.smoi.enums.DateFunction;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.enums.PartlyfailSmessage;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.utils.EQLUtils;
import phoebe.eqx.smoi.utils.MappingMessage;
import phoebe.eqx.smoi.utils.SmoiStatAlarm;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.BSONOBalanceAndFreeResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.EqlBsoAdjustCbsResponse;
import phoebe.eqx.smoi.wsdl.InfoSyncDefines.EqlBsoAdjustCbsValidityResponse;
import smoi.enums.EMsgTagType;
//import sun.security.krb5.Config;

public class StateWaitEQL implements IAFState {

	@Override
	public String doAction(AbstractAF af, Object instance, ArrayList<EquinoxRawData> rawDatas) {
		String nextState = AFState.IDLE;
		MyAppData myAppData = (MyAppData) instance;
		SmoiInstance smoiIns = myAppData.getSmoiIns();
		String page = smoiIns.getPage();
		for (EquinoxRawData r : rawDatas) {
			String inputMsg = r.getRawDataAttribute("val");
			String outputMsg = "";
			String invokeId = r.getInvoke();
			String log_RESULTCODE = "";
			String log_DESC = "";

			HashMap<String, String> hmMessage = null;
			if (r.getRawEventType().equals(AFEvent.IncomingHttpRes)) {
				if (page.equals(ECommand.modiPPSValidity.getCommand())) {
					hmMessage = genMsgHttpResponseModiPPSValidity(af, smoiIns,inputMsg, hmMessage);
					outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
					log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
					log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
					SendMessage.Client(outputMsg, af, myAppData);
					nextState = AFState.IDLE;
				} 
				else if (page.equals(ECommand.modiPPSMultiAttr.getCommand())) {
					hmMessage = genMsgHttpResponseModiPPSMultiAttr(af, smoiIns,inputMsg, hmMessage);
					outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
					log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
					log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
					SendMessage.Client(outputMsg, af, myAppData);
					nextState = AFState.IDLE;
				}
				else if (page.equals(ECommand.setPPSReward.getCommand())) {
					hmMessage = genMsgHttpResponseSetPPSReward(af, smoiIns,inputMsg, hmMessage);
					outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
					log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
					log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
					SendMessage.Client(outputMsg, af, myAppData);
					nextState = AFState.IDLE;
				}
				else if (page.equals(ECommand.modiPPSCreditLimit.getCommand())) {
					hmMessage = genMsgHttpResponseModiPPSCreditLimit(af, smoiIns,inputMsg, hmMessage);
					if(hmMessage != null){
						outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
						log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
						log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
						SendMessage.Client(outputMsg, af, myAppData);
					}else{
						// Send request to MD
						MappingMessage messageFn = new MappingMessage(af);
						messageFn.setEState(AFState.W_MD);
						outputMsg = messageFn.CreateMessage(myAppData);
						SendMessage.MD(outputMsg, af, myAppData, Conf.resource_Name_Active_MD);
						nextState = AFState.W_MD;	
					}
				}
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {
				
					if(smoiIns.getAdjustType().equals(AdjustType.VALIDITY)){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_VALIDITY_Response_Error, smoiIns);
						if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
									StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Error, smoiIns);
						}
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_ERROR);
					}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE) 
							|| smoiIns.getAdjustType().equals(AdjustType.PRMMONEY_AND_FREEUNIT)){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Error, smoiIns);	
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
					}else if(smoiIns.getAdjustType().equals(AdjustType.FREEUNIT)){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Error, smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Error, smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Error, smoiIns);
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR);
					}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE_AND_VALIDITY)){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Response_Error, smoiIns);	
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_ERROR);
					}else if(smoiIns.getAdjustType().equals(AdjustType.QUERY)){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Error, smoiIns);
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_ERROR);
					}
				
				outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
				log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
				log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
				SendMessage.Client(outputMsg, af, myAppData);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
				nextState = AFState.IDLE;

			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {
				if(smoiIns.getAdjustType().equals(AdjustType.VALIDITY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_VALIDITY_Response_Reject, smoiIns);	
					if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Reject, smoiIns);
					}
				}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Reject, smoiIns);
				}else if(smoiIns.getAdjustType().equals(AdjustType.PRMMONEY_AND_FREEUNIT)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Reject, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Reject, smoiIns);
				}else if(smoiIns.getAdjustType().equals(AdjustType.FREEUNIT)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Reject, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Reject, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Reject, smoiIns);
				}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE_AND_VALIDITY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Response_Reject, smoiIns);	
				}else if(smoiIns.getAdjustType().equals(AdjustType.QUERY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Reject, smoiIns);
				}
				
				int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resourceNameEQL_Standby_MaxRetry);
				if (maxRetry > 0) {
					// Standby_MaxRetry
					ec02.utils.Log.d("Retry: " + Conf.resourceNameEQL_Standby_MaxRetry + " maxRetry: " + maxRetry);
					maxRetry -= 1;
					smoiIns.setResourceNameStandbyMaxRetry(Conf.resourceNameEQL_Standby_MaxRetry, maxRetry);
					smoiIns.setRetry(true);

					MappingMessage messageFn = new MappingMessage(af);
					outputMsg = messageFn.CreateMessage(myAppData);
					String state = messageFn.getEState();
					inputMsg = "";

					if (state.equals(AFState.W_EQL)) {
						SendMessage.eql(outputMsg, af, myAppData, Conf.resourceNameEQL_Standby);
					}
					nextState = state;
					EQLUtils.writeStatEQL(smoiIns,af);
					nextState = AFState.W_EQL;

				} else {
					if(smoiIns.getAdjustType().equals(AdjustType.VALIDITY)){
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_REJECT);	
					}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE)
							|| smoiIns.getAdjustType().equals(AdjustType.PRMMONEY_AND_FREEUNIT)){
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_REJECT);	
					}else if(smoiIns.getAdjustType().equals(AdjustType.FREEUNIT)){
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_REJECT);	
					}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE_AND_VALIDITY)){
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_REJECT);	
					}else if(smoiIns.getAdjustType().equals(AdjustType.QUERY)){
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_REJECT);
					}
					
					outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
					log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
					log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
					SendMessage.Client(outputMsg, af, myAppData);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					nextState = AFState.IDLE;
				}

			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {
				if(smoiIns.getAdjustType().equals(AdjustType.VALIDITY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_VALIDITY_Response_Abort, smoiIns);	
					if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Abort, smoiIns);
					}
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_ABORT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Abort, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ABORT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.PRMMONEY_AND_FREEUNIT)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Abort, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ABORT);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Abort, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ABORT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.FREEUNIT)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Abort, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Abort, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Abort, smoiIns);
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ABORT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE_AND_VALIDITY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Response_Abort, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_ABORT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.QUERY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Abort, smoiIns);
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_ABORT);
				}

				outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
				log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
				log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
				SendMessage.Client(outputMsg, af, myAppData);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
				nextState = AFState.IDLE;
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {
				if(smoiIns.getAdjustType().equals(AdjustType.VALIDITY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_VALIDITY_Response_Timeout, smoiIns);	
					if(smoiIns.getPage().equals(ECommand.modiPPSMultiAttr.getCommand())){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Timeout, smoiIns);
					}
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_TIMEOUT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Timeout, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_TIMEOUT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.PRMMONEY_AND_FREEUNIT)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Timeout, smoiIns);	
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Timeout, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_TIMEOUT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.FREEUNIT)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Timeout, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Timeout, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Timeout, smoiIns);
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_TIMEOUT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE_AND_VALIDITY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Response_Timeout, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_TIMEOUT);
				}else if(smoiIns.getAdjustType().equals(AdjustType.QUERY)){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Timeout, smoiIns);
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_TIMEOUT);
				}
			
				outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
				log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
				log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
				SendMessage.Client(outputMsg, af, myAppData);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
				nextState = AFState.IDLE;

			} else {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(), StatAlarm.INGateway_Receive_Unknown_Event,
						smoiIns);
				SendMessage.EqxClearInstance(af, myAppData);
				nextState = AFState.IDLE;
			}

			myAppData.setInput_Msg(inputMsg);
			myAppData.setInput_InvokeId(invokeId);
			myAppData.setInput_resultcode(log_RESULTCODE);
			myAppData.setInput_desc(log_DESC);
			myAppData.setOutput_Msg(outputMsg);

		}
		return nextState;
	}

	

	private HashMap<String, String> genMsgHttpResponseModiPPSMultiAttr(AbstractAF af, SmoiInstance smoiIns,
			String inputMsg, HashMap<String, String> hmMessage) {
		String adjType = smoiIns.getAdjustType();
		String page = smoiIns.getPage();
		if(adjType.equals(AdjustType.VALIDITY)){
//					if(validity != null && !validity.equals("")){
			Gson gson = new Gson();
			EqlBsoAdjustCbsValidityResponse eqlRes = gson.fromJson(inputMsg,
					EqlBsoAdjustCbsValidityResponse.class);

			if (isMissingOmModiPPSMultiAttrValidityRes(eqlRes,page)) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
						StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_VALIDITY_Response, smoiIns);
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
						StatAlarm.INGateway_Receive_EQL_BAD_BSO_QUERY_CBS_SUB_Response, smoiIns);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
				hmMessage = mapEquinoxReturnCmd(EResponseCode.VALIDITY_BAD);

			} else {
				if (eqlRes.getRespstatus().toUpperCase().equals("FAIL") 
						|| eqlRes.getRespstatus().toUpperCase().equals("PROCESS")
						|| eqlRes.getRespstatus().toUpperCase().equals("CANCEL")
						|| eqlRes.getRespstatus().toUpperCase().equals("REJECT")
						|| (eqlRes.getRespstatus().toUpperCase().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {

					writeStatsValidity(eqlRes,af,smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_ERROR);
					
				} else {
					
					writeStatsValidity(eqlRes,af,smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
					hmMessage = mapModiPPSMultiAttrValidityCmd(eqlRes, af, smoiIns);
					
				}
			}
		}else if(adjType.equals(AdjustType.BALANCE)){
			Gson gson = new Gson();
			String prmmoney = smoiIns.getHttpPostValue("prmmoney");
			
			if(prmmoney!=null && !prmmoney.trim().equals("")){
				EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
						EqlBsoAdjustCbsResponse.class);
				if (isMissingOmModiPPSMultiAttrBalanceRes(eqlRes,page)) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.BALANCE_BAD);

				}else{
					
					if (eqlRes.getRespstatus().toUpperCase().equals("FAIL")
							|| eqlRes.getRespstatus().toUpperCase().equals("PROCESS")
							|| eqlRes.getRespstatus().toUpperCase().equals("CANCEL")
							|| eqlRes.getRespstatus().toUpperCase().equals("REJECT")
							|| (eqlRes.getRespstatus().toUpperCase().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {
					
//						writeStatsReceive(eqlRes,af,smoiIns);
						writeStatsPrmmoneyAndFree(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
//						hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
						hmMessage = mapHTTPError(smoiIns,eqlRes,hmMessage);
						
					}else{
						writeStatsPrmmoneyAndFree(eqlRes,af,smoiIns);
//						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
//								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
						hmMessage = mapModiPPSMultiAttrFreeCmd(eqlRes, af, smoiIns);
					}
					
				}
			}else{
				EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
						EqlBsoAdjustCbsResponse.class);
				if (isMissingOmModiPPSMultiAttrBalanceRes(eqlRes,page)) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.BALANCE_BAD);

				}else{
					if (eqlRes.getRespstatus().toUpperCase().equals("FAIL")
							|| eqlRes.getRespstatus().toUpperCase().equals("PROCESS")
							|| eqlRes.getRespstatus().toUpperCase().equals("CANCEL")
							|| eqlRes.getRespstatus().toUpperCase().equals("REJECT")
							|| (eqlRes.getRespstatus().toUpperCase().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {
					
						writeStatsReceive(eqlRes,af,smoiIns);
						
//									SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
//											StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Error, smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
						hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
						
					} else {
						
						writeStatsReceive(eqlRes,af,smoiIns);
//									SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
//											StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
						hmMessage = mapModiPPSMultiAttrBalanceCmd(eqlRes, af, smoiIns);
						
					}
				}
			}
			
		}else if(adjType.equals(AdjustType.FREEUNIT)){
			
			Gson gson = new Gson();
			
			EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
						EqlBsoAdjustCbsResponse.class);
				if (isMissingOmModiPPSMultiAttrBalAndValRes(eqlRes,page)) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_FREEUNIT_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_QUERY_CBS_SUB_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_QUERY_CBS_SUB_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.FREEUNIT_BAD);

				}else{
					
					if (eqlRes.getRespstatus().toUpperCase().equals("FAIL")
							|| eqlRes.getRespstatus().toUpperCase().equals("PROCESS")
							|| eqlRes.getRespstatus().toUpperCase().equals("CANCEL")
							|| eqlRes.getRespstatus().toUpperCase().equals("REJECT")
							|| (eqlRes.getRespstatus().toUpperCase().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {
					
//						writeStatsReceive(eqlRes,af,smoiIns);
						writeStatsPrmmoneyAndFree(eqlRes,af,smoiIns);
//									SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
//											StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Error, smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
						hmMessage = mapHTTPError(smoiIns,eqlRes,hmMessage);
//						
						
					}else{
//						writeStatsReceive(eqlRes,af,smoiIns);
						writeStatsPrmmoneyAndFree(eqlRes,af,smoiIns);
//									SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
//											StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response, smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
						hmMessage = mapModiPPSMultiAttrFreeCmd(eqlRes, af, smoiIns);
					}
				}
			
		}else if(adjType.equals(AdjustType.BALANCE_AND_VALIDITY)){
			Gson gson = new Gson();
			
				EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
						EqlBsoAdjustCbsResponse.class);
				if (isMissingOmModiPPSMultiAttrBalAndValRes(eqlRes,page)) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.BALANCEANDVALIDITY_BAD);

				}else{
					if (eqlRes.getRespstatus().toUpperCase().equals("FAIL")
							|| eqlRes.getRespstatus().toUpperCase().equals("PROCESS")
							|| eqlRes.getRespstatus().toUpperCase().equals("CANCEL")
							|| eqlRes.getRespstatus().toUpperCase().equals("REJECT")
							|| (eqlRes.getRespstatus().toUpperCase().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {
						
						writeStatsReceive(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
						hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_ERROR);
						
					} else {
						
						writeStatsReceive(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
						hmMessage = mapModiPPSMultiAttrBalanceAndValidityCmd(eqlRes, af, smoiIns);
						
					}
				}

			
		}else if(adjType.equals(AdjustType.PRMMONEY_AND_FREEUNIT)){
			Gson gson = new Gson();
			EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
					EqlBsoAdjustCbsResponse.class);
			if (isMissingOmModiPPSMultiAttrBalAndValRes(eqlRes,page)) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
						StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
				hmMessage = mapEquinoxReturnCmd(EResponseCode.BALANCE_BAD);

			}else{
				
				if (eqlRes.getRespstatus().toUpperCase().equals("FAIL") 
						|| eqlRes.getRespstatus().toUpperCase().equals("PROCESS")
						|| eqlRes.getRespstatus().toUpperCase().equals("CANCEL")
						|| eqlRes.getRespstatus().toUpperCase().equals("REJECT")
						|| (eqlRes.getRespstatus().toUpperCase().equals("PARTLYFAIL") 
								&& checkPartlyfail(eqlRes.getSmessage())) ) {
				
					writeStatsPrmmoneyAndFree(eqlRes,af,smoiIns); 
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapHTTPError(smoiIns,eqlRes,hmMessage);
					
					
				}else{
					
					writeStatsPrmmoneyAndFree(eqlRes,af,smoiIns); 
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
					hmMessage = mapModiPPSMultiAttrFreeCmd(eqlRes, af, smoiIns);
				}
				
			}
		}
		return hmMessage;
	}

	private HashMap<String, String> genMsgHttpResponseModiPPSValidity(AbstractAF af, SmoiInstance smoiIns,
			String inputMsg,HashMap<String, String> hmMessage) {
		Gson gson = new Gson();
		String page = smoiIns.getPage();
		EqlBsoAdjustCbsValidityResponse eqlRes = gson.fromJson(inputMsg,
				EqlBsoAdjustCbsValidityResponse.class);
		if (isMissingOmModiPPSValidityRes(eqlRes,page)) {
		   
			    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_VALIDITY_Response, smoiIns);
			    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
			    hmMessage = mapEquinoxReturnCmd(
			    		EResponseCode.VALIDITY_BAD);///
		   
		}else{
			if (eqlRes.getRespstatus().toUpperCase().equals("SUCCESS")) {

				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
						StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_VALIDITY_Response, smoiIns);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
						StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
				hmMessage = mapEquinoxReturnCmd(EResponseCode.VALIDITY_SUCCESS);
			
		   } else {
			SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
					StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_VALIDITY_Response_Error, smoiIns);
			    hmMessage = mapEquinoxReturnCmd(
			    		EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_ERROR);///
		   }
		}
		return hmMessage;
	}
	
	
	private HashMap<String, String> genMsgHttpResponseSetPPSReward(AbstractAF af, SmoiInstance smoiIns, String inputMsg,
			HashMap<String, String> hmMessage) {
		
		String adjType = smoiIns.getAdjustType();
		String page = smoiIns.getPage();
		if(adjType.equals(AdjustType.VALIDITY)){
//					if(validity != null && !validity.equals("")){

			Gson gson = new Gson();
			
			EqlBsoAdjustCbsValidityResponse eqlRes = null;
			try {
				eqlRes = gson.fromJson(inputMsg,EqlBsoAdjustCbsValidityResponse.class);	
			} catch (Exception e) {
				AppLog.i("JsonSyntaxException = " + e);
			}
		
//			EqlBsoAdjustCbsValidityResponse eqlRes = gson.fromJson(inputMsg,
//					EqlBsoAdjustCbsValidityResponse.class);	

			if (isMissingOmSetPPSRewardValidityRes(eqlRes,page)) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
						StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_VALIDITY_Response, smoiIns);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
				hmMessage = mapEquinoxReturnCmd(EResponseCode.VALIDITY_BAD);

			} else {
				if (eqlRes.getRespstatus().toUpperCase().trim().equals("FAIL")
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("PROCESS")
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("CANCEL")
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("REJECT")
						|| (eqlRes.getRespstatus().toUpperCase().trim().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {

					writeStatsValidity(eqlRes,af,smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_VALIDITY_RESPONSE_ERROR);
					
				} else {
					
					writeStatsValidity(eqlRes,af,smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
					hmMessage = mapSetPPSRewardValidityCmd(eqlRes, af, smoiIns);
					
				}
				
			}
		} else if(adjType.equals(AdjustType.BALANCE)){
			Gson gson = new Gson();
			String prmMoney = smoiIns.getHttpPostValue("prmMoney");
			
			if(prmMoney!=null && !prmMoney.trim().equals("")){
				EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
						EqlBsoAdjustCbsResponse.class);
				if (isMissingOmSetPPSRewardBalanceRes(eqlRes,page)) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.BALANCE_BAD);

				}else{
					
					if (eqlRes.getRespstatus().toUpperCase().trim().equals("FAIL")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("PROCESS")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("CANCEL")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("REJECT")
							|| (eqlRes.getRespstatus().toUpperCase().trim().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {
					
						writeStatsReceive(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
						hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
						
					}else{
						writeStatsReceive(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
						hmMessage = mapSetPPSRewardFreeCmd(eqlRes, af, smoiIns);	
					}
					
				}
			} else{
				EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
						EqlBsoAdjustCbsResponse.class);
				if (isMissingOmSetPPSRewardBalanceRes(eqlRes,page)) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.BALANCE_BAD);

				}else{
					if (eqlRes.getRespstatus().toUpperCase().trim().equals("FAIL")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("PROCESS")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("CANCEL")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("REJECT")
							|| (eqlRes.getRespstatus().toUpperCase().trim().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {
					
						writeStatsReceive(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
						hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
						
					} else {
						
						writeStatsReceive(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
						hmMessage = mapSetPPSRewardBalanceCmd(eqlRes, af, smoiIns);
						
					}
				}
			}
			
		} else if(adjType.equals(AdjustType.BALANCE_AND_VALIDITY)){
			
			Gson gson = new Gson();
			EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
					EqlBsoAdjustCbsResponse.class);
			if (isMissingOmSetPPSRewardBalAndValRes(eqlRes,page)) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
						StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Response, smoiIns);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
				hmMessage = mapEquinoxReturnCmd(EResponseCode.BALANCEANDVALIDITY_BAD);

			}else{
				if (eqlRes.getRespstatus().toUpperCase().trim().equals("FAIL")
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("PROCESS")
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("CANCEL")
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("REJECT")
						|| (eqlRes.getRespstatus().toUpperCase().trim().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {
					
					writeStatsReceive(eqlRes,af,smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_RESPONSE_ERROR);
					
				} else {
					
					writeStatsReceive(eqlRes,af,smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
					hmMessage = mapSetPPSRewardBalanceAndValidityCmd(eqlRes, af, smoiIns);
					
				}
			}
			
				
		} else if(adjType.equals(AdjustType.FREEUNIT)){
			
			Gson gson = new Gson();
			
			EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
						EqlBsoAdjustCbsResponse.class);
				if (isMissingOmSetPPSRewardBalAndValRes(eqlRes,page)) {
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_FREEUNIT_Response, smoiIns);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					hmMessage = mapEquinoxReturnCmd(EResponseCode.FREEUNIT_BAD);

				}else{
					
					if (eqlRes.getRespstatus().toUpperCase().trim().equals("FAIL")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("PROCESS")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("CANCEL")
							|| eqlRes.getRespstatus().toUpperCase().trim().equals("REJECT")
							|| (eqlRes.getRespstatus().toUpperCase().trim().equals("PARTLYFAIL") && checkPartlyfail(eqlRes.getSmessage()) ) ) {
					
						writeStatsReceive(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
						hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR);
						
					}else{
						writeStatsReceive(eqlRes,af,smoiIns);
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
								StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
						hmMessage = mapSetPPSRewardFreeCmd(eqlRes, af, smoiIns);
					}
				}
			
		} else if(adjType.equals(AdjustType.PRMMONEY_AND_FREEUNIT)){
			
			Gson gson = new Gson();
			EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg,
					EqlBsoAdjustCbsResponse.class);
			if (isMissingOmSetPPSRewardBalAndValRes(eqlRes,page)) {
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
						StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
				SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
						StatAlarm.INGateway_Receive_EQL_BAD_BSO_ADJUST_CBS_FREEUNIT_Response, smoiIns);
				SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
				hmMessage = mapEquinoxReturnCmd(EResponseCode.BALANCE_BAD);

			}else{
				
				if (eqlRes.getRespstatus().toUpperCase().trim().equals("FAIL") 
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("PROCESS")
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("CANCEL")
						|| eqlRes.getRespstatus().toUpperCase().trim().equals("REJECT")
						|| (eqlRes.getRespstatus().toUpperCase().trim().equals("PARTLYFAIL") 
								&& checkPartlyfail(eqlRes.getSmessage()))) {
				
					writeStatsPrmmoneyAndFree(eqlRes,af,smoiIns); 
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
					boolean isSettingReturn = false;
					int isAllSuccess = 0;
					if(smoiIns.getEqlRequestInstance() != null) {
						for(EQLRequestInstance eqlReq : smoiIns.getEqlRequestInstance()) {
							if(eqlRes.getBsono() != null) {
								for(BSONOBalanceAndFreeResponse bsono : eqlRes.getBsono() ) {
									if(bsono.getBsoid() != null 
											&& bsono.getBso() != null 
											&& bsono.getBsoStatus() != null
											&& bsono.getBso().equals(eqlReq.getBsoName())
											&& bsono.getBsoid().equals(eqlReq.getBsoId())){
										if(bsono.getBsoStatus().equalsIgnoreCase("FAIL")) {
											if(bsono.getBso().trim().equals("ADJUST_CBS_BALANCE")){
												hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
												isSettingReturn = true;
												break;
											} else if(bsono.getBso().trim().equals("ADJUST_CBS_FREEUNIT")){
												hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR);
												isSettingReturn = true;
												break;
											}
										} else if(bsono.getBsoStatus().equalsIgnoreCase("SUCCESS")) {	
											isAllSuccess++;
											if(isAllSuccess == eqlRes.getBSONOListItem().size()) {
												bsono = eqlRes.getBSONOListItem().get(0);
												if(bsono.getBso().trim().equals("ADJUST_CBS_BALANCE")){
													hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
													isSettingReturn = true;
													break;
												} else if(bsono.getBso().trim().equals("ADJUST_CBS_FREEUNIT")){
													hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR);
													isSettingReturn = true;
													break;
												}
												
//												eqlReq = smoiIns.getEqlRequestInstance().get(0);
//												if(eqlReq.getBsoName().trim().equals("ADJUST_CBS_BALANCE")){
//													hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
//													isSettingReturn = true;
//													break;
//												}
//												else if(eqlReq.getBsoName().trim().equals("ADJUST_CBS_FREEUNIT")){
//													hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR);
//													isSettingReturn = true;
//													break;
//												}
											}
										}
									}
								}
							}
							if(isSettingReturn) {
								break;
							}
						}
					}
					
					
					
//					if(eqlRes.getRespstatus().toUpperCase().trim().equals("FAIL") || 
//							(eqlRes.getRespstatus().toUpperCase().trim().equals("PARTLYFAIL")  )){ 
//						hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
//					}else{
//						hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR);
//					}
					
					
					
				}else{
					
					writeStatsPrmmoneyAndFree(eqlRes,af,smoiIns); 
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(),
							StatAlarm.INGateway_Send_HTTP_$s_Response_Success, smoiIns);
					hmMessage = mapSetPPSRewardFreeCmd(eqlRes, af, smoiIns);
				}
				
			}
		}
		
		return hmMessage;
	}
	
	private HashMap<String, String> genMsgHttpResponseModiPPSCreditLimit(AbstractAF af, SmoiInstance smoiIns,
			String inputMsg,HashMap<String, String> hmMessage) {
		Gson gson = new Gson();
		String page = smoiIns.getPage();
		
		EqlBsoAdjustCbsResponse eqlRes = gson.fromJson(inputMsg, EqlBsoAdjustCbsResponse.class);
		
		if (isMissingOmModiPPSCreditLimitRes(eqlRes,page)) {
			//case bad 
			    SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGateway_Receive_EQL_BAD_BSO_QUERY_CBS_SUB_Response, smoiIns);
			    SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGateway_Send_HTTP_$s_Response_Error, smoiIns);
			    hmMessage = mapEquinoxReturnCmd(EResponseCode.QUERY_BAD);
		   

		}

		
		else {
			   //case success from EQL
			   if((eqlRes.getBSONOListItem() != null && eqlRes.getBSONOListItem().get(0).getBsoStatus() != null && ! eqlRes.getBSONOListItem().get(0).getBsoStatus().trim().equals("Success")) 
					   || (eqlRes.getRespstatus() != null  && ! eqlRes.getRespstatus().trim().equals("Success"))){
				   hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_ERROR);
				   SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Error, smoiIns);
				   SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR, smoiIns);
			   }else if(customerStateInvalid(eqlRes,af)){
				   // 1001
				   hmMessage = mapEquinoxReturnCmd(EResponseCode.MSISDM_STATUS_INCORRECT);
				   SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response, smoiIns);
				   SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR, smoiIns);
			   }else if(smoiIns.getHttpPostValue("increment") != null 
					   && Integer.parseInt(smoiIns.getHttpPostValue("increment")) < 0){
				   // 1015
				   hmMessage = mapEquinoxReturnCmd(EResponseCode.MODIFIED_LESS_THAN_ZERO);
				   SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response, smoiIns);
				   SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR, smoiIns);
			   }else{
				   SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response, smoiIns);
				   SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGATEWAY_SEND_MD_BSO_CBS_NEGATIVE_REQUEST, smoiIns);
				   // send request to MD 
			   }
		}
		return hmMessage;
	}
	
	
	private HashMap<String, String> mapSetPPSRewardFreeCmd(EqlBsoAdjustCbsResponse eqlRes, AbstractAF af,
			SmoiInstance smoiIns) {
		
//		HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
		HashMap<String, String> msgReturn = new HashMap<String, String>();
		String res = EResponseCode.OPERATION_SUCCESS.getCode(); 
		String desc = EResponseCode.OPERATION_SUCCESS.getDesc();
		StringBuilder sb = new StringBuilder("");

		String log_RESULTCODE = "";
		String log_DESC = "";	
		
		sb.append("<vcrr>");
		sb.append("<res>").append(res).append("</res>");
		sb.append("<desc>").append(desc).append("</desc>");
		sb.append("</vcrr>");
		
		log_RESULTCODE = res;
		log_DESC = desc;
		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(),
				sb.toString());
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(),
				log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);  
		
		return msgReturn;
		
		
		
	}



	private HashMap<String, String> mapSetPPSRewardBalanceAndValidityCmd(EqlBsoAdjustCbsResponse eqlRes, AbstractAF af,
			SmoiInstance smoiIns) {


		HashMap<String, String> msgReturn = new HashMap<String, String>();
		String res = EResponseCode.OPERATION_SUCCESS.getCode(); 
		String desc = EResponseCode.OPERATION_SUCCESS.getDesc();
		StringBuilder sb = new StringBuilder("");

		String log_RESULTCODE = "";
		String log_DESC = "";

		sb.append("<vcrr>");
		sb.append("<res>").append(res).append("</res>");
		sb.append("<desc>").append(desc).append("</desc>");
		sb.append("</vcrr>");
		
		log_RESULTCODE = res;
		log_DESC = desc;
		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(),
				sb.toString());
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(),
				log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
		
		return msgReturn;
	}



	private boolean isMissingOmSetPPSRewardBalAndValRes(EqlBsoAdjustCbsResponse eqlRes, String page) {
		boolean isMissingOm = false;
		String respstatus = eqlRes.getRespstatus();
		String smessage = eqlRes.getSmessage();
		if( smessage == null || respstatus == null){
			isMissingOm = true;
		}
		
		return isMissingOm;
	}



	private HashMap<String, String> mapSetPPSRewardBalanceCmd(EqlBsoAdjustCbsResponse eqlRes, AbstractAF af,
			SmoiInstance smoiIns) {


		HashMap<String, String> msgReturn = new HashMap<String, String>();
		String res = EResponseCode.OPERATION_SUCCESS.getCode(); 
		String desc = EResponseCode.OPERATION_SUCCESS.getDesc();
		StringBuilder sb = new StringBuilder("");

		String log_RESULTCODE = "";
		String log_DESC = "";

		sb.append("<vcrr>");
		sb.append("<res>").append(res).append("</res>");
		sb.append("<desc>").append(desc).append("</desc>");
		sb.append("</vcrr>");
		
		log_RESULTCODE = res;
		log_DESC = desc;
		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(),
				sb.toString());
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(),
				log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
		
		
		return msgReturn;

	}



	private boolean isMissingOmSetPPSRewardBalanceRes(EqlBsoAdjustCbsResponse eqlRes, String page) {
		boolean isMissingOm = false;
		String respstatus = eqlRes.getRespstatus();
		String smessage = eqlRes.getSmessage();
		if(smessage == null	|| respstatus == null){
			isMissingOm = true;
		}
		
		return isMissingOm;
	}



	private HashMap<String, String> mapSetPPSRewardValidityCmd(EqlBsoAdjustCbsValidityResponse eqlRes, AbstractAF af,
			SmoiInstance smoiIns) {
		
		
		HashMap<String, String> msgReturn = new HashMap<String, String>();
//		String res = "000";
//		String desc = "Operation succeeded";
		String res = EResponseCode.OPERATION_SUCCESS.getCode(); 
		String desc = EResponseCode.OPERATION_SUCCESS.getDesc();
		StringBuilder sb = new StringBuilder("");


		String log_RESULTCODE = "";
		String log_DESC = "";


		sb.append("<vcrr>");
		sb.append("<res>").append(res).append("</res>");
		sb.append("<desc>").append(desc).append("</desc>");
		sb.append("</vcrr>");
		
		log_RESULTCODE = res;
		log_DESC = desc;
		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
		
		return msgReturn;


	}



	private boolean isMissingOmSetPPSRewardValidityRes(EqlBsoAdjustCbsValidityResponse eqlRes, String page) {
		boolean isMissingOm = false;
		String respstatus = eqlRes.getRespstatus();

		String smessage = eqlRes.getSmessage();
		if(smessage == null	|| respstatus == null){
			isMissingOm = true;
		}
		
		return isMissingOm;
	}



	private HashMap<String, String> mapEquinoxReturnCmd(EResponseCode _ResponseCode) {
		HashMap<String, String> msgReturn = new HashMap<String, String>();
		String msg = "";
		String res = _ResponseCode.getCode();
		String desc = _ResponseCode.getDesc();
		String log_RESULTCODE = "";
		String log_DESC = "";

		msg = "<vcrr><res>" + res + "</res><desc>" + desc + "</desc></vcrr>";

		log_RESULTCODE = res;
		log_DESC = desc;

		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), msg);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);

		return msgReturn;
	}

	// Chatl 03/08/2018
	private boolean isMissingOmModiPPSCreditLimitRes(EqlBsoAdjustCbsResponse eqlRes , String page) {
		boolean isMissingOm = false;
		String respstatus = null,smessage = null, substatus= null ;
			try {
				
				respstatus = eqlRes.getRespstatus();
				smessage = eqlRes.getSmessage();
				substatus = eqlRes.getBSONOListItem().get(0).getSubLifeCycle().getSubStatus();
			} catch (Exception e) {
				// TODO: handle exception
				Log.d("Cannot Check mandatory");
				return true;
			}
			
		if( smessage == null || respstatus == null || substatus == null ){
			isMissingOm = true;
		}
		
		return isMissingOm;
	}
	
	
	
	
	// Chatl 14/03/2018
	private boolean isMissingOmModiPPSValidityRes(EqlBsoAdjustCbsValidityResponse eqlRes,String page) {
		boolean isMissingOm = false;
		String respstatus = eqlRes.getRespstatus();
		String smessage = eqlRes.getSmessage();
		if( smessage == null || respstatus == null){
			isMissingOm = true;
		}

		return isMissingOm;
	}

	// PIT 27/09/2017 
	// Chal 28/09/2017
	private boolean isMissingOmModiPPSMultiAttrValidityRes(EqlBsoAdjustCbsValidityResponse eqlRes,String page) {
		boolean isMissingOm = false;
		String respstatus = eqlRes.getRespstatus();

		// 14/03/2018
		String smessage = eqlRes.getSmessage();
		if(smessage == null	|| respstatus == null){
			isMissingOm = true;
		}
		
		return isMissingOm;
	}
	
	private boolean isMissingOmModiPPSMultiAttrBalanceRes(EqlBsoAdjustCbsResponse eqlRes,String page) {
		boolean isMissingOm = false;
		String respstatus = eqlRes.getRespstatus();
		String smessage = eqlRes.getSmessage();
		if(smessage == null	|| respstatus == null){
			isMissingOm = true;
		}
		
		return isMissingOm;
	}
	
	private boolean isMissingOmModiPPSMultiAttrBalAndValRes(EqlBsoAdjustCbsResponse eqlRes,String page) {
		boolean isMissingOm = false;
		String respstatus = eqlRes.getRespstatus();
		String smessage = eqlRes.getSmessage();
		if( smessage == null || respstatus == null){
			isMissingOm = true;
		}
		
		return isMissingOm;
	}
//	private boolean isMissingOmModiPPSMultiAttrFreeRes(EqlBsoAdjustCbsFreeUnitResponse eqlRes) {
//		boolean isMissingOm = false;
//		String respstatus = eqlRes.getRespstatus();
//		int bsono = eqlRes.getBSONOFreeUnitListItem().size();
//		if (respstatus == null || bsono == 0) {
//			isMissingOm = true;
//		}
//		return isMissingOm;
//	}
//	private boolean isMissingOmModiPPSMultiAttrFreeRes(EqlBsoAdjustCbsBalanceResponse eqlRes) {
//		boolean isMissingOm = false;
//		String respstatus = eqlRes.getRespstatus();
//		int bsono = eqlRes.getBSONOBalanceListItem().size();
//		if (respstatus == null || bsono == 0) {
//			isMissingOm = true;
//		}
//		return isMissingOm;
//	}
	// PIT
	private HashMap<String, String> mapModiPPSMultiAttrValidityCmd(EqlBsoAdjustCbsValidityResponse eqlRes, AbstractAF af,
			SmoiInstance smoiIns) {
		HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
		HashMap<String, String> msgReturn = new HashMap<String, String>();
		String res = "000";
		String desc = "Modifying the validity term succeeded";
		StringBuilder sb = new StringBuilder("");
		long newbalance = 0;
		long newprmmoney = 0;
		long newprmsm = 0;
		long newprmminute = 0;
		String newpoint = "";
		long newfreecalltimes = 0;
		long newfreerbtsong = 0;
		long newfreerbtmf = 0;

		String newscore = "";
		String newprmscore = "";
		String newsmusage = "";
		String newactivestop = "";

		String log_RESULTCODE = "";
		String log_DESC = "";
		String mainbalConf = smoi_conf.get(Conf.mainBalanceType).get(0);
			int bsonoSize = eqlRes.getBSONOListItem().size();
			for(int bsonoIndex = 0 ; bsonoIndex < bsonoSize ; bsonoIndex++){
				
				String bso = eqlRes.getBSONOListItem().get(bsonoIndex).getBso();
				if(bso != null && bso.equals("QUERY_CBS_SUB")){
					int balListSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().size();
					for(int balListIndex = 0;balListIndex < balListSize; balListIndex++){
						int balresultListSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balListIndex).getBalanceResultListItem().size();
						for(int balresultListIndex = 0 ; balresultListIndex < balresultListSize ; balresultListIndex++){
								String balanceType = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balListIndex).getBalanceResultListItem().get(balresultListIndex).getBalanceType();
								
								if(balanceType != null && balanceType.equals(mainbalConf)){
									newbalance = newbalance + Long.parseLong(eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balListIndex).getBalanceResultListItem().get(balresultListIndex).getTotalAmount());
								}
						}	
					}
				}
			
				if(eqlRes.getBSONOListItem().get(bsonoIndex).getNewStatuslist() != null){
					int newStatusSize = eqlRes.getBSONOListItem().get(bsonoIndex).getNewStatusListItem().size();
					for(int newStatus = 0 ; newStatus < newStatusSize ; newStatus++){
						
						String newExtTime = null;
						newExtTime = eqlRes
								.getBSONOListItem().get(bsonoIndex)
								.getNewStatusListItem().get(newStatus)
								.getNewExpireTime();
						if (newExtTime != null) {
							String newStatusName = eqlRes
									.getBSONOListItem().get(bsonoIndex)
									.getNewStatusListItem().get(newStatus)
									.getNewStatusName();
							
								if (newStatusName != null && newStatusName.equals("Active")) {
									DateTime parser = DateFunction.EQL
											.parser(newExtTime);
									newactivestop = DateFunction.REPORT_EFFECTIVE.print(parser);
								}
						} 
					}
				}
			
			}
		
		sb.append("<vcrr>");
		sb.append("<res>").append(res).append("</res>");
		sb.append("<desc>").append(desc).append("</desc>");
		// get value from CCA
		sb.append("<newbalance>").append(newbalance).append("</newbalance>");
		// fix 0,blank value
		sb.append("<newprmmoney>").append(newprmmoney).append("</newprmmoney>");
		sb.append("<newprmsm>").append(newprmsm).append("</newprmsm>");
		sb.append("<newprmminute>").append(newprmminute).append("</newprmminute>");
		sb.append("<newpoint>").append(newpoint).append("</newpoint>");
		sb.append("<newfreecalltimes>").append(newfreecalltimes).append("</newfreecalltimes>");
		sb.append("<newfreerbtsong>").append(newfreerbtsong).append("</newfreerbtsong>");
		sb.append("<newfreerbtmf>").append(newfreerbtmf).append("</newfreerbtmf>");
		sb.append("<newscore>").append(newscore).append("</newscore>");
		sb.append("<newprmscore>").append(newprmscore).append("</newprmscore>");
		sb.append("<newsmusage>").append(newsmusage).append("</newsmusage>");
		sb.append("<newactivestop>").append(newactivestop).append("</newactivestop>");
		sb.append("</vcrr>");
		
		log_RESULTCODE = res;
		log_DESC = desc;
		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), sb.toString());
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
		return msgReturn;

	}
	
	// Chatl 17/10/2017 for EQL BSO ADJUST CBS BALANCE And Validity Res
	private HashMap<String, String> mapModiPPSMultiAttrBalanceAndValidityCmd(
			EqlBsoAdjustCbsResponse eqlRes, AbstractAF af,
			SmoiInstance smoiIns) {
		HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
		HashMap<String, String> msgReturn = new HashMap<String, String>();
		String res = EResponseCode.VALIDITY_SUCCESS.getCode(); //"000";
		String desc = EResponseCode.VALIDITY_SUCCESS.getDesc();// "Modifying the validity term succeeded";
		StringBuilder sb = new StringBuilder("");
		long newbalance = 0;
		long newprmmoney = 0;
		long newprmsm = 0;
		long newprmminute = 0;
		String newpoint = "";
		long newfreecalltimes = 0;
		long newfreerbtsong = 0;
		long newfreerbtmf = 0;

		String newscore = "";
		String newprmscore = "";
		String newsmusage = "";
		String newactivestop = "";

		String log_RESULTCODE = "";
		String log_DESC = "";
		String mainbalConf = smoi_conf.get(Conf.mainBalanceType).get(0);
		// set newbalance and newactivestop
		int bsonoSize = eqlRes.getBSONOListItem().size();
		for(int bsonoIndex = 0 ; bsonoIndex < bsonoSize ; bsonoIndex++){
			int adjSize = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().size();
			for(int adjIndex = 0 ; adjIndex < adjSize ; adjIndex++){
				
					String balanaceType = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjIndex).getBalanceType();
					if(balanaceType != null && balanaceType.equals(mainbalConf)){
						newbalance = newbalance + eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjIndex).getNewBalanceAmount();
					}
			}
		
			if(eqlRes.getBSONOListItem().get(bsonoIndex).getLifecyclechginfoObj() != null){
				int newStatusSize = eqlRes.getBSONOListItem().get(bsonoIndex).getLifecyclechginfoObj().getNewStatusListItem().size();
				for(int newStatus = 0 ; newStatus < newStatusSize ; newStatus++){
					
					String newExtTime = null;
					newExtTime = eqlRes
							.getBSONOListItem().get(bsonoIndex)
							.getLifecyclechginfoObj()
							.getNewStatusListItem().get(newStatus)
							.getNewExpireTime();
					if (newExtTime != null) {
						String newStatusName = eqlRes
								.getBSONOListItem().get(bsonoIndex)
								.getLifecyclechginfoObj()
								.getNewStatusListItem().get(newStatus)
								.getNewStatusName();
						if (newStatusName != null && newStatusName.equals("Active")) {
							DateTime parser = DateFunction.EQL
									.parser(newExtTime);
							newactivestop = DateFunction.REPORT_EFFECTIVE.print(parser);
						}
					} 
				}
			}
		}
		
		sb.append("<vcrr>");
		sb.append("<res>").append(res).append("</res>");
		sb.append("<desc>").append(desc).append("</desc>");
		// get value from CCA
		sb.append("<newbalance>").append(newbalance).append("</newbalance>");
		// fix 0,blank value
		sb.append("<newprmmoney>").append(newprmmoney).append("</newprmmoney>");
		sb.append("<newprmsm>").append(newprmsm).append("</newprmsm>");
		sb.append("<newprmminute>").append(newprmminute).append("</newprmminute>");
		sb.append("<newpoint>").append(newpoint).append("</newpoint>");
		sb.append("<newfreecalltimes>").append(newfreecalltimes).append("</newfreecalltimes>");
		sb.append("<newfreerbtsong>").append(newfreerbtsong).append("</newfreerbtsong>");
		sb.append("<newfreerbtmf>").append(newfreerbtmf).append("</newfreerbtmf>");
		sb.append("<newscore>").append(newscore).append("</newscore>");
		sb.append("<newprmscore>").append(newprmscore).append("</newprmscore>");
		sb.append("<newsmusage>").append(newsmusage).append("</newsmusage>");
		sb.append("<newactivestop>").append(newactivestop).append("</newactivestop>");
		sb.append("</vcrr>");
		
		log_RESULTCODE = res;
		log_DESC = desc;
		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(),
				sb.toString());
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(),
				log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
		return msgReturn;

	}
	
	// Chatl 17/10/2017 for EQL BSO ADJUST CBS BALANCE Res
	private HashMap<String, String> mapModiPPSMultiAttrBalanceCmd(
			EqlBsoAdjustCbsResponse eqlRes, AbstractAF af,
			SmoiInstance smoiIns) {
		HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
		HashMap<String, String> msgReturn = new HashMap<String, String>();
		String res = EResponseCode.VALIDITY_SUCCESS.getCode(); //"000";
		String desc = EResponseCode.VALIDITY_SUCCESS.getDesc();// "Modifying the validity term succeeded";
		StringBuilder sb = new StringBuilder("");
		long newbalance = 0;
		long newprmmoney = 0;
		long newprmsm = 0;
		long newprmminute = 0;
		String newpoint = "";
		long newfreecalltimes = 0;
		long newfreerbtsong = 0;
		long newfreerbtmf = 0;

		String newscore = "";
		String newprmscore = "";
		String newsmusage = "";
		String newactivestop = "";

		String log_RESULTCODE = "";
		String log_DESC = "";

		String mainbalConf = smoi_conf.get(Conf.mainBalanceType).get(0);
		// set newbalance 
		int bsonoSize = eqlRes.getBSONOListItem().size();
		for(int bsonoIndex = 0 ; bsonoIndex < bsonoSize ; bsonoIndex++){
			
			int adjSize = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().size();
			String flag = smoiIns.getFlag();
			if( flag.equals("1")){ 
				
					if( adjSize > 0){
						for(int adjIndex = 0 ; adjIndex < adjSize ; adjIndex++){
							String balanceType = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjIndex).getBalanceType();
							if(balanceType != null && balanceType.equals(mainbalConf)){
								newbalance = newbalance + eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjIndex).getNewBalanceAmount();
							}
						}
					}else{
						int balanceSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().size();
						for(int balanceIndex = 0 ; balanceIndex < balanceSize ; balanceIndex++){
							int balanceResultSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balanceIndex).getBalanceResultListItem().size();
							for(int balanceResultIndex = 0 ; balanceResultIndex < balanceResultSize ; balanceResultIndex++){
								String balanceType = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balanceIndex).getBalanceResultListItem().get(balanceResultIndex).getBalanceType();
								if(balanceType != null && balanceType.equals(mainbalConf)){
									int balanceDetailSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balanceIndex).getBalanceResultListItem().get(balanceResultIndex).getBalanceDetailItem().size();
									for(int balanceDetailIndex = 0 ; balanceDetailIndex < balanceDetailSize ; balanceDetailIndex++ ){
										newbalance = newbalance + Long.parseLong(eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balanceIndex).getBalanceResultListItem().get(balanceResultIndex).getBalanceDetailItem().get(balanceDetailIndex).getNewBalanceAmount());
									}
								}
							}
						}
					}
				
				
			}else {
				
				for(int adjIndex = 0 ; adjIndex < adjSize ; adjIndex++){
					String balanceType = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjIndex).getBalanceType();
					if(balanceType != null && balanceType.equals(mainbalConf)){
						newbalance = newbalance + eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjIndex).getNewBalanceAmount();
					}
				}
				
			}
			
			// set newactivestop
			if(eqlRes.getBSONOListItem().get(bsonoIndex).getLifecyclechginfoObj() != null){
				int newStatusSize = eqlRes.getBSONOListItem().get(bsonoIndex).getLifecyclechginfoObj().getNewStatusListItem().size();
				for(int newStatus = 0 ; newStatus < newStatusSize ; newStatus++){
					
					String newExtTime = null;
					newExtTime = eqlRes
							.getBSONOListItem().get(bsonoIndex)
							.getLifecyclechginfoObj()
							.getNewStatusListItem().get(newStatus)
							.getNewExpireTime();
					if (newExtTime != null) {
						String newStatusName = eqlRes
								.getBSONOListItem().get(bsonoIndex)
								.getLifecyclechginfoObj()
								.getNewStatusListItem().get(newStatus)
								.getNewStatusName();
						if (newStatusName != null && newStatusName.equals("Active")) {
							DateTime parser = DateFunction.EQL
									.parser(newExtTime);
							newactivestop = DateFunction.REPORT_EFFECTIVE.print(parser);
						}
					} 
				}
			}
		}
		
		sb.append("<vcrr>");
		sb.append("<res>").append(res).append("</res>");
		sb.append("<desc>").append(desc).append("</desc>");
		// get value from CCA
		sb.append("<newbalance>").append(newbalance).append("</newbalance>");
		// fix 0,blank value
		sb.append("<newprmmoney>").append(newprmmoney).append("</newprmmoney>");
		sb.append("<newprmsm>").append(newprmsm).append("</newprmsm>");
		sb.append("<newprmminute>").append(newprmminute).append("</newprmminute>");
		sb.append("<newpoint>").append(newpoint).append("</newpoint>");
		sb.append("<newfreecalltimes>").append(newfreecalltimes).append("</newfreecalltimes>");
		sb.append("<newfreerbtsong>").append(newfreerbtsong).append("</newfreerbtsong>");
		sb.append("<newfreerbtmf>").append(newfreerbtmf).append("</newfreerbtmf>");
		sb.append("<newscore>").append(newscore).append("</newscore>");
		sb.append("<newprmscore>").append(newprmscore).append("</newprmscore>");
		sb.append("<newsmusage>").append(newsmusage).append("</newsmusage>");
		sb.append("<newactivestop>").append(newactivestop).append("</newactivestop>");
		sb.append("</vcrr>");
		
		log_RESULTCODE = res;
		log_DESC = desc;
		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(),
				sb.toString());
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(),
				log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
		return msgReturn;

	}
	
	// Chatl 2018/04/18 for EQL BSO ADJUST CBS BALANCE(free prmmoney) Res And ADJUST CBS FREEUNIT
	private HashMap<String, String> mapModiPPSMultiAttrFreeCmd(
			EqlBsoAdjustCbsResponse eqlRes, AbstractAF af,
			SmoiInstance smoiIns) {
		HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
		HashMap<String, String> msgReturn = new HashMap<String, String>();
		String res = EResponseCode.VALIDITY_SUCCESS.getCode(); 
		String desc = EResponseCode.VALIDITY_SUCCESS.getDesc();
		StringBuilder sb = new StringBuilder("");
		long newbalance = 0;
		long newprmmoney = 0;
		long newprmsm = 0;
		long newprmminute = 0;
		String newpoint = "";
		long newfreecalltimes = 0;
		long newfreerbtsong = 0;
		long newfreerbtmf = 0;

		String newscore = "";
		String newprmscore = "";
		String newsmusage = "";
		String newactivestop = "";
		

		String log_RESULTCODE = "";
		String log_DESC = "";
		String prmsmConf = smoi_conf.get(Conf.freeUnitId_PRMSM).get(0);
		String prmminuteConf = smoi_conf.get(Conf.freeUnitId_PRMMINUTE).get(0);
		String rbtsongConf = smoi_conf.get(Conf.freeUnitId_FREERBTSONG).get(0);
		
		int indexQr2 = -1;
		int indexQr3 = -1;
		int bsonoSize = eqlRes.getBSONOListItem().size();
		
		
		
		// get index response bsoid of query type. 
		for (int bsonoIndex = 0; bsonoIndex < bsonoSize; bsonoIndex++) {
			if(eqlRes.getBSONOListItem().get(bsonoIndex).getBso().trim().equals("QUERY_CBS_SUB")){
				for (int i = 0; i < smoiIns.getEqlRequestInstance().size(); i++) {
					if(eqlRes.getBSONOListItem().get(bsonoIndex).getBsoid().trim().equals(smoiIns.getEqlRequestInstance().get(i).getBsoId())
							&& smoiIns.getEqlRequestInstance().get(i).getQueryType() != null
							&& smoiIns.getEqlRequestInstance().get(i).getQueryType().equals("2")){
						indexQr2 = bsonoIndex;
						break;
					}else if(eqlRes.getBSONOListItem().get(bsonoIndex).getBsoid().trim().equals(smoiIns.getEqlRequestInstance().get(i).getBsoId())
							&& smoiIns.getEqlRequestInstance().get(i).getQueryType() != null
							&& smoiIns.getEqlRequestInstance().get(i).getQueryType().equals("3")){
						indexQr3 = bsonoIndex;
						break;
					}
				}	
			}
		}
		
		// Calculate NEWPRMMONEY and Calculate new freeunit amount 
		if(indexQr2 >= 0 && eqlRes.getBSONOListItem().get(indexQr2).getBsoStatus().trim().equalsIgnoreCase("success")){ // found EQL response QUERY_TYPE2
//			for (int bsonoIndex = 0; bsonoIndex < bsonoSize; bsonoIndex++) {
				String  balTypeConf = smoi_conf.get(Conf.BALANCETYPE_PRMMONEY).get(0);
				for(int i = 0; i < smoiIns.getEqlRequestInstance().size(); i++) {
					if(eqlRes.getBSONOListItem().get(indexQr2).getBsoid().trim().equals(smoiIns.getEqlRequestInstance().get(i).getBsoId())
							//&& eqlRes.getBSONOListItem().get(indexQr2).getBsoStatus().trim().equalsIgnoreCase("success")
//							&& eqlRes.getBSONOListItem().get(bsonoIndex).getBso().trim().equals("ADJUST_CBS_BALANCE")
							){
						int blSize = eqlRes.getBSONOListItem().get(indexQr2).getBalancelist().size();
						for (int j = 0; j < blSize; j++) {
							int brlSize = eqlRes.getBSONOListItem().get(indexQr2).getBalancelist().get(j).getBalanceResultListItem().size();
							for (int k = 0; k < brlSize; k++) {
								if(eqlRes.getBSONOListItem().get(indexQr2).getBalanceListItem().get(j).getBalanceResultList().get(k).getBalanceType().trim().equals(balTypeConf)){
									newprmmoney = newprmmoney + Long.parseLong(eqlRes.getBSONOListItem().get(indexQr2).getBalanceListItem().get(j).getBalanceResultList().get(k).getTotalAmount());	
								}
							}
						}
						break;
					}
				}
//			}
		}else{
			//old code
			for (int bsonoIndex = 0; bsonoIndex < bsonoSize; bsonoIndex++) {
				if(smoiIns.getFlag().equals("0")){
					ArrayList<String> balanceIdList = new ArrayList<String>();
					String  balTypeConf = smoi_conf.get(Conf.BALANCETYPE_PRMMONEY).get(0);
					// prmmoney  flag = 0
					if((eqlRes.getBSONOListItem().get(bsonoIndex).getBso() != null
							&& eqlRes.getBSONOListItem().get(bsonoIndex).getBso().trim().equals("ADJUST_CBS_BALANCE"))){ //condition 1
						
						
							int adjInfoListSize = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().size();
							for (int adjInfoListIndex = 0; adjInfoListIndex < adjInfoListSize; adjInfoListIndex++) {
								
//								Long newBalAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getNewBalanceAmount();
//								if(newBalAmount != null){
//									newprmmoney = newprmmoney + newBalAmount ;
//								}
								
								String balanceType = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getBalanceType();
								if(balanceType != null 	&& balTypeConf.trim().equals(balanceType.trim())){ //condition 2
									
									Long newBalAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getNewBalanceAmount();
									if(newBalAmount != null){
										newprmmoney = newprmmoney + newBalAmount ;
									}
									
									String balanceId = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getBalanceId();
									if( balanceId != null){
										balanceIdList.add(balanceId);
									}
									
								}
							}
							
							
							int balancelistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().size();
							for (int balancelistIndex = 0; balancelistIndex < balancelistSize; balancelistIndex++) {
								
								int balanceresultlistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().size();
								for (int balanceresultlistIndex = 0; balanceresultlistIndex < balanceresultlistSize; balanceresultlistIndex++) {
									String balanceType =  eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceType();
									
									if( (balanceType != null) && (balTypeConf.trim().equals(balanceType.trim())) ){ // Condition 3
										
										int balancedetaillistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().size();
										for (int balancedetaillistIndex = 0; balancedetaillistIndex < balancedetaillistSize; balancedetaillistIndex++) {
											
											String balanceInsId = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().get(balancedetaillistIndex).getBalanceInsId();
											String newBalAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().get(balancedetaillistIndex).getNewBalanceAmount();
											
											if(newBalAmount != null && balanceInsId != null && !balanceIdList.contains(balanceInsId)){
												newprmmoney = newprmmoney + Long.parseLong(newBalAmount) ;	
											}

										}
									}
								}
							}
						}
				}else if(smoiIns.getFlag().equals("1")){
					// prmmoney  flag = 1
					ArrayList<String> balanceIdList = new ArrayList<String>();
					if((eqlRes.getBSONOListItem().get(bsonoIndex).getBso() != null
							&& eqlRes.getBSONOListItem().get(bsonoIndex).getBso().trim().equals("ADJUST_CBS_BALANCE"))){
						
						if(eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem() != null){
							// 2.1 if $EQLResponse.BSONO.ADJUSTMENTINFOLIST is available
							
							String  balTypeConf = smoi_conf.get(Conf.BALANCETYPE_PRMMONEY).get(0);
//							ArrayList<String> balanceIdList = new ArrayList<String>();
							// $NEWPRMMONEY summary and add $BALANCEIDLIST 
//								if((eqlRes.getBSONOBalanceListItem().get(bsonoIndex).getBso() != null
//									&& eqlRes.getBSONOBalanceListItem().get(bsonoIndex).getBso().trim().equals("ADJUST_CBS_BALANCE"))){ //condition 1
									
									int adjInfoListSize = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().size();
									for (int adjInfoListIndex = 0; adjInfoListIndex < adjInfoListSize; adjInfoListIndex++) {
										
//										Long newBalAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getNewBalanceAmount();
//										if(newBalAmount != null){
//											newprmmoney = newprmmoney + newBalAmount ;
//										}
										String balanceType = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getBalanceType();
										if(balanceType != null 	&& balTypeConf.trim().equals(balanceType.trim())){
											
											Long newBalAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getNewBalanceAmount();
											if(newBalAmount != null){
												newprmmoney = newprmmoney + newBalAmount ;
											}
											
											String balanceId = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getBalanceId();
											if( balanceId != null){
												balanceIdList.add(balanceId);
											}
											
										}
									}
									
									
									int balancelistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().size();
									for (int balancelistIndex = 0; balancelistIndex < balancelistSize; balancelistIndex++) {
										
										int balanceresultlistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().size();
										for (int balanceresultlistIndex = 0; balanceresultlistIndex < balanceresultlistSize; balanceresultlistIndex++) {
											String balanceType =  eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceType();
											
											if( (balanceType != null) && (balTypeConf.trim().equals(balanceType.trim())) ){ // Condition 3
												
												int balancedetaillistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().size();
												for (int balancedetaillistIndex = 0; balancedetaillistIndex < balancedetaillistSize; balancedetaillistIndex++) {
													
													String balanceInsId = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().get(balancedetaillistIndex).getBalanceInsId();
													String newBalAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().get(balancedetaillistIndex).getNewBalanceAmount();
													
													if(newBalAmount != null && balanceInsId != null && !balanceIdList.contains(balanceInsId)){
														newprmmoney = newprmmoney + Long.parseLong(newBalAmount) ;	
													}
												}
											}
										}
									}
//								}
						}else{
							// 2.2 if $EQLResponse.BSONO.ADJUSTMENTINFOLIST is unavailable
							int balancelistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().size();
							for (int ballistIndex = 0; ballistIndex < balancelistSize; ballistIndex++) {
								int balanceresultSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(ballistIndex).getBalanceResultListItem().size();
								for (int balanceresultIndex = 0; balanceresultIndex < balanceresultSize; balanceresultIndex++) {
									if(eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(ballistIndex).getBalanceResultListItem().get(balanceresultIndex) != null){
										int balancedetailSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(ballistIndex).getBalanceResultListItem().get(balanceresultIndex).getBalanceDetailItem().size();
										for (int balancedetailIndex = 0; balancedetailIndex < balancedetailSize; balancedetailIndex++) {
											String newBalanceAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(ballistIndex).getBalanceResultListItem().get(balanceresultIndex).getBalanceDetailItem().get(balancedetailIndex).getNewBalanceAmount();	
											if(newBalanceAmount != null){
												newprmmoney = newprmmoney + Long.parseLong(eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(ballistIndex).getBalanceResultListItem().get(balanceresultIndex).getBalanceDetailItem().get(balancedetailIndex).getNewBalanceAmount());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		if(indexQr3 >= 0 && eqlRes.getBSONOListItem().get(indexQr3).getBsoStatus().trim().equalsIgnoreCase("success")){ // found EQL response QUERY_TYPE3
//			for (int bsonoIndex = 0; bsonoIndex < bsonoSize; bsonoIndex++) {
//				String  balTypeConf = smoi_conf.get(Conf.BALANCETYPE_PRMMONEY).get(0);
				for(int i = 0; i < smoiIns.getEqlRequestInstance().size(); i++) {
					if(eqlRes.getBSONOListItem().get(indexQr3).getBsoid().trim().equals(smoiIns.getEqlRequestInstance().get(i).getBsoId())
//							&& eqlRes.getBSONOListItem().get(indexQr3).getBsoStatus().trim().equalsIgnoreCase("success")
							//&& eqlRes.getBSONOListItem().get(bsonoIndex).getBso().trim().equals("ADJUST_CBS_FREEUNIT")
							){
						
						int freeunitSize = eqlRes.getBSONOListItem().get(indexQr3).getFreeUnitItemListItem().size();
						for (int j = 0; j < freeunitSize; j++) {
//							int freeunitDetailSize = eqlRes.getBSONOListItem().get(indexQr3).getFreeUnitItemListItem().get(j).getFreeUnitItemDetailListItem().size();
//							for (int k = 0; k < freeunitDetailSize; k++) {
								if(eqlRes.getBSONOListItem().get(indexQr3).getFreeUnitItemListItem().get(j).getFreeUnitId().equals(prmsmConf)){
									newprmsm = newprmsm + Long.parseLong(eqlRes.getBSONOListItem().get(indexQr3).getFreeUnitItemListItem().get(j).getTotalRemainingAmount());	
								}else if(eqlRes.getBSONOListItem().get(indexQr3).getFreeUnitItemListItem().get(j).getFreeUnitId().equals(prmminuteConf)){
									newprmminute = newprmminute + Long.parseLong(eqlRes.getBSONOListItem().get(indexQr3).getFreeUnitItemListItem().get(j).getTotalRemainingAmount());	
								}else if(eqlRes.getBSONOListItem().get(indexQr3).getFreeUnitItemListItem().get(j).getFreeUnitId().equals(rbtsongConf)){
									newfreerbtsong = newfreerbtsong + Long.parseLong(eqlRes.getBSONOListItem().get(indexQr3).getFreeUnitItemListItem().get(j).getTotalRemainingAmount());	
								}
//							}
						}
						break;
					   }
				   }
//			    }
		}else{
			// old code

			for (int bsonoIndex = 0; bsonoIndex < bsonoSize; bsonoIndex++) {
				
				if(smoiIns.getFlag().equals("0")){
					//freeunit  flag = 0
//					ArrayList<String> freeUnitInsIdList = new ArrayList<String>();
					ArrayList<String> prmminuteInsIdList = new ArrayList<String>();
					ArrayList<String> prmsmInsIdList = new ArrayList<String>();
					ArrayList<String> freesongInsIdList = new ArrayList<String>();
					if((eqlRes.getBSONOListItem().get(bsonoIndex).getBso() != null
							&& eqlRes.getBSONOListItem().get(bsonoIndex).getBso().trim().equals("ADJUST_CBS_FREEUNIT"))){ //condition 1
							
							int adjInfoListSize = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem().size();
							for (int adjInfoListIndex = 0; adjInfoListIndex < adjInfoListSize; adjInfoListIndex++) {
								
								String freeUnitId = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem().get(adjInfoListIndex).getFreeUnitId();
								Long newFreeUnitAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem().get(adjInfoListIndex).getNewFreeunitAmount();
								String freeUnitInsId = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem().get(adjInfoListIndex).getFreeUnitInstanceId();		
								
								if(freeUnitId != null && prmsmConf.trim().equals(freeUnitId.trim())){ // Case PRMSM 
									
		//							if(freeUnitId != null 	&& freeTypeConf.trim().equals(freeUnitId.trim())){
										
										if(newFreeUnitAmount != null){
											newprmsm = newprmsm + newFreeUnitAmount ;
											if(freeUnitInsId != null){
//												freeUnitInsIdList.add(freeUnitInsId);
												prmsmInsIdList.add(freeUnitInsId);
											}
										}
		//							}
									
								}else if(freeUnitId != null && prmminuteConf.trim().equals(freeUnitId.trim())){ // Case PRMMINUTE 
									
		//							freeTypeConf = smoi_conf.get(Conf.freeUnitId_PRMMINUTE).get(0);
		//							if(freeUnitId != null 	&& freeTypeConf.trim().equals(freeUnitId.trim())){
		
										if(newFreeUnitAmount != null ){
											newprmminute = newprmminute + newFreeUnitAmount ;
											if(freeUnitInsId != null){
//												freeUnitInsIdList.add(freeUnitInsId);	
												prmminuteInsIdList.add(freeUnitInsId);
											}
										}
		//							}
									
								}else if(freeUnitId != null && rbtsongConf.trim().equals(freeUnitId.trim())){ // Case FREERBTSONG 
									
		//							freeTypeConf = smoi_conf.get(Conf.freeUnitId_FREERBTSONG).get(0);
		//							if(freeUnitId != null 	&& freeTypeConf.trim().equals(freeUnitId.trim())){
										
										if(newFreeUnitAmount != null){
											newfreerbtsong = newfreerbtsong + newFreeUnitAmount ;
											if(freeUnitInsId != null){
//												freeUnitInsIdList.add(freeUnitInsId);	
												freesongInsIdList.add(freeUnitInsId);	
											}
										}
		//							}
								}
							}
							
							
							int freeUnitItemSize = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().size();
							for (int freeUnitItemIndex = 0; freeUnitItemIndex < freeUnitItemSize; freeUnitItemIndex++) {
								
								int freeUnitItemDetailSize = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().size();
								for (int freeUnitItemDetailIndex = 0; freeUnitItemDetailIndex < freeUnitItemDetailSize; freeUnitItemDetailIndex++) {
								
									String freeUnitID = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitId();
									String freeUnitInsId = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().get(freeUnitItemDetailIndex).getFreeUnitInsId();
									String newFreeUnitAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().get(freeUnitItemDetailIndex).getNewFreeUnitAmount();
									
									if(newFreeUnitAmount != null && freeUnitInsId != null && prmsmConf.trim().equals(freeUnitID.trim())){ // Case PRMSM 
											
										if(!prmsmInsIdList.contains(freeUnitInsId)){
											newprmsm = newprmsm + Long.parseLong(newFreeUnitAmount);
										}
										
									}else if(freeUnitInsId != null && prmminuteConf.trim().equals(freeUnitID.trim())){ // Case PRMMINUTE 
										
										if(!prmminuteInsIdList.contains(freeUnitInsId)){
											newprmminute = newprmminute + Long.parseLong(newFreeUnitAmount);
										}
										
									}else if(freeUnitInsId != null && rbtsongConf.trim().equals(freeUnitID.trim())){ // Case FREERBTSONG 
										
										if(!freesongInsIdList.contains(freeUnitInsId)){
											newfreerbtsong = newfreerbtsong + Long.parseLong(newFreeUnitAmount);
										}
									}
								}
							 }
						  }
				}else if(smoiIns.getFlag().equals("1")){
					// freeunit flag = 1
					if((eqlRes.getBSONOListItem().get(bsonoIndex).getBso() != null
							&& eqlRes.getBSONOListItem().get(bsonoIndex).getBso().trim().equals("ADJUST_CBS_FREEUNIT"))){ //condition 1
						
						if(eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem() != null){
							// 2.1 if $EQLResponse.BSONO.ADJUSTMENTINFOLIST is available

//							ArrayList<String> freeUnitInsIdList = new ArrayList<String>();
							ArrayList<String> prmminuteInsIdList = new ArrayList<String>();
							ArrayList<String> prmsmInsIdList = new ArrayList<String>();
							ArrayList<String> freesongInsIdList = new ArrayList<String>();
							// $NEWPRMMONEY summary and add $BALANCEIDLIST 
								if((eqlRes.getBSONOListItem().get(bsonoIndex).getBso() != null
									&& eqlRes.getBSONOListItem().get(bsonoIndex).getBso().trim().equals("ADJUST_CBS_FREEUNIT"))){ //condition 1
									
									int adjInfoListSize = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem().size();
									for (int adjInfoListIndex = 0; adjInfoListIndex < adjInfoListSize; adjInfoListIndex++) {
										
										String freeUnitId = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem().get(adjInfoListIndex).getFreeUnitId();
										Long newFreeUnitAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem().get(adjInfoListIndex).getNewFreeunitAmount();
										String freeUnitInsId = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitAdjInfoListItem().get(adjInfoListIndex).getFreeUnitInstanceId();		
										
										if(freeUnitId != null && prmsmConf.trim().equals(freeUnitId.trim())){ // Case PRMSM 
												if(newFreeUnitAmount != null ){
													newprmsm = newprmsm + newFreeUnitAmount ;
													if(freeUnitInsId != null){
//														freeUnitInsIdList.add(freeUnitInsId);
														prmsmInsIdList.add(freeUnitInsId);
													}
												}
											
										}else if(freeUnitId != null && prmminuteConf.trim().equals(freeUnitId.trim())){ // Case PRMMINUTE 
											
												if(newFreeUnitAmount != null ){
													newprmminute = newprmminute + newFreeUnitAmount ;
													if(freeUnitInsId != null){
//														freeUnitInsIdList.add(freeUnitInsId);
														prmminuteInsIdList.add(freeUnitInsId);
													}
												}
											
										}else if(freeUnitId != null && rbtsongConf.trim().equals(freeUnitId.trim())){ // Case FREERBTSONG 
											
												if(newFreeUnitAmount != null ){
													newfreerbtsong = newfreerbtsong + newFreeUnitAmount ;
													if(freeUnitInsId != null){
//														freeUnitInsIdList.add(freeUnitInsId);	
														freesongInsIdList.add(freeUnitInsId);
													}
												}
										}
									}
									
									int freeUnitItemSize = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().size();
									for (int freeUnitItemIndex = 0; freeUnitItemIndex < freeUnitItemSize; freeUnitItemIndex++) {
										
										int freeUnitItemDetailSize = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().size();
										for (int freeUnitItemDetailIndex = 0; freeUnitItemDetailIndex < freeUnitItemDetailSize; freeUnitItemDetailIndex++) {
										
											String freeUnitID = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitId();
											String freeUnitInsId = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().get(freeUnitItemDetailIndex).getFreeUnitInsId();
											String newFreeUnitAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().get(freeUnitItemDetailIndex).getNewFreeUnitAmount();
											
											if(newFreeUnitAmount != null && freeUnitInsId != null && prmsmConf.trim().equals(freeUnitID.trim())){ // Case PRMSM 
													
												if(!prmsmInsIdList.contains(freeUnitInsId)){
													newprmsm = newprmsm + Long.parseLong(newFreeUnitAmount);
												}
												
											}else if(freeUnitInsId != null && prmminuteConf.trim().equals(freeUnitID.trim())){ // Case PRMMINUTE 
												
												if(!prmminuteInsIdList.contains(freeUnitInsId)){
													newprmminute = newprmminute + Long.parseLong(newFreeUnitAmount);
												}
												
											}else if(freeUnitInsId != null && rbtsongConf.trim().equals(freeUnitID.trim())){ // Case FREERBTSONG 
												
												if(!freesongInsIdList.contains(freeUnitInsId)){
													newfreerbtsong = newfreerbtsong + Long.parseLong(newFreeUnitAmount);
												}
											}
										}
									}

								 }
						
						}else{
							// 2.2 if $EQLResponse.BSONO.ADJUSTMENTINFOLIST is unavailable
							int freeUnitItemSize = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().size();
							
							for (int freeUnitItemIndex = 0; freeUnitItemIndex < freeUnitItemSize; freeUnitItemIndex++) {
								
								int freeUnitDetailSize = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().size();
								for (int freeUnitDetailIndex= 0; freeUnitDetailIndex < freeUnitDetailSize; freeUnitDetailIndex++) {
									
									String freeUnitID = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitId();
									String freeUnitInsId = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().get(freeUnitDetailIndex).getFreeUnitInsId();
									String newFreeUnitAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getFreeUnitItemListItem().get(freeUnitItemIndex).getFreeUnitItemDetailListItem().get(freeUnitDetailIndex).getNewFreeUnitAmount();
									
									if(newFreeUnitAmount != null && freeUnitInsId != null && prmsmConf.trim().equals(freeUnitID.trim())){ // Case PRMSM 
											
											newprmsm = newprmsm + Long.parseLong(newFreeUnitAmount);
										
									}else if(freeUnitInsId != null && prmminuteConf.trim().equals(freeUnitID.trim())){ // Case PRMMINUTE 
										
											newprmminute = newprmminute + Long.parseLong(newFreeUnitAmount);
										
									}else if(freeUnitInsId != null && rbtsongConf.trim().equals(freeUnitID.trim())){ // Case FREERBTSONG 
										
											newfreerbtsong = newfreerbtsong + Long.parseLong(newFreeUnitAmount);
									}
									
								}
							}
						}
					}
				}
			}
		}
		
		sb.append("<vcrr>");
		sb.append("<res>").append(res).append("</res>");
		sb.append("<desc>").append(desc).append("</desc>");
		// get value from CCA
		sb.append("<newbalance>").append(newbalance).append("</newbalance>");
		// fix 0,blank value
		
		sb.append("<newprmmoney>").append(newprmmoney).append("</newprmmoney>");	
		sb.append("<newprmsm>").append(newprmsm).append("</newprmsm>");	 	
		sb.append("<newprmminute>").append(newprmminute).append("</newprmminute>");
		sb.append("<newpoint>").append(newpoint).append("</newpoint>");
		sb.append("<newfreecalltimes>").append(newfreecalltimes).append("</newfreecalltimes>");
    	sb.append("<newfreerbtsong>").append(newfreerbtsong).append("</newfreerbtsong>");		
		sb.append("<newfreerbtmf>").append(newfreerbtmf).append("</newfreerbtmf>");
		sb.append("<newscore>").append(newscore).append("</newscore>");
		sb.append("<newprmscore>").append(newprmscore).append("</newprmscore>");
		sb.append("<newsmusage>").append(newsmusage).append("</newsmusage>");
		sb.append("<newactivestop>").append(newactivestop).append("</newactivestop>");
		sb.append("</vcrr>");
		
		log_RESULTCODE = res;
		log_DESC = desc;
		msgReturn.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(),
				sb.toString());
		msgReturn.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(),
				log_RESULTCODE);
		msgReturn.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);  
		return msgReturn;
	}
	

		
	
		
		
//		private boolean checkPrefix(String  freeunitid, String  prefixEC02){
//			String prefixConfig = prefixEC02;
//			String[] prefix = prefixConfig.split(",");
//			for(int i=0 ; i<prefix.length;i++){
//				if(freeunitid.startsWith(prefix[i])){
//					return true;
//				}
//			}
//			return false;
//
//		}
		
		private boolean checkPartlyfail(String  smessage){
			
			if(smessage.contains(PartlyfailSmessage.VALIDITY)
				|| smessage.contains(PartlyfailSmessage.BALANCE)
					|| smessage.contains(PartlyfailSmessage.BALANCEANDVALIDITY)
					   || smessage.contains(PartlyfailSmessage.FREEUNIT)){
				
				return true;
			}

			return false;
		}
		
		
		private void writeStatsValidity(EqlBsoAdjustCbsValidityResponse eqlRes,AbstractAF af,SmoiInstance smoiIns){
			int bsoSize = eqlRes.getBSONOListItem().size();
			for(int bsoIndex = 0 ; bsoIndex < bsoSize ; bsoIndex++){
				
				if(eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus() != null &&
						eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus().toUpperCase().trim().equals("SUCCESS")){
					
					if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("ADJUST_CBS_VALIDITY")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_VALIDITY_Response, smoiIns);
						
					}else if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("QUERY_CBS_SUB")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response, smoiIns);
					}
					
				}else{
					
					if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("ADJUST_CBS_VALIDITY")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_VALIDITY_Response_Error, smoiIns);
					}else if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("QUERY_CBS_SUB")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Error, smoiIns);
					}
					
				}
			}
		}
		
		private void writeStatsPrmmoneyAndFree(EqlBsoAdjustCbsResponse eqlRes,AbstractAF af,SmoiInstance smoiIns){
			int bsoSize = eqlRes.getBSONOListItem().size();
			for(int bsoIndex = 0 ; bsoIndex < bsoSize ; bsoIndex++){
				if(eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus() != null &&
						eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus().toUpperCase().trim().equals("SUCCESS")){
					
					if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("ADJUST_CBS_BALANCE")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
					}else if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("ADJUST_CBS_FREEUNIT")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response, smoiIns);
					}else if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("QUERY_CBS_SUB")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response, smoiIns);
					}
					
				}else{
					
					if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("ADJUST_CBS_BALANCE")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Error, smoiIns);
					}else if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("ADJUST_CBS_FREEUNIT")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Error, smoiIns);
					}else if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("QUERY_CBS_SUB")){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response_Error, smoiIns);
					}
				}
			}
		}
		

		
		private void writeStatsReceive(EqlBsoAdjustCbsResponse eqlRes,AbstractAF af,SmoiInstance smoiIns){
			int bsoSize = eqlRes.getBSONOListItem().size();
			for(int bsoIndex = 0 ; bsoIndex < bsoSize ; bsoIndex++){
				 if(smoiIns.getAdjustType().equals(AdjustType.FREEUNIT)){
					 
    				if(eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus() != null &&
    						eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus().toUpperCase().trim().equals("SUCCESS")){
    					
    					if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("ADJUST_CBS_FREEUNIT")){
    						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
    								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response, smoiIns);
    					}else if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("QUERY_CBS_SUB")){ 
    						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
    								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response, smoiIns);
    					}
    					
//							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
//									StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response, smoiIns);
					}else{
							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
									StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_FREEUNIT_Response_Error, smoiIns);
					}
					 
				 }else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE)){
					 
					if( eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus() != null &&
							eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus().toUpperCase().trim().equals("SUCCESS")){
						
						if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("ADJUST_CBS_BALANCE")){
    						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
    								StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
    					}else if(eqlRes.getBSONOListItem().get(bsoIndex).getBso().trim().equals("QUERY_CBS_SUB")){
    						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
    								StatAlarm.INGateway_Receive_EQL_BSO_QUERY_CBS_SUB_Response, smoiIns);
    					}
//							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
//									StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response, smoiIns);
					}else{
							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
									StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCE_Response_Error, smoiIns);
					}
					 
				 }else if(smoiIns.getAdjustType().equals(AdjustType.BALANCE_AND_VALIDITY)){
					 
					if(eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus() != null &&
							eqlRes.getBSONOListItem().get(bsoIndex).getBsoStatus().toUpperCase().trim().equals("SUCCESS")){
							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
									StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Response, smoiIns);
					}else{
							SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
									StatAlarm.INGateway_Receive_EQL_BSO_ADJUST_CBS_BALANCEANDVALIDITY_Response_Error, smoiIns);
					}
					 
				 }
				
			}
		}
	

//		private long adjCBSBalance(EqlBsoAdjustCbsResponse eqlRes, long newprmmoney, ArrayList<String> balanceIdList,
//				int bsonoIndex, String balTypeConf) {
//			int adjInfoListSize = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().size();
//			for (int adjInfoListIndex = 0; adjInfoListIndex < adjInfoListSize; adjInfoListIndex++) {
//				
//				Long newBalAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getNewBalanceAmount();
//				if(newBalAmount != null){
//					newprmmoney = newprmmoney + newBalAmount ;
//				}
//				
//				String balanceType = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getBalanceType();
//				if(balanceType != null 	&& balTypeConf.trim().equals(balanceType.trim())){
//					
//					String balanceId = eqlRes.getBSONOListItem().get(bsonoIndex).getAdjustmentinfoListItem().get(adjInfoListIndex).getBalanceId();
//					if( balanceId != null){
//						balanceIdList.add(balanceId);
//					}
//					
//				}
//			}
//			
//			
//			int balancelistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().size();
//			for (int balancelistIndex = 0; balancelistIndex < balancelistSize; balancelistIndex++) {
//				
//				int balanceresultlistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().size();
//				for (int balanceresultlistIndex = 0; balanceresultlistIndex < balanceresultlistSize; balanceresultlistIndex++) {
//					String balanceType =  eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceType();
//					
//					if( (balanceType != null) && (balTypeConf.trim().equals(balanceType.trim())) ){ // Condition 3
//						
//						int balancedetaillistSize = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().size();
//						for (int balancedetaillistIndex = 0; balancedetaillistIndex < balancedetaillistSize; balancedetaillistIndex++) {
//							
//							String balanceInsId = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().get(balancedetaillistIndex).getBalanceInsId();
//							String newBalAmount = eqlRes.getBSONOListItem().get(bsonoIndex).getBalanceListItem().get(balancelistIndex).getBalanceResultListItem().get(balanceresultlistIndex).getBalanceDetailItem().get(balancedetaillistIndex).getNewBalanceAmount();
//							
//							if(newBalAmount != null && balanceInsId != null && !balanceIdList.contains(balanceInsId)){
//								newprmmoney = newprmmoney + Long.parseLong(newBalAmount) ;	
//							}
//		
//						}
//					}
//				}
//			}
//			return newprmmoney;
//		}
		
		private <T> HashMap<String, String> mapHTTPError(SmoiInstance smoiIns,EqlBsoAdjustCbsResponse eqlRes,HashMap<String, String> hmMessage){
			boolean isSettingReturn = false;
			int firstResMatch = 0; // index response of first request.
			if(smoiIns.getEqlRequestInstance() != null) {
//				for(EQLRequestInstance eqlReq : smoiIns.getEqlRequestInstance()) {
				for(int j=0; j<smoiIns.getEqlRequestInstance().size(); j++){
					EQLRequestInstance eqlReq = smoiIns.getEqlRequestInstance().get(j);
					if(eqlRes.getBsono() != null) {
						for (int i = 0; i < eqlRes.getBSONOListItem().size(); i++) {
							BSONOBalanceAndFreeResponse bsono = eqlRes.getBSONOListItem().get(i);
							if(bsono.getBsoid() != null 
									&& bsono.getBso() != null 
									&& bsono.getBsoStatus() != null
									&& bsono.getBso().equals(eqlReq.getBsoName())
									&& bsono.getBsoid().equals(eqlReq.getBsoId())
									){
								if(j==0){ // save index value response of first request.
									firstResMatch = i;
								}
								if(!bsono.getBsoStatus().equalsIgnoreCase("SUCCESS")) {
									if(bsono.getBso().trim().equals("ADJUST_CBS_BALANCE")){
										hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
										isSettingReturn = true;
										break;
									}
									else if(bsono.getBso().trim().equals("ADJUST_CBS_FREEUNIT")){
										hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR);
										isSettingReturn = true;
										break;
									}else if(bsono.getBso().trim().equals("QUERY_CBS_SUB")){
										hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_ERROR);
										isSettingReturn = true;
										break;
									}
								} else if(bsono.getBsoStatus().equalsIgnoreCase("SUCCESS") && (eqlRes.getBSONOListItem().size()-1 == i) ) {
									
									bsono = eqlRes.getBSONOListItem().get(firstResMatch);
									if(bsono.getBso().trim().equals("ADJUST_CBS_BALANCE")){
										hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_BALANCE_RESPONSE_ERROR);
//										isSettingReturn = true;
										break;
									}
									else if(bsono.getBso().trim().equals("ADJUST_CBS_FREEUNIT")){
										hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_ADJUST_CBS_FREEUNIT_RESPONSE_ERROR);
//										isSettingReturn = true;
										break;
									}
									else if(bsono.getBso().trim().equals("QUERY_CBS_SUB")){
										hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_EQL_BSO_QUERY_CBS_SUB_RESPONSE_ERROR);
//										isSettingReturn = true;
										break;
									}
								}
								
							}
						}
					}
					if(isSettingReturn) {
						break;
					}
				}
			}
			return hmMessage;
		}
		
		private boolean customerStateInvalid(EqlBsoAdjustCbsResponse eqlRes,AbstractAF af){
			HashMap<String, ArrayList<String>> smoi_conf = af.getUtils().getHmWarmConfig();
			String stateConf = smoi_conf.get(Conf.avatar_modiPPSCreditLimit_Control_state).get(0);
			String[] confArray = stateConf.split(",");
			for(int i=0; i<confArray.length; i++){
				if(eqlRes.getBSONOListItem().get(0).getSubLifeCycle().getSubStatus().trim().equals(confArray[i].trim())){
						   // 1001
						return true;   
				}
			}
			return false;
		}
}
