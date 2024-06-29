package net.bodz.bas.c.javax.servlet;

import javax.servlet.AsyncEvent;

public class JaAsyncEvent {

    public static AsyncEvent ja2x(jakarta.servlet.AsyncEvent ja) {
        return new AsyncEvent(//
                JaAsyncContext.ja2x.cachedMap(ja.getAsyncContext()), //
                JaServletRequest.ja2x.cachedMap(ja.getSuppliedRequest()), //
                JaServletResponse.ja2x.cachedMap(ja.getSuppliedResponse()), //
                ja.getThrowable());
    }

}
