package net.bodz.bas.vfs.impl.jdk;

import java.net.MalformedURLException;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class URLVfsDevice
        extends AbstractVfsDevice {

    // String hostspec; // == device-name.
    URLFile rootFile;

    /**
     * @param hostSpec
     *            Like <code>"//www.example.com:80"</code>.
     */
    public URLVfsDevice(URLVfsDriver driver, String protocol, String hostSpec) {
        super(driver, protocol, hostSpec);
    }

    public String getHostSpec() {
        String deviceSpec = getDeviceSpec();
        if (deviceSpec == null)
            return null;
        if (!deviceSpec.startsWith("//"))
            return null;
        String hostSpec = deviceSpec.substring(2);
        return hostSpec;
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
    public IFile resolve(IPath _path)
            throws FileResolveException {
        String url = _path.toString();
        return new URLPath(url);
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
