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

@WebServlet("/conserveAddDiseaseDo")
public class ConserveAddDiseaseDo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConserveAddDiseaseDo() {
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

        int med_id = Integer.parseInt(request.getParameter("med_id"));
        int dis_id = Integer.parseInt(request.getParameter("dis_id"));
        int species_target = Integer.parseInt(request.getParameter("species_id"));

        Disease_MedBean diseaseMedBean = new Disease_MedBean();
        diseaseMedBean.setMed_id(med_id);
        diseaseMedBean.setDis_id(dis_id);
        diseaseMedBean.setDis_mednum(Float.parseFloat(request.getParameter("dis_mednum")));
        diseaseMedBean.setDis_target(species_target);
        String ddl = request.getParameter("dis_ddl");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = formatter.parse(ddl);
            diseaseMedBean.setDis_ddl(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Disease_medDao diseaseMedDao = new Disease_medDao();
        boolean isup = diseaseMedDao.insertDisease_Med(diseaseMedBean);

        if(isup){
            response.sendRedirect("/gardens/conserveAllDisease?feedbackmessage="+ URLEncoder.encode("更新成功", "UTF-8"));
        }
        else{
            response.sendRedirect("/gardens/conserveAddDisease?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
        }
    }
}
