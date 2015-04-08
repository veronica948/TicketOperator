package by.bsu.first.command;

import by.bsu.first.entity.Seance;
import by.bsu.first.entity.Ticket;
import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.SeanceLogic;
import by.bsu.first.logic.TicketLogic;
import by.bsu.first.logic.UserLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Locale;

public class ShowOrderPageCommand implements Command {
    private static final String PARAM_TICKET_ID = "ticketId";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.client.order");
        try {
            int ticketId = Integer.parseInt(request.getParameter(PARAM_TICKET_ID));
            Seance seance = SeanceLogic.getSeanceByTicketId(ticketId);
            if(seance == null) {
                request.setAttribute("impossibleOperation", MessageManager.getMessage("message.impossible.order", (Locale)request.getSession().getAttribute("locale")));
                return ConfigManager.getProperty("path.page.order.info");
            }
            Ticket ticket = TicketLogic.getTicketById(ticketId);


            request.setAttribute("ticket",ticket);
            request.setAttribute("seance",seance);
            User user = (User)request.getSession().getAttribute("user");
            ArrayList<String> info = UserLogic.getInfoAboutUser(user.getUserId());
            request.setAttribute("firstName",info.get(0));
            request.setAttribute("lastName",info.get(1));
        } catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
