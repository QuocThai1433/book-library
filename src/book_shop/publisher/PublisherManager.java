package book_shop.publisher;

import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static constants.EntityConstant.BOOKS;
import static constants.EntityConstant.PUBLISHER;
import static utils.InputUtils.checkExistIdFunc;
import static utils.InputUtils.inputId;

public class PublisherManager {
    Scanner scanner = new Scanner(System.in);
    
    public Publisher input() {
        System.out.println("Input ID:");
        int id = inputId(checkExistIdFunc(PUBLISHER));
        
        System.out.println("Input Publisher Name:");
        String name = scanner.nextLine();
        
        System.out.println("Input  Book Id:");
        int bookId = inputId(checkExistIdFunc(BOOKS));
        
        return new Publisher(id, name, bookId);
    }
    
    public int create() {
        int kq = 0;
        Publisher publisher = input();
        String query = " insert into publisher value (?,?,?)";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, publisher.getId());
            ps.setString(2, publisher.getPublisherName());
            ps.setInt(3, publisher.getBookId());
            if (publisher.getBookId() != 0) {
                ps.setInt(3, publisher.getBookId());
            } else {
                ps.setObject(3, null);
            }
            kq = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
    }
    
    public List<Publisher> getList() {
        List<Publisher> publishers = new ArrayList<>();
        String query = "select * from publisher";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return Collections.emptyList();
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Publisher publisher = getPublisher(rs);
                publishers.add(publisher);
                System.out.println(
                    publisher.getId()
                        + " | "
                        + publisher.getPublisherName()
                        + " | "
                        + publisher.getBookId()
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return publishers;
    }
    
    private Publisher getPublisher(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String publisherName = rs.getString("publisher_name");
        int bookId = rs.getInt("book_id");
        return new Publisher(id, publisherName, bookId);
    }
    
    public static void main(String[] args) {
        PublisherManager manager = new PublisherManager();
        manager.create();
        manager.getList();
    }
}
