USE [Inventory]
GO
/****** Object:  Table [dbo].[item]    Script Date: 8/5/2016 8:36:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[item](
	[itemID] [int] IDENTITY(1,1) NOT NULL,
	[itemName] [nvarchar](max) NOT NULL,
	[visibilty] [int] NOT NULL,
	[typeID] [int] NOT NULL,
	[threshold] [int] NULL,
 CONSTRAINT [PK_item] PRIMARY KEY CLUSTERED 
(
	[itemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[item] ON 

INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (67, N'Real Apple Juice', 0, 32, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (68, N'Real Mixed Fruit Juice', 0, 33, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (69, N'Real Pomegranate', 0, 34, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (70, N'Real Cranberry Juice', 0, 35, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (71, N'Real Litchi Fruit Juice', 0, 36, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (72, N'Real Guava Juice', 0, 37, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (73, N'Minute Maid Pulpy Orange ', 0, 38, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (74, N'Minute Maid Nimbu Flavour', 0, 39, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (75, N'Maaza Bottle', 0, 40, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (76, N'Nandini Good Life Cow Milk', 0, 41, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (77, N'Haldiram Nimbu Masala', 0, 42, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (78, N'Haldiram Bhujia Sev', 0, 43, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (79, N'Haldiram Moong Dal', 0, 44, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (80, N'MTR Snakup Ompudi', 0, 45, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (81, N'Rose Kodubele Small', 0, 46, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (82, N'Thums Up Bottle ', 0, 47, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (83, N'Sprite Bottle', 0, 48, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (84, N'Madhur Pure & Hygienic Sugar', 0, 49, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (85, N'Kelloggs Real Honey', 0, 50, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (86, N'Kelloggs Chocos', 0, 51, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (87, N'Britannia Good Day Rich Pista Badam Cookies', 0, 52, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (88, N'Parle Hide & Seek Bourbon Chocolate Cream Biscuit', 0, 53, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (89, N'Cadbury Oreo Chocolatey Sandwich Biscuits', 0, 54, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (90, N'Knorr Cup a Soup Tomato Chatpata', 0, 55, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (91, N'Knorr Cup a Soup Sweet Corn Veg', 0, 56, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (92, N'Knorr Mixed Vegetable Cup a Soup', 0, 57, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (93, N'Britannia 50-50 Maska Chaska Biscuit ', 0, 58, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (94, N'Britannia Nutri Choice Hi Fibre Digestive Biscuit', 0, 59, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (95, N'Kelloggs Muesli Nuts Delight ', 0, 60, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (96, N'Sundrop Honey Roast Crunchy Peanut Butter', 0, 61, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (97, N'Sundrop Honey Roast Creamy Peanut Butter', 0, 62, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (98, N'Horlicks Standard Classis Malt Jar', 0, 63, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (99, N'Cadbury Bournvita Jar 5 Star Magic', 0, 64, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (100, N'Boost Jar', 0, 65, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (101, N'Amul Butter', 0, 66, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (102, N'Britannia Premium Bake Rusk', 0, 67, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (103, N'Amul Masti Spiced Buttermilk', 0, 68, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (104, N'Kissan Mixed Fruit Jam', 0, 69, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (105, N'Fun Foods Egg Less Veg Mayonnaise', 0, 70, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (106, N' AirWick Lavender Dew Air Freshener Spray', 0, 72, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (107, N'Odonil Nature Lavender Air Freshener', 0, 73, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (108, N'Mothers Mixed South Indian Style Pickle', 0, 74, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (109, N'Aquafina Water Bottle', 0, 75, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (110, N'Duracell AA Alkaline Battery', 0, 76, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (111, N'Kissan Squeezo Fresh Tomato Ketchup', 0, 77, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (112, N'Baba Ramdev Patanjali Pure Honey', 0, 78, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (113, N'Scotch Brite Scrub Pad', 0, 79, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (114, N'Lizol Sandal 3 In 1 Surface Cleaner', 0, 80, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (115, N'Vim Double Power Lime Liquid Dishwash', 0, 81, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (116, N'Harpic Plus Bleach', 0, 82, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (117, N'Colin Glass & Household Cleaner', 0, 83, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (118, N'Parle Hide & Seek Chocolate Chip Cookies', 0, 84, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (119, N'MTR Gulab Jamun Jar 1', 0, 85, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (120, N'Maggi Masala Noodles', 0, 86, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (121, N'bag', 0, 87, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (122, N'bottles', 0, 88, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (123, N'Pen', 0, 89, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (124, N'Notepad', 0, 90, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (125, N'Marker', 0, 91, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (126, N'Duster', 0, 92, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (127, N'Au Kit', 0, 93, 10)
INSERT [dbo].[item] ([itemID], [itemName], [visibilty], [typeID], [threshold]) VALUES (128, N'Emplyoee Kit', 0, 94, 10)
SET IDENTITY_INSERT [dbo].[item] OFF
ALTER TABLE [dbo].[item]  WITH CHECK ADD  CONSTRAINT [FK_item_type] FOREIGN KEY([typeID])
REFERENCES [dbo].[type] ([typeID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[item] CHECK CONSTRAINT [FK_item_type]
GO
