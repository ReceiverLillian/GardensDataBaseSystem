package servlet;

import bean.ProvinceBean;
import bean.SpeciesBean;
import dao.ProvinceDao;
import dao.SpeciesDao;

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
@WebServlet("/SuperiorSelectByTypeServlet")
public class SuperiorSelectByTypeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SuperiorSelectByTypeServlet() {
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
        String name1 = request.getParameter("name1");
        String name2 = request.getParameter("name2");
        ArrayList<SpeciesBean> selectspecies = new ArrayList<>();
        SpeciesDao speciesDao=new SpeciesDao();
        selectspecies=speciesDao.selectAllSpecies();//找到全部植物信息

        ArrayList<SpeciesBean> result = new ArrayList<>();
        ProvinceDao provinceDao=new ProvinceDao();
        for(SpeciesBean spe : selectspecies){
            ArrayList<ProvinceBean> provinceBeans=new ArrayList<>();
            provinceBeans=provinceDao.getProvinceBySpecies(spe.getSpecies_id());
            String totalprovince="";
            for(ProvinceBean bean : provinceBeans){
                if (bean.getProvince_name()!=null){
                    totalprovince+= bean.getProvince_name()+";";
                }
                spe.setTotalprovience(totalprovince);
            }
            if(spe.getFamily_name().equals(name1)&&name2.equals("-1")){
                result.add(spe);
            }
            else if (spe.getGenus_name().equals(name2)&&name1.equals("-1")) {
                result.add(spe);
            }else if(spe.getFamily_name().equals(name1)&&spe.getGenus_name().equals(name2)){
                result.add(spe);
            }

        }
        request.getSession().setAttribute("allspecies", result);
        response.sendRedirect("/gardens/superior_plants.jsp");

    }
}
