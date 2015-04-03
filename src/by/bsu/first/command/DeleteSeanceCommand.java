package by.bsu.first.command;

import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.DAOException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.SeanceLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Пользователь on 27.11.2014.
 */
public class DeleteSeanceCommand implements Command {
    private static final String PARAM_SEANCE_ID = "seanceId";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int seanceId = Integer.parseInt(request.getParameter(PARAM_SEANCE_ID));
        String page = ConfigManager.getProperty("path.page.admin");
        try {
            if(!SeanceLogic.existOrders(seanceId)) {
                SeanceLogic.deleteSeance(seanceId);
                request.setAttribute("successfulOperation",
                        MessageManager.getMessage("message.operation.success",(Locale) request.getSession().getAttribute("locale")));
            } else {
                request.setAttribute("impossibleOperation",
                        MessageManager.getMessage("message.delete.seance.impossible", (Locale) request.getSession().getAttribute("locale")));
            }
        }
        catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
