package com.sushant.verma.common.applicationCacheLoader.action;

import java.util.Map;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.common.applicationCacheLoader.bll.ApplicationCacheLoaderInterface;

public class ReloadApplicationCache extends TimerTask{
	
	protected static Logger log=LogManager.getLogger(ReloadApplicationCache.class);
	
	private ServletContext servletContext;
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext; 
	}
	
	public void run(){
		log.info("@ ReloadApplicationCache run()");
		ApplicationCacheLoader cacheLoader=new ApplicationCacheLoader();
		log.debug("|__cacheLoader="+cacheLoader);
		Map<String,String> map= cacheLoader.getMap();
		log.debug("|__map="+map);
		ApplicationCacheLoaderInterface loadApplicationAttribute=cacheLoader.getApplicationCacheLoaderImplObj();
		log.debug("|__loadApplicationAttribute="+loadApplicationAttribute);

		log.debug("Cache object is reloaded");
		log.debug("Context Object is: "+this.servletContext);
		if (map==null)
		{ 
			log.debug("No application scope entries found.");
			return;
		}

		try{
			Map<String, Map<String,String>> valuemapAll=loadApplicationAttribute.getContextParameter(map);

			for (String entry: map.keySet()){
				Object valuemap=valuemapAll.get(entry);
				this.servletContext.setAttribute(entry, valuemap);
			}}
		catch (Exception e) {
			log.debug("Exception occured in cache loader: ",e);
		}finally{
			log.info("Cancelling Timer ");
			this.cancel();
		}

	}

}