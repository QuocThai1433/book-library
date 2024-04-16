package book_shop.book_reader;

import book_shop.book.Book;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteExcel {
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_BOOKNAME = 1;
    public static final int COLUMN_INDEX_PUBLICATION_YEAR = 2;
    public static final int COLUMN_INDEX_QUANTITY = 3;
    public static final int COLUMN_INDEX_PRICE = 4;
    public static final int COLUMN_INDEX_RATING_AVERAGE = 5;
    public static final int COLUMN_INDEX_CATEGORY_ID = 6;
    private static CellStyle cellStyleFormatNumber = null;
    static List<Book> books = new ArrayList<>();
    
    public static void writeExcel(List<Book> books, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
        
        Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name
        int rowIndex = 0;
        // Write data
        rowIndex++;
        for (Book book : books) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(book, row);
            rowIndex++;
        }
        
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
    
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
    
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook;
        if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        
        return workbook;
    }
    
    private static List<Book> getBooks() {
        List<Book> listBook = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Book book = new Book(i, "Book " + i, i * 2, i * 1000);
            listBook.add(book);
        }
        return listBook;
    }
    
    private static void writeBook(Book book, Row row) {
        if (cellStyleFormatNumber == null) {
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(book.getId());
        
        cell = row.createCell(COLUMN_INDEX_BOOKNAME);
        cell.setCellValue(book.getBookName());
        
        cell = row.createCell(COLUMN_INDEX_PUBLICATION_YEAR);
        cell.setCellValue(book.getPublicationYear());
        cell.setCellStyle(cellStyleFormatNumber);
        
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(book.getQuantity());
        cell = row.createCell(COLUMN_INDEX_PRICE);
        
        cell.setCellValue(book.getPrice());
        cell = row.createCell(COLUMN_INDEX_RATING_AVERAGE);
        cell.setCellValue(book.getRatingAverage());
        cell = row.createCell(COLUMN_INDEX_CATEGORY_ID);
        cell.setCellValue(book.getCategoryId());
        cell.setCellStyle(cellStyleFormatNumber);
        int currentRow = row.getRowNum() + 1;
        String columnPrice = CellReference.convertNumToColString(COLUMN_INDEX_PRICE);
        String columnQuantity = CellReference.convertNumToColString(COLUMN_INDEX_QUANTITY);
        cell.setCellFormula(columnPrice + currentRow + "*" + columnQuantity + currentRow);
    }
    
    public static void main(String[] args) throws IOException {
        final List<Book> books = getBooks();
        final String excelFilePath = "/Users/huyhanhat/Documents/My_Projects/book-library/books.xls";
        writeExcel(books, excelFilePath);
    }
}
