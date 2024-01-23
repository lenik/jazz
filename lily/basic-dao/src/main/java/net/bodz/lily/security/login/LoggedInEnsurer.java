package net.bodz.lily.security.login;

import java.io.IOException;

import net.bodz.bas.c.javax.servlet.http.IHttpFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Redirect to login page for access controled pages.
 */
public class LoggedInEnsurer
        implements
            IHttpFilter {

    @Override
    public int getPriority() {
        return 10;
    }

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

        LoginToken token = LoginToken.fromRequest(request);
        if (token != null) {
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
