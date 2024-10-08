package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class RequestLogger
        extends AbstractServletRequestListener {

    static Logger logger = LoggerFactory.getLogger(RequestLogger.class);

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest _req = sre.getServletRequest();
        HttpServletRequest req = (HttpServletRequest) _req;
        logger.debug("Request-URL: " + req.getRequestURL());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

}
