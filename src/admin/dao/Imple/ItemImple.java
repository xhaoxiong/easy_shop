package admin.dao.Imple;

import admin.dao.ItemDao;
import entity.Item;
import entity.Shelf;
import front.dao.CommonDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemImple extends CommonDao implements ItemDao {
    @Override
    public int AddItem(Item item) {
        String sql = "insert into item (name,number,shelf_id,stock_qty) values (?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getName());
            ps.setString(2, item.getNumber());
            ps.setInt(3, item.getShelfId());
            ps.setInt(4, item.getStockQty());
            int num = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (num > 0 && rs.next()) {
                long id = rs.getInt(1);
                return (int) id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return 0;
    }

    @Override
    public List<Item> ListItem(int pers, int page, String search, int ShelfId) {
        String sql = "";
        String sql1 = "select count(*) from item";
        int total = 0;

        try {
            ps1 = conn.prepareStatement(sql1);

            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                total = rs1.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("this is query");
        if (search != null) {
            System.out.println(search);
            sql = "select item.id,item.name,item.number,item.stock_qty,shelf.name as shelf_name,shelf.id as shelf_id  from item LEFT JOIN shelf on item.shelf_id=shelf.id  where item.number like ? ";
        } else if (ShelfId != 0) {
            System.out.println("shelf_id" + ShelfId);
            sql = "select item.id,item.name,item.number,item.stock_qty,shelf.name as shelf_name,shelf.id as shelf_id  from item LEFT JOIN shelf on item.shelf_id=shelf.id where shelf_id=? order by item.id DESC limit ?,?";
        } else {
            sql = "select item.id,item.name,item.number,item.stock_qty,shelf.name as shelf_name,shelf.id as shelf_id from  item left join shelf on item.shelf_id=shelf.id order by item.id desc limit ?,?";
        }

        int pageNum = (int) Math.ceil(total / (double) pers);
        if (page > pageNum) {
            page = pageNum;
        }
        int offset = (page - 1) * pers;


        try {

            System.out.println(ShelfId);
            if (search != null) {
                System.out.println("进入了搜索");
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();

            } else if (ShelfId != 0) {
                System.out.println("进入了shelf_id筛选");
                ps = conn.prepareStatement(sql);
                ps.setInt(1, ShelfId);
                ps.setInt(2, offset);
                ps.setInt(3, pers);
                rs = ps.executeQuery();
            } else {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, offset);
                ps.setInt(2, pers);
                rs = ps.executeQuery();
            }

            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setNumber(rs.getString("number"));
                item.setStockQty(rs.getInt("stock_qty"));

                Shelf shelf = new Shelf();
                shelf.setName(rs.getString("shelf_name"));
                shelf.setId(rs.getInt("shelf_id"));
                item.setShelfId(rs.getInt("shelf_id"));
                item.setShelf(shelf);

                items.add(item);

            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return null;
    }

    @Override
    public Boolean DelItem() {
        return null;
    }


    @Override
    public Boolean AddItemQty(Item item) {
        String sql = "select * from item where id= ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, item.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int currentCount = rs.getInt("stock_qty");
                currentCount += item.getStockQty();
                System.out.println(currentCount);
                String sql1 = "update  item set stock_qty=? where id =?";

                ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, currentCount);
                ps1.setInt(2, item.getId());

                int num = ps1.executeUpdate();

                if (num > 0) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return false;
    }

    @Override
    public Boolean CutItemQty(Item item) {
        String sql = "select * from item where id= ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, item.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int currentCount = rs.getInt("stock_qty");
                if (currentCount <= 0) {
                    return false;
                }
                currentCount -= item.getStockQty();
                if (currentCount < 0) {
                    return false;
                }
                System.out.println(currentCount);
                String sql1 = "update  item set stock_qty=? where id =?";

                ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, currentCount);
                ps1.setInt(2, item.getId());

                int num = ps1.executeUpdate();

                if (num > 0) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return false;
    }

    @Override
    public int PageNum(int pers) {
        String sql = "select count(*) from item";

        int total = 0;

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
                int pageNum = (int) Math.ceil(total / (double) pers);
                return pageNum;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return 0;
    }
}
