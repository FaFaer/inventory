package Impl;

import java.sql.ResultSet;

import InterF.Wear_impl;
import Tools.TExecute;
import mode.Wear;
import mode.Weararea;
import mode.Wearposition;

public class Wear_Dao extends TExecute implements Wear_impl {

	@Override
	public boolean Add_wear(Wear wear) {
		String sql = "insert into wear (adminId,wearDesc,wearAddress,wearName) values(?,?,?,?)";
		Object[] objects = { wear.getAdminId(), wear.getWearDesc(), wear.getWearAddress(), wear.getWearName() };
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public boolean Add_weararea(Weararea weararea) {
		String sql = "insert into weararea (wearId,wearAreaDesc) values(?,?)";
		Object[] objects = { weararea.getWearId(), weararea.getWearAreaDesc() };
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public boolean Add_wearposition(Wearposition wearposition) {
		String sql = "insert into wearposition (wearId,wearAreaId,wearPositionDesc,position_full) values(?,?,?,0)";
		Object[] objects = { wearposition.getWearId(), wearposition.getWearAreaId(),
				wearposition.getWearPositionDesc() };
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public ResultSet Display_position() {
		String sql = "select *from wearposition";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

	@Override
	public ResultSet Display_area(int wearId) {
		String sql = "select *from weararea  where wearId=?";
		Object[] objects = {wearId};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

	@Override
	public ResultSet Display_wear() {
		String sql = "select *from wear";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

	@Override
	public ResultSet Display_position(int wearId, int areaId) {
		ResultSet resultSet = null;
		if (wearId != 0) {
			String sql = "select *from wearposition where wearId = ?";
			Object[] objects = { wearId };
			resultSet = execute_Raid(sql, objects);

		}
		if (areaId != 0) {
			String sql = "select *from wearposition where wearAreaId = ?";
			Object[] objects = { areaId };
			resultSet = execute_Raid(sql, objects);

		}
		return resultSet;
	}

	@Override
	public ResultSet Display_position1(int positionId) {
		String sql = "select *from position where positionId = ?";
		Object[] objects = {positionId};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

}
