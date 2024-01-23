package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.store.RegionLevel;
import net.bodz.violet.store.RegionLevelSamples;

public class RegionLevelManagerTest
        extends AbstractManagerTest<RegionLevel, RegionLevelMapper, RegionLevelManager> {

    @Override
    public RegionLevel buildSample()
            throws Exception {
        RegionLevelSamples a = new RegionLevelSamples();
        return a.buildWired(tables);
    }

}
