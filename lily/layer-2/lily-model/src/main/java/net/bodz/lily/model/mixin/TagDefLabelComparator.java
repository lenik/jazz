package net.bodz.lily.model.mixin;

import net.bodz.bas.t.order.AbstractNonNullComparator;
import net.bodz.lily.model.base.schema.TagDef;

public class TagDefLabelComparator
        extends AbstractNonNullComparator<TagDef> {

    @Override
    public int compareNonNull(TagDef o1, TagDef o2) {
        String label1 = o1.getLabel();
        String label2 = o2.getLabel();
        int cmp = label1.compareTo(label2);
        if (cmp != 0)
            return cmp;
        return -1;
    }

    public static final TagDefLabelComparator instance = new TagDefLabelComparator();

}
