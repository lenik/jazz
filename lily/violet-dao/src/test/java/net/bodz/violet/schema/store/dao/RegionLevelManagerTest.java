package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.store.RegionLevel;
import net.bodz.violet.schema.store.RegionLevelSamples;

public class RegionLevelManagerTest
        extends AbstractManagerTest<RegionLevel, RegionLevelMapper, RegionLevelManager> {

    @Override
    public RegionLevel buildSample()
            throws Exception {
        RegionLevelSamples a = new RegionLevelSamples();
        return a.buildWired(tables);
    }

}
