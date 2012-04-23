package net.bodz.bas.vfs.path;

public interface IGenericPathResolver
        extends IPathResolver {

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

}
