package book_shop.publisher;

public class Publisher {
    private int id;
    private String publisherName;

    private  int  bookId;

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public Publisher() {
    }

    public Publisher(int id, String publisherName,int bookId) {
        super();
        this.id = id;
        this.publisherName = publisherName;
        this.bookId= bookId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisherName = publisherName;
    }

    public int getId() {
        return id;
    }

    public String getPublisher_name() {
        return publisherName;
    }
}
