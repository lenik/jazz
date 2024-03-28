package net.bodz.bas.potato.element;

import net.bodz.bas.t.iterator.PrefetchedIterator;

public class AncestorsToRootIterator
        extends PrefetchedIterator<IProperty> {

    IProperty node;

    public AncestorsToRootIterator(IProperty start, boolean includeStart) {
        if (includeStart)
            this.node = start;
        else
            this.node = start.getSuperProperty();
    }

    @Override
    protected IProperty fetch() {
        if (node == null)
            return end();
        IProperty ret = node;
        node = node.getSuperProperty();
        if (ret == null)
            return end();
        return ret;
    }

}
