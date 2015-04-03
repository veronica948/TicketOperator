package by.bsu.first.command;

import by.bsu.first.entity.User;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.OrderLogic;
import by.bsu.first.logic.TicketLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Пользователь on 07.12.2014.
 */
public class ChangeOrderStatusCommand implements Command {
    private static final String PARAM_ORDER_ID = "orderId";
    private static final String PARAM_STATUS = "orderStatus";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int role = user.getRole();
        if(role == 0) {
            page = ConfigManager.getProperty("path.page.search.orders");
        } else {
            page = ConfigManager.getProperty("path.page.client");
        }
        try {
            String orderIdString = request.getParameter(PARAM_ORDER_ID);
            if(!Validation.isNumber(orderIdString)) {
                request.setAttribute("incorrectNumberFormat", MessageManager.getMessage("message.incorrect.number.format",(Locale) request.getSession().getAttribute("locale")));
            } else {
                int orderId = Integer.parseInt(orderIdString);
                String orderStatus = request.getParameter(PARAM_STATUS);
                OrderLogic.changeOrderStatus(orderId, orderStatus);
                request.setAttribute("successfulOperation", MessageManager.getMessage("message.operation.success", (Locale) request.getSession().getAttribute("locale")));
            }
        } catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
