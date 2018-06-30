package front.controller.Buy;

import front.dao.imple.CateDaoImpl;
import front.dao.imple.NavigationImpl;
import front.dao.imple.UserCartDaoImpl;
import front.dao.imple.UserConsigneeImpl;
import entity.*;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@WebServlet("/buy/servlet")
public class BuyController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyController() {
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

        String cartMapStr = request.getParameter("cart_map");
        JSONObject jsonObject = JSONObject.fromObject(cartMapStr);
        Map<String, String> cartMaps = (Map) jsonObject;

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

        //map中是购物车id与购买数量的键值对

        List<UserCart> userCarts = new ArrayList<>();


        for (Map.Entry<String, String> cartMap : cartMaps.entrySet()) {
            UserCart userCart = new UserCart();
            System.out.println("this is key:" + cartMap.getKey() + "value:" + cartMap.getValue());
            userCart.setId(Integer.parseInt(cartMap.getKey()));
            userCart.setCounts(Integer.parseInt(cartMap.getValue()));
            userCarts.add(userCart);
        }


        //获取用户选中的商品的具体信息
        UserCartDaoImpl userCartDao = new UserCartDaoImpl();


        List<UserCart> userCarts1 = new ArrayList<>();
        userCartDao.openDatabase();
        for (UserCart userCart : userCarts) {
            userCart = userCartDao.FindUserCartById(userCart);
            userCarts1.add(userCart);
        }


        //获取用户地址
        UserConsigneeImpl userConsignee = new UserConsigneeImpl();
        userConsignee.openDatabase();
        List<Consignee> consignees = new ArrayList<>();
        consignees = userConsignee.FindUserConsigneeByUserId(user.getId());


        request.setAttribute("consignees", consignees);
        request.setAttribute("userCartList", userCarts1);
        request.setAttribute("categoryList", list);
        request.setAttribute("navs", navs);

        //
        request.getRequestDispatcher("/buy.jsp").forward(request, response);

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
