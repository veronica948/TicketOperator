package by.bsu.first.command;

import by.bsu.first.entity.Movie;
import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.DAOException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.MovieLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Пользователь on 30.11.2014.
 */
public class ShowMoviesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        String page = ConfigManager.getProperty("path.page.movies");
        try {
            ArrayList<Movie> movies = MovieLogic.getAllMovies();
            if (movies.size() != 0) {
                request.setAttribute("movieList", movies);
            } else {
                request.setAttribute("noMovies", MessageManager.getMessage("message.movies.not.exist", locale));
            }
        }
        catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
