package by.bsu.first.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Пользователь on 14.11.2014.
 */
public class MessageManager {
    private MessageManager() {}
    public static String getMessage(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.message", locale);
        return bundle.getString(key);
    }
    public static String getMessage(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.message", new Locale("en","US"));
        return bundle.getString(key);
    }
}
