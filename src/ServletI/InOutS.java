package ServletI;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.tracing.dtrace.ProviderAttributes;

import Factory.MainFactory;
import mode.Dealer;
import mode.Income;
import mode.Outlibrary;
import mode.Position;
import mode.Produce;
import mode.Supplier;
import mode.Wear;
import mode.Weararea;
import mode.Wearposition;

@WebServlet("/InOutS")
public class InOutS extends HttpServlet {
	private String op;
	private String msg;
	private boolean msgv = false;
	MainFactory mainFactory = null;
	private List<Wear> wears = new ArrayList<Wear>();
	private List<Position> positions = new ArrayList<Position>();
	private List<Weararea> wearareas = new ArrayList<Weararea>();
	private List<Wearposition> wearpositions = new ArrayList<Wearposition>();
	private List<Supplier> suppliers = new ArrayList<Supplier>();
	private List<Dealer> dealers = new ArrayList<Dealer>();
	private List<Object[]> names = new ArrayList<Object[]>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		op = request.getParameter("op");

		// 经销商的查询
		if (op == "out1" || "out1".equals(op)) {
			this.doOut1(request, response);
		}
		// 供应商的查询
		if (op == "in1" || "in1".equals(op)) {
			this.doIn1(request, response);
		}
		// 仓库查询，查询三张表
		if (op == "wearposition" || "wearposition".equals(op)) {
			this.doWearposition(request, response);
		}
		// 入库
		if (op == "in2" || "in2".equals(op)) {
			this.doIn2(request, response);
		}
		// 商品所在仓库查询
		if (op == "out2" || "out2".equals(op)) {
			this.doOut2(request, response);
		}
		// 出库
		if (op == "out3" || "out3".equals(op)) {
			this.doOut3(request, response);
		}

	}

	private String allout(Position position, int dealerId) {
		Outlibrary outlibrary = new Outlibrary(position.getPositioninNumber(), new Date(), dealerId,
				position.getProduceId(), 1, position.getPositionId());
		boolean b = mainFactory.get_Out_impl().Add_out(outlibrary);
		boolean c = mainFactory.get_Out_impl().Mod_position(position);
		Produce produce = new Produce(position.getProduceId(), position.getPositioninNumber());
		boolean d = mainFactory.get_Out_impl().Cut_Stock(produce);
		String val = "失败";
		if (b == true && c == true && d == true) {
			val = "成功";
			msgv = true;
		}

		return val;
	}

	private void doOut3(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		int produceId = Integer.parseInt(request.getSession().getAttribute("produceId").toString());
		int dealerId = Integer.parseInt(request.getSession().getAttribute("dealerId").toString());
		msg = "Out";
		names.clear();
		names = (List<Object[]>) request.getSession().getAttribute("names");
		request.getSession().removeAttribute("names");
		List<String> val = new ArrayList<String>();
		for(Object[] name : names) {
			int wearId =  Integer.parseInt(request.getParameter((String) name[0]));
			int areaId =  Integer.parseInt(request.getParameter((String) name[1]));
			int positionId =  Integer.parseInt(request.getParameter((String) name[2]));
			int positioninNumber =  Integer.parseInt(request.getParameter((String) name[3]));
			String check =  request.getParameter((String) name[4]);
			if(check == "on" || "on" .equals(check)) {
				Position position = new Position(positioninNumber, positionId, produceId, areaId, wearId);
				String b = allout(position, dealerId);
				val.add("仓位：" + positionId + "   " + "出库商品:" + positioninNumber + "件" +"  "+ b);
			}
		}request.setAttribute("msgv", msgv);
		request.setAttribute("msg", msg);
		request.setAttribute("val", val);
		request.getRequestDispatcher("JSP/Value.jsp").forward(request, response);

	}

	private void doOut2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int produced = Integer.parseInt(request.getSession().getAttribute("produceId").toString());
		int dealerId = Integer.parseInt(request.getParameter("dealerId").toString());
			positions.clear();
		ResultSet resultSet = mainFactory.get_Out_impl().Query_position(produced);
		try {
			while (resultSet.next()) {
				Position position = new Position(resultSet.getInt("positioninNumber"), resultSet.getInt("positionId"),
						resultSet.getInt("produceId"), resultSet.getInt("areaId"), resultSet.getInt("wearId"),
						resultSet.getInt("positionAId"));
				positions.add(position);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("dealerId", dealerId);
		request.setAttribute("positions", positions);
		request.getRequestDispatcher("JSP/OutPosition.jsp").forward(request, response);;
	}

	private String allin(int incomeNumber, int supplierId, int produceId, int positionId, int areaId, int wearId,
			String check) {
		Income income = new Income(incomeNumber, new Date(), supplierId, 1, produceId, positionId);
		boolean b = mainFactory.get_In_impl().Add_income(income);
		Position position = new Position(incomeNumber, positionId, produceId, areaId, wearId);
		boolean c = mainFactory.get_In_impl().Add_position(position);
		Produce produce = new Produce(produceId, incomeNumber);
		boolean d = mainFactory.get_In_impl().Add_Stock(produce);
		if (check != null) {
			boolean e = mainFactory.get_In_impl().Update_wearposition(positionId);
		}
		boolean j = false;
		if (b == true && c == true && d == true && d == true) {
			j = true;
		}
		String aString = "";
		if (j == true) {
			aString = "成功";
			msgv = true;
		} else {
			aString = "失败";
		}
		return aString;
	}

	private void doIn2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		msg = "In";
		int produceId = Integer.parseInt(request.getSession().getAttribute("produceId").toString());
		int supplierId = Integer.parseInt(request.getSession().getAttribute("supplierId").toString());
			names.clear();
		names = (List<Object[]>) request.getSession().getAttribute("names");
		List<String> val = new ArrayList<String>();
		for (Object[] name : names) {
			String check = request.getParameter((String) name[4]);
			int incomeNumber = Integer.parseInt(request.getParameter((String) name[3]));
			if (incomeNumber != 0) {
				int areaId = Integer.parseInt(request.getParameter((String) name[1]));
				int wearId = Integer.parseInt(request.getParameter((String) name[0]));
				int positionId = Integer.parseInt(request.getParameter((String) name[2]));
				String b = allin(incomeNumber, supplierId, produceId, positionId, areaId, wearId, check);
				val.add("仓位：" + positionId + "   " + "入库商品:" + incomeNumber + "件" +"  "+ b);
			}
		}
		request.setAttribute("msgv", msgv);
		request.setAttribute("msg", msg);
		request.setAttribute("val", val);
		request.getRequestDispatcher("JSP/Value.jsp").forward(request, response);;
	}

	/**
	 * 仓库查询（仓库表，区域表和仓位表）
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 * @throws SQLException
	 */
	private void doWearposition(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (wearareas != null) {
			wearareas.clear();
		}
		if (wearpositions != null) {
			wearpositions.clear();
		}
		if (wears != null) {
			wears.clear();
		}
		request.getSession().setAttribute("inumber", request.getParameter("inumber"));
		request.getSession().setAttribute("supplierId", request.getParameter("supplierId"));
		ResultSet resultSet = mainFactory.get_In_impl().Display_Wear();
		try {
			while (resultSet.next()) {
				Wear wear = new Wear(resultSet.getInt("wearId"), resultSet.getString("wearDesc"),
						resultSet.getString("wearAddress"), resultSet.getString("wearName"),
						resultSet.getInt("adminId"));
				wears.add(wear);
				request.getSession().setAttribute("wears", wears);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		resultSet = mainFactory.get_In_impl().Display_Area();
		try {
			while (resultSet.next()) {
				Weararea weararea = new Weararea(resultSet.getInt("wearAreaId"), resultSet.getString("wearAreaDesc"),
						resultSet.getInt("wearId"));
				wearareas.add(weararea);
				request.getSession().setAttribute("wearareas", wearareas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultSet = mainFactory.get_In_impl().Display_position();
		try {
			while (resultSet.next()) {
				Wearposition wearposition = new Wearposition(resultSet.getInt("wearPositionId"),
						resultSet.getString("wearPositionDesc"), resultSet.getInt("position_full"),
						resultSet.getInt("wearId"), resultSet.getInt("wearAreaId"));
				wearpositions.add(wearposition);
				request.getSession().setAttribute("wearpositions", wearpositions);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("JSP/InPosition.jsp").forward(request, response);;
	}

	/**
	 * 入库供应商的查询
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doIn1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int produceId = Integer.parseInt(request.getParameter("produceId"));
		request.getSession().setAttribute("produceId", produceId);
		ResultSet resultSet = mainFactory.get_Manager_impl().Display_supplier();
		if (suppliers != null) {
			suppliers.clear();
		}
		try {

			while (resultSet.next()) {
				Supplier supplier = new Supplier(resultSet.getString("supplierName"),
						resultSet.getString("supplierCredit"), resultSet.getString("supplierTel"),
						resultSet.getString("supplierAddress"));
				suppliers.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("suppliers", suppliers);
		request.getRequestDispatcher("JSP/In1.jsp").forward(request, response);;
	}
	

	/**
	 * 出库经销商的查询
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doOut1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int produceId = Integer.parseInt(request.getParameter("produceId"));
		request.getSession().setAttribute("produceId", produceId);
		ResultSet resultSet = mainFactory.get_Manager_impl().Display_dealer();
		if (dealers != null) {
			dealers.clear();
		}
		try {
			while (resultSet.next()) {
				Dealer dealer = new Dealer(resultSet.getInt("dealerId"), resultSet.getString("dealerName"),
						resultSet.getString("dealerAddress"), resultSet.getString("dealerTel"));
				dealers.add(dealer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("dealers", dealers);
		request.getRequestDispatcher("JSP/Out1.jsp").forward(request, response);;
	}
}