package book_shop.cart;

public class Cart {
    private int bookId;

    private float price;
    private int quantity;

    public Cart() {
    }

    public Cart(int bookId, float price, int quantity) {
        this.bookId = bookId;
        this.price = price;
        this.quantity = quantity;
    }

    public void setBookId(int book_id) {
        this.bookId = book_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public int getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }


}
