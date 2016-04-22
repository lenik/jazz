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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            serviceImpl(req, resp);
        } catch (Throwable e) {
            resp.addHeader("X-Servlet", DumpServlet.class.getName());
            e.printStackTrace(System.err);
            try {
                OutputStream os = resp.getOutputStream();
                PrintStream ps = new PrintStream(os);
                e.printStackTrace(ps);
            } catch (IllegalStateException ise) {
                PrintWriter pw = resp.getWriter();
                e.printStackTrace(pw);
            }
        }
    }

    protected abstract void serviceImpl(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

}
