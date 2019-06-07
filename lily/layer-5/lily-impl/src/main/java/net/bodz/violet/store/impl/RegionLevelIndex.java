package net.bodz.violet.store.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.RegionLevel;

/**
 * Region Level
 */
@ObjectType(RegionLevel.class)
public class RegionLevelIndex
        extends CoIndex<RegionLevel, RegionLevelMask> {

    public RegionLevelIndex() {
    }

}
