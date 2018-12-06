package Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import InterF.Produce_impl;
import Tools.TExecute;
import mode.Produce;
import mode.ProduceandType;
import mode.Producetype;

public class Produce_Dao extends TExecute implements Produce_impl {

	@Override
	public int Add_produce1(Produce produce) {

		String sql = "insert into produce (produceName,produceTypeId,producePrice,produceCode,produceNumber,producePic) values(?,?,?,?,0,?)";
		Object[] objects1 = { produce.getProduceName(), produce.getProduceTypeId(), produce.getProducePrice(),
				produce.getProduceCode(),produce.getProducePic()};
		boolean b = execute_create_Update_Delete(sql, objects1);
		sql = "select *from produce where produceName = ? and producePrice= ? and produceCode= ? ";
		Object[] objects = { produce.getProduceName(), produce.getProducePrice(), produce.getProduceCode() };
		ResultSet resultSet = execute_Raid(sql, objects);
		int c = 0;
		try {
			while (resultSet.next()) {
				c = resultSet.getInt("produceId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;	}

	public boolean Add_produce2(int produceId) {

		String sql = "insert into downproduce (produceId) values(?)";
		Object[] objects2 = { produceId };
		boolean c = execute_create_Update_Delete(sql, objects2);

		return c;
	}

	@Override
	public boolean Mod_produce(Produce produce) {
		String sql = "update produce set produceName = ?,producePrice = ?,produceCode = ?  where produceId = ?";
		Object[] objects = { produce.getProduceName(), produce.getProducePrice(), produce.getProduceCode(),
				produce.getProduceId() };
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public boolean Status_produce(int produceId) {
		String sql = "select from shelfproduce where produceId = ?";
		Object[] objects = { produceId };
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public boolean Add_producetype(Producetype producetype) {
		String sql = "insert into producetype(produceTypeName,typedescribe) values(?,?)";
		Object[] objects = { producetype.getProduceTypeName(), producetype.getTypeDescribe() };
		boolean b = execute_create_Update_Delete(sql, objects);
		return b;
	}

	@Override
	public ResultSet Display_produce() {
		String sql = "select * from produce";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

	@Override
	public ResultSet Display_producetype() {
		String sql = "select * from producetype";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		return resultSet;
	}

	@Override
	public Produce Return_produce(int produceId) {
		String sql = "select * from produce where produceId = ?";
		Object[] objects = { produceId };
		ResultSet resultSet = execute_Raid(sql, objects);
		Produce produce = null;
		try {
			while (resultSet.next()) {
				produce = new Produce(produceId, resultSet.getString("produceName"),
						Double.parseDouble(resultSet.getString("producePrice")), resultSet.getString("produceCode"),
						Integer.parseInt(resultSet.getString("produceTypeId")), 0 ,resultSet.getString("producePic"));
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}

		return produce;
	}

	@Override
	public Producetype Show_producetype(String produceTypeName, String typeDescribe) {
		String sql = "select * from producetype where produceTypeName = ? and typeDescribe = ?";
		Object[] objects = { produceTypeName, typeDescribe };
		ResultSet resultSet = execute_Raid(sql, objects);
		Producetype producetype = new Producetype(0, produceTypeName, typeDescribe);
		try {
			while (resultSet.next()) {
				producetype.setProduceTypeId(resultSet.getInt("produceTypeId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producetype;
	}

	@Override
	public ResultSet DisProduceandtype() {
		String sql = "select * from produce p,produceType t where p.produceTypeId = t.produceTypeId";
		Object[] objects = {};
		ResultSet resultSet = execute_Raid(sql, objects);
		
		return resultSet;
	}
}
