package by.bsu.first.command;

import by.bsu.first.dao.MovieDAO;
import by.bsu.first.dao.SeanceDAO;
import by.bsu.first.dao.UserDAO;
import by.bsu.first.entity.Movie;
import by.bsu.first.entity.Seance;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.MovieLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.Message;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.pool.Pool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Пользователь on 11.11.2014.
 */
public class ShowMoviePageCommand implements Command {
    private static final String PARAM_MOVIE_ID = "movieId";
    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        String page = ConfigManager.getProperty("path.page.movie");
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        int movieId = Integer.parseInt(request.getParameter(PARAM_MOVIE_ID));
        try {
            ArrayList<Seance> seances = MovieLogic.getSeancesByMovieId(movieId);
            Movie movie = MovieLogic.getMovieById(movieId);
            if(movie == null) {
                request.setAttribute("noMovie", MessageManager.getMessage("message.movie.not.exist",locale));
                return page;
            } else {
                request.setAttribute("movie", movie);
            }
            if (seances.size() != 0) {
                request.setAttribute("seances", seances);
            } else {
                request.setAttribute("noSeances", MessageManager.getMessage("message.seances.not.exist", locale));
            }

        }
        catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
