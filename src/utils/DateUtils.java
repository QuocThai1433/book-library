package utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
    DateUtils() {
    }
    
    public static boolean checkFormat(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public static Date toSqlDate(String date, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            java.util.Date dateUtil = simpleDateFormat.parse(date);
            return new java.sql.Date(dateUtil.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Date toSqlDate(String date) {
        return toSqlDate(date, "dd-MM-yyyy");
    }
}
