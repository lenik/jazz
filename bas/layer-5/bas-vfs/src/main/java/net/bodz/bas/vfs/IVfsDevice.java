package net.bodz.bas.vfs;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.NotLinkException;

import org.apache.commons.vfs.FileSystem;

import net.bodz.bas.tf.std.IAttributes;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

/**
 * @see org.apache.commons.vfs.FileSystem
 */
public interface IVfsDevice {

    IVfsDriver getDriver();

    String getProtocol();

    String getDeviceSpec();

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
    IFile getRootFile();

    /**
     * This returns the path attributes from the root files.
     * 
     * @return non-<code>null</code> root path array.
     */
    IPath getRootPath();

    /**
     * Parse a local path string.
     * <p>
     * The local path is always parsed as absolute path. I.e, if the <code>localPath</code>starts
     * with "../", "./", etc., they are not converted to parent alignments.
     * 
     * @param localPath
     *            Non-<code>null</code> path string with-in this volume.
     * @return Non-<code>null</code> {@link IPath path} reference.
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
     *            non-<code>null</code> path string with-in this device.
     * @return non-<code>null</code> {@link IFile} reference.
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     */
    IFile resolve(String localPath)
            throws BadPathException, FileResolveException;

    /**
     * Resolve a parsed path to a file.
     * 
     * @param _path
     *            The path to be resolved.
     * @return Non-<code>null</code> file reference.
     * @throws ClassCastException
     *             If the path isn't instance of concrete path type.
     */
    IFile resolve(IPath _path)
            throws FileResolveException;

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
    boolean move(String localPathFrom, String localPathTo, CopyOption... options)
            throws BadPathException, IOException;

    /**
     * Create a (symbolic) link at the specified local path, to the target.
     * 
     * @param localPath
     *            The path of the link file.
     * @param targetSpec
     *            Relative path string to the target.
     * @param symbolic
     *            Create a symbolic link rather than hard link.
     * @return <code>false</code> If file is already existed.
     * @throws UnsupportedOperationException
     *             If the underlying device doesn't support symbolic link.
     * @throws BadPathException
     *             If <code>targetSpec</code> is invalid.
     */
    boolean createLink(String localPath, String targetSpec, boolean symbolic)
            throws IOException;

    String readSymbolicLink(String localPath)
            throws NotLinkException, IOException;

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

    // File replicateFile(IFile file, FileSelector selector)
    // throws IOException

}
