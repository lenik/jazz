package net.bodz.bas.repr.req;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.jakarta.servlet.http.IHttpRequestProcessor;
import net.bodz.bas.err.ParseException;

public class HttpSnapRequestProcessor
        implements
            IHttpRequestProcessor {

    @Override
    public void apply(HttpServletRequest request, HttpServletResponse response)
            throws ParseException {
        HttpSnapManager snapManager = (HttpSnapManager) request.getAttribute(HttpSnapManager.ATTRIBUTE_KEY);
        if (snapManager == null)
            return;

        String snapIdStr = request.getParameter("-snap");
        if (snapIdStr == null)
            return;

        long snapId = Long.parseLong(snapIdStr);
        HttpSnap snap = snapManager.get(snapId);
        if (snap == null) {
            // response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            return;
        }

        request.setAttribute(HttpSnap.ATTRIBUTE_KEY, snap);
        return;
    }

}
