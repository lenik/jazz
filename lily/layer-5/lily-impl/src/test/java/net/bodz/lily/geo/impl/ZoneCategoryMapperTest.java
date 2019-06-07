package net.bodz.lily.geo.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.geo.ZoneCategory;
import net.bodz.lily.geo.ZoneCategorySamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ZoneCategoryMapperTest
        extends AbstractMapperTest<ZoneCategory, ZoneCategoryMask, ZoneCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ZoneCategory buildSample() {
        return ZoneCategorySamples.build();
    }

}
