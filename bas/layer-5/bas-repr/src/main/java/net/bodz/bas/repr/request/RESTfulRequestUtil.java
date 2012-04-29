package net.bodz.bas.repr.request;

import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.PathArrival;
import net.bodz.bas.repr.rest.RESTfulRequest;

public class RESTfulRequestUtil {

    public static RESTfulRequest wrapDispatched(Object obj) {
        MockHttpServletRequest req0 = new MockHttpServletRequest();

        RESTfulRequest rreq = new RESTfulRequest(req0);

        IPathArrival arrival = new PathArrival(obj);
        rreq.setArrival(arrival);

        return rreq;
    }

}
