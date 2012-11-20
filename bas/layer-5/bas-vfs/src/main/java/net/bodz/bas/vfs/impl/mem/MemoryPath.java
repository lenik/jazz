package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.ProtocolPath;

public class MemoryPath
        extends ProtocolPath {

    private static final long serialVersionUID = 1L;
    public static final String SCOPE_SEPARATOR = ":";

    private String scope;

    public MemoryPath(String protocol, String scope, String localPath) {
        super(protocol, localPath);
        this.scope = scope;
    }

    public MemoryPath(String protocol, String scope, String[] entries) {
        super(protocol, entries);
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
    protected IPath createLocal(String[] entries)
            throws BadPathException {
        return new MemoryPath(getProtocol(), scope, entries);
    }

}