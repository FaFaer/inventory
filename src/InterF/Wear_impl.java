package InterF;

import java.sql.ResultSet;

import mode.Wear;
import mode.Weararea;
import mode.Wearposition;

public interface Wear_impl {
	/**
	 * 添加仓库信息
	 * 
	 * @param wear
	 * @return
	 */
	public boolean Add_wear(Wear wear);

	/**
	 * 添加仓库区域
	 * 
	 * @param wear
	 * @return
	 */
	public boolean Add_weararea(Weararea weararea);

	/**
	 * 添加仓库仓位
	 * 
	 * @param wear
	 * @return
	 */
	public boolean Add_wearposition(Wearposition wearposition);

	/**
	 * 显示仓库所有信息
	 * 
	 * @return
	 */
	public ResultSet Display_position();

	/**
	 * 显示区域
	 * 
	 * @return
	 */
	public ResultSet Display_area(int wearId);

	/**
	 * 显示仓库
	 * 
	 * @return
	 */
	public ResultSet Display_wear();

	/**
	 *用仓位查询商品
	 * 
	 * @return
	 */
	public ResultSet Display_position(int wearId, int areaId);
	/**
	 * 仓位号查询商品
	 * @param wearId
	 * @param areaId
	 * @return
	 */
	public ResultSet Display_position1(int positionId);
}