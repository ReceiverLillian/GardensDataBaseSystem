package servlet;


import bean.AdminBean;
import dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ�˺ź�����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminDao userdao = new AdminDao();
		// ���˺ź���������ж�
		boolean result = userdao.Login_verify(username, password);
		HttpSession session = request.getSession();
			if (result) {
				AdminBean adminbean = new AdminBean();
				AdminDao admindao = new AdminDao();

				adminbean = admindao.getAdminInfo(username, password);

				session.setAttribute("aid", "" + adminbean.getAid());

				session.setMaxInactiveInterval(6000);
				if (adminbean.getStatus() == 1) {
					response.sendRedirect("/GardensDataBaseSystem/staff_yanghu.jsp");
				}else{   //��⹤����Ա
					response.sendRedirect("/GardensDataBaseSystem/staff_jiance.jsp");
				}
			} else {
				session.setAttribute("state", "密码错误");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script language='javascript'>" +
						"alert('用户名或密码有误，请重新输入！');" +
						"window.location.href='login.jsp';</script>')");
//				response.sendRedirect("/books/login.jsp");
			}
	}

}
