-- select HASHBYTES('sha1','0')

CREATE OR ALTER PROCEDURE [dbo].[SqlHashCostSproc] 
(
    @Loop AS INTEGER
) AS

BEGIN

DECLARE @Counter AS INTEGER
DECLARE @Hash AS VARBINARY(MAX)
DECLARE @EndTime datetime
DECLARE @StartTime datetime 

SET @Counter = 0

SELECT @StartTime=GETDATE() 

WHILE @Counter < @Loop
BEGIN
    SET @Counter = @Counter + 1
    SET @Hash = HASHBYTES('sha1', CAST(@Counter AS VARCHAR(MAX)) )
END

SELECT @EndTime=GETDATE()

-- SELECT HASHBYTES('sha1', '0' )
-- SELECT HASHBYTES('sha1', CAST(0 AS VARCHAR(MAX)) )

SELECT DATEDIFF(ms,@StartTime,@EndTime) AS [Duration in millisecs] 

END