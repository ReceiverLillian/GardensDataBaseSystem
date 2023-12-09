package servlet;


import bean.AdminBean;
import bean.RoleBean;
import bean.UserBean;
import bean.User_roleBean;
import dao.AdminDao;
import dao.RoleDao;
import dao.UserDao;
import dao.User_roleDao;

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

		UserDao userDao = new UserDao();
		// ���˺ź���������ж�
		UserBean userBean = userDao.getUserByNamePass(username, password);
		HttpSession session = request.getSession();
			if (userBean!=null) {

				session.setAttribute("user", userBean);
				User_roleDao userRoleDao =new User_roleDao();
				User_roleBean userRoleBean = userRoleDao.getUser_roleByUser_id(userBean.getUser_id());
				RoleDao roleDao = new RoleDao();
				RoleBean roleBean = roleDao.getRoleById(userRoleBean.getRole_id());

				session.setMaxInactiveInterval(6000);
				if (roleBean.getRole_name().equals("admin")) {
					response.sendRedirect("/gardens/admin_main.jsp");
				}
				else if(roleBean.getRole_name().equals("monitor")){
					response.sendRedirect("/gardens/monitor_main.jsp");
				}
				else if(roleBean.getRole_name().equals("conserve")){
					response.sendRedirect("/gardens/conserve_main.jsp");
				}
				else if(roleBean.getRole_name().equals("superior")){
					response.sendRedirect("/gardens/superior_main.jsp");
				}
			} else {
				session.setAttribute("state", "密码错误");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script language='javascript'>" +
						"alert('用户名或密码有误，请重新输入！');" +
						"window.location.href='login.jsp';</script>')");
			}
	}

}
