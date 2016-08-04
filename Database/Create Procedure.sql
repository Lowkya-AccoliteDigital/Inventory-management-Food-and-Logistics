-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Momin
-- Create date: 2016-08-02
-- Description:	
-- =============================================
CREATE PROCEDURE MyDatabaseStoredProcedure
	-- Add the parameters for the stored procedure here
	@expirydate date = NULL
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE Inventory.dbo.log
    SET expiryflag=1
	--where dateofexpiry='2016-07-26'
    WHERE ((select convert(date, getdate()))> dateofexpiry)
END
GO
