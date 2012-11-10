package net.bodz.bas.vfs.impl.jdk;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.PathFormat;

public class URLVfsDevice
        extends AbstractVfsDevice {

    List<URLFile> rootFiles = new ArrayList<>();

    public URLVfsDevice() {
        super(URLVfsDriver.getInstance());
    }

    @Override
    public List<? extends URLFile> getRootFiles() {
        return rootFiles;
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

    private static final URLVfsDevice instance = new URLVfsDevice();

    public static URLVfsDevice getInstance() {
        return instance;
    }

}
