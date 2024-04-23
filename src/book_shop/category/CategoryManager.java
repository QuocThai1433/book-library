package book_shop.category;

import db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static constants.EntityConstant.CATEGORY;
import static utils.InputUtils.checkExistIdFunc;
import static utils.InputUtils.inputId;

public class CategoryManager {
    Scanner scanner = new Scanner(System.in);
    
    public Category input() {
        System.out.println("Input ID:");
        int id = inputId(checkExistIdFunc(CATEGORY));
        System.out.println("Input Category Name:");
        String name = scanner.nextLine();
        
        return new Category(id, name);
    }
    
    public int create() {
        int kq = 0;
        Category category = input();
        String query = " insert into category value (?,?)";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
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
        List<Category> categories = new ArrayList<>();
        String query = "select * from category";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return Collections.emptyList();
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = getCategory(rs);
                System.out.println(category.getId() + " | " + category.getCategoryName());
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }
    
    private Category getCategory(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String categoryName = rs.getString("category_name");
        return new Category(id, categoryName);
    }
    
    public static void main(String[] args) {
        CategoryManager manager = new CategoryManager();
        manager.getList();
    }
}
