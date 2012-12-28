package net.bodz.bas.repr.req;

import javax.servlet.http.HttpServletRequest;

public abstract class HttpRequestProcessor
        implements IHttpRequestProcessor {

    public static String getParameter(HttpServletRequest request, String name, boolean remove) {
        String param = request.getParameter(name);
        request.getParameterMap().remove(name);
        return param;
    }

}
