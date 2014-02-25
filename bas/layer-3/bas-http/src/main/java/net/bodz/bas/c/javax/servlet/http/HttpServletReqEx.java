package net.bodz.bas.c.javax.servlet.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpServletReqEx
        extends HttpServletRequestWrapper {

    public HttpServletReqEx(HttpServletRequest request) {
        super(request);
    }

    public static HttpServletReqEx of(HttpServletRequest req) {
        if (req instanceof HttpServletReqEx)
            return (HttpServletReqEx) req;
        else
            return new HttpServletReqEx(req);
    }

    public <T> T getAttribute(Class<T> clazz) {
        String key = clazz.getCanonicalName();
        Object obj = getAttribute(key);
        return clazz.cast(obj);
    }

    public <T> void setAttribute(Class<T> clazz, T value) {
        setAttribute(clazz.getCanonicalName(), value);
    }

}
