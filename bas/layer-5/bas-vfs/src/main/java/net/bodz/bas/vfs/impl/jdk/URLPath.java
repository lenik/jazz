package net.bodz.bas.vfs.impl.jdk;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.vfs.IVfsDevice;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.align.IPathAlignment;

public class URLPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    URL url;

    public URLPath(String url)
            throws MalformedURLException {
        this(new URL(url));
    }

    public URLPath(URL url) {
    }

    @Override
    public IPathAlignment getAlignment() {
        return IPathAlignment.ROOT_LAYER;
    }

    @Override
    public IVfsDevice getDevice() {
        return URLVfsDevice.getInstance();
    }

    @Override
    public URLFile resolve() {
        return new URLFile(this);
    }

    @Override
    public String getLocalPath() {
        return null;
    }

    @Override
    public String[] getLocalEntries() {
        return null;
    }

    @Override
    public URL toURL() {
        return url;
    }

}
