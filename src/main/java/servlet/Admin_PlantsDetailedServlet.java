package servlet;

import bean.PictureBean;
import bean.ProvinceBean;
import bean.SpeciesBean;
import dao.PictureDao;
import dao.ProvinceDao;
import dao.SpeciesDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Admin_PlantsDetailedServlet")
public class Admin_PlantsDetailedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Admin_PlantsDetailedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        //获取植物id ,根据植物id获取植物信息->配图信息->区域信息
        int species_id = Integer.parseInt(request.getParameter("spe_id"));

        SpeciesDao speciesDao = new SpeciesDao();
        SpeciesBean speciesBean = speciesDao.selectDetailedSpeciesById(species_id);

        PictureDao pictureDao=new PictureDao();
        ArrayList<PictureBean> pictureBeans=new ArrayList<>();
        pictureBeans=pictureDao.getPictureBySpecies(species_id);

        ProvinceDao provinceDao=new ProvinceDao();
        ArrayList<ProvinceBean> provinceBeans=new ArrayList<>();
        provinceBeans=provinceDao.getProvinceBySpecies(species_id);
        String totalprovince="";
        for(ProvinceBean bean : provinceBeans){
            if (bean.getProvince_name()!=null){
                totalprovince+= bean.getProvince_name()+";";
            }
        }
        speciesBean.setTotalprovience(totalprovince);

        request.getSession().setAttribute("speciesBean", speciesBean);
        request.getSession().setAttribute("pictureBeans", pictureBeans);
        request.getSession().setAttribute("totalprovince", totalprovince);
        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_plantdetailed.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
