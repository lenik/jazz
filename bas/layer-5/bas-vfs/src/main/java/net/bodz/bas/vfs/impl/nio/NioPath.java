package net.bodz.bas.vfs.impl.nio;

import net.bodz.bas.vfs.path.MultiEntryPath;

public class NioPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;

    String scheme;

    public NioPath(String protocol, String scheme, String localPath) {
        super(protocol, localPath);
        this.scheme = scheme;
    }

    public NioPath(String protocol, String scheme, String[] entries, boolean entered) {
        super(protocol, entries, entered);
        this.scheme = scheme;
    }

}
