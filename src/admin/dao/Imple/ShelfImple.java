package admin.dao.Imple;

import admin.dao.ShelfDao;
import entity.Shelf;
import front.dao.CommonDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelfImple extends CommonDao implements ShelfDao {
    @Override
    public List<Shelf> ListShelf() {
        String sql = "select * from shelf";
        try {
            List<Shelf> shelves = new ArrayList<>();
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Shelf shelf = new Shelf();
                shelf.setId(rs.getInt("id"));
                shelf.setName(rs.getString("name"));
                shelves.add(shelf);
            }
            return shelves;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }

        return null;
    }

    @Override
    public Boolean AddShelf() {
        return null;
    }

    @Override
    public Boolean EditShelf(int id) {
        return null;
    }

    @Override
    public Boolean DelShelf(int id) {
        return null;
    }
}
