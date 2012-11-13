package net.bodz.bas.vfs.impl.jdk;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.ProtocolPath;

public class URLPath
        extends ProtocolPath {

    private static final long serialVersionUID = 1L;

    private URL url;

    public URLPath(String url)
            throws MalformedURLException {
        this(new URL(url));
    }

    public URLPath(URL url) {
        super(url.getProtocol(), url.getPath());
        this.url = url;
    }

    @Override
    protected IPath parseLocal(String localPath)
            throws BadPathException {

    }

    @Override
    public URL toURL() {
        return url;
    }

}
