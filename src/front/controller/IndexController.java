package front.controller;


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

@WebServlet("/index/servlet")
public class IndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");
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


        //items
        ItemDaoImpl itemDao = new ItemDaoImpl();
        itemDao.openDatabase();
        List<Item> items = itemDao.ListItem(10, 1, 0, "", "","");


        request.setAttribute("categoryList", list);
        request.setAttribute("navs", navs);
        request.setAttribute("items", items);


        request.getRequestDispatcher("/index.jsp").forward(request, response);

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
