package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;
import org.apache.commons.vfs.FileSystem;

/**
 * @see org.apache.commons.vfs.FileSystem
 */
public interface IVfsDriver {

    // /**
    // * @see FileProvider#getCapabilities()
    // */
    // Collection<Capability> getCapabilities();

    /**
     * @see FileSystem#hasCapability(org.apache.commons.vfs.Capability)
     */
    boolean hasCapability(Capability capability);

    /**
     * Returns the accuracy of file time attributes, like created-time, last-mod-time, and
     * access-time in ms.
     * 
     * @return ms of the least duration, 0 if unknown, or positive value.
     * @see org.apache.commons.vfs.FileSystem#getLastModTimeAccuracy()
     */
    int getTimeAccuracy();

    /**
     * @param watchFile
     *            non-<code>null</code> file to watch changes for, generally it's a folder whose
     *            immediated children are watched.
     * @param listener
     *            Be called when the file to be watched has changed.
     * @throws VFSException
     *             If the file system doesn't support file listener, or failed to register the
     *             listener.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     * @see org.apache.commons.vfs.FileSystem#addListener(org.apache.commons.vfs.FileObject,
     *      org.apache.commons.vfs.FileListener)
     */
    void addFileListener(IFile watchFile, IFileListener listener)
            throws VFSException;

    /**
     * @throws VFSException
     *             If the file system doesn't support file listener, or failed to removethe
     *             listener.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    void removeFileListener(IFile watchFile, IFileListener listener)
            throws VFSException;

    /**
     * @throws VFSException
     *             If the file system doesn't support junction, or failed to setup the junction.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     * @see org.apache.commons.vfs.FileSystem#addJunction(String, org.apache.commons.vfs.FileObject)
     */
    void addJunction(String junctionPoint, IFile targetFile)
            throws VFSException;

    /**
     * @throws VFSException
     *             If the file system doesn't support junction, or failed to remove the junction.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    void removeJunction(String junctionPoint)
            throws VFSException;

    // File replicateFile(IFile file, FileSelector selector)
    // throws IOException

}
