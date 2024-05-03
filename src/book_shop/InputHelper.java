package book_shop;

import java.text.ParseException;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class InputHelper {
    Scanner scanner = new Scanner(System.in);
    ValidatorUtils checkValid = new ValidatorUtils();
    CheckFormatDate checkFormatDate = new CheckFormatDate();

    public int input(IntPredicate checkExist) {
        int id = 0;
        boolean flag = false;
        while (!flag) {
            String number = scanner.nextLine();
            if (!checkValid.isNumber(number)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            id = Integer.parseInt(number);
            if (Boolean.TRUE.equals(checkExist.test(id))) {
                System.out.println("Id exist!! Input Again");

            } else {
                flag = true;
            }
        }
        return id;
    }
    public int inputCheckExistId(IntPredicate checkExist) {
        int id = 0;
        boolean flag = false;
        while (!flag) {
            String number = scanner.nextLine();
            if (!checkValid.isNumber(number)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            id = Integer.parseInt(number);
            if (!Boolean.TRUE.equals(checkExist.test(id))) {
                System.out.println("Id Not Exist!! Input Again");

            } else {
                flag = true;
            }
        }
        return id;


    }


    public int inputNumber() {
        int number = 0;
        boolean flag = false;
        while (!flag) {
            String number1 = scanner.nextLine();
            if (!checkValid.isNumber(number1)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            number = Integer.parseInt(number1);
            flag = true;
        }
        return number;
    }

    public float inputNumberFloat() {
        float number = 0;
        boolean flag = false;
        while (!flag) {
            String number1 = scanner.nextLine();
            if (!checkValid.isNumberFloat(number1)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            number = Float.parseFloat(number1);
            flag = true;
        }
        return number;
    }

    public java.sql.Date inputDate() throws ParseException {
        String borrowDate = checkFormatDate.checkFormat();
        return checkFormatDate.parseDate(borrowDate);
    }


}
