package net.bodz.bas.repr.content.cache;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.string.TokensBuilder;

public class HttpCache
        extends net.bodz.bas.c.javax.servlet.http.HttpCache {

    public static void setCacheControl(HttpServletResponse response, ICacheControl cacheControl) {
        TokensBuilder buf = new TokensBuilder(", ");
        boolean cachable = true;

        switch (cacheControl.getCacheControlMode()) {
        case NO_STORE:
            buf.appendToken("no-store");
            cachable = false;
            break;
        case NO_CACHE:
            buf.appendToken("no-cache");
            cachable = false;
            break;
        case PRIVATE:
            buf.appendToken("private");
            break;
        case PUBLIC:
            buf.appendToken("public");
            break;
        case AUTO:
        default:
        }

        if (cachable)
            buf.appendToken("max-age=" + cacheControl.getMaxAge());

        switch (cacheControl.getCacheRevalidationMode()) {
        case REQUIRED:
            buf.appendToken("must-validate");
            break;
        case WANTED:
            buf.appendToken("proxy-validate");
            break;
        case OPTIONAL:
        default:
        }

        response.setHeader(CACHE_CONTROL, buf.toString());
        if (cachable) {
            Date expires = new Date(System.currentTimeMillis() + cacheControl.getMaxAge() * 1000L);
            // timezone...?
            response.setHeader(EXPIRES, toRFCDate(expires));
        } else {
            response.setHeader(EXPIRES, "-1");
        }

        response.setHeader(LAST_MODIFIED, toRFCDate(cacheControl.getLastModifiedDate()));
    }

    static String toRFCDate(Date date) {
        return null;
    }

}
