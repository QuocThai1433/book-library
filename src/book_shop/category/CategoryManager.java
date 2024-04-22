package book_shop.category;

import book_shop.CheckValid;
import book_shop.InputId;
import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryManager {
    Connection connection = ConnectDB.getConnection();
    Scanner scanner = new Scanner(System.in);
    List<Category> authors = new ArrayList<>();
    CheckValid checkValid = new CheckValid();
    InputId inputId = new InputId();

    public Category input() {
        System.out.println("Input ID:");
        int id = inputId.input(authorId -> checkValid.checkExistId(authorId, "category"));
        System.out.println("Input Category Name:");
        String name = scanner.nextLine();

        return new Category(id, name);
    }

    public int create() {
        int kq = 0;
        Category category = input();
        String query = " insert into category value (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, category.getId());
            ps.setString(2, category.getCategoryName());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
    }

    public List<Category> getList() {
        String query = "select * from category";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String nameAuthor = rs.getString("category_name");

                System.out.println(id + " | " + nameAuthor);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return authors;
    }

    public static void main(String[] args) {
        CategoryManager manager = new CategoryManager();
        manager.getList();
    }
}
