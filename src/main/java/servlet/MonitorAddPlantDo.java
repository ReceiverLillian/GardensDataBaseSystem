package servlet;

import bean.MonitorBean;
import bean.Quota_monBean;
import dao.MonitorDao;
import dao.Quota_monDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/monitorAddPlantDo")
public class MonitorAddPlantDo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public MonitorAddPlantDo() {
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
        String feedbackMessage = request.getParameter("feedbackmessage");
        if (feedbackMessage != null) {
            feedbackMessage = URLDecoder.decode(feedbackMessage, "UTF-8");
            request.setAttribute("feedbackmessage", feedbackMessage);
        }

        MonitorBean monitorBean = new MonitorBean();
        monitorBean.setCreatedby(request.getParameter("createdby"));
        monitorBean.setMonby(request.getParameter("monby"));
        monitorBean.setMon_place(request.getParameter("mon_place"));
        monitorBean.setMon_target(Integer.parseInt(request.getParameter("species_id")));
        String monTimeString = request.getParameter("mon_time");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = formatter.parse(monTimeString);
            monitorBean.setMon_time(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        monitorBean.setMon_ctime(new Date());
        monitorBean.setMon_utime(new Date());
        MonitorDao monitorDao = new MonitorDao();
        monitorBean = monitorDao.insertMonitor(monitorBean);

        Quota_monDao quotaMonDao = new Quota_monDao();
        boolean isup = quotaMonDao.insertQuota_mon(monitorBean.getMon_id(),Integer.parseInt(request.getParameter("quo_id")));


        if(isup){
            response.sendRedirect("/gardens/monitorAllPlants?feedbackmessage="+ URLEncoder.encode("更新成功", "UTF-8"));
        }
        else{
            response.sendRedirect("/gardens/monitorAddPlantDo?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
        }

    }
}
