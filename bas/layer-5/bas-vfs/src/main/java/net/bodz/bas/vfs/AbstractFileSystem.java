package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;

import net.bodz.bas.traits.EmptyAttributes;
import net.bodz.bas.traits.IAttributes;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractFileSystem
        implements IFileSystem {

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
    public final IPath[] getRootPaths() {
        IFile[] rootFiles = getRootFiles();
        IPath[] rootPaths = new IPath[rootFiles.length];
        for (int i = 0; i < rootFiles.length; i++)
            rootPaths[i] = rootFiles[i].getPath();
        return rootPaths;
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
