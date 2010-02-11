package net.bodz.bas.fs.path;

public class ParentLayerAnchor
        extends ChopPathAnchorPoint {

    @Override
    public IPath anchor(IPath context)
            throws PathException {
        return context.getParentLayer();
    }

}
