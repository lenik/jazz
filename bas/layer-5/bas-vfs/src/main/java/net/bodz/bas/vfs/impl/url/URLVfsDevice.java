package net.bodz.bas.vfs.impl.url;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class URLVfsDevice
        extends AbstractVfsDevice {

    URLFormat format;
    URLPath rootPath;
    URLFile rootFile;

    /**
     * @param deviceSpec
     *            Non-<code>null</code> connection spec used as device spec. A connection spec
     *            includes user-info, hostname, and port number. All fields are optional, e.g.,
     *            <code>"foo:bAr@example.com:1200"</code>.
     */
    public URLVfsDevice(URLVfsDriver driver, String protocol, String deviceSpec) {
        super(driver, protocol, deviceSpec);

        if (deviceSpec == null)
            throw new NullPointerException("deviceSpec");

        format = URLSchemes.getSchemeFormat(protocol);
        if (format == null)
            throw new IllegalArgumentException("Unsupported URL protocol: " + protocol);

        String rootPathStr;
        switch (format) {
        case plain:
            String driveName = deviceSpec;
            if (driveName.isEmpty())
                // rootStr = protocol + ":/";
                rootPathStr = protocol + ":///";
            else
                rootPathStr = protocol + "://" + driveName + ":/";
            break;
        case hostPathQuery: // http, https, ftp, gopher
            String userRemote = deviceSpec;
            rootPathStr = protocol + "://" + userRemote + "/";
            break;
        case hostQuery: // mailto
            String email = deviceSpec;
            rootPathStr = protocol + ":" + email + "/";
            break;
        case nestedPath: // jar
            String outerPath = deviceSpec;
            rootPathStr = protocol + ":" + outerPath + "!" + "/";
            break;
        default:
            throw new UnexpectedException("Bad URLFormat: " + format);
        }

        URL rootURL;
        try {
            rootURL = new URL(rootPathStr);
        } catch (MalformedURLException e) {
            throw new UnexpectedException("Can't construct a root URL path: " + rootPathStr, e);
        }

        rootPath = URLPath.parse(rootURL);
        rootFile = new URLFile(this, rootPath);
    }

    @Override
    public URLPath getRootPath() {
        return rootPath;
    }

    @Override
    public URLFile getRootFile() {
        return rootFile;
    }

    @Override
    public URLPath parse(String localPath)
            throws BadPathException {
        return rootPath.createLocal(localPath);
    }

    @Override
    public IFile resolve(IPath _path)
            throws FileResolveException {
        URLPath path = (URLPath) _path;
        return new URLFile(this, path);
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo)
            throws BadPathException {
        if (localPathFrom == null)
            throw new NullPointerException("localPathFrom");
        if (localPathTo == null)
            throw new NullPointerException("localPathTo");

        // You can't rename a remote file.
        // TODO Try to rename a local file:/// file?
        if (localPathFrom.equals(localPathTo))
            return true;
        else
            return false;
    }

}
