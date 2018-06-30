package front.controller.Item;


import front.dao.imple.CateDaoImpl;
import front.dao.imple.ItemDaoImpl;
import front.dao.imple.NavigationImpl;
import entity.Category;
import entity.Item;
import entity.Navigation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/item/list/servlet")
public class ItemListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemListController() {
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
        String cateIdStr = request.getParameter("cate_id");

        String isPrice = request.getParameter("is_price");
        String isVolume = request.getParameter("is_volume");

        String Search=request.getParameter("search");

        int page = 0;
        int cateId = 0;
        if (pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }

        if (cateIdStr == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            cateId = Integer.parseInt(cateIdStr);
        }

        //竖着导航分类
        CateDaoImpl cateDao = new CateDaoImpl();
        cateDao.openDatabase();
        List<Category> list = cateDao.ListNodeByParentId(0);
        //横着导航
        NavigationImpl navDao = new NavigationImpl();
        navDao.openDatabase();
        List<Navigation> navs = navDao.NavigationList();
        navDao.closeAll();


        //面包屑

        CateDaoImpl cateDao2 = new CateDaoImpl();
        cateDao2.openDatabase();
        Category category = cateDao.CurrentCategory(cateId);

        //商品列表
        ItemDaoImpl itemDao = new ItemDaoImpl();
        itemDao.openDatabase();
        List<Item> items = itemDao.ListItem(10, page, cateId, isPrice, isVolume,Search);

        //传入每页多少条得出多少页 分页
        itemDao.openDatabase();
        int pageNum = itemDao.PageNum(10);


        request.setAttribute("category", category);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("categoryList", list);
        request.setAttribute("navs", navs);
        request.setAttribute("items", items);


        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


    }


}
