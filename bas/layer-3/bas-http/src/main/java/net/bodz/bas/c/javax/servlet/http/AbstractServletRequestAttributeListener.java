package net.bodz.bas.c.javax.servlet.http;

import javax.servlet.ServletRequestAttributeEvent;

public abstract class AbstractServletRequestAttributeListener
        implements IServletRequestAttributeListener {

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
