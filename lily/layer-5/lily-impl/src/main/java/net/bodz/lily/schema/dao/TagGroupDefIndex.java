package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.TagGroupDef;

/**
* @label TagGroupDef
*/
@ObjectType(TagGroupDef.class)
public class TagGroupDefIndex
        extends CoIndex<TagGroupDef, TagGroupDefMask> {

    public TagGroupDefIndex() {
    }

}