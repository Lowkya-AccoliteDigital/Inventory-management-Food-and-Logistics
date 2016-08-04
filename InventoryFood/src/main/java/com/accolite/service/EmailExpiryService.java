package com.accolite.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.EmailExpiryDAO;
import com.accolite.dao.ItemDAO;
import com.accolite.model.EmailExpiry;
import com.accolite.model.Item;
import com.accolite.model.Summary;

@Service
public class EmailExpiryService {

	@Autowired
	private EmailExpiryDAO emailexpirydao;

	public static List<EmailExpiry> getItemsExpired() {
		// TODO Auto-generated method stub
		return null;
	}

	
}