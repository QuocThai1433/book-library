package book_shop.publisher;

public class Publisher {
    private int id;
    
    private String publisherName;
    
    private int bookId;
    
    public Publisher() {
    }
    
    public Publisher(int id, String publisherName, int bookId) {
        super();
        this.id = id;
        this.publisherName = publisherName;
        this.bookId = bookId;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getPublisherName() {
        return publisherName;
    }
    
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
