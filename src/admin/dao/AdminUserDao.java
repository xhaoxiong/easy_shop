package admin.dao;

import entity.AdminUser;

import java.util.List;

public interface AdminUserDao {
    public Boolean AddAdminUser(AdminUser adminUser, String[] rolesStr);

    public Boolean DelAdminUser(AdminUser adminUser);

    public Boolean EditAdminUser(AdminUser adminUser);

    public List<AdminUser> ListAdmin(int per, int page, String search);

    public List<AdminUser> ListAdminNormal(AdminUser adminUser);

    public int PageNum(int pers);

    public AdminUser FindUserById(AdminUser user);

    public Boolean GrantAdminUserRole(int roleId, int adminUserId);
}
