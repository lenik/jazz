package net.bodz.bas.vfs.path.align;

import java.io.Serializable;

import net.bodz.bas.vfs.path.IPath;

/**
 * The alignment determines how to anchor to the context path.
 */
public interface IPathAlignment
        extends Serializable {

    /**
     * Get the aligned context path (or, parent-path).
     * 
     * @return non-<code>null</code> path object.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    IPath align(IPath context);

    /**
     * The same as: <code>align(context).join(path)</code>.
     */
    // IPath join(IPath context, IPath path);

    /**
     * Format a bare path to a path with the alignment hint.
     * 
     * The returned path string should be parsable by the path system, and it should be parsed to a
     * path with the same alignment.
     * 
     * @return Non-<code>null</code> path string with alignment info.
     * @throws NullPointerException
     *             If <code>barePath</code> is <code>null</code>.
     */
    String format(String barePath);

    // IPath move(IPath context, IPath path) throws PathException;

    // IPath precalc(IPath path, IPathAlignment newAnchor);

    ParentAlignment RELATIVE = new ParentAlignment(0);
    RootAlignment ROOT = new RootAlignment();
    RootLayerAlignment ROOT_LAYER = new RootLayerAlignment();

}
