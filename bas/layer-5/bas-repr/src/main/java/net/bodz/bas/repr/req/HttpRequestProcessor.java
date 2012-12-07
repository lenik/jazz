package net.bodz.bas.repr.req;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public abstract class HttpRequestProcessor {

    public abstract void apply(HttpServletRequest request)
            throws ParseException;

    public static String getParameter(HttpServletRequest request, String name, boolean remove) {
        String param = request.getParameter(name);
        request.getParameterMap().remove(name);
        return param;
    }

}
