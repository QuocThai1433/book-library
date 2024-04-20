package book_shop;

import book_shop.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckValid {
    Connection connection = ConnectDB.getConnection();

    public int parse(String str) {
        int id = Integer.parseInt(str);
        return id;
    }

    public boolean isNumber(String str) {
        try {
            parse(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkExistId(int id, String table) {
        try {
            String sql = "SELECT * FROM " + table + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
