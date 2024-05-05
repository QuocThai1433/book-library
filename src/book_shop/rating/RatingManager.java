package book_shop.rating;

import book_shop.ValidatorUtils;
import book_shop.ConnectDB;
import book_shop.InputHelper;
import book_shop.book.BookManager;

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
    BookManager bookManager = new BookManager();
    ValidatorUtils checkValid = new ValidatorUtils();
    InputHelper inputHelper = new InputHelper();



    public Rating input() {
        System.out.println("Input ID:");
        int id = inputHelper.input((ratingId) -> checkValid.checkExistId(ratingId, "rating"));
        System.out.println("Input Star Rating");
        float starRating = inputHelper.validRating();
        System.out.println("Input  Book Id:");
        int bookId = inputHelper.inputNumber();
        System.out.println("Input Comment:");
        String comment = scanner.nextLine();
        System.out.println("Input ReaderId:");
        int readerId = inputHelper.inputCheckExistId((readerIds -> checkValid.checkExistId(readerIds, "readers")));
        return new Rating(id, starRating, bookId, comment, readerId);
    }

    public int create() {
        int kq = 0;
        Rating rating = input();
        String query = " insert into rating value (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, rating.getId());
            ps.setFloat(2, rating.getStarRating());
            ps.setInt(3, rating.getBookId());
            ps.setString(4, rating.getComment());
            if (checkValid.checkExistId(rating.getReaderId(), "readers")) {
                ps.setInt(5, rating.getReaderId());
            } else {
                ps.setObject(5, null);
            }
            kq = ps.executeUpdate();
            bookManager.updateStar(rating.getStarRating(), rating.getBookId());
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
            int bookId = inputHelper.inputNumber();
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                int ratingAverage = rs.getInt("rating_average");
                System.out.println(id + " | " + bookName + " | " + ratingAverage + " | ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        RatingManager manager = new RatingManager();
        manager.create();

    }
}
