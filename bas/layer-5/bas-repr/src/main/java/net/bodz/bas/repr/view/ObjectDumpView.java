package net.bodz.bas.repr.view;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.html.AbstractHtmlViewBuilder;
import net.bodz.bas.repr.html.IHtmlOutputContext;
import net.bodz.bas.repr.req.IRequestDispatch;
import net.bodz.bas.repr.req.IRequestMethod;
import net.bodz.bas.repr.req.RequestUtils;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public class ObjectDumpView
        extends AbstractHtmlViewBuilder<Object> {

    // @Override
    public Class<?> getType() {
        return Object.class;
    }

    @Override
    public IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<Object> entry, IOptions options)
            throws ViewBuilderException {
        HttpServletRequest req = ctx.getRequest();

        IRequestDispatch disp = RequestUtils.getRequestDispatch(req);
        IRequestMethod qmethod = RequestUtils.getRequestMethod(req);

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
        out.println("Dispatch-Path: " + disp.getDispatchPath());
        out.println("Arrival: " + disp.getArrival());
        out.println("Remaining-Path: " + disp.getRemainingPath());
        out.println("Method: " + qmethod.getMethodName());
        out.println(disp);

        out.println("</pre>");
        return ctx;
    }

}
