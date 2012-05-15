package net.bodz.bas.disp.req;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.disp.view.AbstractHttpRenderer;
import net.bodz.bas.util.ObjectInfo;

public class ObjectDumpView
        extends AbstractHttpRenderer {

    /**
     * Returns the lowest priority.
     */
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isFallback() {
        return true;
    }

    @Override
    public boolean render(Class<?> clazz, Object obj, HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        IRequestDispatch qdisp = RequestUtils.getRequestDispatch(req);
        IRequestMethod qmethod = RequestUtils.getRequestMethod(req);

        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>Object Dump</title></head>");
        out.println("<body><h1>Object Dump: " + ObjectInfo.getObjectId(obj) + "</h1>");
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
        out.println("Dispatch-Path: " + qdisp.getDispatchPath());
        out.println("Arrival: " + qdisp.getArrival());
        out.println("Remaining-Path: " + qdisp.getRemainingPath());
        out.println("Method: " + qmethod.getMethodName());
        out.println(qdisp);

        out.println("</pre>");
        return true;
    }

}
