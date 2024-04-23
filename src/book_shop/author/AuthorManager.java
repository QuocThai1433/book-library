package book_shop.author;

import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static constants.EntityConstant.AUTHOR;
import static constants.EntityConstant.BOOKS;
import static utils.InputUtils.checkExistIdFunc;
import static utils.InputUtils.inputId;

public class AuthorManager {
    static Scanner scanner = new Scanner(System.in);
    
    public Author input() {
        System.out.println("Input ID:");
        int id = inputId(checkExistIdFunc(AUTHOR));
        
        System.out.println("Input Author Name:");
        String name = scanner.nextLine();
        
        System.out.println("Input Book Id:");
        int bookId = inputId(checkExistIdFunc(BOOKS));
        
        return new Author(id, name, bookId);
    }
    
    public int create() {
        int kq = 0;
        Author author = input();
        String query = " insert into author value (?,?,?)";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, author.getId());
            ps.setString(2, author.getAuthorName());
            ps.setInt(3, author.getBookId());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
    }
    
    public List<Author> getList() {
        List<Author> authors = new ArrayList<>();
        String query = "select * from author";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return Collections.emptyList();
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = getAuthor(rs);
                authors.add(author);
                System.out.println(author.getId() + " | " + author.getAuthorName() + " | " + author.getBookId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return authors;
    }
    
    private Author getAuthor(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nameAuthor = rs.getString("author_name");
        int bookId = rs.getInt("book_id");
        return new Author(id, nameAuthor, bookId);
    }
    
    public static void main(String[] args) {
        AuthorManager manager = new AuthorManager();
        manager.create();
    }
}
