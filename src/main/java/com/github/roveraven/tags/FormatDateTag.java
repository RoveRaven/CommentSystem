package com.github.roveraven.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspTagException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.jasper.tagplugins.jstl.Util;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

//<!--https://stackoverflow.com/questions/28516766/using-java-time-localdate-with-jstl-fmtformatdate-action-->

public class FormatDateTag extends TagSupport {
    protected Temporal value;
    protected String pattern;
    private String var;
    private int scope;

    public FormatDateTag()
    {
        super ();
        init ();
    }

    private void init()
    {
        this.pattern = this.var = null;
        this.value = null;
        this.scope = PageContext.PAGE_SCOPE;
    }

    public void setVar(final String var)
    {
        this.var = var;
    }
    public void setScope(final String scope)
    {
        this.scope = Util.getScope (scope);
    }
    public void setValue(final Temporal value)
    {
        this.value = value;
    }
    public void setPattern(final String pattern)
    {
        this.pattern = pattern;
    }
    @Override
    public int doEndTag() throws JspException
    {
        String formatted = null;
        if (this.value == null)
        {
            if (this.var != null)
            {
                this.pageContext.removeAttribute (this.var, this.scope);
            }
            return EVAL_PAGE;
        }
        // Create formatter
        if (this.pattern != null)
        {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern (this.pattern);
            formatted = formatter.format (this.value);
        }
        else
        {
            // no formatting locale available, use Date.toString()
            formatted = this.value.toString ();
        }

        if (this.var != null)
        {
            this.pageContext.setAttribute (this.var, formatted, this.scope);
        }
        else
        {
            try
            {
                this.pageContext.getOut().print(formatted);
            }
            catch (final IOException ioe)
            {
                throw new JspTagException(ioe.toString (), ioe);
            }
        }
        return EVAL_PAGE;
    }


    @Override
    public void release()
    {
        init ();
    }

}