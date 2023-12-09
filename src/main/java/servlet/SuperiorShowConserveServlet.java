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

@WebServlet("/SuperiorShowConserveServlet")
public class SuperiorShowConserveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SuperiorShowConserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //根据养护id 获取养护对象
        String tip=request.getParameter("tip");
        ArrayList<ConserveBean> allconserve = new ArrayList<>();
        ConserveDao conserveDao=new ConserveDao();
        allconserve=conserveDao.getAllConserve();
        Conserve_speciesDao conserveSpeciesDao=new Conserve_speciesDao();
        SpeciesDao speciesDao = new SpeciesDao();

        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();
        ArrayList<Conserve_speciesBean> conserveSpecies = new ArrayList<Conserve_speciesBean>();
        for(ConserveBean bean:allconserve){

            Conserve_speciesBean conserveSpeciesBean=conserveSpeciesDao.getSpeciesByCon_id(bean.getCon_id());

            SpeciesBean speciesBean = speciesDao.selectSpeciesById(conserveSpeciesBean.getSpecies_id());
            speciesBeans.add(speciesBean);
        }


        request.getSession().setAttribute("allconserve", allconserve);
        request.getSession().setAttribute("speciesBeans", speciesBeans);
        // 转发请求到JSP页面
        if(tip==null){
            response.sendRedirect("/gardens/superior_conserve.jsp");
        }
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/superior_conserve.jsp");
//        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
