package book_shop.category;

public class Category {
    private int id;
    private String categoryName;
    private int bookId;

    public Category ()
    {}
    public Category(int id, String categorName, int bookId) {
        super();

        this.id = id;
        this.categoryName = categorName;
        this.bookId =Category.this.bookId;
    }
    public Category(String categorName)
    {
        this.categoryName=categorName;

    }

    public void setBook_id(int bookId) {
        this.bookId = Category.this.bookId;
    }

    public int getBook_id() {
        return bookId;
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
