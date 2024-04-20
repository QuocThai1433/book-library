package book_shop.cart;

import book_shop.CheckValid;
import book_shop.cart.Cart;
import student.ConnectDB;

import java.nio.charset.CharacterCodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartManager {
    Connection connection = ConnectDB.getConnection();
    Scanner scanner = new Scanner(System.in);
    List<Cart> authors = new ArrayList<>();

    CheckValid checkValid = new CheckValid();

    public Cart input() {
        String number = null;
        int bookId = 0;
        do {
            System.out.println("Input Book Id:");
            number = scanner.nextLine();
            if (checkValid.isNumber(number)) {
                bookId = Integer.parseInt(number);
            }else
            {
                System.out.println("Not Number!! Input Again:");
            }
        } while (!checkValid.isNumber(number));

        System.out.println("Input Book Name:");
        String bookName = scanner.nextLine();
        System.out.println("Input  Price:");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Input  Quantity:");
        int quantity = scanner.nextInt();
        updateQuantity(bookId, quantity);
        reduce(quantity);
        return new Cart(bookId, bookName, price, quantity);
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

    public int create(Cart cart) {
        int kq = 0;
        cart = input();
        String query = " insert into carts value (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            if (checkValid.checkExistId(cart.getBook_id(), "books")) {
                ps.setInt(1, cart.getBook_id());
            } else {
                ps.setObject(1, null);

            }
            ps.setString(2, cart.getBookName());
            ps.setFloat(3, cart.getPrice());
            ps.setInt(4, cart.getQuantity());
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
                String bookName = rs.getString("book_name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                System.out.println(bookId +" | " + bookName + " | "+price + " | "+quantity);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        CartManager cartManager = new CartManager();

        cartManager.getList();
    }
}
