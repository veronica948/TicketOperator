package by.bsu.first.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Пользователь on 23.12.2014.
 */
@SuppressWarnings("serial")
public class OrderSearchFormTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            String path = pageContext.getServletContext().getContextPath();
            ResourceBundle rs = ResourceBundle.getBundle("resources.pagecontent",(Locale)pageContext.getSession().getAttribute("locale"));
            String buttonValue = rs.getString("admin.order.find");
                    out.write("<form action=" + path + "/controller>" +
                    "         <input type=hidden name=command value=find_order>" +
                    "         <input type=number name=orderId>" +
                    "         <input type=submit value=" + buttonValue + "></form> ");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
