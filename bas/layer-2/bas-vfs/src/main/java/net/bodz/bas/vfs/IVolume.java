package net.bodz.bas.vfs;

import java.io.IOException;

import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.type.traits.IAttributes;
import net.bodz.bas.vfs.path.IPath;

import org.apache.commons.vfs.Capability;
import org.apache.commons.vfs.FileSystem;

/**
 * @see org.apache.commons.vfs.FileSystem
 */
public interface IVolume {

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

    IPath resolve(String localPath);

    IFile resolveFile(String localPath);

    IAttributes getAttributes();

    /**
     * @see FileSystem#hasCapability(org.apache.commons.vfs.Capability)
     */
    boolean hasCapability(Capability capability);

    /**
     * @return non-<code>null</code> volume label. If a volume doesn't have a label, an empty string
     *         should be returned.
     */
    String getLabel();

    /**
     * Get total space in bytes.
     * 
     * @return <code>null</code> if unknown.
     */
    Long getCapacity();

    /**
     * Get available space in bytes.
     * 
     * @return <code>null</code> unknown.
     */
    Long getFreeSpace();

    /**
     * @return Positive (generally 2^n) cluster size, <code>0</code> if the cluster size is unknown.
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
