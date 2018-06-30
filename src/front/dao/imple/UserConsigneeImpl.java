package front.dao.imple;

import front.dao.CommonDao;
import front.dao.UserConsignee;
import entity.Consignee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserConsigneeImpl extends CommonDao implements UserConsignee {
    @Override
    public List<Consignee> FindUserConsigneeByUserId(int userId) {
        String sql = "select * from user_consignee where user_id=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            List<Consignee> consignees = new ArrayList<>();
            while (rs.next()) {
                Consignee consignee = new Consignee();
                consignee.setConsigneeId(rs.getInt("consignee_id"));
                consignee.setAddress(rs.getString("address"));
                consignee.setPhone(rs.getString("phone"));
                consignee.setZip(rs.getString("zip"));
                consignee.setIsDefault(rs.getInt("is_default"));
                consignees.add(consignee);
            }
            return consignees;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
