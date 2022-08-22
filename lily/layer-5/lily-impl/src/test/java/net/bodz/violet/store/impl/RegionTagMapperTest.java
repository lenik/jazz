package net.bodz.violet.store.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.RegionTag;
import net.bodz.violet.store.RegionTagSamples;

public class RegionTagMapperTest
        extends AbstractTableTest<RegionTag, RegionTagMask, RegionTagMapper> {

    @Override
    public RegionTag buildSample() {
        return RegionTagSamples.build();
    }

}
