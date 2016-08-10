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

import com.accolite.model.EmailExpiry;
import com.accolite.resources.Constants;

@Repository
public class EmailExpiryDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	List<EmailExpiry> list = null;

	/**
	 * Gets the items expired.
	 *
	 * @return the items expired
	 */
	public List<EmailExpiry> getItemsExpired() {

		jdbcTemplate.query(Constants.EMAIL_EXPIRY, new ResultSetExtractor<List<EmailExpiry>>() {

			public List<EmailExpiry> extractData(ResultSet rs) throws SQLException, DataAccessException {
				list = new ArrayList<EmailExpiry>();
				while (rs.next()) {
					EmailExpiry email = new EmailExpiry();
					email.setItemName(rs.getString("itemName"));
					email.setItemID(rs.getInt("itemID"));
					list.add(email);
				}
				return list;
			}
		});

		String query2 = "insert into log values(?,?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(query2, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				EmailExpiry email = list.get(i);
				ps.setInt(1, email.getItemID());
				ps.setInt(2, 0);
				ps.setString(3, "null");
				ps.setString(4, "e");
				ps.setString(5, "null");
				ps.setString(6, "null");
				ps.setString(7, "null");
			}

			public int getBatchSize() {
				return list.size();
			}
		});

		return list;
	}

}