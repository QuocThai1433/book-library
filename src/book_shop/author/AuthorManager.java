package book_shop.author;

import book_shop.CheckValid;
import book_shop.InputId;
import student.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.function.Function;

public class AuthorManager {
    static Connection connection = ConnectDB.getConnection();
    static Scanner scanner = new Scanner(System.in);
    static List<Author> authors = new ArrayList<>();

    static CheckValid check = new CheckValid();

    InputId inputId = new InputId();
    boolean flag = false;

    public int checkNotNumber(int number) {
        boolean flag = false;
        while (!flag) {
            String number1 = scanner.nextLine();
            if (!check.isNumber(number1)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            number = Integer.parseInt(number1);
            flag = true;
        }
        return number;
    }

    public Author input() {
        System.out.println("Input ID:");
        int id = inputId.input((authorId) -> check.checkExistId(authorId, "author"));
        System.out.println("Input Author Name:");
        String name = scanner.nextLine();
        System.out.println("Input Book Id:");
        int bookId = 0;
        bookId = checkNotNumber(bookId);
        return new Author(id, name, bookId);
    }

    public int create() {
        int kq = 0;
        Author author = input();
        String query = " insert into author value (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, author.getId());
            ps.setString(2, author.getAuthorName());
            if (check.checkExistId(author.getBookId(), "author")) {

                ps.setInt(3, author.getBookId());
            } else {
                ps.setObject(3, null);
            }
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public List<Author> getList() {
        String query = "select * from author";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String nameAuthor = rs.getString("author_name");
                int bookId = rs.getInt("book_id");

                System.out.println(id + " | " + nameAuthor + " | " + bookId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        AuthorManager manager = new AuthorManager();
        manager.create();
    }
}
