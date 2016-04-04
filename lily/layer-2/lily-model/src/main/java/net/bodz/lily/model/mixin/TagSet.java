package net.bodz.lily.model.mixin;

import java.util.TreeSet;

import net.bodz.lily.model.base.schema.TagDef;

public class TagSet
        extends TreeSet<TagDef> {

    private static final long serialVersionUID = 1L;

    public TagSet() {
        super(TagDefLabelComparator.instance);
    }

}
