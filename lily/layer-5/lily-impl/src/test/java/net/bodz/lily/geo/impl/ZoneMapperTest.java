package net.bodz.lily.geo.impl;

import net.bodz.lily.geo.Zone;
import net.bodz.lily.geo.ZoneCategory;
import net.bodz.lily.geo.ZoneSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class ZoneMapperTest
        extends AbstractMapperTest<Zone, ZoneMask, ZoneMapper> {

    @Override
    public Zone buildSample() {
        ZoneCategory category = tables.pickAny(ZoneCategoryMapper.class, "zonecat");
        return ZoneSamples.build(category);
    }

}
