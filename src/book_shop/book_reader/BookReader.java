package book_shop.book_reader;

import java.sql.Date;

public class BookReader {
    private int bookId;
    private int readerId;
    private Date borrowDate;
    private Date returnDate;


    public BookReader() {
    }

    public BookReader(int bookId, int readeId, Date borrowDate, Date returnDate) {
        super();
        this.bookId = bookId;
        this.readerId = readeId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }


    public BookReader(Date borrowDate, Date returnDate) {
        super();
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    public void setBook_id(int bookId) {
        this.bookId = bookId;
    }

    public void setReader_id(int readerId) {
        this.readerId = readerId;
    }

    public void setBorrow_date(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturn_date(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getReaderId() {
        return readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
