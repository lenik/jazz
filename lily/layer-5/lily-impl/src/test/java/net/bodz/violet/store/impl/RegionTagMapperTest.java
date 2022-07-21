package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.store.RegionTag;
import net.bodz.violet.store.RegionTagSamples;

public class RegionTagMapperTest
        extends AbstractMapperTest<RegionTag, RegionTagMask, RegionTagMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public RegionTag buildSample() {
        return RegionTagSamples.build();
    }

}
