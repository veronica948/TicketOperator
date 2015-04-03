package by.bsu.first.filter;

import by.bsu.first.entity.User;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Пользователь on 20.01.2015.
 */
@WebFilter(urlPatterns = "/jsp/admin/*",
        initParams = {
                @WebInitParam(name = "adminValue", value = "0")
        }
)
public class AccessAdminFilter implements Filter {
    int adminValue;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         adminValue = Integer.parseInt(filterConfig.getInitParameter("adminValue"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        User user = (User)session.getAttribute("user");
        boolean isAvailable = false;
        String page = null;
        if(user != null) {
            if(user.getRole() == adminValue) {
                isAvailable = true;
            } else {
                page = ConfigManager.getProperty("path.page.main");
            }
        } else {
            page = ConfigManager.getProperty("path.page.index");
        }
        if(!isAvailable) {

            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect(page);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

}
