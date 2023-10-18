package net.bodz.bas.servlet;

import java.io.IOException;

import net.bodz.bas.c.javax.servlet.http.IHttpFilter;
import net.bodz.bas.c.javax.servlet.http.MutableHttpServletRequest;
import net.bodz.bas.c.javax.servlet.http.MutableHttpSession;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Add session support for requests originated from local files.
 *
 * By default, browsing from file://... can't have a session.
 */
public class FileSessionSupportFilter
        implements
            IHttpFilter {

    static final Logger logger = LoggerFactory.getLogger(FileSessionSupportFilter.class);

    MutableHttpSession fileSession;

    @Override
    public int getPriority() {
        return -10;
    }

    @Override
    public String getPreferredMapping() {
        return "/*";
    }

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        fileSession = new MutableHttpSession(servletContext, "local-file");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        MutableHttpServletRequest reqMod = new MutableHttpServletRequest(request);

        String origin = request.getHeader("Origin");
        boolean fileOrigin = false;
        if (origin != null) {
            if (origin.equals("null") || origin.startsWith("file://"))
                fileOrigin = true;
        }

        if (fileOrigin)
            reqMod.setSession(fileSession);
        else
            logger.debug("Request-URI: " + request.getRequestURI());

        chain.doFilter(reqMod, response);
    }

}
