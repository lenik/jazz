package net.bodz.bas.vfs.path;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.FileResolveOptions;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.align.IPathAlignment;

/**
 * @see org.apache.commons.vfs.FileName
 */
public interface IPath
        extends IPathEntries,
                Serializable {

    String SEPARATOR = "/";
    int SEPARATOR_LEN = SEPARATOR.length();
    char SEPARATOR_CHAR = '/';

    String getProtocol();

    /**
     * Get the device specifier.
     *
     * @return Device specifier. Return <code>null</code> if the device specifier is not available.
     */
    String getDeviceSpec();

    String getDeviceSpecSeparator();

    /**
     * Resolve this path using the default VFS manager.
     */
    IFile toFile()
            throws IOException;

    IFile toFile(FileResolveOptions options)
            throws IOException;

    /**
     * The alignment is used to anchor one path to another.
     *
     * @return Non-<code>null</code> value.
     */
    default IPathAlignment getAlignment() {
        return IPathAlignment.RELATIVE;
    }

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

    @NotNull
    String getLocalPath();

//    /**
//     * Get the local path with-in this layer.
//     *
//     * @return Non-<code>null</code> path string.
//     */
//    @NotNull
//    String getLocalPath();

    /**
     * The dir name is the left part of a path without the base name. The return value of
     * {@link #getDirName()} is the same as {@link #getParent}. {@link #joinEntries()}.
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
    String getName();

    /**
     * Get the extension name, without dot(<code>.</code>).
     *
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    default String getExtension() {
        return getExtension(1);
    }

    /**
     * Get the extension name, with dot(<code>.</code>).
     *
     * @return Empty string(<code>""</code>) if file doesn't have a name, or extension.
     */
    @NotNull
    default String getDotExtension() {
        return getDotExtension(1);
    }

    /**
     * Get the enhanced-extension name, without the  leading dot(<code>.</code>).
     * <p>
     * An enhanced extension may contains many words, like ".tar.gz".
     *
     * @param maxWords
     *            Only less then these words maybe treated as extension name.
     * @return <code>null</code> if file doesn't have a name, or file doesn't have
     *         an extension.
     * @throws IllegalArgumentException
     *             If <code>maxWords</code> is less than 1.
     */
    String getExtension(int maxWords);

    /**
     * Get the enhanced-extension name, with the leading dot(<code>.</code>).
     * <p>
     * An enhanced extension may contains many words, like ".tar.gz".
     *
     * @param maxWords
     *            Only less then these words maybe treated as extension name.
     * @return empty string  if file doesn't have a name, or  file doesn't have
     *         an extension.
     * @throws IllegalArgumentException
     *             If <code>maxWords</code> is less than 1.
     */
    @NotNull
    String getDotExtension(int maxWords);

    /**
     * Whether the path is ended with '/'. An entered path also means it is a directory.
     *
     * @return <code>true</code> if the path is ended with slash.
     */
    boolean isEntered();

    /**
     * Return the same path in entered form.
     *
     * @return this if already entered, or an entered form.
     */
    @NotNull
    IPath enter();

    /**
     * Return the same path in non-entered form.
     *
     * @return this if not entered, or a non-entered form.
     */
    @NotNull
    IPath escape();

    /**
     * Join this path and the special path.
     *<p>
     * The alignment of the result path is equal to this, if <code>spec</code> is a relative path.
     * Otherwise, the alignment of the result path is equal to spec.
     *
     * <p>
     * Examples:
     * <ul>
     * <li>foo/bar + red/apple = foo/red/apple
     * <li>foo/bar/ + red/apple = foo/bar/red/apple
     * <li>foo/bar + /red/apple = /red/apple
     * <li>foo + red/apple = red/apple
     * </ul>
     *
     * @param other
     *            Non-<code>null</code> special path after this path.
     * @return Joined path.
     */
    IPath resolve(@NotNull String other)
            throws BadPathException;

    /**
     * Join this path and special paths.
     * <p>
     * This is the same as nesting {@link #resolve(String)} calls, with <code>null</code> spec skipped.
     *
     * @param others
     *            Special path arrays. Null element is ignored.
     * @return Joined path.
     */
    IPath resolve(@NotNull String... others)
            throws BadPathException;

    /**
     * Join two path, using the alignment of the other path.
     * @see java.nio.file.Path#resolve(Path)
     */
    IPath resolve(@NotNull IPath other)
            throws BadPathException;

    /**
     * Join this path and special paths.
     * <p>
     * This is the same as nesting {@link #resolve(IPath)} calls, with <code>null</code> spec skipped.
     *
     * @param others
     *            Special path arrays. Null element is ignored.
     * @return Joined path.
     */
    IPath resolve(@NotNull IPath... others)
            throws BadPathException;

    /**
     * Get the path relative to the specified base path.
     *
     * @param basePath
     *            The base path which the returned path will be relative to.
     * @return The relative aligned path. Returns <code>null</code> if this path isn't relative to
     *         the <code>basePath</code>.
     */
    IPath getRelativePathTo(@NotNull IPath basePath);

    /**
     * Convert this path object to a {@link URI}.
     *
     * @throws URISyntaxException
     *             If this path can't be represented in RFC 2396 URI syntax.
     */
    @NotNull
    URI toURI()
            throws URISyntaxException;

    /**
     * Convert this path object to a {@link URL}.
     *
     * @throws MalformedURLException
     *             If it couldn't build a {@link URL} object to represent this path.
     */
    @NotNull
    default URL toURL()
            throws MalformedURLException {
        String url = getURLString();
        URI uri = URI.create(url);
        return uri.toURL();
    }

    /**
     * Get full path with all parent layers.
     *
     * @return non-null URL string, which can be resolved to the same path.
     */
    @NotNull
    String getURLString();

    @NotNull
    IPath normalize();

    @NotNull
    default IPath toRealPath()
            throws FileResolveException {
        return normalize();
    }

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
    @NotNull
    String format(PathFormat pathFormat);

    /**
     * Get the default string representation of this path.
     * <p>
     * This should be the same as the {@link PathFormats#STATIC default}
     * {@link #format(PathFormat) format}.
     *
     * @return Default string representation of this path.
     */
    @NotNull
    default String format() {
        return format(PathFormats.DISPLAY);
    }

}
