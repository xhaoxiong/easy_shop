package front.controller.Item;

import front.dao.imple.CateDaoImpl;
import front.dao.imple.ItemDaoImpl;
import front.dao.imple.NavigationImpl;
import entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/item/detail/servlet")
public class DetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");

        String itemIdStr = request.getParameter("item_id");

        if (itemIdStr == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        int itemId = Integer.parseInt(itemIdStr);

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


        //获取单个商品详情
        ItemDaoImpl itemDao = new ItemDaoImpl();
        itemDao.openDatabase();
        Item item = itemDao.ItemDetail(itemId);


        //商品选项
        List<OptionType> optionTypes = itemDao.OptionType(itemId);


        //商品图册
        List<ItemAlbum> itemAlbums = itemDao.ItemAlbum(itemId);


        //面包屑 通过该商品分类

        CateDaoImpl cateDao2 = new CateDaoImpl();
        cateDao2.openDatabase();


        List<Category> breadCateList = cateDao2.ListParentNodeById(item.getCateId());
        cateDao2.closeAll();

        List<Category> newBreadCateList = new ArrayList<>();
        //转换面包屑分类排序
        for (Category category : breadCateList) {
            for (Category category1 : category.getCategory()) {
                for (Category category2 : category1.getCategory()) {
                    newBreadCateList.add(category2);
                }
                newBreadCateList.add(category1);
            }
            newBreadCateList.add(category);
        }

        itemDao.openDatabase();
        //规格参数
        List<ItemAttr> itemAttrs = itemDao.ItemAttr(item);

        //今日推荐暂时不写


        //该用户还购买了暂时不写

        request.setAttribute("categoryList", list);
        request.setAttribute("navs", navs);
        request.setAttribute("item", item);
        request.setAttribute("optionTypes", optionTypes);
        request.setAttribute("breadCateList", newBreadCateList);
        request.setAttribute("itemAlbums", itemAlbums);
        request.setAttribute("itemAttrs", itemAttrs);
        request.getRequestDispatcher("/detail.jsp").forward(request, response);

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
