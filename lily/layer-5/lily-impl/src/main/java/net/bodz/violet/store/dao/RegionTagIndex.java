package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.RegionTag;

/**
* @label RegionTag
*/
@ObjectType(RegionTag.class)
public class RegionTagIndex
        extends CoIndex<RegionTag, RegionTagMask> {

    public RegionTagIndex() {
    }

}
