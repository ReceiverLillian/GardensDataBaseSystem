package servlet;

import bean.ConserveBean;
import bean.Conserve_speciesBean;
import bean.DiseaseBean;
import bean.SpeciesBean;
import dao.ConserveDao;
import dao.Conserve_speciesDao;
import dao.DiseaseDao;
import dao.SpeciesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Superior_DiseaseDetailedServlet")
public class Superior_DiseaseDetailedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Superior_DiseaseDetailedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int dis_id= Integer.parseInt(request.getParameter("dis_id"));

        DiseaseBean diseaseBean;
        DiseaseDao diseaseDao=new DiseaseDao();
        diseaseBean=diseaseDao.getDiseaseById(dis_id);

        request.getSession().setAttribute("diseaseBean", diseaseBean);
        // 转发请求到JSP页
            response.sendRedirect("/gardens/superior_diseasedetailed.jsp");

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/superior_conserve.jsp");
//        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
