package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.TagDef;

/**
* @label TagDef
*/
@ObjectType(TagDef.class)
public class TagDefIndex
        extends CoIndex<TagDef, TagDefMask> {

    public TagDefIndex() {
    }

}