package entity;

import java.util.Date;
import java.util.List;

public class UserCart {
    private int Id;
    private User User;
    private int UserId;
    private int ItemId;
    private Item Item;
    private Date Date;
    private String OptionMap;
    private int Counts;
    private List<ItemOption> itemOption;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<ItemOption> getItemOption() {
        return itemOption;
    }

    public void setItemOption(List<ItemOption> itemOption) {
        this.itemOption = itemOption;
    }


    public int getCounts() {
        return Counts;
    }

    public void setCounts(int counts) {
        Counts = counts;
    }

    public String getOptionMap() {
        return OptionMap;
    }

    public void setOptionMap(String optionMap) {
        OptionMap = optionMap;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public entity.Item getItem() {
        return Item;
    }

    public void setItem(entity.Item item) {
        Item = item;
    }


    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public entity.User getUser() {
        return User;
    }

    public void setUser(entity.User user) {
        User = user;
    }


    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}
