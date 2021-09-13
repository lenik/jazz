package net.bodz.bas.html.viz.fn;

import java.io.IOException;

import net.bodz.bas.meta.source.FnHelper;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.ui.dom1.IUiRef;

@FnHelper
public class RedirectFn {

    public static boolean addSlash(IHttpViewContext ctx, IUiRef<?> ref)
            throws IOException {
        Object obj = ref.get();
        IPathArrival arrival = ctx.query(IPathArrival.class);
        boolean arrivedHere = arrival.getPrevious(obj).getRemainingPath() == null;
        return arrivedHere && addSlash(ctx);
    }

    public static boolean addSlash(IHttpViewContext ctx)
            throws IOException {
        String uri = ctx.getRequest().getRequestURI();
        if (uri.endsWith("/"))
            return false;

        StringBuffer url = ctx.getRequest().getRequestURL();
        url.append('/');

        String queryString = ctx.getRequest().getQueryString();
        if (queryString != null) {
            url.append('?');
            url.append(queryString);
        }

        ctx.getResponse().sendRedirect(url.toString());
        ctx.stop();
        return true;
    }

}
