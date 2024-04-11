package book_shop.readers;

import book_shop.connectDB;;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderManager {
    static Connection connection = connectDB.getConnection();
    static Scanner scanner = new Scanner(System.in);

    public static Readers inputEmployee(Readers reader) {
        int n = 0;
        System.out.println("Input id:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Input Name Reader:");
        String nameReader = scanner.nextLine();
        return new Readers(id, nameReader);
    }


    public static int create(Readers reader) {
        reader = inputEmployee(reader);
        String query = "insert into readers value  (?,?)";
        int kq = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reader.getId());
            statement.setString(2, reader.getNameReader());

            kq = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }

    public static List<ReaderManager> getListBook() {
        List<ReaderManager> readerManagerList = new ArrayList<>();
        String query = "select * from readers";
        try {

            PreparedStatement ps = connectDB.connectDB.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameReader = rs.getString("name_reader");
                System.out.println(id);
                System.out.println(nameReader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readerManagerList;
    }

    public static void main(String[] args) {
        Readers reader = new Readers();
        create(reader);
        getListBook();
    }


}
