package model;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class ConUtil {
	private static DataSource ds;
	
	static {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myOracle");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
