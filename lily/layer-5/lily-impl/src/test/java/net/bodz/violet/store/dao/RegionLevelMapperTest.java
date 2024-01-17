package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.RegionLevel;
import net.bodz.violet.store.RegionLevelSamples;

public class RegionLevelMapperTest
        extends AbstractTableTest<RegionLevel, RegionLevelCriteriaBuilder, RegionLevelMapper> {

    @Override
    public RegionLevel buildSample()
            throws Exception {
        RegionLevelSamples a = new RegionLevelSamples();
        return a.build();
    }

}
