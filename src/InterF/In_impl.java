package InterF;


import java.sql.ResultSet;

import mode.Income;
import mode.Position;
import mode.Produce;

   public interface In_impl {
	/**
	 * 添加入库表记录
	 * @param income
	 * @return
	 */
	public boolean Add_income(Income income);
	/**
	 *添加仓位详细表记录
	 * @param position
	 * @return
	 */
    public boolean Add_position(Position position);
    /**
             *把仓位设置为以满
     * @param b
     * @return
     */
    public boolean Update_wearposition(int wearPositionId);
    /**
     * 入库的时候添加库存
     * @param stock
     * @return
     */
    public boolean Add_Stock(Produce produce);
    /**
     * 显示未满的仓位
     * @return
     */
    public ResultSet Display_position();
    /**
     * 显示区域
     * @return
     */
    public ResultSet Display_Area();
    /**
     * 显示仓库
     * @return
     */
    public ResultSet Display_Wear();
   
}
