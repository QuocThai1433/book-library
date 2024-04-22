package book_shop.publisher;

import book_shop.CheckValid;
import book_shop.InputId;
import student.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class PublisherManager {
     Connection connection = ConnectDB.getConnection();

     Scanner scanner = new Scanner(System.in);
     List<Publisher> authors = new ArrayList<>();
     CheckValid checkValid = new CheckValid();
    InputId inputId = new InputId();

    boolean flag = false;

    public  Publisher input( ) {
        System.out.println("Input ID:");
        int id = inputId.input((publisherId) -> checkValid.checkExistId(publisherId, "publisher"));

        System.out.println("Input Publisher Name:");
        String name = scanner.nextLine();
        System.out.println("Input  Book Id:");
        int bookId = 0;
        while (!flag) {
            String number1 = scanner.nextLine();
            if (!checkValid.isNumber(number1)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            bookId= Integer.parseInt(number1);
            flag = true;

        }
        return new Publisher(id, name,bookId);
    }
    public  int create() {
        int kq = 0;
       Publisher publisher = input();
        String query = " insert into publisher value (?,?,?)";
        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, publisher.getId());
            ps.setString(2, publisher.getPublisher_name());
            ps.setInt(3, publisher.getBookId());
            if (checkValid.checkExistId(publisher.getBookId(), "books")) {
                ps.setInt(3, publisher.getBookId());
            } else {
                ps.setObject(3, null);

            }
            kq= ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public  List<Publisher> getList() {
        String query = "select * from publisher";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String nameAuthor = rs.getString("publisher_name");
                int bookId = rs.getInt("book_id");
                System.out.println(id +" | " + nameAuthor + " | "+bookId );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        PublisherManager manager = new PublisherManager();
        manager.create();
        manager.getList();
    }
}
