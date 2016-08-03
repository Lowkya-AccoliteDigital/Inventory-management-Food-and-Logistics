package com.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.accolite.model.Item;
import com.accolite.model.Log;
import com.accolite.resources.Constants;

@Repository
public class ItemDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static String fromdate;
	public String todate;

	// QUERY TO GET ALL ITEMS
	public List<Item> getItems(Integer visibility) {
		String query = null;

		// Normal user mode
		if (visibility == 0) {
			query = Constants.VIEW_ITEM_USER;
		}
		// Admin mode
		else if (visibility == 1) {
			query = Constants.VIEW_ITEM_ADMIN;
		}

		/* List<Item> list = new ArrayList<Item>(); */
		return jdbcTemplate.query(query, new ResultSetExtractor<List<Item>>() {

			public List<Item> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Item> list = new ArrayList<Item>();
				while (rs.next()) {
					Item item = new Item();
					item.setItemName(rs.getString("itemName"));
					list.add(item);
				}
				return list;
			}
		});
	}

	// QUERY TO ADD ITEM
	public void addItem(String name, Integer typeId) {
		Object[] params = new Object[] { name, typeId };
		int[] types = new int[] { Types.VARCHAR, Types.INTEGER };

		int row = jdbcTemplate.update(Constants.ADD_ITEM, params, types);
		System.out.println(row + " row inserted.");

	}

	// QUERY TO REMOVE ITEM
	public void removeItem(String name) {
		jdbcTemplate.update(Constants.DELETE_ITEM, name);
	}

	// QUERY TO ADD NEW ITEMTYPE
	public void addItemType(String type, String subtype) {
		Object[] params = new Object[] { type, subtype };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

		int row = jdbcTemplate.update(Constants.ADD_ITEM_TYPE, params, types);
		System.out.println(row + " row inserted.");
	}

	// QUERY TO DISPLAY SUMMARY
	public List<Log> viewSummary(String from, String to) throws ParseException {

		return jdbcTemplate.query(Constants.VIEW_SUMMARY, new ResultSetExtractor<List<Log>>() {

			public List<Log> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Log> list = new ArrayList<Log>();
				while (rs.next()) {
					Log log = new Log();
					/*
					 * log.set list.add(log);
					 */
				}
				return list;
			}
		});
	}
}
