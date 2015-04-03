package by.bsu.first.manager;
import java.util.Locale;

/**
 * Created by Пользователь on 14.11.2014.
 */
    public enum LocaleManager {
        EN(new Locale("en","US")),
        RU(new Locale("ru", "RU"));
        private Locale locale;
        LocaleManager(Locale locale) {
            this.locale = locale;
        }
        public Locale getLocale() {
            return locale;
        }
    }
