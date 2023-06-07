package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.Region;

/**
* @label Region
*/
@ObjectType(Region.class)
public class RegionIndex
        extends CoIndex<Region, RegionMask> {

    public RegionIndex() {
    }

}
