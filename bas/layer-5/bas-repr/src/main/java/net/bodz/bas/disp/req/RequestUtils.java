package net.bodz.bas.disp.req;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bodz.bas.disp.view.IRequestResult;
import net.bodz.bas.vfs.util.ContentType;
import net.bodz.bas.vfs.util.ContentTypes;

public class RequestUtils {

    public static <T> T getFeature(ServletRequest req, Class<T> clazz) {
        Object obj = req.getAttribute(clazz.getCanonicalName());
        return clazz.cast(obj);
    }

    public static <T> T getFeature(HttpSession session, Class<T> clazz) {
        Object obj = session.getAttribute(clazz.getCanonicalName());
        return clazz.cast(obj);
    }

    public static <T> T getFeature(ServletContext ctx, Class<T> clazz) {
        Object obj = ctx.getAttribute(clazz.getCanonicalName());
        return clazz.cast(obj);
    }

    public static IRequestDispatch getRequestDispatch(ServletRequest req) {
        return getFeature(req, IRequestDispatch.class);
    }

    public static IRequestMethod getRequestMethod(ServletRequest req) {
        return getFeature(req, IRequestMethod.class);
    }

    public static IRequestView getRequestView(ServletRequest req) {
        return getFeature(req, IRequestView.class);
    }

    public static IRequestResult getRequestResult(ServletRequest req) {
        return getFeature(req, IRequestResult.class);
    }

    public static String getRestfulPath(HttpServletRequest req) {
        IRequestDispatch disp = RequestUtils.getRequestDispatch(req);
        IRequestMethod method = RequestUtils.getRequestMethod(req);
        IRequestView view = RequestUtils.getRequestView(req);

        StringBuilder buf = new StringBuilder();

        buf.append(disp.getDispatchPath());

        ContentType contentType = view.getContentType();
        if (contentType != ContentTypes.text_html) {
            buf.append('.');
            buf.append(contentType.getPreferredExtension());
        }

        String complexPath = buf.toString();
        return complexPath;
    }

}
