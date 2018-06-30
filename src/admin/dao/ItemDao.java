package admin.dao;

import entity.Item;

import java.util.List;

public interface ItemDao {
    public int AddItem(Item item);

    public List<Item> ListItem(int per, int page, String search, int ShelfId);

    public Boolean DelItem();

    public Boolean AddItemQty(Item item);

    public Boolean CutItemQty(Item item);

    public int PageNum(int pers);
}
