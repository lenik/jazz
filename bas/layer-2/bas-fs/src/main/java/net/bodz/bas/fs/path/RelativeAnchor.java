package net.bodz.bas.fs.path;

public class RelativeAnchor
        extends ChopPathAnchorPoint {

    @Override
    public IPath anchor(IPath context)
            throws PathException {
        return context;
    }

}
