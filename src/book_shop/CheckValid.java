package book_shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CheckValid {
    Connection connection = ConnectDB.getConnection();
    Scanner scanner = new Scanner(System.in);

    public boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isNumberFloat(String str) {
        try {
            Float.parseFloat(str);
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

//    }  public boolean inputCheckExistId( int id, String table) {
//        try {
//            int kq = 0;
//            String sql = "SELECT * FROM " + table + " WHERE id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            while (!checkExistId(id, table)) {
//                if (checkExistId(id, table)) {
//                    return true;
//                }else {}
//
//            }
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return fal
}