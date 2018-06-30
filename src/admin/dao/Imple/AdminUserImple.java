package admin.dao.Imple;

import admin.dao.AdminUserDao;
import entity.AdminUser;
import front.dao.CommonDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminUserImple extends CommonDao implements AdminUserDao {

    @Override
    public Boolean AddAdminUser(AdminUser adminUser, String[] rolesStr) {
        String sql = "insert into admin_user values(?,?,?,?,?,?,?)";


        try {
            Timestamp timestamp = new Timestamp(new java.sql.Date(adminUser.getLastLoginTime().getTime()).getTime());
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 0);
            ps.setString(2, adminUser.getAdminUserName());
            ps.setString(3, adminUser.getAdminPassword());
            ps.setString(4, adminUser.getEmail());
            ps.setString(5, adminUser.getLastLoginIp());
            ps.setTimestamp(6, new Timestamp((adminUser.getLastLoginTime().getTime())));
            ps.setTimestamp(7, new Timestamp((adminUser.getCreateTime().getTime())));

            int num = ps.executeUpdate();
            System.out.println(num);
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                long id = rs.getLong(1);
                for (String roleStr : rolesStr) {
                    int roleId = Integer.parseInt(roleStr);
                    if (!GrantAdminUserRole(roleId, (int) id)) {
                        return false;
                    }
                }
            }
            if (num > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return false;
    }

    @Override
    public Boolean DelAdminUser(AdminUser adminUser) {
        String sql = "delete from admin_user where id =?";
        int num;
        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, adminUser.getId());

            num = ps.executeUpdate();
            if (num > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return false;
    }

    @Override
    public Boolean EditAdminUser(AdminUser adminUser) {
        String sql = "update admin_user set admin_user_password=?,email=?,last_login_ip=?,last_login_time=? where id =?";
        int num;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, adminUser.getAdminPassword());
            ps.setString(2, adminUser.getEmail());
            ps.setString(3, adminUser.getLastLoginIp());
            ps.setTimestamp(4, new Timestamp((adminUser.getLastLoginTime().getTime())));
            ps.setInt(5, adminUser.getId());
            num = ps.executeUpdate();

            if (num > 0) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public List<AdminUser> ListAdmin(int pers, int page, String search) {
        int total = 0;
        String sql1 = "select COUNT(*) from admin_user ;";
        String sql = "";
        try {
            ps1 = conn.prepareStatement(sql1);

            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                total = rs1.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (search != null) {
            sql = "select* from admin_user  where admin_user.admin_user_name like ?";
        } else {
            sql = "select * from admin_user order by id DESC limit ?,?";
        }


        List<AdminUser> adminUsers = new ArrayList<>();
        int pageNum = (int) Math.ceil(total / (double) pers);
        if (page > pageNum) {
            page = pageNum;
        }
        int offset = (page - 1) * pers;
        try {
            if (search != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
            } else {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, offset);
                ps.setInt(2, pers);
                rs = ps.executeQuery();
            }


            while (rs.next()) {
                AdminUser adminUser = new AdminUser();
                adminUser.setId(rs.getInt("id"));
                adminUser.setAdminUserName(rs.getString("admin_user_name"));
                adminUser.setEmail(rs.getString("email"));
                adminUser.setLastLoginIp(rs.getString("last_login_ip"));
                adminUser.setLastLoginTime(new java.util.Date(rs.getTimestamp("last_login_time").getTime()));
                adminUsers.add(adminUser);
            }

            return adminUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return null;
    }

    @Override
    public List<AdminUser> ListAdminNormal(AdminUser adminUser) {
        String sql = "select * from admin_user where admin_user_name =?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, adminUser.getAdminUserName());
            ResultSet rs = ps.executeQuery();
            List<AdminUser> adminUsers = new ArrayList<>();
            while (rs.next()) {
                AdminUser adminUserr = new AdminUser();
                adminUserr.setId(rs.getInt("id"));
                adminUserr.setAdminUserName(rs.getString("admin_user_name"));
                adminUserr.setEmail(rs.getString("email"));
                adminUserr.setLastLoginIp(rs.getString("last_login_ip"));
                adminUserr.setLastLoginTime(new java.util.Date(rs.getTimestamp("last_login_time").getTime()));
                adminUsers.add(adminUserr);
            }
            return adminUsers;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return null;
    }


    @Override
    public int PageNum(int pers) {
        String sql = "select count(*) from admin_user";

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
    public AdminUser FindUserById(AdminUser user) {
        String sql = "select *from admin_user where id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            AdminUser adminUser = new AdminUser();

            if (rs.next()) {

                adminUser.setId(rs.getInt("id"));
                adminUser.setAdminUserName(rs.getString("admin_user_name"));
                adminUser.setEmail(rs.getString("email"));
                adminUser.setLastLoginIp(rs.getString("last_login_ip"));
                adminUser.setLastLoginTime(new java.util.Date(rs.getTimestamp("last_login_time").getTime()));
            }
            return adminUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean GrantAdminUserRole(int roleId, int adminUserId) {
        String sql = "insert into admin_user_role values(?,?,?,?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, adminUserId);
            ps.setInt(3, roleId);
            ps.setTimestamp(4, new Timestamp(new Date().getTime()));

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
