package net.bodz.bas.vfs.path.align;

import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathException;

/**
 * The alignment determines how to anchor to the context path.
 */
public interface IPathAlignment {

    /**
     * Get the aligned context path.
     * 
     * @return non-<code>null</code> path object.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    IPath align(IPath context)
            throws PathException;

    // IPath move(IPath context, IPath path) throws PathException;

    // IPath precalc(IPath path, IPathAlignment newAnchor);

    RelativeAlignment RELATIVE = new RelativeAlignment();
    RootAlignment ROOT = new RootAlignment();
    RootLayerAlignment ROOT_LAYER = new RootLayerAlignment();

}
