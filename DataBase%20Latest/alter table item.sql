use Inventory
ALTER TABLE dbo.item ADD threshold  int NULL
select * from dbo.item

UPDATE dbo.item
SET    threshold=10