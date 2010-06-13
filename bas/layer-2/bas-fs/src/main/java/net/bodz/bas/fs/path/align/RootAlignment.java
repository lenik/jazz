package net.bodz.bas.fs.path.align;

import net.bodz.bas.fs.path.IPath;

public class RootAlignment
        extends TruncateOnlyAlignment {

    @Override
    public IPath align(IPath context) {
        return context.getRoot();
    }

}
