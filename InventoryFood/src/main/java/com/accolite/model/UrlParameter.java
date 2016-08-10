/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/
package com.accolite.model;

public class UrlParameter {
	private String itemName;
	private Integer typeId;
	private Integer threshold;
	private String brand;
	private Integer quantity;
	
	/** The unit. */
	private String unit;
	private String dateOfPurchase;
	private String dateOfExpiry;
	private String location;
	private Integer visibility;

	public Integer getVisibility() {
		return visibility;
	}

	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
}