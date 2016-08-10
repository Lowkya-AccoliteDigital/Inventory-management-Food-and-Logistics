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

import com.accolite.service.ThresholdService;

@Controller
public class ThresholdController {
	@Autowired
	private ThresholdService thresholdservice;

	/**
	 * Gets the items below threshold.
	 *
	 * @return the items below threshold
	 */
	// To check if items went below threshold
	@RequestMapping(value = "/threshold", method = RequestMethod.GET)
	@ResponseBody
	public void getItemsBelowThreshold() {
		thresholdservice.getItemsBelowThreshold();
	}
}