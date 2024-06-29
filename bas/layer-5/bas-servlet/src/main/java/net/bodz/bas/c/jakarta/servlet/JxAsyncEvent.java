package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.AsyncEvent;

public class JxAsyncEvent {

    public static AsyncEvent jx2a(javax.servlet.AsyncEvent jx) {
        return new AsyncEvent(//
                JxAsyncContext.jx2a.cachedMap(jx.getAsyncContext()), //
                JxServletRequest.jx2a.cachedMap(jx.getSuppliedRequest()), //
                JxServletResponse.jx2a.cachedMap(jx.getSuppliedResponse()), //
                jx.getThrowable());

    }

}
