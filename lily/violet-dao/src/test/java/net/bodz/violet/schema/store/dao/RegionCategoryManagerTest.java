package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.store.RegionCategory;
import net.bodz.violet.schema.store.RegionCategorySamples;

public class RegionCategoryManagerTest
        extends AbstractManagerTest<RegionCategory, RegionCategoryMapper, RegionCategoryManager> {

    @Override
    public RegionCategory buildSample()
            throws Exception {
        RegionCategorySamples a = new RegionCategorySamples();
        return a.buildWired(tables);
    }

}
