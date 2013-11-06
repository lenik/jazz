package net.bodz.bas.vfs.impl.zip;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.MultiEntryPath;

/**
 * Jar path: jar:FILE!/entry-name
 */
public class ZipEntryPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;

    private String zipName;

    public ZipEntryPath(String protocol, String zipName, String localPath) {
        super(protocol, localPath);
        this.zipName = zipName;
    }

    public ZipEntryPath(String protocol, String zipName, String[] entries, boolean entered) {
        super(protocol, entries, entered);
        this.zipName = zipName;
    }

    public String getZipName() {
        return zipName;
    }

    @Override
    public String getDeviceSpec() {
        return zipName;
    }

    @Override
    public String getDeviceSpecSeparator() {
        return ":/";
    }

    @Override
    protected IPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new ZipEntryPath(getProtocol(), zipName, entries, entered);
    }

}
