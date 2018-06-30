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


@WebServlet("/admin/user/delete/servlet")
public class DelAdminUser extends HttpServlet {


    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelAdminUser() {
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
        String adminUserIdStr = request.getParameter("id");
        int adminUserId = Integer.parseInt(adminUserIdStr);

        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();


        //获取当前用户找到对应权限
        AdminUser user = (AdminUser) session.getAttribute("admin_user");

        PermissionImple permissionImple = new PermissionImple();
        permissionImple.openDatabase();


        //如果用户的权限为root,或者有该uri请求权限 则可以访问
        if (!permissionImple.ValidatePermission(user, "", request.getRequestURI())) {
            System.out.println("您没有权限操作");
            return;
        }

        //然后进行删除用户操作
        AdminUserImple adminUserImple = new AdminUserImple();

        adminUserImple.openDatabase();

        AdminUser adminUser = new AdminUser();
        adminUser.setId(adminUserId);


        if (adminUserImple.DelAdminUser(adminUser)) {
            data.put("status", 10000);
            data.put("message", "删除成功");
            response.getWriter().print(data);
        } else {
            data.put("status", 10001);
            data.put("message", "删除失败");
            response.getWriter().print(data);
        }
    }

    public void init() throws ServletException {

    }
}
