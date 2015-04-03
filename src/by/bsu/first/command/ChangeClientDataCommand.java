package by.bsu.first.command;

import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.UserLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Пользователь on 08.12.2014.
 */
public class ChangeClientDataCommand implements Command {
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_LASTNAME = "lastname";
    private static final String PARAM_EMAIL = "email";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.client");
        String name = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);
        String email = request.getParameter(PARAM_EMAIL);
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();
        if(Validation.isEmpty(lastname) || Validation.isEmpty(name)) {
            request.setAttribute("nameEmpty", MessageManager.getMessage("message.name.empty", locale));
        }  else {
            try {
                if(!Validation.isEmpty(email)) {
                    if(!Validation.isEmail(email)) {
                        request.setAttribute("incorrectEmail", MessageManager.getMessage("message.incorrect.email.format", locale));
                        return page;
                    }
                }
                UserLogic.changeData(userId, lastname, name, email);
                request.setAttribute("dataChanged", MessageManager.getMessage("message.data.changed", locale));
            } catch (LogicException e) {
                throw new CommandException(e.getCause());
            }
        }
        return page;
    }
}
