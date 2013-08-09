/**
 * Contains All the commonly used Utility Classes
 * <p>eg: StringUtility 
 * @author Sushant Verma
 */
package com.sushant.verma.common.utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Contains frequently used functions related to String class
 * @author Sushant Verma
 * @version 1.0
 * @since 13th may 2011
 */
public class StringUtility {
	
	@SuppressWarnings("unused")
	private static Logger log = LogManager.getLogger(StringUtility.class);
	/**
	 * Static method to check if inputString is null or empty
	 * @param inputString
	 * @return true if inputString is either null or empty, else false
	 */
	public static boolean isNullBlank(String inputString){
		if(inputString==null || inputString.equalsIgnoreCase(""))
			return true;
		else 
			return false;			
	}
	/**
	 * Static method to check if inputString is null
	 * @param inputString
	 * @return true if inputString is null, else false
	 */
	public static boolean isNull(String inputString){
		if(inputString==null)
			return true;
		else 
			return false;			
	}
	/**
	 * Static method to check if inputString is empty
	 * @param inputString
	 * @return true if inputString is empty, else false
	 */
	public static boolean isBlank(String inputString){
		if(inputString.equalsIgnoreCase(""))
			return true;
		else 
			return false;			
	}
	/**
	 * Static method to check if inputString is null or empty
	 * @param inputString
	 * @return true if inputString is <b>NEITHER</b> null <b>NOR</b> empty, else false
	 */
	public static boolean isNotNullBlank(String inputString){
		if(!isNullBlank(inputString))
			return true;
		else 
			return false;			
	}
	/**
	 * Static method to check if inputString is null
	 * @param inputString
	 * @return true if inputString is <b>NOT</b> null, else false
	 */
	public static boolean isNotNull(String inputString){
		if(!isNull(inputString))
			return true;
		else 
			return false;			
	}
	/**
	 * Static method to check if inputString is empty
	 * @param inputString
	 * @return true if inputString is <b>NOT</b> empty, else false
	 */
	public static boolean isNotBlank(String inputString){
		if(!isBlank(inputString))
			return true;
		else 
			return false;			
	}
	/**
	 * Static method to check if String array is NOT empty and Null
	 * @param inputString
	 * @return true if any of the String array is <b>NEITHER</b> null <b>NOR</b> empty, else false
	 */
	public static boolean areNotNullBlank(String... args){
		boolean returnValue=false;
		for(String strings:args){
//			log.debug("|___strings=="+strings);
			if(isNotNull(strings) && isNotBlank(strings)){
//				log.info("if block: String != null and !=''");
				returnValue=true;
			}
			else{
//				log.info("else block :String == null OR =='', EXIT");
				returnValue=false;
				break;
			}
		}
		return returnValue;
	}

	/**
	 * Static method to check if String array is empty and Null
	 * @param inputString
	 * @return true if any of the String array is  null <b>OR</b> empty, else false
	 */
	public static boolean areNullBlank(String... args){
		boolean returnValue=false;
		for(String strings:args){
			if(isNull(strings) && isBlank(strings)){
				returnValue=true;
			}
			else{
				returnValue=false;
				break;
			}
		}
		return returnValue;
	}

}
