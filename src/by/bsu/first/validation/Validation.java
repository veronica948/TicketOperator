package by.bsu.first.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Пользователь on 16.12.2014.
 */
public class Validation {
    private static final int MIN_STRING_SIZE = 4;
    public static boolean isNumber(String stringNumber) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(stringNumber);
        return matcher.matches();
    }
    public static boolean isDate(String stringDate) {
        Pattern pattern = Pattern.compile("\\d{4}(-)\\d{2}(-)\\d{2}");
        Matcher matcher = pattern.matcher(stringDate);
        return matcher.matches();
    }
    public static boolean isTime(String stringTime) {
        Pattern pattern = Pattern.compile("\\d{2}(:)\\d{2}");
        Matcher matcher = pattern.matcher(stringTime);
        return matcher.matches();
    }
    public static boolean shortLength(String string) {
        if(string.length() < MIN_STRING_SIZE) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isEmpty(String string) {
         if(string == null) {
             return true;
         } else {
             if(string.isEmpty()) {
                 return true;
             } else {
                 return false;
             }
         }
    }
    public static boolean isEmail(String string) {
        String regex = "(\\w{6,})@(\\w+\\.)([a-z]{2,4})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    public static boolean containsSpace(String string) {
        return string.contains(" ");
    }
}
