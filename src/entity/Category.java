package entity;

import java.util.List;

public class Category {


    private int Id;
    private String Name;
    private int ParentId;
    private String Description;
    private int Seq;
    private List<Category> Category;


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

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getSeq() {
        return Seq;
    }

    public void setSeq(int seq) {
        Seq = seq;
    }

    public List<entity.Category> getCategory() {
        return Category;
    }

    public void setCategory(List<entity.Category> category) {
        Category = category;
    }
}
