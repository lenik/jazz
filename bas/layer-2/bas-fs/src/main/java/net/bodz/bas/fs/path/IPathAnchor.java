package net.bodz.bas.fs.path;

import net.bodz.bas.collection.preorder.IPreorder;

public interface IPathAnchor
        extends IPreorder<IPathAnchor> {

    /**
     * Get the aligned context path.
     * 
     * @return non-<code>null</code> path object.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    IPath anchor(IPath context)
            throws PathException;

    IPath refactor(IPath context, IPath path, IPathAnchor newAnchor);

    IPathAnchorPoint RELATIVE = new RelativeAnchor();
    IPathAnchorPoint ABSOLUTE = new RootAnchor();
    IPathAnchorPoint PARENT_LAYER = new ParentLayerAnchor();
    IPathAnchorPoint ROOT_LAYER = new RootLayerAnchor();

}
