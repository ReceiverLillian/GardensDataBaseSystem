package servlet;

import bean.UserBean;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SuperiorMonPeopleServlet")
public class SuperiorMonPeopleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SuperiorMonPeopleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        ArrayList<UserBean> allmonuser = new ArrayList<>();
        UserDao userDao=new UserDao();
        allmonuser=userDao.getMonitorUser();
        request.getSession().setAttribute("allmonuser", allmonuser);
        // 转发请求到JSP页面
            response.sendRedirect("/gardens/superior_monpeople.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
