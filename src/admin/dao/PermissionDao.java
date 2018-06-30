package admin.dao;

import entity.AdminUser;

public interface PermissionDao {
    public Boolean ValidatePermission(AdminUser adminUser, String menu, String Uri);

    public Boolean IdentityPermission(AdminUser adminUser);
}
