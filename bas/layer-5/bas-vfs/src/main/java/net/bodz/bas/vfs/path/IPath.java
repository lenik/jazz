package net.bodz.bas.vfs.path;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.align.IPathAlignment;

/**
 * @see org.apache.commons.vfs.FileName
 */
public interface IPath
        extends Serializable {

    String SEPARATOR = "/";
    char SEPARATOR_CHAR = '/';

    String getProtocol();

    /**
     * Get the scope name. The scope name is used to select the device in the driver.
     * 
     * @return Scope name. Return <code>null</code> if the scope name is not available.
     */
    String getScopeName();

    String getScopeSeparator();

    /**
     * Resolve this path using the default VFS manager.
     */
    IFile resolve()
            throws FileResolveException;

    /**
     * The alignment is used to anchor one path to another.
     * 
     * @return Non-<code>null</code> value.
     */
    IPathAlignment getAlignment();

    /**
     * If there is no parent layer, this path represents the current layer.
     * 
     * @return Non-<code>null</code> path.
     */
    IPath getRootLayer();

    /**
     * Get the parent layer path, or the path of the device file.
     * 
     * @return <code>null</code> If this is the root layer.
     */
    IPath getParentLayer();

    /**
     * @return Non-<code>null</code> root path, a root should have no parent, and generally has no
     *         entry.
     */
    IPath getRoot();

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
     * Get the local path with-in this layer.
     * 
     * @return Non-<code>null</code> path string.
     */
    String getLocalPath();

    /**
     * Get local entry array.
     * 
     * @return Non-<code>null</code> entry array of local path.
     */
    String[] getLocalEntries();

    /**
     * Get the number of local entries.
     * 
     * @return Count of entries of local path.
     */
    int getLocalEntryCount();

    /**
     * Get local entry element by index.
     * 
     * @param index
     *            0-based entry index. Negative integer for reversed index, i.e., <code>-1</code> to
     *            get the last entry.
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
     * Get the extension name, without dot(<code>.</code>).
     * 
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtension();

    /**
     * Get the extension name, with or without dot(<code>.</code>).
     * 
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtension(boolean withDot);

    /**
     * Get the enhanced-extension name, with or without dot(<code>.</code>).
     * 
     * An enhanced extension may contains many words, like ".tar.gz".
     * 
     * @param maxWords
     *            Only less then these words maybe treated as extension name.
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     * @throws IllegalArgumentException
     *             If <code>maxWords</code> is less than 1.
     */
    String getExtension(boolean withDot, int maxWords);

    /**
     * Join this path and the special path.
     * 
     * The alignment of the result path is equal to this, if <code>spec</code> is a relative path.
     * Otherwise, the alignment of the result path is equal to spec.
     * 
     * @param spec
     *            Non-<code>null</code> special path after this path.
     * @return Joined path.
     */
    IPath join(String spec)
            throws BadPathException;

    /**
     * Join this path and special paths.
     * 
     * This is the same as nesting {@link #join(String)} calls, with <code>null</code> spec skipped.
     * 
     * @param specs
     *            Special path arrays. Null element is ignored.
     * @return Joined path.
     */
    IPath join(String... specs)
            throws BadPathException;

    /**
     * Join two path, using the alignment of the other path.
     * 
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath join(IPath spec)
            throws BadPathException;

    /**
     * Join this path and special paths.
     * 
     * This is the same as nesting {@link #join(IPath)} calls, with <code>null</code> spec skipped.
     * 
     * @param specs
     *            Special path arrays. Null element is ignored.
     * @return Joined path.
     */
    IPath join(IPath... specs)
            throws BadPathException;

    /**
     * Get the path relative to the specified base path.
     * 
     * @param basePath
     *            The base path which the returned path will be relative to.
     * @return The relative aligned path. Returns <code>null</code> if this path isn't relative to
     *         the <code>basePath</code>.
     * @throws NullPointerException
     *             If <code>basePath</code> is <code>null</code>.
     */
    IPath getRelativePathTo(IPath basePath);

    /**
     * Get full path with all parent layers.
     * 
     * @return non-null URL string, which can be resolved to the same path.
     */
    String getURLString();

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
     *            Non-<code>null</code> {@link PathFormat} object.
     */
    String format(PathFormat pathFormat);

    /**
     * Get the default string representation of this path.
     * 
     * This should be the same as the {@link PathFormats#DEFAULT default}
     * {@link #format(PathFormat) format}.
     * 
     * @return Default string representation of this path.
     */
    String toString();

}
