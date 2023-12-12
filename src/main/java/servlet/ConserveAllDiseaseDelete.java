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
import java.net.URLEncoder;
import java.util.ArrayList;

@WebServlet("/conserveAllDiseaseDelete")
public class ConserveAllDiseaseDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllDiseaseDelete() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int dis_id = Integer.parseInt(request.getParameter("dis_id"));
        int med_id = Integer.parseInt(request.getParameter("med_id"));

        Disease_medDao diseaseMedDao = new Disease_medDao();
        boolean isup = diseaseMedDao.deleteDisease_MedById(dis_id,med_id);
        if(isup){
            response.sendRedirect("/gardens/conserveAllDisease?feedbackmessage="+ URLEncoder.encode("更新成功", "UTF-8"));
        }
        else{
            response.sendRedirect("/gardens/conserveAllDisease?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
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
