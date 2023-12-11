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
import java.util.ArrayList;
/*
查看植物信息
 */
@WebServlet("/SuperiorShowPlantsServlet")
public class SuperiorShowPlantsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SuperiorShowPlantsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<SpeciesBean> allspecies = new ArrayList<>();

        SpeciesDao speciesDao=new SpeciesDao();
        allspecies=speciesDao.SuperiorselectAllSpecies();
        ProvinceDao provinceDao=new ProvinceDao();
        for(SpeciesBean spe : allspecies){
            ArrayList<ProvinceBean> provinceBeans=new ArrayList<>();
            provinceBeans=provinceDao.getProvinceBySpecies(spe.getSpecies_id());
            String totalprovince="";
            for(ProvinceBean bean : provinceBeans){
                if (bean.getProvince_name()!=null){
                    totalprovince+= bean.getProvince_name()+";";
                }
            }
            spe.setTotalprovience(totalprovince);
        }

        request.getSession().setAttribute("allspecies", allspecies);


        // 转发请求到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/superior_plants.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
