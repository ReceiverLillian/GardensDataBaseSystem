package servlet;

import bean.*;
import dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

@WebServlet("/monitorAllPlants")
public class MonitorAllPlantServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public MonitorAllPlantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String feedbackMessage = request.getParameter("feedbackmessage");
        if (feedbackMessage != null) {
            feedbackMessage = URLDecoder.decode(feedbackMessage, "UTF-8");
            request.setAttribute("feedbackmessage", feedbackMessage);
        }
        MonitorDao monitorDao = new MonitorDao();
        ArrayList<MonitorBean> monitorBeans = monitorDao.getAllMonitor();
        ArrayList<QuotaBean> quotaBeans = new ArrayList<>();
        ArrayList<DeviceBean> deviceBeans = new ArrayList<>();
        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();

        Quota_monDao quotaMonDao = new Quota_monDao();
        QuotaDao quotaDao = new QuotaDao();
        Device_quotaDao deviceQuotaDao = new Device_quotaDao();
        DeviceDao deviceDao = new DeviceDao();
        SpeciesDao speciesDao = new SpeciesDao();

        for(MonitorBean monitorBean:monitorBeans){
            Quota_monBean quotaMonBean = quotaMonDao.selectQuota_monByMon_id(monitorBean.getMon_id());
            QuotaBean quotaBean = quotaDao.selectQuotaById(quotaMonBean.getQuo_id());
            quotaBeans.add(quotaBean);

            Device_quotaBean deviceQuotaBean = deviceQuotaDao.selectDevice_quotaByQuo_id(quotaBean.getQuo_id());
            DeviceBean deviceBean = deviceDao.selectDeviceById(deviceQuotaBean.getDev_id());
            deviceBeans.add(deviceBean);

            SpeciesBean speciesBean = speciesDao.selectSpeciesById(monitorBean.getMon_target());
            speciesBeans.add(speciesBean);
        }


        request.getSession().setAttribute("monitorBeans", monitorBeans);
        request.getSession().setAttribute("quotaBeans", quotaBeans);
        request.getSession().setAttribute("deviceBeans", deviceBeans);
        request.getSession().setAttribute("speciesBeans", speciesBeans);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/monitor_all_plant.jsp");
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
