package InterF;

import java.sql.ResultSet;

import mode.Outlibrary;
import mode.Position;
import mode.Produce;
import mode.Wearposition;

public interface Out_impl {
	/**
	 * 先删除仓位详细表中该商品为空的记录，然后在查询仓位详细表中该商品的纪录
	 * @param produceId
	 * @return
	 */
public ResultSet Query_position(int produceId);
/**
 * 仓位号查询仓库区域
 * @param wearPositionId
 * @return
 */
public ResultSet Query_wearposition(String wearPositionId);
/**
 *出库表添加记录
 * @param income
 * @return
 */
public boolean Add_out(Outlibrary outlibrary);
/**
 *修改仓位信息表，减去出库的数量
 * @param position
 * @return
 */
public boolean Mod_position(Position position);
/**
 * 修改库存表，减去出库的数量
 * @param stock
 * @return
 */
public boolean Cut_Stock(Produce produce);

}
