package com.accolite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.model.EmailExpiry;
import com.accolite.service.EmailExpiryService;

@Controller
public class EmailExpiryController {

	@Autowired
	private EmailExpiryService emailexpiryservice; 
	// To get the list of expired items
	@RequestMapping(value = "/email/expiry", method = RequestMethod.GET)
	@ResponseBody
	public List<EmailExpiry> getItemsExpired() {
		return emailexpiryservice.getItemsExpired();
	}
}