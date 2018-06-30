package entity;

import java.util.Date;
import java.util.List;

public class StoreRecord {
    private int Id;
    private String RecordNumber;//入库或者出库的单号
    private Date CreateTime; //入库或者出库单的创建时间
    private int Type; //判断是入库还是出库
    private AdminUser AdminUser;
    private int AdminUserId; //入库或者出库经手人
    private int Count;  //入库或者出库数量
    private Date UpdateTime; //入库或者出库单的更新时间
    private List<StoreRecordDetail> StoreRecordDetails;

    public List<StoreRecordDetail> getStoreRecordDetails() {
        return StoreRecordDetails;
    }

    public void setStoreRecordDetails(List<StoreRecordDetail> storeRecordDetails) {
        StoreRecordDetails = storeRecordDetails;
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

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
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

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }
}
