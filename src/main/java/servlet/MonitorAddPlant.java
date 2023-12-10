package servlet;

import bean.QuotaBean;
import bean.SpeciesBean;
import dao.MonitorDao;
import dao.QuotaDao;
import dao.Quota_monDao;
import dao.SpeciesDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

@WebServlet("/monitorAddPlant")
public class MonitorAddPlant extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MonitorAddPlant() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        QuotaDao quotaDao = new QuotaDao();
        ArrayList<QuotaBean> quotaBeans = quotaDao.selectAllQuota();
        SpeciesDao speciesDao = new SpeciesDao();
        ArrayList<SpeciesBean> speciesBeans = speciesDao.selectAllSpecies();

        request.getSession().setAttribute("quotaBeans", quotaBeans);
        request.getSession().setAttribute("speciesBeans", speciesBeans);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/monitor_add_plant.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
