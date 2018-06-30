package front.dao;

import entity.Consignee;

import java.util.List;

public interface UserConsignee {

    public List<Consignee> FindUserConsigneeByUserId(int userId);
}
