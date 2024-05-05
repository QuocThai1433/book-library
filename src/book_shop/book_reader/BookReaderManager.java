package book_shop.book_reader;

import book_shop.CheckFormatDate;
import book_shop.ValidatorUtils;
import book_shop.ConnectDB;
import book_shop.InputHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

public class BookReaderManager {

    Connection connection = ConnectDB.getConnection();

    ValidatorUtils checkValid = new ValidatorUtils();
    InputHelper input = new InputHelper();

    CheckFormatDate checkDate = new CheckFormatDate();



    public BookReader input() throws ParseException {
        System.out.println("Input Book ID:");
        int bookId = input.inputMax();
        System.out.println("Input Reader Id:");
        int readId = input.inputCheckExistId((id) -> checkValid.checkExistId(id, "readers"));
        System.out.println("Input  Borrow Date (dd-MM-yyyy):");
        java.sql.Date borrowSql = input.inputDate();
        System.out.println("Input  Return Date Id (dd-MM-yyyy):");
        java.sql.Date returnSql = input.inputDate();
        returnSql = checkDate.checkBorrowDate(returnSql, borrowSql);
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
            ps.setInt(2, bookReader.getReaderId());
            ps.setDate(3, bookReader.getBorrowDate());
            ps.setDate(4, bookReader.getReturnDate());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public void translate() {
        String query = "select c.category_name,count(b.id) as total from books b INNER JOIN category c ON b.category_id = c.id group by c.category_name\n";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("category_name");
                int total = rs.getInt("total");
                System.out.println(categoryName +" | " +total);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    public void showBorrowBooks() {
        String query = "SELECT r.name_reader,b.book_name,b.quantity, br.borrowed_day, br.return_day FROM books b inner join book_reader br on b.id = br.book_id inner join readers r on br.reader_id =r.id";
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
    public void lateDateFilter() {

        String query = "SELECT *" +
                "FROM book_reader WHERE borrowed_day > ? and borrowed_day <? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            java.sql.Date borrowDate = input.inputDate();
            java.sql.Date reurnDate = input.inputDate();
            reurnDate = checkDate.checkBorrowDate(reurnDate, borrowDate);
            ps.setDate(1, borrowDate);
            ps.setDate(2, reurnDate);
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

    public static void main(String[] args) {
        BookReaderManager bookReader = new BookReaderManager();
        bookReader.translate();

    }
}
