package net.bodz.lily.geo.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.geo.Zone;
import net.bodz.lily.geo.ZoneCategory;
import net.bodz.lily.geo.ZoneSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ZoneMapperTest
        extends AbstractMapperTest<Zone, ZoneMask, ZoneMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Zone buildSample() {
        ZoneCategory category = tables.pickAny(ZoneCategoryMapper.class, "zonecat");
        return ZoneSamples.build(category);
    }

}
