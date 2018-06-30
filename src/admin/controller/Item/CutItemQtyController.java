package admin.controller.Item;


import admin.dao.Imple.ItemImple;
import admin.dao.Imple.StoreRecordImple;
import entity.AdminUser;
import entity.Item;
import entity.StoreRecord;
import entity.StoreRecordDetail;
import net.sf.json.JSONObject;
import utils.RandomStringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/admin/item/qty/cut/servlet")
public class CutItemQtyController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CutItemQtyController() {
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

        String itemIdStr = request.getParameter("item_id");
        String inCountStr = request.getParameter("input_count");
        HttpSession session = request.getSession();
        JSONObject data = new JSONObject();

        int itemId = 0, inCount = 0;
        if (itemIdStr == null | inCountStr == null) {
            data.put("status", 10002);
            data.put("message", "参数不能为空");
            response.getWriter().print(data);
            return;
        }


        AdminUser user = (AdminUser) session.getAttribute("admin_user");

        if (!inCountStr.equals("")) {
            inCount = Integer.parseInt(inCountStr);
        }

        if (!itemIdStr.equals("")) {
            itemId = Integer.parseInt(itemIdStr);
        }
        ItemImple itemImple = new ItemImple();
        itemImple.openDatabase();

        StoreRecordImple storeRecordImple = new StoreRecordImple();
        storeRecordImple.openDatabase();

        RandomStringUtil randomStringUtil = new RandomStringUtil();
        String recordNumber = randomStringUtil.getRandomCode(16, 0);
        StoreRecord storeRecord = new StoreRecord();
        StoreRecordDetail storeRecordDetail = new StoreRecordDetail();
        Item item = new Item();
        if (inCount > 0 && itemId != 0) {
            item.setStockQty(inCount);
            item.setId(itemId);
        }
        System.out.println("inCount:" + inCount + "itemId:" + itemId);

        if (itemImple.CutItemQty(item)) {

            storeRecord.setAdminUserId(user.getId());
            storeRecord.setCreateTime(new Date());
            storeRecord.setRecordNumber(recordNumber);
            storeRecord.setType(2);
            storeRecord.setUpdateTime(new Date());

            storeRecordDetail.setAdminUserId(user.getId());
            storeRecordDetail.setCount(inCount);
            storeRecordDetail.setRecordNumber(recordNumber);
            storeRecordDetail.setItemId(itemId);
            storeRecordDetail.setCreateTime(new Date());
            storeRecordDetail.setUpdateTime(new Date());

            if (storeRecordImple.AddStoreRecord(storeRecord, storeRecordDetail, inCount)) {
                data.put("status", 10000);
                data.put("message", "出库成功");
                response.getWriter().print(data);
            }
        } else {
            data.put("status", 10003);
            data.put("message", "出库失败");
            response.getWriter().print(data);
        }

    }
}
