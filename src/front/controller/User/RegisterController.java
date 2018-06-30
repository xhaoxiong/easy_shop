package front.controller.User;

import front.dao.imple.UserDaoImpl;
import entity.User;
import net.sf.json.JSONObject;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
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
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();

        if (username.length() == 0 || password.length() == 0 || email.length() == 0 || phone.length() == 0) {
            data.put("status", 10002);
            data.put("message", "请输入正确的参数");
            response.getWriter().print(data);
            return;
        }

        UserDaoImpl userDao = new UserDaoImpl();
        userDao.openDatabase();

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);

        JsonUtils jsonUtils = new JsonUtils();

        if (userDao.getUserByUserName(username)) {
            jsonUtils.returnJson(response, 10001, "该用户名已经存在");
            return;
        }

        if (userDao.addUser(user)) {
            session.setAttribute("userinfo", user);
            user.setPassword("");
            jsonUtils.returnSuccess(response, "user", user, "");
            return;
        } else {
            jsonUtils.returnJson(response, 10003, "注册失败");
        }
    }

    public void init() throws ServletException {

    }


}
