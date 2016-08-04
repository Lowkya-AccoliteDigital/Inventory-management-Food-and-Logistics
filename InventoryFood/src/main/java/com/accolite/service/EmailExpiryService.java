package com.accolite.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.EmailExpiryDAO;
import com.accolite.model.EmailExpiry;

@Service
public class EmailExpiryService {

	@Autowired
	private EmailExpiryDAO emailexpirydao;

	public void getItemsExpired() {
		
	List<EmailExpiry> expiryList=emailexpirydao.getItemsExpired();
	
	Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    try {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("momin.yadav@accoliteindia.com", "Momin Yadav"));
      msg.addRecipient(Message.RecipientType.TO,
                       new InternetAddress("momin.yadav@accoliteindia.com", "Admin"));
      msg.setSubject("Regarding inventory expiry");
      String str=null;
      int i;
      for(i=0;i<expiryList.size();i++)
      {
      str=str+Integer.toString(expiryList.get(i).getItemID())+" "+(expiryList.get(i).getItemName());
      str=str+"\n"; 
      }
      msg.setContent(str,"text/plain");
      Transport.send(msg);
    } catch (AddressException e) {
    	e.printStackTrace();
      // ...
    } catch (MessagingException e) {
    	e.printStackTrace();
      // ...
    } catch (UnsupportedEncodingException e) {
    	e.printStackTrace();
      // ...
    }
		
	}

}
