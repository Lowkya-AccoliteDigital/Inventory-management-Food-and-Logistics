/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/
package com.accolite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.service.EmailExpiryService;

/**
 * The Class EmailExpiryController.
 */
@Controller
public class EmailExpiryController {

	@Autowired
	private EmailExpiryService emailexpiryservice;

	// To get the list of expired items
	@RequestMapping(value = "/email/expiry", method = RequestMethod.GET)
	@ResponseBody
	public void getItemsExpired() {
		emailexpiryservice.getItemsExpired();
	}
}