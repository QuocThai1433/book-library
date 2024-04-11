package book_shop.book_reader;

import java.sql.Date;

public class BookReader {
    private int book_id;
    private int reader_id;
    private String borrow_date;
    private String return_date;
   private Date borrow_datef;

private Date return_datef;
    public BookReader() {
    }

    public BookReader(int bookId, int readeId, String borrowDate, String returDate) {
        super();
        this.book_id = bookId;
        this.reader_id = readeId;
        this.borrow_date = borrowDate;
        this.return_date = returDate;
    }

    public BookReader(int bookId, int readId, Date borrowDate, Date returnDate) {
        super();
        this.book_id = bookId;
        this.reader_id = readId;
        this.borrow_datef = borrowDate;
        this.return_datef = returnDate;
    }

    public void setBorrow_datef(Date borrow_datef) {
        this.borrow_datef = borrow_datef;
    }

    public void setReturn_datef(Date return_datef) {
        this.return_datef = return_datef;
    }

    public java.sql.Date getBorrow_datef() {
        return (java.sql.Date) borrow_datef;
    }

    public java.sql.Date getReturn_datef() {
        return (java.sql.Date) return_datef;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public int getReader_id() {
        return reader_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public String getReturn_date() {
        return return_date;
    }
}
