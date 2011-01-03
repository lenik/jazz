package net.bodz.bas.vfs.path;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVolume;
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
     * @return non-<code>null</code> file volume.
     */
    IVolume getVolume();

    /**
     * The same as:
     * 
     * <pre>
     * getVolume().resolveFile(this.getLocalPath)
     * </pre>
     * 
     * @return non-null {@link IFile} which this path refers to.
     */
    IFile toFile();

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
    IPath join(String path)
            throws PathException;

    /**
     * Join two path, using the alignment of the specified <code>path</code>.
     * 
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath join(IPath path)
            throws PathException;

    /**
     * The relative path biased from <code>basePath</code>.
     */
    IPath getRelativePath(IPath basePath);

    /**
     * Get the local path with-in this layer.
     * <p>
     * You can get the same path object by calling {@link IVolume#resolve(String)} of
     * {@link #getVolume() this file system} with the local path.
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
     * Returns the same as {@link #getParent}.{@link #getLocalPath()}. If there is no parent,
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
     * Get full path with all parent layers.
     * 
     * @return non-null URL string, which can be resolved to the same path.
     */
    String getURL();

    URL toURL()
            throws MalformedURLException;

    URI toURI()
            throws URISyntaxException;

    /**
     * Get canonicalized form of local part of this path.
     */
    IPath canonicalize(PathCanonicalizeOptions canonicalizeOptions)
            throws IOException;

    /**
     * The default format result should be the same to {@link #toString()}.
     * 
     * @param formatOptions
     *            May be <code>null</code>.
     */
    String format(PathFormatOptions formatOptions);

}
