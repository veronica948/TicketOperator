package by.bsu.first.command;

import by.bsu.first.entity.Seance;
import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.OrderLogic;
import by.bsu.first.logic.SeanceLogic;
import by.bsu.first.logic.TicketLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Пользователь on 04.12.2014.
 */
public class MakeOrderCommand implements Command {
    private static final String PARAM_TICKET_ID = "ticketId";
    private static final String PARAM_USER = "user";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.order.info");
        HttpSession session = request.getSession();
        try {
            int userId = ((User) session.getAttribute(PARAM_USER)).getUserId();
            int ticketId = Integer.parseInt(request.getParameter(PARAM_TICKET_ID));
            if(!OrderLogic.existOrder(ticketId)) {
                if(SeanceLogic.getSeanceByTicketId(ticketId) == null) {
                    request.setAttribute("impossibleOperation", MessageManager.getMessage("message.impossible.order", (Locale)request.getSession().getAttribute("locale")));
                } else {
                    int orderId = OrderLogic.makeOrder(ticketId, userId);
                    request.setAttribute("orderNumber", orderId);
                }
            } else {
                request.setAttribute("existOrder", MessageManager.getMessage("message.order.exist",(Locale)session.getAttribute("locale")));
            }
        } catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
