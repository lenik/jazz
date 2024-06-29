package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.DispatcherType;

import net.bodz.bas.err.UnexpectedException;

public class JxDispatcherType {

    public static DispatcherType jx2a(javax.servlet.DispatcherType jx) {
        if (jx == null)
            return null;
        switch (jx) {
        case FORWARD:
            return DispatcherType.FORWARD;
        case INCLUDE:
            return DispatcherType.INCLUDE;
        case REQUEST:
            return DispatcherType.REQUEST;
        case ASYNC:
            return DispatcherType.ASYNC;
        case ERROR:
            return DispatcherType.ERROR;
        default:
            throw new UnexpectedException();
        }
    }

}
