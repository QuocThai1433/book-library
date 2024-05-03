package book_shop.rating;

public class Rating {
    private int id;
    private float starRating;

    private int bookId;

    private String comment;
    private int readerId;

    public Rating(int id, float starRating, int bookId, String comment, int readerId) {
        super();
        this.id = id;
        this.starRating = starRating;
        this.bookId = bookId;
        this.comment = comment;
        this.readerId = readerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getStarRating() {
        return starRating;
    }

    public int getBookId() {
        return bookId;
    }

    public String getComment() {
        return comment;
    }

    public int getId() {
        return id;
    }

    public int getReaderId() {
        return readerId;
    }
}
