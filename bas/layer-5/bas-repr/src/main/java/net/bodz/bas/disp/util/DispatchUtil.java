package net.bodz.bas.disp.util;

import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.IDispatcher;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.PathArrival;
import net.bodz.bas.disp.TokenQueue;

public class DispatchUtil {

    public static Object dispatch(IDispatcher dispatcher, Object startObject, ITokenQueue tokens)
            throws DispatchException {
        IPathArrival context = new PathArrival(startObject, tokens.getRemainingPath());
        context = dispatcher.dispatch(context, tokens);
        if (context == null)
            return null;
        return context.getTarget();
    }

    public static Object dispatch(IDispatcher dispatcher, Object startObject, String path)
            throws DispatchException {
        TokenQueue tokens = new TokenQueue(path);
        return dispatch(dispatcher, startObject, tokens);
    }

}
