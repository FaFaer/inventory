package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_Connection_Utils {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/inventory?useUnicode=true&characterEncoding=utf-8";
	private static final String user = "root";
	private static final String password = "199511";
	private static Connection con = null;

	static {
		
			try {
				Class.forName(driver).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		
	}

	/**
	 * create connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * order : resultSet -> statement-> connection
	 * 
	 * @param rs
	 * @param stmt
	 * @param con
	 */
	public static void close(ResultSet rs, Statement stmt, Connection con) {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
