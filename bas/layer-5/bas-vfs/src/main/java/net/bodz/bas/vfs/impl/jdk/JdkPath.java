package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.ProtocolPath;
import net.bodz.bas.vfs.path.IPath;

public class JdkPath
        extends ProtocolPath {

    private static final long serialVersionUID = 1L;

    public JdkPath(String protocol, String localPath) {
        super(protocol, localPath);
    }

    @Override
    protected IPath parseLocal(String localPath)
            throws BadPathException {
        return new JdkPath(getProtocol(), localPath);
    }

}
