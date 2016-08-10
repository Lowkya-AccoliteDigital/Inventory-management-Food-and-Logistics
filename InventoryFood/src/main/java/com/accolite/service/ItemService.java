/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/
package com.accolite.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.ItemDAO;
import com.accolite.model.Item;
import com.accolite.model.ItemCollection;
import com.accolite.model.ItemsByLocation;
import com.accolite.model.Summary;
import com.accolite.model.UrlParameter;
import com.accolite.model.ViewSummary;

@Service
public class ItemService {

	@Autowired
	private ItemDAO itemDao;

	@Autowired
	private ThresholdService thresholdservice;

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	// To get the list of all items
	public List<Item> getItems() {
		return itemDao.getItems();
	}

	// To add a new type of item
	public void addItemType(String type, String subtype) {
		itemDao.addItemType(type, subtype);
	}

	// To display summary for a given period
	public List<Summary> viewSummary(String startdate, String enddate) throws ParseException {
		return itemDao.viewSummary(startdate, enddate);
	}

	// To increase the existing item quantity
	public void addItemQuantity(Item addItem) {
		String intake = "i";
		itemDao.addItemQuantity(addItem.getItemId(), addItem.getQuantity(), addItem.getDateOfPurchase(),
				addItem.getDateOfExpiry(), addItem.getLocation(), addItem.getUnit());
	}

	// To reduce the existing item quantity
	public void removeItemQuantity(List<Item> item) {
		String outake = "o";
		itemDao.removeItemQuantity(item);
		thresholdservice.getItemsBelowThreshold();
	}

	// To remove a item collection
	public void removeItemCollection(int itemId) {
		itemDao.removeItemCollection(itemId);
	}

	// To add a new item collection
	public void addItemCollection(ItemCollection collection) {
		String[] subItemid = collection.getSubItemids().split(",");
		itemDao.addItemCollection(collection, subItemid);
	}

	// To display summary for a given type
	public List<ViewSummary> viewSummaryType(String type) throws ParseException {
		return itemDao.viewSummaryType(type);
	}

	// To get all types available
	public List<String> getTypeName() {
		return itemDao.getTypeName();
	}

	// To add a new non-existing item
	public void addItem(UrlParameter url) {
		itemDao.addItem(url);
	}

	// To list the items for user
	public List<ItemsByLocation> getItemsForUser(String location) {
		return itemDao.getItemsForUser(location);
	}
}