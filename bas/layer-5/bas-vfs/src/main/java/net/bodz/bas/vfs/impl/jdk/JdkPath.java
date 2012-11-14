package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.ProtocolPath;

public class JdkPath
        extends ProtocolPath {

    private static final long serialVersionUID = 1L;

    public JdkPath(String protocol, String localPath) {
        super(protocol, localPath);
    }

    public JdkPath(String protocol, String[] entries) {
        super(protocol, entries);
    }

    @Override
    protected IPath createLocal(String[] entries)
            throws BadPathException {
        return new JdkPath(getProtocol(), entries);
    }

}
