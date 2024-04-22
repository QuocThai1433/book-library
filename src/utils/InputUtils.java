package utils;

import java.util.Scanner;
import java.util.function.Function;

public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);

    InputUtils() {
    }

    public static int inputId(Function<Integer, Boolean> checkExist) {
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

    public static Boolean checkValid(String number) {

    }
}
