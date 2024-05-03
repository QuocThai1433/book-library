package book_shop;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectDB {
    static Connection connect = null;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/book_shop";
            String userName = "root";
            String password = "1234";
            connect = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            System.out.println("Invalid username or password!!");
        }
        return connect;
    }
    public static void main(String[] args) {
        if (getConnection() != null) {
            System.out.println("Connect Success");
        } else {
            System.out.println("Connect Fail");
            System.exit(1);
        }
    }
}
