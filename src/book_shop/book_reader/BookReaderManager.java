package book_shop.book_reader;

import book_shop.book.Book;
import book_shop.category.Category;
import student.connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookReaderManager {
    static Connection connection = connectDB.getConnection();
    static Scanner scanner = new Scanner(System.in);
    static List<BookReader> authors = new ArrayList<>();
    static List<BookCategory> bookCategories = new ArrayList<>();


    //static String formatter =("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");

    private static DateTimeFormatter dateFormatter;

    public static BookReader input(BookReader book_reader) throws Exception {
        String pattern = "MM-dd-yyyy";
        System.out.println("Input Book Id:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input Reader Id:");
        int readId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input  Borrow Date:");
        String borrowDate = scanner.nextLine();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        borrowDate = simpleDateFormat.format(new Date());
        simpleDateFormat.parse(borrowDate);

//        if (dateFormatter.parse(borrowDate)) {
//            System.out.println("Chuỗi  đúng định dạng.");
//        } else {
//            System.out.println("Chuỗi không đúng định dạng.");
//        }
        System.out.println("Input  Return Date Id:");
        String returnDate = scanner.nextLine();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);
        returnDate = simpleDateFormat1.format(new Date());
        simpleDateFormat.parse(returnDate);
        return new BookReader(bookId, readId, borrowDate, returnDate);

    }

    public static int create(BookReader book_reader) throws Exception {
        int kq = 0;
        book_reader = input(book_reader);
        String query = " insert into book_reader value (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, book_reader.getBook_id());
            ps.setInt(2, book_reader.getReader_id());
            ps.setString(3, book_reader.getBorrow_date());
            ps.setString(4, book_reader.getReturn_date());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static List<BookCategory> filter(BookCategory category) {
        String query = "select b.id,b.book_name, c.id as categoryId,c.category_name \n" +
                "from books b, category c \n" +
                "where c.category_name like ?\n" +
                "and b.category_id= c.id;\n ";
        System.out.println("Input Category Name: ");
        String categoryName = scanner.nextLine();
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int bookId = rs.getInt("id");
                String nameBook = rs.getString("book_name");
                int categoryId = rs.getInt("categoryId");
                String categoryName1 = rs.getString("category_name");

                System.out.println(bookId);
                System.out.println(nameBook);
                System.out.println(categoryId);
                System.out.println(categoryName1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookCategories;
    }

    public static void statistical() {
    String query ="select c.category_name,count(b.id) as total\n" +
            "from books b\n" +
            "INNER JOIN category c ON b.category_id = c.id\n" +
            "group by c.category_name\n";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {


                String categoryName = rs.getString("category_name");
                int total = rs.getInt("total");


                System.out.println(categoryName);
                System.out.println(total);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<BookReader> getList() {
        String query = "select * from book_reader";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int bookId = rs.getInt("book_id");
                String readerId = rs.getString("reader_Id");
                String borrowedDay = rs.getString("borrowed_day");
                String returnDate = rs.getString("return_day");

                System.out.println(bookId);
                System.out.println(readerId);
                System.out.println(borrowedDay);
                System.out.println(returnDate);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void lateLog() {
        System.out.println("Nhap ngay hien tai:");

    }

    public static void main(String[] args) throws Exception {
        BookReader book_reader = new BookReader();
        BookCategory category = new BookCategory();
        statistical();
    }
}
