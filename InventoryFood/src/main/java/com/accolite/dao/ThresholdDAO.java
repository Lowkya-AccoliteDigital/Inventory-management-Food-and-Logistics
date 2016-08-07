package com.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.accolite.model.EmailExpiry;
import com.accolite.model.Threshold;
import com.accolite.resources.Constants;

@Repository
public class ThresholdDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Threshold> getItemsBelowThreshold() {
		return jdbcTemplate.query(Constants.THRESHOLD, new ResultSetExtractor<List<Threshold>>() {

			public List<Threshold> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Threshold> list = new ArrayList<Threshold>();
				while (rs.next()) {
					Threshold email = new Threshold();
					email.setItemName(rs.getString("itemName"));
					email.setItemId(rs.getInt("itemID"));
					list.add(email);
				}
				return list;
			}
		});

	}

}
