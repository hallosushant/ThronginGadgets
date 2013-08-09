package com.sushant.verma.common.applicationCacheLoader.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.applicationCacheLoader.bll.ApplicationCacheLoaderInterface;

public class ApplicationCacheLoader extends BaseAction implements ServletContextListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7421589299732801066L;
	protected static Logger log=LogManager.getLogger(ApplicationCacheLoader.class);

	private static final int START_HOUR = 23;
	private static final  int START_MINUTE = 59;
//	private static final  long DELAY_MILLISEC = 60*60*1000 ; //1 minute
	private Timer chacheReloadrerTimer=new Timer("Chache Reloadrer Timer",false);
	private static Map<String,String> map;
	private static ApplicationCacheLoaderInterface applicationCacheLoaderImplObj;
	private ReloadApplicationCache rc;
	
	public ApplicationCacheLoader(){
		super();
		log.info(" ! ApplicationCacheLoader instantiated");

	}


	public void contextInitialized(ServletContextEvent event) {
		/* This method is called when the servlet context is 
		 * initialized(when the Web Application is deployed).          
		 * You can initialize servlet context related data here.*/     
		Date time=new Date();
		log.debug("Context Intializing, starting to load application cache at: "+time);
		
		ServletContext sc=event.getServletContext();
		log.debug("Context Object is: "+sc);
		
		if (map==null)
		{ 
			log.info("No application scope entries found.");
			return;
		}

		rc = new ReloadApplicationCache();
		rc.setServletContext(sc);
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR, START_HOUR);
		cal.set(Calendar.MINUTE, START_MINUTE);
		/**
		 * new Date() to be replaced by cal, if  CacheReLoading needs to be started at START_HOUR:START_MINUTE
		 * Else if new Date() to be used, the CacheReLoading will be started as soon as Server Starts
		 * 
		 * DELAY_MILLISEC will reload the cache after specified time
		 */
		//chacheReloadrerTimer.schedule(rc, new Date(), DELAY_MILLISEC);
		chacheReloadrerTimer.schedule(rc, new Date());
		
		log.debug("Context Intialized, end of loading application cache at: "+new Date());
		log.debug("Time taken to load cache: "+(new Date().getTime()-time.getTime())+" ms");


		
	}

	public void contextDestroyed(ServletContextEvent event) { 
		/* This method is invoked when the Servlet Context  
		 *         (the Web Application) is undeployed WebLogic Server shuts down.*/			        
		log.debug("Application Context Destroyed");
		rc.cancel();
		chacheReloadrerTimer.cancel();
		chacheReloadrerTimer.purge();
		log.debug("Chache Reloadrer Timer cancelled");
	}


	public Map<String, String> getMap() {
		return map;
	}

	@SuppressWarnings("static-access")
	public void setMap(Map<String, String> map) {
		log.debug("set map entered, map="+map);
		this.map = map;
	}

	public ApplicationCacheLoaderInterface getApplicationCacheLoaderImplObj() {
		return applicationCacheLoaderImplObj;
	}


	@SuppressWarnings("static-access")
	public void setApplicationCacheLoaderImplObj(
			ApplicationCacheLoaderInterface applicationCacheLoaderImplObj) {
		this.applicationCacheLoaderImplObj = applicationCacheLoaderImplObj;
	}


}