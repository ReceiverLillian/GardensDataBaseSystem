package servlet;

import bean.DiseaseBean;
import bean.Disease_MedBean;
import bean.MedicineBean;
import bean.SpeciesBean;
import dao.DiseaseDao;
import dao.Disease_medDao;
import dao.MedicineDao;
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


@WebServlet("/conserveAddDisease")
public class ConserveAddDisease extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAddDisease() {
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

        MedicineDao medicineDao = new MedicineDao();
        DiseaseDao diseaseDao = new DiseaseDao();
        SpeciesDao speciesDao = new SpeciesDao();

        ArrayList<MedicineBean> medicineBeans = medicineDao.selectAllMedicine();
        ArrayList<DiseaseBean> diseaseBeans = diseaseDao.selectAllDisease();
        ArrayList<SpeciesBean> speciesBeans = speciesDao.selectAllSpecies();

        request.getSession().setAttribute("medicineBeans", medicineBeans);
        request.getSession().setAttribute("diseaseBeans", diseaseBeans);
        request.getSession().setAttribute("speciesBeans", speciesBeans);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/conserve_add_disease.jsp");
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
