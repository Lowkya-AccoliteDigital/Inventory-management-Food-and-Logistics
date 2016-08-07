package com.accolite.service;

import java.security.Security;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.EmailExpiryDAO;
import com.accolite.dao.ThresholdDAO;
import com.accolite.model.EmailExpiry;
import com.accolite.model.Threshold;
import com.sun.mail.smtp.SMTPTransport;

@Service
public class ThresholdService {
	@Autowired
	private ThresholdDAO thresholddao;
	@SuppressWarnings("restriction")
	public void getItemsBelowThreshold() {

		List<Threshold> list = thresholddao.getItemsBelowThreshold();

//		Properties props = new Properties();
//		Session session = Session.getDefaultInstance(props, null);

		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	        // Get a Properties object
	        Properties properties= System.getProperties();
	        properties.setProperty("mail.smtps.host", "smtp.gmail.com");
	        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	        properties.setProperty("mail.smtp.port", "465");
	        properties.setProperty("mail.smtp.socketFactory.port", "465");
	        properties.setProperty("mail.smtps.auth", "true");

	        /*
	        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
	        to true (the default), causes the transport to wait for the response to the QUIT command.
	        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
	                http://forum.java.sun.com/thread.jspa?threadID=5205249
	                smtpsend.java - demo program from javamail
	        */
	        properties.put("mail.smtps.quitwait", "false");

	        Session session = Session.getInstance(properties, null);

	        // -- Create a new message --
	        final MimeMessage msg = new MimeMessage(session);

	        // -- Set the FROM and TO fields --
	        msg.setFrom(new InternetAddress("loghithavani@gmail.com"));
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("loghithavani@gmail.com", false));

	        String ccEmail="loghithavan.sundaramoorthi@accoliteindia.com";
	        
	        if (ccEmail.length() > 0) {
	            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
	        }

			String message = "";
			int i;
			for (i = 0; i < list.size(); i++) {
				message = message + Integer.toString(list.get(i).getItemId()) + " " + (list.get(i).getItemName());
				message = message + "\n";
			}
			
	        msg.setSubject("Items below threshold");
	        msg.setText(message, "utf-8");
	        msg.setSentDate(new Date());

	        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

	        t.connect("smtp.gmail.com", "momin.yadav@accoliteindia.com", "momingoria");
	        t.sendMessage(msg, msg.getAllRecipients());      
	        t.close();
	    }
//			Message msg = new MimeMessage(session);
//			msg.setFrom(new InternetAddress("momin.yadav@accoliteindia.com", "Momin Yadav"));
//			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("momin.yadav@accoliteindia.com", "Admin"));
//			msg.setSubject("Regarding inventory expiry");

			/*msg.setContent(str, "text/plain");
			Transport.send(msg);*/
		 catch (AddressException e) {
			e.printStackTrace();
			// ...
		} catch (MessagingException e) {
			e.printStackTrace();
			// ...
		}

	}
}
