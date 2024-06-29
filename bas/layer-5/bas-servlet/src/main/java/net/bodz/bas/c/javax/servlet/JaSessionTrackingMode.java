package net.bodz.bas.c.javax.servlet;

import javax.servlet.SessionTrackingMode;

import net.bodz.bas.err.UnexpectedException;

public class JaSessionTrackingMode {

    public static SessionTrackingMode ja2x(jakarta.servlet.SessionTrackingMode ja) {
        if (ja == null)
            return null;
        switch (ja) {
        case COOKIE:
            return SessionTrackingMode.COOKIE;
        case URL:
            return SessionTrackingMode.URL;
        case SSL:
            return SessionTrackingMode.SSL;
        default:
            throw new UnexpectedException();
        }
    }

}
