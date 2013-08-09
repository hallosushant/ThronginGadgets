package com.sushant.verma.common.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.login.LoginConstants;
import com.thoughtworks.xstream.XStream;




public class CommonUtility {
	private static Logger log=LogManager.getLogger(CommonUtility.class);
	public static String getDTOasXML(Object dto){
		XStream xstream = new XStream();
		String xmlString= xstream.toXML(dto);
		System.out.println(xmlString);
		return xmlString;
	}
	
	public static Object getCacheDetail(String cacheKey,Object result){
		Object cacheObj=ApplicationCache.getFromCache(cacheKey);
		if(null==cacheObj){
			cacheObj=result;
			ApplicationCache.addToCache(cacheKey, cacheObj);
		}
		return cacheObj;
	}
	
	public static final int getRandomNumberInRange(int minValue,int maxValue){
		int randomNumber =minValue + (int)(Math.random() * ((maxValue - minValue) + 1));
		return randomNumber;
	}
	
	public static final String getRandomKey(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*";
		String key = "";
		for(int i = 0; i < length; i++) {
			int index = (int) Math.floor(Math.random() * 62);
			key += chars.charAt(index);
		}

		return key;
	}
	
	
	
	static class StringList extends ArrayList<String> {
		private static final long serialVersionUID = 1L;
	}  
	static class SublistMap extends HashMap<String, StringList> {
		private static final long serialVersionUID = 1L;
	}  
	/**
	 * Creates a Random wordlist of size 5000
	 */
	public static void generateRandomWordList(){
		StringList list = new StringList();   
		for(long i = 0; i < 5000; i++){
			String a=getRandomKey(8);
			System.out.print("\""+a+"\",");
			list.add(a);
		}   
	    System.out.println("\nListSize=="+list.size());
	    SublistMap map = new SublistMap();  
	    for (String item : list) { // all items in list  
	        StringList subList = map.get(item); // get sublist for specific item  
	        if (subList == null) { // sublist not in map   
	            subList = new StringList();  
	            map.put(item, subList); // add a new sublist  
	        }  
	        subList.add(item); // add item to selected sublist  
	    }  
	        
	    int i=0;
	    for (StringList subList : map.values()) { // print result
	    	
	    	if(subList.size()>1){
	    		System.out.print(i);
	    		System.out.print(") ");
	        System.out.println(subList);
	    	i++;
	    	}
	    }
	}
	
	public static String getEncWord(String plainWord) throws Exception{
		log.debug("|__ plainWord="+plainWord); 
		int keyWordSize=Integer.valueOf(getCacheDetail("CONFIRM_REGISTRATION_KEYWORD_SIZE",LoginConstants.CONFIRM_REGISTRATION_KEYWORD_SIZE).toString());
		int randomIndex=CommonUtility.getRandomNumberInRange(1,keyWordSize-1);
		log.debug("|__ randomIndex="+randomIndex);
		String confirmKeyword=LoginConstants.CONFIRM_REGISTRATION_KEYWORD.get(randomIndex);
		log.debug("|__ confirmKeyword="+confirmKeyword);
		String encWord=ZEncrypt.encryptAES(plainWord+"&"+confirmKeyword);
		log.debug("|__ encWord="+encWord);
		return encWord;
	}
	
	@SuppressWarnings("unchecked")
	public static String getDecWord(String encWord) throws Exception{
		log.debug("|__ encWord="+encWord); 
		String responseDecrypted=ZEncrypt.decryptAES(encWord);
		String[] splits=responseDecrypted.split("&");
		String decword=splits[0];
		String confirmKeyword=splits[1];
		List<String> confirmRegistrationKeyword=(List<String>) CommonUtility.getCacheDetail("CONFIRM_REGISTRATION_KEYWORD",LoginConstants.CONFIRM_REGISTRATION_KEYWORD);
		if(confirmRegistrationKeyword.contains(confirmKeyword)){
			log.debug("|__ decword="+decword+" |__ confirmKeyword="+confirmKeyword);
			return decword;
		}
		log.error("confirmKeyword does not match returning null");
		return null;
		
	}
	
	public static void main(String[] args) throws Exception {
//		generateRandomWordList();
		System.out.println("userName="+getEncWord("guest"));
//		System.out.println("userName="+getEncWord("throngin_admin"));
//		System.out.println("password="+getEncWord("bmts@2281"));
//		System.out.println("adminPwd="+getEncWord("cheena@21march"));
//		System.out.println("userNme=="+getDecWord("o1OysEdWNQt1JVshDrlwH3oVNWU38T67rJGXmVOKSuI="));
//		System.out.println("pwd=="+getDecWord("U8gRjUYXaiM4CK0hP4S7Bko7l47wBiJHfOyPuGHT/Q0="));
	}
}
