package book_shop.author;

import student.connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorManager {
    static Connection connection = connectDB.getConnection();
    static Scanner scanner = new Scanner(System.in);
    static  List<Author> authors = new ArrayList<>();

    public static Author input(Author author) {
        System.out.println("Input ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input Author Name:");
        String name = scanner.nextLine();
        System.out.println("Input  Book Id:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        return new Author(id, name,bookId);
    }

    public static int create(Author author) {
        int kq = 0;
        author = input(author);
        String query = " insert into author value (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, author.getId());
            ps.setString(2, author.getAuthor_name());
            ps.setInt(3, author.getBook_id());

            kq= ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static List<Author> getList() {
        String query = "select * from author";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String nameAuthor = rs.getString("author_name");
                int bookId = rs.getInt("book_id");

                System.out.println(id );
                System.out.println(nameAuthor);
                System.out.println(bookId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        Author author = new Author();
        create(author);
        getList();
    }
}
