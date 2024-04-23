package book_shop.rating;

public class RatingReader {
    private String nameReader;
    private   int starRating;

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getNameReader() {
        return nameReader;
    }

    public int getStarRating() {
        return starRating;
    }
}
