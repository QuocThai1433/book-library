package book_shop.book_reader;

import db.ConnectDB;
import utils.InputUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constants.EntityConstant.BOOKS;
import static constants.EntityConstant.READERS;
import static utils.InputUtils.checkExistId;
import static utils.InputUtils.inputSqlDate;

public class BookReaderManager {
    public BookReader input() {
        System.out.println("Input Book ID:");
        int bookId = InputUtils.inputInteger();
        
        System.out.println("Input Reader Id:");
        int readId = InputUtils.inputInteger();
        
        System.out.println("Input  Borrow Date:");
        java.sql.Date borrowSql = InputUtils.inputSqlDate();
        
        System.out.println("Input  Return Date Id:");
        java.sql.Date returnSql = InputUtils.inputSqlDate();
        
        return new BookReader(bookId, readId, borrowSql, returnSql);
    }
    
    public int create() {
        int kq = 0;
        BookReader bookReader = input();
        String query = " insert into book_reader (book_id, reader_id, borrowed_day, return_day) value (?, ?, ?, ?)";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return 0;
            }
            
            PreparedStatement ps = connection.prepareStatement(query);
            if (checkExistId(bookReader.getBookId(), BOOKS)) {
                ps.setInt(1, bookReader.getBookId());
            } else {
                ps.setObject(1, null);
            }
            
            if (checkExistId(bookReader.getReaderId(), READERS)) {
                ps.setInt(2, bookReader.getReaderId());
            } else {
                ps.setObject(2, null);
            }
            
            ps.setDate(3, bookReader.getBorrowDate());
            ps.setDate(4, bookReader.getReturnDate());
            kq = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kq;
    }
    
    public void statistical() {
        String query = """
            select c.category_name, count(b.id) as total
            from books b
            INNER JOIN category c ON b.category_id = c.id
            group by c.category_name
            """;
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("category_name");
                int total = rs.getInt("total");
                System.out.println(categoryName);
                System.out.println(total);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<BookReader> getList() {
        List<BookReader> bookReaders = new ArrayList<>();
        String query = "select * from book_reader";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return Collections.emptyList();
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookReader bookReader = getBookReader(rs);
                showBookReader(bookReader);
                bookReaders.add(bookReader);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bookReaders;
    }
    
    public BookReader getBookReader(ResultSet rs) throws SQLException {
        int bookId = rs.getInt("book_id");
        int readerId = rs.getInt("reader_Id");
        Date borrowedDay = rs.getDate("borrowed_day");
        Date returnDate = rs.getDate("return_day");
        return new BookReader(bookId, readerId, borrowedDay, returnDate);
    }
    
    public void showBookReader(BookReader bookReader) {
        System.out.println(
            bookReader.getBookId()
                + " | "
                + bookReader.getReaderId()
                + " | "
                + bookReader.getBorrowDate()
                + " | "
                + bookReader.getReturnDate())
        ;
    }
    
    public void totalBook() {
        String query = "select sum(b.quantity) as total  from book_shop.books b";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt("total");
                System.out.println(total);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void showBorrowBooks() {
        String query = """
            SELECT r.name_reader,b.book_name,b.quantity, br.borrowed_day, br.return_day
            FROM books b
            inner join book_reader br
            on b.id = br.book_id
            inner join readers r
            on br.reader_id = r.id
            """;
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return;
            }
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
            System.out.println(e.getMessage());
        }
    }
    
    
    public void lateLog() {
        System.out.print("Input start date 1 (dd-mm-yyyy): ");
        Date startDate = inputSqlDate();
        
        System.out.print("Input end date 2 Id (dd-mm-yyyy): ");
        Date endDate = inputSqlDate();
        
        String query = "SELECT * FROM book_reader WHERE borrowed_day > ? and borrowed_day < ?";
        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            if (connection == null) {
                return;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                java.util.Date brdate = rs.getDate("borrowed_day");
                java.util.Date rtdate = rs.getDate("return_day");
                System.out.println(bookId + " | " + brdate + " | " + rtdate + " | ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        BookReaderManager manager = new BookReaderManager();
        manager.showBorrowBooks();
    }
}
