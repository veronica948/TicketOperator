package by.bsu.first.command;

import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.UserLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.LocaleManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Пользователь on 08.12.2014.
 */
public class ChangePasswordCommand implements Command {
    private static final String PARAM_OLD_PASSWORD = "oldPassword";
    private static final String PARAM_NEW_PASSWORD = "newPassword";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.password.change");
        String oldPassword = request.getParameter(PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        if(Validation.isEmpty(oldPassword) || Validation.isEmpty(newPassword)) {
            request.setAttribute("passError", MessageManager.getMessage("message.password.empty", locale));
        } else {
            if(Validation.shortLength(newPassword)) {
                request.setAttribute("passError", MessageManager.getMessage("message.password.short",locale));
                return page;
            }
            if(Validation.containsSpace(newPassword)) {
                request.setAttribute("passError", MessageManager.getMessage("message.password.space",locale));
                return page;
            }
            User user = (User)session.getAttribute("user");
            int userId = user.getUserId();
            try {
                if(UserLogic.equalsToOldPassword(userId,oldPassword)) {
                    UserLogic.changePassword(userId,newPassword);
                    request.setAttribute("dataChanged", MessageManager.getMessage("message.data.changed", locale));
                    page = ConfigManager.getProperty("path.page.client");
                } else {
                    request.setAttribute("passError", MessageManager.getMessage("message.password.incorrect", locale));
                }
            } catch (LogicException e) {
                throw new CommandException(e.getCause());
            }
        }
        return page;
    }
}
