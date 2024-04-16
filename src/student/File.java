package student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class File {
    public static void main(String[] args) throws  Exception {
        Scanner scanner= new Scanner( System.in);
        System.out.println("Nhap ngay:");
        String ngay= scanner.nextLine();
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter1.parse(ngay);
        System.out.println(date);
    }
}
