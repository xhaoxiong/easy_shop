package front.controller.Cart;

import front.dao.imple.CateDaoImpl;
import front.dao.imple.NavigationImpl;
import front.dao.imple.UserCartDaoImpl;
import entity.Category;
import entity.Navigation;
import entity.User;
import entity.UserCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart/list/servlet")
public class CartListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userinfo");
        if (user == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        //竖着导航分类
        CateDaoImpl cateDao = new CateDaoImpl();
        cateDao.openDatabase();
        List<Category> list = cateDao.ListNodeByParentId(0);
        cateDao.closeAll();
        //横着导航
        NavigationImpl navDao = new NavigationImpl();
        navDao.openDatabase();
        List<Navigation> navs = navDao.NavigationList();
        navDao.closeAll();


        UserCartDaoImpl userCartDao = new UserCartDaoImpl();
        userCartDao.openDatabase();

        System.out.println("this is user cart start");
        List<UserCart> userCarts = userCartDao.UserCartList(user.getId());
        userCartDao.closeAll();
        System.out.println("this is user cart end");

        request.setAttribute("userCarts", userCarts);

        request.setAttribute("categoryList", list);
        request.setAttribute("navs", navs);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


    }

    public void init() throws ServletException {

    }

}
