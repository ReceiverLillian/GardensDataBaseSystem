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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

@WebServlet("/monitorAllPlantDelete")
public class MonitorAllPlantDelete extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public MonitorAllPlantDelete() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int mon_id = Integer.parseInt(request.getParameter("mon_id"));
        int quo_id = Integer.parseInt(request.getParameter("quo_id"));

        Quota_monDao quotaMonDao = new Quota_monDao();
        MonitorDao monitorDao = new MonitorDao();

        boolean isde1 = quotaMonDao.deleteQuota_mon(mon_id, quo_id);
        boolean isde2 = monitorDao.deleteMonitorById(mon_id);

        if( isde1 && isde2 ){
            response.sendRedirect("/gardens/monitorAllPlants?feedbackmessage="+ URLEncoder.encode("更新成功", "UTF-8"));
        }
        else{
            response.sendRedirect("/gardens/monitorAllPlants?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
