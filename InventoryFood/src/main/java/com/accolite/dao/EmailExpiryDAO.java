package com.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.accolite.model.EmailExpiry;
import com.accolite.model.Item;
import com.accolite.resources.Constants;

public class EmailExpiryDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<EmailExpiry> getItemsExpired() {
		return jdbcTemplate.query(Constants.EMAIL_EXPIRY, new ResultSetExtractor<List<EmailExpiry>>() {

			public List<EmailExpiry> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<EmailExpiry> list = new ArrayList<EmailExpiry>();
				while (rs.next()) {
					EmailExpiry email = new EmailExpiry();
					email.setItemName(rs.getString("itemName"));
					email.setItemID(rs.getInt("itemID"));
					list.add(email);
				}
				return list;
			}
		});

	}

}
