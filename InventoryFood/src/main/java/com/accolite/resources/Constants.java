/****************************************************************************
* Copyright (c) 2016 by Accolite.com. All rights reserved
* 
* Team:Lowkya Vuppu,Loghitha,Pawan Prakash,Momin Yadav
* 
* ***************************************************************************
*/
package com.accolite.resources;

public class Constants {
	
	public static final String VIEW_ITEM_ADMIN = "select dbo.item.itemID,dbo.item.itemName,dbo.log.dateofpurchase,dbo.log.dateofexpiry from dbo.item"
			+ " join dbo.itemCollection" + " on dbo.item.itemID=dbo.itemCollection.itemID"
			+ " join dbo.log on dbo.itemCollection.subItemID=dbo.log.itemID and dbo.log.dateofexpiry>GETDATE()  group by dbo.item.itemName,item.itemID,dbo.log.dateofpurchase,dbo.log.dateofexpiry;";

	public static final String VIEW_ITEM_ADMIN_QUANTITY = ";with log11 as( select log1.itemID itemID,sum(log1.Quantity) total from dbo.log log1 join item on log1.itemID=item.itemID   where log1.io='i'  group  by log1.itemID),logFinal as(select item.itemID,item.itemName from item),log12 as( select log2.itemID,sum(Quantity) used from dbo.log log2 left join item on log2.itemID=item.itemID   where log2.io='o'  group by log2.itemID) select log11.itemID,IsNull(total-used,0) as remaining  from log11 left outer join log12 on log11.itemID=log12.itemID;";
	
	public static final String DELETE_ITEM = "DELETE FROM dbo.item WHERE itemID=?;";

	public static final String ADD_ITEM_TYPE = "Insert into dbo.type(type,subtype)values(?,?);";

	public static final String EMAIL_EXPIRY = "select log.itemID, item.itemName from log join item on"
			+ " log.itemID=item.itemID where log.dateofexpiry = convert(varchar(10),GETDATE(),126);";

	public static final String ADD_ITEM_QUANTITY = "Insert into dbo.log values(?,?,?,?,?)";

	public static final String REMOVE_ITEM_QUANTITY = "Insert into dbo.log values(?,?,?,?,?)";

	public static final String DISPLAY_TYPE = "Select subtype from dbo.type;";
}