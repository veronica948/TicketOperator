package by.bsu.first.command;

import by.bsu.first.entity.Order;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.OrderLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Пользователь on 08.12.2014.
 */

public class ShowOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.admin.orders");
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        try {
            ArrayList<Order> orders = OrderLogic.findOrders();
            if(orders.size() != 0) {
                request.setAttribute("orders", orders);
            } else {
                request.setAttribute("notFound", MessageManager.getMessage("message.orders.not.found", locale));
            }
        } catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
