package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;

public abstract class AbstractVfsDriver
        extends AbstractVfsProtocolHandler
        implements IVfsDriver {

    // IFileSystem fileSystem;

    // @Override
    // public IFileSystem getFileSystem() {
    // return fileSystem;
    // }

    @Override
    public boolean hasCapability(Capability capability) {
        return false;
    }

    /**
     * @return <code>0</code>.
     */
    @Override
    public int getTimeAccuracy() {
        return 0;
    }

}
