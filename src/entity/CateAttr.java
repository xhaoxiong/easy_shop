package entity;


//分类下的属性表
public class CateAttr {
    private int Id;
    private String name;
    private String Opts;
    private int CategoryId;
    private Category Category;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOpts() {
        return Opts;
    }

    public void setOpts(String opts) {
        Opts = opts;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public entity.Category getCategory() {
        return Category;
    }

    public void setCategory(entity.Category category) {
        Category = category;
    }
}
