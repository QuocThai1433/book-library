package book_shop.rating;

public class Rating {
    private int id;
    private int star_rating;

    private  int book_id;

    private String comment;

    public Rating() {
    }


    public Rating(int id, int star_rating) {
        super();
        this.id = id;
        this.star_rating = star_rating;
    }

    public Rating(int id, int starRating, int bookId, String comment) {
        super();
        this.id = id;
        this.star_rating =starRating;
        this.book_id = bookId;
        this.comment=comment;
    }

    public void setBook_id(int id) {

        this.book_id =id;

    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBook_id() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStar_rating(int star_rating) {
        this.star_rating = star_rating;
    }

    public int getId() {
        return id;
    }

    public int getStar_rating() {
        return star_rating;
    }
}
