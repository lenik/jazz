package net.bodz.bas.fs.path;

import net.bodz.bas.fs.IFileContainer;

public interface IPathResolver {

    /**
     * If the path resolver is registered in non-explicit mode, the accepts function is called to
     * check if the protocol is matched.
     * 
     * @param protocol
     *            non-empty protocol name.
     * @return <code>true</code> if specified <code>protocol</code> is accepted by this path
     *         resolver.
     */
    boolean accepts(String protocol);

    /**
     * Returns the length of the file container prefix. The returned value is then used to split the
     * path into container path and container-specific path.
     * 
     * @return 0 if path is relative, or positive index to the start of container-specific path.
     */
    int getPrefixLength(String path);

    /**
     * @return non-<code>null</code> {@link IFileContainer}, if the path is relative, a
     *         {@link IPathResolver} is returned.
     * @throws PathException
     *             If container-path is in bad format.
     */
    IFileContainer getFileContainer(String containerPath)
            throws PathException;

    /**
     * @return non-<code>null</code> path.
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath resolve(String path)
            throws PathException;

}
