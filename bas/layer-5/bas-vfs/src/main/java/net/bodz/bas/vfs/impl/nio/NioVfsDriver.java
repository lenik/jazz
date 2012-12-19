package net.bodz.bas.vfs.impl.nio;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class NioVfsDriver
        extends AbstractVfsDriver {

    final String protocol;

    public NioVfsDriver(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    @Override
    protected NioPath _parse(String protocol, String _path)
            throws BadPathException {
        // Ignore the protocol.
    }

    @Override
    public NioFile resolve(IPath _path)
            throws FileResolveException {
    }

    private static final NioVfsDriver instance = new NioVfsDriver("nio");

    public static NioVfsDriver getInstance() {
        return instance;
    }

}
