package net.bodz.bas.c.javax.servlet.http;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.std.rfc.http.HttpHeaderNames;

public class HttpCache
        implements HttpHeaderNames {

    public static void setShouldNotCache(HttpServletResponse response) {
        response.setHeader(CACHE_CONTROL, "public, max-age=0");
    }

    public static void setNoCache(HttpServletResponse response) {
        response.setHeader(PRAGMA, "no-cache");

        // Basically it's the same to:
        // response.setHeader(CACHE_CONTROL, "max-age=0; must-revalidate");
        response.setHeader(CACHE_CONTROL, "no-cache");

        response.setHeader(EXPIRES, "-1");
    }

    public static void setNoStore(HttpServletResponse response) {
        response.setHeader(PRAGMA, "no-store");
        response.setHeader(CACHE_CONTROL, "no-store");
        response.setHeader(EXPIRES, "-1");
    }

}
