package entity;

import java.util.List;

public class Permission {
    private int Id;
    private String PermissionName;
    private int ParentId;
    private int Type;
    private List<Permission> SubPermission;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPermissionName() {
        return PermissionName;
    }

    public void setPermissionName(String permissionName) {
        PermissionName = permissionName;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public List<Permission> getSubPermission() {
        return SubPermission;
    }

    public void setSubPermission(List<Permission> subPermission) {
        SubPermission = subPermission;
    }
}
