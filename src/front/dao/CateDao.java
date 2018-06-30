package front.dao;

import entity.Category;

import java.util.List;

public interface CateDao {


    public List<Category> ListNodeByParentId(int parent_id);

    public List<Category> ListParentNodeById(int id);

    public Category CurrentCategory(int id);
}
