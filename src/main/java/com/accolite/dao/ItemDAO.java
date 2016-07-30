package com.accolite.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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

@Repository
public class ItemDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// QUERY TO GET ALL ITEMS
	public List<Item> getItems(Integer visibility) {
		String query = null;
		
		//Normal user mode
		if(visibility == 0){
			query = "select distinct(item.itemName) from item join itemCollection "
                    +" on item.itemID=itemCollection.itemID join log on"
					+" itemCollection.subItemID=log.itemID and"
                    +" log.dateofexpiry>GETDATE() where item.visibility=0"
					+" group by item.itemName;";
		}
		
		//Admin mode
		else if(visibility == 1){
			query = "select distinct(item.itemName) from item left join itemCollection" 
                   +" on item.itemID=itemCollection.itemID left join log"
				   +" on itemCollection.subItemID=log.itemID and "
                   +" log.dateofexpiry>GETDATE() group by item.itemName;";
		}

		/*List<Item> list = new ArrayList<Item>();*/
		return jdbcTemplate.query(query, new ResultSetExtractor<List<Item>>() {

			public List<Item> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Item item = new Item();
				List<Item> list = new ArrayList<Item>();
				while (rs.next()) {

					item.setItemId(rs.getInt("itemId"));
					item.setItemName(rs.getString("itemName"));

					list.add(item);
				}
				return list;
			}
		});
	}

	// QUERY TO ADD ITEM
	public void addItem( String name, Integer typeId) {
		String query = "Insert into item(name,visibility,typeId)values(?,1,?);";
		Object[] params = new Object[] {name,typeId};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER };

		int row = jdbcTemplate.update(query, params, types);
		System.out.println(row + " row inserted.");

	}

	// QUERY TO REMOVE ITEM
	public void removeItem(String name) {
		String query = "DELETE FROM item WHERE name=?";
		jdbcTemplate.update(query, name);
	}
	
	//QUERY TO ADD NEW ITEMTYPE
	public void addItemType(String type, String subtype){
		String query = "Insert into type(type,subtype)values(?,?);";
		Object[] params = new Object[] {type,subtype};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

		int row = jdbcTemplate.update(query, params, types);
		System.out.println(row + " row inserted.");
	}
	
	//QUERY TO DISPLAY SUMMARY
	public void viewSummary(Date from, Date to){
		String query = ";with log11 as("
				      + " select log1.itemID itemID,sum(log1.Quantity) total from log log1" 
				      + " where log1.io='i' and log1.dateofpurchase between "+from+" and "+to
				      + " group  by log1.itemID),log12 as("
				      + " select itemID,sum(Quantity) used from log log2 where log2.io='o'"
				      + " and log2.dateofpurchase between "+from+" and "+to 
                      + " group by itemID) select log11.itemID,total,IsNull(used,0) as used,"
				      + " IsNull(total-used,0) as remaining  from log11 left outer join log12"
                      + " on log11.itemID=log12.itemID;";
         jdbcTemplate.execute(query); 
    }
}
