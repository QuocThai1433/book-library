package book_shop.cart;

import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static constants.EntityConstant.BOOKS;
import static utils.InputUtils.checkExistIdFunc;
import static utils.InputUtils.inputId;

public class CartManager {
    Scanner scanner = new Scanner(System.in);
    
    public Cart input() {
        System.out.println("Input Book Id:");
        int bookId = inputId(checkExistIdFunc(BOOKS));
        
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
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kq = rs.getFloat("quantity");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
    }
    
    public void updateQuantity(int bookId, float quantity) {
        float update = reduce(bookId);
        String query = "update books set quantity = ? where id= ?";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            update = (update - quantity);
            ps.setFloat(1, update);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int create() {
        int kq = 0;
        Cart cart = input();
        String query = " insert into carts (book_id, price, quantity) value (?,?,?)";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            if (cart.getBookId() != 0) {
                ps.setInt(1, cart.getBookId());
            } else {
                ps.setObject(1, null);
                
            }
            ps.setFloat(2, cart.getPrice());
            ps.setInt(3, cart.getQuantity());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
    }
    
    public List<Cart> getList() {
        List<Cart> carts = new ArrayList<>();
        String query = "select * from carts";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return Collections.emptyList();
            }
            
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                System.out.println(bookId + " | " + " | " + price + " | " + quantity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return carts;
    }
    
    public static void main(String[] args) {
        CartManager cartManager = new CartManager();
        cartManager.create();
    }
}
