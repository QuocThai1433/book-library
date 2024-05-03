package book_shop.publisher;

public class Publisher {
    private int id;
    private String publisherName;
    private int bookId;

    public int getBookId() {
        return bookId;
    }

    public Publisher(int id, String publisherName, int bookId) {
        super();
        this.id = id;
        this.publisherName = publisherName;
        this.bookId = bookId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPublisherName() {
        return publisherName;
    }
}
