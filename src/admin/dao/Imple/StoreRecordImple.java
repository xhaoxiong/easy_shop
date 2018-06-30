package admin.dao.Imple;

import admin.dao.StoreRecordDao;
import entity.AdminUser;
import entity.Item;
import entity.StoreRecord;
import entity.StoreRecordDetail;
import front.dao.CommonDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StoreRecordImple extends CommonDao implements StoreRecordDao {

    @Override
    public Boolean AddStoreRecord(StoreRecord storeRecord, StoreRecordDetail storeRecordDetail, int inCount) {
        String sql = "insert into store_record values(?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 0);
            ps.setString(2, storeRecord.getRecordNumber());
            ps.setInt(3, storeRecord.getType());
            ps.setInt(4, storeRecord.getAdminUserId());
            ps.setTimestamp(5, new Timestamp(storeRecord.getCreateTime().getTime()));
            ps.setTimestamp(6, new Timestamp(storeRecord.getUpdateTime().getTime()));

            int num = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (num > 0) {
                String sql1 = "insert into store_record_detail values(?,?,?,?,?,?,?)";
                ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, 0);
                ps1.setString(2, storeRecord.getRecordNumber());
                ps1.setInt(3, storeRecord.getAdminUserId());
                ps1.setInt(4, storeRecordDetail.getItemId());
                ps1.setInt(5, inCount);
                ps1.setTimestamp(6, new Timestamp(storeRecord.getCreateTime().getTime()));
                ps1.setTimestamp(7, new Timestamp(storeRecord.getUpdateTime().getTime()));

                int num1 = ps1.executeUpdate();
                if (num1 > 0) {
                    return true;
                }
                return false;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return false;
    }

    @Override
    public Boolean DelStoreRecord(int id) {
        String sql = "select * from store_record where id=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String record_number = rs.getString("record_number");

                String sql1 = "delete from store_record  where id=?";
                ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, id);
                int num1 = ps1.executeUpdate();


                String sql2 = "delete from store_record_detail where record_number=?";

                ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, record_number);
                int num2 = ps2.executeUpdate();

                if (num1 > 0 && num2 > 0) {
                    return true;
                }
                return false;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return false;
    }

    @Override
    public void EditStoreRecord() {

    }

    @Override
    public List<StoreRecord> ListStoreRecord(int pers, int page, String search, int type) {
        String sql2 = "select count(*) from store_record";
        String sql = "";
        int total = 0;
        try {
            ps2 = conn.prepareStatement(sql2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int pageNum = (int) Math.ceil(total / (double) pers);

        if (page > pageNum) {
            if (pageNum == 0) {
                pageNum = pageNum + 1;
            }
            page = pageNum;
        }
        int offset = (page - 1) * pers;
        if (search != null) {
            sql = "select *,admin_user.id as adminuser_id from store_record left join admin_user on store_record.admin_user_id=admin_user.id where  record_number LIKE ? ";

        } else if (type != 0) {
            sql = "select *,admin_user.id as adminuser_id from store_record left join admin_user on store_record.admin_user_id=admin_user.id  WHERE type =? limit ?,?";
        } else {
            sql = "select *,admin_user.id as adminuser_id from store_record left join admin_user on store_record.admin_user_id=admin_user.id  limit ?,?";
        }

        List<StoreRecord> storeRecords = new ArrayList<>();

        try {
            if (search != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
            } else if (type != 0) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, type);
                ps.setInt(2, offset);
                ps.setInt(3, pers);
                rs = ps.executeQuery();
            } else {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, offset);
                ps.setInt(2, pers);
                rs = ps.executeQuery();
            }

            while (rs.next()) {
                StoreRecord storeRecord = new StoreRecord();
                storeRecord.setId(rs.getInt("id"));
                storeRecord.setRecordNumber(rs.getString("record_number"));
                storeRecord.setType(rs.getInt("type"));
                storeRecord.setUpdateTime(new java.util.Date(rs.getTimestamp("update_time").getTime()));
                storeRecord.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));


                AdminUser adminUser = new AdminUser();
                adminUser.setId(rs.getInt("adminuser_id"));
                adminUser.setAdminUserName(rs.getString("admin_user_name"));

                storeRecord.setAdminUser(adminUser);
                storeRecord.setAdminUserId(rs.getInt("adminuser_id"));


                List<StoreRecordDetail> storeRecordDetails = new ArrayList<>();

                String sql1 = "select * from store_record_detail LEFT JOIN item on item.id=store_record_detail.item_id where record_number=?";


                ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, storeRecord.getRecordNumber());

                ResultSet rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    StoreRecordDetail storeRecordDetail = new StoreRecordDetail();

                    storeRecordDetail.setId(rs1.getInt("id"));
                    storeRecordDetail.setItemId(rs1.getInt("item_id"));
                    storeRecordDetail.setCount(rs1.getInt("count"));
                    storeRecordDetail.setUpdateTime(new java.util.Date(rs1.getTimestamp("update_time").getTime()));
                    storeRecordDetail.setCreateTime(new java.util.Date(rs1.getTimestamp("create_time").getTime()));
                    storeRecordDetail.setAdminUser(adminUser);
                    storeRecordDetail.setAdminUserId(adminUser.getId());

                    storeRecordDetails.add(storeRecordDetail);
                }
                storeRecords.add(storeRecord);
            }
            return storeRecords;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return null;
    }

    @Override
    public int PageNum(int pers) {
        String sql = "select count(*) from store_record";

        int total = 0;

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
                int pageNum = (int) Math.ceil(total / (double) pers);
                return pageNum;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return 0;
    }

    @Override
    public List<StoreRecordDetail> ListStoreRecordDetail(int id) {
        String sql = "select * from store_record_detail LEFT JOIN  item ON store_record_detail.item_id=item.id left JOIN admin_user ON admin_user.id=store_record_detail.admin_user_id where store_record_detail.id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<StoreRecordDetail> storeRecordDetails = new ArrayList<>();
            while (rs.next()) {
                StoreRecordDetail storeRecordDetail = new StoreRecordDetail();
                storeRecordDetail.setItemId(rs.getInt("item_id"));
                storeRecordDetail.setId(rs.getInt("id"));
                storeRecordDetail.setAdminUserId(rs.getInt("admin_user_id"));
                storeRecordDetail.setRecordNumber(rs.getString("record_number"));
                storeRecordDetail.setCount(rs.getInt("count"));
                storeRecordDetail.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
                storeRecordDetail.setUpdateTime(new java.util.Date(rs.getTimestamp("update_time").getTime()));

                AdminUser adminUser = new AdminUser();
                adminUser.setAdminUserName(rs.getString("admin_user_name"));
                adminUser.setId(rs.getInt("admin_user_id"));
                storeRecordDetail.setAdminUser(adminUser);

                Item item = new Item();
                item.setNumber(rs.getString("number"));
                item.setStockQty(rs.getInt("stock_qty"));
                item.setName(rs.getString("name"));

                storeRecordDetail.setItem(item);
                storeRecordDetails.add(storeRecordDetail);
            }
            return storeRecordDetails;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return null;
    }

}
