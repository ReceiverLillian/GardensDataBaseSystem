package servlet;

import bean.FamilyBean;
import bean.ProvinceBean;
import bean.SpeciesBean;
import dao.FamilyDao;
import dao.ProvinceDao;
import dao.SpeciesDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/*
查看植物信息
 */
@WebServlet("/SuperiorShowTypesServlet")
public class SuperiorShowTypesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SuperiorShowTypesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<FamilyBean> alltypes = new ArrayList<>();
        FamilyDao familyDao=new FamilyDao();
        alltypes=familyDao.getAllTypeByFamily();


        request.getSession().setAttribute("alltypes", alltypes);


        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/superior_types.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
