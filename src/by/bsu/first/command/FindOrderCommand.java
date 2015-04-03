package by.bsu.first.command;

import by.bsu.first.entity.Order;
import by.bsu.first.entity.Seance;
import by.bsu.first.entity.Ticket;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.OrderLogic;
import by.bsu.first.logic.SeanceLogic;
import by.bsu.first.logic.TicketLogic;
import by.bsu.first.logic.UserLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Пользователь on 07.12.2014.
 */
public class FindOrderCommand implements Command {
    private static final String PARAM_ORDER_ID = "orderId";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.order");
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        try {
            String orderIdString = request.getParameter(PARAM_ORDER_ID);
            if(!Validation.isNumber(orderIdString)) {
                request.setAttribute("incorrectNumberFormat", MessageManager.getMessage("message.incorrect.number.format",locale));
                page = ConfigManager.getProperty("path.page.search.orders");
            } else {
                int orderId = Integer.parseInt(orderIdString);
                Order order = OrderLogic.findOrderById(orderId);
                if (order != null) {
                    int ticketId = order.getTicketId();
                    int userId = order.getUserId();
                    Seance seance = SeanceLogic.getSeanceByTicketId(ticketId);
                    Ticket ticket = TicketLogic.getTicketById(ticketId);
                    ArrayList<String> info = UserLogic.getInfoAboutUser(userId);
                    request.setAttribute("firstName", info.get(0));
                    request.setAttribute("lastName", info.get(1));
                    request.setAttribute("ticket", ticket);
                    request.setAttribute("seance", seance);
                    request.setAttribute("order", order);

                } else {
                    request.setAttribute("notFound", MessageManager.getMessage("message.order.not.exist", locale));
                    page = ConfigManager.getProperty("path.page.search.orders");
                }
            }
        }
        catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
