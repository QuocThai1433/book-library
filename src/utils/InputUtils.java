package utils;

import db.ConnectDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int DEFAULT_RETRY_TIME = 3;
    
    InputUtils() {
    }
    
    public static int inputId(List<IntPredicate> checks) {
        while (true) {
            String number = scanner.nextLine();
            if (isNumber(number)) {
                int finalId = Integer.parseInt(number);
                if (checks.stream().noneMatch(item -> item.test(finalId))) {
                    System.out.println("Id exist!! Input Again");
                } else {
                    return finalId;
                }
            } else {
                System.out.println("Not Number!! Input Again:");
            }
        }
    }
    
    public static Date inputSqlDate(String format) {
        while (true) {
            String dateString = scanner.nextLine();
            if (!DateUtils.checkFormat(dateString, format)) {
                System.out.println("Not format date!! Input Again:");
                continue;
            }
            return DateUtils.toSqlDate(dateString, format);
        }
    }
    
    public static Date inputSqlDate() {
        return inputSqlDate("dd-MM-yyyy");
    }
    
    public static int inputId(IntPredicate check) {
        return inputId(List.of(check));
    }
    
    public static int inputInteger(int retryTime) {
        int numberOfTries = 0;
        while (numberOfTries < retryTime) {
            String number = scanner.nextLine();
            if (isNumber(number)) {
                return Integer.parseInt(number);
            } else {
                System.out.println("Not Number!! Input Again:");
            }
            numberOfTries++;
        }
        throw new RuntimeException("Number of tries exceeded!");
    }
    
    public static int inputInteger() {
        return inputInteger(DEFAULT_RETRY_TIME);
    }
    
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static IntPredicate checkExistIdFunc(String table) {
        return id -> checkExistId(id, table);
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
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
