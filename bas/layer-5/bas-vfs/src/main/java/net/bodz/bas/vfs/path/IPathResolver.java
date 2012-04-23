package net.bodz.bas.vfs.path;

public interface IPathResolver {

    // String getPreferredProtocolName();

    /**
     * The request path with protocol name.
     * 
     * @return non-<code>null</code> path.
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath resolve(String path)
            throws BadPathException;

}
