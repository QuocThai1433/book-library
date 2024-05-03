package book_shop.book;

import java.io.Serializable;

public class Book implements Serializable {
    int id;
    String bookName;
    int publicationYear;
    int quantity;
    float price;
    float ratingAverage;
    int categoryId;

    int authorId;

    public Book() {
    }



    public Book id(int id) {
        this.id = id;
        return this;
    }

    public Book bookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public Book(int id, String bookName, int publicationYear, int quantity) {
        super();
        this.id = id;
        this.bookName = bookName;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
    }

    public int getAuthorId() {
        return authorId;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getQuantity() {
        return quantity;
    }


    public String getBookName() {
        return bookName;
    }


    public Book publicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    public Book quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Book price(float price) {
        this.price = price;
        return this;
    }

    public Book ratingAverage(float ratingAverage) {
        this.ratingAverage = ratingAverage;
        return this;
    }

    public Book authorId(int authorId) {
        this.authorId = authorId;
        return this;
    }

    public Book categoryId(int categoryId) {
        this.categoryId=categoryId;
        return this;
    }
}
