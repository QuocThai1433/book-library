package book_shop.rating;

import book_shop.CheckValid;
import book_shop.InputId;
import book_shop.book.BookManager;
import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RatingManager {
    Connection connection = ConnectDB.getConnection();
    Scanner scanner = new Scanner(System.in);
    List<Rating> authors = new ArrayList<>();
    List<RatingReader> ratingReaders = new ArrayList<>();

    BookManager bookManager = new BookManager();
    CheckValid checkValid = new CheckValid();
    InputId inputId = new InputId();

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


    public Rating input() {
        System.out.println("Input ID:");
        int id = inputId.input((ratingId) -> checkValid.checkExistId(ratingId, "rating"));
        System.out.println("Input Star Rating");
        int starRating = 0;
        starRating = checkNotNumber(starRating);
        System.out.println("Input  Book Id:");
        int bookId = 0;
        bookId = checkNotNumber(bookId);
        bookManager.updateStar(starRating, bookId);
        System.out.println("Input Comment:");
        String comment = scanner.nextLine();
        System.out.println("Input ReaderId:");
        int readerId = 0;
        readerId = checkNotNumber(readerId);
        return new Rating(id, starRating, bookId, comment, readerId);
    }

    public int create() {
        int kq = 0;
        Rating rating = input();
        String query = " insert into rating value (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, rating.getId());
            ps.setInt(2, rating.getStarRating());
            ps.setInt(3, rating.getBookId());
            ps.setString(4, rating.getComment());

            if (checkValid.checkExistId(rating.getBookId(), "readers")) {
                ps.setInt(5, rating.getReaderId());
            } else {
                ps.setObject(1, null);

            }
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public List<Rating> getList() {
        String query = "SELECT b.id,book_name,b.rating_average FROM rating r, books b where r.book_id =b.id and b.id =? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("Input Id Book");
            int bookId1 = scanner.nextInt();
            ps.setInt(1, bookId1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                int bookId = rs.getInt("rating_average");
                System.out.println(id + " | " + bookName + " | " + bookId + " | ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public List<RatingReader> ratingBook() {
        String query = "SELECT r.name_reader, ra.star_rating\n" +
            " FROM book_shop.readers r, book_shop.rating ra\n" +
            "where r.id = ra.reader_id\n" +
            "order by ra.star_rating desc";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String nameReader = rs.getString("name_reader");
                int starRating = rs.getInt("star_rating");
                System.out.println(nameReader);
                System.out.println(starRating);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ratingReaders;
    }


    public static void main(String[] args) {
        Rating rating = new Rating();
        RatingReader ratingReader = new RatingReader();
        RatingManager manager = new RatingManager();
        manager.getList();
    }
}
