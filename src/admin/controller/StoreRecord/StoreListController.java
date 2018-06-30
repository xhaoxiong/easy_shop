package admin.controller.StoreRecord;


import admin.dao.Imple.ItemImple;
import admin.dao.Imple.StoreRecordImple;
import entity.StoreRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/store/list/servlet")
public class StoreListController extends HttpServlet {


    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreListController() {
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
        String typeStr = request.getParameter("type");

        ItemImple itemImple = new ItemImple();
        itemImple.openDatabase();


        int page = 1,type=0;


        if (pageStr == null) {
            pageStr = "1";
        }
        if (!pageStr.equals("")) {
            page = Integer.parseInt(pageStr);
        }

        if (typeStr!=null){
            type=Integer.parseInt(typeStr);
        }

        List<StoreRecord> storeRecordList = new ArrayList<>();
        StoreRecordImple storeRecordImple = new StoreRecordImple();
        storeRecordImple.openDatabase();
        storeRecordList = storeRecordImple.ListStoreRecord(10, page, str,type);

        storeRecordImple.openDatabase();
        int PageNum=storeRecordImple.PageNum(10);

        request.setAttribute("pageNum",PageNum);



        request.setAttribute("storeRecordList", storeRecordList);
        request.getRequestDispatcher("/admin/store.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }


}
