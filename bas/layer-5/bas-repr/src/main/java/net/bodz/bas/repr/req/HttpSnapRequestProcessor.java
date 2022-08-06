package net.bodz.bas.repr.req;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.err.ParseException;

public class HttpSnapRequestProcessor
        implements
            IHttpRequestProcessor {

    @Override
    public boolean apply(HttpServletRequest request, HttpServletResponse response)
            throws ParseException {
        HttpSnapManager snapManager = (HttpSnapManager) request.getAttribute(HttpSnapManager.ATTRIBUTE_KEY);
        if (snapManager == null)
            return true;

        String snapIdStr = request.getParameter("-snap");
        if (snapIdStr == null)
            return true;

        long snapId = Long.parseLong(snapIdStr);
        HttpSnap snap = snapManager.get(snapId);
        if (snap == null) {
            // response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            return true;
        }

        request.setAttribute(HttpSnap.ATTRIBUTE_KEY, snap);
        return true;
    }

}
