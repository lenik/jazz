package net.bodz.lily.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.c.javax.servlet.http.AbstractHttpFilter;

public class LoginFilter
        extends AbstractHttpFilter {

    @Override
    public String getPreferredMapping() {
        return "/*";
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!accessControlled(request)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        LoginData loginData = LoginData.fromSession(session);
        if (loginData != null) {
            chain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/login/index.html");
    }

    boolean accessControlled(HttpServletRequest request) {
        // white-list...
        String pathInfo = request.getPathInfo();
        if (pathInfo.startsWith("login/"))
            return false;
        if (pathInfo.startsWith("lib"))
            return false;
        if (pathInfo.startsWith("js"))
            return false;
        if (pathInfo.startsWith("image"))
            return false;
        return true;
    }

}
