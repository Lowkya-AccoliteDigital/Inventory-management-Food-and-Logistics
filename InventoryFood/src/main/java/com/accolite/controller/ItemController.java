/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/

package com.accolite.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.model.Item;
import com.accolite.model.ItemCollection;
import com.accolite.model.ItemsByLocation;
import com.accolite.model.Summary;
import com.accolite.model.UrlParameter;
import com.accolite.model.ViewSummary;
import com.accolite.service.ItemService;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemController.
 */
@Controller
public class ItemController {

	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(ItemController.class);

	/** The item service. */
	@Autowired
	private ItemService itemService;

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	// To display all items
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@ResponseBody
	public List<Item> getItems() {
		if (logger.isDebugEnabled()) {
			logger.debug("Request for displaying all items is sent....");
		}

		if (logger.isInfoEnabled()) {
			logger.info("Request for displaying all items is sent....");
		}
		return itemService.getItems();
	}

	/**
	 * Adds the item type.
	 *
	 * @param type
	 *            the type
	 * @param subtype
	 *            the subtype
	 */
	// To add new item type
	@RequestMapping(value = "/addItemType/{typeName}/{subtypeName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void addItemType(@PathVariable("typeName") String type, @PathVariable("subtypeName") String subtype) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request for adding new item type " + subtype + " is sent....");
		}

		if (logger.isInfoEnabled()) {
			logger.info("Request for adding new item type " + subtype + " is sent....");
		}
		itemService.addItemType(type, subtype);
	}

	/**
	 * View summary.
	 *
	 * @param startdate
	 *            the startdate
	 * @param enddate
	 *            the enddate
	 * @return the list
	 * @throws ParseException
	 *             the parse exception
	 */
	// To get summary by from and to date
	@RequestMapping(value = "/summary/{fromDate}/{toDate}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Summary> viewSummary(@PathVariable("fromDate") String startdate, @PathVariable("toDate") String enddate)
			throws ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("Request for displaying summary is sent....");
		}

		if (logger.isInfoEnabled()) {
			logger.info("Request for displaying summary is sent....");
		}
		return itemService.viewSummary(startdate, enddate);
	}

	/**
	 * Adds the item quantity.
	 *
	 * @param addItem
	 *            the add item
	 */
	// To add quantity of an item
	@RequestMapping(value = "/addItemQuantity", method = RequestMethod.POST)
	@ResponseBody
	/* @JsonIgnoreProperties(ignoreUnknown = true) */
	public void addItemQuantity(@RequestBody Item addItem) {
		itemService.addItemQuantity(addItem);
	}

	/**
	 * Removes the item quantity.
	 *
	 * @param item
	 *            the item
	 */
	// To remove item quantity
	@RequestMapping(value = "/removeItemQuantity", method = RequestMethod.POST)
	@ResponseBody
	public void removeItemQuantity(@RequestBody List<Item> item) {
		itemService.removeItemQuantity(item);
	}

	/**
	 * Adds the item collection.
	 *
	 * @param collection
	 *            the collection
	 */
	// To add item collection
	@RequestMapping(value = "/addItemCollection", method = RequestMethod.POST)
	@ResponseBody
	/* @JsonIgnoreProperties(ignoreUnknown = true) */
	public void addItemCollection(@RequestBody ItemCollection collection) {
		itemService.addItemCollection(collection);
	}

	/**
	 * Removes the item collection.
	 *
	 * @param itemid
	 *            the itemid
	 */
	// To delete item collection
	@RequestMapping(value = "/removeItemCollection/{itemid}", method = RequestMethod.GET)
	@ResponseBody
	public void removeItemCollection(@PathVariable int itemid) {
		itemService.removeItemCollection(itemid);
	}

	/**
	 * View summary type.
	 *
	 * @param type
	 *            the type
	 * @return the list
	 * @throws ParseException
	 *             the parse exception
	 */
	// To get summary by type
	@RequestMapping(value = "/summary/{type}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ViewSummary> viewSummaryType(@PathVariable("type") String type) throws ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("Request for displaying summary is sent....");
		}

		if (logger.isInfoEnabled()) {
			logger.info("Request for displaying summary is sent....");
		}
		return itemService.viewSummaryType(type);
	}

	/**
	 * Gets the type name.
	 *
	 * @return the type name
	 */
	// To get all the types
	@RequestMapping(value = "/type", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<String> getTypeName() {
		return itemService.getTypeName();
	}

	/**
	 * Update.
	 *
	 * @param url
	 *            the url
	 * @return the response entity
	 */
	// To add a new item
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ResponseEntity<UrlParameter> update(@RequestBody UrlParameter url) {
		itemService.addItem(url);
		return new ResponseEntity<UrlParameter>(url, HttpStatus.OK);
	}

	/**
	 * Gets the items for user.
	 *
	 * @param location
	 *            the location
	 * @return the items for user
	 */
	// To display all items-NORMAL USER VIEW
	@RequestMapping(value = "/users/{location}", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemsByLocation> getItemsForUser(@PathVariable("location") String location) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request for displaying all items is sent....");
		}

		if (logger.isInfoEnabled()) {
			logger.info("Request for displaying all items is sent....");
		}
		return itemService.getItemsForUser(location);
	}

}