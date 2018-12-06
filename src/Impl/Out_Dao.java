package Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import InterF.Out_impl;
import Tools.TExecute;
import mode.Outlibrary;
import mode.Position;
import mode.Produce;
import mode.Wearposition;

public class Out_Dao extends TExecute implements Out_impl {

	@Override
	public boolean Add_out(Outlibrary outlibrary) {
		String sql = "insert into outlibrary(dealerId,wearPosition,adminId,produceId,outLibraryNumber,outLibraryTime) values(?,?,?,?,?,now())";
		Object[] objects = {outlibrary.getDealerId(), outlibrary.getWearPosition(), 1, outlibrary.getProduceId(),
				outlibrary.getOutLibraryNumber()};
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}
	
	@Override
	public boolean Mod_position(Position position) {
		String sql = "update position set positioninNumber = positioninNumber - ? where produceId = ? and positionId = ?";
		Object[] objects = {position.getPositioninNumber(),position.getProduceId(),position.getPositionId()};
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public boolean Cut_Stock(Produce produce) {
		String sql = "update produce set produceNumber=produceNumber - ? where produceId = ?";
		Object[] objects = { produce.getProduceNumber(), produce.getProduceId() };
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public ResultSet Query_position(int produceId) {
		String sql = "delete from position where produceId = ? and positioninNumber = 0";
		Object[] objects = {produceId};
		boolean b = execute_create_Update_Delete(sql, objects);
		
		sql = "select *from position where produceId = ?";
		ResultSet resultSet = execute_Raid(sql, objects);
		
		return resultSet;
	}

	@Override
	public ResultSet Query_wearposition(String wearPositionId) {
		String sql = "select *from wearposition where wearPositionId = ?";
		Object[] objects = {wearPositionId};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

}
