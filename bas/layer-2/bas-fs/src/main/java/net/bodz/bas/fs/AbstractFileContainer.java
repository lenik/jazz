package net.bodz.bas.fs;

import java.io.IOException;

import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.fs.path.IPath;
import net.bodz.bas.type.traits.EmptyAttributes;
import net.bodz.bas.type.traits.IAttributes;

public abstract class AbstractFileContainer
        implements IFileContainer {

    private final IFile deviceFile;

    public AbstractFileContainer(IFile deviceFile) {
        this.deviceFile = deviceFile;
    }

    @Override
    public IAttributes getAttributes() {
        return EmptyAttributes.getInstance();
    }

    @Override
    public IFile getDeviceFile() {
        return deviceFile;
    }

    @Override
    public IFile getRootFile() {
        return getRootPath().toFile();
    }

    @Override
    public IFile get(String containerSpecificPath) {
        IPath path = resolve(containerSpecificPath);
        return get(path);
    }

    /**
     * @return Empty string (<code>""</code>).
     */
    @Override
    public String getLabel() {
        return "";
    }

    /**
     * @return <code>0</code>.
     */
    @Override
    public long getCapacity() {
        return 0;
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
    public long getFreeSpace() {
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
        throw new NotImplementedException();
    }

    @Override
    public void removeFileListener(IFile watchFile, IFileListener listener) {
        throw new NotImplementedException();
    }

    @Override
    public synchronized void addJunction(String junctionPoint, IFile targetFile)
            throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public synchronized void removeJunction(String junctionPoint)
            throws IOException {
        throw new NotImplementedException();
    }

}
