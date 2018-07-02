package Playground.SqlHashCost;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.security.*;

/**
 * Hello world!
 *
 */
public class App 
{
	private static int version = 1;
	private static int loop = 1000000;

    public static void main( String[] args )
    {
		System.out.println("Version: " + version);
		for (int i = 0; i < 5; i++) {
			injava();
			db();
		}
	}

	private static void injava() {
		try {
			byte[][] hashes= new byte[loop][];
			long startTime = System.currentTimeMillis();
			for(int i = 0; i < loop; i++) {
				hashes[i] = DigestUtils.sha1(Integer.toString(i).getBytes("UTF-8"));
			}
			long endTime = System.currentTimeMillis();
			// System.out.println("0: " + Hex.encodeHexString(hashes[0]));
			// System.out.println("1: " + Hex.encodeHexString(hashes[1]));
			System.out.println("Java time in ms: " + (endTime - startTime));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void db()
	{
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=PlayGround;user=sa;password=sql";

		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				connection.setAutoCommit(false);
				try (PreparedStatement sprocStatement = connection.prepareStatement("{CALL SqlHashCostSproc (?)}")){
					sprocStatement.setInt(1,loop);
					ResultSet rs = sprocStatement.executeQuery();
					rs.next();
					System.out.println("Db time in ms: " + rs.getInt(1));
				}
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
        }
	}
}
