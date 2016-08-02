package com.accolite.controller;


import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.dao.ItemDAO;
import com.accolite.model.Item;


@Controller
public class ItemController {
	
	@Autowired
	private ItemDAO jdbc;
	
	//To display all items 
	@RequestMapping(value="/items/{visibility}",method=RequestMethod.GET)
	@ResponseBody
	public List<Item> getItems(@PathVariable("visibility")Integer visibility ){
	    return jdbc.getItems(visibility);
	}
	
	@RequestMapping(value="/items",method=RequestMethod.GET,produces = "text/html")
	@ResponseBody
	public String getItems(){
	    return "Welcome";
	}	
	
	//To add a new item
	@RequestMapping(value = "/addItem/{itemName}/{typeId}",method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public void addItem(@PathVariable("itemName")String name,@PathVariable("typeId")Integer typeId){
		jdbc.addItem(name, typeId);
	}
	
	//To remove a item
	@RequestMapping(value = "/removeItem/{itemName}",method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public void removeItem(@PathVariable("itemName")String name){
		jdbc.removeItem(name);
	}
	
	//To add new item type
	@RequestMapping(value = "/addItemType/{typeName}/{subtypeName}",method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public void addItemType(@PathVariable("typeName")String type,@PathVariable("subtypeName")String subtype){
		jdbc.addItemType(type, subtype);
	}
	
	@RequestMapping(value = "/summary/{fromDate}/{toDate}",method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public void viewSummary(@PathVariable("fromDate")String startdate,@PathVariable("toDate")String enddate) throws ParseException{
		jdbc.viewSummary(startdate, enddate);
	}

}
