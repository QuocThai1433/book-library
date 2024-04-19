package book_shop.rating;

import book_shop.CheckValid;
import book_shop.book.Book;
import book_shop.book.BookManager;
import student.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class RatingManager {
     Connection connection = ConnectDB.getConnection();
     Scanner scanner = new Scanner(System.in);
     List<Rating> authors = new ArrayList<>();
     List<RatingReader> ratingReaders = new ArrayList<>();

     BookManager bookManager = new BookManager();
     CheckValid checkValid = new CheckValid();

    public int inputId(Function<Integer, Boolean> checkExist) {
        int id = 0;
        boolean flag = false;

        while (!flag) {
            String number = scanner.nextLine();

            if (!checkValid.isNumber(number)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            id = Integer.parseInt(number);
            if (Boolean.TRUE.equals(checkExist.apply(id))) {
                System.out.println("Id exist!! Input Again");
                continue;
            }
            flag = true;
        }
        return id;
    }
    public  Rating input(Rating rating) {
        System.out.println("Input ID:");
        int id = inputId((authorId) -> checkValid.checkExistId(authorId, "rating"));
        scanner.nextLine();
        System.out.println("Input Star Rating");
        int star_rating = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Input  Book Id:");
        int bookId = inputId((authorId) -> checkValid.checkExistId(authorId, "books"));
        bookManager.updateStar(star_rating,bookId);
        System.out.println("Input Comment:");
        String comment = scanner.nextLine();
        System.out.println("Input ReaderId:");
        int readerId = inputId((authorId) -> checkValid.checkExistId(authorId, "reader"));
        return new Rating(id, star_rating, bookId, comment, readerId);
    }

    public  int create(Rating rating) {
        int kq = 0;
        rating = input(rating);
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

    public  List<Rating> getList() {
        String query = "select * from rating";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameAuthor = rs.getString("star_rating");
                int bookId = rs.getInt("book_id");
                String comment = rs.getString("comment");
                System.out.println(id);
                System.out.println(nameAuthor);
                System.out.println(bookId);
                System.out.println(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public  List<RatingReader> ratingBook() {
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


    public void main(String[] args) {
        Rating rating = new Rating();
        RatingReader ratingReader = new RatingReader();
       create(rating);
    }
}
