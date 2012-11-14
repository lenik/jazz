package net.bodz.bas.vfs.impl.jdk;

import java.net.MalformedURLException;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.PathFormat;

public class URLVfsDevice
        extends AbstractVfsDevice {

    // String hostspec; // == device-name.
    URLFile rootFile;

    public URLVfsDevice(URLVfsDriver driver, String hostspec, String protocol) {
        super(driver, hostspec, protocol);
    }

    @Override
    public URLFile getRootFile() {
        return rootFile;
    }

    @Override
    public URLPath parse(String localPath)
            throws BadPathException {
        String url = localPath;
        try {
            return new URLPath(url);
        } catch (MalformedURLException e) {
            throw new BadPathException(url, e);
        }
    }

    @Override
    public URLFile resolve(String localPath)
            throws BadPathException, FileResolveException {
        URLPath urlPath = parse(localPath);
        return urlPath.resolve();
    }

    @Override
    public String format(String localPath, PathFormat pathFormat) {
        return localPath;
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo)
            throws BadPathException {
        return false;
    }

}
