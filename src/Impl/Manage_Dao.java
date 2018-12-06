package Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import InterF.Manager_impl;
import Tools.TExecute;
import mode.Admin;
import mode.Dealer;
import mode.Supplier;

public class Manage_Dao extends TExecute implements Manager_impl {

	@Override
	public ResultSet Display_dealer()  {
		String sql = "select * from dealer";
		Object[] objects = {};
		ResultSet re = execute_Raid(sql, objects);
		return re;
	}

	@Override
	public ResultSet Display_supplier() {
		String sql = "select * from supplier";
		Object[] objects = {};
		ResultSet re = execute_Raid(sql, objects);
		return re;
	}
	public boolean Login(Admin admin) {
		String sql = "select * from admin where adminName = ? and adminPassword = ?";
		Object[] objects = {admin.getAdminName(),admin.getAdminPassword()};
		ResultSet re = execute_Raid(sql, objects);
		boolean b = false;
		try {
			
			re.last();
			if(re.getRow() != 0) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean Sign(Admin admin) {
		String sql = "insert into admin (adminTypeId,adminName,adminPassword) values(?,?,?)";
		Object[] objects = {admin.getAdminTypeId(),admin.getAdminName(),admin.getAdminPassword()};
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public ResultSet Display_wearmanage(int adminTypeId) {
	String sql = "select * from admin where adminTypeId = ?";
	Object[] objects = {adminTypeId};
	ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}


}
