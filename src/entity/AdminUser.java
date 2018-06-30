package entity;

import java.util.Date;

public class AdminUser {
    private int Id;
    private String AdminUserName;
    private String AdminPassword;
    private String Email;
    private String LastLoginIp;
    private Date LastLoginTime;
    private Date CreateTime;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAdminUserName() {
        return AdminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        AdminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return AdminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        AdminPassword = adminPassword;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLastLoginIp() {
        return LastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        LastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        LastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }
}
