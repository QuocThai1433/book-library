package book_shop.book;


import book_shop.CheckValid;
import book_shop.ConnectDB;
import book_shop.Input;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    Scanner scanner = new Scanner(System.in);
    CheckValid check = new CheckValid();
    Connection connection = ConnectDB.getConnection();
    Input input = new Input();

    public Book inputBook() {
        System.out.println("Input Book ID:");
        int id = input.input((authorId) -> check.checkExistId(authorId, "books"));
        System.out.println("Input Book Name:");
        String bookName = scanner.nextLine();
        System.out.println("Input Publication Year:");
        int publicationYear = input.inputNumber();
        System.out.println("Input quantity:");
        int quantity = input.inputNumber();
        System.out.println("Input Price:");
        float price = input.inputNumberFloat();
        System.out.println("Input Rating Average:");
        float ratingAverage = input.inputNumberFloat();
        System.out.println("Input category Id:");
        int categoryId = input.inputNumber();
        System.out.println("Input quantity:");
        int authorId = scanner.nextInt();
      return new Book()
                .id(id)
                .bookName(bookName)
                .publicationYear(publicationYear)
                .quantity(quantity)
                .price(price)
                .ratingAverage(ratingAverage)
                .categoryId(categoryId)
                .authorId(authorId);

    }

    public float getAverage(float id) {
        float kq = 0;
        String query = "SELECT *" +
                " from books where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kq = rs.getFloat("rating_average");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;

    }

    public void updateStar(float newRating, float bookId) {
        float ratingAverage = getAverage(bookId);
        String query = "update books set rating_average = ? where id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ratingAverage = (ratingAverage + newRating) / 2;
            ps.setFloat(2, bookId);
            ps.setFloat(1, ratingAverage);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int create() {
        Book book = inputBook();
        String query = "insert into books value  (?,?,?,?,?,?,?,?)";
        int kq = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getBookName());
            statement.setInt(3, book.getPublicationYear());
            statement.setInt(4, book.getQuantity());
            statement.setFloat(5, book.getPrice());
            statement.setFloat(6, book.getRatingAverage());
            if (check.checkExistId(book.getCategoryId(), "category")) {
                statement.setInt(7, book.getCategoryId());

            } else {
                statement.setObject(7, null);

            }
            if (check.checkExistId(book.getCategoryId(), "category")) {
                statement.setInt(8, book.getAuthorId());

            } else {
                statement.setObject(8, null);

            }
            kq = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;

    }
    public Book getDataBook(ResultSet rs) {
        try {

            int id = rs.getInt("id");
            String bookName = rs.getString("book_name");
            int publicationYear = rs.getInt("publication_year");
            int quantity = rs.getInt("quantity");
            int price = rs.getInt("price");
            float ratingAverage = rs.getFloat("rating_average");
            int categoryId = rs.getInt("category_id");
            int authorId = rs.getInt("author_id");
            return new Book()
                    .id(id)
                    .bookName(bookName)
                    .publicationYear(publicationYear)
                    .quantity(quantity)
                    .price(price)
                    .ratingAverage(ratingAverage)
                    .categoryId(categoryId)
                    .authorId(authorId);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public List<Book> getListBook() {
        List<Book> bookList = new ArrayList<>();
        String query = "select * " +
                "from books";
        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = getDataBook(rs);
                System.out.println(book.getId() + " | " + book.getBookName() + " | " + book.getPublicationYear() + " | " + book.quantity + " | " + book.price + " | " + book.ratingAverage + " | " + book.categoryId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookList;
    }

    public void filter() {
        String query = """ 
                SELECT b.*, a.author_name,b.book_name,c.category_name
                FROM books b
                left join author a on a.id = b.author_id
                left join category c on b.category_id =c.id
                WHERE c.category_name LIKE ?  OR b.book_name LIKE ? or a.author_name like ?
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("Search:");
            String characters = scanner.nextLine();
            ps.setString(1, "%" + characters + "%");
            ps.setString(2, "%" + characters + "%");
            ps.setString(3, "%" + characters + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = getDataBook(rs);
                String authorName = rs.getString("author_name");
                String categoryName = rs.getString("category_name");
                System.out.println(book.getId() + " | " + book.getBookName() + " | " + categoryName + " | " + book.getPublicationYear() + " | " + book.quantity + " | " + book.price + " | " + book.ratingAverage + " | " + book.categoryId + " | " + authorName + " | " + categoryName);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.getListBook();


    }
}

