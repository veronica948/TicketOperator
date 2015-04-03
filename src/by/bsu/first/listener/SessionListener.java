package by.bsu.first.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

/**
 * Created by Пользователь on 30.11.2014.
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se){
        se.getSession().setAttribute("locale", Locale.getDefault());
}
    public void sessionDestroyed(HttpSessionEvent se){
          //se.getSession().removeAttribute("user");
    }
}
