package Playground.SqlGlobalTemp;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=PlayGround;user=sa;password=sql";

		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				System.out.println("// disable autocommit");
				connection.setAutoCommit(false);
				System.out.println("// create temp-table");
				try (Statement crStmt = connection.createStatement()) {
					crStmt.executeUpdate("CREATE TABLE [dbo].[#SqlGlobalTemp]([ValueCol] [int] ) ");
				}
				System.out.println("// insert records");
				try (PreparedStatement insStmt = connection.prepareStatement("INSERT INTO #SqlGlobalTemp values(?)")) {
					for (int i = 0; i < 10; i++) {
						insStmt.setInt(1, i);
						insStmt.addBatch();
					}
					insStmt.executeBatch();
				}
				System.out.println("// fetch via stored procedure & match count");
				System.out.println("Records on Select: " + GetCount(connection, queryCall));
				System.out.println("Records on Sproc: " + GetCount(connection, sprocCall));
				System.out.println("Records on Select: " + GetCount(connection, queryCall));
				System.out.println("// // create second connection and un-match count");
				try (Connection connection2 = DriverManager.getConnection(connectionUrl)) {
					if (GetCount(connection2, sprocCall) == 10) {
						System.err.println("found 10 records in another universe");
					}
				}
				System.out.println("// drop table");
				Statement delStmt = connection.createStatement();
				delStmt.executeUpdate("DROP TABLE [dbo].[#SqlGlobalTemp]");
				System.out.println("Done.");
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
        }
	}

	private static String sprocCall = "{CALL SqlGlobalTempSproc}";
	private static String queryCall = "select count(*) from #SqlGlobalTemp";
	
	private static int GetCount(Connection conn, String sql) {
		int value = -1;
		try {
			try (PreparedStatement sprocStatement = conn.prepareStatement(sql)){
				ResultSet rs = sprocStatement.executeQuery();
				rs.next();
				value = rs.getInt(1);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return value;
	}
}
