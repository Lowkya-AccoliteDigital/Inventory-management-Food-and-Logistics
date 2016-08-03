package com.accolite.resources;

public class Constants {
	public static final String VIEW_ITEM_USER = "select distinct(dbo.item.itemName) from dbo.item join dbo.itemCollection "
			+ " on dbo.item.itemID=dbo.itemCollection.itemID join dbo.log on"
			+ " dbo.itemCollection.subItemID=dbo.log.itemID and"
			+ " dbo.log.dateofexpiry>GETDATE() where dbo.item.visibility=0" + " group by dbo.item.itemName;";

	public static final String VIEW_ITEM_ADMIN = "select distinct(dbo.item.itemName) from dbo.item left join dbo.itemCollection"
			+ " on dbo.item.itemID=dbo.itemCollection.itemID left join dbo.log"
			+ " on dbo.itemCollection.subItemID=dbo.log.itemID and "
			+ " dbo.log.dateofexpiry>GETDATE() group by dbo.item.itemName;";

	public static final String ADD_ITEM = "Insert into dbo.item(itemName,visibility,typeID)values(?,1,?);";

	public static final String DELETE_ITEM = "DELETE FROM dbo.item WHERE itemName=?;";

	public static final String ADD_ITEM_TYPE = "Insert into dbo.type(type,subtype)values(?,?);";

	public static final String VIEW_SUMMARY = ";with log11 as("
			+ " where log1.io='i' and log1.dateofpurchase between '?' and '?'"
			+ " select log1.itemID itemID,sum(log1.Quantity) total from dbo.log log1"
			+ "' group  by log1.itemID),log12 as("
			+ " select itemID,sum(Quantity) used from dbo.log log2 where log2.io='o'"
			+ " and log2.dateofpurchase between '?' and '?'"
			+ "' group by itemID) select log11.itemID,total,IsNull(used,0) as used,"
			+ " IsNull(total-used,0) as remaining  from log11 left outer join log12" + " on log11.itemID=log12.itemID;";

}
