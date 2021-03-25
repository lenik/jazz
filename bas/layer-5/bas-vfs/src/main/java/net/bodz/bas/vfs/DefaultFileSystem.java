package net.bodz.bas.vfs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TreeSet;

import net.bodz.bas.meta.codegen.IndexedTypeLoader;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.vfs.context.IVfsVars;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

@IndexedTypeLoader(IVfsDriverProvider.class)
public class DefaultFileSystem
        implements
            IFileSystem {

    private final Map<String, IVfsDriver> driverMap;
    private final TreeSet<VfsDriverKey> fallbacks;

    public DefaultFileSystem() {
        driverMap = new HashMap<String, IVfsDriver>();
        fallbacks = new TreeSet<VfsDriverKey>(PriorityComparator.INSTANCE);

        for (IVfsDriverProvider provider : ServiceLoader.load(IVfsDriverProvider.class))
            for (IVfsDriver driver : provider.getDrivers())
                driver.configure(this);
    }

    public Map<String, IVfsDriver> getDriverMap() {
        return driverMap;
    }

    @Override
    public IVfsDriver getDriver(String protocol) {
        IVfsDriver driver = driverMap.get(protocol);
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
        IVfsDriver inuse = driverMap.get(protocol);
        if (inuse != null) {
            return false;
        } else {
            driverMap.put(protocol, driver);
            return true;
        }
    }

    @Override
    public void removeDriver(String protocol) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        driverMap.remove(protocol);
    }

    @Override
    public void addGenericDriver(IVfsDriver driver, int priority) {
        VfsDriverKey key = new VfsDriverKey(priority, driver);
        fallbacks.add(key);
    }

    @Override
    public void removeGenericDriver(IVfsDriver driver) {
        Iterator<VfsDriverKey> iterator = fallbacks.iterator();
        while (iterator.hasNext()) {
            VfsDriverKey key = iterator.next();
            if (key.driver == driver)
                iterator.remove();
        }
    }

    @Override
    public IPath parse(String path)
            throws BadPathException {
        if (path == null)
            throw new NullPointerException("path");
        int colon = path.indexOf(':');
        String protocol = colon == -1 ? null : path.substring(0, colon);

        IVfsDriver explicitDriver = driverMap.get(protocol);
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
    public final IFile resolve(IPath path)
            throws FileResolveException {
        return resolve(path, FileResolveOptions.DEFAULT);
    }

    @Override
    public IFile resolve(IPath path, FileResolveOptions options)
            throws FileResolveException {
        if (path == null)
            throw new NullPointerException("path");

        IVfsDriver driver = getDriver(path.getProtocol());
        if (driver == null)
            throw new FileResolveException("No VFS driver for protocol " + path.getProtocol());

        IFile file = driver.resolve(path, options);
        return file;
    }

    @Override
    public synchronized IPath getContextPath() {
        IFile contextFile = IVfsVars.workdir.get();
        return contextFile.getPath();
    }

    @Override
    public void setContextPath(IPath contextPath) {
        IFile contextFile = resolve(contextPath);
        IVfsVars.workdir.set(contextFile);
    }

}

class VfsDriverKey
        implements
            IPriority {

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
