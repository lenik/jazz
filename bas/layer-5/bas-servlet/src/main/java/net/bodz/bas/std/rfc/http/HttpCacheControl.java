package net.bodz.bas.std.rfc.http;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.time.IDateTimeFormatConsts;
import net.bodz.bas.c.string.TokensBuilder;

public class HttpCacheControl
        implements
            HttpHeaderNames,
            IDateTimeFormatConsts {

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

    public static void apply(HttpServletResponse response, ICacheControl cacheControl) {
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

        buf.appendToken(cacheControl.getCacheRevalidationMode().getHttpEquivContent(), false);

        response.setHeader(CACHE_CONTROL, buf.toString());
        if (cachable) {
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime expires = now.plus(cacheControl.getMaxAge(), ChronoUnit.SECONDS);
            response.setHeader(EXPIRES, RFC_1123_DATE_TIME.format(expires));
        } else {
            response.setHeader(EXPIRES, "-1");
        }

        response.setHeader(LAST_MODIFIED, RFC_1123_DATE_TIME.format(cacheControl.getLastModified()));
    }

}
