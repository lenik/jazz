package net.bodz.bas.repr.req;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.http.ctx.IAnchor;
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

    public static String getServerURL(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder(40);
        sb.append(req.isSecure() ? "https://" : "http://");
        sb.append(req.getServerName());

        int port = req.getServerPort();
        if (req.isSecure()) {
            if (port != 443)
                sb.append(port);
        } else {
            if (port != 80)
                sb.append(port);
        }

        sb.append("/");
        return sb.toString();
    }

    public static String getURL(HttpServletRequest req, IAnchor anchor) {
        String url = getServerURL(req);
        return url + anchor.absoluteHref();
    }

}
