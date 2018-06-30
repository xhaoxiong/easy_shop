package admin.controller.AdminUser;

import admin.dao.Imple.AdminUserImple;
import admin.dao.Imple.PermissionImple;
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

@WebServlet("/admin/user/edit/servlet")
public class EditAdminUser extends HttpServlet {


    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAdminUser() {
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
        String idStr = request.getParameter("id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();

        if (idStr == null) {
            data.put("status", 10003);
            data.put("message", "id错误");
            response.getWriter().print(data);
            return;
        }
        int id = Integer.parseInt(idStr);

        //获取当前用户找到对应权限
        AdminUser user = (AdminUser) session.getAttribute("admin_user");

        PermissionImple permissionImple = new PermissionImple();
        permissionImple.openDatabase();


        //如果用户的权限为root,或者有该uri请求权限 则可以访问
        if (!permissionImple.ValidatePermission(user, "", request.getRequestURI())) {
            System.out.println("您没有权限操作");
            return;
        }


        //然后进行添加编辑操作
        AdminUserImple adminUserImple = new AdminUserImple();

        adminUserImple.openDatabase();
        Date date = new Date();
        AdminUser adminUser = new AdminUser();
        adminUser.setId(id);
        adminUser.setAdminPassword(password);
        adminUser.setEmail(email);
        adminUser.setLastLoginTime(date);
        adminUser.setLastLoginIp(getLocalIp(request));
        if (adminUserImple.EditAdminUser(adminUser)) {
            data.put("status", 10000);
            data.put("message", "编辑成功");
            response.getWriter().print(data);
        } else {
            data.put("status", 10001);
            data.put("message", "编辑失败");
            response.getWriter().print(data);
        }

    }

    public void init() throws ServletException {

    }

    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }
}
