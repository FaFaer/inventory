package ServletI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Factory.MainFactory;
import mode.Admin;

@WebServlet("/ManageS")
public class ManageS extends HttpServlet {
	private String op = null;
	private String path = null;
	private MainFactory mainFactory;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		op = request.getParameter("op");
		// 登陆
		System.out.println(op);
		if (op == "login" || "login".equals(op)) {
			op = request.getParameter("op");
			this.doLogin(request, response);
		}

	}

	/**
	 * 登陆
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */

	protected void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String Username = request.getParameter("Username");
		String Password = request.getParameter("Password");
		System.out.println(Username);
		Admin admin = new Admin(Username, Password);
		boolean b = mainFactory.get_Manager_impl().Login(admin);
		if (b == true) {
			path = "JSP/AddType.jsp";
			request.getSession().setAttribute("admin", admin);
		} else {
			request.getSession().setAttribute("va", 0);
			path = "JSP/Index.jsp";
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doDesign(HttpServletRequest request, HttpServletResponse response) {

	}
}
