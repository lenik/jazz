package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.RegionSamples;

public class RegionMapperTest
        extends AbstractTableTest<Region, RegionMapper> {

    @Override
    public Region buildSample()
            throws Exception {
        RegionSamples a = new RegionSamples();
        return a.buildWired(tables);
    }

}
