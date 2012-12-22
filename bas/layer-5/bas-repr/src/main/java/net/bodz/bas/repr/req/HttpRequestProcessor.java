package net.bodz.bas.repr.req;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(obsoleted = true)
public abstract class HttpRequestProcessor
        implements IHttpRequestProcessor {

    public static String getParameter(HttpServletRequest request, String name, boolean remove) {
        String param = request.getParameter(name);
        request.getParameterMap().remove(name);
        return param;
    }

}
