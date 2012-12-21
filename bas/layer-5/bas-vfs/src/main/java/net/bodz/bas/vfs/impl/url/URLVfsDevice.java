package net.bodz.bas.vfs.impl.url;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.NotLinkException;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
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
    public URLFile resolve(String localPath)
            throws BadPathException, FileResolveException {
        return (URLFile) super.resolve(localPath);
    }

    @Override
    public URLFile resolve(IPath _path)
            throws FileResolveException {
        URLPath path = (URLPath) _path;
        return new URLFile(this, path);
    }

    public String _parse(String localPath) {
        URLPath joined = (URLPath) rootPath.join(localPath);
        return joined.getURLString();
    }

    public URL _resolve(String localPath) {
        URLPath joined = (URLPath) rootPath.join(localPath);
        return joined.toURL();
    }

    @Override
    public boolean move(String localPathFrom, String localPathTo, CopyOption... options)
            throws BadPathException {
        if (localPathFrom == null)
            throw new NullPointerException("localPathFrom");
        if (localPathTo == null)
            throw new NullPointerException("localPathTo");

        if (localPathFrom.equals(localPathTo))
            return true;

        URL srcURL = _resolve(localPathFrom);
        URL destURL = _resolve(localPathTo);

        assert srcURL.getProtocol().equals(destURL.getProtocol());
        switch (srcURL.getProtocol()) {
        case "file":
            File srcFile = FileURL.toFile(srcURL, null);
            if (srcFile == null)
                throw new IllegalArgumentException("Illegal src file URL: " + srcURL);

            File destFile = FileURL.toFile(destURL, null);
            if (destFile == null)
                throw new IllegalArgumentException("Illegal dest file URL: " + destURL);

            return srcFile.renameTo(destFile);

        default:
            return false;
        }
    }

    @Override
    public boolean createLink(String localPath, String targetSpec, boolean symbolic)
            throws IOException {
        URL link = _resolve(localPath);
        switch (link.getProtocol()) {
        case "file":
            File linkFile = FileURL.toFile(link, null);
            if (linkFile == null)
                throw new IllegalArgumentException("Illegal file URL: " + link);
            return FilePath.createLink(linkFile, targetSpec, symbolic);

        default:
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String readSymbolicLink(String localPath)
            throws NotLinkException, IOException {
        return null;
    }

}
