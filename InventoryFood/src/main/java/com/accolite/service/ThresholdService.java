/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/
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

import com.accolite.dao.ThresholdDAO;
import com.accolite.model.Threshold;
import com.sun.mail.smtp.SMTPTransport;

@Service
public class ThresholdService {
	@Autowired
	private ThresholdDAO thresholddao;

	/**
	 * Gets the items below threshold.
	 *
	 * @return the items below threshold
	 */
	// To display list of items whose quantity are less than threshold
	@SuppressWarnings("restriction")
	public void getItemsBelowThreshold() {

		List<Threshold> list = thresholddao.getItemsBelowThreshold();

		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

			// Get a Properties object
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtps.host", "smtp.gmail.com");
			properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			properties.setProperty("mail.smtp.socketFactory.fallback", "false");
			properties.setProperty("mail.smtp.port", "465");
			properties.setProperty("mail.smtp.socketFactory.port", "465");
			properties.setProperty("mail.smtps.auth", "true");
			properties.put("mail.smtps.quitwait", "false");

			Session session = Session.getInstance(properties, null);
			// -- Create a new message --
			final MimeMessage msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("momin.yadav@accoliteindia.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("momin.yadav@accoliteindia.com", false));
			String ccEmail = "loghithavani@gmail.com,vlowkya11@gmail.com,pawan.prakash@accoliteindia.com,momin.yadav@accoliteindia.com";

			if (ccEmail.length() > 0) {
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
			}

			String message = "Please note that the following items below threshold and ensure that you add them\n.";
			int i;
			if (list.size() > 0) {
				for (i = 0; i < list.size(); i++) {
					message = message + Integer.toString(list.get(i).getItemId()) + " " + (list.get(i).getItemName());
					message = message + "\n";
				}
				message = message + "Thanks. \nTeam Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav";
				msg.setSubject("Items below threshold");
				msg.setText(message, "utf-8");
				msg.setSentDate(new Date());

				SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

				t.connect("smtp.gmail.com", "momin.yadav@accoliteindia.com", "momingoria");
				t.sendMessage(msg, msg.getAllRecipients());
				t.close();
			}
		} catch (AddressException e) {
			e.printStackTrace();
			// ...
		} catch (MessagingException e) {
			e.printStackTrace();
			// ...
		}

	}
}