package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;

public abstract class AbstractVfsDriver
        implements IVfsDriver {

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

    @Override
    public void addFileListener(IFile watchFile, IFileListener listener)
            throws VFSException {
        throw new UnsupportedOperationException("file-listener");
    }

    @Override
    public void removeFileListener(IFile watchFile, IFileListener listener)
            throws VFSException {
        throw new UnsupportedOperationException("file-listener");
    }

    @Override
    public synchronized void addJunction(String junctionPoint, IFile targetFile)
            throws VFSException {
        throw new UnsupportedOperationException("junction");
    }

    @Override
    public synchronized void removeJunction(String junctionPoint)
            throws VFSException {
        throw new UnsupportedOperationException("junction");
    }

    public abstract IVfsDevice getDevice(IFile deviceFile);

}
