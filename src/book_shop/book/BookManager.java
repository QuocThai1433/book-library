package book_shop.book;


import db.ConnectDB;
import utils.InputUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static constants.EntityConstant.BOOKS;
import static constants.EntityConstant.CATEGORY;
import static utils.InputUtils.checkExistIdFunc;
import static utils.InputUtils.inputId;

public class BookManager {
    Scanner scanner = new Scanner(System.in);
    
    public Book inputUpdate() {
        System.out.println("Input Id Name:");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Input Book Name:");
        String bookName = scanner.nextLine();
        
        System.out.println("Input Publication Year:");
        int publicationYear = InputUtils.inputInteger();
        
        System.out.println("Input quantity:");
        int quantity = scanner.nextInt();
        
        System.out.println("Input Price:");
        float price = scanner.nextFloat();
        System.out.println("Input Rating Average:");
        float ratingAverage = scanner.nextFloat();
        scanner.nextLine();
        
        System.out.println("Input category Id:");
        int categoryId = inputId(checkExistIdFunc(CATEGORY));
        
        return new Book(id, bookName, publicationYear, quantity, price, ratingAverage, categoryId);
    }
    
    public Book inputBook() {
        System.out.println("Input Book ID:");
        int id = inputId(checkExistIdFunc(BOOKS));
        
        System.out.println("Input Book Name:");
        String bookName = scanner.nextLine();
        
        System.out.println("Input Publication Year:");
        int publicationYear = InputUtils.inputInteger();
        
        System.out.println("Input quantity:");
        int quantity = scanner.nextInt();
        
        System.out.println("Input Price:");
        float price = scanner.nextFloat();
        
        System.out.println("Input Rating Average:");
        float ratingAverage = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Input category Id:");
        int categoryId = inputId(checkExistIdFunc(CATEGORY));
        
        return new Book(id, bookName, publicationYear, quantity, price, ratingAverage, categoryId);
    }
    
    
    public float getAverage(float id) {
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
                kq = rs.getFloat("rating_average");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
        
    }
    
    public void updateStar(float newRating, float bookId) {
        float ratingAverage = getAverage(bookId);
        ratingAverage = (ratingAverage + newRating) / 2;
        String query = "update books set rating_average = ? where id = ? ";
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            if (con == null) {
                return;
            }
            PreparedStatement ps = con.prepareStatement(query);
            ps.setFloat(2, bookId);
            ps.setFloat(1, ratingAverage);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public int create() {
        Book book = inputBook();
        String query = """
            insert into books (id, book_name, publication_year, quantity, price, rating_average, category_id)
            value (?, ?, ?, ?, ?, ?, ?)
            """;
        int kq = 0;
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getBookName());
            statement.setInt(3, book.getPublicationYear());
            statement.setInt(4, book.getQuantity());
            statement.setFloat(5, book.getPrice());
            statement.setFloat(6, book.getRatingAverage());
            
            if (book.getCategoryId() != 0) {
                statement.setInt(7, book.getCategoryId());
            } else {
                statement.setObject(7, null);
            }
            
            kq = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return kq;
        
    }
    
    public int update() {
        String query = "UPDATE books  SET book_name = ?, publication_year = ?, quantity = ?,price = ?,rating_average = ?,category_id = ? WHERE id = ?";
        Book book = inputUpdate();
        int kq = 0;
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(7, book.getId());
            ps.setString(1, book.getBookName());
            ps.setInt(2, book.getPublicationYear());
            ps.setInt(3, book.getQuantity());
            ps.setFloat(4, book.getPrice());
            ps.setFloat(5, book.getRatingAverage());
            
            if (book.getCategoryId() != 0) {
                ps.setInt(6, book.getCategoryId());
            } else {
                ps.setObject(6, null);
            }
            kq = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
    }
    
    public void showBook(Book book) {
        String builder = book.getId() +
            " | " +
            book.getBookName() +
            " | " +
            book.getPublicationYear() +
            " | " +
            book.getQuantity() +
            " | " +
            book.getPrice() +
            " | " +
            book.getRatingAverage() +
            " | " +
            book.getCategoryId();
        System.out.println(builder);
    }
    
    public void showBooks(List<Book> books) {
        books.forEach(this::showBook);
    }
    
    public List<Book> getListBook() {
        List<Book> bookList = new ArrayList<>();
        String query = "select * from books";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return Collections.emptyList();
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = getBook(rs);
                bookList.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        showBooks(bookList);
        return bookList;
    }
    
    public void filter() {
        String query = """
                        select a.author_name,b.book_name,c.category_name from author a
                        right join books b
                        on a.book_id = b.id
                        right join category c
                        on b.category_id =c.id
                        where c.category_name like ?\s
                        OR b.book_name like ?
                        or a.author_name like ?
            """;
        
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("Input Category Name: ");
            String characters = scanner.nextLine();
            ps.setString(1, "%" + characters + "%");
            ps.setString(2, "%" + characters + "%");
            ps.setString(3, "%" + characters + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String bookName = rs.getString("author_name");
                String publicationYear = rs.getString("book_name");
                String categoryName = rs.getString("category_name");
                System.out.println(bookName + " | " + publicationYear + " | " + categoryName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Book getBook(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String bookName = rs.getString("book_name");
        int publicationYear = rs.getInt("publication_year");
        int quantity = rs.getInt("quantity");
        int price = rs.getInt("price");
        float ratingAverage = rs.getFloat("rating_average");
        int categoryId = rs.getInt("category_id");
        return new Book(id, bookName, publicationYear, quantity, price, ratingAverage, categoryId);
    }
    
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.filter();
    }
}

