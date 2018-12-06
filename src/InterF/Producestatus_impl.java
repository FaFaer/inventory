package InterF;

import java.sql.ResultSet;
import java.util.List;


public interface Producestatus_impl{
	/**
	 * 显示下架商品
	 */
	  public ResultSet Display_down_producesatus();
	/**
	 * 显示上架商品
	 * @return
	 */
	  public ResultSet Display_shelf_producesatus();
	  /**
	   *下架商品，把商品从商家上架商品信息表删除，添加到下架商品表
	   * @param object
	   * @return
	   */
	  public boolean Mod_down_producesatus(int produceId);
	  /**
	   * 上架商品，把商品从商家下架商品信息表删除，添加到上架商品表
	   * @param object
	   * @return
	   */
	  public boolean Mod_Shelf_producesatus(int produceId);
}
