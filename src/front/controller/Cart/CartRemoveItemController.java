package front.controller.Cart;

import front.dao.imple.UserCartDaoImpl;
import entity.User;
import entity.UserCart;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/cart/remove/servlet")
public class CartRemoveItemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartRemoveItemController() {
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


        HttpSession session = request.getSession();
        JSONObject data = new JSONObject();

        User user = (User) session.getAttribute("userinfo");


        String userCartIdStr = request.getParameter("user_cart_id");

        System.out.println(userCartIdStr);

        int userCartId;

        if (user == null) {
            //
            data.put("status", 10001);
            data.put("message", "请登陆");
            response.getWriter().print(data);
            return;
        }


        if (userCartIdStr == null) {
            data.put("status", 10002);
            data.put("message", "请传入id");
            response.getWriter().print(data);
            return;
        }

        userCartId = Integer.parseInt(userCartIdStr);


        UserCart userCart = new UserCart();
        userCart.setId(userCartId);


        UserCartDaoImpl userCartDao = new UserCartDaoImpl();
        userCartDao.openDatabase();

        //添加商品到该用户的购物车
        if (userCartDao.DelItemCart(userCart)) {
            data.put("status", 10000);
            data.put("message", "移除成功");
            response.getWriter().print(data);
            return;
        } else {
            data.put("status", 10004);
            data.put("message", "服务器错误");
            response.getWriter().print(data);
            return;
        }
    }

    public void init() throws ServletException {

    }
}
