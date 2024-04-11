package book_shop.category;

import book_shop.category.Category;
import student.connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryManager {
    static Connection connection = connectDB.getConnection();
    static Scanner scanner = new Scanner(System.in);
    static List<Category> authors = new ArrayList<>();

    public static Category input(Category category) {
        System.out.println("Input ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input Category Name:");
        String name = scanner.nextLine();
        System.out.println("Input  Book Id:");
        int bookId = scanner.nextInt();
        return new Category(id, name,bookId);
    }

    public static int create(Category category) {
        int kq = 0;
        category = input(category);
        String query = " insert into category value (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, category.getId());
            ps.setString(2, category.getCategoryName());
            ps.setInt(3, category.getBook_id());
            kq= ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static List<Category> getList() {
        String query = "select * from category";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String nameAuthor = rs.getString("category_name");
                int bookId = rs.getInt("book_id");
                System.out.println(id );
                System.out.println(nameAuthor);
                System.out.println(bookId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void main(String[] args) {
        Category category = new Category();
        create(category);
        getList();
    }
}
