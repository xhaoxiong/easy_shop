package front.dao.imple;

import front.dao.CommonDao;
import front.dao.ItemDao;
import entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl extends CommonDao implements ItemDao {
    @Override
    public List<Item> ListItem(int pers, int page, int cate_id, String is_price, String is_volume, String search) {

        int total = 0;
        String sql = "";
        String sql1 = "select count(*) from item LEFT JOIN  category ON item.cate_id=category.id LEFT JOIN  brand ON item.brand_id=brand.id  LEFT JOIN  navigation ON item.nav_id=navigation.id WHERE item.cate_id=? or item.root_cate_id=?";
        List<Item> items = new ArrayList<>();

        try {
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, cate_id);
            ps1.setInt(2, cate_id);

            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                total = rs1.getInt(1);
            }

            //总数除以每页条数
            int pageNum = (int) Math.ceil(total / (double) pers);
            if (page > pageNum) {
                page = pageNum;
            }
            int offset = (page - 1) * pers;


            if (is_price != null) {
                sql = "select item.id as id,item.name as name,now_price,origin_price,item.cover as item_cover,is_index_big,stock_qty,content,weight,nav_id,category.id as cate_id,brand.id as brand_id,category.name as cate_name,brand.name as brand_name,navigation.id as nav_id,navigation.name as nav_name from item LEFT JOIN  category ON item.cate_id=category.id  LEFT JOIN  brand ON item.brand_id=brand.id  LEFT JOIN  navigation ON item.nav_id=navigation.id WHERE item.cate_id=? or item.root_cate_id=? order by now_price  DESC limit ?,?";

            } else if (is_volume != null) {
                sql = "select item.id as id,item.name as name,now_price,origin_price,item.cover as item_cover,is_index_big,stock_qty,content,weight,nav_id,category.id as cate_id,brand.id as brand_id,category.name as cate_name,brand.name as brand_name,navigation.id as nav_id,navigation.name as nav_name from item LEFT JOIN  category ON item.cate_id=category.id  LEFT JOIN  brand ON item.brand_id=brand.id  LEFT JOIN  navigation ON item.nav_id=navigation.id WHERE item.cate_id=? or item.root_cate_id=? order by volume DESC limit ?,?";
            } else if (search != null) {
                sql = "select item.id as id,item.name as name,now_price,origin_price,item.cover as item_cover,is_index_big,stock_qty,content,weight,nav_id,category.id as cate_id,brand.id as brand_id,category.name as cate_name,brand.name as brand_name,navigation.id as nav_id,navigation.name as nav_name from item LEFT JOIN  category ON item.cate_id=category.id  LEFT JOIN  brand ON item.brand_id=brand.id  LEFT JOIN  navigation ON item.nav_id=navigation.id WHERE  item.name like ? order by id ASC limit ?,?";
            } else {
                sql = "select item.id as id,item.name as name,now_price,origin_price,item.cover as item_cover,is_index_big,stock_qty,content,weight,nav_id,category.id as cate_id,brand.id as brand_id,category.name as cate_name,brand.name as brand_name,navigation.id as nav_id,navigation.name as nav_name from item LEFT JOIN  category ON item.cate_id=category.id  LEFT JOIN  brand ON item.brand_id=brand.id  LEFT JOIN  navigation ON item.nav_id=navigation.id WHERE item.cate_id=? or item.root_cate_id=?  order by id ASC limit ?,?";
            }

            ps = conn.prepareStatement(sql);
            if (search == null) {
                ps.setInt(1, cate_id);
                ps.setInt(2, cate_id);
                ps.setInt(3, offset);
                ps.setInt(4, pers);
            } else {
                ps.setString(1, "%" + search + "%");
                ps.setInt(2, offset);
                ps.setInt(3, pers);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setNowPrice(rs.getFloat("now_price"));
                item.setOriginPrice(rs.getFloat("origin_price"));
                item.setCover(rs.getString("item_cover"));
                item.setIsIndexBig(rs.getInt("is_index_big"));
                item.setStockQty(rs.getInt("stock_qty"));
                item.setWeight(rs.getFloat("weight"));
                item.setNavigationId(rs.getInt("nav_id"));

                Category cate = new Category();
                cate.setName(rs.getString("cate_name"));
                cate.setId(rs.getInt("cate_id"));

                item.setCate(cate);
                item.setCateId(cate.getId());


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
    public Item ItemDetail(int itemId) {
        String sql = "select *,category.id as cate_id,category.name as cate_name ,brand.id as brand_id,brand.name as brand_name,root_cate_id  from  item,category,brand,navigation where item.id=? and item.brand_id=brand.id and item.cate_id=category.id AND navigation.id=item.nav_id;";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, itemId);

            ResultSet rs = ps.executeQuery();
            Item item = new Item();
            if (rs.next()) {

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setNowPrice(rs.getFloat("now_price"));
                item.setOriginPrice(rs.getFloat("origin_price"));
                item.setCover(rs.getString("cover"));
                item.setIsIndexBig(rs.getInt("is_index_big"));
                item.setStockQty(rs.getInt("stock_qty"));
                item.setWeight(rs.getFloat("weight"));
                item.setNavigationId(rs.getInt("nav_id"));
                item.setCateId(rs.getInt("cate_id"));

                item.setRootCateId(rs.getInt("root_cate_id"));
                Category category = new Category();
                category.setId(rs.getInt("cate_id"));
                category.setName(rs.getString("cate_name"));

                Brand brand = new Brand();
                brand.setId(rs.getInt("brand_id"));
                brand.setName(rs.getString("brand_name"));

                item.setBrand(brand);
                item.setCate(category);
            }
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<OptionType> OptionType(int itemId) {


        String sql = "select distinct option_type.name as option_type_name,option_type.id as option_type_id from item_option,option_type ,item where item_option.option_type_id=option_type.id and item_option.item_id=?";


        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, itemId);

            ResultSet rs = ps.executeQuery();

            List<OptionType> optionTypes = new ArrayList<>();

            String sql1 = "select * from item_option where item_id=? and option_type_id=?";
            while (rs.next()) {
                OptionType optionType = new OptionType();
                optionType.setId(rs.getInt("option_type_id"));
                optionType.setName(rs.getString("option_type_name"));

                ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, itemId);
                ps1.setInt(2, rs.getInt("option_type_id"));
                ResultSet rs1 = ps1.executeQuery();
                List<ItemOption> itemOptions = new ArrayList<>();
                while (rs1.next()) {
                    ItemOption itemOption = new ItemOption();

                    itemOption.setId(rs1.getInt("id"));
                    itemOption.setItemId(rs1.getInt("item_id"));
                    itemOption.setOptionTypeId(rs1.getInt("option_type_id"));
                    itemOption.setText(rs1.getString("text"));
                    itemOption.setPrice(rs1.getFloat("price"));
                    //将获取的数据赋值给itemoption
                    itemOptions.add(itemOption);
                }
                optionType.setItemOptions(itemOptions);
                optionTypes.add(optionType);
            }

            return optionTypes;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemAlbum> ItemAlbum(int itemId) {
        String sql = "select *from item_album where item_id=?";

        List<ItemAlbum> itemAlbums = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemAlbum itemAlbum = new ItemAlbum();
                itemAlbum.setImage(rs.getString("image"));
                itemAlbum.setId(rs.getInt("id"));
                itemAlbum.setItemId(rs.getInt("item_id"));
                itemAlbums.add(itemAlbum);
            }
            return itemAlbums;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }


        return null;
    }

    @Override
    public List<ItemAttr> ItemAttr(Item item) {
        String sql = "select *,cate_attr.name as cate_attr_name ,cate_attr.id as cate_attr_id from item_attr,cate_attr where  item_attr.cate_attr_id=cate_attr.id and item_id=? and cate_id=?";

        List<ItemAttr> itemAttrs = new ArrayList();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, item.getId());
            ps.setInt(2, item.getCateId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemAttr itemAttr = new ItemAttr();
                itemAttr.setValue(rs.getString("value"));
                itemAttr.setId(rs.getInt("id"));
                itemAttr.setCateAttrId(rs.getInt("cate_attr_id"));
                itemAttr.setItemId(rs.getInt("item_id"));

                CateAttr cateAttr = new CateAttr();
                cateAttr.setName(rs.getString("cate_attr_name"));

                itemAttr.setCateAttr(cateAttr);

                itemAttrs.add(itemAttr);
            }
            return itemAttrs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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

    @Override
    public List<ItemOption> ListItemOption(List<ItemOption> itemOptions) {
        String sql = "select *from item_option where id =? and item_id=?";

        try {
            List<ItemOption> itemOptions1 = new ArrayList<>();
            for (ItemOption itemOption : itemOptions) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, itemOption.getId());
                ps.setInt(2, itemOption.getItemId());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    itemOption.setPrice(rs.getFloat("price"));
                    itemOption.setText(rs.getString("text"));
                    itemOptions1.add(itemOption);
                }
            }
            return itemOptions1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public ItemOption ItemOption(int optionTypeId) {

        return null;
    }


}
