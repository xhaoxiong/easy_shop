package admin.dao;

import entity.StoreRecord;
import entity.StoreRecordDetail;

import java.util.List;

public interface StoreRecordDao {
    public Boolean AddStoreRecord(StoreRecord storeRecord, StoreRecordDetail storeRecordDetail, int inCount);

    public Boolean DelStoreRecord(int id);

    public void EditStoreRecord();

    public List<StoreRecord> ListStoreRecord(int per, int page, String search, int type);

    public int PageNum(int pers);

    public List<StoreRecordDetail> ListStoreRecordDetail(int id);
}
