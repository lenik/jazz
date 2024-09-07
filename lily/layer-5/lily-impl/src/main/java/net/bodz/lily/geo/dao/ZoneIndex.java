package net.bodz.lily.geo.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.geo.Zone;
import net.bodz.lily.model.base.CoIndex;

/**
* @label Zone
*/
@ObjectType(Zone.class)
public class ZoneIndex
        extends CoIndex<Zone, ZoneCriteriaBuilder> {

    public ZoneIndex() {
    }

}
