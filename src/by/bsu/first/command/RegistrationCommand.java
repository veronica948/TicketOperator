package by.bsu.first.command;

import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.RegistrationLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Пользователь on 16.11.2014.
 */
public class RegistrationCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_LASTNAME = "lastname";
    private static final String PARAM_EMAIL = "email";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);
        String name = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);
        String email = request.getParameter(PARAM_EMAIL);
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        boolean errorExist = false;
        if (Validation.isEmpty(loginValue) || Validation.isEmpty(passValue)) {
            request.setAttribute("loginPassError", MessageManager.getMessage("message.login.empty",locale));
            errorExist = true;
        }
        if(Validation.isEmpty(name) || Validation.isEmpty(lastname)) {
            request.setAttribute("nameEmpty",MessageManager.getMessage("message.name.empty",locale));
            errorExist = true;
        }
        if(Validation.shortLength(loginValue)) {
            request.setAttribute("shortLogin", MessageManager.getMessage("message.login.short",locale));
            errorExist = true;
        }
        if(Validation.containsSpace(loginValue)) {
            request.setAttribute("loginError", MessageManager.getMessage("message.login.incorrect",locale));
            errorExist = true;
        }
        if(Validation.shortLength(passValue)) {
            request.setAttribute("shortPassword", MessageManager.getMessage("message.password.short",locale));
            errorExist = true;
        }
        if(Validation.containsSpace(passValue)) {
            request.setAttribute("passError", MessageManager.getMessage("message.password.space",locale));
            errorExist = true;
        }
        if(!Validation.isEmpty(email)) {
            if(!Validation.isEmail(email)) {
                request.setAttribute("incorrectEmail", MessageManager.getMessage("message.incorrect.email.format", locale));
                errorExist = true;
            }
        }
        try {
            if (RegistrationLogic.loginAlreadyExist(loginValue)) {
                request.setAttribute("loginExistError", MessageManager.getMessage("message.login.existerror", locale));
                errorExist = true;
            }
            if (!errorExist) {
                page = ConfigManager.getProperty("path.page.main");
                User user = RegistrationLogic.insertUser(loginValue, passValue, 1, lastname, name, email);
                session.setAttribute("user", user);
                request.setAttribute("welcome", MessageManager.getMessage("message.welcome", locale));
            } else {
                page = ConfigManager.getProperty("path.page.registration");
            }
            return page;
        }
        catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
    }
}
