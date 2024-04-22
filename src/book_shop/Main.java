package book_shop;

import book_shop.author.AuthorManager;
import book_shop.book.BookManager;
import book_shop.book_reader.BookReaderManager;
import book_shop.book_reader.WriteExcel;
import book_shop.cart.CartManager;
import book_shop.category.Category;
import book_shop.category.CategoryManager;
import book_shop.publisher.PublisherManager;
import book_shop.rating.RatingManager;
import book_shop.readers.ReaderManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BookManager bookManager = new BookManager();
        BookReaderManager bookReaderManager = new BookReaderManager();
        AuthorManager authorManager = new AuthorManager();
        PublisherManager publisherManager = new PublisherManager();
        ReaderManager readerManager = new ReaderManager();
        RatingManager ratingManager = new RatingManager();
        CartManager cartManager = new CartManager();
        WriteExcel writeExcel = new WriteExcel();
        CategoryManager categoryManager = new CategoryManager();
        Category category = new Category();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        int choose;
        do {
            System.out.println("1.Add Books:");
            System.out.println("2.Show Books:");
            System.out.println("3.Filter Books:");
            System.out.println("4.Add Author:");
            System.out.println("5.Show Author:");
            System.out.println("6.Add Publisher:");
            System.out.println("7.Show Publisher:");
            System.out.println("8.Add Readers:");
            System.out.println("9.Show Readers:");
            System.out.println("10.Statistical Books:");
            System.out.println("11.Show Total Books:");
            System.out.println("12.Show the borrowing and returning status of each Books:");
            System.out.println("13.Show lists based on user ratings:");
            System.out.println("14.Add Cart:");
            System.out.println("15.Show Cart:");
            System.out.println("16.Add Category:");
            System.out.println("17.Show Category:");
            System.out.println("18.Add Rating:");
            System.out.println("19.Export To Excel:");
            System.out.println("20.ShowBorrowBooks:");
            System.out.println("Input Choose:");
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    bookManager.create();
                    break;
                case 2:
                    bookManager.getListBook();
                    break;
                case 3:
                    bookManager.filter();
                    break;
                case 4:
                    authorManager.create();
                    break;
                case 5:
                    authorManager.getList();
                    break;
                case 6:
                    publisherManager.create();
                    break;
                case 7:
                    publisherManager.getList();
                    break;
                case 8:
                    readerManager.create();
                    break;
                case 9:
                    readerManager.getList();
                    break;
                case 10:
                    bookReaderManager.statistical();
                    break;
                case 11:
                    bookReaderManager.totalBook();
                    break;
                case 12:
                    bookReaderManager.lateLog();
                    break;
                case 13:
                    ratingManager.getList();
                    break;
                case 14:
                    cartManager.create();
                    break;
                case 15:
                    cartManager.getList();
                    break;
                case 16:
                    categoryManager.create(category);
                    break;
                case 17:
                    categoryManager.getList();
                    break;
                case 18:
                    ratingManager.create();
                    break;
                case 19:
                    writeExcel.expotToExel();
                    break;
                case 20:
                    bookReaderManager.showBorrowBooks();
                    break;
                default:
                    break;
            }

            if (!exit) {
                System.out.print("Bạn có muốn tiếp tục chương trình không? (y/n): ");
                String continueChoice = scanner.next();
                if (!continueChoice.equalsIgnoreCase("Y") &&
                    !continueChoice.equalsIgnoreCase("y") &&
                    !continueChoice.equalsIgnoreCase("Yes") &&
                    !continueChoice.equalsIgnoreCase("yes")) {
                    exit = true;
                }
            }

        } while (!exit);
    }
}
