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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("/conserveAllDiseaseUpdateDo")
public class ConserveAllDiseaseUpdateDo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAllDiseaseUpdateDo() {
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

        Disease_MedBean diseaseMedBean = (Disease_MedBean) request.getSession().getAttribute("diseaseMedBean");
        diseaseMedBean.setDis_target(Integer.parseInt(request.getParameter("species_id")));
        diseaseMedBean.setDis_mednum(Float.parseFloat(request.getParameter("dis_mednum")));
        String ddl = request.getParameter("dis_ddl");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = formatter.parse(ddl);
            diseaseMedBean.setDis_ddl(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Disease_medDao diseaseMedDao = new Disease_medDao();
        boolean isup = diseaseMedDao.updateDisease_MedById(diseaseMedBean);

        if(isup){
            response.sendRedirect("/gardens/conserveAllDisease?feedbackmessage="+ URLEncoder.encode("更新成功", "UTF-8"));
        }
        else{
            response.sendRedirect("/gardens/conserveAllDisease?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
        }

    }
}
