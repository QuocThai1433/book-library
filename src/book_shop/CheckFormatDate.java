package book_shop;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckFormatDate {  public  boolean checkDate(String date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    dateFormat.setLenient(false);
    try {
        dateFormat.parse(date);
        return true;
    } catch (ParseException e) {
        return false;
    }
}


}
