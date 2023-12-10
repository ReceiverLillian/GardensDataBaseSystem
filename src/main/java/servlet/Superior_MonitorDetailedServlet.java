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

@WebServlet("/Superior_MonitorDetailedServlet")
public class Superior_MonitorDetailedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Superior_MonitorDetailedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        //根据id 获取对象
        int mon_id = Integer.parseInt(request.getParameter("mon_id"));
        int dev_id = Integer.parseInt(request.getParameter("dev_id"));
        int quo_id = Integer.parseInt(request.getParameter("quo_id"));
        int species_id = Integer.parseInt(request.getParameter("species_id"));

        MonitorDao monitorDao = new MonitorDao();
        QuotaDao quotaDao = new QuotaDao();
        DeviceDao deviceDao = new DeviceDao();
        SpeciesDao speciesDao = new SpeciesDao();

        MonitorBean monitorBean = monitorDao.selectMonitorById(mon_id);
        QuotaBean quotaBean = quotaDao.selectQuotaById(quo_id);
        DeviceBean deviceBean = deviceDao.selectDeviceById(dev_id);
        SpeciesBean speciesBean = speciesDao.selectSpeciesById(species_id);

        request.getSession().setAttribute("monitorBean", monitorBean);
        request.getSession().setAttribute("quotaBean", quotaBean);
        request.getSession().setAttribute("deviceBean", deviceBean);
        request.getSession().setAttribute("speciesBean", speciesBean);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/superior_monitordetailed.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
