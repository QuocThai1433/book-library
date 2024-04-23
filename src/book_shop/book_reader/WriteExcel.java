package book_shop.book_reader;

import book_shop.book.Book;
import book_shop.book.BookManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class WriteExcel {
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_BOOK_NAME = 1;
    public static final int COLUMN_INDEX_PUBLICATION_YEAR = 2;
    public static final int COLUMN_INDEX_QUANTITY = 3;
    public static final int COLUMN_INDEX_PRICE = 4;
    public static final int COLUMN_INDEX_RATING_AVERAGE = 5;
    public static final int COLUMN_INDEX_CATEGORY_ID = 6;
    private static CellStyle cellStyleFormatNumber = null;
    
    public static void writeExcel(List<Book> books, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
        
        Sheet sheet = workbook.createSheet("Books");
        int rowIndex = 0;
        rowIndex++;
        Row headerRow = sheet.createRow(0);
        String[] headers = {"id", "book_name", "publication_year", "quantity", "price", "rating_average", "category_id"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        for (Book book : books) {
            Row row = sheet.createRow(rowIndex);
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
    
    private static Workbook getWorkbook(String excelFilePath) {
        Workbook workbook;
        if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        
        return workbook;
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
        
        cell = row.createCell(COLUMN_INDEX_BOOK_NAME);
        cell.setCellValue(book.getBookName());
        
        cell = row.createCell(COLUMN_INDEX_PUBLICATION_YEAR);
        cell.setCellValue(book.getPublicationYear());
        
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(book.getQuantity());
        
        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellValue(book.getPrice());
        
        cell = row.createCell(COLUMN_INDEX_RATING_AVERAGE);
        cell.setCellValue(book.getRatingAverage());
        
        cell = row.createCell(COLUMN_INDEX_CATEGORY_ID);
        cell.setCellValue(book.getCategoryId());
    }
    
    public void exportToExel() throws IOException {
        final String excelFilePath = "books.xls";
        BookManager bookManager = new BookManager();
        writeExcel(bookManager.getListBook(), excelFilePath);
    }
}
