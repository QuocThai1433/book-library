package book_shop;

import book_shop.author.Author;
import book_shop.author.AuthorManager;
import book_shop.book.Book;
import book_shop.book.BookManager;
import book_shop.book_reader.BookReader;
import book_shop.book_reader.BookReaderManager;
import book_shop.book_reader.WriteExcel;
import book_shop.cart.Cart;
import book_shop.cart.CartManager;
import book_shop.category.Category;
import book_shop.category.CategoryManager;
import book_shop.publisher.Publisher;
import book_shop.publisher.PublisherManager;
import book_shop.rating.Rating;
import book_shop.rating.RatingManager;
import book_shop.readers.ReaderManager;
import book_shop.readers.Readers;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BookManager bookManager = new BookManager();
        Book book = new Book();
        BookReaderManager bookReaderManager = new BookReaderManager();
        BookReader bookReader = new BookReader();
        AuthorManager authorManager = new AuthorManager();
        Author author = new Author();
        PublisherManager publisherManager = new PublisherManager();
        Publisher publisher = new Publisher();
        ReaderManager readerManager = new ReaderManager();
        Readers readers = new Readers();
        RatingManager ratingManager = new RatingManager();
        Rating rating = new Rating();
        CartManager cartManager = new CartManager();
        Cart cart = new Cart();
        WriteExcel writeExcel = new WriteExcel();
        CategoryManager categoryManager = new CategoryManager();
        Category category = new Category();
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
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
        System.out.println("18.Export To Excel:");

        System.out.println("Input Choose:");
        choose = scanner.nextInt();
        switch (choose) {
            case 1:
                bookManager.create(book);
                break;
            case 2:
                bookManager.getListBook();
                break;
            case 3:
                bookManager.filter();
                break;
            case 4:
                authorManager.create(author);
                break;
            case 5:
                authorManager.getList();
                break;
            case 6:
                publisherManager.create(publisher);
                break;
            case 7:
                publisherManager.getList();
                break;
            case 8:
                readerManager.create(readers);
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
                cartManager.create(cart);
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
                writeExcel.expotToExel();
                break;
            default:
                break;
        }


    }
}
