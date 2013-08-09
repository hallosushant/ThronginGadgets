package com.sushant.verma.common.ResourceLoader;

import java.io.InputStream;
import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Factory for all resources present in src/com/Resources resource directory
 * <LI>File</Li>
 * <Li>Property Information</Li>
 *
 * @version 1.0
 * @since 
 */
public class ResourceFactory {
	private static final Logger log=LogManager.getLogger(ResourceFactory.class);
	/**
	 * Property object containing Application property
	 */
	private Properties props = null;

	/**
	 * Constructor
	 */
	public ResourceFactory(String resourceFile) {
		loadResource(resourceFile);
	}

	/**
	 * Get named resource as InputStream present in /WEB-INF/classes/Resources/WordList.properties
	 * <Br> directory
	 * 
	 * @param resourceName Name of the resource
	 * @return InputStream for resource
	 * @since 
	 */
	public static InputStream getResourceAsStream(String resourceName){
		log.debug("resourceName=="+resourceName);
		InputStream in = ResourceLoader.getResourceAsStream(resourceName);
		log.debug("in=="+in);
		if(in == null) {
			//don't log in this file, Do not use Log class
			getResourceAsFileName(resourceName);
		}
		return in;
	}

	/**
	 * Get named resource as URL present in /WEB-INF/classes/Resources/WordList.properties
	 * 
	 * @param resourceName name of resource
	 * @return URL object
	 * @since 
	 */
	public static URL getResourceAsURL(String resourceName){

		URL url = ResourceLoader.getResourceAsURL(resourceName);
		if(url == null) {
			//don't log in this file, Do not use Log class
			getResourceAsFileName(resourceName);
		}
		return url;
	}

	/**
	 * Get named resource as absolute file name
	 * 
	 * @param resourceName name of resource
	 * @return String absolute file path name
	 * @since 
	 */
	public static String getResourceAsFileName(String resourceName){
		File file = ResourceLoader.getResourceAsFile(resourceName);
		if(!file.exists()) {
			//don't log in this file, Do not use Log class
			log.debug("Requested resource not found at "+file.getAbsolutePath());
		}
		String fileName=file.getAbsolutePath();
		return fileName;
	}


	/**
	 * Get named resource as File object
	 * 
	 * @param resourceName name of resource
	 * @return File object
	 * @since 
	 */
	public static File getResourceAsFile(String resourceName){
		File file = ResourceLoader.getResourceAsFile(resourceName);
		if(!file.exists()) {
			//don't log in this file, Do not use Log class
			getResourceAsFileName(resourceName);
		}
		return file;
	}


	/**
	 * Get named property, present in "psp.properties" file
	 * <br>in /WEB-INF/classes/psp.properties directory
	 * 
	 * @param name name of the property
	 * @return String property name
	 * @since 
	 */
	public String getProperty(String name) {
		/*
   if(props == null) {
            loadResource();
        }
		 */
		return (String)props.getProperty(name);
	}


	/**
	 * load property resource
	 * 
	 * @since 
	 */

	private void loadResource(String resourceFile) 
	{
		// Set up to load the property resource for this locale key, if we can
		if(props == null) {
			props =  new Properties();
		}else {
			props.clear();
		}
		InputStream is = null;
		// Load the specified property resource
		try {
			is = getResourceAsStream(resourceFile);
			log.debug("Properties **************"+is);
			if (is != null) {
				props.load(is);
				log.debug("Properties loaded**************"+props);
				is.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			if (is != null) {
				try {
					is.close();
				} catch (Throwable u) {
					;
				}
			}
		}
	}


}


