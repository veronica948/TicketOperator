package by.bsu.first.command;

import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.DAOException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 07.10.2014.
 */
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}
