package book_shop.readers;

import book_shop.ValidatorUtils;
import book_shop.ConnectDB;
import book_shop.InputHelper;;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderManager {
    Connection connection = ConnectDB.getConnection();
    Scanner scanner = new Scanner(System.in);

    InputHelper inputId = new InputHelper();
    ValidatorUtils checkValid = new ValidatorUtils();

    public Readers input() {
        int n = 0;
        System.out.println("Input id:");
        int id = inputId.input((readerId) -> checkValid.checkExistId(readerId, "readers"));
        System.out.println("Input Name Reader:");
        String nameReader = scanner.nextLine();
        return new Readers(id, nameReader);

    }


    public int create() {
        Readers reader = input();
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

    public List<ReaderManager> getList() {
        List<ReaderManager> readerManagerList = new ArrayList<>();
        String query = "select * from readers";
        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameReader = rs.getString("name_reader");
                System.out.println(id + " | " + nameReader);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readerManagerList;
    }

    public static void main(String[] args) {
        Readers reader = new Readers();
        ReaderManager readerManager = new ReaderManager();
        readerManager.getList();

    }


}
