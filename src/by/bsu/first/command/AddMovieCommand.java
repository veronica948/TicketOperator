package by.bsu.first.command;

import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.MovieLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Locale;

public class AddMovieCommand implements Command {
    private static Logger logger = Logger.getLogger(AddMovieCommand.class);
    private static final String PARAM_MOVIE_NAME = "movieName";
    private static final String PARAM_MOVIE_DESCRIPTION = "description";
    private static final String PARAM_MOVIE_COUNTRY = "country";
    private static final String PARAM_MOVIE_RELEASE_DATE = "releaseDate";
    private static final String PARAM_MOVIE_ACTORS = "actors";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.movie.add");
        String movieName = request.getParameter(PARAM_MOVIE_NAME);
        String description = request.getParameter(PARAM_MOVIE_DESCRIPTION);
        String country = request.getParameter(PARAM_MOVIE_COUNTRY);
        String dateValue = request.getParameter(PARAM_MOVIE_RELEASE_DATE);
        Date releaseDate = null;
        Locale locale =  (Locale) request.getSession().getAttribute("locale");
        if(Validation.isEmpty(movieName)) {
            request.setAttribute("emptyFields", MessageManager.getMessage("message.empty.field", locale));
        } else {
            if(!Validation.isEmpty(dateValue)) {
                if(Validation.isDate(dateValue)) {
                    releaseDate = Date.valueOf(dateValue);
                }
                else {
                    request.setAttribute("incorrectDataFormat",MessageManager.getMessage("message.incorrect.date.format",locale));
                    return page;
                }
            }
            String actors = request.getParameter(PARAM_MOVIE_ACTORS);
            try {
                MovieLogic.insertMovie(movieName, description, country, actors, releaseDate);
                request.setAttribute("successfulOperation", MessageManager.getMessage("message.operation.success",locale));
                page = ConfigManager.getProperty("path.page.admin");
            } catch (LogicException e) {
                logger.error(e.getMessage(),e.getCause());
                request.setAttribute("impossibleOperation",MessageManager.getMessage("message.movie.add.impossible", locale));
            }
        }
        return page;
    }
}
