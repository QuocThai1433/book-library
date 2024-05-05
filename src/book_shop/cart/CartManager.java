package book_shop.cart;

import book_shop.ValidatorUtils;
import book_shop.ConnectDB;
import book_shop.InputHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    Connection connection = ConnectDB.getConnection();
    List<Cart> authors = new ArrayList<>();
    ValidatorUtils checkValid = new ValidatorUtils();
    InputHelper inputHelper = new InputHelper();

    public Cart input() {

        System.out.println("Input Book Id:");
        int id = inputHelper.input((ratingId) -> checkValid.checkExistId(ratingId, "carts"));
        System.out.println("Input  Price:");
        float price = inputHelper.inputNegativeFloat();
        System.out.println("Input  Quantity:");
        int quantity = inputHelper.inputNegativeInteger();
        reduce(quantity);
        return new Cart(id, price, quantity);
    }

    public float reduce(int id) {
        float kq = 0;
        String query = "select *" + " from books where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kq = rs.getFloat("quantity");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public void updateQuantity(int bookId, float quantity) {
        float update = reduce(bookId);
        String query = "update books set quantity = ? where id= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            update = (update - quantity);
            ps.setFloat(1, update);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int create() {
        int kq = 0;
        Cart cart = input();
        String query = " insert into carts value (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            if (checkValid.checkExistId(cart.getBookId(), "books")) {
                ps.setInt(1, cart.getBookId());
            } else {
                ps.setObject(1, null);
            }
            ps.setFloat(2, cart.getPrice());
            ps.setInt(3, cart.getQuantity());
            kq = ps.executeUpdate();
            updateQuantity(cart.getBookId(), cart.getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public List<Cart> getList() {
        String query = "select *" + " from carts";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                System.out.println(bookId + " | " + " | " + price + " | " + quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        CartManager cartManager = new CartManager();
        cartManager.create();
    }
}
