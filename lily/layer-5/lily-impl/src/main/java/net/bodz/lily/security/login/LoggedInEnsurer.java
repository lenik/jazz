package net.bodz.lily.security.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.javax.servlet.http.IHttpFilter;

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
