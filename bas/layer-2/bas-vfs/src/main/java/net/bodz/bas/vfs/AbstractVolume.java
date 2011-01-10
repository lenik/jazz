package net.bodz.bas.vfs;

import java.io.IOException;

import net.bodz.bas.type.traits.EmptyAttributes;
import net.bodz.bas.type.traits.IAttributes;

import org.apache.commons.vfs.Capability;

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
    public IFile getRootFile() {
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
    public void addFileListener(IFile watchFile, IFileListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeFileListener(IFile watchFile, IFileListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized void addJunction(String junctionPoint, IFile targetFile)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized void removeJunction(String junctionPoint)
            throws IOException {
        throw new UnsupportedOperationException();
    }

}
