package entity;

//商品规格参数
public class ItemAttr {
    private int Id;
    private Item Item;
    private int ItemId;
    private CateAttr CateAttr;
    private int CateAttrId;
    private String Value;




    public int getCateAttrId() {
        return CateAttrId;
    }

    public void setCateAttrId(int cateAttrId) {
        CateAttrId = cateAttrId;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public entity.CateAttr getCateAttr() {
        return CateAttr;
    }

    public void setCateAttr(entity.CateAttr cateAttr) {
        CateAttr = cateAttr;
    }



    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
