USE Inventory
GO

IF  EXISTS( SELECT *
            FROM sys.objects
            WHERE object_id = OBJECT_ID(N'[dbo].[MyBackgroundTask]')
            AND type in (N'P', N'PC'))
    DROP PROCEDURE [dbo].[MyBackgroundTask]


GO

CREATE PROCEDURE MyBackgroundTask
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    -- The interval between cleanup attempts
    declare @timeToRun nvarchar(50)
    set @timeToRun = '03:33:33'

    while 1 = 1
    begin
        waitfor time @timeToRun
        begin
            execute [Inventory].[dbo].[MyDatabaseStoredProcedure];
        end
    end
END
GO

-- Run the procedure when the master database starts.
sp_procoption    @ProcName = 'MyBackgroundTask',
                @OptionName = 'startup',
                @OptionValue = 'on'
GO