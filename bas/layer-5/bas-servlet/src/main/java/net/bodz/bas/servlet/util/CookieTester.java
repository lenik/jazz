package net.bodz.bas.servlet.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.jakarta.servlet.http.IHttpRequestProcessor;
import net.bodz.bas.err.ParseException;

public class CookieTester
        implements
            IHttpRequestProcessor {

    @Override
    public boolean apply(HttpServletRequest req, HttpServletResponse resp)
            throws ParseException {
        String sc = req.getParameter("_sc");
        Cookie cookie = new Cookie("a", "b");
        resp.addCookie(cookie);
        Cookie[] cookies = req.getCookies();

        return false;
    }

}
