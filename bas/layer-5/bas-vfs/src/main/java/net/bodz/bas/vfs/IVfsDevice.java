package net.bodz.bas.vfs;

import java.io.File;
import java.util.List;

import org.apache.commons.vfs.FileSystem;

import net.bodz.bas.traits.IAttributes;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

/**
 * @see org.apache.commons.vfs.FileSystem
 */
public interface IVfsDevice {

    IVfsDriver getDriver();

    /**
     * Return the underlying device on which this volume is implemented.
     * 
     * @return <code>null</code> If this volume is a top-level (physical) volume.
     */
    IFile getDeviceFile();

    /**
     * @see FileSystem#getAttribute(String)
     */
    IAttributes getAttributes();

    /**
     * For some file systems (like DOS) there are more then one root.
     * 
     * @return non-<code>null</code> root file array.
     */
    List<? extends IFile> getRootFiles();

    /**
     * This returns the path attributes from the root files.
     * 
     * @return non-<code>null</code> root path array.
     */
    List<? extends IPath> getRootPaths();

    /**
     * Parse a local path string.
     * <p>
     * The local path is always parsed as absolute path. I.e, if the <code>localPath</code>starts
     * with "../", "./", etc., they are not converted to parent alignments.
     * 
     * @param localPath
     *            Non-<code>null</code> path string with-in this volume.
     * @return Non-<code>null</code> {@link IPath} reference.
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     */
    IPath parse(String localPath)
            throws BadPathException;

    /**
     * Resolve a local path to file.
     * <p>
     * The local path is always parsed as absolute path. I.e, if the <code>localPath</code>starts
     * with "../", "./", etc., they are not converted to parent alignments.
     * 
     * @param localPath
     *            non-<code>null</code> path string with-in this volume.
     * @return non-<code>null</code> {@link IFile} reference.
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     */
    IFile resolve(String localPath)
            throws BadPathException, FileResolveException;

    /**
     * Format the given path with-in the volume.
     */
    String format(String localPath, PathFormat pathFormat);

    /**
     * Renames the file denoted by the local pathnames.
     * 
     * Many aspects of the behavior of this method are inherently platform-dependent: The rename
     * operation might not be able to move a file from one filesystem to another, it might not be
     * atomic, and it might not succeed if a file with the destination abstract pathname already
     * exists. The return value should always be checked to make sure that the rename operation was
     * successful.
     * 
     * @see File#renameTo(File)
     */
    boolean rename(String localPathFrom, String localPathTo)
            throws BadPathException;

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
