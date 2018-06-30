package entity;


public class OrderItem {
    private int Id;
    private String ItemName;
    private String Image;
    private int ItemQty;
    private float ItemPrice;
    private String Review;
    private Item Item;
    private String OrderNumber;

    public OrderItem(int id, String itemName, String image, int itemQty, float itemPrice, String review, entity.Item item, String orderNumber) {
        Id = id;
        ItemName = itemName;
        Image = image;
        ItemQty = itemQty;
        ItemPrice = itemPrice;
        Review = review;
        Item = item;
        OrderNumber = orderNumber;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getItemQty() {
        return ItemQty;
    }

    public void setItemQty(int itemQty) {
        ItemQty = itemQty;
    }

    public float getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(float itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public entity.Item getItem() {
        return Item;
    }

    public void setItem(entity.Item item) {
        Item = item;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }
}
