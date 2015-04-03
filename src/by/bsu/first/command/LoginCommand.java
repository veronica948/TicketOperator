package by.bsu.first.command;

import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.LoginLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Пользователь on 07.10.2014.
 */

public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        if(Validation.isEmpty(loginValue) || Validation.isEmpty(passValue)) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("message.login.empty", locale));
            page = ConfigManager.getProperty("path.page.login");
        }  else {
            try {
                User user = LoginLogic.checkUser(loginValue, passValue);
                if (user != null) {
                    session.setAttribute("user", user);
                    int role = user.getRole();
                    if (role == 0) {
                        page = ConfigManager.getProperty("path.page.admin");
                    } else {
                        page = ConfigManager.getProperty("path.page.main");
                    }
                } else {
                    request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("message.loginerror", locale));
                    page = ConfigManager.getProperty("path.page.login");
                }
            }
            catch (LogicException e) {
                throw new CommandException(e.getCause());
            }
        }
        return page;
    }
}
