package front.dao;

import entity.*;

import java.util.List;

public interface ItemDao {
    public List<Item> ListItem(int pers, int page, int cate_id, String is_price, String is_volume, String search);

    public Item ItemDetail(int itemId);

    public ItemOption ItemOption(int optionTypeId);

    public List<OptionType> OptionType(int optionTypeId);

    public List<ItemAlbum> ItemAlbum(int itemId);

    public List<ItemAttr> ItemAttr(Item item);

    public int PageNum(int pers);

    public List<ItemOption> ListItemOption(List<ItemOption> itemOptions);

}
