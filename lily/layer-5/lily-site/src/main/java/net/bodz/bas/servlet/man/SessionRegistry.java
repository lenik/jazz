package net.bodz.bas.servlet.man;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import net.bodz.bas.c.javax.servlet.http.AbstractHttpSessionListener;

public class SessionRegistry
        extends AbstractHttpSessionListener {

    public static final String KEY_SESSION_REGISTRY = "sessionRegistry";

    Map<String, HttpSession> map = new LinkedHashMap<>();

    public HttpSession getSession(String sessionId) {
        return map.get(sessionId);
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String id = session.getId();
        map.put(id, session);

        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute(KEY_SESSION_REGISTRY, this);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String id = session.getId();
        map.remove(id);
    }

    public static SessionRegistry fromServletContext(ServletContext servletContext) {
        if (servletContext == null)
            throw new NullPointerException("servletContext");
        Object attribute = servletContext.getAttribute(KEY_SESSION_REGISTRY);
        return (SessionRegistry) attribute;
    }

}
