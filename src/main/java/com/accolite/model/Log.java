package com.accolite.model;

import java.sql.Date;

public class Log {
	Integer id;
	Integer itemID;
	Date dateOfPurchase;
	Integer quantity;
	String unit;
	Date dateOfExpiry;
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
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
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
	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public Integer getExpiryFlag() {
		return expiryFlag;
	}
	public void setExpiryFlag(Integer expiryFlag) {
		this.expiryFlag = expiryFlag;
	}
	
	
	
}
