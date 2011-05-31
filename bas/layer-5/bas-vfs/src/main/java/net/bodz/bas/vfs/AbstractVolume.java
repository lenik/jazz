package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;

import net.bodz.bas.traits.EmptyAttributes;
import net.bodz.bas.traits.IAttributes;

public abstract class AbstractVolume
        implements IVolume {

    @Override
    public IAttributes getAttributes() {
        return EmptyAttributes.getInstance();
    }

    @Override
    public boolean hasCapability(Capability capability) {
        return false;
    }

    @Override
    public IFile getDeviceFile() {
        return null;
    }

    @Override
    public IFile getRootFile()
            throws FileResolveException {
        return getRootPath().toFile();
    }

    /**
     * Implementation:
     * 
     * Return empty string (<code>""</code>).
     */
    @Override
    public String getLabel() {
        return "";
    }

    /**
     * Implementation: Return <code>null</code>.
     */
    @Override
    public Long getCapacity() {
        return null;
    }

    /**
     * Implementation: Return <code>null</code>.
     */
    @Override
    public Long getFreeSpace() {
        return null;
    }

    /**
     * @return <code>0</code>.
     */
    @Override
    public int getClusterSize() {
        return 0;
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
        throw new VFSException("File listener is unsupported");
    }

    @Override
    public void removeFileListener(IFile watchFile, IFileListener listener)
            throws VFSException {
        throw new VFSException("File listener is unsupported");
    }

    @Override
    public synchronized void addJunction(String junctionPoint, IFile targetFile)
            throws VFSException {
        throw new VFSException("Junction is unsupported");
    }

    @Override
    public synchronized void removeJunction(String junctionPoint)
            throws VFSException {
        throw new VFSException("Junction is unsupported");
    }

}
