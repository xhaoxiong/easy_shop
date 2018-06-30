package admin.dao;

import entity.Role;

import java.util.List;

public interface RoleDao {
    public void AddRole();

    public void DelRole();

    public void EditRole();

    public List<Role> ListRole();
}
