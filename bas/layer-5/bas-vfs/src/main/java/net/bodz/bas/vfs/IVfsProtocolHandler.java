package net.bodz.bas.vfs;

import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public interface IVfsProtocolHandler
        extends IPriority {

    int HIGH_PRIORITY = -100;
    int NORMAL_PRIORITY = 0;
    int LOW_PRIORITY = 100;

    /**
     * If this path resolver is registered in non-explicit mode, the accepts function is called to
     * check if the protocol is matched.
     * 
     * @param protocol
     *            non-empty protocol name.
     * @return <code>true</code> if specified <code>protocol</code> is accepted by this path
     *         resolver.
     */
    boolean accepts(String protocol);

    /**
     * The full qualified path string.
     * 
     * @return non-<code>null</code> path.
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath parse(String fqPath)
            throws BadPathException;

    IFile resolve(IPath path)
            throws FileResolveException;

}
