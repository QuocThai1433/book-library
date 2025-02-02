package book_shop.publisher;

import book_shop.publisher.Publisher;
import student.connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PublisherManager {
    static Connection connection = connectDB.getConnection();

    static Scanner scanner = new Scanner(System.in);
    static List<Publisher> authors = new ArrayList<>();

    public static Publisher input(Publisher publisher) {
        System.out.println("Input ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input Publisher Name:");
        String name = scanner.nextLine();
        System.out.println("Input  Book Id:");
        int bookId = scanner.nextInt();
        return new Publisher(id, name,bookId);
    }

    public static int create(Publisher publisher) {
        int kq = 0;
        publisher = input(publisher);
        String query = " insert into publisher value (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, publisher.getId());
            ps.setString(2, publisher.getPublisher_name());
            ps.setInt(3, publisher.getBook_id());
            kq= ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static List<Publisher> getList() {
        String query = "select * from publisher";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String nameAuthor = rs.getString("publisher_name");
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
        Publisher publisher = new Publisher();
        create(publisher);
        getList();
    }
}
