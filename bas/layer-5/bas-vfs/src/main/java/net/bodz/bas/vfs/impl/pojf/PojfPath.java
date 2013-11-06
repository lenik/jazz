package net.bodz.bas.vfs.impl.pojf;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.MultiEntryPath;

public class PojfPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;

    private String driveName;

    public PojfPath(String protocol, String driveName, String localPath) {
        super(protocol, localPath);
        this.driveName = driveName;
    }

    public PojfPath(String protocol, String driveName, String[] entries, boolean entered) {
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
        return new PojfPath(getProtocol(), driveName, entries, entered);
    }

}
