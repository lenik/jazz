package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.store.RegionTag;
import net.bodz.violet.store.RegionTagSamples;

public class RegionTagManagerTest
        extends AbstractManagerTest<RegionTag, RegionTagMapper, RegionTagManager> {

    @Override
    public RegionTag buildSample()
            throws Exception {
        RegionTagSamples a = new RegionTagSamples();
        return a.buildWired(tables);
    }

}
