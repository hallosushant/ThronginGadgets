package com.sushant.verma.common.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public final class IPRetriever extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074284335444963990L;
	private static Logger log = LogManager.getLogger(IPRetriever.class);
	
	public static String getLocalIPAddress() throws UnknownHostException{
		InetAddress thisIp =InetAddress.getLocalHost();
		log.debug("IP:"+thisIp.getHostAddress());
		return thisIp.getHostAddress();
	}
	
/*	public static String getIPAddress1(){
		HttpServletRequest req = request;
		HttpServletResponse response;
		log.debug("IP:"+request.getRemoteAddr());
		return request.getRemoteAddr();
	}
*/}
