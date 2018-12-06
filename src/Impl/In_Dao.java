package Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import InterF.In_impl;
import Tools.TExecute;
import mode.Income;
import mode.Position;
import mode.Produce;

public class In_Dao extends TExecute implements In_impl {

	@Override
	public boolean Add_income(Income income) {
		String sql = "insert into income(wearPositionId,supplierId,adminId,produceId,incomeNumber,incomeTime) values (?,?,?,?,?,now())";
		Object[] objects = {income.getProduceId(),income.getSupplierId(), 1, income.getProduceId(), income.getIncomeNumber()};
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}
	
	@Override
	public boolean Add_position(Position position) {
		boolean b = false;
		String sql = "select * from position where positionId = ? and produceId = ?";
		Object[] objects = {position.getPositionId(),position.getProduceId()};
		ResultSet resultSet = execute_Raid(sql, objects);
		try {
			resultSet.last();
			if(resultSet.getRow() == 0) {
				sql = " insert into position (positioninNumber,positionId,produceId,areaId,wearId) values(?,?,?,?,?)";
				Object[] objects1= {position.getPositioninNumber(),position.getPositionId(),position.getProduceId(),position.getAreaId(),position.getWearId()};
				b = execute_create_Update_Delete(sql, objects1);
			}
			else {
				sql = "Update position set positioninNumber = positioninNumber + ? where positionAId = ?";
				Object[] objects2 = {position.getPositioninNumber(),resultSet.getInt("positionAId")};
				b = execute_create_Update_Delete(sql, objects2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean Update_wearposition(int wearPositionId) {
	
		String sql = "update wearPosition set position_Full = 1 where wearPositionId = ? ";
		Object[] objects = {wearPositionId};
		boolean b = execute_create_Update_Delete(sql, objects);
	
		return b;
	}

	@Override
	public boolean Add_Stock(Produce produce) {
		String sql = "update produce set produceNumber=produceNumber +? where produceId = ?";
		Object[] objects = {produce.getProduceNumber(),produce.getProduceId()};
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public ResultSet Display_position() {
		String sql = "select * from wearposition where position_full = 0";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

	@Override
	public ResultSet Display_Area() {
		String sql = "select * from weararea";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

	@Override
	public ResultSet Display_Wear() {
		String sql = "select * from wear";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

}
