package book_shop;

import book_shop.book.Book;
import book_shop.book.BookManager;
import book_shop.rating.Rating;
import book_shop.rating.RatingManager;
import book_shop.rating.RatingReader;

public class main {
    public static void main(String[] args) {
        Rating rating= new Rating();
        RatingManager ratingReader = new RatingManager();
            ratingReader.create(rating);
    }
}
