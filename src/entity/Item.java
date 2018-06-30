package entity;

public class Item {
    private int Id;
    private String Name; //商品名
    private float NowPrice;//现价
    private float OriginPrice;//原价
    private String Number;//商品编号
    private String Cover;//商品大图
    private int IsIndexBig;
    private int StockQty;//库存
    private String Content;//
    private float Weight;//商品重量
    private int CateId;//分类id
    private Category Cate;
    private Category RootCate;
    private int RootCateId;
    private Brand Brand;
    private int BranId;
    private int NavigationId;
    private Navigation navigation;
    private int ShelfId;
    private Shelf shelf;

    public int getShelfId() {
        return ShelfId;
    }

    public void setShelfId(int shelfId) {
        ShelfId = shelfId;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Category getRootCate() {
        return RootCate;
    }

    public void setRootCate(Category rootCate) {
        RootCate = rootCate;
    }

    public int getRootCateId() {
        return RootCateId;
    }

    public void setRootCateId(int rootCateId) {
        RootCateId = rootCateId;
    }

    public int getNavigationId() {
        return NavigationId;
    }

    public void setNavigationId(int navigationId) {
        NavigationId = navigationId;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

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

    public entity.Brand getBrand() {
        return Brand;
    }

    public void setBrand(entity.Brand brand) {
        Brand = brand;
    }

    public int getBranId() {
        return BranId;
    }

    public void setBranId(int branId) {
        BranId = branId;
    }

    public int getIsIndexBig() {
        return IsIndexBig;
    }

    public void setIsIndexBig(int isIndexBig) {
        IsIndexBig = isIndexBig;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String cover) {
        Cover = cover;
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

    public float getNowPrice() {
        return NowPrice;
    }

    public void setNowPrice(float nowPrice) {
        NowPrice = nowPrice;
    }

    public float getOriginPrice() {
        return OriginPrice;
    }

    public void setOriginPrice(float originPrice) {
        OriginPrice = originPrice;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }


    public int getStockQty() {
        return StockQty;
    }

    public void setStockQty(int stockQty) {
        StockQty = stockQty;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }


}
