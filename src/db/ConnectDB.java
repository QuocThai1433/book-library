package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectDB {
    private static ConnectDB instance = null;

    public static synchronized ConnectDB getInstance() {
        if (instance == null) {
            instance = new ConnectDB();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/book_shop";
            String userName = "root";
            String password = "FormosVN@123";
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        if (ConnectDB.getInstance().getConnection() != null) {
            System.out.println("Connect Success");
        } else {
            System.out.println("Connect Fail");
        }
    }
}
