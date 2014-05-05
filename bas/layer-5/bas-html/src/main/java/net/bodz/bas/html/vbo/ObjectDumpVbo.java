package net.bodz.bas.html.vbo;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.gui.dom1.IGUIRefEntry;
import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHttpReprContext;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public class ObjectDumpVbo
        extends AbstractHtmlViewBuilder<Object> {

    public ObjectDumpVbo() {
        super(Object.class);
    }

    @Override
    public IHttpReprContext buildHtmlView(IHttpReprContext ctx, IGUIRefEntry<Object> entry, IOptions options)
            throws ViewBuilderException {
        HttpServletReqEx req = HttpServletReqEx.of(ctx.getRequest());

        IMethodOfRequest qmethod = req.getAttribute(IMethodOfRequest.class);

        Object obj = entry.get();
        IHtmlOut out = ctx.getOut();

        out.println("<html><head><title>Object Dump</title></head>");
        out.println("<body><h1>Object Dump: " + ObjectInfo.getSimpleId(obj) + "</h1>");
        out.println("<hr/>");

        String string = String.valueOf(obj);
        String html = string; // TODO HtmlUtils.htmlEscape(string);
        out.println("<pre>");
        out.println(html);
        out.println("</pre>");

        out.println("<hr/>");
        out.println("<h2>Request Info</h2>");
        out.println("<pre>");

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
