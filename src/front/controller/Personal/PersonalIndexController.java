package front.controller.Personal;

import front.dao.imple.CateDaoImpl;
import front.dao.imple.NavigationImpl;
import entity.Category;
import entity.Navigation;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/personal/index/servlet")
public class PersonalIndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userinfo");

        CateDaoImpl cateDao = new CateDaoImpl();
        cateDao.openDatabase();
        List<Category> list = cateDao.ListNodeByParentId(0);
        cateDao.closeAll();
        //横着导航
        NavigationImpl navDao = new NavigationImpl();
        navDao.openDatabase();
        List<Navigation> navs = navDao.NavigationList();
        navDao.closeAll();


        if (user == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        request.setAttribute("categoryList", list);
        request.setAttribute("navs", navs);


        request.getRequestDispatcher("/personal.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");





    }

    public void init() throws ServletException {

    }
}
