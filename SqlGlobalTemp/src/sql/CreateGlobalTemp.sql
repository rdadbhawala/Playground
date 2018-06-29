CREATE TABLE [dbo].[##GlobalTemp](
	[ValueCol] [int] 
) 

insert into ##GlobalTemp values (0)

select * from ##GlobalTemp

drop table ##GlobalTemp


CREATE PROCEDURE [dbo].[SqlGlobalTempSproc] AS
    select * from #SqlGlobalTemp
GO

call SqlGlobalTempSproc