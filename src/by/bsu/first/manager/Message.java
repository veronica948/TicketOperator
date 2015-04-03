package by.bsu.first.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Пользователь on 07.10.2014.
 */
public enum Message {
    EN(ResourceBundle.getBundle("resources.message",new Locale("en","EN"))),
    RU(ResourceBundle.getBundle("resources.message", new Locale("ru", "RU")));
    private ResourceBundle bundle;
    Message(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    public String getMessage(String key) {
        return bundle.getString(key);
    }

}
