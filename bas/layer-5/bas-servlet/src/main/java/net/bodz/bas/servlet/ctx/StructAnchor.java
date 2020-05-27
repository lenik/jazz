package net.bodz.bas.servlet.ctx;

import java.net.MalformedURLException;

public abstract class StructAnchor
        extends AbstractAnchor {

    /**
     * @return Local path in absolute form.
     */
    protected abstract String getLocalPath();

    protected abstract IAnchor create(String localPath);

    @Override
    public IAnchor join(String spec) {
        if (spec == null)
            throw new NullPointerException("spec");
        if (spec.startsWith("/"))
            return new PathAnchor(spec);

        int ss = spec.indexOf("://");
        if (ss != -1 && spec.indexOf('/') > ss)
            try {
                return new URLAnchor(spec);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }

        String localPath = getLocalPath();
        String localPath_ = localPath.substring(0, localPath.lastIndexOf('/') + 1);
        return create(localPath_ + spec);
    }

}
