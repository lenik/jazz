package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.vfs.IVfsDevice;
import net.bodz.bas.vfs.path.DefaultPath;

public class JdkPath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    public JdkPath(IVfsDevice device, String localPath) {
        super(device, localPath);
    }

}
