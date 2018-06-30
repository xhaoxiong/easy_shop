package entity;

public class OrderConsignee {
    private int Id;
    private String OrderNumber;
    private String Receiver;
    private String Province;
    private String City;
    private String Borough;
    private String Address;
    private String Zip;
    private String Phone;

    public OrderConsignee(int id, String orderNumber, String receiver, String province, String city, String borough, String address, String zip, String phone) {
        Id = id;
        OrderNumber = orderNumber;
        Receiver = receiver;
        Province = province;
        City = city;
        Borough = borough;
        Address = address;
        Zip = zip;
        Phone = phone;
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

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getBorough() {
        return Borough;
    }

    public void setBorough(String borough) {
        Borough = borough;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
