package net.bodz.bas.vfs.impl.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.MultiEntryPath;

public class NioPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;

    private final String rootName;

    public NioPath(String protocol, String rootName, String localPath) {
        super(protocol, localPath);
        this.rootName = rootName;
    }

    public NioPath(String protocol, String rootName, String[] entries, boolean entered) {
        super(protocol, entries, entered);
        this.rootName = rootName;
    }

    public String getRootName() {
        return rootName;
    }

    @Override
    public String getDeviceSpec() {
        return rootName;
    }

    @Override
    public String getDeviceSpecSeparator() {
        return "/";
    }

    @Override
    protected IPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new NioPath(getProtocol(), rootName, entries, entered);
    }

    public Path toPath() {
        String _pathstr = rootName + "/" + getLocalPath();
        return Paths.get(_pathstr);
    }

}
