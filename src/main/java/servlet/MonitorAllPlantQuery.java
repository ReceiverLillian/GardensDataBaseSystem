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
import java.util.Date;
import java.util.Iterator;

@WebServlet("/monitorAllPlantQuery")
public class MonitorAllPlantQuery extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MonitorAllPlantQuery() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String searchWord = request.getParameter("searchWord");
        SpeciesDao speciesDao = new SpeciesDao();

        ArrayList<MonitorBean> monitorBeans = (ArrayList<MonitorBean>) request.getSession().getAttribute("monitorBeans");
        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();
        ArrayList<DeviceBean> deviceBeans = new ArrayList<>();
        ArrayList<QuotaBean> quotaBeans = new ArrayList<>();
        Iterator<MonitorBean> iterator = monitorBeans.iterator();

        Quota_monDao quotaMonDao = new Quota_monDao();
        QuotaDao quotaDao = new QuotaDao();
        Device_quotaDao deviceQuotaDao = new Device_quotaDao();
        DeviceDao deviceDao = new DeviceDao();

        while ( iterator.hasNext() ){
            MonitorBean monitorBean = iterator.next();
            int species_id = monitorBean.getMon_target();
            SpeciesBean speciesBean = speciesDao.selectSpeciesById(species_id);
            if( speciesBean.getSpecies_name().contains(searchWord) || monitorBean.getCreatedby().contains(searchWord) || monitorBean.getMonby().contains(searchWord)){
                speciesBeans.add(speciesBean);

                Quota_monBean quotaMonBean = quotaMonDao.selectQuota_monByMon_id(monitorBean.getMon_id());
                QuotaBean quotaBean = quotaDao.selectQuotaById(quotaMonBean.getQuo_id());
                quotaBeans.add(quotaBean);

                Device_quotaBean deviceQuotaBean = deviceQuotaDao.selectDevice_quotaByQuo_id(quotaBean.getQuo_id());
                DeviceBean deviceBean = deviceDao.selectDeviceById(deviceQuotaBean.getDev_id());
                deviceBeans.add(deviceBean);
            }
            else {
                iterator.remove();
            }
        }

        request.getSession().setAttribute("monitorBeans", monitorBeans);
        request.getSession().setAttribute("quotaBeans", quotaBeans);
        request.getSession().setAttribute("deviceBeans", deviceBeans);
        request.getSession().setAttribute("speciesBeans", speciesBeans);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/monitor_all_plant_query.jsp");
        dispatcher.forward(request, response);
    }
}
