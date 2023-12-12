package servlet;

import bean.DiseaseBean;
import bean.Disease_MedBean;
import bean.SpeciesBean;
import dao.DiseaseDao;
import dao.Disease_medDao;
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

@WebServlet("/conserveAllDiseaseQuery")
public class ConserveAllDiseaseQuery extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllDiseaseQuery() {
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
        Disease_medDao diseaseMedDao = new Disease_medDao();
        ArrayList<Disease_MedBean> diseaseMedBeans = diseaseMedDao.selectAllDisease_med();
        Iterator<Disease_MedBean> iterator = diseaseMedBeans.iterator();
        SpeciesDao speciesDao = new SpeciesDao();
        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();
        DiseaseDao diseaseDao = new DiseaseDao();
        ArrayList<DiseaseBean> diseaseBeans = new ArrayList<>();

        while (iterator.hasNext()){
            Disease_MedBean diseaseMedBean = iterator.next();
            SpeciesBean speciesBean = speciesDao.selectSpeciesById(diseaseMedBean.getDis_target());
            if( diseaseMedBean.getMed_name().contains(searchWord) || diseaseMedBean.getDis_name().contains(searchWord) || speciesBean.getSpecies_name().contains(searchWord)){
                DiseaseBean diseaseBean = diseaseDao.selectDiseaseById(diseaseMedBean.getDis_id());
                diseaseBeans.add(diseaseBean);
                speciesBeans.add(speciesBean);
                continue;
            }
            else{
                iterator.remove();
            }
        }

        request.getSession().setAttribute("speciesBeans", speciesBeans);
        request.getSession().setAttribute("diseaseMedBeans", diseaseMedBeans);
        request.getSession().setAttribute("diseaseBeans", diseaseBeans);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/conserve_all_disease_query.jsp");
        dispatcher.forward(request, response);

    }
}
