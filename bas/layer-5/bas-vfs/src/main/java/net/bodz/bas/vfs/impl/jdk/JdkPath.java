package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;
import net.bodz.bas.vfs.path.MultiEntryPath;

public class JdkPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;

    private String driveName;

    public JdkPath(String protocol, String driveName, String localPath) {
        super(protocol, localPath);
        this.driveName = driveName;
    }

    public JdkPath(String protocol, String driveName, String[] entries, boolean entered) {
        super(protocol, entries, entered);
        this.driveName = driveName;
    }

    public String getDriveName() {
        return driveName;
    }

    @Override
    public String getDeviceSpec() {
        return driveName;
    }

    @Override
    public String getDeviceSpecSeparator() {
        return ":";
    }

    @Override
    protected IPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new JdkPath(getProtocol(), driveName, entries, entered);
    }

    @Override
    protected String formatLocal(PathFormat format) {
        String localPath = getLocalPath();
        return SEPARATOR + localPath;
    }

}
