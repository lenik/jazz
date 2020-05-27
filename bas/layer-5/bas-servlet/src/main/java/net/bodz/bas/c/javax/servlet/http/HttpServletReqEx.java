package net.bodz.bas.c.javax.servlet.http;

import java.util.Enumeration;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.t.model.IWrapper;

public class HttpServletReqEx
        extends HttpServletRequestWrapper
        implements IWrapper<HttpServletRequest> {

    private HttpServletRequest wrapped;

    public HttpServletReqEx(HttpServletRequest request) {
        super(request);
        this.wrapped = request;
    }

    public static HttpServletReqEx of(HttpServletRequest req) {
        if (req instanceof HttpServletReqEx)
            return (HttpServletReqEx) req;
        else
            return new HttpServletReqEx(req);
    }

    @Override
    public HttpServletRequest getWrapped() {
        return wrapped;
    }

    public <T> T getAttribute(Class<T> clazz) {
        String key = clazz.getCanonicalName();
        Object obj = super.getAttribute(key);
        return clazz.cast(obj);
    }

    public <T> void setAttribute(Class<T> clazz, T value) {
        super.setAttribute(clazz.getCanonicalName(), value);
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut();
        out.println(wrapped);

        out.println("Headers:");
        Enumeration<String> headers = getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            Enumeration<String> values = getHeaders(name);
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                out.println("    " + name + ": " + value);
            }
        }

        out.println("Parameters:");
        Set<String> keys = new TreeSet<String>(getParameterMap().keySet());
        for (String key : keys) {
            String[] values = getParameterValues(key);
            for (String value : values)
                out.println("    " + key + " = " + value);
        }

        out.println("Attributes:");
        Enumeration<String> attrs = getAttributeNames();
        while (attrs.hasMoreElements()) {
            String name = attrs.nextElement();
            Object value = getAttribute(name);
            out.println("    " + name + " = " + value);
        }
        out.close(); // No-op! just remove warning.
        return out.toString();
    }

}
