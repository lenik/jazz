package net.bodz.bas.fs;

import java.io.IOException;

import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.fs.path.IPath;
import net.bodz.bas.type.traits.IAttributes;

/**
 * @see org.apache.commons.vfs.FileSystem
 */
public interface IFileContainer {

    /**
     * Same as:
     * 
     * <pre>
     * {
     *     getRootFile().getPath().getParentLayer().toFile();
     * }
     * </pre>
     * 
     * @return <code>null</code> If this volume is a top-level (physical) volume.
     */
    IFile getDeviceFile();

    /**
     * @return non-<code>null</code> root path.
     */
    IPath getRootPath();

    /**
     * @return non-<code>null</code> root file.
     */
    IFile getRootFile();

    // IFsEntry resolveEntry(String path);
    // IFsEntry resolveEntry(IPath path);

    IFile get(IPath path);

    IFile get(String containerSpecificPath);

    IPath resolve(String containerSpecificPath);

    IAttributes getAttributes();

    // /**
    // * @see FileSystem#hasCapability(org.apache.commons.vfs.Capability)
    // */
    // boolean hasCapability(Capability capability);

    /**
     * @return non-<code>null</code> volume label. If a volume doesn't have a label, an empty string
     *         should be returned.
     */
    String getLabel();

    /**
     * Get the capacity of the volume.
     * 
     * @return Positive integer, or 0 if unknown.
     */
    long getCapacity();

    /**
     * Get the available space in bytes.
     * 
     * @return Positive integer, or 0 if unknown.
     */
    long getFreeSpace();

    /**
     * @return Positive (generally 2^n) cluster size, or 0 if the cluster size is unknown.
     */
    int getClusterSize();

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
     * @throws NotImplementedException
     *             If the file system doesn't support file listener.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     * @see org.apache.commons.vfs.FileSystem#addListener(org.apache.commons.vfs.FileObject,
     *      org.apache.commons.vfs.FileListener)
     */
    void addFileListener(IFile watchFile, IFileListener listener);

    /**
     * @throws NotImplementedException
     *             If the file system doesn't support file listener.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    void removeFileListener(IFile watchFile, IFileListener listener);

    /**
     * @throws NotImplementedException
     *             If the file system doesn't support junction.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     * @see org.apache.commons.vfs.FileSystem#addJunction(String, org.apache.commons.vfs.FileObject)
     */
    void addJunction(String junctionPoint, IFile targetFile)
            throws IOException;

    /**
     * @throws NotImplementedException
     *             If the file system doesn't support junction.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    void removeJunction(String junctionPoint)
            throws IOException;

    // File replicateFile(IFile file, FileSelector selector)
    // throws IOException

}
