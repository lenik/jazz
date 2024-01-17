package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.RegionLevel;

/**
* @label RegionLevel
*/
@ObjectType(RegionLevel.class)
public class RegionLevelIndex
        extends CoIndex<RegionLevel, RegionLevelCriteriaBuilder> {

    public RegionLevelIndex() {
    }

}
