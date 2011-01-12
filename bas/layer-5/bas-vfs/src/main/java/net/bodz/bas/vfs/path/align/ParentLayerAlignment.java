package net.bodz.bas.vfs.path.align;

import net.bodz.bas.util.exception.NotImplementedException;
import net.bodz.bas.vfs.path.IPath;

public class ParentLayerAlignment
        extends TruncateOnlyAlignment {

    public static final int FAIL_DEFAULT = 0;
    public static final int FAIL_ROOT = 1;
    public static final int FAIL_PARENT = 2;

    private int failMode;

    public ParentLayerAlignment(int failMode) {
        this.failMode = failMode;
    }

    @Override
    public IPath align(IPath context) {
        if (context == null)
            throw new NullPointerException("context");

        IPath parentLayer = context.getParentLayer();
        if (parentLayer != null)
            return parentLayer;

        switch (failMode) {
        case FAIL_ROOT:
            return context.getRoot();
        case FAIL_PARENT:
            IPath parent = context.getParent();
            if (parent == null)
                return context;
        case FAIL_DEFAULT:
        default:
            return context;
        }
    }

    @Override
    public String decorate(String localPath) {
        throw new NotImplementedException();
    }

}
