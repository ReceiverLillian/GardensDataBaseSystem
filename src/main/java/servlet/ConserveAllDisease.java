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
import java.net.URLDecoder;
import java.util.ArrayList;

@WebServlet("/conserveAllDisease")
public class ConserveAllDisease extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllDisease() {
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

        Disease_medDao diseaseMedDao = new Disease_medDao();
        ArrayList<Disease_MedBean> diseaseMedBeans = diseaseMedDao.selectAllDisease_med();
        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();
        ArrayList<DiseaseBean> diseaseBeans = new ArrayList<>();
        SpeciesDao speciesDao = new SpeciesDao();
        DiseaseDao diseaseDao = new DiseaseDao();

        for(Disease_MedBean diseaseMedBean:diseaseMedBeans){
            SpeciesBean speciesBean = speciesDao.selectSpeciesById(diseaseMedBean.getDis_target());
            speciesBeans.add(speciesBean);
            DiseaseBean diseaseBean = diseaseDao.selectDiseaseById(diseaseMedBean.getDis_id());
            diseaseBeans.add(diseaseBean);
        }

        request.getSession().setAttribute("speciesBeans", speciesBeans);
        request.getSession().setAttribute("diseaseMedBeans", diseaseMedBeans);
        request.getSession().setAttribute("diseaseBeans", diseaseBeans);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/conserve_all_disease.jsp");
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
