package entity;

public class Consignee {
    private int ConsigneeId;
    private int UserId;
    private String Address;
    private String Zip;
    private String Phone;
    private int IsDefault;

    public int getConsigneeId() {
        return ConsigneeId;
    }

    public void setConsigneeId(int consigneeId) {
        ConsigneeId = consigneeId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
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

    public int getIsDefault() {
        return IsDefault;
    }

    public void setIsDefault(int isDefault) {
        IsDefault = isDefault;
    }
}
