package book_shop.author;

public class Author {
    private int id;
    private final String authorName;

    public Author(int id, String authorName) {
        super();
        this.id = id;
        this.authorName = authorName;

    }

    @Override
    public String toString() {
        return "author{" + "id=" + id + ", authorName='" + authorName + '\'' + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }
}
