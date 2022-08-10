package net.bodz.violet.store.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.store.RegionTag;
import net.bodz.violet.store.RegionTagSamples;

public class RegionTagMapperTest
        extends AbstractMapperTest<RegionTag, RegionTagMask, RegionTagMapper> {

    @Override
    public RegionTag buildSample() {
        return RegionTagSamples.build();
    }

}
