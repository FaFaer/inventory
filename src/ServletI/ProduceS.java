package ServletI;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.org.apache.bcel.internal.generic.NEW;

import Factory.MainFactory;
import mode.Produce;
import mode.ProduceandType;
import mode.Producetype;

@WebServlet("/ProduceS")
public class ProduceS extends HttpServlet {
	private String op = null;
	private String path = null;
	private String msg = "";
	private boolean msgv = false;
	private List<Producetype> producetypes = new ArrayList<Producetype>();
	private List<Produce> produces = new ArrayList<Produce>();
	private List<ProduceandType> produceandTypes = new ArrayList<ProduceandType>();
	private Produce produce = null;

	private MainFactory mainFactory;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		op = request.getParameter("op");
		// 转到添加商品
		if (op == "addproduce" || "addproduce".equals(op)) {
			this.doAddproduce(request, response);
		}
		// 添加商品类型
		else if (op == "addproducetype" || "addproducetype".equals(op)) {
			this.doAddproducetype(request, response);
		}
		// 下拉框显示商品类型
		else if (op == "distype" || "distype".equals(op)) {
			this.doDistype(request, response);
		}// 表格显示商品类型
		else if (op == "distype1" || "distype1".equals(op)) {
			this.doDistype1(request, response);
		}
		// 出库入库的商品显示
				else if (op == "distype2" || "distype2".equals(op)) {
					this.doDistype2(request, response);
				}
		// 显示商品信息
		else if (op == "disproduce" || "disproduce".equals(op)) {
			this.disProduce(request, response);
		}
		// 显示商品到修改商品之间
		else if (op == "distomod" || "distomod".equals(op)) {
			this.doDistomod(request, response);
		}
		// 修改商品信息
		else if (op == "modproduce" || "modproduce".equals(op)) {
			this.doModproduce(request, response);
		}

	}
private void doDistype2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ResultSet resultSet = mainFactory.get_Produce_impl().DisProduceandtype();
	ProduceandType produceandType = null;
	HashSet<String> producetype = new HashSet<>();
		produceandTypes.clear();
	try {

		while (resultSet.next()) {
			produceandType = new ProduceandType(resultSet.getInt("produceId"),
					resultSet.getString("produceName"), resultSet.getDouble("producePrice"),
					resultSet.getString("produceCode"), resultSet.getInt("produceTypeId"),
					resultSet.getInt("produceNumber"), resultSet.getString("produceTypeName"),
					resultSet.getString("typeDescribe"));
			producetype.add(produceandType.getProduceTypeName());
			produceandTypes.add(produceandType);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	request.setAttribute("produceandTypes", produceandTypes);
	request.setAttribute("producetype", producetype);

     path = "JSP/"+request.getParameter("path")+".jsp";
request.getRequestDispatcher(path).forward(request, response);

	}

/**
 * 表格显示商品类型
 * @param request
 * @param response
 * @throws IOException 
 */
	private void doDistype1(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ResultSet resultSet = null;
		resultSet = mainFactory.get_Produce_impl().Display_producetype();
		producetypes.clear();
		try {

			while (resultSet.next()) {
				Producetype producetype = new Producetype(resultSet.getInt("produceTypeId"),
						resultSet.getString("produceTypeName"), resultSet.getString("typeDescribe"));
				producetypes.add(producetype);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Producetype producetype : producetypes) {
			response.getWriter().print("<tr><td>"+producetype.getProduceTypeName()+"</td>"+
					"<td>"+producetype.getTypeDescribe()+"</td></tr>");
		}
		
	}

	/**
	 * 表格显示商品信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void disProduce(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultSet resultSet = mainFactory.get_Produce_impl().Display_produce();
		produces.clear();
		try {
			while (resultSet.next()) {
				Produce produce = new Produce(Integer.parseInt(resultSet.getString("produceId")),
						resultSet.getString("produceName"), Double.parseDouble(resultSet.getString("producePrice")),
						resultSet.getString("produceCode"), Integer.parseInt(resultSet.getString("produceTypeId")),
						resultSet.getInt("produceNumber"), resultSet.getString("producePic"));
				produces.add(produce);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Produce produce : produces) {
      response.getWriter().print("<tr>" +"<td><a href='"+request.getContextPath()+"/ProduceS?op=distomod&produceId="+produce.getProduceId()+"'>"+  produce.getProduceCode() +"</a></td>"
    		                            +"<td><a href='"+request.getContextPath()+"/ProduceS?op=distomod&produceId="+produce.getProduceId()+"'>"+  produce.getProduceName() +"</a></td>"
    		                            +"<td><a href='"+request.getContextPath()+"/ProduceS?op=distomod&produceId="+produce.getProduceId()+"'>"+  produce.getProducePrice() +"</a></td>"+ "</tr>");
		}

	}

	/**
	 * 添加商品信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doAddproduce(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmartUpload smartUpload = new SmartUpload();
		SimpleDateFormat time = new SimpleDateFormat("YYYYMMDDhhmmssSSS");
		smartUpload.initialize(this.getServletConfig(), request, response);
		String path = null;
		try {
			smartUpload.upload();
			Files files = smartUpload.getFiles();
			File file = files.getFile(0);
			String fileName = time.format(new Date()) + file.getFieldName();
			path = fileName + "." + file.getFileExt();
			file.saveAs("f:\\pc\\" + path, SmartUpload.SAVE_PHYSICAL);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		Request smartUploadRequest = smartUpload.getRequest();
		String Name = smartUploadRequest.getParameter("Name");
		String Code = smartUploadRequest.getParameter("Code");
		Double Price = Double.parseDouble(smartUploadRequest.getParameter("Price"));
		int Type = Integer.parseInt(smartUploadRequest.getParameter("Type"));
		msg = "addproduce";
		Produce produce = new Produce(0, Name, Price, Code, Type, 0, path);
		int b = mainFactory.get_Produce_impl().Add_produce1(produce);
		boolean c = false;
		c = mainFactory.get_Produce_impl().Add_produce2(b);

		if (c == true) {
			msgv = true;
		}
		path = "JSP/Value.jsp";
		request.setAttribute("msg", msg);
		request.setAttribute("msgv", msgv);
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * 下拉框显示商品类型
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void doDistype(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ResultSet resultSet = null;
		resultSet = mainFactory.get_Produce_impl().Display_producetype();
		producetypes.clear();
		try {

			while (resultSet.next()) {
				Producetype producetype = new Producetype(resultSet.getInt("produceTypeId"),
						resultSet.getString("produceTypeName"), resultSet.getString("typeDescribe"));
				producetypes.add(producetype);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Producetype producetype : producetypes) {
			response.getWriter().print("<option value =" + producetype.getProduceTypeId() + ">"
					+ producetype.getProduceTypeName() + "</option>");
		}
	}
/**
	 * 添加商品类型
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doAddproducetype(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String producetype2 = request.getParameter("producetype");
		String typedescribe = request.getParameter("typedescribe");
		msg = "addtype";
		Producetype producetype1 = new Producetype(1, producetype2, typedescribe);
		boolean b = mainFactory.get_Produce_impl().Add_producetype(producetype1);
		if (b == true) {
			msgv = true;
		}
		path = "JSP/Value.jsp";
		request.setAttribute("msg", msg);
		request.setAttribute("msgv", msgv);
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * 显示商品到修改商品
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	protected void doDistomod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int produceId = Integer.parseInt(request.getParameter("produceId"));
		produce = mainFactory.get_Produce_impl().Return_produce(produceId);
		path = "JSP/Mod.jsp";
		request.setAttribute("produce", produce);
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * 修改商品类型
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doModproduce(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		msg = "modproduce";
		
		if (request.getParameter("Name") != "") {
			produce.setProduceName(request.getParameter("Name"));
		}
		if (request.getParameter("Code") != "") {
			produce.setProduceCode(request.getParameter("Code"));
		}
		if (request.getParameter("Price") != "") {
			produce.setProducePrice(Double.parseDouble(request.getParameter("Price")));
		}
		boolean b = mainFactory.get_Produce_impl().Mod_produce(produce);
		if (b == true) {
			msgv = true;
		}
		path = "JSP/Value.jsp";
		request.setAttribute("msg", msg);
		request.setAttribute("msgv", msgv);
		request.getRequestDispatcher(path).forward(request, response);
	}
}
