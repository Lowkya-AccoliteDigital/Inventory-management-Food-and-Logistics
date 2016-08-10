/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/
package com.accolite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.accolite.model.Item;
import com.accolite.model.ItemCollection;
import com.accolite.model.ItemsByLocation;
import com.accolite.model.Summary;
import com.accolite.model.UrlParameter;
import com.accolite.model.ViewSummary;
import com.accolite.resources.Constants;

@Repository
public class ItemDAO {
	final static Logger logger = Logger.getLogger(ItemDAO.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static String fromdate;
	public String todate;
	List<Item> list;
	List<Item> items = null;
	public String quantityQuery = null;
	List<Item> tempItem = null;
	List<Integer> ids = null;
	List<ViewSummary> tempSummary = null;
	List<ViewSummary> summaryByType = null;
	List<ItemsByLocation> tempitemLoc = null;
	List<ItemsByLocation> itemLoc = null;

	// to get all items from database
	public List<Item> getItems() {

		try {
			String query1 = "create table Temp(itemID int not null,itemName nvarchar(50) not null,remaining int null,location nvarchar(50) not null,unit nvarchar(20) null,dateOfPurchase nvarchar(20),dateOfExpiry nvarchar(20),logtype nvarchar(50) not null,brand nvarchar(50) );";
			jdbcTemplate.execute(query1);

			String query2 = ";with log1 as(select log.itemID log1itemID,item.itemName as itemname,IsNull(sum(Quantity),0) total,log.location as location,log.unit as unit,log.dateofpurchase as date1,log.dateofexpiry as date2,item.typeID as type,item.brand from log join item on log.itemID=item.itemID where io='i' group by log.itemID,item.itemName,location,log.unit,log.dateofpurchase,log.dateofexpiry,item.typeID,item.brand)"
					+ ",log2 as(select log.itemID log2itemID,item.itemName as itemname,IsNull(sum(Quantity),0) used,log.location as location,log.unit as unit,log.dateofpurchase as date1,log.dateofexpiry as date2,item.typeID as type,item.brand from log join item on log.itemID=item.itemID where io='o' group by log.itemID,item.itemName,location,log.unit,log.dateofpurchase,log.dateofexpiry,item.typeID,item.brand)"
					+ "select log1.log1itemID as itemid,log1.itemname,total-used as remaining,log1.location,log1.unit,log1.date1 as dop,log1.date2 as doe,log1.type as typ,log1.brand from log2 join log1 on "
					+ "log1.log1itemID=log2.log2itemID and log1.date2 > convert(varchar(10),GETDATE(),126) and log1.date1=log2.date1 and log1.date2=log2.date2  and log1.location=log2.location;";

			jdbcTemplate.query(query2, new ResultSetExtractor<List<Item>>() {
				public List<Item> extractData(ResultSet rs) throws SQLException, DataAccessException {
					tempItem = new ArrayList<Item>();
					while (rs.next()) {
						Item item = new Item();
						item.setItemId(rs.getInt("itemID"));
						item.setItemName(rs.getString("itemName"));
						item.setQuantity(rs.getInt("remaining"));
						item.setLocation(rs.getString("location"));
						item.setUnit(rs.getString("unit"));
						item.setDateOfPurchase(rs.getString("dop"));
						item.setDateOfExpiry(rs.getString("doe"));
						item.setTypeid(rs.getInt("typ"));
						item.setBrand(rs.getString("brand"));
						tempItem.add(item);
					}
					return tempItem;
				}
			});

			String query3 = "	insert into Temp values(?,?,?,?,?,?,?,?,?)";

			jdbcTemplate.batchUpdate(query3, new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Item item = tempItem.get(i);
					ps.setInt(1, item.getItemId());
					ps.setString(2, item.getItemName());
					ps.setInt(3, item.getQuantity());
					ps.setString(4, item.getLocation());
					ps.setString(5, item.getUnit());
					ps.setString(6, item.getDateOfPurchase());
					ps.setString(7, item.getDateOfExpiry());
					ps.setString(9, item.getBrand());
					ps.setInt(8, item.getTypeid());
				}

				public int getBatchSize() {
					return tempItem.size();
				}
			});

			String query4 = "select itemID,itemName,remaining,location,dateofpurchase,dateofexpiry,brand,unit,logtype from Temp where remaining > 0;";

			jdbcTemplate.query(query4, new ResultSetExtractor<List<Item>>() {
				public List<Item> extractData(ResultSet rs) throws SQLException, DataAccessException {
					items = new ArrayList<Item>();
					while (rs.next()) {
						Item item = new Item();
						item.setItemId(rs.getInt("itemID"));
						item.setItemName(rs.getString("itemName"));
						item.setQuantity(rs.getInt("remaining"));
						item.setLocation(rs.getString("location"));
						item.setDateOfPurchase(rs.getString("dateofpurchase"));
						item.setDateOfExpiry(rs.getString("dateofexpiry"));
						item.setTypeid(rs.getInt("logtype"));
						item.setBrand(rs.getString("brand"));
						item.setUnit(rs.getString("unit"));
						items.add(item);
					}
					return items;
				}
			});
			return items;
		} finally {
			jdbcTemplate.execute("Drop Table Temp");
		}

	}

	// to add a new item type to database
	public void addItemType(String type, String subtype) {
		Object[] params = new Object[] { type, subtype };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

		int row = jdbcTemplate.update(Constants.ADD_ITEM_TYPE, params, types);
		System.out.println(row + " row inserted.");
		if (logger.isDebugEnabled()) {
			logger.debug("......Response successful for adding  new item type " + subtype + " ....");
		}

		if (logger.isInfoEnabled()) {
			logger.info("......Response successful for adding new item type " + subtype + " ....");
		}
	}

	// to display summary based on from date and to date
	public List<Summary> viewSummary(String from, String to) throws ParseException {

		String query = ";with log11 as( select log1.itemID itemID,item.itemName,sum(log1.Quantity) total from dbo.log log1 join item on log1.itemID=item.itemID   where log1.io='i' and log1.dateofpurchase between '"
				+ from + "' and '" + to
				+ "' group  by log1.itemID,item.itemName),logFinal as(select item.itemID,item.itemName from item),log12 as( select log2.itemID,item.itemName,sum(Quantity) used from dbo.log log2 join item on item.itemID=log2.itemID where log2.io='o' and log2.dateofpurchase between '"
				+ from + "' and '" + to
				+ "' group by log2.itemID,item.itemName) select log11.itemID,log11.itemName,total,IsNull(used,0) as used, IsNull(total-used,0) as remaining  from log11 left outer join log12 on log11.itemID=log12.itemID;";

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
				if (logger.isDebugEnabled()) {
					logger.debug("......Response successful for displaying summary....");
				}

				if (logger.isInfoEnabled()) {
					logger.info("......Response successful for displaying summary....");
				}
				return list;
			}
		});
	}

	// to increase quantity of item
	public void addItemQuantity(int itemid, int quantity, String dateOfPurchase, String dateOfExpiry, String location,
			String unit) {
		String intake = "i";
		String query = "insert into dbo.log values(" + itemid + "," + quantity + ",'" + unit + "','" + intake + "','"
				+ dateOfPurchase + "','" + dateOfExpiry + "','" + location + "')";
		jdbcTemplate.update(query);
	}

	// to reduce quantity of an item
	public void removeItemQuantity(final List<Item> items) {
		final String outake = "o";
		String query = "insert into dbo.log values(?,?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Item item = items.get(i);
				ps.setInt(1, item.getItemId());
				ps.setInt(2, item.getQuantity());
				ps.setString(3, item.getUnit());
				ps.setString(4, outake);
				ps.setString(5, item.getDateOfPurchase());
				ps.setString(6, item.getDateOfExpiry());
				ps.setString(7, item.getLocation());
			}

			public int getBatchSize() {
				return items.size();
			}
		});

	}

	// to delete an item from database
	public void removeItemCollection(final int itemid) {
		String query1 = "select subItemID from itemCollection where itemID=" + itemid;

		jdbcTemplate.query(query1, new ResultSetExtractor<List<Integer>>() {
			public List<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ids = new ArrayList<Integer>();
				while (rs.next()) {
					ids.add(rs.getInt(1));
				}
				return ids;
			}
		});

		ids.add(itemid);
		if (ids.size() > 1) {
			String query3 = "delete from itemCollection where itemID=?";

			jdbcTemplate.batchUpdate(query3, new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, itemid);
				}

				public int getBatchSize() {
					return ids.size() - 1;
				}
			});
		}

		String query4 = "delete from dbo.log where itemID=?";
		jdbcTemplate.batchUpdate(query4, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, itemid);
			}

			public int getBatchSize() {
				return ids.size() - 1;
			}
		});

		String query2 = "delete from item where itemID=?";

		jdbcTemplate.batchUpdate(query2, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, ids.get(i));
			}

			public int getBatchSize() {
				return ids.size();
			}
		});

	}

	// to add an item collection
	public void addItemCollection(final ItemCollection collection, final String[] subItemid) {

		final String itemQuery = "insert into dbo.item(itemName,typeID)values(?,?);";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(itemQuery, new String[] { "itemID" });
				ps.setString(1, collection.getItemName());
				ps.setInt(2, collection.getTypeId());
				return ps;
			}
		}, keyHolder);

		final int itemid = (int) keyHolder.getKey().longValue();

		String query = "insert into dbo.itemCollection values(?,?)";
		jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, itemid);
				ps.setInt(2, Integer.parseInt(subItemid[i]));
			}

			public int getBatchSize() {
				return subItemid.length;
			}
		});
		String intake = "i";
		query = "insert into dbo.log values(" + itemid + "," + collection.getQuantity() + ",'" + collection.getUnit()
				+ "','" + intake + "','" + collection.getDateOfPurchase() + "','" + collection.getDateOfExpiry() + "','"
				+ collection.getLocation() + "');";
		jdbcTemplate.update(query);

		String outake = "o";
		query = "insert into dbo.log values(" + itemid + ",0" + ",'" + collection.getUnit() + "','" + outake + "','"
				+ collection.getDateOfPurchase() + "','" + collection.getDateOfExpiry() + "','"
				+ collection.getLocation() + "');";
		jdbcTemplate.update(query);
	}

	public List<String> getTypeName() {
		String query = Constants.DISPLAY_TYPE;
		List<String> type = new ArrayList<String>();
		type = jdbcTemplate.query(query, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});

		return type;
	}

	// to fetch summary based on item type
	public List<ViewSummary> viewSummaryType(String type) throws ParseException {
		try {
			String query1 = "create table Temp(itemID int not null,itemName nvarchar(50) not null,"
					+ " remaining int not null,type nvarchar(50) not null,total int);";
			jdbcTemplate.execute(query1);

			String query2 = "with log1 as("
					+ " select log.itemID log1itemID,item.itemName as itemname,IsNull(sum(Quantity),0) total,item.typeID"
					+ " as typeid from log join item on log.itemID=item.itemID where io='i' group by"
					+ " log.itemID,item.itemName,item.typeID),log2 as(select log.itemID log2itemID ,"
					+ " item.itemName as itemname, IsNull(sum(quantity),0) used,item.typeID as typeid"
					+ " from log join item on log.itemID=item.itemID where io='o'"
					+ " group by log.itemID,item.itemName,item.typeID) "
					+ " select log1.log1itemID as itemid,log1.itemname,total-used"
					+ " as remaining,log1.typeid,log1.total from log2,log1 where log1.log1itemID=log2.log2itemID;";

			jdbcTemplate.query(query2, new ResultSetExtractor<List<ViewSummary>>() {
				public List<ViewSummary> extractData(ResultSet rs) throws SQLException, DataAccessException {
					tempSummary = new ArrayList<ViewSummary>();
					while (rs.next()) {
						ViewSummary summary = new ViewSummary();
						summary.setItemID(rs.getInt(1));
						summary.setItemName(rs.getString(2));
						summary.setRemaining(rs.getInt(3));
						summary.setType(rs.getString(4));
						summary.setTotal(rs.getInt(5));
						tempSummary.add(summary);
					}
					return tempSummary;
				}
			});

			String query3 = "insert into Temp values(?,?,?,?,?)";

			jdbcTemplate.batchUpdate(query3, new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ViewSummary summary = tempSummary.get(i);
					ps.setInt(1, summary.getItemID());
					ps.setString(2, summary.getItemName());
					ps.setInt(3, summary.getRemaining());
					ps.setString(4, summary.getType());
					ps.setInt(5, summary.getTotal());
				}

				public int getBatchSize() {
					return tempSummary.size();
				}
			});

			String query4 = "select itemID,itemName,remaining,type.subtype,total from Temp join type on Temp.type=type.typeID"
					+ " where remaining > 0 and type.subtype='" + type + "';";

			jdbcTemplate.query(query4, new ResultSetExtractor<List<ViewSummary>>() {
				public List<ViewSummary> extractData(ResultSet rs) throws SQLException, DataAccessException {
					summaryByType = new ArrayList<ViewSummary>();
					while (rs.next()) {
						ViewSummary summary = new ViewSummary();
						summary.setItemID(rs.getInt(1));
						summary.setItemName(rs.getString(2));
						summary.setRemaining(rs.getInt(3));
						summary.setType(rs.getString(4));
						summary.setTotal(rs.getInt(5));
						summaryByType.add(summary);
					}
					return summaryByType;
				}
			});
			return summaryByType;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			String query5 = " Drop Table Temp";
			jdbcTemplate.execute(query5);
		}
		return summaryByType;
	}

	// to add item
	public void addItem(final UrlParameter url) {
		final String ADD_ITEM = "Insert into dbo.item(itemName,visibility,typeID,brand,threshold)values(?,?,?,?,?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(ADD_ITEM, new String[] { "itemID" });
				ps.setString(1, url.getItemName());
				ps.setInt(2, url.getVisibility());
				ps.setInt(3, url.getTypeId());
				ps.setString(4, url.getBrand());
				ps.setInt(5, url.getThreshold());
				return ps;
			}
		}, keyHolder);

		final int itemid = (int) keyHolder.getKey().longValue();
		String ADD_ITEM_LOG = "Insert into dbo.log values(?,?,?,?,?,?,?);";

		String intake = "i";
		String outtake = "o";
		String query = "insert into dbo.log values(" + itemid + ",0" + ",'" + url.getUnit() + "','" + intake + "','"
				+ url.getDateOfPurchase() + "','" + url.getDateOfExpiry() + "','" + url.getLocation() + "');";
		jdbcTemplate.update(query);

		query = "insert into dbo.log values(" + itemid + "," + url.getQuantity() + ",'" + url.getUnit() + "','"
				+ outtake + "','" + url.getDateOfPurchase() + "','" + url.getDateOfExpiry() + "','" + url.getLocation()
				+ "');";
		jdbcTemplate.update(query);
		if (logger.isDebugEnabled()) {
			logger.debug("......Response successful for adding item " + url.getItemName() + " ....");
		}

		if (logger.isInfoEnabled()) {
			logger.info("......Response successful for adding item " + url.getItemName() + " ....");
		}
	}

	/**
	 * Gets the items for user.
	 *
	 * @param location the location
	 * @return the items for user
	 */
	// to get user view based on location
	public List<ItemsByLocation> getItemsForUser(String location) {
		try {
			String query1 = "create table Temp(itemID int not null,itemName nvarchar(50) not null,remaining int not null,"
					+ " location nvarchar(50) not null);";

			jdbcTemplate.execute(query1);

			String query2 = "with log1 as(select log.itemID log1itemID,item.itemName as itemname,IsNull(sum(Quantity),0)"
					+ " total,log.location as location from log join item on log.itemID=item.itemID where io='i' and "
					+ " log.location='Bangalore' and item.visibility=0 group by log.itemID,item.itemName,location),"
					+ " log2 as(select log.itemID log2itemID ,item.itemName as itemname, IsNull(sum(quantity),0) used,"
					+ " log.location as location from log join item on log.itemID=item.itemID where io='o' "
					+ " and log.location='" + location
					+ "' and item.visibility=0 group by log.itemID,item.itemName,location)"
					+ " select log1.log1itemID as itemid,log1.itemname,total-used as remaining,log1.location from log2,log1"
					+ " where log1.log1itemID=log2.log2itemID;";

			jdbcTemplate.query(query2, new ResultSetExtractor<List<ItemsByLocation>>() {
				public List<ItemsByLocation> extractData(ResultSet rs) throws SQLException, DataAccessException {
					tempitemLoc = new ArrayList<ItemsByLocation>();
					while (rs.next()) {
						ItemsByLocation item = new ItemsByLocation();
						item.setItemID(rs.getInt(1));
						item.setItemName(rs.getString(2));
						item.setRemaining(rs.getInt(3));
						item.setLocation(rs.getString(4));

						tempitemLoc.add(item);
					}
					return tempitemLoc;
				}
			});

			String query3 = "insert into Temp values(?,?,?,?)";
			jdbcTemplate.batchUpdate(query3, new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ItemsByLocation item = tempitemLoc.get(i);
					ps.setInt(1, item.getItemID());
					ps.setString(2, item.getItemName());
					ps.setInt(3, item.getRemaining());
					ps.setString(4, item.getLocation());
				}

				public int getBatchSize() {
					return tempitemLoc.size();
				}
			});

			String query4 = "select itemID,itemName,remaining,location from Temp where remaining > 0;";
			jdbcTemplate.query(query4, new ResultSetExtractor<List<ItemsByLocation>>() {
				public List<ItemsByLocation> extractData(ResultSet rs) throws SQLException, DataAccessException {
					itemLoc = new ArrayList<ItemsByLocation>();
					while (rs.next()) {
						ItemsByLocation item = new ItemsByLocation();
						item.setItemID(rs.getInt(1));
						item.setItemName(rs.getString(2));
						item.setRemaining(rs.getInt(3));
						item.setLocation(rs.getString(4));

						itemLoc.add(item);
					}
					return itemLoc;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			String query5 = "Drop table Temp";
			jdbcTemplate.execute(query5);
		}
		return itemLoc;
	}
}