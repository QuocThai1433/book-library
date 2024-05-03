package book_shop.cart;

public class Cart {
    private int bookId;
    private float price;
    private int quantity;

    public Cart(int bookId, float price, int quantity) {
        this.bookId = bookId;
        this.price = price;
        this.quantity = quantity;
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
