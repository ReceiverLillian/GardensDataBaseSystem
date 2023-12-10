package servlet;

import bean.*;
import dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@WebServlet("/monitorQuotaError")
public class MonitorQuotaError extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MonitorQuotaError() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Err_monDao errMonDao = new Err_monDao();
        ArrayList<Err_monBean> errMonBeans = errMonDao.selectAllErr_mon();
        Iterator<Err_monBean> iterator = errMonBeans.iterator();
        QuotaDao quotaDao = new QuotaDao();
        ArrayList<QuotaBean> quotaBeans = new ArrayList<>();
        Quota_monDao quotaMonDao = new Quota_monDao();
        MonitorDao monitorDao = new MonitorDao();
        ArrayList<MonitorBean> monitorBeans = new ArrayList<>();
        SpeciesDao speciesDao = new SpeciesDao();
        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();

        while(iterator.hasNext()){
            Err_monBean errMonBean = iterator.next();
            QuotaBean quotaBean = quotaDao.selectQuotaById(errMonBean.getQuo_id());
            quotaBeans.add(quotaBean);
            Quota_monBean quotaMonBean = quotaMonDao.selectQuota_monByQuo_id(errMonBean.getQuo_id());
            MonitorBean monitorBean = monitorDao.selectMonitorById(quotaMonBean.getMon_id());
            monitorBeans.add(monitorBean);
            SpeciesBean speciesBean = speciesDao.selectSpeciesById(monitorBean.getMon_target());
            speciesBeans.add(speciesBean);
        }

        request.getSession().setAttribute("quotaBeans",quotaBeans);
        request.getSession().setAttribute("monitorBeans",monitorBeans);
        request.getSession().setAttribute("speciesBeans", speciesBeans);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/monitor_quota_error.jsp");
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
