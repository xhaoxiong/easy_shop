package front.dao.imple;

import front.dao.CommonDao;
import front.dao.UserDao;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends CommonDao implements UserDao {

    //修改用户信息
    public boolean editUser(User user) {
        boolean r = false;


        String sql = "UPDATE user SET userpass=?,Role=?,regtime=?,lognum=? WHERE username=?";
        try {

            int num = this.executeUpdate(sql, user);
            if (num > 0) {
                r = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return r;

    }

    @Override
    public User getUserByUserId(int id) {
        User user = new User();
        String sql = "Select * from user where id =?";
        try {
            if (this.conn.isClosed()) {
                conn = openDatabase();
            }

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getInt("gender"));
                user.setNickName(rs.getString("nick_name"));
                user.setEmail(rs.getString("email"));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return null;
    }

    public boolean getUserByUserName(String username) {
        String sql = "Select * from user where user_name =?";
        try {
            if (this.conn.isClosed()) {
                conn = openDatabase();
            }

            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return false;
    }


    //验证用户名和密码
    @Override
    public User validateUser(User user) {
        User user1 = new User();
        String sql = "Select id,user_name,nick_name,gender,email,phone from user where user_name=? and password = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user1.setId(rs.getInt("id"));
                user1.setUserName(rs.getString("user_name"));
                user1.setNickName(rs.getString("nick_name"));
                user1.setGender(rs.getInt("gender"));
                user1.setEmail(rs.getString("email"));
                user1.setPhone(rs.getString("phone"));
                return user1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return null;
    }

    //添加用户
    @Override
    public boolean addUser(User user) {

        boolean r = false;
        String sql = "INSERT INTO user(user_name,password,nick_name,gender,email)VALUES(?,?,?,?,?)";
        try {
            if (this.conn.isClosed()) {
                conn = openDatabase();
            }
            int num = 0;
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getNickName());
                ps.setInt(4, user.getGender());
                ps.setString(5, user.getEmail());
                num = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (num == 1) {
                r = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return r;
    }


}
