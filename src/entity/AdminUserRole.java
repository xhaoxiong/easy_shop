package entity;

import java.util.Date;

public class AdminUserRole {
    private int Id;
    private AdminUser AdminUser;
    private int AdminUserId;
    private Role Role;
    private int RoleId;
    private Date CreateTime;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public entity.AdminUser getAdminUser() {
        return AdminUser;
    }

    public void setAdminUser(entity.AdminUser adminUser) {
        AdminUser = adminUser;
    }

    public int getAdminUserId() {
        return AdminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        AdminUserId = adminUserId;
    }

    public entity.Role getRole() {
        return Role;
    }

    public void setRole(entity.Role role) {
        Role = role;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }
}
