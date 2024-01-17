package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.RegionTag;
import net.bodz.violet.store.RegionTagSamples;

public class RegionTagMapperTest
        extends AbstractTableTest<RegionTag, RegionTagMapper> {

    @Override
    public RegionTag buildSample()
            throws Exception {
        RegionTagSamples a = new RegionTagSamples();
        return a.buildWired(tables);
    }

}
