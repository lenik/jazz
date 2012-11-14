package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.ProtocolPath;

public class MemoryPath
        extends ProtocolPath {

    private static final long serialVersionUID = 1L;

    public MemoryPath(String protocol, String localPath) {
        super(protocol, localPath);
    }

    public MemoryPath(String protocol, String[] entries) {
        super(protocol, entries);
    }

    @Override
    protected IPath createLocal(String[] entries)
            throws BadPathException {
        return new MemoryPath(getProtocol(), entries);
    }

}
