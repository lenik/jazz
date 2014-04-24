package net.bodz.bas.http.ctx;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import net.bodz.bas.c.javax.servlet.http.AbstractServletContextListener;
import net.bodz.bas.err.IllegalUsageException;

public class CurrentServletContext
        extends AbstractServletContextListener {

    private ServletContext servletContext;
    private boolean initialized;

    public CurrentServletContext() {
        if (instance == null)
            instance = this;
        else
            throw new IllegalStateException("Multiple instances.");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        servletContext = event.getServletContext();
        initialized = true;
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        servletContext = null;
        initialized = false;
    }

    public boolean isInitialized() {
        return initialized;
    }

    // ________________________________________________________________________
    // ⇱ Part: Static Accessor
    //

    private static CurrentServletContext instance;

    public static ServletContext getServletContextOpt() {
        if (instance == null)
            throw new IllegalUsageException("The listener isn't configured.");
        return instance.servletContext;
    }

    public static ServletContext getServletContext() {
        ServletContext servletContext = getServletContextOpt();
        if (servletContext == null)
            throw new IllegalStateException("Not initialized yet.");
        return servletContext;
    }

}
