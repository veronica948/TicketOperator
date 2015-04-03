package by.bsu.first.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 07.10.2014.
 */
public class ActionFactory {
    public static Command defineCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        Command current = null;
        if(commandName == null) {
            return new EmptyCommand();
        }
        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = type.getCommand();
        } catch (IllegalArgumentException e) {
            current = new EmptyCommand();
        }
        return current;
    }
}
