package net.bodz.lily.schema.meta;

import java.util.TreeSet;

public class TagDefSet
        extends TreeSet<TagDef> {

    private static final long serialVersionUID = 1L;

    public TagDefSet() {
        super(TagDefLabelComparator.instance);
    }

}
