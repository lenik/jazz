package net.bodz.bas.c.javax.servlet.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import net.bodz.bas.t.iterator.Enumerations;

public class MutableHttpServletRequest
        extends HttpServletRequestWrapper {

    Hashtable<String, List<String>> headers;

    boolean sessionOverride;
    HttpSession session;

    public MutableHttpServletRequest(HttpServletRequest request) {
        super(request);

        headers = new Hashtable<String, List<String>>();
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            List<String> list = new ArrayList<String>();
            Enumeration<String> vals = request.getHeaders(name);
            while (vals.hasMoreElements())
                list.add(vals.nextElement());
            headers.put(name, list);
        }
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return headers.keys();
    }

    @Override
    public String getHeader(String name) {
        List<String> vals = headers.get(name);
        if (vals == null || vals.isEmpty())
            return null;
        else
            return vals.get(0);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> vals = headers.get(name);
        if (vals == null)
            vals = Collections.emptyList();
        return Enumerations.enm(vals);
    }

    public synchronized List<String> getOrCreateHeaders(String name) {
        List<String> vals = headers.get(name);
        if (vals == null) {
            vals = new ArrayList<String>();
            headers.put(name, vals);
        }
        return vals;
    }

    public void addHeader(String name, String header) {
        List<String> vals = getOrCreateHeaders(name);
        vals.add(header);
    }

    public void setHeader(String name, String header) {
        List<String> vals = new ArrayList<String>(1);
        vals.add(header);
        headers.put(name, vals);
    }

    @Override
    public HttpSession getSession(boolean create) {
        if (!sessionOverride)
            return super.getSession(create);
        // session-overrided.
        if (!create)
            return session;
        if (session != null)
            return session;
        // create in override-mode.
        this.session = super.getSession(true);
        return session;
    }

    @Override
    public HttpSession getSession() {
        return getSession(true);
    }

    public void setSession(HttpSession session) {
        this.session = session;
        this.sessionOverride = true;
    }

}
