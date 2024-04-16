package book_shop.book_reader;

import java.sql.Date;

public class BookReader {
    private int book_id;
    private int reader_id;
    private Date borrow_date;
    private Date return_date;


    private Date return_datef;

    public BookReader() {
    }

    public BookReader(int bookId, int readeId, Date borrowDate, Date returnDate) {
        super();
        this.book_id = bookId;
        this.reader_id = readeId;
        this.borrow_date = borrowDate;
        this.return_date = returnDate;
    }


    public BookReader(Date borrow_date, Date return_date) {
        super();
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }

    public void setReturn_datef(Date return_datef) {
        this.return_datef = return_datef;
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

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getReader_id() {
        return reader_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }
}
