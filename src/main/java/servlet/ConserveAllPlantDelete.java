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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet("/conserveAllPlantDelete")
public class ConserveAllPlantDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllPlantDelete() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int con_id = Integer.parseInt(request.getParameter("con_id"));
        int species_id = Integer.parseInt(request.getParameter("species_id"));

        ConserveDao conserveDao = new ConserveDao();
        Conserve_speciesDao conserveSpeciesDao = new Conserve_speciesDao();

        boolean isup1 = conserveSpeciesDao.deleteConserve_speciesByCon_id(con_id);
        boolean isup2 = conserveDao.deleteConserveById(con_id);

        if(isup1 && isup2 ){
            response.sendRedirect("/gardens/conserveAllPlants?feedbackmessage="+ URLEncoder.encode("更新成功", "UTF-8"));
        }
        else{
            response.sendRedirect("/gardens/conserveAllPlants?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
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
