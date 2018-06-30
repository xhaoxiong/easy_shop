package admin.controller.AdminUser;

import admin.dao.Imple.AdminUserImple;
import admin.dao.Imple.PermissionImple;
import admin.dao.Imple.RoleImple;
import entity.AdminUser;
import entity.Role;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/admin/user/list/servlet")
public class ListAdminUser extends HttpServlet {


    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAdminUser() {
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

        PrintWriter out = response.getWriter();

        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();
        String pageStr = request.getParameter("p");
        String searchStr = request.getParameter("str");
        int page = 0;


        if (pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }

        //获取当前用户找到对应权限
        AdminUser user = (AdminUser) session.getAttribute("admin_user");

        PermissionImple permissionImple = new PermissionImple();
        permissionImple.openDatabase();

        //如果用户的权限为root,或者有该uri请求权限 则可以访问
        if (!permissionImple.ValidatePermission(user, "", request.getRequestURI())) {
            System.out.println("您没有权限操作");
            return;
        }

        List<AdminUser> adminUsers = new ArrayList<>();
        //然后进行添加用户操作
        permissionImple.openDatabase();
        int pageNum = 0;
        if (permissionImple.IdentityPermission(user)) {
            //超级管理员权限显示所有用户列表
            AdminUserImple adminUserImple = new AdminUserImple();
            adminUserImple.openDatabase();
            System.out.println("权限通过");
            adminUsers = adminUserImple.ListAdmin(10, page, searchStr);

            if (adminUsers.size() == 0) {
                adminUserImple.openDatabase();
                adminUsers = adminUserImple.ListAdmin(10, page, "");
            }
            adminUserImple.openDatabase();
            pageNum = adminUserImple.PageNum(10);

            RoleImple roleImple = new RoleImple();
            roleImple.openDatabase();

            List<Role> roles = new ArrayList<>();
            roles = roleImple.ListRole();


            request.setAttribute("roles", roles);
            request.setAttribute("adminUsers", adminUsers);
            request.setAttribute("pageNum", pageNum);
            request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
        } else {
            //普通管理员权限
            AdminUserImple adminUserImple = new AdminUserImple();
            adminUserImple.openDatabase();
            adminUsers = adminUserImple.ListAdminNormal(user);
            pageNum = 1;
            RoleImple roleImple = new RoleImple();
            roleImple.openDatabase();

            List<Role> roles = new ArrayList<>();
            roles = roleImple.ListRole();
            System.out.println("this is admin_user size:" + adminUsers.size());
            request.setAttribute("roles", roles);
            request.setAttribute("adminUsers", adminUsers);
            request.setAttribute("pageNum", pageNum);
            request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
        }


    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void init() throws ServletException {

    }
}
