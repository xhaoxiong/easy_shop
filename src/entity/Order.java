package entity;

import java.util.Date;

public class Order {
    private int Id;
    private String OrderNumber;
    private float ItemAmount;
    private float ShippingAmount;
    private float OrderAmount;
    private int ShippingMethod;
    private int PaymentMethod;
    private int OrderStatus;
    private Date  CreateTime;
    private Date PaymentTime;
    private User User;
    private int UserId;


    public Order(int id, String orderNumber, float itemAmount, float shippingAmount, float orderAmount, int shippingMethod, int paymentMethod, int orderStatus, Date createTime, Date paymentTime, entity.User user, int userId) {
        Id = id;
        OrderNumber = orderNumber;
        ItemAmount = itemAmount;
        ShippingAmount = shippingAmount;
        OrderAmount = orderAmount;
        ShippingMethod = shippingMethod;
        PaymentMethod = paymentMethod;
        OrderStatus = orderStatus;
        CreateTime = createTime;
        PaymentTime = paymentTime;
        User = user;
        UserId = userId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public float getItemAmount() {
        return ItemAmount;
    }

    public void setItemAmount(float itemAmount) {
        ItemAmount = itemAmount;
    }

    public float getShippingAmount() {
        return ShippingAmount;
    }

    public void setShippingAmount(float shippingAmount) {
        ShippingAmount = shippingAmount;
    }

    public float getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        OrderAmount = orderAmount;
    }

    public int getShippingMethod() {
        return ShippingMethod;
    }

    public void setShippingMethod(int shippingMethod) {
        ShippingMethod = shippingMethod;
    }

    public int getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        OrderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getPaymentTime() {
        return PaymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        PaymentTime = paymentTime;
    }

    public entity.User getUser() {
        return User;
    }

    public void setUser(entity.User user) {
        User = user;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
