package book_shop.cart;

public class Cart {
    private int book_id;

    private float price;
    private int quantity;

    public Cart() {
    }

    public Cart(int book_id, float price, int quantity) {
        this.book_id = book_id;
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



    public int getBook_id() {
        return book_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }



}
