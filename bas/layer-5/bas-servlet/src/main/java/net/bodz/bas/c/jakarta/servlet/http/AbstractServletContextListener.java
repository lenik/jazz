package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.ServletContextEvent;

public abstract class AbstractServletContextListener
        implements
            IServletContextListener {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isIncluded(ServletContextEvent event) {
        return true;
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

}
