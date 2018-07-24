package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.RegionSamples;

public class RegionMapperTest
        extends AbstractMapperTest<Region, RegionMask, RegionMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Region buildSample() {
        return RegionSamples.build();
    }

}
