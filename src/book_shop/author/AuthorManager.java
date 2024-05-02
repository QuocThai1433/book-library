package book_shop.author;

import book_shop.CheckValid;
import book_shop.ConnectDB;
import book_shop.Input;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorManager {
    static Connection connection = ConnectDB.getConnection();
    static Scanner scanner = new Scanner(System.in);
    static List<Author> authors = new ArrayList<>();

    static CheckValid check = new CheckValid();

    Input inputId = new Input();
    boolean flag = false;



    public Author input() {
        System.out.println("Input ID:");
        int id = inputId.input((authorId) -> check.checkExistId(authorId, "author"));
        System.out.println("Input Author Name:");
        String name = scanner.nextLine();
        return new Author(id, name);
    }

    public int create() {
        int kq = 0;
        Author author = input();
        String query = " insert into author value (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, author.getId());
            ps.setString(2, author.getAuthorName());
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
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {

                int id = rs.getInt("id");
                String nameAuthor = rs.getString("author_name");
                int bookId = rs.getInt("book_id");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(" | "+ metaData.getColumnName(i)  );
                }
                System.out.println(); // New line after printing column names


                System.out.println(" | " +id + "  |  " + nameAuthor );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        AuthorManager manager = new AuthorManager();
        manager.getList();
    }
}
