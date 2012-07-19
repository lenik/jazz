package net.bodz.bas.vfs.path;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.align.IPathAlignment;

/**
 * @see org.apache.commons.vfs.FileName
 */
public interface IPath
        extends Serializable {

    String SEPARATOR = "/";
    char SEPARATOR_CHAR = '/';

    /**
     * The alignment is used to anchor one path to another.
     * 
     * @return non-<code>null</code> value.
     */
    IPathAlignment getAlignment();

    /**
     * @return non-<code>null</code> file system.
     */
    IFileSystem getFileSystem();

    /**
     * The same as:
     * 
     * <pre>
     * getFileSystem().resolve(this.getLocalPath)
     * </pre>
     * 
     * @return non-null {@link IFile} which this path refers to.
     * @throws VFSException
     *             If error occurred when resolve the path.
     */
    IFile toFile()
            throws FileResolveException;

    /**
     * The same as
     * 
     * <pre>
     * getVolume().getDeviceFile().getPath()
     * </pre>
     * 
     * @return <code>null</code> If this is the root layer.
     */
    IPath getParentLayer();

    /**
     * If there is no parent layer, this path represents the current layer.
     * 
     * @return non-<code>null</code> path.
     */
    IPath getRootLayer();

    /**
     * Generally the parent path is the path without the last entry.
     * 
     * @return <code>null</code> If no parent.
     */
    IPath getParent();

    /**
     * @return <code>null</code> If the specified level of parent doesn't exist.
     */
    IPath getParent(int n);

    /**
     * @return non-<code>null</code> root path, a root should have no parent, and generally has no
     *         entry.
     */
    IPath getRoot();

    /**
     * The alignment of the given <code>path</code> is depended on whether the specified
     * <code>path</code> is starts with `/'.
     */
    IPath join(String path)
            throws BadPathException;

    /**
     * Join two path, using the alignment of the specified <code>path</code>.
     * 
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath join(IPath path)
            throws BadPathException;

    /**
     * The relative path biased from <code>basePath</code>.
     */
    IPath getRelativePath(IPath basePath);

    /**
     * Get the local path with-in this layer.
     * <p>
     * You can get the same path object by calling {@link IFileSystem#parse(String)} of
     * {@link #getFileSystem() this file system} with the local path.
     * 
     * @return non-<code>null</code> path string.
     */
    String getLocalPath();

    /**
     * @return non-<code>null</code> entry array of local path.
     */
    String[] getLocalEntries();

    /**
     * @return Count of entries of local path.
     */
    int getLocalEntryCount();

    /**
     * @param index
     *            0-based entry index.
     * @return The indexed entry.
     * @throws IndexOutOfBoundsException
     *             If <code>index</code> is out of range.
     */
    String getLocalEntry(int index);

    /**
     * The dir name is the left part of a path without the base name. The return value of
     * {@link #getDirName()} is the same as {@link #getParent}. {@link #getLocalPath()}.
     * <p>
     * If there is no parent, <code>null</code> is returned.
     * 
     * @return The dir name with no trailing slash.
     */
    String getDirName();

    /**
     * The base name is the rightmost part of a path without any parent dir names.
     * 
     * @return non-<code>null</code> base name, with no leading slash.
     */
    String getBaseName();

    /**
     * Remove extension (with dot) from the base name. If there is no extension, the result is the
     * same as the base name.
     * 
     * @return non-<code>null</code> string. The extension name and dot(.) is removed.
     */
    String getStrippedName();

    /**
     * Get the extension name, without dot(.).
     * 
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtension();

    /**
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtension(boolean withDot);

    /**
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtension(boolean withDot, int maxWords);

    /**
     * Get full path with all parent layers.
     * 
     * @return non-null URL string, which can be resolved to the same path.
     */
    String getURL();

    /**
     * Convert this path object to a {@link URL}.
     * 
     * @throws MalformedURLException
     *             If it couldn't build a {@link URL} object to represent this path.
     */
    URL toURL()
            throws MalformedURLException;

    /**
     * Convert this path object to a {@link URI}.
     * 
     * @throws URISyntaxException
     *             If this path can't be represented in RFC 2396 URI syntax.
     */
    URI toURI()
            throws URISyntaxException;

    /**
     * Get canonicalized form of local part of this path.
     */
    IPath canonicalize(PathCanonicalizeOptions canonicalizeOptions)
            throws FileResolveException;

    /**
     * Format the path in specific path format.
     * <p>
     * This is a shortcut for
     * 
     * <pre>
     * getVolume().format(this, pathFormat)
     * </pre>.
     * 
     * @param pathFormat
     *            non-<code>null</code> {@link PathFormat} object.
     */
    String format(PathFormat pathFormat);

}
