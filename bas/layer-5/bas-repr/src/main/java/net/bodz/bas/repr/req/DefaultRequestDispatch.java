package net.bodz.bas.repr.req;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.TokenQueue;

public class DefaultRequestDispatch
        extends HttpRequestProcessor
        implements IRequestDispatch, Serializable {

    private static final long serialVersionUID = 1L;

    private String path;
    private ITokenQueue tokenQueue;
    private IPathArrival arrival;

    @Override
    public String getDispatchPath() {
        return path;
    }

    public void setDispatchPath(String path) {
        this.path = path;
        if (path == null)
            tokenQueue = null;
        else
            tokenQueue = new TokenQueue(path);
    }

    @Override
    public ITokenQueue getTokenQueue() {
        return tokenQueue;
    }

    @Override
    public IPathArrival getArrival() {
        return arrival;
    }

    public void setArrival(IPathArrival arrival) {
        this.arrival = arrival;
    }

    @Override
    public <T> T getTarget() {
        if (arrival == null)
            return null;
        return (T) arrival.getTarget();
    }

    @Override
    public String getRemainingPath() {
        if (arrival == null)
            return null;
        return arrival.getRemainingPath();
    }

    // @Override
    // public String toString() {
    // return getRestfulPath();
    // }

    @Override
    public void apply(HttpServletRequest request)
            throws ParseException {
        // request.setAttribute("target", getTarget());
        // request.setAttribute("remainingPath", getRemainingPath());
        request.setAttribute(ATTRIBUTE_KEY, this);
    }

    public static DefaultRequestDispatch process(HttpServletRequest request)
            throws ParseException {
        DefaultRequestDispatch d = new DefaultRequestDispatch();
        d.apply(request);
        return d;
    }

}
