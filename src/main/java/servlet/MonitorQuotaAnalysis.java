package servlet;

import bean.QuotaBean;
import bean.SpeciesBean;
import dao.QuotaDao;
import dao.SpeciesDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/monitorQuotaAnalysis")
public class MonitorQuotaAnalysis extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MonitorQuotaAnalysis() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        QuotaDao quotaDao = new QuotaDao();
        Map<String, Double> aveValue = quotaDao.getAverageQuoValueByQuoName();
        Map<String, Double> maxValue = quotaDao.getMaxQuoValueByQuoName();

        request.getSession().setAttribute("aveValue", aveValue);
        request.getSession().setAttribute("maxValue", maxValue);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/monitor_quota_analysis.jsp");
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
