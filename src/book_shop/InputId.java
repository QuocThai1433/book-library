package book_shop;

import java.util.Scanner;
import java.util.function.Function;

public class InputId {
    Scanner scanner = new Scanner(System.in);
    CheckValid checkValid = new CheckValid();
    public int input(Function<Integer, Boolean> checkExist) {
        int id = 0;
        boolean flag = false;
        while (!flag) {
            String number = scanner.nextLine();
            if (!checkValid.isNumber(number)) {
                System.out.println("Not Number!! Input Again:");
                continue;
            }
            id = Integer.parseInt(number);
            if (Boolean.TRUE.equals(checkExist.apply(id))) {
                System.out.println("Id exist!! Input Again");
                continue;
            }
            flag = true;
        }
        return id;
    }
    public int inputNumber (int number) {
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
}
