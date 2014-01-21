package net.bodz.bas.c.javax.servlet.http;

import javax.servlet.http.HttpSessionBindingEvent;

public abstract class AbstractHttpSessionAttributeListener
        implements IHttpSessionAttributeListener {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }

}
