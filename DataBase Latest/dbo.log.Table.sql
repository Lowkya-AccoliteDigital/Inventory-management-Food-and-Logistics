USE [Inventory]
GO
/****** Object:  Table [dbo].[log]    Script Date: 8/5/2016 8:36:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[log](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[itemID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[unit] [nvarchar](50) NULL,
	[io] [nvarchar](50) NOT NULL,
	[dateofpurchase] [date] NOT NULL,
	[dateofexpiry] [date] NULL,
 CONSTRAINT [PK_log] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[log] ON 

INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (1, 1, 15, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-22' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (2, 2, 15, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (3, 3, 15, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (4, 4, 15, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (5, 5, 15, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (6, 6, 15, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (7, 7, 70, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (8, 8, 15, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (9, 9, 15, N'1.5 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (10, 10, 65, N'500 ml', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (11, 11, 75, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (12, 12, 20, N'150 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (13, 13, 8, N'350 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (14, 14, 12, N'350 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (15, 15, 10, N'180 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (16, 16, 20, N'150 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-22' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (17, 17, 16, N'1.25 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (18, 18, 16, N'1.25 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (19, 19, 15, N'1 Kg', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (20, 20, 16, N'300 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (21, 21, 15, N'700 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (22, 22, 70, N'90 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (23, 23, 30, N'150 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (24, 24, 20, N'127.4 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (25, 25, 20, N'18 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (26, 26, 20, N'15 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (27, 27, 20, N'18 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (28, 28, 20, N'55 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (29, 29, 25, N'100 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (30, 30, 20, N'550 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (31, 31, 5, N'462 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-22' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (32, 32, 5, N'462 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (33, 33, 3, N'400 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (34, 34, 3, N'500 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (35, 35, 3, N'500 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (36, 36, 5, N'500 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (37, 37, 12, N'200 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (38, 38, 90, N'1 Ltr', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (39, 39, 15, N'100 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (40, 40, 5, N'500 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (41, 41, 5, N'275 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (42, 42, 10, N'300 ml', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (43, 43, 10, N'50 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (44, 44, 3, N'300 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (45, 45, 5, N'500 ml', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (46, 46, 5, N'8 Pcs', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-22' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (47, 47, 3, N'900 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (48, 48, 3, N'500 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (49, 49, 5, N'7.5*2.5 cm 1 pc', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (50, 50, 10, N'500 ml', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (51, 51, 5, N'250 ml', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (52, 52, 5, N'500 ml', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (53, 53, 5, N'500 ml', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (54, 54, 15, N'82 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (55, 55, 10, N'1 Kg', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (56, 56, 75, N'70 gm', N'i', CAST(N'2016-08-01' AS Date), CAST(N'2016-10-26' AS Date))
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (57, 57, 60, NULL, N'i', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (58, 58, 60, NULL, N'i', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (59, 59, 60, NULL, N'i', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (60, 60, 60, NULL, N'i', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (61, 57, 50, NULL, N'o', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (62, 58, 50, NULL, N'o', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (63, 59, 50, NULL, N'o', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (64, 60, 50, NULL, N'o', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (65, 61, 60, NULL, N'i', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (66, 62, 60, NULL, N'i', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (67, 61, 50, NULL, N'0', CAST(N'2010-08-01' AS Date), NULL)
INSERT [dbo].[log] ([id], [itemID], [Quantity], [unit], [io], [dateofpurchase], [dateofexpiry]) VALUES (68, 62, 50, NULL, N'o', CAST(N'2010-08-01' AS Date), NULL)
SET IDENTITY_INSERT [dbo].[log] OFF
