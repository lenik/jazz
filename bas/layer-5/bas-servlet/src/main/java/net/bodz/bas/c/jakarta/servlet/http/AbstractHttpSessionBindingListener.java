package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.http.HttpSessionBindingEvent;

public abstract class AbstractHttpSessionBindingListener
        implements
            IHttpSessionBindingListener {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
    }

}
