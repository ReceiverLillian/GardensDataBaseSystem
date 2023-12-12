package servlet;

import bean.DiseaseBean;
import bean.ProvinceBean;
import bean.SpeciesBean;
import dao.DiseaseDao;
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
查看病虫害信息
 */
@WebServlet("/SuperiorShowDiseaseServlet")
public class SuperiorShowDiseaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SuperiorShowDiseaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<DiseaseBean> diseaseBeans = new ArrayList<>();
        DiseaseDao diseaseDao=new DiseaseDao();
        diseaseBeans=diseaseDao.SuperiorgetAllDisease();
        request.getSession().setAttribute("diseaseBeans", diseaseBeans);


        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/superior_disease.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
