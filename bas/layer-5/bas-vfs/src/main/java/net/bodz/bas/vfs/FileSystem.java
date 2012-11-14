package net.bodz.bas.vfs;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import net.bodz.bas.util.order.IPriority;
import net.bodz.bas.util.order.PriorityComparator;
import net.bodz.bas.vfs.context.VFSColos;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class FileSystem
        implements IFileSystem {

    private final Map<String, IVfsDriver> protocols;
    private final TreeSet<VfsDriverKey> fallbacks;

    private IPath contextPath;

    public FileSystem() {
        protocols = new HashMap<String, IVfsDriver>();
        fallbacks = new TreeSet<VfsDriverKey>(PriorityComparator.INSTANCE);

        contextPath = VFSColos.workdir.get().getPath();
    }

    @Override
    public IVfsDriver getDriver(String protocol) {
        IVfsDriver driver = protocols.get(protocol);
        if (driver == null) {
            for (VfsDriverKey fallback : fallbacks)
                if (fallback.driver.accepts(protocol)) {
                    driver = fallback.driver;
                    break;
                }
        }
        return driver;
    }

    @Override
    public boolean addDriver(String protocol, IVfsDriver driver) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        if (driver == null)
            throw new NullPointerException("driver");
        IVfsDriver inuse = protocols.get(protocol);
        if (inuse != null) {
            return false;
        } else {
            protocols.put(protocol, driver);
            return true;
        }
    }

    @Override
    public void removeDriver(String protocol) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        protocols.remove(protocol);
    }

    @Override
    public void addGenericDriver(IVfsDriver driver, int priority) {
        VfsDriverKey key = new VfsDriverKey(priority, driver);
        fallbacks.add(key);
    }

    @Override
    public void removeGenericDriver(IVfsDriver driver) {
        fallbacks.remove(driver);
    }

    @Override
    public IPath parse(String path)
            throws BadPathException {
        if (path == null)
            throw new NullPointerException("path");
        int colon = path.indexOf(':');
        String protocol = colon == -1 ? null : path.substring(0, colon);

        IVfsDriver explicitDriver = protocols.get(protocol);
        if (explicitDriver != null) {
            // assert explicitDriver.accepts(protocol);
            // String protocolSpecificPath = path.substring(colon + 1);
            return explicitDriver.parse(path);
        }

        for (VfsDriverKey key : fallbacks) {
            IVfsDriver driver = key.driver;
            if (!driver.accepts(protocol))
                continue;
            IPath parsedPath = driver.parse(path);
            return parsedPath;
        }

        IPath context = getContextPath();
        return context.join(path);
    }

    @Override
    public IFile resolve(IPath path)
            throws FileResolveException {
        if (path == null)
            throw new NullPointerException("path");

        IVfsDriver driver = getDriver(path.getProtocol());
        if (driver == null)
            throw new FileResolveException("No VFS driver for protocol " + path.getProtocol());

        IFile file = driver.resolve(path);
        return file;
    }

    @Override
    public IPath getContextPath() {
        if (contextPath == null)
            throw new IllegalStateException("Default context wasn't set");
        return contextPath;
    }

    @Override
    public void setContextPath(IPath contextPath) {
        if (contextPath == null)
            throw new NullPointerException("contextPath");
        this.contextPath = contextPath;
    }

}

class VfsDriverKey
        implements IPriority {

    public final int priority;
    public final IVfsDriver driver;

    public VfsDriverKey(int priority, IVfsDriver driver) {
        this.priority = priority;
        this.driver = driver;
    }

    @Override
    public int getPriority() {
        return priority;
    }

}
