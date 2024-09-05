package net.bodz.bas.servlet.util;

import java.util.Enumeration;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.jakarta.servlet.http.IHttpRequestProcessor;
import net.bodz.bas.err.ParseException;

public class CookieTester
        implements
            IHttpRequestProcessor {

    public static final String[] NS_OPS = //
//            { "_cop:", "co:" };
            { "_cop:" };

    @Override
    public void apply(HttpServletRequest req, HttpServletResponse resp)
            throws ParseException {

        Enumeration<String> names = req.getParameterNames();

        while (names.hasMoreElements()) {
            String param = names.nextElement();
            for (String ns : NS_OPS) {
                int nsLen = ns.length();
                if (param.startsWith(ns)) {
                    String name = param.substring(nsLen);
                    String domain = null;
                    String path = "/";

                    int colon = name.indexOf(':');
                    if (colon != -1) {
                        domain = name.substring(colon + 1);
                        name = name.substring(0, colon);

                        colon = domain.indexOf(':');
                        if (colon != -1) {
                            path = domain.substring(colon + 1);
                            domain = domain.substring(0, colon);
                        }
                    }

                    Cookie cookie;
                    if (name.startsWith("-")) {
                        name = name.substring(1);
                        // expire it to delete
                        cookie = new Cookie(name, "");
                        cookie.setMaxAge(0);
                    } else {
                        String value = req.getParameter(param);
                        cookie = new Cookie(name, value);
                    }

                    if (domain != null)
                        cookie.setDomain(domain);
                    if (path != null)
                        cookie.setPath(path);
                    else
                        cookie.setPath("/");

                    resp.addCookie(cookie);
                }
            }
        }
    }

}
