package net.bodz.bas.repr.req;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public class RequestUtils {

    public static <T> T getFeature(HttpSession session, Class<T> clazz) {
        Object obj = session.getAttribute(clazz.getCanonicalName());
        return clazz.cast(obj);
    }

    public static <T> T getFeature(ServletContext ctx, Class<T> clazz) {
        Object obj = ctx.getAttribute(clazz.getCanonicalName());
        return clazz.cast(obj);
    }

    public static String getRestfulPath(HttpServletRequest _req) {
        HttpServletReqEx req = HttpServletReqEx.of(_req);
        IMethodOfRequest method = req.getAttribute(IMethodOfRequest.class);
        IViewOfRequest view = req.getAttribute(IViewOfRequest.class);

        StringBuilder buf = new StringBuilder();

        buf.append(req.getPathInfo());

        ContentType contentType = view.getContentType();
        if (contentType != ContentTypes.text_html) {
            buf.append('.');
            buf.append(contentType.getPreferredExtension());
        }

        String complexPath = buf.toString();
        return complexPath;
    }

}
