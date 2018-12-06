package ServletI;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.sun.corba.se.spi.copyobject.ObjectCopier;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import Factory.MainFactory;
import mode.Admin;
import mode.Dealer;
import mode.Position;
import mode.Produce;
import mode.Producetype;
import mode.Supplier;
import mode.Wear;
import mode.Weararea;
import mode.Wearposition;

@WebServlet("/PositionS")
public class PositionS extends HttpServlet {
	private String op;
	private String path = "";
	private String msg;
	private boolean msgv;
	private HashSet<Wear> wears = new HashSet<Wear>();
	private List<Weararea> wearareas = new ArrayList<Weararea>();
	private List<Position> positions = new ArrayList<Position>();
	private List<Wearposition> wearpositions = new ArrayList<Wearposition>();
	private List<Admin> admins = new ArrayList<>();
	MainFactory mainFactory;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		op = request.getParameter("op");
		// 仓位具体商品信息查询
		if (op == "position" || "position".equals(op)) {
			op = request.getParameter("op");
			this.doPosition(request, response);
		}
		// AJAX局部数据替换
		if (op == "position1" || "position1".equals(op)) {
			op = request.getParameter("op");
			this.doPosition1(request, response);
		}
		// AJAX局部数据替换
				if (op == "position2" || "position2".equals(op)) {
					op = request.getParameter("op");
					this.doPosition2(request, response);
				}
		// 仓位查询
		if (op == "wearposition" || "wearposition".equals(op)) {
			op = request.getParameter("op");
			this.doWearposition(request, response);
		}
		// 仓库添加
		if (op == "addwear" || "addwear".equals(op)) {
			op = request.getParameter("op");
			this.doAddwear(request, response);
		}
		// 区域添加查询
		if (op == "addarea" || "addarea".equals(op)) {
			op = request.getParameter("op");
			this.doAddarea(request, response);
		}
		// 仓位添加
		if (op == "addposition" || "addposition".equals(op)) {
			op = request.getParameter("op");
			this.doAddposition(request, response);
		}

		// 添加之前仓库和区域的查询
		if (op == "disposition" || "disposition".equals(op)) {

			op = request.getParameter("op");
			this.doDisposition(request, response);
		}
		// 显示仓库管理员
		if (op == "disadmin" || "disadmin".equals(op)) {
			op = request.getParameter("op");
			this.doDisadmin(request, response);
		}

	}

	private void doPosition2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int position = Integer.parseInt(request.getParameter("sel3"));
		ResultSet resultSet = mainFactory.get_Wear_impl().Display_position1(position);
		response.getWriter().print("<tr><td>商品条形码</td><td>商品名称</td><td>商品数量</td></tr>");
		try {
			while(resultSet.next()) {
				Produce produce = mainFactory.get_Produce_impl().Return_produce(resultSet.getInt("produceId"));
				response.getWriter().print("<tr><td>"+produce.getProduceCode()+"</td><td>"+produce.getProduceName()+"</td><td>"
				+resultSet.getInt("positioninNumber")+"</td></tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	// AJAX局部数据替换仓位
	private void doPosition1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int areaId = Integer.parseInt(request.getParameter("sel2"));
		ResultSet resultSet = mainFactory.get_Wear_impl().Display_position(0, areaId);
		HashSet<Integer> areaIdH = new HashSet<Integer>();
		try {
			while (resultSet.next()) {
				areaIdH.add(resultSet.getInt("wearPositionId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}response.getWriter().print("<option>  </option>");
		for (Integer Id : areaIdH) {
			response.getWriter().print("<option>" + Id + "</option>");
		}

	}

	/**
	 * 仓库管理员的下拉框
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doDisadmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		admins.clear();
		ResultSet resultSet = mainFactory.get_Manager_impl().Display_wearmanage(1);
		try {
			while (resultSet.next()) {
				Admin admin = new Admin(resultSet.getInt("adminId"), resultSet.getString("adminName"));
				admins.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("admins", admins);
		response.sendRedirect("JSP/AddWare.jsp");
	}

	/**
	 * 显示仓库和区域的下拉框
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void doDisposition(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String path = null;
		String aString = request.getParameter("wera");
		String bString = request.getParameter("area");
		if (aString == "true" || "true".equals(aString)) {
			wears.clear();
			ResultSet resultSet = mainFactory.get_Wear_impl().Display_wear();
			try {
				while (resultSet.next()) {
					Wear wear = new Wear(resultSet.getInt("wearId"), resultSet.getString("wearName"));
					wears.add(wear);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.getWriter().print("<option></option>");
			for (Wear wear : wears) {
				response.getWriter()
						.print("<option value=" + wear.getWearId() + ">" + wear.getWearName() + "</option>");
			}

		}

		if (bString == "true" || "true".equals(bString)) {
			wearareas.clear();
			if (request.getParameter("sel1") != "") {

				ResultSet resultSet = mainFactory.get_Wear_impl()
						.Display_area(Integer.parseInt(request.getParameter("sel1")));
				try {
					while (resultSet.next()) {
						Weararea weararea = new Weararea(resultSet.getInt("wearAreaId"), resultSet.getInt("wearId"));
						wearareas.add(weararea);
					}
				} catch (SQLException e) {
				}
				response.getWriter().print("<option></option>");
				for (Weararea weararea : wearareas) {
					response.getWriter().print("<option>" + weararea.getWearAreaId() + "</option>");
				}

			}
		}

	}

	/**
	 * 添加仓位
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doAddposition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		msg = "addposition";
		System.out.println("出来了？");
		int wear = Integer.parseInt(request.getParameter("wear"));
		int area = Integer.parseInt(request.getParameter("area"));
		String desc = request.getParameter("size");
		Wearposition wearposition = new Wearposition(desc, 0, wear, area);
		boolean msgv = mainFactory.get_Wear_impl().Add_wearposition(wearposition);
		request.getSession().setAttribute("msg", msg);
		request.getSession().setAttribute("msgv", msgv);
		System.out.println("出来了？");
		System.out.println(msgv);
		response.sendRedirect("JSP/Value.jsp");
	}

	/**
	 * 添加区域
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doAddarea(HttpServletRequest request, HttpServletResponse response) throws IOException {
		msg = "addarea";
		int wear = Integer.parseInt(request.getParameter("wear"));
		String desc = request.getParameter("desc");
		Weararea weararea = new Weararea(desc, wear);
		boolean msgv = mainFactory.get_Wear_impl().Add_weararea(weararea);
		request.getSession().setAttribute("msg", msg);
		request.getSession().setAttribute("msgv", msgv);
		response.sendRedirect("JSP/Value.jsp");
	}

	/**
	 * 添加仓库
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doAddwear(HttpServletRequest request, HttpServletResponse response) throws IOException {
		msg = "addwear";
		String wear = request.getParameter("wear");
		String address = request.getParameter("address");
		String size = request.getParameter("size");
		int admin = Integer.parseInt(request.getParameter("admin"));
		Wear wear2 = new Wear(size, address, wear, admin);
		boolean msgv = mainFactory.get_Wear_impl().Add_wear(wear2);
		request.getSession().setAttribute("msg", msg);
		request.getSession().setAttribute("msgv", msgv);
		response.sendRedirect("JSP/Value.jsp");
	}

	/**
	 * 
	 * 仓位查询
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doWearposition(HttpServletRequest request, HttpServletResponse response) throws IOException {

		positions.clear();
		request.getSession().setAttribute("supplier", Integer.parseInt(request.getParameter("supplierId")));
		ResultSet resultSet = MainFactory.get_Wear_impl().Display_position();
		try {
			while (resultSet.next()) {
				Wearposition wearposition = new Wearposition(resultSet.getInt("wearPositionId"),
						resultSet.getString("wearPositionDesc"), resultSet.getInt("position_full"),
						resultSet.getInt("wearId"), resultSet.getInt("wearAreaId"));
				wearpositions.add(wearposition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("wearpositions", wearpositions);
		response.sendRedirect(path);
	}

	/**
	 * 仓位具体商品信息查询
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doPosition(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ResultSet resultSet = mainFactory.get_Wear_impl().Display_wear();
		wears.clear();
		try {
			while (resultSet.next()) {
				Wear wear = new Wear(resultSet.getInt("wearId"), resultSet.getString("wearName"));
				wears.add(wear);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("wears", wears);
		response.sendRedirect("JSP/Warehouse.jsp");
	}
}
