package book_shop;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static Connection connectDB = null;
    public static Connection getConnection()
    {
       try
       {
           Class.forName("com.mysql.cj.jdbc.Driver");
           String url = "jdbc:mysql://localhost:3306/book_shop";
           String userName = "root";
           String password = "1234";
           connectDB= DriverManager.getConnection(url,userName, password);
       }catch (Exception e)
       {
           e.printStackTrace();
       }return connectDB;
    }
    public static void main(String[] args) {
        if (getConnection()!=null)
        {
            System.out.println("Connect Success");
        }else
        {
            System.out.println("Connect Fail");
            System.exit(1);
        }
    }
}
