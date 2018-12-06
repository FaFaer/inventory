package InterF;

import java.sql.ResultSet;

import mode.Admin;
import mode.Supplier;




public interface Manager_impl {
	/**
	 * 显示经销商
	 * @return
	 */
	public ResultSet Display_dealer();
	/**
	 *显示供应商
	 * @return
	 */
	   public ResultSet Display_supplier();
	   /**
	    *登陆
	    * @return
	    */
		public boolean Login(Admin admin);
		/**
		 *注册
		 * @return
		 */
		public boolean Sign(Admin admin);
		/**
		 * 管理员查询
		 */
		public ResultSet Display_wearmanage(int adminTypeId);
}
