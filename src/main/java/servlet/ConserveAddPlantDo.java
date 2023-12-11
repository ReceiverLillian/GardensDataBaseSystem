package servlet;

import bean.ConserveBean;
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
import java.net.URLEncoder;
import java.util.ArrayList;

@WebServlet("/conserveAddPlantDo")
public class ConserveAddPlantDo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAddPlantDo() {
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

        ConserveBean conserveBean = new ConserveBean();
        conserveBean.setCreatedby(request.getParameter("createdby"));
        conserveBean.setConby(request.getParameter("conby"));
        conserveBean.setCon_place(request.getParameter("con_place"));
        conserveBean.setCon_name(request.getParameter("con_name"));
        conserveBean.setCon_desc(request.getParameter("con_desc"));

        int species_id = Integer.parseInt(request.getParameter("species_id"));
        Conserve_speciesDao conserveSpeciesDao =  new Conserve_speciesDao();
        ConserveDao conserveDao = new ConserveDao();

        conserveBean = conserveDao.insertConserve(conserveBean);
        boolean isup = conserveSpeciesDao.insertConserve_species(conserveBean.getCon_id(), species_id);


        if( isup ){
            response.sendRedirect("/gardens/conserveAllPlants?feedbackmessage="+ URLEncoder.encode("更新成功", "UTF-8"));
        }
        else{
            response.sendRedirect("/gardens/conserveAddPlant?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
        }
    }
}
