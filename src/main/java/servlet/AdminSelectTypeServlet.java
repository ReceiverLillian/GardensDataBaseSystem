package servlet;

import bean.FamilyBean;
import dao.FamilyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/*
根据科属分类查找下属分类
 */
@WebServlet("/AdminSelectTypeServlet")
public class AdminSelectTypeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AdminSelectTypeServlet() {
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
        String name3 = request.getParameter("name3");//科名
        String name4 = request.getParameter("name4");//属名

        ArrayList<FamilyBean> selecttype = new ArrayList<>();
        FamilyDao familyDao=new FamilyDao();
        if(name3.equals("-1")&&name4.equals("-1")){   //查询内容为空
            response.sendRedirect("/gardens/SuperiorShowTypesServlet");
        } else if (name3.equals("-1") && (!name4.equals("-1"))) {  //只输入了属信息
            selecttype=familyDao.SelectTypeByGenus(name4);
            request.getSession().setAttribute("alltypes", selecttype);
            response.sendRedirect("/gardens/superior_types.jsp");
        } else if (name4.equals("-1") && (!name3.equals("-1"))) {  //只输入了科信息
            selecttype=familyDao.SelectTypeByFamily(name3);
            request.getSession().setAttribute("alltypes", selecttype);

            response.sendRedirect("/gardens/superior_types.jsp");
        }else{
            selecttype=familyDao.SelectTypeByFamilyAndGenus(name3,name4);
            request.getSession().setAttribute("alltypes", selecttype);

            response.sendRedirect("/gardens/admin_types.jsp");
        }


    }
}
