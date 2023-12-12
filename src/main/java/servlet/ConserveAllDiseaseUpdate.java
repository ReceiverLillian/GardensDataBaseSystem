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
import java.net.URLDecoder;
import java.util.ArrayList;

@WebServlet("/conserveAllDiseaseUpdate")
public class ConserveAllDiseaseUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllDiseaseUpdate() {
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

        int dis_id = Integer.parseInt(request.getParameter("dis_id"));
        int med_id = Integer.parseInt(request.getParameter("med_id"));

        Disease_medDao diseaseMedDao = new Disease_medDao();
        Disease_MedBean diseaseMedBean = diseaseMedDao.selectDisease_MedById(dis_id, med_id);
        MedicineDao medicineDao = new MedicineDao();
        DiseaseDao diseaseDao = new DiseaseDao();
        MedicineBean medicineBean = medicineDao.selectMedicineById(diseaseMedBean.getMed_id());
        diseaseMedBean.setMed_name(medicineBean.getMed_name());
        DiseaseBean diseaseBean = diseaseDao.selectDiseaseById(diseaseMedBean.getDis_id());
        diseaseMedBean.setDis_name(diseaseBean.getDis_name());

        SpeciesDao speciesDao = new SpeciesDao();
        ArrayList<SpeciesBean> speciesBeans = speciesDao.selectAllSpecies();
        SpeciesBean speciesBean = speciesDao.selectSpeciesById(diseaseMedBean.getDis_target());


        request.getSession().setAttribute("diseaseMedBean", diseaseMedBean);
        request.getSession().setAttribute("speciesBeans", speciesBeans);
        request.getSession().setAttribute("speciesBean", speciesBean);

        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/conserve_all_disease_update.jsp");
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
