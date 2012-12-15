package net.bodz.bas.vfs.impl.url;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.MultiEntryPath;
import net.bodz.bas.vfs.path.PathFormats;

/**
 * <ul>
 * <li>localPath: realPath "?" query
 * <li>baseName: localEntries[-1]
 * <li>realPath: localPath without query
 * <li>realBaseName: realEntries[-1]
 * </ul>
 */
public abstract class URLPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;

    final String scheme;

    String query;
    String fragmentId;

    transient URL url;

    public URLPath(String scheme, String localPath) {
        super(localPath);
        this.scheme = scheme;
    }

    public URLPath(String scheme, String[] entries, boolean entered) {
        super(entries, entered);
        this.scheme = scheme;
    }

    @Override
    protected abstract URLPath createLocal(String[] entries, boolean entered)
            throws BadPathException;

    @Override
    protected abstract URLPath createLocal(String localPath)
            throws BadPathException;

    public static URLPath parse(URL url) {
        URLPath result;
        String localPath = url.getFile();

        String scheme = url.getProtocol();
        URLFormat format = URLSchemes.getSchemeFormat(scheme);

        switch (format) {
        case plain:
            while (localPath.startsWith("/"))
                localPath = localPath.substring(1);
            result = new PlainURLPath(scheme, localPath);
            break;

        case hostPathQuery:
            String user = null;
            String password = null;
            String userInfo = url.getUserInfo();
            if (userInfo != null) {
                user = StringPart.before(userInfo, ':', userInfo);
                password = StringPart.after(userInfo, ':');
            }

            String host = url.getHost();
            int port = url.getPort();

            result = new HostPathQueryURLPath(scheme, user, password, host, port, localPath);
            break;

        case hostQuery:
            result = new HostQueryURLPath(scheme, localPath);
            break;

        case nestedPath:
            String mixedPath = url.getPath();
            int exclsl = mixedPath.indexOf("!/");
            String nestedStr;
            String realPath;
            if (exclsl != -1) {
                nestedStr = mixedPath.substring(0, exclsl);
                realPath = mixedPath.substring(exclsl + 2);
            } else {
                nestedStr = mixedPath;
                realPath = "";
            }
            IPath nestedPath = VFS.parse(nestedStr);
            result = new NestedURLPath(scheme, nestedPath, realPath);
            break;

        default:
            throw new UnexpectedException("Unknown URL format: " + format);
        }

        result.setFragmentId(url.getRef());
        return result;
    }

    @Override
    public String getProtocol() {
        return scheme;
    }

    public URLFormat getFormat() {
        return URLSchemes.getSchemeFormat(scheme);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        String[] entries = getLocalEntries();
        assert entries.length != 0;
        int lastEntry = entries.length - 1;
        String entry = entries[lastEntry];
        String baseName = StringPart.before(entry, '?', entry);

        this.query = query;

        if (query != null)
            entry = baseName + '?' + query;
        else
            entry = baseName;
        entries[lastEntry] = entry;
    }

    public String getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(String fragmentId) {
        this.fragmentId = fragmentId;
    }

    @Override
    public URLFile resolve()
            throws FileResolveException {
        URLVfsDriver driver = URLVfsDriver.getInstance();
        URLFile file = driver.resolve(this);
        return file;
    }

    @Override
    public synchronized URL toURL() {
        if (url == null) {
            String urlStr = format(PathFormats.REPR);
            try {
                url = new URL(urlStr);
            } catch (MalformedURLException e) {
                throw new IllegalStateException("Illegal URL has been parsed: " + urlStr, e);
            }
        }
        return url;
    }

}
