package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.AbstractPath;

public class URLPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    public URLPath(String url) {
        super(url);
    }

    public String getURL() {
        return localPath;
    }

    @Override
    public IVolume getVolume() {
        return URLVolume.getInstance();
    }

}
