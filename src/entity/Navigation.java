package entity;

import java.util.List;

public class Navigation {
    private int Id;
    private String Name;
    private String Url;
    private List<Item> Items;
    private String Cover;
    private int CateId;
    private Category Cate;

    public int getCateId() {
        return CateId;
    }

    public void setCateId(int cateId) {
        CateId = cateId;
    }

    public Category getCate() {
        return Cate;
    }

    public void setCate(Category cate) {
        Cate = cate;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String cover) {
        Cover = cover;
    }

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        Items = items;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
