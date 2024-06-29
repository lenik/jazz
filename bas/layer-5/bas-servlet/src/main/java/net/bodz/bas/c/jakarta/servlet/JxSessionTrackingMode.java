package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.SessionTrackingMode;

import net.bodz.bas.err.UnexpectedException;

public class JxSessionTrackingMode {

    public static SessionTrackingMode jx2a(javax.servlet.SessionTrackingMode jx) {
        if (jx == null)
            return null;
        switch (jx) {
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
