package admin.controller.Item;

import admin.dao.Imple.ItemImple;
import admin.dao.Imple.ShelfImple;
import entity.Item;
import entity.Shelf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/admin/item/list/servlet")
public class ListItemController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListItemController() {
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
        String str = request.getParameter("str");
        String shelfIdStr = request.getParameter("shelf_id");

        ItemImple itemImple = new ItemImple();
        itemImple.openDatabase();


        int page = 1, shelf_id = 0;
        if (shelfIdStr == null) {
            shelfIdStr = "0";

        }

        if (pageStr == null) {
            pageStr = "1";
        }
        if (!pageStr.equals("")) {
            page = Integer.parseInt(pageStr);
        }

        System.out.println(shelfIdStr);
        shelf_id = Integer.parseInt(shelfIdStr);
        List<Item> items = new ArrayList<>();
        items = itemImple.ListItem(10, page, str, shelf_id);


        itemImple.openDatabase();
        int pageNum = itemImple.PageNum(10);

        if (items.size() == 0) {
            items = itemImple.ListItem(10, page, "", 0);
        }
        ShelfImple shelfImple = new ShelfImple();
        shelfImple.openDatabase();
        List<Shelf> shelves = shelfImple.ListShelf();

        request.setAttribute("shelves", shelves);
        request.setAttribute("items", items);
        request.setAttribute("search", str);
        request.setAttribute("pageNum", pageNum);
        request.getRequestDispatcher("/admin/item.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }


}
