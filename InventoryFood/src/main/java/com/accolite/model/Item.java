/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/
package com.accolite.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public class Item {
	
	/** The item id. */
	private Integer itemId;
	
	/** The item name. */
	private String itemName;
	
	/** The quantity. */
	private Integer quantity;
	
	/** The date of purchase. */
	private String dateOfPurchase;
	
	/** The date of expiry. */
	private String dateOfExpiry;
	
	/** The unit. */
	private String unit;
	
	/** The location. */
	private String location;
	
	/** The brand. */
	private String brand;
	
	/** The typeid. */
	private Integer typeid;

	/**
	 * Gets the typeid.
	 *
	 * @return the typeid
	 */
	public Integer getTypeid() {
		return typeid;
	}

	/**
	 * Sets the typeid.
	 *
	 * @param typeid the new typeid
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand.
	 *
	 * @param brand the new brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the unit.
	 *
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Sets the unit.
	 *
	 * @param unit the new unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * Sets the item id.
	 *
	 * @param itemId the new item id
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * Gets the item name.
	 *
	 * @return the item name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets the item name.
	 *
	 * @param itemName the new item name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the date of purchase.
	 *
	 * @return the date of purchase
	 */
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	/**
	 * Sets the date of purchase.
	 *
	 * @param dateOfPurchase the new date of purchase
	 */
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	/**
	 * Gets the date of expiry.
	 *
	 * @return the date of expiry
	 */
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	/**
	 * Sets the date of expiry.
	 *
	 * @param dateOfExpiry the new date of expiry
	 */
	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
}
