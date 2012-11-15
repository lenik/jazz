package net.bodz.bas.vfs.impl.jdk;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class URLPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    private URL url;

    public URLPath(String url)
            throws MalformedURLException {
        this(new URL(url));
    }

    public URLPath(URL url) {
        this.url = url;
    }

    @Override
    public URL toURL() {
        return url;
    }

    @Override
    public String getProtocol() {
        return url.getProtocol();
    }

    @Override
    public String getDeviceSpec() {
        String userInfo = url.getUserInfo();
        String authority = url.getAuthority();

        if (userInfo == null && authority == null)
            return null;

        StringBuilder buf = new StringBuilder();
        buf.append("//");

        if (userInfo != null) {
            buf.append(userInfo);
            buf.append("@");
        }
        if (authority != null)
            buf.append(authority);

        return buf.toString();
    }

    @Override
    public String getDeviceSpecSeparator() {
        return "/";
    }

    @Override
    public String getLocalPath() {
        String path = url.getPath();
        // String pathWithQuery= url.getFile();
        return path;
    }

    @Override
    public String[] getLocalEntries() {
        String localPath = getLocalPath();
        String[] entries = localPath.split("/");
        return entries;
    }

    @Override
    protected IPath createLocal(String localPath)
            throws BadPathException {
        String urlPath = localPath;
        if (!urlPath.startsWith("/"))
            urlPath = "/" + urlPath;
        try {
            return new URLPath(urlPath);
        } catch (MalformedURLException e) {
            throw new BadPathException(e.getMessage(), e);
        }
    }

    @Override
    public URLFile resolve()
            throws FileResolveException {
        URLVfsDriver driver = URLVfsDriver.getInstance();
        URLFile file = driver.resolve(this);
        return file;
    }

}
