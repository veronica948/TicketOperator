package by.bsu.first.command;

import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.LocaleManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private static final String PARAM_LOCALE = "locale";
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigManager.getProperty("path.page.index");
        String localeValue = request.getParameter(PARAM_LOCALE);
        Locale locale = LocaleManager.valueOf(localeValue.toUpperCase()).getLocale();
        session.setAttribute("locale",locale);
        return page;
    }
}
