package servlet;

import bean.DeviceBean;
import bean.MonitorBean;
import bean.QuotaBean;
import bean.SpeciesBean;
import dao.DeviceDao;
import dao.MonitorDao;
import dao.QuotaDao;
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
import java.util.Date;

@WebServlet("/monitorAllPlantUpdateDo")
public class MonitorAllPlantUpdateDo extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public MonitorAllPlantUpdateDo() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        MonitorBean monitorBean = (MonitorBean) request.getSession().getAttribute("monitorBean");
        monitorBean.setCreatedby(request.getParameter("createdby"));
        monitorBean.setMonby(request.getParameter("monby"));
        monitorBean.setMon_place(request.getParameter("mon_place"));
        monitorBean.setMon_target(Integer.parseInt(request.getParameter("species_id")));
        monitorBean.setMon_utime(new Date());

        MonitorDao monitorDao = new MonitorDao();
        boolean isup = monitorDao.updateMonitor(monitorBean);
        request.getSession().setAttribute("monitorBean",monitorBean);

        if(isup){
            response.sendRedirect("/gardens/monitorAllPlants?feedbackmessage="+URLEncoder.encode("更新成功", "UTF-8"));
        }
        else{
            response.sendRedirect("/gardens/monitorAllPlantUpdate?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
        }

    }
}
