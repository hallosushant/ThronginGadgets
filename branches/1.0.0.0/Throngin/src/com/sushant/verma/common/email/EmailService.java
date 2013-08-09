package com.sushant.verma.common.email;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.common.utility.CommonUtility;

public class EmailService implements EmailServiceInterface{
	private static Logger log=LogManager.getLogger(EmailService.class);
	private String adminPwd;
	
	public void sendEmail(HashMap<String,Object> emailMap) throws AddressException, MessagingException{
		log.info("--creating new AsyncEmailSend  with emailMap="+emailMap);
		new AsyncEmailSend(emailMap);
	}

	class AsyncEmailSend extends Thread{
		private HashMap<String,Object> emailMap;
		AsyncEmailSend(HashMap<String,Object> emailMap){
			log.info("AsyncEmailSend Constructor");
			this.emailMap=emailMap;
			new Thread(this,"AsyncEmailSend").start();
		}

		@Override
		public void run() {
			try {
				log.info("--calling sendAsyncEmail--");
				sendAsyncEmail(emailMap);
			} catch (AddressException e) {
				log.error("AddressException, exception=="+e);
			} catch (MessagingException e) {
				log.error("MessagingException, exception=="+e);
			}
		}

		public void sendAsyncEmail(HashMap<String,Object> emailMap) throws AddressException, MessagingException{
			log.info("sendAsyncEmail-- | emailMap="+emailMap);

			String fromEmail=(String) emailMap.get("FROM_EMAIL");
			String[] toEmail=(String[]) emailMap.get("TO_EMAIL");
			String emailSubject=(String) emailMap.get("SUBJECT_EMAIL");
			String emailBody=(String) emailMap.get("BODY_EMAIL");

			Properties prop = System.getProperties();
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", EmailConstants.HOST_ADDRESS_SMTP);
			prop.put("mail.smtp.user", fromEmail);
			prop.put("mail.smtp.password",adminPwd);
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.connectiontimeout", EmailConstants.CONNECTION_TIMEOUT_MSEC);
			prop.put("mail.smtp.timeout", EmailConstants.IO_TIMEOUT_MSEC);
			String[] to = toEmail;

			Session session = Session.getDefaultInstance(prop, null);
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromEmail));

			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for( int i=0; i < to.length; i++ ) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int j=0; j < toAddress.length; j++) { // changed from a while loop
				msg.addRecipient(Message.RecipientType.TO, toAddress[j]);
			}
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(EmailConstants.SUPPORT_THRONGIN_EMAIL_ID));
			msg.setSubject(emailSubject);
			//  msg.setText("Welcome to JavaMail");

			// alternately, to send HTML mail:
			msg.setContent(emailBody, "text/html");

			Transport transport = session.getTransport("smtp");
			transport.connect(EmailConstants.HOST_ADDRESS_SMTP, fromEmail, adminPwd);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

		}
	}
	
	
	
	/*
	 * Getters & Setters
	 * 
	 */
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) throws Exception {
		this.adminPwd = CommonUtility.getDecWord(adminPwd);
	}
}
