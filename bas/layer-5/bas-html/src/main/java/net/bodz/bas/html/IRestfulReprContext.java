package net.bodz.bas.html;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.req.IResultOfRequest;
import net.bodz.bas.repr.req.IViewOfRequest;

public interface IRestfulReprContext {

    ITokenQueue getTokenQueue();

    IPathArrival getPathArrival();

    IMethodOfRequest getMethodOfRequest();

    IResultOfRequest getResultOfRequest();

    IViewOfRequest getViewOfRequest();

}