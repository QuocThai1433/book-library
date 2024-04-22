package book_shop.cart;

import book_shop.CheckValid;
import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartManager {
    Connection connection = ConnectDB.getInstance().getConnection();
    Scanner scanner = new Scanner(System.in);
    List<Cart> authors = new ArrayList<>();

    CheckValid checkValid = new CheckValid();
    boolean flag = false;

    public int checkNotNumber(int number) {
        boolean flag = false;
        while (!flag) {
            String number1 = scanner.nextLine();
            if (!checkValid.isNumber(number1)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            number = Integer.parseInt(number1);
            flag = true;
        }
        return number;
    }

    public Cart input() {
        String number = null;
        int bookId = 0;
        System.out.println("Input Book Id:");
        bookId = checkNotNumber(bookId);
        System.out.println("Input  Price:");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Input  Quantity:");
        int quantity = scanner.nextInt();
        updateQuantity(bookId, quantity);
        reduce(quantity);
        return new Cart(bookId, price, quantity);
    }


    public float reduce(int id) {
        float kq = 0;
        String query = "select * from books where id = ?";
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
            if (checkValid.checkExistId(cart.getBook_id(), "books")) {
                ps.setInt(1, cart.getBook_id());
            } else {
                ps.setObject(1, null);

            }
            ps.setFloat(2, cart.getPrice());
            ps.setInt(3, cart.getQuantity());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public List<Cart> getList() {
        String query = "select * from carts";
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
