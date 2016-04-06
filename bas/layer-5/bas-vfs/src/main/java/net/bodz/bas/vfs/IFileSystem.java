package net.bodz.bas.vfs;

import net.bodz.bas.ctx.sys.UserDirScr;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public interface IFileSystem {

    IVfsDriver getDriver(String protocol);

    /**
     * Set the path parser of specific protocol, if the protocol is in use, return false.
     * 
     * @return <code>false</code> If the protocol name is in use.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    boolean addDriver(String protocol, IVfsDriver driver);

    /**
     * Remove a path parser by protocol.
     * 
     * @throws NullPointerException
     *             If <code>protocol</code> is <code>null</code>.
     */
    void removeDriver(String protocol);

    /**
     * Add a generic path parser when no protocol is matched.
     * 
     * @param driver
     *            A generic path resolver to be added.
     * @param priority
     *            The priority of this generic path parser, smaller integer will be evaluated first.
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     * @see IGenericPathParser#LOW_PRIORITY
     * @see IGenericPathParser#NORMAL_PRIORITY
     * @see IGenericPathParser#HIGH_PRIORITY
     */
    void addGenericDriver(IVfsDriver driver, int priority);

    /**
     * Remove a generic path parser.
     * 
     * @param driver
     *            A generic path resolver to be removed.
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     */
    void removeGenericDriver(IVfsDriver driver);

    /**
     * Get the context path (or "current directory").
     * <p>
     * The context path is used to expand a relative path to absolute path.
     * <p>
     * USING WITH CAUTION: Context path should not be cached.
     * 
     * @return non-<code>null</code> context path, whose alignment should be absolute.
     * @see UserDirScr
     */
    IPath getContextPath();

    /**
     * Change the context path (or "current directory").
     * <p>
     * The context path is used to expand a relative path to absolute path.
     * 
     * Change context path won't affect those already joined relative paths,
     * <p>
     * USING WITH CAUTION: Context path should not be cached.
     * 
     * @param Non
     *            -<code>null</code> context path to be set.
     */
    void setContextPath(IPath path);

    /**
     * Parse unaligned (i.e., relative) path string within this path system.
     * 
     * @param path
     *            The path string to be resolved.
     * @return Non-<code>null</code> resolved path object.
     */
    IPath parse(String path)
            throws BadPathException;

    IFile resolve(IPath path)
            throws FileResolveException;

    IFile resolve(IPath path, FileResolveOptions options)
            throws FileResolveException;

}
