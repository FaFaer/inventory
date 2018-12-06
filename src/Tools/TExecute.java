package Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TExecute extends Database_Connection_Utils{
	private static Connection con = null;
	private static PreparedStatement stmt = null;
	
	/**ÔöÉ¾¸Ä
	 * insert update delete
	 * @param sql
	 * @param objects
	 * @return
	 */
	public boolean execute_create_Update_Delete(String sql , Object[] objects) {
		boolean b = false;
		con = getConnection();
		try {
			stmt = con.prepareStatement(sql);
			if(objects != null) {
				for(int index = 0 ; index < objects.length ; index++) {
					stmt.setObject(index + 1, objects[index]);
				}
			}
			
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, stmt, con);
		}
		return b;
	}
	
	/**
	 * Raid²éÑ¯
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet execute_Raid(String sql , Object[] objects)  {
		ResultSet rs = null;
		con = getConnection();
		try {
			stmt =  con.prepareStatement(sql);
			if(objects != null) {
				for(int index = 0 ; index < objects.length ; index++) {
					stmt.setObject(index + 1, objects[index]);
				}
			}
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * close resultSet
	 * @param rs
	 */
	public void close(ResultSet rs) {
		close(rs, stmt, con);
	}
	
	
	
}
