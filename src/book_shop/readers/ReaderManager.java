package book_shop.readers;

import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static constants.EntityConstant.READERS;
import static utils.InputUtils.checkExistIdFunc;
import static utils.InputUtils.inputId;

public class ReaderManager {
    Scanner scanner = new Scanner(System.in);
    
    public Readers input() {
        System.out.println("Input id:");
        int id = inputId(checkExistIdFunc(READERS));
        
        System.out.println("Input Name Reader:");
        String nameReader = scanner.nextLine();
        
        return new Readers(id, nameReader);
    }
    
    public int create() {
        Readers reader = input();
        String query = "insert into readers (id, name_reader) value (?, ?)";
        int kq = 0;
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reader.getId());
            statement.setString(2, reader.getNameReader());
            
            kq = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
    }
    
    public List<ReaderManager> getList() {
        List<ReaderManager> readerManagerList = new ArrayList<>();
        String query = "select * from readers";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return Collections.emptyList();
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameReader = rs.getString("name_reader");
                System.out.println(id + " | " + nameReader);
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return readerManagerList;
    }
    
    public static void main(String[] args) {
        ReaderManager readerManager = new ReaderManager();
        readerManager.getList();
    }
}
