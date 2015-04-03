package by.bsu.first.command;

import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.MovieLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Locale;

/**
 * Created by Пользователь on 07.12.2014.
 */
public class ChangeMovieCommand implements Command {
    private static final String PARAM_MOVIE_ID = "movieId";
    private static final String PARAM_MOVIE_NAME = "movieName";
    private static final String PARAM_MOVIE_DESCRIPTION = "description";
    private static final String PARAM_MOVIE_COUNTRY = "country";
    private static final String PARAM_MOVIE_RELEASE_DATE = "releaseDate";
    private static final String PARAM_MOVIE_ACTORS = "actors";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.admin");
        try {
            int movieId = Integer.parseInt(request.getParameter(PARAM_MOVIE_ID));
            String movieName = request.getParameter(PARAM_MOVIE_NAME);
            String description = request.getParameter(PARAM_MOVIE_DESCRIPTION);
            String country = request.getParameter(PARAM_MOVIE_COUNTRY);
            String actors = request.getParameter(PARAM_MOVIE_ACTORS);
            String dateValue = request.getParameter(PARAM_MOVIE_RELEASE_DATE);
            Date releaseDate = null;
            Locale locale =  (Locale) request.getSession().getAttribute("locale");
            if(Validation.isEmpty(movieName)) {
                request.setAttribute("emptyFields", MessageManager.getMessage("message.empty.field", locale));
            } else {
                if (!Validation.isEmpty(dateValue)) {
                    if(Validation.isDate(dateValue)) {
                        releaseDate = Date.valueOf(dateValue);
                    }
                    else {
                        request.setAttribute("incorrectDataFormat",MessageManager.getMessage("message.incorrect.date.format",locale));
                        return page;
                    }
                }
                MovieLogic.changeMovie(movieId, movieName, description, country, actors, releaseDate);
                request.setAttribute("successfulOperation", MessageManager.getMessage("message.operation.success",locale));
            }
        }catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
