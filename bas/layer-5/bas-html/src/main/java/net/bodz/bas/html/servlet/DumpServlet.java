package net.bodz.bas.html.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class DumpServlet
        extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String EXCEPTION_HANDLED = "exception-handled";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            serviceImpl(req, resp);
        } catch (Throwable e) {
            resp.addHeader("X-Servlet", DumpServlet.class.getName());
            resp.setContentType("text/plain; charset=utf-8");
            e.printStackTrace(System.err);
            if (req.getAttribute(EXCEPTION_HANDLED) == null)
                try {
                    OutputStream os = resp.getOutputStream();
                    PrintStream ps = new PrintStream(os);
//                    ps.println("<html><body><pre>");
                    ps.println("Exception Dump: ");
                    e.printStackTrace(ps);
//                    ps.println("</pre></body></html>");
                } catch (IllegalStateException ise) {
                    PrintWriter pw = resp.getWriter();
//                    pw.println("<html><body><pre>");
                    pw.println("Exception Dump: ");
                    e.printStackTrace(pw);
//                    pw.println("</pre></body></html>");
                }
        }
    }

    protected abstract void serviceImpl(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

}
