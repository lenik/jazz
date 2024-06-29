package net.bodz.bas.c.javax.servlet;

import javax.servlet.DispatcherType;

import net.bodz.bas.err.UnexpectedException;

public class JaDispatcherType {

    public static DispatcherType ja2x(jakarta.servlet.DispatcherType ja) {
        if (ja == null)
            return null;
        switch (ja) {
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
