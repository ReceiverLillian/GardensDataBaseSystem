package servlet;

import bean.ConserveBean;
import bean.Conserve_speciesBean;
import bean.SpeciesBean;
import dao.ConserveDao;
import dao.Conserve_speciesDao;
import dao.SpeciesDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

@WebServlet("/conserveAllPlantUpdate")
public class ConserveAllPlantUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllPlantUpdate() {
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

        int con_id = Integer.parseInt(request.getParameter("con_id"));
        int species_id = Integer.parseInt(request.getParameter("species_id"));

        ConserveDao conserveDao = new ConserveDao();
        SpeciesDao speciesDao = new SpeciesDao();

        ConserveBean conserveBean = conserveDao.getConserveById(con_id);
        SpeciesBean speciesBean = speciesDao.selectSpeciesById(species_id);
        ArrayList<SpeciesBean> speciesBeans = speciesDao.selectAllSpecies();

        request.getSession().setAttribute("conserveBean", conserveBean);
        request.getSession().setAttribute("speciesBean", speciesBean);
        request.getSession().setAttribute("speciesBeans", speciesBeans);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/conserve_all_plant_update.jsp");
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
