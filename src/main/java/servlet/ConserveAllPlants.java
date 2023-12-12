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

@WebServlet("/conserveAllPlants")
public class ConserveAllPlants extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllPlants() {
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

        ConserveDao conserveDao = new ConserveDao();
        ArrayList<ConserveBean> conserveBeans = conserveDao.getAllConserve();
        Conserve_speciesDao conserveSpeciesDao = new Conserve_speciesDao();
        SpeciesDao speciesDao = new SpeciesDao();
        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();

        for(ConserveBean conserveBean:conserveBeans){
            Conserve_speciesBean conserveSpeciesBean = conserveSpeciesDao.getSpeciesByCon_id(conserveBean.getCon_id());
            SpeciesBean speciesBean = speciesDao.selectSpeciesById(conserveSpeciesBean.getSpecies_id());
            speciesBeans.add(speciesBean);
        }

        request.getSession().setAttribute("conserveBeans", conserveBeans);
        request.getSession().setAttribute("speciesBeans", speciesBeans);
        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/conserve_all_plant.jsp");
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
