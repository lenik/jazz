package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.http.HttpSessionActivationListener;

import net.bodz.bas.t.order.IPriority;

public interface IHttpSessionActivationListener
        extends
            HttpSessionActivationListener,
            IPriority {

}
