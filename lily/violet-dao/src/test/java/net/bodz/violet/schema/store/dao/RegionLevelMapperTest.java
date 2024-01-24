package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.store.RegionLevel;
import net.bodz.violet.schema.store.RegionLevelSamples;

public class RegionLevelMapperTest
        extends AbstractTableTest<RegionLevel, RegionLevelMapper> {

    @Override
    public RegionLevel buildSample()
            throws Exception {
        RegionLevelSamples a = new RegionLevelSamples();
        return a.buildWired(tables);
    }

}
