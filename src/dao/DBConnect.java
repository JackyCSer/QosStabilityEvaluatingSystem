package dao;

import java.sql.*;

public class DBConnect
{
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	public static Connection OracleConnection()
	{
		Connection conn = null;
		try
		{
			/** 获取连接对象 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(JDBC_URL, "scott", "tiger");
			/** 获取操作对象 */
			return conn;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		/*
		 * 
		 * Connection conn = null; Statement st = null; ResultSet rs = null;
		 * String sql = "";
		 * 
		 * try { conn = DBConnect.OracleConnection(); st =
		 * conn.createStatement(); rs = st.executeQuery(sql); while (rs.next())
		 * {
		 * 
		 * }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } finally { try {
		 * rs.close(); st.close(); conn.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 */

	}

}
