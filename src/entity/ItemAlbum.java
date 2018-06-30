package entity;

//商品图册
public class ItemAlbum {
    private int Id;
    private String Image;
    private Item Item;
    private int ItemId;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public entity.Item getItem() {
        return Item;
    }

    public void setItem(entity.Item item) {
        Item = item;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }


}

