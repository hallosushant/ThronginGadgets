package com.sushant.verma.common.ResourceLoader;


import java.io.InputStream;
import java.io.File;
import java.net.URL;


/**
 * Factory for all resources present in /WEB-INF/classes/*.properties directory
 * <LI>File</Li>
 * <Li>Property Information</Li>
 *  * 
 * @version 1.0
 * @since 
 */
public class ResourceLoader{

	/**
	 * Get named resource as InputStream present in /WEB-INF/classes/*.properties
	 * <Br> directory
	 * 
	 * @param resourceName Name of the resource
	 * @return InputStream for resource
	 * @since 
	 */
	public static InputStream getResourceAsStream(String resourceName){
		return  ResourceLoader.class.getResourceAsStream(resourceName);
	}

	/**
	 * Get named resource as URL present in /WEB-INF/classes/*.properties directory
	 * 
	 * @param resourceName name of resource
	 * @return URL object
	 * @since 
	 */
	public static URL getResourceAsURL(String resourceName){
		return ResourceLoader.class.getResource(resourceName);
	}

	/**
	 * Get named resource as absolute file name
	 * 
	 * @param resourceName name of resource
	 * @return String absolute file path name
	 * @since 
	 */
	public static String getResourceAsFileName(String resourceName){
		return getResourceAsURL(resourceName).getFile();
	}



	/**
	 * Get named resource as File object
	 * 
	 * @param resourceName name of resource
	 * @return File object
	 * @since 
	 */
	public static File getResourceAsFile(String resourceName){
		return  new File(getResourceAsURL(resourceName).getFile());
	}
}
