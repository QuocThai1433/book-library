package book_shop;

import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class InputId {
    Scanner scanner = new Scanner(System.in);
    CheckValid checkValid = new CheckValid();

    public int input(IntPredicate checkExist) {
        int id;
        while (true) {
            String number = scanner.nextLine();
            if (!checkValid.isNumber(number)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }

            id = Integer.parseInt(number);
            if (checkExist.test(id)) {
                System.out.println("Id exist!! Input Again");
                continue;
            }
            return id;
        }
    }

    public static boolean checkExistId(int id, String table) {
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                System.out.println("Not Connected!");
                return false;
            }
            String sql = "SELECT * FROM " + table + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ps.close();
            rs.close();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
