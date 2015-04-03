package by.bsu.first.command;

import by.bsu.first.entity.Movie;
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
public class GetMoviesCommand implements Command {
    private static final String PARAM_PAGE = "page";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        String page = null;
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        switch (pageNumber) {
            case 0: {
                page = ConfigManager.getProperty("path.page.movie.delete");
                break;
            }
            case 1: {
                page = ConfigManager.getProperty("path.page.seance.add");
                break;
            }
            case 2: {
                page = ConfigManager.getProperty("path.page.movie.change.list");
                break;
            }
            default: {
                page = ConfigManager.getProperty("path.page.admin");
                return page;
            }
        }
        try {
            ArrayList<String> movieNames = MovieLogic.getAllMovieNames();
            if (movieNames.size() != 0) {
                request.setAttribute("movieList", movieNames);
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
