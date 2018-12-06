package InterF;

import java.sql.ResultSet;
import mode.Produce;
import mode.ProduceandType;
import mode.Producetype;

public interface Produce_impl {

	/**
	 * 商品编号查询商品
	 * @param produceName
	 * @return
	 */
	public Produce Return_produce(int produceId);
	/**
	 * 添加商品信息（商品信息表）
	 * @param produce
	 * @return
	 */
	 public int Add_produce1(Produce produce);
	 /**
		 * 添加商品信息（下架表）
		 * @param produce
		 * @return
		 */
	 public boolean Add_produce2(int produceId);
	 /**
	  * 显示所有商品
	  * @return
	  */
    public ResultSet Display_produce();
    /**
     * 修改商品信息
     * @param produce
     * @return
     */
    public boolean Mod_produce(Produce produce);
    /**
     *用商品Id在上架表里查询，如果查询到就是上架状态，如果没有查询到就是下架状态̬
     * @param produceCode
     * @return
     */
    public boolean Status_produce(int produceId);
    /**
	 * 显示商品类型
	 * @return
	 */
	  public ResultSet Display_producetype();
	  /**
	   * 用商品类型名称查询商品类型id
	   * @param produceName
	   * @return
	   */
	  public Producetype Show_producetype(String produceName,String typeDescribe);
	  /**
	   * 添加商品类型
	   * @param producetype
	   * @return
	   */
	  public boolean Add_producetype(Producetype producetype);
	  /**
	   * 显示商品信息和商品类型表的联合
	   * @return
	   */
	  public ResultSet DisProduceandtype();
    
}
