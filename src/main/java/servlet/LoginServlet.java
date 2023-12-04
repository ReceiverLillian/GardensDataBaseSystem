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
				// �����˺ź�������ҳ����ߵ���Ϣ
				adminbean = admindao.getAdminInfo(username, password);
				// ��aid����session��
				session.setAttribute("aid", "" + adminbean.getAid());
				// ����session��ʧЧʱ��
				session.setMaxInactiveInterval(6000);
				// ����status��ֵ���ж��ǹ���Ա������Ա�����Ƕ��ߣ�status=1Ϊ���� =2�ǹ�����Ա
				if (adminbean.getStatus() == 1) {  //����������Ա
					response.sendRedirect("/GardensDataBaseSystem/staff_yanghu.jsp");
				}else{   //��⹤����Ա
					response.sendRedirect("/GardensDataBaseSystem/staff_jiance.jsp");
				}
			} else {
				// û���ҵ���Ӧ���˺ź����룬�������µ�¼
				session.setAttribute("state", "�������");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script language='javascript'>" +
						"alert('�û����������������������룡');" +
						"window.location.href='login.jsp';</script>')");
//				response.sendRedirect("/books/login.jsp");
			}
	}

}
