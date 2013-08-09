package com.sushant.verma.common.applicationCacheLoader.bll;

import java.util.Map;

public interface ApplicationCacheLoaderInterface {

	public Map<String,String> getContextParameter(String pquery);
	public Map<String, Map<String,String>> getContextParameter(Map <String,String> CacheMap);
}

