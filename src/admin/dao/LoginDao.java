package admin.dao;

import entity.AdminUser;

public interface LoginDao {
    public AdminUser ValidateAdminUser(AdminUser user);

    public AdminUser GetAdminUserByUserName(AdminUser user);


    public Boolean UpdateAdminLoginTime(AdminUser adminUser);
}
