package book_shop.book_reader;

import book_shop.CheckValid;
import book_shop.InputId;
import db.ConnectDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookReaderManager {

    Connection connection = ConnectDB.getInstance().getConnection();
    Scanner scanner = new Scanner(System.in);
    List<BookReader> authors = new ArrayList<>();
    List<BookCategory> bookCategories = new ArrayList<>();
    CheckValid checkValid = new CheckValid();
    String formatter = ("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
    InputId inputId = new InputId();


    public int checkNotNumber(int number) {
        boolean flag = false;
        while (!flag) {
            String number1 = scanner.nextLine();
            if (!checkValid.isNumber(number1)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            number = Integer.parseInt(number1);
            flag = true;
        }
        return number;
    }


    public String checkFormat(String input) {
        boolean flag1 = false;
        while (!flag1) {
            String number1 = scanner.nextLine();
            if (!checkValid.checkDate(number1, "dd-MM-yyyy")) {
                System.out.println("Not format date!! Input Again:");
                continue;
            }
            input = number1;
            flag1 = true;

        }
        return input;
    }

    public BookReader input() throws ParseException {

        System.out.println("Input Book ID:");
        int bookId = 0;
        bookId = checkNotNumber(bookId);

        System.out.println("Input Reader Id:");
        int readId = 0;
        readId = checkNotNumber(readId);
        System.out.println("Input  Borrow Date:");
        String borrowDate = null;
        borrowDate = checkFormat(borrowDate);

        java.sql.Date borrowSql = formatDate(borrowDate);
        System.out.println("Input  Return Date Id:");
        String returnDate = null;
        returnDate = checkFormat(returnDate);
        java.sql.Date returnSql = formatDate(returnDate);
        return new BookReader(bookId, readId, borrowSql, returnSql);
    }

    public int create() throws ParseException {
        int kq = 0;
        BookReader bookReader = input();
        String query = " insert into book_reader value (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            if (checkValid.checkExistId(bookReader.getBookId(), "books")) {
                ps.setInt(1, bookReader.getBookId());
            } else {
                ps.setObject(1, null);

            }

            if (checkValid.checkExistId(bookReader.getBookId(), "readers")) {
                ps.setInt(2, bookReader.getReaderId());
            } else {
                ps.setObject(2, null);

            }
            ps.setDate(3, bookReader.getBorrowDate());
            ps.setDate(4, bookReader.getReturnDate());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public List<BookCategory> filter() {
        String query = "select b.id,b.book_name, c.id as categoryId,c.category_name from books b, category c where c.categozry_name like ? and b.category_id= c.id;\n ";
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
                System.out.println(bookId + " | " + nameBook + " | " + categoryId + " | " + categoryName);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookCategories;
    }

    public void statistical() {
        String query = "select c.category_name,count(b.id) as total from books b INNER JOIN category c ON b.category_id = c.id group by c.category_name\n";
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

    public List<BookReader> getList() {
        String query = "select * from book_reader";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int bookId = rs.getInt("book_id");
                String readerId = rs.getString("reader_Id");
                String borrowedDay = rs.getString("borrowed_day");
                String returnDate = rs.getString("return_day");
                System.out.println(bookId + " | " + readerId + " | " + borrowedDay + " | " + returnDate);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public void totalBook() {
        String query = "select sum(b.quantity) as total  from book_shop.books b";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt("total");
                System.out.println(total);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public java.sql.Date formatDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateUtil = format.parse(date);
        return new java.sql.Date(dateUtil.getTime());


    }

    public void showBorrowBooks() {
        String query = "SELECT r.name_reader,b.book_name,b.quantity, br.borrowed_day, br.return_day\n" +
            " FROM books b\n" +
            "inner join book_reader br\n" +
            "on b.id = br.book_id\n" +
            "inner join readers r\n" +
            "on br.reader_id =r.id";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nameReader = rs.getString("name_reader");
                String bookName = rs.getString("book_name");
                int quantity = rs.getInt("quantity");
                java.util.Date brdate = rs.getDate("borrowed_day");
                java.util.Date rtdate = rs.getDate("return_day");
                System.out.println(nameReader + " | " + bookName + " | " + quantity + " | " + brdate + " | " + rtdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void report() {
        String query = "SELECT * FROM books b  inner join book_reader br on b.id = br.book_id inner join readers r on br.reader_id =r.id";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt("id");
                java.util.Date brdate = rs.getDate("borrowed_day");
                java.util.Date rtdate = rs.getDate("return_day");
                String nameReader = rs.getString("name_reader");
                System.out.println(bookId + " | " + brdate + " | " + rtdate + " | " + nameReader);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void lateLog() {
        String query = "SELECT * FROM book_reader WHERE borrowed_day > ? and borrowed_day <? ";
        BookReader bookReader = new BookReader();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("Input  Borrow Date 1 (dd-mm-yyyy):");
            String borrowDate = scanner.nextLine();
            Date borrowDateSql1 = formatDate(borrowDate);

            System.out.println("Input  Borrow Date 2 Id (dd-mm-yyyy) :");
            String returnDateSql = scanner.nextLine();
            Date borrowDateSql2 = formatDate(returnDateSql);
            ps.setDate(1, borrowDateSql1);
            ps.setDate(2, borrowDateSql2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                java.util.Date brdate = rs.getDate("borrowed_day");
                java.util.Date rtdate = rs.getDate("return_day");
                System.out.println(bookId + " | " + brdate + " | " + rtdate + " | ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        BookReaderManager manager = new BookReaderManager();
        manager.showBorrowBooks();
    }
}
