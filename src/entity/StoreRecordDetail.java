package entity;

import java.util.Date;

public class StoreRecordDetail {
    private int Id;
    private String RecordNumber;
    private AdminUser AdminUser;
    private int AdminUserId;

    public int getAdminUserId() {
        return AdminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        AdminUserId = adminUserId;
    }

    private Item Item;
    private int ItemId;
    private int Count;
    private Date CreateTime;
    private Date UpdateTime;


    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRecordNumber() {
        return RecordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        RecordNumber = recordNumber;
    }

    public entity.AdminUser getAdminUser() {
        return AdminUser;
    }

    public void setAdminUser(entity.AdminUser adminUser) {
        AdminUser = adminUser;
    }

    public entity.Item getItem() {
        return Item;
    }

    public void setItem(entity.Item item) {
        Item = item;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
