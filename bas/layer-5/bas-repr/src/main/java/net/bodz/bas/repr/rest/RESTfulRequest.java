package net.bodz.bas.repr.rest;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.TokenQueue;
import net.bodz.bas.vfs.util.MIMEType;
import net.bodz.bas.vfs.util.MIMETypes;

public class RESTfulRequest
        extends HttpServletRequestWrapper
        implements IRESTfulRequest, Serializable {

    private static final long serialVersionUID = 1L;

    private String method;
    private String path;
    private ITokenQueue tokenQueue;
    private IPathArrival arrival;

    private MIMEType contentType = MIMETypes.text_html;

    public RESTfulRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
        this.setAttribute("method", method);
    }

    @Override
    public MIMEType getTargetContentType() {
        return contentType;
    }

    public void setTargetContentType(MIMEType contentType) {
        this.contentType = contentType;
        this.setAttribute("contentType", contentType);
    }

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
        this.setAttribute("dispatchPath", path);
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
        this.setAttribute("target", getTarget());
        this.setAttribute("restPath", getRemainingPath());
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

    public String getRestfulPath() {
        StringBuilder buf = new StringBuilder();

        buf.append(path);

        if (contentType != MIMETypes.text_html) {
            buf.append('.');
            buf.append(contentType.getPreferredExtension());
        }

        String complexPath = buf.toString();
        return complexPath;
    }

    @Override
    public String toString() {
        return getRestfulPath();
    }

}
