package book_shop.category;

public class Category {
    private int id;
    private String categoryName;

    public Category(int id, String categorName ) {
        super();

        this.id = id;
        this.categoryName = categorName;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
