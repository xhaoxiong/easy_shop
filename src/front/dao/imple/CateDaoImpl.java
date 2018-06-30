package front.dao.imple;

import front.dao.CateDao;
import front.dao.CommonDao;
import entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CateDaoImpl extends CommonDao implements CateDao {


    @Override
    public List<Category> ListNodeByParentId(int parent_id) {
        String sql = "select * from category where parent_id=?";
        List<Category> list = new ArrayList<Category>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, parent_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setParentId(rs.getInt("parent_id"));
                category.setName(rs.getString("name"));
                List<Category> categories = ListNodeByParentId(rs.getInt("id"));

                category.setCategory(categories);
                list.add(category);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> ListParentNodeById(int id) {
        String sql = "select * from category where id=?";
        List<Category> list = new ArrayList<Category>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setParentId(rs.getInt("parent_id"));
                category.setName(rs.getString("name"));
                List<Category> categories = ListParentNodeById(rs.getInt("parent_id"));

                category.setCategory(categories);
                list.add(category);

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category CurrentCategory(int id) {
        String sql = "select * from category where id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                return category;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }

        return null;
    }


}
