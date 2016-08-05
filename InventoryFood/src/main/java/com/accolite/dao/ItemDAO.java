package com.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.accolite.controller.ItemController;
import com.accolite.model.Item;
import com.accolite.model.Log;
import com.accolite.model.Summary;
import com.accolite.resources.Constants;

@Repository
public class ItemDAO {
	final static Logger logger = Logger.getLogger(ItemDAO.class);
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
				if(logger.isDebugEnabled()){
					logger.debug("......Response successful for displaying all items....");
				}
				
				if(logger.isInfoEnabled()){
					logger.info("......Response successful for displaying all items....");
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
		if(logger.isDebugEnabled()){
			logger.debug("......Response successful for adding item "+name+" ....");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("......Response successful for adding item "+name+" ....");
		}
	}

	// QUERY TO REMOVE ITEM
	public void removeItem(String name) {
		jdbcTemplate.update(Constants.DELETE_ITEM, name);
		if(logger.isDebugEnabled()){
			logger.debug("......Response successful for deleting item "+name+" ....");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("......Response successful for deleting item "+name+" ....");
		}
	}

	// QUERY TO ADD NEW ITEMTYPE
	public void addItemType(String type, String subtype) {
		Object[] params = new Object[] { type, subtype };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

		int row = jdbcTemplate.update(Constants.ADD_ITEM_TYPE, params, types);
		System.out.println(row + " row inserted.");
		if(logger.isDebugEnabled()){
			logger.debug("......Response successful for adding  new item type "+subtype+" ....");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("......Response successful for adding new item type "+subtype+" ....");
		}
	}

	// QUERY TO DISPLAY SUMMARY
	public List<Summary> viewSummary(String from, String to) throws ParseException {

		String query=";with log11 as( select log1.itemID itemID,item.itemName,sum(log1.Quantity) total from dbo.log log1 join item on log1.itemID=item.itemID   where log1.io='i' and log1.dateofpurchase between"
				+from+" and "+to+" group  by log1.itemID,item.itemName),logFinal as(select item.itemID,item.itemName from item),log12 as( select log2.itemID,item.itemName,sum(Quantity) used from dbo.log log2 join item on item.itemID=log2.itemID where log2.io='o' and log2.dateofpurchase between"
				+from+" and "+to+" group by log2.itemID,item.itemName) select log11.itemID,log11.itemName,total,IsNull(used,0) as used, IsNull(total-used,0) as remaining  from log11 left outer join log12 on log11.itemID=log12.itemID;";

		return jdbcTemplate.query(query, new ResultSetExtractor<List<Summary>>() {
			public List<Summary> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Summary> list = new ArrayList<Summary>();
				while (rs.next()) {
					Summary summary = new Summary();
					summary.setItemID(rs.getInt(1));
					summary.setItemName(rs.getString(2));
					summary.setTotal(rs.getInt(3));
					summary.setUsed(rs.getInt(4));
					summary.setRemaining(rs.getInt(5));
					list.add(summary);
				}
				if(logger.isDebugEnabled()){
					logger.debug("......Response successful for displaying summary....");
				}
				
				if(logger.isInfoEnabled()){
					logger.info("......Response successful for displaying summary....");
				}
				return list;
			}
		});
	}
}