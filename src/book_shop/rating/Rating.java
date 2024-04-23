package book_shop.rating;

public class Rating {
    private int id;
    private int starRating;

    private int bookId;

    private String comment;
    private int readerId;

    public Rating() {
    }


    public Rating(int id, int starRating) {
        super();
        this.id = id;
        this.starRating = starRating;
    }

    public Rating(int id, int starRating, int bookId, String comment, int readerId) {
        super();
        this.id = id;
        this.starRating = starRating;
        this.bookId = bookId;
        this.comment = comment;
        this.readerId = readerId;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getStarRating() {
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
