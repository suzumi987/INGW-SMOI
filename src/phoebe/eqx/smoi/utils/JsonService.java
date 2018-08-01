package phoebe.eqx.smoi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;


public class JsonService {
	
	private final static JsonService service = new JsonService();
	
	private final static Gson BASIC_GSON = new Gson();
	private final static Gson DISABLEHTML_GSON = new GsonBuilder()
		.disableHtmlEscaping()
		.create();
	private final static Gson DETAIL_LOG_GSON = new GsonBuilder()
		.excludeFieldsWithoutExposeAnnotation()
		.disableHtmlEscaping()
//		.serializeNulls()
		.create();

	private JsonService() {
	}
	
	public static JsonService getInstance(){
		return service;
	}

	public SmoiInstance decodeInstance( String instance ){
		return BASIC_GSON.fromJson( instance, SmoiInstance.class);
	}

	public String encodeInstance( SmoiInstance instance ){
		return BASIC_GSON.toJson(instance);
	}



}
