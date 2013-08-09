package com.sushant.verma.common.email;

import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


public interface EmailServiceInterface {
	public void sendEmail(HashMap<String,Object> emailMap) throws AddressException, MessagingException;
}
