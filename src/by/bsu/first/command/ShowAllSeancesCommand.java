package by.bsu.first.command;

import by.bsu.first.entity.Seance;
import by.bsu.first.exception.CommandException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.logic.SeanceLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Пользователь on 03.12.2014.
 */
public class ShowAllSeancesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigManager.getProperty("path.page.seances");
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("locale");
        try {
            ArrayList<Seance> seances = SeanceLogic.getAllSeances();
            if (seances.size() != 0) {
                request.setAttribute("seances", seances);
            } else {
                request.setAttribute("noSeances", MessageManager.getMessage("message.seances.not.exist", locale));
            }
        } catch (LogicException e) {
            throw new CommandException(e.getCause());
        }
        return page;
    }
}
