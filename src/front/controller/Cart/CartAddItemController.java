package front.controller.Cart;

import front.dao.imple.ItemDaoImpl;
import front.dao.imple.UserCartDaoImpl;
import entity.ItemOption;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet("/cart/add/servlet")
public class CartAddItemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartAddItemController() {
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


        String itemIdStr = request.getParameter("item_id");
        String[] optionMaps = request.getParameterValues("option_map[]");
        String countStr = request.getParameter("count");

        String optionsStr = "";


        int itemId;
        int count;

        if (user == null) {
            data.put("status", 10001);
            data.put("message", "请登录");
            response.getWriter().print(data);
            return;
        }

        if (itemIdStr == null) {
            data.put("status", 10002);
            data.put("message", "请传入商品id");
            response.getWriter().print(data);
            return;
        } else {
            itemId = Integer.parseInt(itemIdStr);
        }


        if (optionMaps == null || optionMaps.length == 0) {
            data.put("status", 10003);
            data.put("message", "请选择合适的商品选项");
            response.getWriter().print(data);
            return;
        }

        if (countStr != null) {
            count = Integer.parseInt(countStr);
        } else {
            data.put("status", 10005);
            data.put("message", "limit count");
            response.getWriter().print(data);
            return;
        }
        //将数组转换为字符串存入数据库一个字段
        for (String optionMap : optionMaps) {
            optionsStr += optionMap + ",";
        }

        //从optionMap中获取itemoption的实际价格

        float price = 0;
        List<ItemOption> itemOptions = new ArrayList<>();
        String[] sourceStrArray = optionsStr.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            int option_id = Integer.parseInt(sourceStrArray[i]);
            ItemOption itemOption = new ItemOption();
            itemOption.setItemId(itemId);
            itemOption.setId(option_id);
            itemOptions.add(itemOption);
        }

        ItemDaoImpl itemDao = new ItemDaoImpl();
        itemDao.openDatabase();
        itemOptions = itemDao.ListItemOption(itemOptions);


        for (ItemOption itemOption : itemOptions) {
            if (itemOption.getPrice() != 0) {
                price += itemOption.getPrice();
            }
        }

        System.out.println(price);

        UserCart userCart = new UserCart();
        //获取商品item_id 将optionMAP选项信息存入
        userCart.setItemId(itemId);
        userCart.setUserId(user.getId());
        userCart.setOptionMap(optionsStr);
        userCart.setDate(new Date());
        userCart.setCounts(count);
        userCart.setPrice(price);

        UserCartDaoImpl userCartDao = new UserCartDaoImpl();
        userCartDao.openDatabase();

        //添加商品到该用户的购物车
        if (userCartDao.AddItemCart(userCart)) {
            data.put("status", 10000);
            data.put("message", "添加成功");
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
