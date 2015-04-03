package by.bsu.first.command;

import by.bsu.first.entity.Seance;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.DAOException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.SeanceLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Пользователь on 24.11.2014.
 */
public class SearchCommand implements Command {
    private static final String PARAM_MOVIE_NAME = "movieName";
    private static final String PARAM_DATE = "seanceDate";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.seances");
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        String dateValue = request.getParameter(PARAM_DATE);
        String movieName = request.getParameter(PARAM_MOVIE_NAME);
        if(Validation.isEmpty(dateValue) && Validation.isEmpty(movieName)) {
            request.setAttribute("emptyFields",MessageManager.getMessage("message.empty.fields",locale));
            return page;
        }
        try {
            ArrayList<Seance> seances = SeanceLogic.getSeancesByDateAndMovie(movieName, dateValue);
            if(seances == null) {
                request.setAttribute("incorrectDateFormat", MessageManager.getMessage("message.incorrect.date.format",locale));
            } else {
                if (seances.size() == 0) {
                    request.setAttribute("notFound", MessageManager.getMessage("message.seances.not.exist", locale));
                } else {
                    request.setAttribute("seances", seances);
                }
            }
        }
        catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
