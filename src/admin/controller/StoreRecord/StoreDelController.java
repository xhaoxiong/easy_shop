package admin.controller.StoreRecord;

import admin.dao.Imple.StoreRecordImple;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/store/delete/one/servlet")
public class StoreDelController extends HttpServlet {


    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreDelController() {
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

        String idStr = request.getParameter("id");
        int id = 0;
        JSONObject data = new JSONObject();

        if (idStr == null) {
            data.put("status", 10002);
            data.put("message", "参数不能为空");
            response.getWriter().print(data);
            return;
        }

        id = Integer.parseInt(idStr);

        StoreRecordImple storeRecordImple = new StoreRecordImple();
        storeRecordImple.openDatabase();

        if (storeRecordImple.DelStoreRecord(id)) {
            data.put("status", 10000);
            data.put("message", "删除成功");
            response.getWriter().print(data);

        } else {
            data.put("status", 10003);
            data.put("message", "删除失败");
            response.getWriter().print(data);
        }
    }


}
