package by.bsu.first.command;

import by.bsu.first.entity.Movie;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.MovieLogic;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Пользователь on 01.12.2014.
 */
public class GetMovieCommand implements Command {
    private static final String PARAM_MOVIE_NAME = "movieName";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String movieName = request.getParameter(PARAM_MOVIE_NAME);
        try {
            Movie movie = MovieLogic.getMovieByName(movieName);
            request.setAttribute("movie",movie);
        }
        catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        String page = ConfigManager.getProperty("path.page.movie.change");
        return page;
    }
}
