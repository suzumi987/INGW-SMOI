package phoebe.eqx.smoi.state;

import java.util.ArrayList;
import java.util.HashMap;

import phoebe.eqx.smoi.bean.MyAppData;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;
import phoebe.eqx.smoi.conf.Conf;
import phoebe.eqx.smoi.control.AFEvent;
import phoebe.eqx.smoi.control.AFState;
import phoebe.eqx.smoi.enums.ECommand;
import phoebe.eqx.smoi.enums.EResponseCode;
import phoebe.eqx.smoi.enums.StatAlarm;
import phoebe.eqx.smoi.message.builder.SendMessage;
import phoebe.eqx.smoi.message.soapmsg.MDResponse;
import phoebe.eqx.smoi.message.soapmsg.QueryResponseHeader;
import phoebe.eqx.smoi.utils.MappingMessage;
import phoebe.eqx.smoi.utils.SmoiStatAlarm;
import smoi.enums.EMsgTagType;


import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.interfaces.IAFState;

public class StateWaitMD implements IAFState {

	@Override
	public String doAction(AbstractAF af, Object instance,
			ArrayList<EquinoxRawData> rawDatas) {

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
				if (page.equals(ECommand.activatePPSSingSub.getCommand())) {
					hmMessage = genActivatePPSSingSub(af, smoiIns, r);
					outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
					log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
					log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
					SendMessage.Client(outputMsg, af, myAppData);
				} 
				else if(page.equals(ECommand.modiPPSCreditLimit.getCommand())){
					hmMessage = genModiPPSCreditLimit(af, smoiIns, r);
					outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
					log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
					log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
					SendMessage.Client(outputMsg, af, myAppData);
				}
				
				nextState = AFState.IDLE;

			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Error)) {

				if(page.equals(ECommand.activatePPSSingSub.getCommand())){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Error, smoiIns);	
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), 
							StatAlarm.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR, smoiIns);
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Error);
				}else if(page.equals(ECommand.modiPPSCreditLimit.getCommand())){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_ERROR, smoiIns);	
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), 
							StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR, smoiIns);
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_ERROR);
				}
				
				outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
				log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
				log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
				SendMessage.Client(outputMsg, af, myAppData);
				nextState = AFState.IDLE;
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Reject)) {

				if(page.equals(ECommand.activatePPSSingSub.getCommand())){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Reject, smoiIns);	
				}else if(page.equals(ECommand.modiPPSCreditLimit.getCommand())){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_REJECT, smoiIns);
				}
					
			
				int maxRetry = smoiIns.getResourceNameStandbyMaxRetry(Conf.resource_Name_Standby_MD_MaxRetry);
				if (maxRetry > 0) {
					// Standby_MaxRetry
					ec02.utils.Log.d("Retry: " + Conf.resource_Name_Standby_MD_MaxRetry + " maxRetry: " + maxRetry);
					maxRetry -= 1;
					smoiIns.setResourceNameStandbyMaxRetry(Conf.resource_Name_Standby_MD_MaxRetry, maxRetry);
					smoiIns.setRetry(true);

					// System.out.println("isRetry before send
					// "+myAppData.getSmoiIns().isRetry());
					MappingMessage messageFn = new MappingMessage(af);
					outputMsg = messageFn.CreateMessage(myAppData);
					String state = messageFn.getEState();
					inputMsg = "";

					if (state.equals(AFState.W_MD)) {
						SendMessage.MD(outputMsg, af, myAppData, Conf.resource_Name_Standby_MD);
					}
					nextState = state;
					if(page.equals(ECommand.activatePPSSingSub.getCommand())){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGATEWAY_SEND_MD_BSO_CBS_SUBSTATUS_REQUEST, smoiIns);
					}else if(page.equals(ECommand.modiPPSCreditLimit.getCommand())){
						SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
								StatAlarm.INGATEWAY_SEND_MD_BSO_CBS_NEGATIVE_REQUEST, smoiIns);
					}
					nextState = AFState.W_MD;

				} else {

					if(page.equals(ECommand.activatePPSSingSub.getCommand())){
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Reject);	
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR, smoiIns);
					}else if(page.equals(ECommand.modiPPSCreditLimit.getCommand())){
						hmMessage = mapEquinoxReturnCmd(
								EResponseCode.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_REJECT);	
						SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR, smoiIns);
					}
					outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
					log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
					log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
					SendMessage.Client(outputMsg, af, myAppData);

					nextState = AFState.IDLE;
				}
				
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Abourt)) {

				if(page.equals(ECommand.activatePPSSingSub.getCommand())){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Abort, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Abort);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR, smoiIns);
				}else if(page.equals(ECommand.modiPPSCreditLimit.getCommand())){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_ABORT, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_ABORT);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR, smoiIns);
				}
			
				outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
				log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
				log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
				SendMessage.Client(outputMsg, af, myAppData);
				nextState = AFState.IDLE;
			} else if (r.getRawEventType().equals(AFEvent.IncomingEquinox_Timeout)) {

				if(page.equals(ECommand.activatePPSSingSub.getCommand())){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Timeout, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Timeout);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR, smoiIns);
				}else if(page.equals(ECommand.modiPPSCreditLimit.getCommand())){
					SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
							StatAlarm.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_TIMEOUT, smoiIns);	
					hmMessage = mapEquinoxReturnCmd(
							EResponseCode.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_TIMEOUT);
					SmoiStatAlarm.incrementStats(af, smoiIns.getPage(), StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR, smoiIns);
				}
			
				outputMsg = hmMessage.get(EMsgTagType.RESPONSE_MESSAGE.getDisplayName());
				log_RESULTCODE = hmMessage.get(EMsgTagType.RESPONSE_LOG_RES.getDisplayName());
				log_DESC = hmMessage.get(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName());
				SendMessage.Client(outputMsg, af, myAppData);
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
	
	private HashMap<String, String> genActivatePPSSingSub(AbstractAF af, SmoiInstance smoiIns, EquinoxRawData r) {
		HashMap<String, String> hmMessage;
		QueryResponseHeader queryResponseHeader = new QueryResponseHeader();
		MDResponse resMD = queryResponseHeader.readResponseMD(r.getRawDataMessage());
		
		if (resMD != null && resMD.getStatus().trim().equals("9")){
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS, smoiIns);
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGATEWAY_RECEIVE_MD_BSO_CBS_SUBSTATUS_RESPONSE, smoiIns);
			hmMessage = mapEquinoxReturnCmd(EResponseCode.ACTIVATING_SUCCESS);
			
		}else {
			
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Error, smoiIns);
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR, smoiIns);
			
			if(resMD.getSMESSAGE_1() != null && resMD.getSMESSAGE_1().trim().contains("118030219")){
				hmMessage =  mapEquinoxReturnCmd(EResponseCode.INGateway_Receive_MD_ALREADY_ACTIVE);
			}else{
				hmMessage = mapEquinoxReturnCmd(EResponseCode.INGateway_Receive_MD_BSO_CBS_SUBSTATUS_Response_Error);	
			}
		}
		return hmMessage;
	}
	
	private HashMap<String, String> genModiPPSCreditLimit(AbstractAF af, SmoiInstance smoiIns, EquinoxRawData r) {
		HashMap<String, String> hmMessage;
		
		QueryResponseHeader queryResponseHeader = new QueryResponseHeader();
		MDResponse resMD = queryResponseHeader.readResponseMD(r.getRawDataMessage());
		if (resMD != null && resMD.getStatus().trim().equals("9")){
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_SUCCESS, smoiIns);
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE, smoiIns);
			hmMessage = mapEquinoxReturnCmd(EResponseCode.MODIFIED_NAGATIVE_SUCCESS);
			
		}else {
			
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_ERROR, smoiIns);
			SmoiStatAlarm.incrementStats(af, smoiIns.getMapCmd(),
					StatAlarm.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR, smoiIns);

			if(resMD.getSMESSAGE_1() == null && resMD.getStatus().trim() == null ){
				hmMessage = mapEquinoxReturnCmd(EResponseCode.MODIFIED_NAGATIVE_SUCCESS);
			}else if(resMD.getStatus().trim() == null){
				hmMessage = mapEquinoxReturnCmd(EResponseCode.INGATEWAY_RECEIVE_MD_BSO_CBS_NEGATIVE_RESPONSE_ERROR);
			}else if(resMD.getSMESSAGE_1() == null){
				String log_RESULTCODE = resMD.getStatus().trim();
				String log_DESC ="";
				String msg = "<vcrr><res>" + log_RESULTCODE + "</res><desc>" + "</desc></vcrr>";
				
				hmMessage = new HashMap<String, String>();
				hmMessage.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), msg);
				hmMessage.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
				hmMessage.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
			}else{
				String log_RESULTCODE = resMD.getStatus().trim();
				String log_DESC = resMD.getSMESSAGE_1().trim();
				String msg = "<vcrr><res>" + log_RESULTCODE + "</res><desc>" + log_DESC + "</desc></vcrr>";
				
				hmMessage = new HashMap<String, String>();
				hmMessage.put(EMsgTagType.RESPONSE_MESSAGE.getDisplayName(), msg);
				hmMessage.put(EMsgTagType.RESPONSE_LOG_RES.getDisplayName(), log_RESULTCODE);
				hmMessage.put(EMsgTagType.RESPONSE_LOG_DESC.getDisplayName(), log_DESC);
			}
		}
			
		
		return hmMessage;
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

}
