package front.controller;

import front.dao.imple.ItemDaoImpl;
import entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class TestController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");

        //items
        ItemDaoImpl itemDao = new ItemDaoImpl();
        itemDao.openDatabase();
        List<Item> items = itemDao.ListItem(10, 1, 0, "", "","");
        for (Item item : items) {
            System.out.println(item.getName());
        }

//        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

}
