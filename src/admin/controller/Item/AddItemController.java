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

@WebServlet("/admin/item/add/servlet")
public class AddItemController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemController() {
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

        String itemName = request.getParameter("item_name");
        String itemNumber = request.getParameter("item_number");
        String shelfIdStr = request.getParameter("shelf_id");
        String inCountStr = request.getParameter("in_count");
        HttpSession session = request.getSession();
        JSONObject data = new JSONObject();

        int shelfId = 0, inCount = 0;
        if (itemName == null | shelfIdStr == null | inCountStr == null) {
            data.put("status", 10002);
            data.put("message", "参数不能为空");
            response.getWriter().print(data);
            return;
        }

        AdminUser user = (AdminUser) session.getAttribute("admin_user");

        shelfId = Integer.parseInt(shelfIdStr);
        if (!inCountStr.equals("")) {
            inCount = Integer.parseInt(inCountStr);
        }

        ItemImple itemImple = new ItemImple();
        itemImple.openDatabase();
        RandomStringUtil randomStringUtil = new RandomStringUtil();
        String itemRandNumber = randomStringUtil.getRandomCode(18, 0);
        if (itemNumber.equals("")) {
            itemNumber = itemRandNumber;
        }


        Item item = new Item();
        item.setShelfId(shelfId);
        if (inCount > 0) {
            item.setStockQty(inCount);
        }
        item.setName(itemName);
        item.setNumber(itemNumber);
        int item_id = itemImple.AddItem(item);


        StoreRecordImple storeRecordImple = new StoreRecordImple();
        storeRecordImple.openDatabase();


        String recordNumber = randomStringUtil.getRandomCode(16, 0);
        System.out.println("inCount:" + inCount + "item_id:" + item_id);
        if (inCount > 0 && item_id != 0) {
            StoreRecord storeRecord = new StoreRecord();
            StoreRecordDetail storeRecordDetail = new StoreRecordDetail();

            storeRecord.setAdminUserId(user.getId());
            storeRecord.setCreateTime(new Date());
            storeRecord.setRecordNumber(recordNumber);
            storeRecord.setType(1);
            storeRecord.setUpdateTime(new Date());

            storeRecordDetail.setAdminUserId(user.getId());
            storeRecordDetail.setCount(inCount);
            storeRecordDetail.setRecordNumber(recordNumber);
            storeRecordDetail.setItemId(item_id);
            storeRecordDetail.setCreateTime(new Date());
            storeRecordDetail.setUpdateTime(new Date());

            if (storeRecordImple.AddStoreRecord(storeRecord, storeRecordDetail, inCount)) {
                data.put("status", 10000);
                data.put("message", "入库成功");
                response.getWriter().print(data);
                return;
            }
        }

        data.put("status", 10000);
        data.put("message", "入库成功");
        response.getWriter().print(data);
        return;
    }
}
