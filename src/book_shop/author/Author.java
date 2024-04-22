package book_shop.author;

public class Author {
    private int id;
    private String authorName;
    private int bookId;

    public Author() {
    }

    public Author(int id, String authorName, int bookId) {
        super();
        this.id = id;
        this.authorName = authorName;
        this.bookId = bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "author{" + "id=" + id + ", authorName='" + authorName + '\'' + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }
}
