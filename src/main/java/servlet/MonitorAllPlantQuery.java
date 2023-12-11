package servlet;



import bean.MonitorBean;
import bean.SpeciesBean;
import dao.SpeciesDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        Iterator<MonitorBean> iterator = monitorBeans.iterator();

        while ( iterator.hasNext() ){
            MonitorBean monitorBean = iterator.next();
            int species_id = monitorBean.getMon_target();
            SpeciesBean speciesBean = speciesDao.selectSpeciesById(species_id);
            if( speciesBean.getSpecies_name().contains(searchWord) ){
                continue;
            }
            else if( monitorBean.getCreatedby().contains(searchWord) ){
                continue;
            }
            else if( monitorBean.getMonby().contains(searchWord) ){
                continue;
            }
            else {
                iterator.remove();
            }
        }

        request.getSession().setAttribute("monitorBeans", monitorBeans);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/monitor_all_plant_query.jsp");
        dispatcher.forward(request, response);
    }
}
