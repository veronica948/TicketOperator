package by.bsu.first.command;

import by.bsu.first.entity.Ticket;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.SeanceLogic;
import by.bsu.first.logic.TicketLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Пользователь on 03.12.2014.
 */
public class ShowTicketsCommand implements Command {
    private static final String PARAM_SEANCE_ID = "seanceId";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.tickets");
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        int seanceId = Integer.parseInt(request.getParameter(PARAM_SEANCE_ID));
        try {
            if(SeanceLogic.isOldSeance(seanceId)) {
                request.setAttribute("oldSeance",MessageManager.getMessage("message.old.seance",locale));
                return ConfigManager.getProperty("path.page.seances");
            }
            ArrayList<Ticket> tickets = TicketLogic.getTicketsBySeanceId(seanceId);
            if(tickets.size() != 0) {
                request.setAttribute("tickets", tickets);
            } else {
                request.setAttribute("notFound", MessageManager.getMessage("message.tickets.not.found",locale));
            }
        } catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
