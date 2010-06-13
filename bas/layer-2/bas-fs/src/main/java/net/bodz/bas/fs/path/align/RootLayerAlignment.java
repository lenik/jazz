package net.bodz.bas.fs.path.align;

import net.bodz.bas.fs.path.IPath;
import net.bodz.bas.fs.path.PathException;

public class RootLayerAlignment
        extends TruncateOnlyAlignment {

    @Override
    public IPath align(IPath context)
            throws PathException {
        return context.getRootLayer();
    }

}
