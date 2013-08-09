package com.sushant.verma.common.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ApplicationCache {

	protected static Logger log = LogManager.getLogger(ApplicationCache.class);
	private static Map<String,CacheObject> cache=new HashMap<String,CacheObject>();

	public static void addToCache(String key,Object value)
	{
		log.debug("Adding Key " + key + " to Cache Object"); 
		cache.put(key, new CacheObject(value));
	}
	public static Object getFromCache(String key)
	{
		CacheObject cacheobj=cache.get(key);
		if (cacheobj==null) 
		{
			log.debug("Cache Object is null for Key " + key);
			return null;
		}

		cacheobj.lastAccessedDate=new Date();
		log.debug("Returning  Key " + key + " Value " +  cacheobj.object + " from Cache"+" and cache size : "+cache.size());
		return cacheobj.object;
	}

}

