package by.bsu.first.controller;

import by.bsu.first.command.ActionFactory;
import by.bsu.first.command.Command;
import by.bsu.first.exception.CommandException;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.pool.Pool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Пользователь on 07.10.2014.
 */
@WebServlet(
        urlPatterns = {"/controller"}
)
public class Controller extends HttpServlet {
    static Logger logger = Logger.getLogger(Controller.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        Command command = ActionFactory.defineCommand(request);
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            logger.error(e.getMessage(), e.getCause());
            request.setAttribute("exception", e.getCause());
            page = ConfigManager.getProperty("path.page.error");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = this.getServletContext().getRealPath("/");
        new DOMConfigurator().doConfigure(path + "/log4j.xml", LogManager.getLoggerRepository());
    }

    public void destroy() {
        super.destroy();
        Pool.getPool().closePool();
    }
}
