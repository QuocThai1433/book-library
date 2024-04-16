package book_shop.cart;

public class Cart {
    private int book_id;
    private String bookName;
    private float price;
    private int quantity;

    public Cart() {
    }

    public Cart(int book_id, String bookName, float price, int quantity) {
        this.book_id = book_id;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
    }

    public void setBookId(int book_id) {
        this.book_id = book_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "book_id=" + book_id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
