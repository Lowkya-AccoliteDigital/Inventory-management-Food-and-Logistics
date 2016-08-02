package com.accolite.model;

import java.sql.Date;

public class Log {
	Integer id;
	Integer itemID;
	String dateOfPurchase;
	Integer quantity;
	String unit;
	String dateOfExpiry;
	Integer expiryFlag;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemID() {
		return itemID;
	}
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public Integer getExpiryFlag() {
		return expiryFlag;
	}
	public void setExpiryFlag(Integer expiryFlag) {
		this.expiryFlag = expiryFlag;
	}
	
	
	
}
