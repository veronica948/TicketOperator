package by.bsu.first.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Пользователь on 16.12.2014.
 */
@SuppressWarnings("serial")
public class MovieListTag extends TagSupport {
    private ArrayList<String> list;

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<select name=movieName>");
            for(int i = 0; i < list.size(); i++ ) {
                out.write("<option>" + list.get(i) + "</option>");
            }
            out.write("</select>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}

