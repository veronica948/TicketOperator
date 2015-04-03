package by.bsu.first.command;

import by.bsu.first.entity.Movie;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.DAOException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.MovieLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Пользователь on 27.11.2014.
 */
public class DeleteMovieCommand implements Command {
    private static final String PARAM_MOVIE_NAME = "movieName";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String movieName = request.getParameter(PARAM_MOVIE_NAME);
        String page = ConfigManager.getProperty("path.page.admin");
        try {
            if(!MovieLogic.existOrders(movieName)) {
                MovieLogic.deleteMovie(movieName);
                request.setAttribute("successfulOperation",
                        MessageManager.getMessage("message.operation.success",(Locale) request.getSession().getAttribute("locale")));
            } else {
                request.setAttribute("impossibleOperation",
                        MessageManager.getMessage("message.delete.movie.impossible",(Locale)request.getSession().getAttribute("locale")));
            }
        }
        catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
