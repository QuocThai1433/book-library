package book_shop;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CheckFormatDate {
    Scanner scanner = new Scanner(System.in);

    public java.sql.Date formatDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateUtil = format.parse(date);
        return new java.sql.Date(dateUtil.getTime());


    }


    public boolean checkDate(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public String checkFormat() {
        String input = null;
        boolean flag1 = false;
        while (!flag1) {
            String number1 = scanner.nextLine();
            if (!checkDate(number1, "dd-MM-yyyy")) {
                System.out.println("Not format date!! Input Again:");
                continue;
            }
            input = number1;
            flag1 = true;

        }
        return input;
    }

    public boolean checkBorrowDate( Date returnSql, Date borrowSql) throws ParseException {
        while (!returnSql.after(borrowSql)) {
            if (!returnSql.after(borrowSql)) {
                System.out.println("Ngày trả không hợp lệ. Ngày trả phải sau ngày mượn ít nhất 1 ngày, nhập lại:");
                returnSql = formatDate(checkFormat());
            }
        }
        System.out.println("Ngày Trả Hợp Lệ!!");
        return true;
    }


}
