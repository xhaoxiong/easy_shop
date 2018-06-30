package front.dao.imple;

import front.dao.CommonDao;
import front.dao.UserCartDao;
import entity.Item;
import entity.ItemOption;
import entity.OptionType;
import entity.UserCart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCartDaoImpl extends CommonDao implements UserCartDao {
    @Override
    public boolean AddItemCart(UserCart userCart) {

        //插入之前首先判断该用户是否已经加入了购物车
        int id = ItemIsInCart(userCart);
        if (id != 0) {
            if (UpdateItemCartCount(userCart, id)) {
                return true;
            } else {
                return false;
            }
        } else {

            String sql = "insert into user_cart (user_id,item_id,date,option_map,counts,price) values(?,?,?,?,?,?)";

            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, userCart.getUserId());
                ps.setInt(2, userCart.getItemId());
                java.sql.Date sdate = new java.sql.Date(userCart.getDate().getTime());
                ps.setDate(3, sdate);
                ps.setString(4, userCart.getOptionMap());
                ps.setInt(5, userCart.getCounts());
                ps.setFloat(6, userCart.getPrice());
                int rs = ps.executeUpdate();

                if (rs > 0) {
                    return true;
                }
                return false;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean DelItemCart(UserCart userCart) {

        if (FindUserCartByUserId(userCart.getId())) {
            String sql = "DELETE  FROM  user_cart WHERE  id =?";

            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, userCart.getId());

                int rs = ps.executeUpdate();
                if (rs > 0) {
                    return true;
                }
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }

        return false;
    }

    @Override
    public List<UserCart> UserCartList(int userId) {
        String sql = "select *,user_cart.price as real_price from user_cart left JOIN item on user_cart.item_id=item.id where user_id=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            String sql1 = "select * from item_option left join option_type on item_option.option_type_id=option_type.id where item_option.id =?";


            ResultSet rs = ps.executeQuery();
            List<UserCart> userCarts = new ArrayList<>();

            while (rs.next()) {
                UserCart userCart = new UserCart();
                userCart.setId(rs.getInt("id"));
                userCart.setOptionMap(rs.getString("option_map"));
                userCart.setCounts(rs.getInt("counts"));
                userCart.setPrice(rs.getFloat("real_price"));
                Item item = new Item();
                item.setName(rs.getString("name"));
                item.setCover(rs.getString("cover"));
                userCart.setItem(item);
                String sourceStr = rs.getString("option_map");


                String[] sourceStrArray = sourceStr.split(",");

                List<ItemOption> itemOptions = new ArrayList<>();
                //将optionMap查出来 存入userCart
                for (int i = 0; i < sourceStrArray.length; i++) {
                    int option_id = Integer.parseInt(sourceStrArray[i]);

                    //获取选项的具体信息

                    ps1 = conn.prepareStatement(sql1);
                    ps1.setInt(1, option_id);
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        ItemOption itemOption = new ItemOption();
                        itemOption.setId(option_id);
                        itemOption.setText(rs1.getString("text"));
                        itemOption.setPrice(rs1.getFloat("price"));

                        OptionType optionType = new OptionType();

                        optionType.setName(rs1.getString("name"));
                        optionType.setId(rs1.getInt("option_type_id"));
                        itemOption.setOptionType(optionType);
                        itemOptions.add(itemOption);
                    }
                    userCart.setItemOption(itemOptions);
                }
                userCarts.add(userCart);
            }
            return userCarts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int ItemIsInCart(UserCart userCart) {
        String sql = "select * from user_cart where user_id= ? and item_id= ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userCart.getUserId());
            ps.setInt(2, userCart.getItemId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString("option_map").equals(userCart.getOptionMap())) {
                    return rs.getInt("id");
                }
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean UpdateItemCartCount(UserCart userCart, int id) {
        String sql1 = "select counts from user_cart where id =?";
        String sql = "update user_cart set counts=?,price=? where id =?";

        try {
            //获取数据库中之前的值rsCount 然后加上现在的值count
            ps1 = conn.prepareStatement(sql1);

            ps1.setInt(1, id);

            ResultSet rs1 = ps1.executeQuery();
            int rsCount = 0;
            if (rs1.next()) {
                rsCount = rs1.getInt("counts");
            }
            ps = conn.prepareStatement(sql);
            ps.setInt(1, rsCount + userCart.getCounts());
            ps.setFloat(2, userCart.getPrice());
            ps.setInt(3, id);


            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean FindUserCartByUserId(int userCartId) {
        String sql = "select * from user_cart where id =?";

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, userCartId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public UserCart FindUserCartById(UserCart userCart) {
        String sql = "select *,user_cart.price as real_price,user_cart.id as id from user_cart left JOIN item on user_cart.item_id=item.id where id=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userCart.getId());
            String sql1 = "select * from item_option left join option_type on item_option.option_type_id=option_type.id where item_option.id =?";


            ResultSet rs = ps.executeQuery();
            UserCart userCart1 = new UserCart();
            //获取cart的一条纪录
            if (rs.next()) {
                userCart1.setId(userCart.getId());
                userCart1.setOptionMap(rs.getString("option_map"));
                userCart1.setCounts(userCart.getCounts());
                userCart1.setPrice(rs.getFloat("real_price"));
                Item item = new Item();
                item.setName(rs.getString("name"));
                item.setCover(rs.getString("cover"));
                userCart1.setItem(item);
                String sourceStr = rs.getString("option_map");


                String[] sourceStrArray = sourceStr.split(",");

                List<ItemOption> itemOptions = new ArrayList<>();
                //该条纪录的optionMap查出来 存入userCart
                for (int j = 0; j < sourceStrArray.length; j++) {
                    int option_id = Integer.parseInt(sourceStrArray[j]);

                    //获取选项的具体信息

                    ps1 = conn.prepareStatement(sql1);
                    ps1.setInt(1, option_id);
                    ResultSet rs1 = ps1.executeQuery();

                    if (rs1.next()) {
                        ItemOption itemOption = new ItemOption();
                        itemOption.setId(option_id);
                        itemOption.setText(rs1.getString("text"));
                        itemOption.setPrice(rs1.getFloat("price"));

                        OptionType optionType = new OptionType();

                        optionType.setName(rs1.getString("name"));
                        optionType.setId(rs1.getInt("option_type_id"));
                        itemOption.setOptionType(optionType);
                        itemOptions.add(itemOption);
                    }
                    userCart1.setItemOption(itemOptions);
                }
            }
            return userCart1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
