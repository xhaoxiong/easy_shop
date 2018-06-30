package front.controller.User;

import front.dao.imple.UserDaoImpl;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");

        HttpSession session = request.getSession();


        UserDaoImpl userDao = new UserDaoImpl();
        userDao.openDatabase();


//        JsonUtils jsonUtils = new JsonUtils();
//
//        jsonUtils.returnSuccess(response, "user", "", "注销成功");

        session.removeAttribute("userinfo");

        response.sendRedirect("/index.jsp");
    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");

        HttpSession session = request.getSession();


        UserDaoImpl userDao = new UserDaoImpl();
        userDao.openDatabase();


        JsonUtils jsonUtils = new JsonUtils();
        session.removeAttribute("userinfo");
        System.out.println(111);
        jsonUtils.returnSuccess(response, "user", "", "注销成功");
    }

    public void init() throws ServletException {

    }
}
