package net.bodz.bas.vfs.path;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileContainer;
import net.bodz.bas.vfs.RelativeFileContainer;
import net.bodz.bas.vfs.path.align.IPathAlignment;

/**
 * @see org.apache.commons.vfs.FileName
 */
public interface IPath {

    String SEPARATOR = "/";
    char SEPARATOR_CHAR = '/';

    /**
     * @return non-<code>null</code> file container, for relative path, a
     *         {@link RelativeFileContainer} is returned.
     */
    IFileContainer getContainer();

    /**
     * The alignment is used to anchor one path to another.
     * 
     * @return non-<code>null</code> value.
     */
    IPathAlignment getAlignment();

    /**
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
    IPath resolve(String path)
            throws PathException;

    /**
     * Join two path, using the alignment of the specified <code>path</code>.
     * 
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath resolve(IPath path)
            throws PathException;

    int getEntryCount();

    String getEntry(int index);

    String[] getEntries();

    /**
     * Get full path with all parent layers.
     * 
     * @return non-null URL string, which can be resolved to the same path.
     */
    String getURL();

    URI toURI()
            throws URISyntaxException;

    URL toURL()
            throws MalformedURLException;

    /**
     * @return non-null {@link IFile} which this path refers to.
     */
    IFile toFile();

    /**
     * Get the local path with-in this layer.
     * <p>
     * You can get the same path object by calling {@link IVolume#resolve(String)} of
     * {@link #getVolume() this file system} with the local path.
     * 
     * @return non-null path string.
     */
    String getPath();

    /**
     * Returns the same as {@link #getParent}.{@link #getPath()}. If there is no parent,
     * <code>null</code> is returned.
     */
    String getDirName();

    /**
     * @return non-<code>null</code> base name.
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
     * Get the extension name, with dot(.).
     * 
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtensionWithDot();

    /**
     * Convert the path of the last layer to absolute form. The parent layer isn't affected.
     */
    IPath getAbsolutePath();

    int FOLLOW_SYMLINK = 1;
    int SYMLINK_MUST_EXIST = 2;

    /**
     * @see #FOLLOW_SYMLINK
     * @see #SYMLINK_MUST_EXIST
     */
    IPath getCanonicalPath(int options)
            throws IOException;

    IPath getRelativePath(IPath path);

    /**
     * The default format result should be the same to {@link #toString()}. When the format
     */
    String format(PathFormatOptions formatOptions);

}
