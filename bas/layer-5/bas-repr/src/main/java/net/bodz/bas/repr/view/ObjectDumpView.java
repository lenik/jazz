package net.bodz.bas.repr.view;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.html.AbstractHtmlViewBuilder;
import net.bodz.bas.repr.html.IHtmlOutputContext;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public class ObjectDumpView
        extends AbstractHtmlViewBuilder<Object> {

    public ObjectDumpView() {
        super(Object.class);
    }

    @Override
    public IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<Object> entry, IOptions options)
            throws ViewBuilderException {
        HttpServletReqEx req = HttpServletReqEx.of(ctx.getRequest());

        IMethodOfRequest qmethod = req.getAttribute(IMethodOfRequest.class);

        Object obj = entry.get();
        IPrintOut out = ctx.getPrintOut();

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
