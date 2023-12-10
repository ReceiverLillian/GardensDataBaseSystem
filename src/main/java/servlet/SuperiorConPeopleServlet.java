package servlet;

import bean.ConserveBean;
import bean.Conserve_speciesBean;
import bean.SpeciesBean;
import bean.UserBean;
import dao.ConserveDao;
import dao.Conserve_speciesDao;
import dao.SpeciesDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SuperiorConPeopleServlet")
public class SuperiorConPeopleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SuperiorConPeopleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //根据养护id 获取养护对象

        ArrayList<UserBean> allconuser = new ArrayList<>();
        UserDao userDao=new UserDao();
        allconuser=userDao.getConserveUser();
        request.getSession().setAttribute("allconuser", allconuser);
        // 转发请求到JSP页面
            response.sendRedirect("/gardens/superior_conpeople.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
