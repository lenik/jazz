package net.bodz.bas.html.vbo;

import java.io.IOException;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewContext;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public class ObjectDumpVbo
        extends AbstractHtmlViewBuilder<Object> {

    public ObjectDumpVbo() {
        super(Object.class);
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<Object> ref, IOptions options)
            throws ViewBuilderException, IOException {
        HttpServletReqEx req = HttpServletReqEx.of(ctx.getRequest());

        IMethodOfRequest qmethod = req.getAttribute(IMethodOfRequest.class);

        Object obj = ref.get();
        IHtmlTag out = ctx.getOut();
        out = out.html();

        out.head().title("Object Dump");

        out = out.body();
        out.h1().text("Object Dump: " + ObjectInfo.getSimpleId(obj));
        out.hr();

        String string = String.valueOf(obj);
        String html = string; // TODO HtmlUtils.htmlEscape(string);
        out.pre().verbatim(html);

        out.hr();
        out.h2().text("Request Info");

        out = out.pre();
        out.println("Context-Path: " + req.getContextPath());
        out.println("Path-Info: " + req.getPathInfo());

        ITokenQueue tq = req.getAttribute(ITokenQueue.class);
        IPathArrival arrival = req.getAttribute(IPathArrival.class);
        out.println("Arrival: " + arrival);
        out.println("Remaining-Path: " + tq.getRemainingPath());
        out.println("Method: " + qmethod.getMethodName());

        out.println("</pre>");
        return ctx;
    }

}
