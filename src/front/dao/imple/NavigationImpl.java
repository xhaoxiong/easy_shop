package front.dao.imple;

import front.dao.CommonDao;
import front.dao.NavigationDao;
import entity.Category;
import entity.Item;
import entity.Navigation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NavigationImpl extends CommonDao implements NavigationDao {
    @Override
    public List<Navigation> NavigationList() {
        String sql = "select *,category.id as cate_id,category.name as cate_name from navigation LEFT JOIN category ON navigation.cate_id=category.id;";
        List<Navigation> list = new ArrayList<Navigation>();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String sql1 = "select  navigation.id as id,navigation.name as name,navigation.cover as cover,item.id as item_id,item.name as item_name,now_price,origin_price,item.cover as item_cover,is_index_big,stock_qty,content,weight from item,navigation where item.nav_id=? ";
                Navigation navigation = new Navigation();
                List<Item> items = new ArrayList<>();

                navigation.setId(rs.getInt("id"));
                navigation.setName(rs.getString("name"));
                navigation.setUrl(rs.getString("url"));
                navigation.setCover(rs.getString("cover"));
                navigation.setCateId(rs.getInt("cate_id"));

                Category category = new Category();
                category.setName(rs.getString("cate_name"));
                category.setId(rs.getInt("cate_id"));

                navigation.setCate(category);

                ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, navigation.getId());

                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    Item item = new Item();
                    item.setId(rs1.getInt("item_id"));
                    item.setName(rs1.getString("item_name"));
                    item.setNavigationId(rs1.getInt("id"));
                    item.setNowPrice(rs1.getFloat("now_price"));
                    item.setOriginPrice(rs1.getFloat("origin_price"));
                    item.setCover(rs1.getString("item_cover"));
                    item.setIsIndexBig(rs1.getInt("is_index_big"));
                    item.setStockQty(rs1.getInt("stock_qty"));
                    item.setContent(rs1.getString("content"));
                    items.add(item);
                }
                navigation.setItems(items);
                list.add(navigation);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
