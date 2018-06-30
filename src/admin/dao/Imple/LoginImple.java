package admin.dao.Imple;

import admin.dao.LoginDao;
import front.dao.CommonDao;
import entity.AdminUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LoginImple extends CommonDao implements LoginDao {

    @Override
    public AdminUser ValidateAdminUser(AdminUser user) {
        String sql = "select * from admin_user where admin_user_name=? and admin_user_password=?";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getAdminUserName());
            ps.setString(2, user.getAdminPassword());

            ResultSet rs = ps.executeQuery();
            AdminUser adminUser = new AdminUser();
            if (rs.next()) {
                adminUser.setId(rs.getInt("id"));
                adminUser.setEmail(rs.getString("email"));
                adminUser.setLastLoginTime(new java.util.Date(rs.getDate("last_login_time").getTime()));
                adminUser.setLastLoginIp(rs.getString("last_login_ip"));
                adminUser.setAdminUserName(rs.getString("admin_user_name"));
                adminUser.setCreateTime(new java.util.Date(rs.getDate("create_time").getTime()));
            }
            return adminUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AdminUser GetAdminUserByUserName(AdminUser user) {
        String sql = "select * from admin_user where admin_user_name=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getAdminUserName());

            ResultSet rs = ps.executeQuery();
            AdminUser adminUser = new AdminUser();
            if (rs.next()) {

                adminUser.setId(rs.getInt("id"));
                adminUser.setEmail(rs.getString("email"));
                adminUser.setLastLoginTime(rs.getDate("last_login_time"));
                adminUser.setLastLoginIp(rs.getString("last_login_ip"));
                adminUser.setAdminUserName(rs.getString("admin_user_name"));
                adminUser.setCreateTime(rs.getDate("create_time"));
            }
            return adminUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean UpdateAdminLoginTime(AdminUser adminUser) {
        String sql = "update admin_user set last_login_time =? WHERE  admin_user_name=?";


        try {
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(adminUser.getLastLoginTime().getTime()));
            ps.setString(2, adminUser.getAdminUserName());
            int num = ps.executeUpdate();

            if (num > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
