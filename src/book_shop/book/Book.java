package book_shop.book;

import java.io.Serializable;

public class Book implements Serializable {
    int id;
    String bookName;
    int publication_year;
    int quantity;
    float price;
    float ratingAverage;

    public Book() {
    }
    public  Book(int id, String bookName, int publication_year,int quantity, float price, float ratingAverage)
    {

        super();
        this.id=id;
        this.bookName = bookName;
        this.publication_year=publication_year;
        this.quantity = quantity;
        this.price= price;
        this.ratingAverage = ratingAverage;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameBook(String bookName) {
        this.bookName = bookName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRatingAverage(float ratingAverage) {
        this.ratingAverage = ratingAverage;
    }


    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public float getRatingAverage() {
        return ratingAverage;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public int getQuantity() {
        return quantity;
    }



    public String getBookName() {
        return bookName;
    }
}
