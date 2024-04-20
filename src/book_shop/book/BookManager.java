package book_shop.book;


import book_shop.CheckValid;
import book_shop.ConnectDB;
import book_shop.InputId;
import book_shop.category.Category;
import com.mysql.cj.jdbc.SuspendableXAConnection;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.function.Function;

public class BookManager {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Book> books = new ArrayList<>();
    CheckValid check = new CheckValid();
    Connection connection = ConnectDB.getConnection();
    InputId inputId = new InputId();



    public Book inputBook() {
        String number = null;
        System.out.println("Input Book ID:");
        int id = inputId.input((authorId) -> check.checkExistId(authorId, "author"));

        System.out.println("Input Book Name:");
        String bookName = scanner.nextLine();

        System.out.println("Input Publication Year:");
        int publicationYear = scanner.nextInt();

        System.out.println("Input quantity:");
        int quantity = scanner.nextInt();

        System.out.println("Input Price:");
        float price = scanner.nextFloat();
        System.out.println("Input Rating Average:");
        float ratingAverage = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Input category Id:");
        int categoryId = inputId.input((authorId) -> check.checkExistId(authorId, "author"));
        return new Book(id, bookName, publicationYear, quantity, price, ratingAverage, categoryId);
    }


    public float getAverage(float id) {
        float kq = 0;
        String query = "select * from books where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kq = rs.getFloat("rating_average");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;

    }

    public void updateStar(float newRating, float bookId) {
        float ratingAverage = getAverage(bookId);
        String query = "update books set rating_average = ? where id = ? ";
        try {
            PreparedStatement ps = ConnectDB.connectDB.prepareStatement(query);
            ratingAverage = (ratingAverage + newRating) / 2;
            ps.setFloat(2, bookId);
            ps.setFloat(1, ratingAverage);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int create(Book book) {
        book = inputBook();
        Category category = new Category();
        String query = "insert into books value  (?,?,?,?,?,?,?)";
        int kq = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getBookName());
            statement.setInt(3, book.getPublicationYear());
            statement.setInt(4, book.getQuantity());
            statement.setFloat(5, book.getPrice());
            statement.setFloat(6, book.getRatingAverage());
            if (check.checkExistId(book.getCategoryId(),"category"))
            {
                statement.setInt(7, book.getCategoryId());

            }
            else
            {
                statement.setObject(7, null);

            }
            kq = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;

    }

    public List<Book> getListBook() {
        List<Book> bookList = new ArrayList<>();
        String query = "select * from books";
        try {

            PreparedStatement ps = ConnectDB.connectDB.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                int publicationYear = rs.getInt("publication_year");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                float ratingAverage = rs.getFloat("rating_average");
                int categoryId = rs.getInt("category_id");
                System.out.println(id +" | "+ bookName +" | "+ publicationYear +" | "+ quantity +" | "+ price +" | "+ ratingAverage +" | "+ categoryId );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public int filter() {
        int kq = 0;
        String query = "select * from books where category_id = ?";
        try {
            PreparedStatement ps = ConnectDB.connectDB.prepareStatement(query);
            System.out.println("InputCategory: ");
            int id = inputId.input((authorId) -> check.checkExistId(authorId, "rating"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                String publicationYear = rs.getString("publication_year");
                String quantity = rs.getString("quantity");
                int price = rs.getInt("price");
                int ratingAverage = rs.getInt("rating_average");
                float categoryId = rs.getFloat("category_id");

                System.out.println(id +" | "+ bookName +" | "+ publicationYear +" | "+ quantity +" | "+ price +" | "+ ratingAverage +" | "+ categoryId );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }


//    public static List<Book> getListBook() {
//        for (Book book : books
//        ) {
//            outputBook(book);
//        }
//        return null;
//    }


//    public static void writeFile(Book book) {
//
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("book.txt");
//            ObjectOutputStream f = new ObjectOutputStream(fileOutputStream);
//            f.writeObject(books);
//            f.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void readFile() {
//        try {
//            FileInputStream inputStream = new FileInputStream("book.txt");
//            ObjectInput objectInput = new ObjectInputStream(inputStream);
//            List<Book> bookList = (List<Book>) objectInput.readObject();
//            for (int i = 0; i < bookList.size(); i++) {
//                System.out.println(outputBook(bookList.get(i)));
//            }
//            objectInput.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void filterBook(List<Book> books) {
////        String nameBook;
////        inputList();
////        System.out.println("Nhap sach can tim: ");
////        nameBook = scanner.nextLine();
////
////        for (Book book : books) {
////            if (nameBook.equals(book.getTenSach())) {
////                System.out.println("Sach ban vua tim co trong danh sach:" + book.getTenSach());
////            } else {
////                System.out.println("Sach khong ton tai!!");
////            }
////
////        }
//    }

//        public static void updateBook () {
//
//
//            System.out.println("Nhap ma sach can sua:");
//            int id = scanner.nextInt();
//
//            for (Book book1 : books) {
//                if (id == book1.getMaSach()) {
//                    System.out.println("Moi ban sua thong tin:\n");
//
//                    book1.setMaSach(inputBook().id);
//                    book1.setTenSach(inputBook().tenSach);
//                    book1.setTacGia(inputBook().tacGia);
//                    book1.setNhaXuatBan(inputBook().nhaXuatBan);
//                    book1.setNamXuatBan(inputBook().namXuatBan);
//                    book1.setSoLuong(inputBook().soLuong);
//                    book1.setGiaBan(inputBook().giaBan);
//                } else {
//                    System.out.println("Ma Sach khong ton tai");
//                }


//}}


    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.getListBook();


    }
}

