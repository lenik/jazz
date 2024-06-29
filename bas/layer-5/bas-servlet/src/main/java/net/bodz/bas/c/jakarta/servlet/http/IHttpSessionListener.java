package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.http.HttpSessionListener;

import net.bodz.bas.t.order.IPriority;

/**
 * NOTICE: Jetty-6 doesn't support http session listener.
 * 
 * @see HttpSessionListener
 */
public interface IHttpSessionListener
        extends
            HttpSessionListener,
            IPriority {

}
