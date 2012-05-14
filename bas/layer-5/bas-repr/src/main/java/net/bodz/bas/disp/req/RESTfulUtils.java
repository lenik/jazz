package net.bodz.bas.disp.req;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.vfs.util.ContentType;
import net.bodz.bas.vfs.util.ContentTypes;

public class RESTfulUtils {

    public static String getRestfulPath(HttpServletRequest request) {
        IRequestDispatch disp = (IRequestDispatch) request.getAttribute(IRequestDispatch.ATTRIBUTE_KEY);
        IRequestMethod method = (IRequestMethod) request.getAttribute(IRequestMethod.ATTRIBUTE_KEY);
        IRequestView view = (IRequestView) request.getAttribute(IRequestView.ATTRIBUTE_KEY);

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
