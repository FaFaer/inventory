package ServletI;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.image.impl.IntArgb;

import Factory.MainFactory;
import mode.Downproduce;
import mode.Produce;
import mode.Shelfproduce;

@WebServlet("/ShelfDownS")
public class ShelfDownS extends HttpServlet {
	private String op;
	private String msg;
	private boolean msgv = false;
	private String path;
	MainFactory mainFactory = null;
	List<Produce> produces = new ArrayList<Produce>();
	List<Object[]> names = new ArrayList<Object[]>();
	List<String> vals = new ArrayList<String>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		op = request.getParameter("op");

		// 上架商品的查询
		if (op == "shelf" || "shelf".equals(op)) {
			this.CheckShelf(request, response);
		}
		// 下架商品的查询
		if (op == "down" || "down".equals(op)) {
			this.CheckDown(request, response);
		} // 商品下架
		if (op == "producedown" || "producedown".equals(op)) {
			this.CheckPDown(request, response);
		} // 商品上架
		if (op == "produceshelf" || "produceshelf".equals(op)) {
			this.CheckPShelf(request, response);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void CheckPShelf(HttpServletRequest request, HttpServletResponse response) throws IOException {
		msg = "shelf";
		names.clear();
		vals.clear();
		names = (List<Object[]>) request.getSession().getAttribute("names");
		request.getSession().removeAttribute("names");
		for (int i = 0; i < names.size(); i++) {
			Object[] name = names.get(i);
			String checkv = request.getParameter((String) name[1]);
			if (checkv == "on" || "on".equals(checkv)) {
				String a = "上架失败";
				int produceId = Integer.parseInt(request.getParameter((String)name[0]));
				boolean b = mainFactory.get_Producestatus_impl().Mod_Shelf_producesatus(produceId);
				Produce produce = mainFactory.get_Produce_impl().Return_produce(produceId);
				if (b == true) {
					a = "上架成功";
					msgv = true;
				}
				String val ="商品编号："+produce.getProduceCode()+"  "+"商品名称："+produce.getProduceName() +"  "+ a;
				vals.add(val);
			}
		}
		request.setAttribute("vals", vals);
		request.setAttribute("msg", msg);
		request.setAttribute("msgv", msgv);
		path = "JSP/Value.jsp";
	}
/**
 * 商品下架
 * @param request
 * @param response
 * @throws IOException
 */
	private void CheckPDown(HttpServletRequest request, HttpServletResponse response) throws IOException {
		msg = "down";
		names.clear();
		vals.clear();
		names = (List<Object[]>) request.getSession().getAttribute("names");
		request.getSession().removeAttribute("names");
		for (int i = 0; i < names.size(); i++) {
			Object[] name = names.get(i);
			String checkv = request.getParameter((String) name[1]);
			if (checkv == "on" || "on".equals(checkv)) {
				String a = "下架失败";
				int produceId = Integer.parseInt(request.getParameter((String)name[0]));
				boolean b = mainFactory.get_Producestatus_impl().Mod_down_producesatus(produceId);
				Produce produce = mainFactory.get_Produce_impl().Return_produce(produceId);
				if (b == true) {
					a = "下架成功";
					msgv = true;
				}
				String val ="商品编号："+produce.getProduceCode()+"  "+"商品名称："+produce.getProduceName() +"  "+ a;
				vals.add(val);
			}
		}
		request.setAttribute("vals", vals);
		request.setAttribute("msg", msg);
		request.setAttribute("msgv", msgv);
		path = "JSP/Value.jsp";
	}

	private void CheckDown(HttpServletRequest request, HttpServletResponse response) throws IOException {
		produces.clear();
		ResultSet resultSet = mainFactory.get_Producestatus_impl().Display_down_producesatus();
		try {
			while (resultSet.next()) {
				int produceId = resultSet.getInt("produceId");
				Produce produce = mainFactory.get_Produce_impl().Return_produce(produceId);
				produces.add(produce);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("produces", produces);
		path = "JSP/DownStatus.jsp";
	}

	private void CheckShelf(HttpServletRequest request, HttpServletResponse response) throws IOException {
		produces.clear();
		ResultSet resultSet = mainFactory.get_Producestatus_impl().Display_shelf_producesatus();
		try {
			while (resultSet.next()) {
				int produceId = resultSet.getInt("produceId");
				Produce produce = mainFactory.get_Produce_impl().Return_produce(produceId);
				produces.add(produce);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("produces", produces);
		path = "JSP/ShelfStatus.jsp";
	}

}