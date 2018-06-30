package admin.dao.Imple;

import admin.dao.PermissionDao;
import entity.AdminUser;
import front.dao.CommonDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PermissionImple extends CommonDao implements PermissionDao {

    @Override
    public Boolean ValidatePermission(AdminUser adminUser, String Menu, String Uri) {
        String sql = "select * from admin_user_role LEFT JOIN role_permission ON role_permission.role_id=admin_user_role.role_id  LEFT JOIN  permission ON role_permission.permission_id=permission.id  where admin_user_id=?";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, adminUser.getId());

            ResultSet rs = ps.executeQuery();


            //普通用户鉴权需要传入当前模块的名称（用于界面的鉴权）

            //对于url的鉴权，应进行url匹配
            while (rs.next()) {
                System.out.println(rs.getString("uri"));
                if (rs.getString("uri").equals("/*")) {
                    return true;
                } else if (rs.getString("uri").equals(Uri)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean IdentityPermission(AdminUser adminUser) {
        String sql = "select * from admin_user_role LEFT JOIN role ON admin_user_role.role_id=role.id where admin_user_id=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, adminUser.getId());
            ResultSet rs = ps.executeQuery();


            //普通用户鉴权需要传入当前模块的名称（用于界面的鉴权）

            //对于url的鉴权，应进行url匹配
            while (rs.next()) {
                if (rs.getString("role_name").equals("root")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
