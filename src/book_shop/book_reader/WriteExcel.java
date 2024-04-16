package book_shop.book_reader;

import book_shop.ConnectDB;
import book_shop.book.Book;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WriteExcel {
   static Connection connection= ConnectDB.getConnection();
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
    
    private static Workbook getWorkbook(String excelFilePath)  {
        Workbook workbook;
        if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        
        return workbook;
    }
    
    private static List<Book> getListBooks() {
        String query = "SELECT * FROM books;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                int publicationYear = rs.getInt("publication_year");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                float ratingAverage = rs.getFloat("rating_average");
                int categoryId = rs.getInt("category_id");
                Book book = new Book(id,bookName,publicationYear,quantity,price,ratingAverage, categoryId);
            books.add(book);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
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
    
    public  void expotToExel() throws IOException {
        final List<Book> books = getListBooks();
        final String excelFilePath = "D:/Downloads/books.xls";
        writeExcel(books, excelFilePath);
    }
}
