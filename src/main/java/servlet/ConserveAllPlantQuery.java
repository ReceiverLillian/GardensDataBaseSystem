package servlet;

import bean.ConserveBean;
import bean.Conserve_speciesBean;
import bean.MonitorBean;
import bean.SpeciesBean;
import dao.Conserve_speciesDao;
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

@WebServlet("/conserveAllPlantQuery")
public class ConserveAllPlantQuery extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllPlantQuery() {
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
        Conserve_speciesDao conserveSpeciesDao = new Conserve_speciesDao();
        ArrayList<ConserveBean> conserveBeans = (ArrayList<ConserveBean>) request.getSession().getAttribute("conserveBeans");
        Iterator<ConserveBean> iterator = conserveBeans.iterator();
        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();

        while ( iterator.hasNext() ){
            ConserveBean conserveBean = iterator.next();
            Conserve_speciesBean conserveSpeciesBean = conserveSpeciesDao.getSpeciesByCon_id(conserveBean.getCon_id());
            int species_id = conserveSpeciesBean.getSpecies_id();
            SpeciesBean speciesBean = speciesDao.selectSpeciesById(species_id);
            if( speciesBean.getSpecies_name().contains(searchWord) || conserveBean.getCreatedby().contains(searchWord) || conserveBean.getConby().contains(searchWord)){
                speciesBeans.add(speciesBean);
            }
            else {
                iterator.remove();
            }
        }

        request.getSession().setAttribute("conserveBeans", conserveBeans);
        request.getSession().setAttribute("speciesBeans", speciesBeans);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/conserve_all_plant_query.jsp");
        dispatcher.forward(request, response);
    }
}
