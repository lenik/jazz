package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;
import org.apache.commons.vfs.FileSystem;

import net.bodz.bas.traits.IAttributes;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

/**
 * @see org.apache.commons.vfs.FileSystem
 */
public interface IFileSystem {

    /**
     * Return the underlying device on which this volume is implemented.
     * 
     * @return <code>null</code> If this volume is a top-level (physical) volume.
     */
    IFile getDeviceFile();

    /**
     * For some file systems (like DOS) there are more then one root.
     * 
     * @return non-<code>null</code> root file array.
     */
    IFile[] getRootFiles();

    /**
     * This returns the path attributes from the root files.
     * 
     * @return non-<code>null</code> root path array.
     */
    IPath[] getRootPaths();

    /**
     * @param localPath
     *            non-<code>null</code> path string with-in this volume.
     * @return non-<code>null</code> {@link IPath} reference.
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     * @throws BadPathException
     *             If <code>localPath</code> is invalid.
     */
    IPath parse(String localPath)
            throws BadPathException;

    /**
     * @param localPath
     *            non-<code>null</code> path string with-in this volume.
     * @return non-<code>null</code> {@link IFile} reference.
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     * @throws VFSException
     *             If <code>localPath</code> is invalid.
     */
    IFile resolve(String localPath)
            throws FileResolveException;

    /**
     * @see FileSystem#getAttribute(String)
     */
    IAttributes getAttributes();

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

    /**
     * Format the given path with-in the volume.
     */
    String format(IPath path, PathFormat pathFormat);

}
