package by.bsu.first.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created by Пользователь on 25.11.2014.
 */
@WebFilter(urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "requestEncoding", value = "UTF-8")
        })
public class CharsetFilter implements Filter {
        private String encoding;
        public void init(FilterConfig config) throws ServletException {
            if (encoding == null) {
                encoding = config.getInitParameter("requestEncoding");
            }
        }
        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain next) throws IOException, ServletException {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
            next.doFilter(request, response);
        }
        public void destroy() {
        }
}
