package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.servlet.HttpServlet;

public class LazyLoadServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String className;

    transient HttpServlet servlet;

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        className = config.getInitParameter("servlet-class");

        if (className == null)
            throw new ServletException("Servlet class name isn't specified. (init-param: servlet-class)");

        super.init(config);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        if (servlet == null) {
            synchronized (this) {
                if (servlet == null)
                    try {
                        servlet = loadServlet();
                    } catch (ReflectiveOperationException e) {
                        throw new ServletException(e.getMessage(), e);
                    }
            }
        }
        servlet.service(req, res);
    }

    protected HttpServlet loadServlet()
            throws ReflectiveOperationException, ServletException {
        Class<?> servletClass = Class.forName(className);

        if (HttpServlet.class.isAssignableFrom(servletClass))
            throw new IllegalUsageException("Not an http-servlet class: " + servletClass);

        servlet = (HttpServlet) servletClass.newInstance();

        servlet.init(getServletConfig());
        return servlet;
    }

}
