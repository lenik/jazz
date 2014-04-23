package net.bodz.bas.http.ctx;

import net.bodz.bas.c.java.io.FilePath;

public class AbsoluteHref
        extends AbstractHref {

    /**
     * Without trailing slash.
     */
    private String target;

    /**
     * With the trailing slash.
     */
    private String target_;

    public AbsoluteHref(String absoluteHref) {
        if (absoluteHref == null)
            throw new NullPointerException("absoluteHref");
        if (!absoluteHref.startsWith("/"))
            throw new IllegalArgumentException("Not absolute path: " + absoluteHref);
        target = FilePath.removeTrailingSlashes(absoluteHref);
        target_ = target + "/";
    }

    @Override
    public String getAbsoluteHref() {
        return target_;
    }

    @Override
    public String getRelativeHrefTo(String from) {
        if (from == null)
            throw new NullPointerException("from");
        if (!from.startsWith("/"))
            throw new IllegalArgumentException("Not absolute: " + from);

        // Remove the "basename" at first.
        String from_ = from.substring(0, from.lastIndexOf('/') + 1);

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

}