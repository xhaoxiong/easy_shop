package entity;

public class ItemOption {
    private int Id;
    private String Text;
    private float Price;
    private Item Item;
    private OptionType OptionType;
    private int ItemId;
    private int OptionTypeId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public entity.Item getItem() {
        return Item;
    }

    public void setItem(entity.Item item) {
        Item = item;
    }

    public entity.OptionType getOptionType() {
        return OptionType;
    }

    public void setOptionType(entity.OptionType optionType) {
        OptionType = optionType;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public int getOptionTypeId() {
        return OptionTypeId;
    }

    public void setOptionTypeId(int optionTypeId) {
        OptionTypeId = optionTypeId;
    }
}
