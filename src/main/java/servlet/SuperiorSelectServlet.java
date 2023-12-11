package servlet;

import bean.ProvinceBean;
import bean.SpeciesBean;
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

/*
查找植物信息，按照分布地区，名称，别名模糊查找
 */
@WebServlet("/SuperiorSelectServlet")
public class SuperiorSelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SuperiorSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        ArrayList<SpeciesBean> selectspecies = new ArrayList<>();
        SpeciesDao speciesDao=new SpeciesDao();
        selectspecies=speciesDao.SuperiorselectAllSpecies();//找到全部植物信息

        ArrayList<SpeciesBean> result = new ArrayList<>();
        ProvinceDao provinceDao=new ProvinceDao();
        for(SpeciesBean spe : selectspecies){
            ArrayList<ProvinceBean> provinceBeans=new ArrayList<>();
            provinceBeans=provinceDao.getProvinceBySpecies(spe.getSpecies_id());
            String totalprovince=" ";
            for(ProvinceBean bean : provinceBeans){
                if (bean.getProvince_name()!=null){
                    totalprovince+= bean.getProvince_name()+";";
                }
                spe.setTotalprovience(totalprovince);
            }
            if(spe.getSpecies_name().contains(name)){
                result.add(spe);
            } else if (spe.getSpecies_othername().contains(name)) {
                result.add(spe);
            } else if (totalprovince.contains(name)) {
                result.add(spe);
            }
        }
        request.getSession().setAttribute("allspecies", result);
        response.sendRedirect("/gardens/superior_plants.jsp");

    }
}
