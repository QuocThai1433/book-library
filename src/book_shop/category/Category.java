package book_shop.category;

public class Category {
    private int id;
    private String categoryName;


    public Category() {
    }

    public Category(int id, String categorName) {
        super();

        this.id = id;
        this.categoryName = categorName;
    }

    public Category(String categorName) {
        this.categoryName = categorName;

    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCategory_name(String categorName) {
        this.categoryName = categorName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
