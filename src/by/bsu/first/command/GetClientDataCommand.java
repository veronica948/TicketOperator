package by.bsu.first.command;

import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.UserLogic;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Пользователь on 08.12.2014.
 */
public class GetClientDataCommand implements Command {
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.data.change");
        try {
            User user = (User)request.getSession().getAttribute("user");
            ArrayList<String> info = UserLogic.getInfoAboutUser(user.getUserId());
            request.setAttribute("firstname",info.get(0));
            request.setAttribute("lastname",info.get(1));
            request.setAttribute("email",info.get(2));
        } catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
