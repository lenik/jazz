package net.bodz.bas.html.viz.util;

import java.io.IOException;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlPre;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.meta.Face;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;

@Face("debug")
public class ObjectDump_htm
        extends AbstractHtmlViewBuilder<Object> {

    public ObjectDump_htm() {
        super(Object.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<Object> ref)
            throws ViewBuilderException, IOException {
        HttpServletReqEx req = HttpServletReqEx.of(ctx.getRequest());

        IMethodOfRequest methodOfRequest = req.getAttribute(IMethodOfRequest.class);

        Object obj = ref.get();

        out.h1().text("Object Dump: " + ObjectInfo.getSimpleId(obj));
        out.hr();

        HtmlPre pre = out.pre();

        Class<?> valueType = ref.getValueType();
        pre.div().text("Type: " + valueType);

        String string = String.valueOf(obj);
        String html = string; // TODO HtmlUtils.htmlEscape(string);
        pre.verbatim(html);

        out.hr();
        out.h2().text("Request Info");

        out = out.pre();
        out.textln("Context-Path: " + req.getContextPath());
        out.textln("Path-Info: " + req.getPathInfo());

        ITokenQueue tokenQueue = req.getAttribute(ITokenQueue.class);
        IPathArrival arrival = req.getAttribute(IPathArrival.class);
        out.textln("Arrival: " + arrival);

        if (tokenQueue == null)
            out.textln("No token queue.");
        else
            out.textln("Remaining-Path: " + tokenQueue.getRemainingPath());

        if (methodOfRequest == null)
            out.textln("No method of request.");
        else
            out.textln("Method: " + methodOfRequest.getMethodName());

        return out;
    }

}
