package admin.dao;

import entity.Shelf;

import java.util.List;

public interface ShelfDao {
    public List<Shelf> ListShelf();

    public Boolean AddShelf();

    public Boolean EditShelf(int id);

    public Boolean DelShelf(int id);
}
