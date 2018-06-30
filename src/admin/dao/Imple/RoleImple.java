package admin.dao.Imple;

import front.dao.CommonDao;
import admin.dao.RoleDao;
import entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleImple extends CommonDao implements RoleDao {
    @Override
    public void AddRole() {

    }

    @Override
    public void DelRole() {

    }

    @Override
    public void EditRole() {

    }

    @Override
    public List<Role> ListRole() {
        String sql = "select * from role ";

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Role> roles = new ArrayList<>();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("role_name"));
                role.setRoleDesc(rs.getString("role_desc"));
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }


        return null;
    }
}
