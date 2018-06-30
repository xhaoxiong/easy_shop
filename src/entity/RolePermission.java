package entity;

import java.util.Date;

public class RolePermission {
    private int Id;
    private int RoleId;
    private Role Role;
    private Permission PermissionId;
    private Date CreateTime;
    private Date LastUpdateTime;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public entity.Role getRole() {
        return Role;
    }

    public void setRole(entity.Role role) {
        Role = role;
    }

    public Permission getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(Permission permissionId) {
        PermissionId = permissionId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getLastUpdateTime() {
        return LastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        LastUpdateTime = lastUpdateTime;
    }
}
