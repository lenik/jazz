package net.bodz.bas.c.javax.servlet.http;

import net.bodz.bas.t.order.IPriority;

import jakarta.servlet.http.HttpSessionListener;

/**
 * NOTICE: Jetty-6 doesn't support http session listener.
 * 
 * @see HttpSessionListener
 */
public interface IHttpSessionListener
        extends HttpSessionListener, IPriority {

}
