package net.bodz.bas.fs.path;

public class RootLayerAnchor
        extends ChopPathAnchorPoint {

    @Override
    public IPath anchor(IPath context)
            throws PathException {
        return context.getRootLayer();
    }

}
