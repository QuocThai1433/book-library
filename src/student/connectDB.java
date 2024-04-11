package student;

import java.sql.Connection;
import java.sql.DriverManager;
public class connectDB {
    static Connection connection = null;
    public static Connection getConnection()  {
        try{

            Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/book_shop";
            String userName = "root";
            String password = "1234";

            connection = DriverManager.getConnection(url,userName,password);



        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) throws Exception{
        if (getConnection()!=null)
        {
            System.out.println("Connect Success");
        }else
        {
            System.out.println("Connect Fail");
        }
    }
}
