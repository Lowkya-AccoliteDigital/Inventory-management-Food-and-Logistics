package com.accolite.dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.accolite.model.Item;
import com.accolite.model.Log;

@Repository
public class ItemDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// QUERY TO GET ALL ITEMS
	public List<Item> getItems(Integer visibility) {
		String query = null;
		
		//Normal user mode
		if(visibility == 0){
			query = "select distinct(dbo.item.itemName) from dbo.item join dbo.itemCollection "
                    +" on dbo.item.itemID=dbo.itemCollection.itemID join dbo.log on"
					+" dbo.itemCollection.subItemID=dbo.log.itemID and"
                    +" dbo.log.dateofexpiry>GETDATE() where dbo.item.visibility=0"
					+" group by dbo.item.itemName;";
		}
				//Admin mode
		else if(visibility == 1){
			query = "select distinct(dbo.item.itemName) from dbo.item left join dbo.itemCollection" 
                   +" on dbo.item.itemID=dbo.itemCollection.itemID left join dbo.log"
				   +" on dbo.itemCollection.subItemID=dbo.log.itemID and "
                   +" dbo.log.dateofexpiry>GETDATE() group by dbo.item.itemName;";
		}

		/*List<Item> list = new ArrayList<Item>();*/
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
	public void addItem( String name, Integer typeId) {
		String query = "Insert into dbo.item(itemName,visibility,typeID)values(?,1,?);";
		Object[] params = new Object[] {name,typeId};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER };

		int row = jdbcTemplate.update(query, params, types);
		System.out.println(row + " row inserted.");

	}

	// QUERY TO REMOVE ITEM
	public void removeItem(String name) {
		String query = "DELETE FROM dbo.item WHERE itemName=?";
		jdbcTemplate.update(query, name);
	}
	
	//QUERY TO ADD NEW ITEMTYPE
	public void addItemType(String type, String subtype){
		String query = "Insert into dbo.type(type,subtype)values(?,?);";
		Object[] params = new Object[] {type,subtype};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

		int row = jdbcTemplate.update(query, params, types);
		System.out.println(row + " row inserted.");
	}
	
	//QUERY TO DISPLAY SUMMARY
	public List<Log> viewSummary(String from, String to) throws ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date fromDate =  formatter.parse(from);
		String fromdate=formatter.format(fromDate);
		Date toDate =  formatter.parse(to);
		String todate=formatter.format(toDate);
		String query = ";with log11 as("
				      + " select log1.itemID itemID,sum(log1.Quantity) total from dbo.log log1" 
				      + " where log1.io='i' and log1.dateofpurchase between '"+fromdate+"' and '"+todate
				      + "' group  by log1.itemID),log12 as("
				      + " select itemID,sum(Quantity) used from dbo.log log2 where log2.io='o'"
				      + " and log2.dateofpurchase between '"+fromdate+"' and '"+todate 
                      + "' group by itemID) select log11.itemID,total,IsNull(used,0) as used,"
				      + " IsNull(total-used,0) as remaining  from log11 left outer join log12"
                      + " on log11.itemID=log12.itemID;";
		System.out.println(query);
 
         return jdbcTemplate.query(query, new ResultSetExtractor<List<Log>>() {

 			public List<Log> extractData(ResultSet rs) throws SQLException, DataAccessException {
 				List<Log> list = new ArrayList<Log>();
 				while (rs.next()) {
 					Log log = new Log();
 					log.set
 					list.add(log);
 				}
 				return list;
 			}
 		});
    }
}
