package book_shop.publisher;

import book_shop.CheckValid;
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

    public  Publisher input( ) {
        System.out.println("Input ID:");
        int id = inputId((authorId) -> checkValid.checkExistId(authorId, "publisher"));
        scanner.nextLine();
        System.out.println("Input Publisher Name:");
        String name = scanner.nextLine();
        System.out.println("Input  Book Id:");
        int bookId = inputId((authorId) -> checkValid.checkExistId(authorId, "books"));
        return new Publisher(id, name,bookId);
    }
    public  int create(Publisher publisher) {
        int kq = 0;
        publisher = input();
        String query = " insert into publisher value (?,?,?)";
        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, publisher.getId());
            ps.setString(2, publisher.getPublisher_name());
            ps.setInt(3, publisher.getBookId());
            if (checkValid.checkExistId(publisher.getBookId(), "books")) {
                ps.setInt(3, publisher.getBookId());
            } else {
                ps.setObject(1, null);

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
        PublisherManager manager = new PublisherManager();
        manager.create(publisher);
        manager.getList();
    }
}
