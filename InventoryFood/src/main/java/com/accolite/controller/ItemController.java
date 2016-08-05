package com.accolite.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.model.Item;
import com.accolite.model.Summary;
import com.accolite.resources.Logging;
import com.accolite.service.ItemService;

@Controller
public class ItemController {
	final static Logger logger = Logger.getLogger(ItemController.class);
	@Autowired
	private ItemService itemService;

	// To display all items
	@RequestMapping(value = "/items/{visibility}", method = RequestMethod.GET)
	@ResponseBody
	public List<Item> getItems(@PathVariable("visibility") Integer visibility) {
		if(logger.isDebugEnabled()){
			logger.debug("Request for displaying all items is sent....");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("Request for displaying all items is sent....");
		}
		return itemService.getItems(visibility);
	}

	// To add a new item
	@RequestMapping(value = "/addItem/{itemName}/{typeId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void addItem(@PathVariable("itemName") String name, @PathVariable("typeId") Integer typeId) {
		//Log file generation
		if(logger.isDebugEnabled()){
			logger.debug("Request for adding item "+name+" is sent....");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("Request for adding item "+name+" is sent....");
		}
		
		itemService.addItem(name, typeId);
	}

	// To remove a item
	@RequestMapping(value = "/removeItem/{itemName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void removeItem(@PathVariable("itemName") String name) {
		if(logger.isDebugEnabled()){
			logger.debug("Request for removing item "+name+" is sent....");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("Request for removing item "+name+" is sent....");
		}
		itemService.removeItem(name);
	}

	// To add new item type
	@RequestMapping(value = "/addItemType/{typeName}/{subtypeName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void addItemType(@PathVariable("typeName") String type, @PathVariable("subtypeName") String subtype) {
		if(logger.isDebugEnabled()){
			logger.debug("Request for adding new item type "+subtype+" is sent....");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("Request for adding new item type "+subtype+" is sent....");
		}
		itemService.addItemType(type, subtype);
	}

	@RequestMapping(value = "/summary/{fromDate}/{toDate}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Summary> viewSummary(@PathVariable("fromDate") String startdate, @PathVariable("toDate") String enddate)
			throws ParseException {
		if(logger.isDebugEnabled()){
			logger.debug("Request for displaying summary is sent....");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("Request for displaying summary is sent....");
		}
		return itemService.viewSummary(startdate, enddate);
	}

}