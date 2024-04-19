package book_shop.category;

import book_shop.CheckValid;
import student.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class CategoryManager {
     Connection connection = ConnectDB.getConnection();
     Scanner scanner = new Scanner(System.in);
     List<Category> authors = new ArrayList<>();
     CheckValid checkValid = new CheckValid();
    public int inputId(Function<Integer, Boolean> checkExist) {
        int id = 0;
        boolean flag = false;

        while (!flag) {
            String number = scanner.nextLine();

            if (!checkValid.isNumber(number)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            id = Integer.parseInt(number);
            if (Boolean.TRUE.equals(checkExist.apply(id))) {
                System.out.println("Id exist!! Input Again");
                continue;
            }
            flag = true;
        }
        return id;
    }
    public  Category input(Category category) {
        System.out.println("Input ID:");
        int id = inputId((authorId) -> checkValid.checkExistId(authorId, "category"));
        scanner.nextLine();
        System.out.println("Input Category Name:");
        String name = scanner.nextLine();

        return new Category(id, name);
    }

    public  int create(Category category) {
        int kq = 0;
        category = input(category);
        String query = " insert into category value (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, category.getId());
            ps.setString(2, category.getCategoryName());
            kq= ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public  List<Category> getList() {
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

    public  void main(String[] args) {
        Category category = new Category();
        create(category);
        getList();
    }
}
