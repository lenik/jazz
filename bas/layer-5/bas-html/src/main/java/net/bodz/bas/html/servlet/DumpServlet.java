package net.bodz.bas.html.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

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
            e.printStackTrace(System.err);
            OutputStream os = resp.getOutputStream();
            PrintStream out = new PrintStream(os);
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");
        }
    }

    protected abstract void serviceImpl(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

}
