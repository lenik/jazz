package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.store.Region;
import net.bodz.violet.schema.store.RegionSamples;

public class RegionManagerTest
        extends AbstractManagerTest<Region, RegionMapper, RegionManager> {

    @Override
    public Region buildSample()
            throws Exception {
        RegionSamples a = new RegionSamples();
        return a.buildWired(tables);
    }

}
