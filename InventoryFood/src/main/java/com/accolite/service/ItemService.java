package com.accolite.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.ItemDAO;
import com.accolite.model.Item;
import com.accolite.model.Summary;

@Service
public class ItemService {

	@Autowired
	private ItemDAO itemDao;

	public List<Item> getItems(Integer visibility) {
		return itemDao.getItems(visibility);
	}

	public void addItem(String name, Integer typeId) {
		itemDao.addItem(name, typeId);
	}

	public void removeItem(String name) {
		itemDao.removeItem(name);
	}

	public void addItemType(String type, String subtype) {
		itemDao.addItemType(type, subtype);
	}

	public List<Summary> viewSummary(String startdate, String enddate) throws ParseException {
		return itemDao.viewSummary(startdate, enddate);
	}

}