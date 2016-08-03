package com.accolite.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.model.Item;
import com.accolite.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	// To display all items
	@RequestMapping(value = "/items/{visibility}", method = RequestMethod.GET)
	@ResponseBody
	public List<Item> getItems(@PathVariable("visibility") Integer visibility) {
		return itemService.getItems(visibility);
	}

	// To add a new item
	@RequestMapping(value = "/addItem/{itemName}/{typeId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void addItem(@PathVariable("itemName") String name, @PathVariable("typeId") Integer typeId) {
		itemService.addItem(name, typeId);
	}

	// To remove a item
	@RequestMapping(value = "/removeItem/{itemName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void removeItem(@PathVariable("itemName") String name) {
		itemService.removeItem(name);
	}

	// To add new item type
	@RequestMapping(value = "/addItemType/{typeName}/{subtypeName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void addItemType(@PathVariable("typeName") String type, @PathVariable("subtypeName") String subtype) {
		itemService.addItemType(type, subtype);
	}

	@RequestMapping(value = "/summary/{fromDate}/{toDate}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void viewSummary(@PathVariable("fromDate") String startdate, @PathVariable("toDate") String enddate)
			throws ParseException {
		itemService.viewSummary(startdate, enddate);
	}

}
