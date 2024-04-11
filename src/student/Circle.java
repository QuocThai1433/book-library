package student;

import java.util.Scanner;

public class Circle implements  Shape{
    static private float dk;
    static private double pi= 3.14;
    static Scanner scanner = new Scanner(System.in);


    @Override
    public  Float DT() {
        System.out.println("Nhap duong kinh:");
        dk = scanner.nextFloat();
        System.out.println("Dien Tich Hinh Tron:"+dk*pi);
        return dk;
    }

    @Override
    public    Float CV() {
        return null;
    }


}

