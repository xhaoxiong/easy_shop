package admin.controller.Role;

import admin.dao.Imple.RoleImple;
import entity.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/admin/role/list/servlet")
public class ListRoleController extends HttpServlet {


    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListRoleController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");

        String pageStr = request.getParameter("p");

        RoleImple roleImple = new RoleImple();
        roleImple.openDatabase();

        List<Role> roles = new ArrayList<>();
        roles = roleImple.ListRole();

        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/admin/role.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


    }


}
