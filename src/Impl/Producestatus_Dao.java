package Impl;

import java.sql.ResultSet;
import java.util.List;

import InterF.Producestatus_impl;
import Tools.TExecute;

public class Producestatus_Dao extends TExecute implements Producestatus_impl {

	@Override
	public boolean Mod_down_producesatus(int produceId) {
		String sql = "delete from shelfproduce where produceId = ?";
		Object[] objects = {produceId};
		boolean a = execute_create_Update_Delete(sql, objects);
		sql = "insert into downproduce(produceId) values(?)";
		Object[] objects1 = {produceId};
		boolean b = execute_create_Update_Delete(sql, objects1);
		boolean c = false;
		if(a == true && b == true) {
			c = true;
		}
		return c;
	}

	@Override
	public boolean Mod_Shelf_producesatus(int produceId) {
		String sql = "delete from downproduce where produceId = ?";
		Object[] objects = {produceId};
		boolean a = execute_create_Update_Delete(sql, objects);
	
		sql = "insert into shelfproduce(produceId) values(?)";
		Object[] objects1 = {produceId};
		boolean b = execute_create_Update_Delete(sql, objects1);
	
		boolean c = false;
		if(a == true && b == true) {
			c = true;
		}
		return c;
	}

	@Override
	public ResultSet Display_down_producesatus() {
		String sql = "select * from downproduce";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

	@Override
	public ResultSet Display_shelf_producesatus() {
		String sql = "select * from shelfproduce";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}


}
