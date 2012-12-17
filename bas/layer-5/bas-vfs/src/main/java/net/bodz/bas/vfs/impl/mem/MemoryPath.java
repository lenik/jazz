package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.MultiEntryPath;

public class MemoryPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;
    public static final String SCOPE_SEPARATOR = ":/";

    private String scope;

    public MemoryPath(String protocol, String scope, String localPath) {
        super(protocol, localPath);
        this.scope = scope;
    }

    public MemoryPath(String protocol, String scope, String[] entries, boolean entered) {
        super(protocol, entries, entered);
        this.scope = scope;
    }

    @Override
    public String getDeviceSpec() {
        return scope;
    }

    @Override
    public String getDeviceSpecSeparator() {
        return SCOPE_SEPARATOR;
    }

    @Override
    protected IPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new MemoryPath(getProtocol(), scope, entries, entered);
    }

}
