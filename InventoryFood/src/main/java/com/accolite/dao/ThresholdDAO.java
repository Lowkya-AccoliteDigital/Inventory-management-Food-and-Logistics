/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/
package com.accolite.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.accolite.model.Threshold;

@Repository
public class ThresholdDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	List<Threshold> tempThreshold = null;
	List<Threshold> threshold = null;

	/**
	 * Gets the items below threshold.
	 *
	 * @return the items below threshold
	 */
	// to get items below threshold
	public List<Threshold> getItemsBelowThreshold() {

		String query1 = "create table Temp(itemID int not null,itemName nvarchar(50) not null,"
				+ " remaining int not null,limit int not null);";

		jdbcTemplate.execute(query1);

		String query2 = "with log1 as( select log.itemID log1itemID,item.itemName as itemname,item.threshold  as limit,IsNull(sum(Quantity),0) total from log join item on log.itemID=item.itemID  where io='i' group by log.itemID,item.itemName,item.threshold),log2 as (select log.itemID log2itemID ,item.itemName  as itemname,item.threshold as limit, IsNull(sum(quantity),0)  used from log join item on log.itemID=item.itemID where io='o' group by log.itemID,item.itemName,item.threshold)"
				+ "select log1.log1itemID as itemid,log1.itemname,total-used as remaining, log1.limit as limit from log2,log1 where log1.log1itemID=log2.log2itemID;";

		jdbcTemplate.query(query2, new ResultSetExtractor<List<Threshold>>() {
			public List<Threshold> extractData(ResultSet rs) throws SQLException, DataAccessException {
				tempThreshold = new ArrayList<Threshold>();
				while (rs.next()) {
					Threshold email = new Threshold();
					email.setItemId(rs.getInt(1));
					email.setItemName(rs.getString(2));
					email.setRemaining(rs.getInt(3));
					email.setLimit(rs.getInt(4));
					tempThreshold.add(email);
				}
				return tempThreshold;
			}
		});

		String query3 = "insert into Temp values(?,?,?,?) ";

		jdbcTemplate.batchUpdate(query3, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Threshold email = tempThreshold.get(i);
				ps.setInt(1, email.getItemId());
				ps.setString(2, email.getItemName());
				ps.setInt(3, email.getRemaining());
				ps.setInt(4, email.getLimit());
			}

			public int getBatchSize() {
				return tempThreshold.size();
			}
		});

		String query4 = "select itemID,itemName,remaining,limit from Temp where remaining < limit;";

		jdbcTemplate.query(query4, new ResultSetExtractor<List<Threshold>>() {
			public List<Threshold> extractData(ResultSet rs) throws SQLException, DataAccessException {
				threshold = new ArrayList<Threshold>();
				while (rs.next()) {
					Threshold email = new Threshold();
					email.setItemId(rs.getInt(1));
					email.setItemName(rs.getString(2));
					email.setRemaining(rs.getInt(3));
					email.setLimit(rs.getInt(4));
					threshold.add(email);
				}
				return threshold;
			}
		});

		String query5 = " Drop Table Temp";
		jdbcTemplate.execute(query5);

		return threshold;
	}

}