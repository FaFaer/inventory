package Factory;

import Impl.In_Dao;
import Impl.Manage_Dao;
import Impl.Out_Dao;
import Impl.Produce_Dao;
import Impl.Producestatus_Dao;
import Impl.Wear_Dao;
import InterF.In_impl;
import InterF.Manager_impl;
import InterF.Out_impl;
import InterF.Produce_impl;
import InterF.Producestatus_impl;
import InterF.Wear_impl;

public class MainFactory {
	private static In_impl in_impl = null;
	private static Manager_impl manager_impl = null;
	private static Out_impl out_impl = null;
	private static Produce_impl produce_impl = null;
	private static Producestatus_impl producestatus_impl = null;
	private static Wear_impl wear_impl = null;

	public static In_impl get_In_impl() {
		if (in_impl == null) {
			in_impl = new In_Dao();
		}
		return in_impl;
	}

	public static Manager_impl get_Manager_impl() {
		if (manager_impl == null) {
			manager_impl = new Manage_Dao();
		}
		return manager_impl;
	}

	public static Out_impl get_Out_impl() {
		if (out_impl == null) {
			out_impl = new Out_Dao();
		}
		return out_impl;
	}

	public static Produce_impl get_Produce_impl() {
		if (produce_impl == null) {
			produce_impl = new Produce_Dao();
		}
		return produce_impl;
	}

	public static Producestatus_impl get_Producestatus_impl() {
		if (producestatus_impl == null) {
			producestatus_impl = new Producestatus_Dao();
		}
		return producestatus_impl;
	}

	public static Wear_impl get_Wear_impl() {
		if (wear_impl == null) {
			wear_impl = new Wear_Dao();
		}
		return wear_impl;
	}

}
