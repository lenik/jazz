package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBasePath
        implements IBasePath {

    @Override
    public String from(String otherPath) {
        if (otherPath == null)
            throw new NullPointerException("otherPath");
        if (!otherPath.startsWith("/"))
            throw new IllegalArgumentException("Not absolute: " + otherPath);

        // Remove the "basename" at first.
        String from_ = otherPath.substring(0, otherPath.lastIndexOf('/') + 1);
        String target_ = absolute();

        StringBuilder sb = new StringBuilder(80);
        while (true) {
            if (target_.startsWith(from_))
                break;

            int slash = from_.lastIndexOf('/', from_.length() - 2);
            if (slash == -1)
                break;

            from_ = from_.substring(0, slash + 1);
            sb.append("../");
        }

        String remaining = target_.substring(from_.length());
        sb.append(remaining);
        return sb.toString();
    }

    @Override
    public String from(HttpServletRequest req) {
        if (req == null)
            throw new NullPointerException("req");
        String uri = req.getRequestURI();
        return from(uri);
    }

    @Override
    public String fromCurrentRequest() {
        HttpServletRequest req = CurrentHttpService.getRequest();
        return from(req);
    }

    @Override
    public String toString() {
        return fromCurrentRequest();
    }

}
