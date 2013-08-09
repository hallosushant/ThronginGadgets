package com.sushant.verma.common.dbConnect;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.common.utility.CommonUtility;

public class DbConnectPropLoader {

	Logger log=LogManager.getLogger(DbConnectPropLoader.class);
	
    public String userName;
    public String password;

    public DbConnectPropLoader(String userName,String password) throws Exception{
    	log.debug("Constructor DbConnectPropLoader |userName="+userName+" | password="+password);
    	log.debug("calling getDecWord for userName="+userName);
    	this.userName=CommonUtility.getDecWord(userName);
    	log.debug("calling getDecWord for password="+password);
    	this.password=CommonUtility.getDecWord(password);
    	log.debug("Constructor DbConnectPropLoader |this.userName="+this.userName+" | this.password="+this.password);
    }
    
    
    
    /*
     * Getter & Setters
     */
    
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
