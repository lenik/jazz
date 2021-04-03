package net.bodz.bas.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.javax.servlet.http.AbstractHttpFilter;
import net.bodz.bas.c.javax.servlet.http.MutableHttpServletRequest;
import net.bodz.bas.c.javax.servlet.http.MutableHttpSession;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class HttpModFilter
        extends AbstractHttpFilter {

    static final Logger logger = LoggerFactory.getLogger(HttpModFilter.class);

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
