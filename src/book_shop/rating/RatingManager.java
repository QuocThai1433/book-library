package book_shop.rating;

import book_shop.rating.Rating;
import student.connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RatingManager {
    static Connection connection = connectDB.getConnection();
    static Scanner scanner = new Scanner(System.in);
    static List<Rating> authors = new ArrayList<>();

    public static Rating input(Rating rating) {
        System.out.println("Input ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input Star Rating");
        int star_rating = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input  Book Id:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input Comment:");
        String comment = scanner.nextLine();
        return new Rating(id, star_rating,bookId,comment);
    }

    public static int create(Rating rating) {
        int kq = 0;
        rating = input(rating);
        String query = " insert into rating value (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, rating.getId());
            ps.setInt(2, rating.getStar_rating());
            ps.setInt(3, rating.getBook_id());
            ps.setString(4, rating.getComment());

            kq= ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static List<Rating> getList() {
        String query = "select * from rating";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameAuthor = rs.getString("star_rating");
                int bookId = rs.getInt("book_id");
                String comment = rs.getString("comment");
                System.out.println(id );
                System.out.println(nameAuthor);
                System.out.println(bookId);
                System.out.println(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        Rating rating = new Rating();
        create(rating);
        getList();
    }
}
