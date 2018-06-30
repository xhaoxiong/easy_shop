package admin.controller.AdminUser;

import admin.dao.Imple.LoginImple;
import entity.AdminUser;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@WebServlet("/admin/login/validate/servlet")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");


        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();

        if (username.length() == 0 || password.length() == 0) {
            data.put("status", 10001);
            data.put("message", "请输入正确的用户名和密码");
            response.getWriter().print(data);
            return;
        }


        LoginImple LoginImple = new LoginImple();
        LoginImple.openDatabase();

        Date date = new Date();
        AdminUser user = new AdminUser();
        user.setAdminUserName(username);
        user.setAdminPassword(password);
        user.setLastLoginTime(date);

        AdminUser user1 = new AdminUser();
        user1 = LoginImple.ValidateAdminUser(user);
        if (LoginImple.UpdateAdminLoginTime(user)) {
            if (user1.getId() != 0) {
                data.put("status", 10000);
                data.put("user", user1);
                data.put("message", "登陆成功");
                session.setAttribute("admin_user", user1);
                response.getWriter().print(data);
            } else {
                data.put("status", 10002);
                data.put("message", "用户或者密码错误");
                response.getWriter().print(data);
            }
        } else {
            data.put("status", 10003);
            data.put("message", "服务器错误");
            response.getWriter().print(data);
        }
    }

    public void init() throws ServletException {

    }
}
