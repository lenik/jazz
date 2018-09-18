package net.bodz.bas.c.javax.servlet.http;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

@SuppressWarnings("deprecation")
public class MutableHttpSession
        implements HttpSession {

    String id;

    long creationTime = System.currentTimeMillis();
    long lastAccessedTime = creationTime;

    ServletContext servletContext;
    HttpSessionContext sessionContext;

    Hashtable<String, Object> attributes = new Hashtable<>();
    boolean invalid;
    boolean isNewCreated;

    public MutableHttpSession(ServletContext servletContext, String id) {
        if (servletContext == null)
            throw new NullPointerException("servletContext");
        this.servletContext = servletContext;
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    @Override
    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    int maxInactiveInterval;

    @Override
    public void setMaxInactiveInterval(int interval) {
        this.maxInactiveInterval = interval;
    }

    @Override
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    @Deprecated
    @Override
    public HttpSessionContext getSessionContext() {
        return sessionContext;
    }

    @Deprecated
    public void setSessionContext(HttpSessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public final Object getValue(String name) {
        return getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return attributes.keys();
    }

    @Override
    public String[] getValueNames() {
        return attributes.keySet().toArray(new String[0]);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public final void putValue(String name, Object value) {
        setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    @Override
    public final void removeValue(String name) {
        removeAttribute(name);
    }

    @Override
    public synchronized void invalidate() {
        if (invalid)
            throw new IllegalStateException("Already invalidated.");
        attributes.clear();
        invalid = true;
    }

    @Override
    public boolean isNew() {
        return isNewCreated;
    }

    public void setNew(boolean isNewCreated) {
        this.isNewCreated = isNewCreated;
    }

}
