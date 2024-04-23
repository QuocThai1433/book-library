package book_shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class CheckValid {
    Connection connection = ConnectDB.getConnection();

    public int parse(String str) {
        return  Integer.parseInt(str);
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
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);


        return false;
    }


}
