package net.bodz.bas.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpServlet
        extends jakarta.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected final void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("X-Servlet", getClass().getSimpleName());
        serviceImpl(req, resp);
    }

    protected void serviceImpl(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.service(req, resp);
    }

}
