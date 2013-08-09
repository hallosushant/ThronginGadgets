package com.sushant.verma.common.applicationCacheLoader.bll;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.common.applicationCacheLoader.dao.ApplicationCacheLoaderDao;


public class ApplicationCacheLoaderImpl implements ApplicationCacheLoaderInterface{

	protected static Logger log=LogManager.getLogger(ApplicationCacheLoaderImpl.class);
	private ApplicationCacheLoaderDao applicationCacheLoaderDaoObj;
	
	public Map<String, String> getContextParameter(String pquery) {
		return applicationCacheLoaderDaoObj.getContextParameter(pquery);
	}
	

	public Map<String, Map<String, String>> getContextParameter(Map<String, String> CacheMap) {
		HashMap<String,Map<String,String>> cache=new HashMap<String,Map<String,String>>();
		
		String exception=null;
		for (String entry: CacheMap.keySet()){
			try{
				String query=CacheMap.get(entry);
				log.debug("|__query="+query);
				cache.put(entry, getContextParameter(query));
			}
			catch(Exception e){
				exception="Key: "+entry+" Query: "+CacheMap.get(entry) +"\n";
				log.error("Exception in cache loading",e);
			}
		}
		if (exception==null) return cache;
		else throw new RuntimeException(exception);
	}


	public ApplicationCacheLoaderDao getApplicationCacheLoaderDaoObj() {
		return applicationCacheLoaderDaoObj;
	}


	public void setApplicationCacheLoaderDaoObj(
			ApplicationCacheLoaderDao applicationCacheLoaderDaoObj) {
		this.applicationCacheLoaderDaoObj = applicationCacheLoaderDaoObj;
	}
	
}
