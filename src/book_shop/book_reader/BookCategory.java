package book_shop.book_reader;

public class BookCategory {
    private int bookId;
    private String nameBook;
    private int categoryId;
    private String nameCategory;
    public BookCategory ()
    {

    }
    public BookCategory(int bookId ,String nameBook,int categoryId, String nameCategory)
    {
        super();
        this.bookId =bookId;
        this.nameBook= nameBook;
        this.categoryId = categoryId;
        this.nameCategory= nameCategory;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameBook() {
        return nameBook;
    }

    public int getBookId() {
        return bookId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getNameCategory() {
        return nameCategory;
    }
}
