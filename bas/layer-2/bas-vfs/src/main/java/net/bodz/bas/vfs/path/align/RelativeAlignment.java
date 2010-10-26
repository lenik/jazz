package net.bodz.bas.vfs.path.align;

import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathException;

public class RelativeAlignment
        extends TruncateOnlyAlignment {

    @Override
    public IPath align(IPath context)
            throws PathException {
        return context;
    }

}
