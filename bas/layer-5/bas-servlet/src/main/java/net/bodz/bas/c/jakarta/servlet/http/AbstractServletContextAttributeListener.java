package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.ServletContextAttributeEvent;

public abstract class AbstractServletContextAttributeListener
        implements
            IServletContextAttributeListener {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
    }

}
