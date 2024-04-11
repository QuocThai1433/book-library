package book_shop.publisher;

public class Publisher {
    private int id;
    private String publisher_name;

    private  int  book_id;

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public Publisher() {
    }

    public Publisher(int id, String publisher_name,int book_id) {
        super();
        this.id = id;
        this.publisher_name = publisher_name;
        this.book_id= book_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public int getId() {
        return id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }
}
