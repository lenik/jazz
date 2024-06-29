package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.ServletRequestAttributeEvent;

public abstract class AbstractServletRequestAttributeListener
        implements
            IServletRequestAttributeListener {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
    }

}
