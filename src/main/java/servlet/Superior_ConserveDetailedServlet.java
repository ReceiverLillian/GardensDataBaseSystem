package servlet;

import bean.ConserveBean;
import bean.Conserve_speciesBean;
import bean.SpeciesBean;
import dao.ConserveDao;
import dao.Conserve_speciesDao;
import dao.SpeciesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Superior_ConserveDetailedServlet")
public class Superior_ConserveDetailedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Superior_ConserveDetailedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int con_id= Integer.parseInt(request.getParameter("con_id"));

        //根据养护id 获取养护对象
        ConserveBean targetconserve;
        ConserveDao conserveDao=new ConserveDao();
        targetconserve=conserveDao.getConserveById(con_id); //找到目标养护表

        Conserve_speciesDao conserveSpeciesDao=new Conserve_speciesDao();
        SpeciesDao speciesDao = new SpeciesDao();
        Conserve_speciesBean conserveSpeciesBean=conserveSpeciesDao.getSpeciesByCon_id(targetconserve.getCon_id());
        SpeciesBean targetspecies = speciesDao.selectSpeciesById(conserveSpeciesBean.getSpecies_id());



        request.getSession().setAttribute("targetconserve", targetconserve);
        request.getSession().setAttribute("targetspecies", targetspecies);
        // 转发请求到JSP页
            response.sendRedirect("/gardens/superior_conservedetailed.jsp");

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/superior_conserve.jsp");
//        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
