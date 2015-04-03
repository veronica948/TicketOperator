package by.bsu.first.command;

import by.bsu.first.entity.Order;
import by.bsu.first.entity.User;
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
 * Created by Пользователь on 07.12.2014.
 */
public class ShowClientOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.client.orders");
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();
        try {
            ArrayList<Order> orders = OrderLogic.findOrdersByUserId(userId);
            if(orders.size() != 0) {
                request.setAttribute("orders", orders);
            } else {
                request.setAttribute("notFound", MessageManager.getMessage("message.orders.not.found",locale));
            }
        } catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
