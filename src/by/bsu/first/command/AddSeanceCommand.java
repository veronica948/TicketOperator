package by.bsu.first.command;

import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.SeanceLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Created by Пользователь on 27.11.2014.
 */
public class AddSeanceCommand implements Command {
    private static Logger logger = Logger.getLogger(AddSeanceCommand.class);
    private static final String PARAM_MOVIE_NAME = "movieName";
    private static final String PARAM_SEANCE_DATE = "seanceDate";
    private static final String PARAM_SEANCE_TIME = "seanceTime";
    private static final String PARAM_SEANCE_PRICE = "price";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.admin");
        String movieName = request.getParameter(PARAM_MOVIE_NAME);
        String timeString = request.getParameter(PARAM_SEANCE_TIME);
        String dateValue = request.getParameter(PARAM_SEANCE_DATE);
        String priceValue = request.getParameter(PARAM_SEANCE_PRICE);
        Locale locale = (Locale)request.getSession().getAttribute("locale");
        if(Validation.isEmpty(movieName) || Validation.isEmpty(timeString) || Validation.isEmpty(dateValue) || Validation.isEmpty(priceValue)) {
            request.setAttribute("emptyFields", MessageManager.getMessage("message.empty.field",locale));
            return page;
        }
        if(!Validation.isDate(dateValue) || !Validation.isTime(timeString) || !Validation.isNumber(priceValue)) {
            request.setAttribute("incorrectDataFormat", MessageManager.getMessage("message.incorrect.data.format",locale));
            return page;
        }
        try {
            SimpleDateFormat sdfm = new SimpleDateFormat("HH:mm");
            Date date = Date.valueOf(dateValue);
            Time time = new Time(sdfm.parse(timeString).getTime());
            int price = Integer.parseInt(priceValue);
            if(price <= 0) {
                request.setAttribute("incorrectPrice", MessageManager.getMessage("message.price.incorrect",locale));
                return page;
            }
            if((LocalDate.now().compareTo(date.toLocalDate()) == 0 && LocalTime.now().isAfter(time.toLocalTime())) || LocalDate.now().isAfter(date.toLocalDate())) {
                request.setAttribute("incorrectDateTime", MessageManager.getMessage("message.date.time.incorrect",locale));
                return page;
            }
            SeanceLogic.addSeance(movieName, date, time, price);
            request.setAttribute("successfulOperation", MessageManager.getMessage("message.operation.success",locale));
        }
        catch (LogicException e) {
            logger.error(e.getMessage(),e.getCause());
            request.setAttribute("impossibleOperation",MessageManager.getMessage("message.seance.add.impossible", locale));
        }
        catch (ParseException e) {
            throw new CommandException("impossible convert time",e);
        }
        return page;
    }
}
